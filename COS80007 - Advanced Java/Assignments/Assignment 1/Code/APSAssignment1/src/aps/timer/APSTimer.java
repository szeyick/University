package aps.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import javax.swing.Timer;
/**
 * The {@link APSTimer}.
 * <p>
 * This class represents the timer class that will be used for the automatic 
 * parking system simulation.
 * <p>
 * @author szeyick
 * StudentID 0 1763652
 */
public class APSTimer {
    
    /**
     * The timer used to trigger scheduled events in the simulation. 
     */
    private Timer timer;
    
    /**
     * Constructor.
     */
    public APSTimer() {
       timer = new Timer(1000, new TimerListener());
       timer.start();
    }
    
    /***
     * Update the timer, allows for speed up or slow down of the simulation.
     * @param delay - The delay in milliseconds.
     */
    public void updateTimer(int delay) {
        // When updating the time, stop the timer before continuing.
        timer.stop();
        timer.setDelay(delay);
        timer.start();
    }
    
    /***
     * The {@link TimerListener}.
     * <p>
     * This class is responsible for notifying the system when a timer
     * event is triggered.
     * <p>
     */
    private class TimerListener implements ActionListener {

        /***
         * Update the system when the timer is triggered.
         * @param e - The timer event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Timer Triggered.");
        }
    }   
}
