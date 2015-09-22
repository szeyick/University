package aps.events;

/**
 * The EventType.
 * <p>
 * This enumeration class provides the parking event types for the Automatic Parking
 * Simulator. Parking events consist of two types, arrival and departure. 
 * <p>
 * A arrival event is defined as a car that is entering into the system that is 
 * required to be parked somewhere in the carpark.
 * <p>
 * A departure event is defined as a car that is already parked in the car park
 * and is required to be retrieved for the user.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public enum EventType {

    /**
     * The arrival event.
     */
    ARRIVAL,
    
    /**
     * The departure event.
     */
    DEPARTURE;
}
