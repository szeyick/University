/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import aps.elevator.Elevator;
import aps.timer.IAPSTimerListener;
import control.APSControl;

/**
 *
 * @author szeyick
 */
public class ParkingLevelFloorControl implements IAPSTimerListener {

    /**
     * An integer representing the current floor.
     */
    private final int FLOOR_NUMBER = 1;
    
    /**
     * The panel to draw.
     */ 
    private ParkingLevelFloorPanel parkingPanel;
    
    /**
     * Constructor.
     */
    public ParkingLevelFloorControl() {
        parkingPanel =  new ParkingLevelFloorPanel();
    }
    
    /**
     * @return the panel with the parking floor.
     */
    public ParkingLevelFloorPanel getPanel() {
        return parkingPanel;
    }
    
    /**
     * When the car has arrived on the floor, we can do something.
     */ 
    @Override
    public void update(long dt) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == FLOOR_NUMBER) {
            // If we have arrived on the floor, we can draw things.
            parkingPanel.draw();
        }
    }
    
}
