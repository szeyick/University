package aps.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;
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
public class APSTimer implements IAPSTimer {
    
    /**
     * The timer used to trigger scheduled events in the simulation. 
     */
    private final Timer timer;
    
    /**
     * A set containing a collection of timer listeners.
     */
    private Set<IAPSTimerListener> timerListeners;
            
    /**
     * Constructor.
     */
    public APSTimer() {
       timer = new Timer(1000, new TimerListener());
       timerListeners = new HashSet<IAPSTimerListener>();
    }
   
    /**
     * {@inheritDoc  
     */ 
    @Override
    public void stopTimer() {
        System.out.println("Timer is Stopped");
        timer.stop();
    }

    /**
     * {@inheritDoc  
     */ 
    @Override
    public void startTimer() {
        System.out.println("Timer is Started");
        timer.start();
    }

    /**
     * {@inheritDoc  
     */ 
    @Override
    public void pauseTimer() {
        System.out.println("Timer is Paused");
        timer.stop();
    }

    /**
     * {@inheritDoc  
     */ 
    @Override
    public void addTimerListener(IAPSTimerListener listener) {
        timerListeners.add(listener);
    }

    /**
     * {@inheritDoc  
     */
    @Override
    public void removeTimerListener(IAPSTimerListener listener) {
        timerListeners.remove(listener);
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
            for (IAPSTimerListener listener : timerListeners) {
                listener.update(1000);
            }
        }
    }   
}
