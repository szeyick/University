package aps.events;

import aps.timer.IAPSTimerListener;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The {@link EventManager}.
 * <p>
 * This class is responsible for creating events to be pushed into the
 * Automatic Parking Simulator. This manager will listen for update
 * requests from the APS timer so it will load new events each time
 * where sufficient time has lapsed.
 * <p>
 * @author szeyick
 */
public class EventManager implements IAPSTimerListener {
    
    /**
     * A priority queue of parking events, prioritised by start time.
     */
    private PriorityQueue<ParkingEvent> parkingEventQueue;
    
    /**
     * The current time. (milliseconds since start)
     */
    private long currentTime = 0;
    
    /**
     * The simulation start time. // This will need to be consolidated but for
     * the sake of the manager, writing it here.
     */
    private long startTime;
    
    /**
     * Constructor.
     * @param fileName - The name of the input file for simulated traffic.
     */
    public EventManager(String filename) {
        parkingEventQueue = new PriorityQueue<ParkingEvent>();
        createParkingEvents(filename);
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
            // Close the file.
            reader.close();
        }
        catch (Exception e) {
            System.out.println("Error - No input file found");
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
     * Concrete implementation of the listeners update method. For the event
     * manager, this will trigger an event to be loaded into the simulation
     * so long as the item at the head of the priority queue is at the current
     * time or already in the past. Event items that are still in the future
     * are not removed from the queue, and must wait until the correct time before
     * they are available to be executed.
     */
    @Override
    public void update(long dt) {
        currentTime += dt;
        ParkingEvent event = parkingEventQueue.peek();
        System.out.println(currentTime);
        while (event != null && event.getEventStartTime() <= currentTime) {
            event = parkingEventQueue.poll();  /// Removes from queue
            System.out.println("Processing " + event.getEventType().toString() + " Event - " + event.getEventStartTime());
            event = parkingEventQueue.peek();
        }   
    }
}
