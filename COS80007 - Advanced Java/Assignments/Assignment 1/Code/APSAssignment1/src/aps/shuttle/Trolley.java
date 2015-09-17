/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import control.APSControl;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The {@link Trolley}.
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
     * The current X position of the trolley. The trolley X will always follow
     * the shuttleX position.
     */
    private float trolleyX;

    /**
     * The target destination of the trolley.
     */
    private float targetY = 5;

    /**
     * The time it takes to lock the trolley to the car.
     */
    private int lockTime;

    /**
     * The current time.
     */
    private int currentTime;

    /**
     * A reference to the shuttle.
     */
    private Shuttle shuttle;

    // Will be true if the trolley has locked a car.
    private boolean containsCar;

    public enum TrolleyState {

        DOCKED_SHUTTLE, DEPLOYING, RETURNING, LOCKING, LOCKED;
    }

    /**
     * Constructor.
     */
    public Trolley(Shuttle shuttle) {
        this.shuttle = shuttle;
        state = TrolleyState.DOCKED_SHUTTLE;
        trolleyY = shuttle.getY();
        lockTime = 3000;
        currentTime = 0;
        containsCar = false;
    }

    public boolean containsCar() {
        return containsCar;
    }

    public void deployTrolley() {
        // Deploying the trolley will move it north/south to get or unload
        // a car. 
        System.out.println("Deploying Trolley");
        state = TrolleyState.DEPLOYING;
    }

    public TrolleyState getState() {
        return state;
    }

    @Override
    public void update(long dt) {
        Elevator elevator = APSControl.getControl().getElevator();
        // If we are on the ground floor, move the trolley up to pick up the car.
        if (elevator.getCurrentFloor() == 0) {
            if (TrolleyState.DEPLOYING.equals(state)) {
                targetY = 5.0f; // Should be centre of the turntable.
                if (trolleyY <= targetY) {
                    state = TrolleyState.LOCKING;
                    System.out.println("Locking Trolley");
                }
                if (trolleyY > targetY) {
                    trolleyY--;
                }
            } else if (TrolleyState.LOCKING.equals(state)) {
                if (currentTime == lockTime) {
                    state = TrolleyState.RETURNING;
                    containsCar = true;
                    System.out.println("Returning Trolley");
                }
                if (currentTime < lockTime) {
                    currentTime += dt;
                }
            } else if (TrolleyState.RETURNING.equals(state)) {
                // Return to lift centre.
                targetY = shuttle.getY(); // Should be centre of the turntable.
                if (trolleyY >= targetY) {
                    trolleyY = shuttle.getY();
                    state = TrolleyState.DOCKED_SHUTTLE;
                    System.out.println("Docking Trolley");
                    // Return to Shuttle Control
                    currentTime = 0;
                    shuttle.updateShuttleState(Shuttle.ShuttleState.RETURNED);
                }
                if (trolleyY < targetY) {
                    trolleyY++;
                }
                if (containsCar) {
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    carModel.setDestinationPoint(new Point2D.Float(shuttle.getX(), trolleyY));
                    carModel.updateDxDy(0, 10);
                    carModel.updateCoordinates();
                }
            }
        }
        if (elevator.getCurrentFloor() != 0) {
            // If current floor is not 0 we need to move up or down depending on the allocated bay.
            // This is for unloading a car onto a bay.
            ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
            if (event != null && EventType.ARRIVAL.equals(event.getEventType())) {
                ParkingBay parkingBay = ParkingBayManager.getManager().getFreeBay();
                Config config = Config.getConfig();
                if (TrolleyState.DEPLOYING.equals(state)) {
                    // Get the bay information.
                    targetY = (float) config.BAY_LENGTH;
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        if (trolleyY <= targetY) {
                            state = TrolleyState.LOCKING;
                            System.out.println("Locking Trolley");
                        }
                        if (trolleyY > targetY) {
                            trolleyY--;
                        }
                        if (containsCar) {
                            CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                            carModel.setDestinationPoint(new Point2D.Float(shuttle.getX(), targetY));
                            carModel.updateDxDy(0, -10);
                            carModel.updateCoordinates();
                        }
                    }
                } else if (TrolleyState.LOCKING.equals(state)) {
                    if (currentTime == lockTime) {
                        // Drop the car off.
                        state = TrolleyState.RETURNING;
                        containsCar = false;
                        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                        carModel.updateFloor(parkingBay.getFloorNumber());
                        parkingBay.setCarModel(carModel);
                        CarModelManager.getModelManager().addCarToFloor(parkingBay.getFloorNumber(), carModel);
                        System.out.println("Returning Trolley");
                    }
                    if (currentTime < lockTime) {
                        currentTime += dt;
                    }
                } else if (TrolleyState.RETURNING.equals(state)) {
                    // Return to lift centre.
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        targetY = shuttle.getY(); // Should be centre of the turntable.
                        if (trolleyY >= targetY) {
                            trolleyY = shuttle.getY();
                            state = TrolleyState.DOCKED_SHUTTLE;
                            System.out.println("Docking Trolley");
                            // Return to Shuttle Control
                            currentTime = 0;
                            shuttle.updateShuttleState(Shuttle.ShuttleState.RETURNED);
                        }
                        if (trolleyY < targetY) {
                            trolleyY++;
                        }
                    }
                }
            }
            if (event != null && EventType.DEPARTURE.equals(event.getEventType())) {
                // Pick up a car.
                CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                ParkingBay parkingBay = ParkingBayManager.getManager().getParkingBayForCar(carModel);

                Config config = Config.getConfig();
                if (TrolleyState.DEPLOYING.equals(state)) {
                    targetY = targetY = (float) config.BAY_LENGTH;
                    // If deploying to retrieve the car, we should only update the trolley pos
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        if (trolleyY <= targetY) {
                            state = TrolleyState.LOCKING;
                            System.out.println("Locking Trolley");
                        }
                        if (trolleyY > targetY) {
                            trolleyY--;
                        }
                    }
                } else if (TrolleyState.LOCKING.equals(state)) {
                    if (currentTime == lockTime) {
                        // Drop the car off.
                        state = TrolleyState.RETURNING;
                        containsCar = true;
                        carModel.updateFloor(parkingBay.getFloorNumber());
                        parkingBay.setCarModel(carModel);
                        CarModelManager.getModelManager().addCarToFloor(parkingBay.getFloorNumber(), carModel);
                        System.out.println("Returning Trolley");
                    }
                    if (currentTime < lockTime) {
                        currentTime += dt;
                    }
                } else if (TrolleyState.RETURNING.equals(state)) {
                    // Return to lift centre.
                    targetY = shuttle.getY(); // Should be centre of the turntable.
                    if (trolleyY >= targetY) {
                        trolleyY = shuttle.getY();
                        state = TrolleyState.DOCKED_SHUTTLE;
                        System.out.println("Docking Trolley");
                        // Return to Shuttle Control
                        currentTime = 0;
                        shuttle.updateShuttleState(Shuttle.ShuttleState.RETURNED);
                    }
                    if (trolleyY < targetY) {
                        trolleyY++;
                    }
                    if (containsCar) {
                        carModel.setDestinationPoint(new Point2D.Float(shuttle.getX(), trolleyY));
                        carModel.updateDxDy(0, 10);
                        carModel.updateCoordinates();
                    }
                }
            }
        }
    }

    /**
     * @return the bounds of the trolley.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(shuttle.getX(), trolleyY, 0.3f, 4.75f);

    }
}
