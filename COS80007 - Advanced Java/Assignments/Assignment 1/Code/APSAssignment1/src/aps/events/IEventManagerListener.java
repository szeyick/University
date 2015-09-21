package aps.events;

/**
 * The {@link IEventManagerListener}.
 * <p>
 * This interface is responsible for providing a method that will be invoked
 * when there is a new parking event that is entering the simulation. Classes 
 * that wish to be notified of parking events will be required to implement
 * this interface and register themselves as listeners to the {@link EventManager}.
 * <p>
 * <strong>Warning: </strong> As of the revised update to Assignment 1, the parking
 * events will not be read from an offline defined file and will be user
 * interaction driven.
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
