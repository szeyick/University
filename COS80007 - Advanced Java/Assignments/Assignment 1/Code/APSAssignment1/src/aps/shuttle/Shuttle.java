package aps.shuttle;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.config.Config;
import aps.elevator.ElevOperation;
import aps.elevator.Elevator;
import aps.events.EventType;
import aps.events.ParkingEvent;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayDirection;
import aps.floor.ParkingBayManager;
import aps.timer.APSTimer;
import aps.timer.IAPSTimerListener;
import aps.control.APSControl;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aps.utilities.APSUtilities;

/**
 * The Shuttle.
 * <p>
 * This class represents the shuttle mechanism. In the simulation, the shuttle
 * carries the trolley. It moves sideways (left - right) when the trolley is
 * locked into it, with a car. The shuttle will move sideways to align its centre
 * X with either the centre of the elevator, or the parking bay in which it is
 * to deliver the car.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class Shuttle implements IAPSTimerListener {

    /**
     * Draw the shuttle shape.
     */
    private GeneralPath shuttleShape;

    /**
     * The centre X of the shuttle.
     */
    private float shuttleX;

    /**
     * The centre Y of the shuttle.
     */
    private final float shuttleY;

    /**
     * The elevator.
     */
    private final Elevator elevator;

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
    private final Trolley trolley;

    /**
     * The width of the shuttle.
     */
    private final float shuttleWidth;

    /**
     * The length of the shuttle.
     */
    private final float shuttleLength;

    /**
     * Constructor.
     *
     * @param elevator - A reference to the elevator.
     */
    public Shuttle(Elevator elevator) {
        this.elevator = elevator;
        shuttleWidth = Config.getConfig().SHUTTLE_WIDTH;
        shuttleLength = Config.getConfig().SHUTTLE_LENGTH;
        centreRadiusTT = 5; // Move to the this one.
        shuttleX = 1.6f + 2.5f; // Default to centre elevator.
        shuttleY = (Config.getConfig().BAY_LENGTH * 3) / 2;
        shuttleState = ShuttleState.LOCKED;
        trolley = new Trolley(this);
        APSTimer.getTimer().addTimerListener(trolley);
    }

    /**
     * Update the state of the shuttle.
     *
     * @param state - The new state of the shuttle.
     */
    public void updateShuttleState(ShuttleState state) {
        shuttleState = state;
    }

    /**
     * With the shuttle, if it starts to receive updates to retrieve we need to
     * get the car, which means to deploy the trolley to get the car.
     * <p>
     * The shuttle can move left and right to the correct bay to retrieve the
     * car. (On the ground floor, it lines up with the centre of the turntable
     * and on every other floor it lines up with the bay).
     *
     * @param dt - The time lapsed.
     */
    @Override
    public void update(long dt) {
        if (ShuttleState.LOCKED.equals(shuttleState)
                && ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())
                && TrolleyState.DOCKED_SHUTTLE.equals(trolley.getState())) {
            shuttleState = ShuttleState.UNLOCKED;
        }
        if (ShuttleState.UNLOCKED.equals(shuttleState)) {
            if (elevator.getCurrentFloor() == 0
                    && ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {

                shuttleState = ShuttleState.LOCKED;
                // Deploy the trolley but also stop update
                trolley.deployTrolley();
            }
            if (elevator.getCurrentFloor() != 0
                    && ElevOperation.DEPLOY_SHUTTLE.equals(elevator.getCurrentElevatorOperation())) {

                // If we are unlocked, we need to go to the designated bay. If trolley contains car.
                if (trolley.containsCar()) {
                    // Move to designated bay (if north bay we need the distance from the east wall.
                    updateCentreRadius();
                    // Move the shuttle along X axis  ()
                    moveShuttlePositiveXAxis();
                    if (trolley.containsCar()) {
                        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                        carModel.setDestinationPoint(new Point2D.Float(shuttleX, shuttleY));
                        carModel.updateDxDy(2, 0);
                        carModel.updateFloor(elevator.getCurrentFloor());
                        carModel.updateCoordinates();
                    }
                }
                if (!trolley.containsCar()) {
                    // If we do not have the car, we're here to pick one up.
                    updateCentreRadius();
                    moveShuttlePositiveXAxis();
                }
            }
        }
        if (ShuttleState.RETURNED.equals(shuttleState)) {
            // Shuttle to be marked to return to original point
            // If shuttle moves x, the trolley needs to move too.
            if (shuttleX <= centreRadiusTT || shuttleX <= (1.6f + 2.5f)) {
                shuttleX = 1.6f + 2.5f;
                   
                // Return to elevator operations.
                shuttleState = ShuttleState.IDLE;
                elevator.shuttleCallback();
            }
            if ((shuttleX > centreRadiusTT) && !ShuttleState.IDLE.equals(shuttleState)) {
                shuttleX--;
            }
            ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();

            // If we have the car, we need to move that too.
            if (event != null && EventType.DEPARTURE.equals(event.getEventType()) && trolley.containsCar()) {
                CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
                carModel.setDestinationPoint(new Point2D.Float(shuttleX, shuttleY));
                carModel.updateDxDy(-2, 0);
                carModel.updateFloor(elevator.getCurrentFloor());
                carModel.updateCoordinates();
            }
        }
    }

    /**
     * Move the shuttle in the X direction. It will move it to the right.
     */ 
    private void moveShuttlePositiveXAxis() {
        if (shuttleX >= (centreRadiusTT * 3)) {
            shuttleState = ShuttleState.LOCKED;
            // Deploy the trolley but also stop update
            trolley.deployTrolley();
        }
        if (shuttleX < (centreRadiusTT * 3)) {
            shuttleX++;
        }
    }

    /**
     * Update the centre radius of the elevator that the shuttle is required to
     * move from and return to.
     */
    private void updateCentreRadius() {
        ParkingBay parkingBay = ParkingBayManager.getManager().getFreeBay();
        if (ParkingBayDirection.NORTH.equals(parkingBay.getDirection())) {
            centreRadiusTT = calculateCentreRadiusNorth(parkingBay);
        }
        if (ParkingBayDirection.SOUTH.equals(parkingBay.getDirection())) {
            centreRadiusTT = calculateCentreRadiusSouth(parkingBay);
        }
    }

    /**
     * Calculate the distance to travel along the X axis if we are parking in a
     * north bay.
     *
     * @param parkedBay - The allocated bay to pick up or retrieve a car from.
     * @return the calculated centre of the designated north bay.
     */
    private float calculateCentreRadiusNorth(ParkingBay parkedBay) {
        // It is a north bay, so we do something.
        double distanceFromEastWall = APSUtilities.getNorthBayDistanceFromEastWall();
        int bayNumber = parkedBay.getBayNumber();
        double bayWidth = Config.getConfig().BAY_WIDTH;

        double centreBay = (bayNumber * bayWidth) + distanceFromEastWall - (0.5 * bayWidth);
        return (float) centreBay;
    }

    /**
     * Calculate the distance to travel along the X axis if we are parking in a
     * south bay.
     */
    private float calculateCentreRadiusSouth(ParkingBay parkedBay) {
        double centreXBay = Config.getConfig().SOUTH_BAY_CENTER_X;
        double bayWidth = Config.getConfig().BAY_WIDTH;
        double bayXStart = (parkedBay.getBayNumber() * bayWidth) + centreXBay - (Config.getConfig().BAY_WIDTH / 2);

        return (float) bayXStart;
    }

    /**
     * @return the bounds of the shuttle.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(shuttleX, shuttleY, shuttleWidth, shuttleLength);
    }

    /**
     * @return the shuttle shape to draw.
     */
    public GeneralPath getShuttle() {
        shuttleShape = new GeneralPath();
        drawShuttle();
        return shuttleShape;
    }

    /**
     * Draw the shuttle as a triangle.
     */
    private void drawShuttle() {
        shuttleShape.moveTo(shuttleX, shuttleY);
        shuttleShape.lineTo(shuttleX + shuttleWidth + 1, shuttleY + (shuttleLength / 2));
        shuttleShape.lineTo(shuttleX - shuttleWidth - 1, shuttleY + (shuttleLength / 2));
        shuttleShape.closePath();
    }

    /**
     * @return the y position of the shuttle.
     */
    public float getY() {
        return shuttleY;
    }

    /**
     * @return the x position of the shuttle.
     */
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
