package aps.floor;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.car.CarState;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;
import aps.turntable.TurntableModel;
import aps.userStation.UserStationControl;
import aps.control.APSControl;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 * The GroundLevelFloorControl.
 * <p>
 * This class represents the controller for the ground level of the Automatic
 * Parking Simulator. It will be responsible for creating the view and the
 * components that will be drawn by the GroundLevelFloorView.
 * <p>
 * @author szeyick StudentID - 1763652.
 */
public class GroundLevelFloorControl implements IAPSTimerListener {

    /**
     * An integer representing the current floor.
     */
    private final int FLOOR_NUMBER = 0;

    /**
     * The distance components are from the wall;
     */
    private final int WALL_PADDING = 10;

    /**
     * The panel managed by this view.
     */
    private final GroundLevelFloorPanel panel;

    /**
     * The car to be drawn on the ground floor.
     */
    private CarModel car;

    /**
     * The turntable to draw on the ground floor.
     */
    private final TurntableModel turntable;

    /**
     * The user station control
     */
    private final UserStationControl userStationControl;

    /**
     * Constructor.
     *
     * @param timer - The timer.
     */
    public GroundLevelFloorControl(IAPSTimer timer) {
        turntable = new TurntableModel(WALL_PADDING);
        panel = new GroundLevelFloorPanel(turntable);
        userStationControl = APSControl.getControl().getUserStation();
        timer.addTimerListener(userStationControl);
    }

    /**
     * @return the panel.
     */
    public GroundLevelFloorPanel getPanel() {
        return panel;
    }

    /**
     * Update the car and turntable graphical components.
     */
    @Override
    public void update(long dt) {
        car = CarModelManager.getModelManager().getCurrentCarModel();
        ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();

        if (car != null) {
            if (CarState.MOVING.equals(car.getCarState())) {
                if (car.getFloor() == FLOOR_NUMBER) {
                    if (EventType.ARRIVAL.equals(event.getEventType())) {
                        moveCarTowardTurnTable();
                    }
                    if (EventType.DEPARTURE.equals(event.getEventType())) {
                        moveCarOutOfCarPark();
                    }
                }
                if (car.getBounds().intersects(turntable.getTurntableBounds())) {
                    updateCarState(CarState.IDLE);
                    userStationControl.requestUserDropOff();
                }
            }
        }
        panel.draw();
    }

    /**
     * Move the car diagonally from the waiting area towards the turntable.
     */
    private void moveCarTowardTurnTable() {
        updateCarModel(turntable.getPoint(), -2, -2, panel.getSize());
        updateCarState(CarState.MOVING);
        panel.setCarModel(car);
    }

    /**
     * Move the car southwards (Y) out of the car park exit.
     */
    private void moveCarOutOfCarPark() {
        Point2D destinationPoint = new Point2D.Float((float) turntable.getPoint().getX(), 0);
        updateCarModel(destinationPoint, 0, 10, panel.getSize());
        panel.setCarModel(car);
    }

    /**
     * Update the car models coordinates.
     *
     * @param destinationPoint - The point that the car will need to move to.
     * @param dx - The x direction in pixels that the car will need to move.
     * @param dy - The y direction in pixels that the car will need to move.
     * @param panelDimensions - The size of the panel the car will be drawn on.
     */
    private void updateCarModel(Point2D destinationPoint, int dx, int dy, Dimension panelDimensions) {
        car.setDestinationPoint(destinationPoint);
        car.updateDxDy(dx, dy);
        car.updateDimension(panelDimensions);
        car.updateCoordinates();
    }

    /**
     * Update the state of the car.
     *
     * @param state - The new car state.
     */
    private void updateCarState(CarState state) {
        car.updateCarState(state);
    }
}
