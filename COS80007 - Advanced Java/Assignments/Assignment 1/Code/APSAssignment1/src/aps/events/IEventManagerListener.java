package aps.events;

/**
 * The {@link IEventManagerListener}.
 * <p>
 * This interface class is responsible for notifying implementing listeners
 * that there is a parking event entering the simulation.
 * <p>
 * @author szeyick
 */
public interface IEventManagerListener {
    
    /**
     * Notify listeners of a parking event.
     * @param event - The current parking event.
     */
    void processEvent(ParkingEvent event);
}
