package aps.gui;

import aps.action.ControlPanelActionListener;
import aps.action.CreateDropOffEventAction;
import aps.action.CreatePickupEventAction;
import aps.action.IAction;
import aps.action.PauseTimerAction;
import aps.action.SlowDownTimerAction;
import aps.action.SpeedUpTimerAction;
import aps.action.StartTimerAction;
import aps.timer.APSClock;
import aps.timer.APSTimer;
import aps.timer.IAPSTimer;
import aps.control.APSControl;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The SimulationControlPanel.
 * <p>
 * This panel class is responsible for creating the button elements that make up
 * the controls for the Automatic Parking Simulator.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class SimulationControlPanel extends JPanel {

    /**
     * Constructor.
     *
     * @param timer - The simulation timer.
     */
    public SimulationControlPanel(IAPSTimer timer) {
        super(new GridLayout(2, 1));
        createDisplayPanel();
        createControls();
    }

    /**
     * Create the panel to display the statistics and the time lapsed
     * in the simulation.
     */
    private void createDisplayPanel() {
        APSClock clock = new APSClock();
        APSTimer.getTimer().addTimerListener(clock);

        JPanel displayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        displayPanel.add(new JLabel("Current Time: "));
        displayPanel.add(clock.getPanel());
        displayPanel.add(new JLabel("Waiting Park : "));
        displayPanel.add(APSControl.getControl().getCarsForDropOff());
        displayPanel.add(new JLabel("Waiting Collect : "));
        displayPanel.add(APSControl.getControl().getCarsForPickup());
        add(displayPanel);
    }

    /**
     * Create the control buttons for the panel.
     */
    private void createControls() {
        JPanel buttonPanel = new JPanel();

        IAction startTimerAction = new StartTimerAction();

        JButton startTimerButton = new JButton("Start");
        startTimerButton.addActionListener(new ControlPanelActionListener(startTimerAction));

        IAction pauseTimerAction = new PauseTimerAction();
        JButton pauseTimerButton = new JButton("Pause");
        pauseTimerButton.addActionListener(new ControlPanelActionListener(pauseTimerAction));

        IAction speedUpTimerAction = new SpeedUpTimerAction();
        JButton speedUpTimerButton = new JButton("Speed Up");
        speedUpTimerButton.addActionListener(new ControlPanelActionListener(speedUpTimerAction));

        IAction slowDownTimerAction = new SlowDownTimerAction();
        JButton slowDownTimerButton = new JButton("Slow Down");
        slowDownTimerButton.addActionListener(new ControlPanelActionListener(slowDownTimerAction));

        IAction createDropOffEventAction = new CreateDropOffEventAction();
        JButton createDropOffButton = new JButton("Park Car");
        createDropOffButton.addActionListener(new ControlPanelActionListener(createDropOffEventAction));

        IAction createPickupEventAction = new CreatePickupEventAction();
        JButton createPickUpButton = new JButton("Collect Car");
        createPickUpButton.addActionListener(new ControlPanelActionListener(createPickupEventAction));

        buttonPanel.add(startTimerButton);
        buttonPanel.add(pauseTimerButton);
        buttonPanel.add(speedUpTimerButton);
        buttonPanel.add(slowDownTimerButton);
        buttonPanel.add(createPickUpButton);
        buttonPanel.add(createDropOffButton);
        add(buttonPanel);
    }
}
