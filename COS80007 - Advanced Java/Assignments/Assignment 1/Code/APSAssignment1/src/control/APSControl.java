package control;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.events.EventManager;
import aps.events.EventType;
import aps.events.IEventManagerListener;
import aps.events.ParkingEvent;
import aps.timer.APSTimer;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;
import java.util.PriorityQueue;

/**
 * The {@link APSControl}.
 * <p>
 * This class is responsible for managing the sequence of events in the 
 * Automatic Parking Simulator. It responds to the events received
 * from the {@link EventManager} to start the adding or removal of a car
 * from the car park.
 * <p>
 * @author szeyick
 */
public class APSControl implements IEventManagerListener, IAPSTimerListener {

    /**
     * The event manager. 
     */
    private EventManager eventManager;
    
    /**
     * A priority queue of parking events, prioritised by start time.
     */
    private PriorityQueue<ParkingEvent> parkingEventQueue;
    
    /**
     * The current parking event.
     */
    private ParkingEvent currentParkingEvent;
    
    /**
     * Is there an event being processed.
     */
    private boolean processEvent;
    
    /**
     * Constructor.
     */
    public APSControl(APSTimer timer) {
        parkingEventQueue = new PriorityQueue<ParkingEvent>();
        eventManager = new EventManager("simulatorTraffic.txt");
        processEvent = false;
        
        // Add listeners
        eventManager.addEventListener(this);
        timer.addTimerListener(this);
        timer.addTimerListener(eventManager);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processEvent(ParkingEvent event) {
       // Adding event.
       System.out.println("Adding Event " + event.getEventStartTime());
       parkingEventQueue.add(event);
    } 
    
    /***
     * Update whether the system is currently processing an event.
     * @param eventProcessing 
     */
    public void updateEventProcessing(boolean eventProcessing) {
        processEvent = eventProcessing;
        if (!processEvent) {
            currentParkingEvent = null;
        }
    }

    /**
     * {@inheritDoc 
     */
    @Override
    public void update(long dt) {
        if (currentParkingEvent == null && !parkingEventQueue.isEmpty()) {
            currentParkingEvent =  parkingEventQueue.poll();
            System.out.println("Polling Event");
        }
        // If the current event has completed, dequeue the next one.
        if (currentParkingEvent != null && !processEvent) {
            processEvent = true;  
            // Send Car or Pickup Car.
            System.out.println("Processing Event: " + currentParkingEvent.getEventType().toString());
            
            if (EventType.ARRIVAL.equals(currentParkingEvent.getEventType())) {
                // Arrival event so we create a new car and deploy it.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = new CarModel();
                
                // Add the car to the ground floor.
                car.updateCarState(CarModel.carState.MOVING);
                carModelManager.setCurrentCarModel(car);
            }
            else {
                // Departure event so we pick up a car.
            }
        }
        // If event is still going, we do nothing.
    }
}
