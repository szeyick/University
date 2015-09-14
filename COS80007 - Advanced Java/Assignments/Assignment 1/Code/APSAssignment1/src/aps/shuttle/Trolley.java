/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.shuttle;

import aps.timer.IAPSTimerListener;

/**
 * The {@link Trolley}.
 * <p>
 * This class represents the trolley mechanism in the Automatic Parking Simulator.
 * The purpose of the trolley is to load and unload the car from either a parking
 * bay or from the turntable.
 * <p>
 * @author szeyick
 */
public class Trolley implements IAPSTimerListener {
    
    private TrolleyState state;
    
    private int trolleyY;
    
    private int targetY = 5;
    
    private int lockTime;
    
    private int currentTime;
    
    private Shuttle shuttle;
    
    // Will be true if the trolley has locked a car.
    private boolean containsCar;
    
    public enum TrolleyState {
        DOCKED_SHUTTLE, DEPLOYING, RETURNING, LOCKING, LOCKED;
    }
    /**
     * Constructor.
     */
    public Trolley(Shuttle shuttle) {
        state = TrolleyState.DOCKED_SHUTTLE;
        trolleyY = 0;
        lockTime = 5;
        currentTime = 0;
        containsCar = false;
        this.shuttle = shuttle;
    }
    
    public boolean containsCar() {
        return containsCar;
    }
    
    public void deployTrolley() {
        // Deploying the trolley will move it north/south to get or unload
        // a car. 
        System.out.println("Deploying Trolley");
        state = TrolleyState.DEPLOYING;
    }
    
    public TrolleyState getState() {
        return state;
    }
    
    @Override
    public void update(long dt) {
        // The trolley moves up and down to lock it with the car. The shuttle should line it up in
        // The X coord, the trolley should move in the Y direction sso it fits underneath.
        if (TrolleyState.DEPLOYING.equals(state)) {
            if (trolleyY == targetY) {
                state = TrolleyState.LOCKING;
                System.out.println("Locking Trolley");
            }
            if (trolleyY < targetY) {
                trolleyY++;
            }
        }
        else if (TrolleyState.LOCKING.equals(state)) {
            if (currentTime == lockTime) {
                state = TrolleyState.RETURNING;
                containsCar = true;
                System.out.println("Returning Trolley");
            }
            if (currentTime < lockTime) {
                currentTime++;
            }    
        }
        else if (TrolleyState.RETURNING.equals(state)) {
            if (trolleyY == 0) {
                state = TrolleyState.DOCKED_SHUTTLE;
                System.out.println("Docking Trolley");
                // Return to Shuttle Control
                currentTime = 0;
                shuttle.updateShuttleState(Shuttle.ShuttleState.RETURNED);
            }
            if (trolleyY > 0) {
                trolleyY--;
            }
        }
    }
    
}
