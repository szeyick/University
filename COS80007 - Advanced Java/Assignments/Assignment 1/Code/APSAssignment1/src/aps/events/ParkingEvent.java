package aps.events;

/**
 * The {@link ParkingEvent}.
 * <p>
 * This class represents an individual parking event. A parking event is
 * comprised of either an arrival or departure event, meaning that a
 * driver is either requesting pickup or drop off of their vehicle.
 * <p>
 * @author szeyick
 */
public class ParkingEvent implements Comparable<ParkingEvent> {
    
    /**
     * The type of event.
     */
    private EventType eventType;

    /**
     * The start time of this event. 
     */
    private long eventStartTime;
    
    /**
     * The number plate of the car to add/remove 
     */
    private String numberPlate;

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
    
    /***
     * Comparator will be based on the event start time.
     */
    @Override
    public int compareTo(ParkingEvent other) {
        return (int)(this.eventStartTime - other.eventStartTime);
    }
}
