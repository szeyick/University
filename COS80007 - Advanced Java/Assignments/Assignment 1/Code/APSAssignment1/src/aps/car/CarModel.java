package aps.car;

import aps.config.Config;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The {@link CarModel}.
 * <p>
 This class represents the data structure for holding information about
 a single carShape instance.
 <p>
 * @author szeyick 
 * StudentID - 1763652
 */
public class CarModel {

    /**
     * The floor the carShape is on.
     */
    private int floor;

    /**
     * The number plate for the carShape.
     */
    private String numberPlate;

    /**
     * The current x position of the carShape.
     */
    private double xCoord;

    /**
     * The current y position of the carShape.
     */
    private double yCoord;

    /**
     * The x direction in which to move the carShape.
     */
    private int dx;

    /**
     * The y direction in which to move the carShape.
     */
    private int dy;

    /**
     * The destination point to move the car to.
     */
    private Point2D destination;

    /**
     * The dimension of the panel that this model is contained in.
     */
    private Dimension dimension;

    /**
     * The width of the car.
     */ 
    private final float carWidth;

    /**
     * The length of the car.
     */ 
    private final float carLength;

    /**
     * The current state of the car.
     */ 
    private CarState state;
    
    /**
     * Constructor.
     */
    public CarModel() {
        carWidth = Config.getConfig().CAR_WIDTH;
        carLength = Config.getConfig().CAR_LENGTH;
        
        // Default parameters of the car.
        xCoord = 150;
        yCoord = 150;
        floor = 0;
        state = CarState.IDLE;
    }

    /**
     * @return the current x position of the carShape.
     */
    public double getCurrentXPosition() {
        return xCoord;
    }

    /**
     * @return the current y position of the carShape.
     */
    public double getCurrentYPosition() {
        return yCoord;
    }

    /**
     * Update the coordinates. This will move the target from x,y to x + dx, y +
     * dy
     */
    public void updateCoordinates() {
        // Move in the x direction.
        System.out.println("Destination X,Y : " + destination.getX() + " " + destination.getY());
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
     * Update the dx, dy values to change the direction the carShape will move in.
     *
     * @param dxTmp - The new x direction in pixels.
     * @param dyTmp - The new y direction in pixels.
     */
    public void updateDxDy(int dxTmp, int dyTmp) {
        dx = dxTmp;
        dy = dyTmp;
    }

    /**
     * Update the dimension object to calculate the new position of the carShape
     * model.
     * <p>
     * @param dimension - The dimension of the containing panel.
     */
    public void updateDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Update the number plate details for this carShape instance.
     * <p>
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
     * Set a destination point to move the car to.
     * <p>
     * @param point - The point that the car needs to move to.
     */
    public void setDestinationPoint(Point2D point) {
        destination = point;
    }

    /**
     * @return the floor that the car is on.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Update the floor that the carShape is on.
     * @param floorNo - the floor the car is now on.
     */
    public void updateFloor(int floorNo) {
        floor = floorNo;
    }
    
    /**
     * @return the bounds of the car to draw.
     */ 
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(xCoord, yCoord, carWidth, carLength);
    }
    
    /**
     * Update the state of the car.
     * <p>
     * @param state - The state of the car.
     */ 
    public void updateCarState(CarState state) {
        this.state = state;
    }
    
    /**
     * @return the state of the carShape.
     */ 
    public CarState getCarState() {
        return state;
    }
}
