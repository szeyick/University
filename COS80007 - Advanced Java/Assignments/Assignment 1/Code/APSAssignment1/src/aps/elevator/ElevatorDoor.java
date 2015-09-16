/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.elevator;

import aps.timer.APSTimer;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;

/**
 *
 * @author szeyick
 */
public class ElevatorDoor implements IAPSTimerListener {
    
    private boolean isOpen;
    
    private int doorLength;
    
    /**
     * The state of the elevator door. 
     */ 
    private ElevatorDoorState doorState;
    
    private Elevator elevator;
    
    /**
     * The states that the elevator door can be in.
     */
    public enum ElevatorDoorState {
        OPENED, CLOSED, OPENING, CLOSING;
    }
    /**
     * Constructor. 
     */
    public ElevatorDoor(APSTimer timer, Elevator elevator) {
        isOpen = false;
        doorState = ElevatorDoorState.CLOSED;
        doorLength = 5;
        this.elevator = elevator;
        timer.addTimerListener(this);
    }
    
    public void setDoorState(ElevatorDoorState newState) {
        doorState = newState;
    } 
    
    /***
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
        if (ElevatorDoorState.OPENING.equals(doorState)) {
            System.out.println("Door is opening: " + doorLength);
            // If it is opening then we open the door (update door attributes)
            doorLength--;
            if (doorLength == 0) {
                // Door is fully closed.
                doorState = ElevatorDoorState.OPENED;
                System.out.println("Door is opened: ");
                elevator.elevatorDoorCallback();
            }
        }
        if (ElevatorDoorState.CLOSING.equals(doorState)) {
            // If it is closing, then we close the door (update door attributes)
            System.out.println("Door is closing: ");
            doorLength++;
            if (doorLength == 5) {
                doorState = ElevatorDoorState.CLOSED;
                System.out.println("Door is closed: ");
                elevator.elevatorDoorCallback();
            }
        }
    }
    
    /**
     * @return the length of the door.
     */ 
    public int getDoorLength() {
        return doorLength;
    }
}
