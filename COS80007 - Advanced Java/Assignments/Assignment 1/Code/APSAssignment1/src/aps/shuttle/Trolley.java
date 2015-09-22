package aps.shuttle;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.config.Config;
import aps.elevator.Elevator;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayDirection;
import aps.floor.ParkingBayManager;
import aps.timer.IAPSTimerListener;
import aps.control.APSControl;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The Trolley.
 * <p>
 * This class represents the trolley mechanism in the Automatic Parking
 * Simulator. The purpose of the trolley is to load and unload the car from
 * either a parking bay or from the turntable.
 * <p>
 * @author szeyick
 */
public class Trolley implements IAPSTimerListener {

    /**
     * The state of the trolley.
     */
    private TrolleyState state;

    /**
     * The current Y position of the trolley. The trolley defaults to the same Y
     * pos as the shuttle but will move when docked.
     */
    private float trolleyY;

    /**
     * The target destination of the trolley.
     */
    private float targetY = 5;

    /**
     * The time it takes to lock the trolley to the car.
     */
    private final int lockTime;

    /**
     * The current time.
     */
    private int currentTime;

    /**
     * A reference to the shuttle.
     */
    private final Shuttle shuttle;

    /**
     * boolean flag to indicate if the trolley is currently carrying
     * a car.
     */ 
    private boolean containsCar;

    /**
     * Constructor.
     *
     * @param shuttle - The shuttle instance.
     */
    public Trolley(Shuttle shuttle) {
        this.shuttle = shuttle;
        state = TrolleyState.DOCKED_SHUTTLE;
        trolleyY = shuttle.getY();
        lockTime = Config.getConfig().TROLLEY_LOCK_PERIOD;
        currentTime = 0;
        containsCar = false;
    }

    /**
     * @return true if the trolley is carrying a car, false otherwise.
     */
    public boolean containsCar() {
        return containsCar;
    }

    /**
     * Method to update the state of the trolley to deploy.
     */
    public void deployTrolley() {
        state = TrolleyState.DEPLOYING;
    }

    /**
     * @return the current state of the trolley.
     */
    public TrolleyState getState() {
        return state;
    }

    /**
     * Unlock the trolley to enable car moving.
     */
    public void unlockTrolley() {
        containsCar = false;
    }

