package aps.elevator;

import aps.config.Config;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;

/**
 * The ElevatorDoor.
 * <p>
 * This class is responsible for managing the state of the elevator door in the
 * parking simulation along with providing the graphical representation of the
 * door.
 * <p>
 * @author szeyick StudentID - 1763652.
 */
public class ElevatorDoor implements IAPSTimerListener {

    /**
     * The length of the elevator door.
     */
    private int doorLength;

    /**
     * The state of the elevator door.
     */
    private ElevatorDoorState doorState;

    /**
     * A reference to the elevator.
     */
    private final Elevator elevator;

    /**
     * The time that it takes for the door to open/close (in milliseconds).
     */
    private int doorTimer;

    /**
     * Constructor.
     *
     * @param elevator - The elevator.
     */
    public ElevatorDoor(Elevator elevator) {
        doorState = ElevatorDoorState.CLOSED;
        doorTimer = 0;
        doorLength = 5;
        this.elevator = elevator;
        APSTimer.getTimer().addTimerListener(this);
    }

    /**
     * Update the state of the elevator door.
     *
     * @param newState - The new state of the door.
     */
    public void setDoorState(ElevatorDoorState newState) {
        doorState = newState;
    }

    /**
     * *
     * @return the current state of the elevator door.
     */
    public ElevatorDoorState getDoorState() {
        return doorState;
    }

    /**
     * Update the state of the elevator door.
     */
    @Override
    public void update(long dt) {
        doorTimer += dt;
        if (ElevatorDoorState.OPENING.equals(doorState)) {
            doorLength--;
            if (doorLength <= 0) {
                doorLength = 0;
                // Door is fully opened.
                if (hasDoorTimerExpired()) {
                    doorState = ElevatorDoorState.OPENED;
                    elevator.elevatorDoorCallback();
                }

            }
        }
        if (ElevatorDoorState.CLOSING.equals(doorState)) {
            doorLength++;
            if (doorLength >= 5) {
                doorLength = 5;
                if (hasDoorTimerExpired()) {
                    doorState = ElevatorDoorState.CLOSED; 
                    elevator.elevatorDoorCallback();
                }
            }
        }
        if (ElevatorDoorState.CLOSED.equals(doorState) || ElevatorDoorState.OPENED.equals(doorState)) {
            doorTimer = 0;
        }
    }

    /**
     * @return true if the door timer has expired, false otherwise.
     */
    private boolean hasDoorTimerExpired() {
        return doorTimer >= Config.getConfig().ELEVATOR_DOOR_PERIOD;
    }

    /**
     * @return the length of the door.
     */
    public int getDoorLength() {
        return doorLength;
    }
}
