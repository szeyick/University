package aps.events;

/**
 * The {@link IEventManagerListener}.
 * <p>
 * This interface is responsible for providing a method that will be invoked
 * when there is a new parking event that is entering the simulation. Classes 
 * that wish to be notified of parking events will be required to implement
 * this interface and register themselves as listeners to the {@link EventManager}.
 * <p>
 * @author szeyick
 * StudentID - 1763652.
 */
public interface IEventManagerListener {
    
    /**
     * Process the parking event.
     * @param event - The current parking event.
     */
    void processEvent(ParkingEvent event);
}
