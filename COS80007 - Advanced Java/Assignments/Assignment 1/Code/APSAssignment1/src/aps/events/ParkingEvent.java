package aps.events;

/**
 * The ParkingEvent.
 * <p>
 * This class represents a single parking event. A parking event is can be
 * either of two types, an arrival or departure event. This class
 * is a composition required by the system to determine the
 * type of event that will be triggered.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class ParkingEvent implements Comparable<ParkingEvent> {
    
    /**
     * The type of this parking event (Arrival, Departure)
     */
    private final EventType eventType;

    /**
     * The time that this event is to begin.
     */
    private final long eventStartTime;
    
    /**
     * The number plate of the car to add or remove
     * from the car park.
     */
    private final String numberPlate;

    /**
     * A flag to determine if a parking event
     * has been completed.
     */
    private boolean eventComplete;
    
    /**
     * Constructor.
     * @param eventType - The type of event.
     * @param eventStartTime - The start time of the event in milliseconds.
     * @param numberPlate - The number plate of the vehicle in this event.
     */
    public ParkingEvent(EventType eventType, long eventStartTime, String numberPlate) {
        this.eventType = eventType;
        this.eventStartTime = eventStartTime;
        this.numberPlate = numberPlate;
    }
    
    /**
     * @return - The type of this event instance. 
     */
    public EventType getEventType() {
        return eventType;
    }
    
    /**
     * @return - The start time of this event.
     */
    public long getEventStartTime() {
        return eventStartTime;
    }
    
    /** 
     * @return - The number plate of the vehicle in this event. 
     */
    public String getNumberPlate() {
        return numberPlate;
    }
    
    /**
     * Set the event state to complete.
     */
    public void setEventComplete() {
        eventComplete = true;
    }
    
    /**
     * @return true if the current has been completed, false otherwise.
     */
    public boolean hasCurrentEventCompleted() {
        return eventComplete;
    }
    
    /***
     * Compare a parking event with another to determine the 
     * order in which events will be executed.
     * @param  otherParkingEvent - The parking event to compare.
     */
    @Override
    public int compareTo(ParkingEvent otherParkingEvent) {
        return (int)(this.eventStartTime - otherParkingEvent.eventStartTime);
    }
}
