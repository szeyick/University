package aps.timer;

/**
 * The {@link IAPSTimer}.
 * <p>
 * This interface is responsible for providing the public accessible
 * methods for the timer.
 * <p>
 * @author szeyick
 */
public interface IAPSTimer {

    /**
     * Stop the scenario timer. 
     */
    void stopTimer();
    
    /**
     * Start the scenario timer. 
     */
    void startTimer();
    
    /**
     * Pause the scenario timer.
     */
    void pauseTimer();
    
    /**
     * Add a timer listener.
     * @param listener - The object to listen for timer updates.
     */
    void addTimerListener(IAPSTimerListener listener);
    
    /**
     * Remove a timer listener.
     * @param listener - The object to be removed from listening to timer updates.
     */ 
    void removeTimerListener(IAPSTimerListener listener);
}
