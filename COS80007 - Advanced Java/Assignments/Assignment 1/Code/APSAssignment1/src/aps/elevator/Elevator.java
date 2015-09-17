package aps.elevator;

import aps.config.Config;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.shuttle.Shuttle;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import control.APSControl;
import java.awt.geom.Rectangle2D;

/**
 * The {@link Elevator}.
 * <p>
 * This class represents the elevator. The elevator is a composite object that
 * is made up of a variety of components, including the elevator door. The
 * elevator itself can move up and down, between floors.
 * <p>
 * @author szeyick
 */
public class Elevator implements IAPSTimerListener {

    /**
     * The current floor the elevator is on.
     */
    private int currentFloor;

    /**
     * The target floor to move to.
     */
    private int targetFloor;

    /**
     * The elevator door.
     */
    private ElevatorDoor door;

    /**
     * The current direction of the elevator.
     */
    private ElevDirection currentDirection;

    /**
     * The current operation of the elevator.
     */
    private ElevOperation currentElevOperation;

    /**
     * The shuttle.
     */
    private Shuttle shuttle;

    /**
     * The floor to send the elevator to.
     */
    private int destinationFloor;

    private float elevatorCentreX;

    private float elevatorCentreY;

    private float elevatorWidth;

    private float elevatorLength;

    /**
     * The current direction that the elevator is traveling.
     */
    public enum ElevDirection {

        UP, DOWN, IDLE, DEFAULT;
    }

    public enum ElevOperation {

        OPENING_DOOR, CLOSING_DOOR, MOVING, ARRIVED_AT_FLOOR, IDLE, DEPLOY_SHUTTLE;
    }

    /**
     * Constructor.
     */
    public Elevator(APSTimer timer) {
        currentFloor = 0;
        destinationFloor = 0;
        elevatorCentreX = 1.6f;
        elevatorCentreY = (5.75f * 3) / 2;
        elevatorLength = 6.0f;
        elevatorWidth = 5.0f;
        currentDirection = ElevDirection.IDLE;
        currentElevOperation = ElevOperation.IDLE;

        door = new ElevatorDoor(timer, this);
        shuttle = new Shuttle(this, timer);
        timer.addTimerListener(shuttle);
    }

    public ElevOperation getCurrentElevatorOperation() {
        return currentElevOperation;
    }

    /**
     * @return the current floor that the elevator is on.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    // The door should call back to the elevator that it has opened.
    public void elevatorDoorCallback() {
        if (ElevatorDoor.ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
            System.out.println("Door is - " + door.getDoorState().toString());
            currentElevOperation = ElevOperation.IDLE;
            // door.setDoorState(ElevatorDoor.ElevatorDoorState.OPENING);
            // Move to floor.
            // If car is unloaded, then move back to 0
            if (currentFloor != 0) {
                destinationFloor = 0;
                moveToFloor(destinationFloor);
                // Terminate the event here
                ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
                if (!EventType.DEPARTURE.equals(event.getEventType())) {
                    APSControl.getControl().updateEventProcessing(false);
                }
            } else {
                moveToFloor(destinationFloor);
            }
        }
        if (ElevatorDoor.ElevatorDoorState.OPENED.equals(door.getDoorState())) {
            System.out.println("Door is - " + door.getDoorState().toString());
            currentElevOperation = ElevOperation.IDLE;
            // door.setDoorState(ElevatorDoor.ElevatorDoorState.CLOSING);
            // If door is opened and idle, then deploy the trolley.
            System.out.println("Deploy Shuttle X: ");
            currentElevOperation = ElevOperation.DEPLOY_SHUTTLE;
            shuttle.updateShuttleState(Shuttle.ShuttleState.LOCKED);
        }
    }

    public void shuttleCallback() {
        currentElevOperation = ElevOperation.CLOSING_DOOR;
        System.out.println("Shuttle Ready - Close Door");
    }

    /**
     * Stop the elevator from accepting any commands. Elevator should be stopped
     * when the car has been unloaded and elevator has returned to ground.
     */
    public void stopElevator() {
        currentDirection = ElevDirection.IDLE;
        currentElevOperation = ElevOperation.IDLE;
    }

    /**
     * Move the elevator to a floor.
     *
     * @param floorNumber - The floor to move to.
     */
    public void moveToFloor(int floorNumber) {
        targetFloor = floorNumber;
        if (currentFloor < floorNumber) {
            // If the current floor is at a lower floor than the target we need to go up.
            currentDirection = ElevDirection.UP;
        } else if (currentFloor > floorNumber) {
            currentDirection = ElevDirection.DOWN;
        } else {
            currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
        }
    }

    /**
     * Perform an operation on the elevator. Update how far the
     */
    @Override
    public void update(long dt) {
        ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
        // Only move the elevator if there is an event.
        if (event != null) {
            // If the elevator has arrived at the floor we only do something if we are instructed to.
            if (ElevOperation.ARRIVED_AT_FLOOR.equals(currentElevOperation)) {
                // Wait for command
                currentDirection = ElevDirection.IDLE;
                currentElevOperation = ElevOperation.IDLE;
                System.out.println("Elevator has arrived at floor: " + currentFloor);

                // Need to figure out the next command (Whether to open or close the door)
                if (EventType.DEPARTURE.equals(event.getEventType()) && currentFloor == 0
                        && shuttle.getTrolley().containsCar()) {
                    System.out.println("Calling User to Pickup Car...");
                    APSControl.getControl().getUserStation().carReadyForPickup();

                } else {
                    if (ElevatorDoor.ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
                        currentElevOperation = ElevOperation.OPENING_DOOR;
                    } else if (ElevatorDoor.ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
                        currentElevOperation = ElevOperation.CLOSING_DOOR;
                    }
                }
            }
            if (ElevDirection.UP.equals(currentDirection)) {
                currentFloor++;
                currentElevOperation = ElevOperation.MOVING;
                System.out.println("Elevator is moving up");
                if (currentFloor == destinationFloor) {
                    currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
                }
            }
            if (ElevDirection.DOWN.equals(currentDirection)) {
                // Decrement the current floor count.
                currentFloor--;
                currentElevOperation = ElevOperation.MOVING;
                System.out.println("Elevator is moving down");
                if (currentFloor == destinationFloor) {
                    currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
                }
            }
            if (ElevOperation.OPENING_DOOR.equals(currentElevOperation) && ElevatorDoor.ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
                // If the door is currently closed, we need to open.
                door.setDoorState(ElevatorDoor.ElevatorDoorState.OPENING);
                currentElevOperation = ElevOperation.OPENING_DOOR;
            }
            if (ElevOperation.CLOSING_DOOR.equals(currentElevOperation) && ElevatorDoor.ElevatorDoorState.OPENED.equals(door.getDoorState())) {
                // If the door is currently closed, we need to open.
                door.setDoorState(ElevatorDoor.ElevatorDoorState.CLOSING);
                currentElevOperation = ElevOperation.CLOSING_DOOR;
            }
        }

    }

    /**
     * @return the elevator door.
     */
    public ElevatorDoor getDoor() {
        return door;
    }

    /**
     * @return the elevator shuttle.
     */
    public Shuttle getShuttle() {
        return shuttle;
    }

    /**
     * @return the bounds of the elevator.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(elevatorCentreX, elevatorCentreY, elevatorWidth, elevatorLength);
    }
}
