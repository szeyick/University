package aps.gui;

import aps.action.ControlPanelActionListener;
import aps.action.IAction;
import aps.action.PauseTimerAction;
import aps.action.StartTimerAction;
import aps.timer.IAPSTimer;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The {@link SimulationControlPanel}.
 * <p>
 * This panel class is responsible for creating the button elements that
 * make up the controls for the Automatic Parking Simulator.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class SimulationControlPanel extends JPanel {
    
    /**
     * The label to display the time lapsed in the simulation.
     */
    private JLabel timeLapsedLabel;
    
    /**
     * Constructor. 
     * @param timer - The simulation timer.
     */
    public SimulationControlPanel(IAPSTimer timer) {
        super(new GridLayout(2,1));
        
        add(createTimeLapsedLabel());
        createControls(timer);
    }
    
    /**
     * Create the label to display the time lapsed. 
     */
    private JLabel createTimeLapsedLabel() {
        timeLapsedLabel = new JLabel("00:00:00.000", JLabel.CENTER);
        return timeLapsedLabel;
    }
    
    /**
     * Create the control buttons for the panel. 
     */
    private void createControls(IAPSTimer timer) {
        JPanel buttonPanel = new JPanel();
        
        IAction startTimerAction = new StartTimerAction(timer);
        
        JButton startTimerButton = new JButton("Start");
        startTimerButton.addActionListener(new ControlPanelActionListener(startTimerAction));
        
        IAction pauseTimerAction = new PauseTimerAction(timer);
        JButton pauseTimerButton = new JButton("Pause");
        pauseTimerButton.addActionListener(new ControlPanelActionListener(pauseTimerAction));
        
        buttonPanel.add(startTimerButton);
        buttonPanel.add(pauseTimerButton);
        
        add(buttonPanel);
    }
}
