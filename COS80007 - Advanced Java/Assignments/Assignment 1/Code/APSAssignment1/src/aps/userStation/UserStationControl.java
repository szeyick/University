package aps.userStation;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.elevator.Elevator;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;
import aps.timer.IAPSTimerListener;
import control.APSControl;
import java.awt.geom.Point2D;

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
        System.out.println("Requesting Pickup");
        currentTime = 0;
    }

    /**
     * The callback from the elevator to notify the user that their car is ready
     * for pickup.
     */
    public void carReadyForPickup() {
        state = UserControlState.COLLECT_CAR;
        resetTimer();
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
                if (UserControlState.DROP_OFF.equals(state)) {
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
                            Elevator elevator = APSControl.getControl().getElevator();
                            elevator.setDestinationFloor(parkingBay.getFloorNumber());
                            elevator.moveToFloor(0);
                        }
                    }
                } else if (UserControlState.PICKUP.equals(state)) {
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    System.out.println("Requesting Pickup.");
                    state = UserControlState.IDLE;
                    resetTimer();
                    Elevator elevator = APSControl.getControl().getElevator();
                    elevator.setDestinationFloor(carModel.getFloor());
                    elevator.moveToFloor(carModel.getFloor());
                }
            }
            if (currentTime >= delay) {
                if (UserControlState.COLLECT_CAR.equals(state)) {
                    System.out.println("Picking Up Car...Hello");
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    // If the car is outside the bounds of the panel then go away
                    carModel.updateCarState(CarModel.carState.MOVING);
                    carModel.updateFloor(0);
                    System.out.println("Car Y Pos: " + carModel.getCurrentYPosition());
                    if (carModel.getCurrentYPosition() > 350) {
                        CarModelManager.getModelManager().removeCarModel(carModel);
                        System.out.println("Car has left the building...");
                        state = UserControlState.IDLE;
                        resetTimer();
                        APSControl.getControl().updateEventProcessing(false);
                    }

                }
            }
        }

    }
}
