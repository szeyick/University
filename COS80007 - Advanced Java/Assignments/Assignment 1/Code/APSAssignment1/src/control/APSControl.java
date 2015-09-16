package control;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.elevator.Elevator;
import aps.events.EventManager;
import aps.events.EventType;
import aps.events.IEventManagerListener;
import aps.events.ParkingEvent;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import aps.userStation.UserStationControl;
import java.util.PriorityQueue;

/**
 * The {@link APSControl}.
 * <p>
 * This class is responsible for managing the sequence of events in the
 * Automatic Parking Simulator. It responds to the events received from the
 * {@link EventManager} to start the adding or removal of a car from the car
 * park.
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
     * An instance of the elevator to be shared within the simulation.
     */
    private Elevator elevator;

    /**
     * An instance of the user control.
     */
    private UserStationControl userStationControl;

    /**
     * The instance of the control.
     */
    private static APSControl control;

    /**
     * Constructor.
     */
    private APSControl() {
        parkingEventQueue = new PriorityQueue<ParkingEvent>();
        eventManager = new EventManager("simulatorTraffic.txt");
        processEvent = false;
        userStationControl = new UserStationControl();

        APSTimer timer = APSTimer.getTimer();
        elevator = new Elevator(timer);

        // Add listeners
        eventManager.addEventListener(this);
        timer.addTimerListener(this);
        timer.addTimerListener(eventManager);
        timer.addTimerListener(elevator);
        timer.addTimerListener(userStationControl);
    }

    /**
     * @return the current parking event.
     */
    public ParkingEvent getCurrentParkingEvent() {
        return currentParkingEvent;
    }

    /**
     * @return an instance of the user station.
     */
    public UserStationControl getUserStation() {
        return userStationControl;
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

    /**
     * *
     * Update whether the system is currently processing an event.
     *
     * @param eventProcessing
     */
    public void updateEventProcessing(boolean eventProcessing) {
        processEvent = eventProcessing;
        if (!processEvent) {
            currentParkingEvent = null;
            elevator.stopElevator();
        }
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void update(long dt) {
        if (currentParkingEvent == null && !parkingEventQueue.isEmpty()) {
            currentParkingEvent = parkingEventQueue.poll();
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
            } else {
                // Departure event so we pick up a car.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = carModelManager.getParkedCarModel();

                if (car != null) {
                    // Move to floor and start doing things.
                    userStationControl.requestUserPickup();
                }
            }
        }
        // If event is still going, we do nothing.
    }

    /**
     * @return an instance of the elevator.
     */
    public Elevator getElevator() {
        return elevator;
    }

    /**
     * @return an instance of the APSControl
     */
    public static APSControl getControl() {
        if (control == null) {
            control = new APSControl();
        }
        return control;
    }
}
