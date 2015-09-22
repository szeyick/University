package aps.timer;

/**
 * The IAPSTimerListener.
 * <p>
 * This interface is responsible for providing an update method that will
 * be called whenever the timer triggers. Concrete classes that wish to 
 * respond to {@link APSTimer} updates will be required to implement this
 * interface and register themselves with the timer as a listener.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public interface IAPSTimerListener {
    
    /**
     * Notify an implementing class that a timer update has been received.
     * @param dt - The time passed since the last triggered event.
     */
    void update(long dt);
}
