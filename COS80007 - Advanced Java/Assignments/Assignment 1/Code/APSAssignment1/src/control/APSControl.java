package control;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.car.CarState;
import aps.elevator.Elevator;
import aps.events.EventManager;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import aps.userStation.UserStationControl;
import java.util.PriorityQueue;
import javax.swing.JLabel;

/**
 * The {@link APSControl}.
 * <p>
 * This class is responsible for managing the {@link ParkingEvents} in the
 * simulation. It responds to events received from the {@link EventManager} to
 * begin the event of adding or removing a car from the car park.
 * <p>
 * If multiple events are received, it is queued within this controller and
 * subsequent events will only be executed when the current event has completed
 * its execution.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class APSControl implements IAPSTimerListener {

    /**
     * A priority queue of parking events, prioritised by start time.
     */
    private final PriorityQueue<ParkingEvent> parkingEventQueue;

    /**
     * The current parking event being executed.
     */
    private ParkingEvent currentParkingEvent;

    /**
     * boolean flag to indicate if there is a parking event that is currently
     * being processed.
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
     * The label to display the number of cars waiting in the forecourt.
     * (ARRIVAL Event)
     */
    private final JLabel carArrivalLabel;
    
    /**
     * The label to display the number of users awaiting pickup of their
     * vehicle. (DEPARTURE Event)
     */ 
    private final JLabel carDepartureLabel;

    /**
     * The number of users waiting to drop off their cars..
     */
    private int arrivalEventNum;

    /**
     * The number of users waiting to pick up their cars..
     */
    private int departureEventNum;

    /**
     * Constructor.
     */
    private APSControl() {
        parkingEventQueue = new PriorityQueue<>();
        processEvent = false;
        userStationControl = new UserStationControl();
        arrivalEventNum = 0;
        departureEventNum = 0;
        APSTimer timer = APSTimer.getTimer();
        elevator = new Elevator();
        carArrivalLabel = new JLabel(String.valueOf(arrivalEventNum));
        carDepartureLabel = new JLabel(String.valueOf(departureEventNum));
        
        // Add components as listeners to the timer.
        timer.addTimerListener(this);
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
     * Add a parking event to the simulation. An event that is added will be
     * invoked immediately if there are no currently executed events.
     *
     * @param event - The parking event to add.
     */
    private void processEvent(ParkingEvent event) {
        // System.out.println("Adding Event " + event.getEventStartTime());
        parkingEventQueue.add(event);
        if (EventType.ARRIVAL.equals(event.getEventType())) {
            arrivalEventNum++;
            carArrivalLabel.setText(String.valueOf(arrivalEventNum));
        } else {
            departureEventNum++;
            carDepartureLabel.setText(String.valueOf(departureEventNum));
        }
    }

    /**
     * Create a parking event. An event that is manually entered into the system
     * is immediately invoked if possible.
     *
     * @param eventType - The type of event to create.
     */
    public void createParkingEvent(EventType eventType) {
        long eventStartTime = 0;

        ParkingEvent event = new ParkingEvent(eventType, eventStartTime, "");
        processEvent(event);
    }

    /**
     * Update the state of the currently processed event.
     * <p>
     * @param eventProcessing - true if there is an event being processed, false
     * otherwise. False will indicate that the current event has finished
     * processing.
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
                arrivalEventNum--;
                carArrivalLabel.setText(String.valueOf(arrivalEventNum));
                // Arrival event so we create a new car and load it into the simulation.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = new CarModel();

                // Add the car to the ground floor.
                car.updateCarState(CarState.MOVING);
                carModelManager.setCurrentCarModel(car);
            } else {
                departureEventNum--;
                carDepartureLabel.setText(String.valueOf(departureEventNum));
                // Departure event so we pick up a car.
                CarModelManager carModelManager = CarModelManager.getModelManager();
                CarModel car = carModelManager.getParkedCarModel();

                if (car != null) {
                    // User makes the request to pick up car from the user station.
                    userStationControl.requestUserPickup();
                }
                else {
                    updateEventProcessing(false);
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

    /**
     * @return the label with the number of departure events.
     */
    public JLabel getCarsForPickup() {
        return carDepartureLabel;
    }
    
    /**
     * @return the label with the number of arrival events.
     */
    public JLabel getCarsForDropOff() {
        return carArrivalLabel;
    }
}
