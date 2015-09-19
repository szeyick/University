package aps.timer;

import aps.config.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;

/**
 * The {@link APSTimer}.
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
     * Constructor.
     */
    private APSTimer() {
        timer = new Timer(Config.getConfig().TIMER_PERIOD, new TimerListener());
        timerListeners = new HashSet<>();
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
     * *
     * The {@link TimerListener}.
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
