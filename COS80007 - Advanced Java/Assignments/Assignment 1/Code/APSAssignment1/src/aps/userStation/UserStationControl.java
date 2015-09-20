package aps.userStation;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.car.CarState;
import aps.elevator.Elevator;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;
import aps.timer.IAPSTimerListener;
import control.APSControl;

/**
 * The {@link UserStationControl}.
 * <p>
 * This class is responsible for being the controller for the User Station in
 * the Automatic Parking Simulator. It manages the life cycle of the user
 * control and also the graphical components that will be drawn onto the
 * display.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class UserStationControl implements IAPSTimerListener {

    /**
     * The data model containing the user station.
     */
    private final UserStationModel model;

    /**
     * The state of the user control.
     */
    private UserControlState state;

    /**
     * Simulate 3 second delay.
     */
    private final int delay = 1000;

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
        resetTimer();
    }

    /**
     * A user has requested to pick up their car. Each time a request is placed
     * into the control, there is a 3 second delay before the next action is
     * invoked.
     */
    public void requestUserPickup() {
        state = UserControlState.PICKUP;
        // System.out.println("Requesting Pickup");
        resetTimer();
    }

    /**
     * The callback from the elevator to notify the user that their car is ready
     * for pickup.
     */
    public void carReadyForPickup() {
        state = UserControlState.COLLECT_CAR;
        resetTimer();
    }

    /**
     * Set the user control state to idle.
     */
    private void setIdleAndReset() {
        state = UserControlState.IDLE;
        resetTimer();
    }

    /**
     * Reset the internal timer of the user station back to 0.
     */
    public void resetTimer() {
        currentTime = 0;
    }

    public void elevatorArrivedAtFloor() {

    }

    /**
     * Call the elevator.
     * <p>
     * @param destinationFloor - The destination floor the elevator is to move
     * to.
     * @param moveToFloor - The floor to move the elevator.
     */
    private void callElevator(int destinationFloor, int moveToFloor) {
        Elevator elevator = APSControl.getControl().getElevator();
        elevator.setDestinationFloor(destinationFloor);
        elevator.moveToFloor(moveToFloor);
        System.out.println("Moving Elevator - " + destinationFloor + " " + moveToFloor);
    }

    /**
     * When this method is invoked, it will be responsible for notifying whether
     * to pick up a car, drop a car off or await a user to drive away.
     */
    @Override
    public void update(long dt) {
        if (!UserControlState.IDLE.equals(state)) {
            currentTime += dt;
            // System.out.println("User Station Delay - " + currentTime);
            // Ensure that the adequate delay has been done.
            if (currentTime == delay) {
                // If requesting pickup, then call the elevator.
                if (UserControlState.DROP_OFF.equals(state)) {
                    // Find a free bay and move the car there.
                    ParkingBayManager manager = ParkingBayManager.getManager();
                    ParkingBay parkingBay = manager.getFreeBay();
                    if (parkingBay != null) {
                        System.out.println("Parking in Bay: " + parkingBay.getBayNumber());
                        System.out.println("Bay Direction: " + parkingBay.getDirection());
                        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                        if (carModel != null) {
                            // Call the elevator to the ground floor.
                            // System.out.println("Calling Elevator to Ground...");
                            setIdleAndReset();
                            callElevator(parkingBay.getFloorNumber(), 0);
                        }
                    }
                } else if (UserControlState.PICKUP.equals(state)) {
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    // System.out.println("Requesting Pickup.");
                    setIdleAndReset();
                    callElevator(carModel.getFloor(), carModel.getFloor());
                }
            }
            if (currentTime >= delay) {
                if (UserControlState.COLLECT_CAR.equals(state)) {
                    // System.out.println("Picking Up Car...Hello");
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    // If the car is outside the bounds of the panel then go away
                    carModel.updateCarState(CarState.MOVING);
                    carModel.updateFloor(0);
                    // System.out.println("Car Y Pos: " + carModel.getCurrentYPosition());
                    if (carModel.getCurrentYPosition() > 350) {
                        // System.out.println("Car has left the building...");
                        CarModelManager.getModelManager().removeCarModel(carModel);
                        setIdleAndReset();
                        APSControl.getControl().updateEventProcessing(false);
                    }
                }
            }
        }
        // Check if car has arrived on TT, if it has then do move open elevator door.

    }
}
