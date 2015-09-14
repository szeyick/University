/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.shuttle;

import aps.elevator.Elevator;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;

/**
 * The {@link Shuttle}.
 * <p>
 * This class represents the shuttle mechanism. In the simulation, the shuttle
 * carries the trolley. It moves sideways (left - right) when the trolley is
 * locked into it, with a car. The shuttle will move sidways to align its
 * centre X with either the centre of the elevator, or the parking bay in 
 * which it is to deliver the car.
 * <p>
 * @author szeyick
 */
public class Shuttle implements IAPSTimerListener {
    
    private int shuttleX;
    
    private Elevator elevator;
    
    private int centreRadiusTT;
    
    private ShuttleState shuttleState;
    
    private Trolley trolley;
    
    public enum ShuttleState {
        DEPLOYING, LOCKED, UNLOCKED, RETURNED, IDLE;
    }
            
    /**
     * Constructor.
     * @param elevator - A reference to the elevator.
     */
    public Shuttle(Elevator elevator, APSTimer timer) {
        this.elevator = elevator;
        centreRadiusTT = 5;
        shuttleX = 0;
        shuttleState = ShuttleState.LOCKED;
        trolley = new Trolley(this);
        timer.addTimerListener(trolley);
    }

    public void updateShuttleState(ShuttleState state) {
     shuttleState = state; 
     
     // Update shuttle
    }
    /**
     * With the shuttle, if it starts to receive updates to retrieve we need to
     * get the car, which means to deploy the trolley to get the car.
     * <p>
     * The shuttle can move left and right to the correct bay to retrieve the
     * car. (On the ground floor, it lines up with the centre of the turntable
     * and on every other floor it lines up with the bay).
     * 
     * @param dt 
     */
    @Override
    public void update(long dt) {
        // Need a reference to whether we are doing a delivery or retrieval.
        // For deliveries, we need to know the bay to assign. Means we need 
        // a bay manager.
        if (ShuttleState.LOCKED.equals(shuttleState)
                && Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())
                && Trolley.TrolleyState.DOCKED_SHUTTLE.equals(trolley.getState())) {
            shuttleState = ShuttleState.UNLOCKED;
            System.out.println("Unlocking Shuttle");
        }    
        if (ShuttleState.UNLOCKED.equals(shuttleState)) {
            if (elevator.getCurrentFloor() == 0 && 
                    Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {
                 
                if (shuttleX == centreRadiusTT) {
                    shuttleState = ShuttleState.LOCKED;
                    // Deploy the trolley but also stop update
                    System.out.println("Locking Shuttle");
                    trolley.deployTrolley();
                }
                // Move the shuttle along X axis
                if (shuttleX < centreRadiusTT) {
                    shuttleX++;
                    System.out.println("Moving Shuttle");
                }
            }
            if (elevator.getCurrentFloor() != 0 && 
                    Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {
             
                // If we are unlocked, we need to go to the designated bay. If trolley contains car.
                if (trolley.containsCar()) {
                    // Move to designated bay
                    // Move the shuttle along X axis
                    if (shuttleX == centreRadiusTT) {
                        shuttleState = ShuttleState.LOCKED;
                        // Deploy the trolley but also stop update
                        System.out.println("Locking Shuttle With Car");
                        trolley.deployTrolley();
                    }                
                    if (shuttleX < centreRadiusTT) {
                        shuttleX++;
                        System.out.println("Moving Shuttle With Car");
                    }
                }
            }            
        }
        if (ShuttleState.RETURNED.equals(shuttleState)) {
            // Shuttle to be marked to return to original point
            // If shuttle moves x, the trolley needs to move too.
            if (shuttleX == 0) {
                // Return to elevator operations.
                shuttleState = ShuttleState.IDLE;
                System.out.println("Shuttle Idle");
                elevator.shuttleCallback();
            }
            if (shuttleX > 0) {
                shuttleX--;
                System.out.println("Moving Shuttle Back to Base");
            }
        }
        

    }
}
