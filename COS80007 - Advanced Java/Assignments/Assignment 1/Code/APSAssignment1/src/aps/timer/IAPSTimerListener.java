package aps.timer;

/**
 * The {@link IAPSTimerListener}.
 * <p>
 * This interface class contains the update method that will trigger
 * updates in the Automatic Parking Simulator. Classes that wish to
 * directly respond to {@link APSTimer} updates will need to implement
 * this interface and register themselves as a listener.
 * <p>
 * @author szeyick
 */
public interface IAPSTimerListener {
    
    /**
     * Method to invoke when update is received.
     * @param dt - The current time in the simulation.
     */
    void update(long dt);
}
