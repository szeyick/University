package aps.elevator;

import aps.shuttle.Shuttle;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;

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
    
    private ElevOperation currentElevOperation;
    
    private Shuttle shuttle;
    
    private int destinationFloor;
    
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
        currentDirection = ElevDirection.DEFAULT;
        currentElevOperation = ElevOperation.ARRIVED_AT_FLOOR;
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
                moveToFloor(0);
            }
            else {
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
     * Move the elevator to a floor.
     * @param floorNumber - The floor to move to.
     */
    public void moveToFloor(int floorNumber) {
        targetFloor = floorNumber;
        if (currentFloor < floorNumber) {
            // If the current floor is at a lower floor than the target we need to go up.
            currentDirection = ElevDirection.UP;
        }
        if (currentFloor > floorNumber) {
            currentDirection = ElevDirection.DOWN;
        }
    }
    
    /**
     * Perform an operation on the elevator. Update how far the 
     */ 
    @Override
    public void update(long dt) {
        if ((currentFloor == targetFloor) && !ElevDirection.IDLE.equals(currentDirection)) {
            currentDirection = ElevDirection.IDLE;
            currentElevOperation = ElevOperation.OPENING_DOOR;
            System.out.println("Elevator has arrived at floor: " + currentFloor);
        }
        // If the elevator direction is Up, we need to increment the current floor count.
        if (ElevDirection.UP.equals(currentDirection)) {
            // Increment the current floor count.
            currentFloor++;
            currentElevOperation = ElevOperation.MOVING;
            System.out.println("Elevator is moving up");
        }
        if (ElevDirection.DOWN.equals(currentDirection)) {
            // Decrement the current floor count.
            currentFloor--;
            currentElevOperation = ElevOperation.MOVING;
            System.out.println("Elevator is moving down");
        }
        // If arrived at floor, we need to decide whether to open/close
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
