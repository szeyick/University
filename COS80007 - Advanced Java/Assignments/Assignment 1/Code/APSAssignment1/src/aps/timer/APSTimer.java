package aps.timer;

import aps.config.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;

/**
 * The APSTimer.
 * <p>
 * This class is responsible for functioning as the timer that will be used
 * to trigger components to be updated during the simulation. It is a singleton
 * instance that will be called and reused by all the components in the application. 
 * <p>
 * @author szeyick 
 * StudentID - 1763652
 */
public class APSTimer implements IAPSTimer {

    /**
     * An instance of the timer.
     */
    private static APSTimer apsTimer;

    /**
     * The timer used to trigger scheduled events in the simulation.
     */
    private final Timer timer;

    /**
     * A set containing a collection of timer listeners.
     */
    private final Set<IAPSTimerListener> timerListeners;
    
    /**
     * The time in milliseconds that the timer will be triggered
     */
    private int timerDelay;

    /**
     * Constructor.
     */
    private APSTimer() {
        timerDelay = Config.getConfig().TIMER_PERIOD;
        timer = new Timer(timerDelay, new TimerListener());
        timerListeners = new HashSet<>();
    }

    /**
     * Stop the timer.
     */
    @Override
    public void stopTimer() {
        timer.stop();
    }

    /**
     * Start the timer.
     */
    @Override
    public void startTimer() {
        timer.start();
    }

    /**
     * Pause the timer.
     */
    @Override
    public void pauseTimer() {
        timer.stop();
    }

    /**
     * Add a listener to the timer.
     */
    @Override
    public void addTimerListener(IAPSTimerListener listener) {
        timerListeners.add(listener);
    }

    /**
     * Remove a listener from the timer.
     */
    @Override
    public void removeTimerListener(IAPSTimerListener listener) {
        timerListeners.remove(listener);
    }

    /**
     * @return the instance of the timer.
     */
    public static APSTimer getTimer() {
        if (apsTimer == null) {
            apsTimer = new APSTimer();
        }
        return apsTimer;
    }

    /**
     * Speed up the timer by 100 milliseconds.
     */ 
    @Override
    public void speedUpTimer() {
        if (!((timerDelay - 100) <= 0)) {
            timerDelay -= 100;
        }
        timer.setDelay(timerDelay);
    }

    /**
     * Slow down the timer by 100 milliseconds.
     */
    @Override
    public void slowDownTimer() {
        timerDelay += 100;
        timer.setDelay(timerDelay);
    }

    /**
     * *
     * The TimerListener.
     * <p>
     * This class is responsible for notifying the system when a timer event is
     * triggered.
     * <p>
     */
    private class TimerListener implements ActionListener {

        /**
         * Notify all the timer listeners to update
         * when the timer is triggered.
         * <p>
         * @param e - The timer event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            for (IAPSTimerListener listener : timerListeners) {
                listener.update(Config.getConfig().TIMER_PERIOD);
            }
        }
    }
}
