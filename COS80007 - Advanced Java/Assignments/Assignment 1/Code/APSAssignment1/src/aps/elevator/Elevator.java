package aps.elevator;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.config.Config;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;
import aps.shuttle.Shuttle;
import aps.shuttle.ShuttleState;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import aps.control.APSControl;
import java.awt.geom.Rectangle2D;

/**
 * The Elevator.
 * <p>
 * This class represents the elevator. The elevator is a composite object that
 * is made up of a variety of components, including the elevator door. The
 * elevator itself can move up and down, between floors.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class Elevator implements IAPSTimerListener {

    /**
     * The current floor the elevator is on.
     */
    private int currentFloor;

    /**
     * The elevator door component.
     */
    private final ElevatorDoor door;

    /**
     * The current direction of the elevator.
     */
    private ElevDirection currentDirection;

    /**
     * The current operation of the elevator.
     */
    private ElevOperation currentElevOperation;

    /**
     * The shuttle component.
     */
    private final Shuttle shuttle;

    /**
     * The floor to send the elevator to.
     */
    private int destinationFloor;

    /**
     * The centre X coordinate of the elevator.
     */
    private final float elevatorCentreX;

    /**
     * The centre Y coordinate of the elevator.
     */
    private final float elevatorCentreY;

    /**
     * The width of the elevator.
     */
    private final float elevatorWidth;

    /**
     * The length of the elevator.
     */
    private final float elevatorLength;

    /**
     * Constructor.
     */
    public Elevator() {
        Config config = Config.getConfig();

        currentFloor = 0;
        destinationFloor = 0;
        elevatorCentreX = config.LIFT_CENTRE_X;
        elevatorCentreY = (config.BAY_LENGTH * 3) / 2;
        elevatorLength = config.LIFT_LENGTH;
        elevatorWidth = config.LIFT_WIDTH;

        currentDirection = ElevDirection.IDLE;
        currentElevOperation = ElevOperation.IDLE;

        door = new ElevatorDoor(this);
        shuttle = new Shuttle(this);
        APSTimer.getTimer().addTimerListener(shuttle);
    }

    /**
     * @return the current operating state of the elevator.
     */
    public ElevOperation getCurrentElevatorOperation() {
        return currentElevOperation;
    }

    /**
     * @return the current floor that the elevator is on.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Assign the floor the elevator is required to travel to.
     *
     * @param destinationFloor - The floor to send the elevator.
     */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    /**
     * The callback function from the elevator door when it has completed its
     * tasks.
     */
    public void elevatorDoorCallback() {
        if (ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
            currentElevOperation = ElevOperation.IDLE;
            // If car is unloaded, then move back to floor 0
            if (currentFloor != 0) {
                destinationFloor = 0;
                ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
                if (!EventType.DEPARTURE.equals(event.getEventType())) {
                    currentElevOperation = ElevOperation.RESET;
                } else {
                    moveToFloor(destinationFloor);
                }
            } else {
                moveToFloor(destinationFloor);
            }
        }
        if (ElevatorDoorState.OPENED.equals(door.getDoorState())) {
            currentElevOperation = ElevOperation.IDLE;
            // If door is opened and idle, then deploy the trolley.
            currentElevOperation = ElevOperation.DEPLOY_SHUTTLE;
            shuttle.updateShuttleState(ShuttleState.LOCKED);
        }
    }

    /**
     * The callback method for the shuttle when it has completed its operation.
     */
    public void shuttleCallback() {
        currentElevOperation = ElevOperation.CLOSING_DOOR;
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
        currentElevOperation = ElevOperation.MOVING;
        if (currentFloor < floorNumber) {
            currentDirection = ElevDirection.UP;
        } else if (currentFloor > floorNumber) {
            currentDirection = ElevDirection.DOWN;
        }
        else {
            currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
        }
    }

    /**
     * Perform an operation on the elevator. Update the elevator state if it is
     * currently in operation.
     */
    @Override
    public void update(long dt) {
        ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
        // Only move the elevator if there is an event.
        if (event != null) {
            if (ElevOperation.ARRIVED_AT_FLOOR.equals(currentElevOperation)) {
                elevatorArrivedAtFloor(event);
            }
            if (ElevDirection.UP.equals(currentDirection)) {
                currentFloor++;
                currentElevOperation = ElevOperation.MOVING;
                if (currentFloor == destinationFloor) {
                    currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
                }
            } else if (ElevDirection.DOWN.equals(currentDirection)) {
                // Decrement the current floor count.
                currentFloor--;
                currentElevOperation = ElevOperation.MOVING;
                if (currentFloor == destinationFloor) {
                    currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
                }
            }
            if (ElevOperation.OPENING_DOOR.equals(currentElevOperation) && ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
                // If the door is currently closed, we need to open it.
                door.setDoorState(ElevatorDoorState.OPENING);
                currentElevOperation = ElevOperation.OPENING_DOOR;
            }
            if (ElevOperation.CLOSING_DOOR.equals(currentElevOperation) && ElevatorDoorState.OPENED.equals(door.getDoorState())) {
                // If the door is currently open, we need to close.
                door.setDoorState(ElevatorDoorState.CLOSING);
                currentElevOperation = ElevOperation.CLOSING_DOOR;
            }
            if (ElevOperation.RESET.equals(currentElevOperation)) {
                currentFloor--;
                if (currentFloor == destinationFloor) {
                    currentElevOperation = ElevOperation.IDLE;
                    APSControl.getControl().updateEventProcessing(false);
                }
            }
        }
    }

    /**
     * Evaluate the next state to proceed to when the elevator has arrived at
     * its destination floor. If the elevator is on the target floor and it is a
     * collect (DEPARTURE) event, the shuttle will be automatically deployed to
     * retrieve the car, however for a parking event (ARRIVAL), the ground floor
     * door will be opened to retrieve the car from the turntable.
     *
     * @param event - The current parking event.
     */
    private void elevatorArrivedAtFloor(ParkingEvent event) {
        currentDirection = ElevDirection.IDLE;
        currentElevOperation = ElevOperation.IDLE;

        if (EventType.DEPARTURE.equals(event.getEventType()) && currentFloor == 0
                && shuttle.getTrolley().containsCar()) {
            shuttle.getTrolley().unlockTrolley();
            // Update the floor for the car.
            CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
            CarModelManager.getModelManager().removeCarFromFloor(carModel);
            ParkingBay parkingBay = ParkingBayManager.getManager().getParkingBayForCar(carModel);
            if (parkingBay != null) {
                parkingBay.removeCarModel();
            }
            APSControl.getControl().getUserStation().carReadyForPickup();

        } else {
            if (ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
                currentElevOperation = ElevOperation.OPENING_DOOR;
            } else if (ElevatorDoorState.CLOSED.equals(door.getDoorState())) {
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
