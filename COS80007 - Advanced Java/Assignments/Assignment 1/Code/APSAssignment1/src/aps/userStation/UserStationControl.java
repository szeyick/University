package aps.userStation;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;
import aps.timer.IAPSTimerListener;

/**
 * The {@link UserStationControl}.
 * <p>
 * This class is responsible for being the controller for the User Station in
 * the Automatic Parking Simulator. It manages the life cycle of the user
 * control and also the graphical components that will be drawn onto the
 * display.
 * <p>
 * @author szeyick
 */
public class UserStationControl implements IAPSTimerListener {

    /**
     * The data model containing the user station.
     */
    private UserStationModel model;

    /**
     * The state of the user control.
     */
    private UserControlState state;

    /**
     * Simulate 3 second delay.
     */
    private int delay = 3000;

    /**
     * The current time the control is ready.
     */
    private int currentTime;

    /**
     * Constructor.
     */
    public UserStationControl() {
        model = new UserStationModel();
        state = UserControlState.IDLE;
        currentTime = 0;
    }

    /**
     * A user has requested that their car be dropped off. Each time a request
     * is placed into the control, there is a 3 second delay before the next
     * action is invoked.
     */
    public void requestUserDropOff() {
        state = UserControlState.DROP_OFF;
        currentTime = 0;
    }

    /**
     * A user has requested to pick up their car. Each time a request is placed
     * into the control, there is a 3 second delay before the next action is
     * invoked.
     */
    public void requestUserPickup() {
        state = UserControlState.PICKUP;
        currentTime = 0;
    }
    
    public void resetTimer() {
        currentTime = 0;
    }

    /**
     * When this timer triggers, it will be responsible for notifying whether to
     * pick up a car or to drop one off. Dropping one off involves calling the
     * lift since the car is ready on the turntable
     */
    @Override
    public void update(long dt) {
        if (!UserControlState.IDLE.equals(state)) {
            currentTime += dt;
            System.out.println("User Station Delay - " + currentTime);
            // Ensure that the adequate delay has been done.
            if (currentTime == delay) {
                // If requesting pickup, then call the elevator.
                if (UserControlState.PICKUP.equals(state)) {
                    // Find a free bay and move the car there.
                    ParkingBayManager manager = ParkingBayManager.getManager();
                    ParkingBay parkingBay = manager.getFreeBay();
                    if (parkingBay != null) {
                        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                        if (carModel != null) {
                            // Call the elevator to the ground floor.
                            System.out.println("Calling Elevator to Ground...");
                            state = UserControlState.IDLE;
                            resetTimer();
                        }
                    }
                } else if (UserControlState.DROP_OFF.equals(state)) {
                            System.out.println("Requesting Pickup.");
                }
            }
        }

    }
}
