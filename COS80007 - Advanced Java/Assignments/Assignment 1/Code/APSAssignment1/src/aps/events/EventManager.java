package aps.events;

import aps.timer.IAPSTimerListener;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The EventManager}.
 * <p>
 * This class is responsible for creating ParkingEvents that will
 * be pushed into the simulation. It will listen to the APSTimer
 * to create new parking events when sufficient time has lapsed.
 * <p>
 * <strong>Warning: </strong> As of the revised update to Assignment 1, the parking
 * events will not be read from an offline defined file and will be user
 * interaction driven.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class EventManager implements IAPSTimerListener {
    
    /**
     * A set of parking event listeners
     */
    private final Set<IEventManagerListener> eventListeners;
    
    /**
     * A priority queue of parking events, prioritised by start time.
     */
    private final PriorityQueue<ParkingEvent> parkingEventQueue;
    
    /**
     * The time in milliseconds since the start of the simulation.
     */
    private long currentTime = 0;
        
    /**
     * Constructor.
     * @param fileName - The name of the input file for simulated traffic.
     */
    public EventManager(String fileName) {
        parkingEventQueue = new PriorityQueue<>();
        eventListeners = new HashSet<>();
        // createParkingEvents(fileName);
    }
    
    /**
     * Add a listener to listen for event manager events.
     * @param listener - The listener to add.
     */
    public void addEventListener(IEventManagerListener listener) {
        eventListeners.add(listener);
    }
    
    /**
     * Remove a listener from the event manager.
     * @param listener - The listener to remove.
     */
    public void removeEventListener(IEventManagerListener listener) {
        eventListeners.remove(listener);
    }
    
    /**
     * Create the traffic events from the input file.
     * @param fileName - The name of the input file to read.
     */
    private void createParkingEvents(String fileName) {
        int lineNumber = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            LineNumberReader reader = new LineNumberReader(fileReader);    
        
            String line = null;
            while ((line = reader.readLine()) != null) {
                lineNumber = reader.getLineNumber();
                String lineChars[] = line.split("[ ]");
                String eventType = lineChars[0];
                double minutesAfterStart = Double.parseDouble(lineChars[1]);
                int numberOfVehicles = Integer.parseInt(lineChars[2]);
                double interval = Double.parseDouble(lineChars[3]);
                
                parkingEventQueue.addAll(createParkingEvents(eventType, minutesAfterStart, numberOfVehicles, interval));
            }
            // Close the file after reading.
            reader.close();
        }
        catch (Exception e) {
            System.out.println("Error - Line Number: " + lineNumber);
            e.printStackTrace();
        }
    }
    
    /**
     * Create a list of {@link ParkingEvent} based on the provided traffic parameters.
     * @param eventType - The type of event.
     * @param minutesAfterStart - The minutes after start the first event occurs.
     * @param numVehicles - The number of vehicles involved in this traffic.
     * @param interval - The interval after the arrival of the first car.
     * <p>
     * @return - A list of all the parking events for this lot of traffic.
     */
    private List<ParkingEvent> createParkingEvents(String eventTypeStr, double minutesAfterStart, int numVehicles, double interval) {
        List<ParkingEvent> parkingEvents = new ArrayList<ParkingEvent>();
        
        EventType eventType = eventTypeStr.equals("A") ? EventType.ARRIVAL : EventType.DEPARTURE;
        
        long millisecondsAfterStart = convertMinutesToMilliseconds(minutesAfterStart);
        long intervalInMilliseconds = convertMinutesToMilliseconds(interval);
        
        // Creating the events.
        long eventStartTime = millisecondsAfterStart;
        for (int i = 0; i < numVehicles; i++) {
            ParkingEvent event = new ParkingEvent(eventType, eventStartTime, "");
            parkingEvents.add(event);
            // Increment the event start time by the interval.
            eventStartTime += intervalInMilliseconds;
        }
        return parkingEvents;
    }
    
    /**
     * Convert the time in minutes to milliseconds
     * @param minutes - The time in minutes.
     */
    private long convertMinutesToMilliseconds(double minutes) {
        long millisecondsInASecond = 1000;
        long secondsInAMinute = 60;
         
        return (long) (millisecondsInASecond * secondsInAMinute * minutes);
    }

    /**
     * When the APSTimer triggers an update event, this manager will
     * evaluate if sufficient time has passed so as to invoke another ParkingEvent.
     * <p>
     * If sufficient time has lapsed, a ParkingEvent will be removed from the
     * managers priority queue and loaded into the simulation by notifying listeners
     * of this manager that a new parking event is ready to be invoked.
     * <p>
     * ParkingEvent items that are still to occur in the future, are not
     * removed from the priority queue and will remain in the queue until sufficient
     * time has lapsed to render the event at the current time or in the past.
     */
    @Override
    public void update(long dt) {
        currentTime += dt;
        ParkingEvent event = parkingEventQueue.peek();
        while (event != null && (event.getEventStartTime() <= currentTime)) {
            event = parkingEventQueue.poll();  /// Removes from queue
            // System.out.println("Current Time : " + currentTime + " Event Start Time: " + event.getEventStartTime());
            notifyAllListeners(event);
            event = parkingEventQueue.peek();
        }   
    }
    
    /**
     * Notify all the listeners of this event manager that a parking
     * event is to be loaded into the simulation.
     */
    private void notifyAllListeners(ParkingEvent event) {
        for (IEventManagerListener listener : eventListeners) {
            listener.processEvent(event);
        }
    }
}
