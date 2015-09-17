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
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import control.APSControl;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import utilities.APSUtilities;

/**
 * The {@link Shuttle}.
 * <p>
 * This class represents the shuttle mechanism. In the simulation, the shuttle
 * carries the trolley. It moves sideways (left - right) when the trolley is
 * locked into it, with a car. The shuttle will move sidways to align its centre
 * X with either the centre of the elevator, or the parking bay in which it is
 * to deliver the car.
 * <p>
 * @author szeyick
 */
public class Shuttle implements IAPSTimerListener {

    /**
     * The centre X of the shuttle.
     */
    private float shuttleX;

    /**
     * The centre Y of the shuttle.
     */
    private float shuttleY;

    /**
     * The elevator.
     */
    private Elevator elevator;

    /**
     * The centre radius of the turntable.
     */
    private float centreRadiusTT;

    /**
     * The state of the shuttle.
     */
    private ShuttleState shuttleState;

    /**
     * The trolley.
     */
    private Trolley trolley;

    /**
     * The width of the shuttle.
     */
    private float shuttleWidth = 0.5f;

    /**
     * The length of the shuttle.
     */
    private float shuttleLength = 5.75f;

    /**
     * The states that the shuttle can be in.
     */
    public enum ShuttleState {

        DEPLOYING, LOCKED, UNLOCKED, RETURNED, IDLE;
    }

    /**
     * Constructor.
     *
     * @param elevator - A reference to the elevator.
     */
    public Shuttle(Elevator elevator, APSTimer timer) {
        this.elevator = elevator;
        centreRadiusTT = 5; // Move to the this one.
        shuttleX = 1.6f; // Default to centre elevator.
        shuttleY = (5.75f * 3) / 2;
        shuttleState = ShuttleState.LOCKED;
        trolley = new Trolley(this);
        timer.addTimerListener(trolley);
    }

    public void updateShuttleState(ShuttleState state) {
        shuttleState = state;

        // Update shuttle
    }

    /**
     * With the shuttle, if it starts to receive updates to retrieve we need to
     * get the car, which means to deploy the trolley to get the car.
     * <p>
     * The shuttle can move left and right to the correct bay to retrieve the
     * car. (On the ground floor, it lines up with the centre of the turntable
     * and on every other floor it lines up with the bay).
     *
     * @param dt
     */
    @Override
    public void update(long dt) {
        // Need a reference to whether we are doing a delivery or retrieval.
        // For deliveries, we need to know the bay to assign. Means we need 
        // a bay manager.
        if (ShuttleState.LOCKED.equals(shuttleState)
                && Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())
                && Trolley.TrolleyState.DOCKED_SHUTTLE.equals(trolley.getState())) {
            shuttleState = ShuttleState.UNLOCKED;
            System.out.println("Unlocking Shuttle");
        }
        if (ShuttleState.UNLOCKED.equals(shuttleState)) {
            if (elevator.getCurrentFloor() == 0
                    && Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {

                if (shuttleX >= centreRadiusTT) {
                    shuttleState = ShuttleState.LOCKED;
                    // Deploy the trolley but also stop update
                    System.out.println("Locking Shuttle");
                    trolley.deployTrolley();
                }
                // Move the shuttle along X axis
                if (shuttleX < centreRadiusTT) {
                    shuttleX++;
                    System.out.println("Moving Shuttle");
                }
            }
            if (elevator.getCurrentFloor() != 0
                    && Elevator.ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {

                // Find the parking bay to move the car to.                
                ParkingBay parkingBay = ParkingBayManager.getManager().getFreeBay();
                Config config = Config.getConfig();

                // If we are unlocked, we need to go to the designated bay. If trolley contains car.
                if (trolley.containsCar()) {
                    // Move to designated bay (if north bay we need the distance from the east wall.
                    if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
                        // It is a north bay, so we do something.
                        double distanceFromEastWall = APSUtilities.getNorthBayDistanceFromEastWall();
                        int bayNumber = parkingBay.getBayNumber();
                        double bayWidth = config.BAY_WIDTH;

                        double centreBay = (bayNumber * bayWidth) + distanceFromEastWall - (0.5 * bayWidth);
                        centreRadiusTT = (float) centreBay;
                    }
                    // Move the shuttle along X axis  ()
                    if (shuttleX >= centreRadiusTT) {
                        shuttleState = ShuttleState.LOCKED;
                        // Deploy the trolley but also stop update
                        System.out.println("Locking Shuttle With Car");
                        trolley.deployTrolley();
                    }
                    if (shuttleX < centreRadiusTT) {
                        shuttleX++;
                        System.out.println("Moving Shuttle With Car");
                    }
                }
                if (!trolley.containsCar()) {
                    // If we do not have the car, we're here to pick one up.
                    CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                    ParkingBay parkedBay = ParkingBayManager.getManager().getParkingBayForCar(carModel);
                    
                    if (ParkingBayDirection.NORTH.equals(parkedBay.getDirection())) {
                        // It is a north bay, so we do something.
                        double distanceFromEastWall = APSUtilities.getNorthBayDistanceFromEastWall();
                        int bayNumber = parkedBay.getBayNumber();
                        double bayWidth = config.BAY_WIDTH;

                        double centreBay = (bayNumber * bayWidth) + distanceFromEastWall - (0.5 * bayWidth);
                        centreRadiusTT = (float) centreBay;
                    }
                    // Move the shuttle along X axis  ()
                    if (shuttleX >= centreRadiusTT) {
                        shuttleState = ShuttleState.LOCKED;
                        // Deploy the trolley but also stop update
                        System.out.println("Locking Shuttle With Car");
                        trolley.deployTrolley();
                    }
                    if (shuttleX < centreRadiusTT) {
                        shuttleX++;
                        System.out.println("Moving Shuttle With Car");
                    }
                }
            }
        }
        if (ShuttleState.RETURNED.equals(shuttleState)) {
            // Shuttle to be marked to return to original point
            // If shuttle moves x, the trolley needs to move too.
            if (shuttleX <= 0) {
                shuttleX = 0;
                // Return to elevator operations.
                shuttleState = ShuttleState.IDLE;
                System.out.println("Shuttle Idle");
                elevator.shuttleCallback();
            }
            if (shuttleX > 0) {
                shuttleX--;
                System.out.println("Moving Shuttle Back to Base");
            }
            ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
            
            // If we have the car, we need to move that too.
            if (event != null && EventType.DEPARTURE.equals(event.getEventType()) && trolley.containsCar()) {
                CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                carModel.setDestinationPoint(new Point2D.Float(shuttleX, shuttleY));
                carModel.updateDxDy(-10, 0);
                carModel.updateCoordinates();
            }
        }
    }

    /**
     * @return the bounds of the shuttle.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(shuttleX, shuttleY, shuttleWidth, shuttleLength);
    }

    /**
     * @return the y position of the shuttle.
     */
    public float getY() {
        return shuttleY;
    }

    public float getX() {
        return shuttleX;
    }

    /**
     * @return the trolley.
     */
    public Trolley getTrolley() {
        return trolley;
    }
}
