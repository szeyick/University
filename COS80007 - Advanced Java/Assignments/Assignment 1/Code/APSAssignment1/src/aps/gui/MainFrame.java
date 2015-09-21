package aps.gui;

import aps.config.Config;
import aps.floor.GroundLevelFloorControl;
import aps.floor.ParkingLevelFloorControl;
import aps.timer.APSTimer;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;
import control.APSControl;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * The {@link MainFrame}.
 * <p>
 * This class is responsible for holding all the graphical components that are
 * to be displayed as part of the Automatic Parking Simulator.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class MainFrame extends JFrame implements IAPSTimerListener {

    /**
     * The control object that is responsible for all the ground level
     * components.
     */
    private GroundLevelFloorControl control;

    /**
     * The current floor that is being displayed by the frame.
     */
    private int currentFloor;

    /**
     * The tabbed pane containing the floor layouts.
     */ 
    private final JTabbedPane tabbedPane;

    /**
     * Constructor.
     */
    public MainFrame() {
        super("APS - Sze Yick");

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentFloor = APSControl.getControl().getElevator().getCurrentFloor();
        IAPSTimer timer = APSTimer.getTimer();

        timer.addTimerListener(this);

        tabbedPane = new JTabbedPane();

        // Create the controls and components for the car park.
        createSimulationControlPanel(timer);
        createFloorView(timer);
        createParkingFloorViews();

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Create the simulation control panel and add it to the frame.
     */
    private void createSimulationControlPanel(IAPSTimer timer) {
        add(new SimulationControlPanel(timer), BorderLayout.SOUTH);

    }

    /**
     * Create the floor control and add the view to the frame.
     */
    private void createFloorView(IAPSTimer timer) {
        control = new GroundLevelFloorControl(timer);
        timer.addTimerListener(control);
        tabbedPane.addTab("Ground", control.getPanel());
    }
    
    /**
     * Create n number of floors that cars can be parked on and
     * add them to the tabbed pane.
     */
    private void createParkingFloorViews() {
        IAPSTimer timer = APSTimer.getTimer();
        int numberOfFloors = Config.getConfig().NF;
        for (int floor = 1; floor <= numberOfFloors; floor++) {
            ParkingLevelFloorControl parkingFloorControl = new ParkingLevelFloorControl(floor);
            timer.addTimerListener(parkingFloorControl);
            String floorID = "Floor " + floor;
            tabbedPane.addTab(floorID, parkingFloorControl.getPanel());
        }
    }

    /**
     * Update the displayed view in the frame if the current floor has changed.
     * <p>
     * {@inheritDoc }.
     */
    @Override
    public void update(long dt) {
        if (currentFloor != APSControl.getControl().getElevator().getCurrentFloor()) {
            currentFloor = APSControl.getControl().getElevator().getCurrentFloor();
            System.out.println("Switching to View - Floor" + currentFloor);
            if (currentFloor == 0) {
                tabbedPane.setSelectedIndex(currentFloor);
            } else {
                tabbedPane.setSelectedIndex(currentFloor);
            }
            // re-draw the floor if it has changed.
            invalidate();
            validate();
            repaint();
        }
    }
}
