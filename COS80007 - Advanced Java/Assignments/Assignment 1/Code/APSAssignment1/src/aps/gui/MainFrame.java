package aps.gui;

import aps.floor.GroundLevelFloorControl;
import aps.floor.ParkingLevelFloorControl;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;
import control.APSControl;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * The {@link MainFrame}.
 * <p>
 * This class is responsible for holding all the graphical components that
 * are to be seen as part of the Automatic Parking Simulator.
 * <p>
 * @author szeyick
 */
public class MainFrame extends JFrame implements IAPSTimerListener {
    
    private GroundLevelFloorControl control;
    
    private ParkingLevelFloorControl parkingFloorControl;
    
    private int currentFloor;
            
    /**
     * Constructor.
     */
    public MainFrame(IAPSTimer timer) {
        super("APS - Sze Yick");
        
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        currentFloor = APSControl.getControl().getElevator().getCurrentFloor();
        
        timer.addTimerListener(this);
        createSimulationControlPanel(timer);
        createFloorView(timer);
        createParkingFloorView(timer);
        setVisible(true);
    }
    
    /**
     * Create the simulation control panel and
     * add it to the frame.
     */
    private void createSimulationControlPanel(IAPSTimer timer) {
        add(new SimulationControlPanel(timer), BorderLayout.SOUTH);

    }
    
    /**
     * Create the floor view and add it to the frame.
     */
    private void createFloorView(IAPSTimer timer) {
        control = new GroundLevelFloorControl(timer);
        timer.addTimerListener(control);
        add(control.getPanel(), BorderLayout.CENTER);
    }
    
    private void createParkingFloorView(IAPSTimer timer) {
        parkingFloorControl = new ParkingLevelFloorControl();
        timer.addTimerListener(parkingFloorControl);
        // add(parkingFloorControl.getPanel(), BorderLayout.CENTER);
    }

    /**
     * Change the view if the current floor is changed.
     */ 
    @Override
    public void update(long dt) {
        if (currentFloor != APSControl.getControl().getElevator().getCurrentFloor()) {
            currentFloor = APSControl.getControl().getElevator().getCurrentFloor();
            
            System.out.println("SWAPPING FLOOR :" + currentFloor);
            if (currentFloor == 0) {
                remove(parkingFloorControl.getPanel());
                add(control.getPanel(), BorderLayout.CENTER);
            }
            else {
                remove(control.getPanel());
                add(parkingFloorControl.getPanel(), BorderLayout.CENTER);
            }
            invalidate();
            validate();
            repaint();
        }
    }
    
    
}
