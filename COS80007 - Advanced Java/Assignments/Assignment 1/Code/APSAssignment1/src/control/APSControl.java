package control;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.car.CarState;
import aps.elevator.Elevator;
import aps.events.EventManager;
import aps.events.EventType;
import aps.events.IEventManagerListener;
import aps.events.ParkingEvent;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import aps.turntable.TurntableModel;
import aps.userStation.UserStationControl;
import java.util.PriorityQueue;

/**
 * The {@link APSControl}.
 * <p>
 * This class is responsible for managing the {@link ParkingEvents} in the
 * simulation. It responds to events received from the {@link EventManager}
 * to begin the event of adding or removing a car from the car park.
 * <p>
 * If multiple events are received, it is queued within this controller and
 * subsequent events will only be executed when the current event has completed
 * its execution.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class APSControl implements IEventManagerListener, IAPSTimerListener {

    /**
     * A reference to the event manager.
     */
    private final EventManager eventManager;

    /**
     * A priority queue of parking events, prioritised by start time.
     */
    private final PriorityQueue<ParkingEvent> parkingEventQueue;

    /**
     * The current parking event being executed.
     */
    private ParkingEvent currentParkingEvent;

    /**
     * boolean flag to indicate if there is a parking event
     * that is currently being processed.
     */
    private boolean processEvent;

    /**
     * An instance of the elevator to be shared within the simulation.
     */
    private final Elevator elevator;

    /**
     * An instance of the user control.
     */
    private final UserStationControl userStationControl;

    /**
     * The singleton instance of this control.
     */
    private static APSControl control;

    /**
     * Constructor.
     */
    private APSControl() {
        parkingEventQueue = new PriorityQueue<>();
        eventManager = new EventManager("simulatorTraffic.txt");
        processEvent = false;
        userStationControl = new UserStationControl();

        APSTimer timer = APSTimer.getTimer();
        elevator = new Elevator(timer);

        // Add components as listeners to the timer.
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
     * Add a parking event to the simulation.
     */
    @Override
    public void processEvent(ParkingEvent event) {
        // System.out.println("Adding Event " + event.getEventStartTime());
        parkingEventQueue.add(event);
    }

    /**
     * Update the state of the currently processed event.
     * <p>
     * @param eventProcessing - true if there is an event being
     * processed, false otherwise. False will indicate that the
     * current event has finished processing.
     */
    public void updateEventProcessing(boolean eventProcessing) {
        processEvent = eventProcessing;
        if (!processEvent) {
            currentParkingEvent = null;
            // Perhaps return the elevator to the ground floor before
            // terminating the simulation.
            elevator.stopElevator();
            CarModelManager.getModelManager().stopCurrentCarModel();
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
        // If the current event has completed, dequeue and execute the next event.
        if (currentParkingEvent != null && !processEvent) {
            processEvent = true;
            System.out.println("Processing Event: " + currentParkingEvent.getEventType().toString());

            if (EventType.ARRIVAL.equals(currentParkingEvent.getEventType())) {
                // Arrival event so we create a new car and load it into the simulation.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = new CarModel();

                // Add the car to the ground floor.
                car.updateCarState(CarState.MOVING);
                carModelManager.setCurrentCarModel(car);
            } 
            else {
                // Departure event so we pick up a car.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = carModelManager.getParkedCarModel();

                if (car != null) {
                    // User makes the request to pick up car from the user station.
                    userStationControl.requestUserPickup();
                }
            }
        }
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