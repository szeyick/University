package aps.gui;

import aps.floor.GroundLevelFloorPanel;
import aps.floor.ParkingLevelFloorPanel;
import aps.timer.IAPSTimer;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The {@link MainFrame}.
 * <p>
 * This class is responsible for holding all the graphical components that
 * are to be seen as part of the Automatic Parking Simulator.
 * <p>
 * @author szeyick
 */
public class MainFrame extends JFrame {
    
    /**
     * Constructor.
     */
    public MainFrame(IAPSTimer timer) {
        super("APS - Sze Yick");
        
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createSimulationControlPanel(timer);
        createFloorView();
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
    private void createFloorView() {
        GroundLevelFloorPanel panel = new GroundLevelFloorPanel();        
        add(panel, BorderLayout.CENTER);
    }
}
