package aps.timer;

/**
 * The IAPSTimer.
 * <p>
 * This interface is responsible for providing the public accessible
 * methods for the timer.
 * <p>
 * @author szeyick
 * StudentID - 1763652
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
    
    /**
     * Increase the speed of the simulation by 100 milliseconds.
     */ 
    void speedUpTimer();
    
    /**
     * Decrease the speed of the simulation by 100 milliseconds.
     */ 
    void slowDownTimer();
}
