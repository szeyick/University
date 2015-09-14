package aps.car;

import aps.timer.IAPSTimerListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import utilities.APSUtilities;

/**
 * The {@link CarModel}.
 * <p>
 * This class represents the data structure for holding details about a car
 * instance.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class CarModel {

    /**
     * The floor the car is on.
     */
    private int floor;

    /**
     * The number plate for the car.
     */
    private String numberPlate;

    /**
     * The current x position of the car, relative to the floor.
     */
    private double xCoord;

    /**
     * The current y position of the car, relative to the floor.
     */
    private double yCoord;

    /**
     * The x direction in which to move the car.
     */
    private int dx;

    /**
     * The y direction in which to move the car.
     */
    private int dy;

    /**
     * The shape that represents the car.
     */
    private Rectangle2D car;

    /**
     * The destination point to move the car to.
     */
    private Point2D destination;

    /**
     * The dimension of the panel that this model is contained in.
     */
    private Dimension dimension;

    private int carWidth;

    private int carLength;

    private carState state;
    
    /**
     * The two states that a car can be in. The current car will be moving, all
     * other cars will be idle. If it is idle, the listener will not update the
     * coordinates.
     */
    public enum carState {

        IDLE, MOVING;
    }

    /**
     * Constructor.
     */
    public CarModel() {
        carWidth = 2;
        carLength = 5;
        car = new Rectangle2D.Double(0, 0, carWidth, carLength);
        xCoord = 100;
        yCoord = 200;
        floor = 0;
        state = carState.IDLE;
    }

    /**
     * @return the current x position of the car.
     */
    public double getCurrentXPosition() {
        return xCoord;
    }

    /**
     * @return the current y position of the car.
     */
    public double getCurrentYPosition() {
        return yCoord;
    }

    /**
     * Update the coordinates. This will move the target from x,y to x + dx, y +
     * dy
     *
     * 200, 200 move to 50, 50 (Decremental steps)
     */
    public void updateCoordinates() {
        // Move in the x direction.
        if (destination.getX() <= xCoord) {
            xCoord += dx;
            if (xCoord < 0) {
                xCoord = 0;
                dx = -dx;
            }
            if (xCoord + carWidth >= dimension.width) {
                xCoord = dimension.width - carWidth;
                dx = -dx;
            }
        }
        // Move in the y direction.
        if (destination.getY() <= yCoord) {
            yCoord += dy;
            if (yCoord < 0) {
                yCoord = 0;
                dy = -dy;
            }
            if (yCoord + carLength >= dimension.height) {
                yCoord = dimension.height - carLength;
                dy = -dy;
            } 
        }
    }

    /**
     * Update the dx, dy values to change the direction the car will move in.
     *
     * @param dxTmp - The new x direction in pixels.
     * @param dyTmp - The new y direction in pixels.
     */
    public void updateDxDy(int dxTmp, int dyTmp) {
        dx = dxTmp;
        dy = dyTmp;
    }

    /**
     * Update the dimension object to calculate the new position of the car
     * model.
     *
     * @param dimension - The dimension of the containing panel.
     */
    public void updateDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Update the number plate details for this car instance.
     *
     * @param numberPlatTmp - The number plate associated to this vehicle.
     */
    public void assignNumberPlate(String numberPlatTmp) {
        numberPlate = numberPlatTmp;
    }

    /**
     * @return - Return the shape that represents the car.
     */
    public Rectangle2D getShape() {
        return new Rectangle2D.Double(xCoord, yCoord, 10, 10);
    }
    
    /**
     * @return the position of the car.
     */ 
    public Point2D getCarPosition() {
        return new Point2D.Double(xCoord, yCoord);
    }

    /**
     * @return - The number plate.
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * Move the car to a destination point.
     *
     * @param point - The point that the car needs to move to.
     */
    public void setDestinationPoint(Point2D point) {
        destination = point;
    }

    /**
     * Return the floor that the car is on.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Update the floor that the car is on.
     */
    public void updateFloor(int floorNo) {
        floor = floorNo;
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(xCoord, yCoord, carWidth, carLength);
    }
    
    /**
     * Update the state of the car.
     */ 
    public void updateCarState(CarModel.carState state) {
        this.state = state;
    }
    
    /**
     * @return the state of the car.
     */ 
    public CarModel.carState getCarState() {
        return state;
    }
}
