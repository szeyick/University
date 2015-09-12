package aps.car;

import java.awt.geom.Rectangle2D;
import utilities.APSUtilities;

/**
 * The {@link CarModel}.
 * <p>
 * This class represents the data structure for holding details about a car
 * instance.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class CarModel {
    
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
     * The shape that represents the car.
     */
    private Rectangle2D car;
    
    /**
     * Constructor.
     */ 
    public CarModel() {
        car = new Rectangle2D.Double(0, 0, 2, 5);
        xCoord = 100;
        yCoord = 200;
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
     * Update the x,y coordinates of the car within the car park.
     * @param xCoordTmp - the new x coordinate.
     * @param yCoordTmp - the new y coordinate.
     */
    public void updateXYCoordinates(double xCoordTmp, double yCoordTmp) {
        xCoord--;
        double m = APSUtilities.calculateGradient(xCoordTmp, yCoordTmp, xCoord, yCoord);
        yCoord = (int) m * xCoord;
    }
    
    /**
     * Update the number plate details for this car instance.
     * @param numberPlatTmp - The number plate associated to
     * this vehicle.
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
}