    /**
     * Update the state of the trolley and redraw its coordinates.
     */
    @Override
    public void update(long dt) {
        Elevator elevator = APSControl.getControl().getElevator();
        // If we are on the ground floor, move the trolley up to pick up the car.
        if (elevator.getCurrentFloor() == 0) {
            if (TrolleyState.DEPLOYING.equals(state)) {
                targetY = 0;
                if (trolleyY <= targetY) {
                    state = TrolleyState.LOCKING;
                }
                if (trolleyY > targetY) {
                    trolleyY--;
                }
            } else if (TrolleyState.LOCKING.equals(state)) {
                if (currentTime == lockTime) {
                    state = TrolleyState.RETURNING;
                    containsCar = true;
                }
                if (currentTime < lockTime) {
                    currentTime += dt;
                }
            } else if (TrolleyState.RETURNING.equals(state)) {
                // Return to lift centre.
                targetY = shuttle.getY();
                if (trolleyY >= targetY) {
                    trolleyY = shuttle.getY();
                    state = TrolleyState.DOCKED_SHUTTLE;
                    // Return to Shuttle Control
                    currentTime = 0;
                    shuttle.updateShuttleState(ShuttleState.RETURNED);
                }
                if (trolleyY < targetY) {
                    trolleyY++;
                }
                if (containsCar) {
                    updateCarDxDy(0, 10, shuttle.getX(), trolleyY);
                }
            }
        }
        if (elevator.getCurrentFloor() != 0) {
            // If current floor is not 0 we need to move up or down depending on the allocated bay.
            // This is for unloading a car onto a bay.
            ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
            if (event != null && EventType.ARRIVAL.equals(event.getEventType())) {
                ParkingBay parkingBay = ParkingBayManager.getManager().getFreeBay();
                if (TrolleyState.DEPLOYING.equals(state)) {
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        updateNorthBayTrolleyDeploy();
                        if (containsCar) {
                            updateCarDxDy(0, -10, shuttle.getX(), targetY);
                        }
                    }
                    if (ParkingBayDirection.SOUTH.equals(parkingBay.getDirection())) {
                        updateSouthBayTrolleyDeploy();
                        if (containsCar) {
                            updateCarDxDy(0, 10, shuttle.getX(), targetY);
                        }
                    }
                } else if (TrolleyState.LOCKING.equals(state)) {
                    if (currentTime == lockTime) {
                        // Add the car to the parked floor.
                        state = TrolleyState.RETURNING;
                        containsCar = false;
                        updateCarInfo(containsCar, parkingBay);
                    }
                    if (currentTime < lockTime) {
                        currentTime += dt;
                    }
                } else if (TrolleyState.RETURNING.equals(state)) {
                    // Return to lift centre.
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        updateNorthBayTrolleyReturn();
                    }
                    if (ParkingBayDirection.SOUTH.equals(parkingBay.getDirection())) {
                        updateNorthBayTrolleyReturn();
                    }
                }
            }
            if (event != null && EventType.DEPARTURE.equals(event.getEventType())) {
                // Pick up a car.
                CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                ParkingBay parkingBay = ParkingBayManager.getManager().getParkingBayForCar(carModel);

                if (TrolleyState.DEPLOYING.equals(state)) {
                    // If deploying to retrieve the car, we should only update the trolley pos
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        updateNorthBayTrolleyDeploy();
                    }
                    if (ParkingBayDirection.SOUTH.equals(parkingBay.getDirection())) {
                        updateSouthBayTrolleyDeploy();
                    }
                } else if (TrolleyState.LOCKING.equals(state)) {
                    if (currentTime == lockTime) {
                        // Fasten car to the trolley.
                        state = TrolleyState.RETURNING;
                        containsCar = true;
                        updateCarInfo(containsCar, parkingBay);
                    }
                    if (currentTime < lockTime) {
                        currentTime += dt;
                    }
                } else if (TrolleyState.RETURNING.equals(state)) {
                    // Return to lift centre.
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        updateNorthBayTrolleyReturn();
                        if (containsCar) {
                            updateCarDxDy(0, 10, shuttle.getX(), trolleyY);
                        }
                    }
                    if (ParkingBayDirection.SOUTH.equals(parkingBay.getDirection())) {
                        updateSouthBayTrolleyReturn();
                        if (containsCar) {
                            updateCarDxDy(0, -10, shuttle.getX(), trolleyY);
                        }
                    }
                }
            }
        }
    }

    /**
     * Update the movement of the car horizontally and vertically.
     *
     * @param dx - The distance in pixels to move in the x direction.
     * @param dy - The distance in pixels to move in the y direction.
     * @param x - The point on the x axis to move to.
     * @param y - The point in the y axis to move to.
     */
    private void updateCarDxDy(int dx, int dy, float x, float y) {
        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
        carModel.setDestinationPoint(new Point2D.Float(x, y));
        carModel.updateDxDy(dx, dy);
        carModel.updateCoordinates();
    }

    /**
     * Update the trolley Y position when deploying to the north bay.
     */
    private void updateNorthBayTrolleyDeploy() {
        targetY = targetY = (float) Config.getConfig().BAY_LENGTH;
        if (trolleyY <= targetY) {
            state = TrolleyState.LOCKING;
        }
        if (trolleyY > targetY) {
            trolleyY--;
        }
    }

    /**
     * Update the trolley Y position when returning from a north bay
     */
    private void updateNorthBayTrolleyReturn() {
        targetY = shuttle.getY();
        if (trolleyY >= targetY) {
            trolleyY = shuttle.getY();
            state = TrolleyState.DOCKED_SHUTTLE;
            // Return to Shuttle Control
            currentTime = 0;
            shuttle.updateShuttleState(ShuttleState.RETURNED);
        }
        if (trolleyY < targetY) {
            trolleyY++;
        }
    }

    /**
     * Update the trolley Y position when deploying to the south bay.
     */
    private void updateSouthBayTrolleyDeploy() {
        targetY = (float) Config.getConfig().BAY_LENGTH * 3;
        if (trolleyY >= targetY) {
            state = TrolleyState.LOCKING;
        }
        if (trolleyY < targetY) {
            trolleyY++;
        }
    }

    /**
     * Update the trolley Y position when returning from a south bay.
     */
    private void updateSouthBayTrolleyReturn() {
        targetY = shuttle.getY();
        if (trolleyY <= targetY) {
            trolleyY = shuttle.getY();
            state = TrolleyState.DOCKED_SHUTTLE;
            // Return to Shuttle Control
            currentTime = 0;
            shuttle.updateShuttleState(ShuttleState.RETURNED);
        }
        if (trolleyY > targetY) {
            trolleyY--;
        }
    }

    /**
     * Update the car information.
     *
     * @param carUnlockedFromTrolley - false if the car has been unlocked from
     * the trolley, true otherwise.
     * @param parkingBay - The bay the car is parked in.
     */
    private void updateCarInfo(boolean carUnlockedFromTrolley, ParkingBay parkingBay) {
        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
        carModel.updateFloor(parkingBay.getFloorNumber());
        parkingBay.setCarModel(carModel);
        System.out.println("Adding Car To Level and Bay - " + parkingBay.getFloorNumber() + " " + parkingBay.getBayNumber());
         
        if (!carUnlockedFromTrolley) {
            ParkingBayManager.getManager().carParkedInBay();
            CarModelManager.getModelManager().addCarToFloor(parkingBay.getFloorNumber(), carModel);
        }
    }

    /**
     * @return the bounds of the trolley.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(shuttle.getX(), trolleyY, 0.3f, 4.75f);
    }

    /**
     * @return the trolley shape to draw.
     */
    public GeneralPath getTrolleyShape() {
        GeneralPath trolleyShape = drawTrolley();
        return trolleyShape;
    }

    /**
     * @return the trolley shape as a triangle.
     */
    private GeneralPath drawTrolley() {
        GeneralPath trolleyShape = new GeneralPath();
        trolleyShape.moveTo(shuttle.getX(), trolleyY);
        trolleyShape.lineTo(shuttle.getX() + 0.3f + 1, trolleyY + (4.75f / 2));
        trolleyShape.lineTo(shuttle.getX() - 0.3f - 1, trolleyY + (4.75f / 2));
        trolleyShape.closePath();

        return trolleyShape;
    }
}
