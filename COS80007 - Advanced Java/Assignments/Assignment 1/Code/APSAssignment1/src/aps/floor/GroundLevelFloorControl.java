/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;
import aps.turntable.TurntableModel;
import aps.userStation.UserStationControl;
import control.APSControl;

/**
 * The {@link GroundLevelFloorControl}.
 * <p>
 * This class represents the controller for the ground level of the Automatic
 * Parking Simulator. It will be responsible for creating the view and the
 * components that will be drawn by the {@link GroundLevelFloorView}.
 * <p>
 * @author szeyick
 */
public class GroundLevelFloorControl implements IAPSTimerListener {

    /**
     * An integer representing the current floor.
     */
    private final int FLOOR_NUMBER = 0;

    /**
     * The distance things are from the wall;
     */
    private final int WALL_PADDING = 5;

    /**
     * The panel managed by this view.
     */
    private GroundLevelFloorPanel panel;

    /**
     * The car to be drawn on the ground floor.
     */
    private CarModel car;

    /**
     * The turntable to draw on the ground floor.
     */
    private TurntableModel turntable;

    /**
     * The user station control
     */
    private UserStationControl userStationControl;
            
    /**
     * Constructor.
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
     * Update the models.
     */
    @Override
    public void update(long dt) {
        // The car model will be updated then draw the rest of the shit.
        car = CarModelManager.getModelManager().getCurrentCarModel();
        if (car != null && car.getFloor() == FLOOR_NUMBER && CarModel.carState.MOVING.equals(car.getCarState())) {
            System.out.println("Drawing Car");
            car.setDestinationPoint(turntable.getPoint());
            car.updateDxDy(-2, -2);
            car.updateDimension(panel.getSize());
            car.updateCoordinates();
            car.updateCarState(CarModel.carState.MOVING);
            panel.setCarModel(car);
            
        }
        // If the car has arrived on the turntable we stop moving the car.
        if (car != null && car.getBounds().intersects(turntable.getTurntableBounds())) {
            if (car.getCarState().equals(CarModel.carState.MOVING)) {
                System.out.println("Car has arrived on the turntable");                
                car.updateCarState(CarModel.carState.IDLE);
                userStationControl.requestUserDropOff();
            }
        }
        panel.draw();
    }
}
