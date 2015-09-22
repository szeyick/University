package aps.floor;

import aps.elevator.Elevator;
import aps.timer.IAPSTimerListener;
import aps.control.APSControl;

/**
 * The ParkingLevelFloorControl
 * <p>
 * This class is responsible for creating and managing the components for
 * the parking level floors. 
 * <p>
 * @author szeyick
 * StudentID - 1763652.
 */
public class ParkingLevelFloorControl implements IAPSTimerListener {

    /**
     * An integer representing the current floor.
     */
    private final int FLOOR_NUMBER;
    
    /**
     * The panel to draw on.
     */ 
    private final ParkingLevelFloorPanel parkingPanel;
    
    /**
     * Constructor.
     * @param floorNum - The floor number associated to the
     * current instance of the controller.
     */
    public ParkingLevelFloorControl(int floorNum) {
        FLOOR_NUMBER = floorNum;
        parkingPanel =  new ParkingLevelFloorPanel(FLOOR_NUMBER);
    }
    
    /**
     * @return the number of the floor.
     */ 
    public int getFloorNumber() {
        return FLOOR_NUMBER;
    }
    
    /**
     * @return the panel with the parking floor.
     */
    public ParkingLevelFloorPanel getPanel() {
        return parkingPanel;
    }
    
    /**
     * When the elevator has arrived on the designated floor we
     * need to perform a redraw to correctly display the elevator, shuttle
     * trolley and cars on the floor.
     */ 
    @Override
    public void update(long dt) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == FLOOR_NUMBER) {
            parkingPanel.draw();
        }
    }  
}
