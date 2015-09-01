package aps.car;

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
     * Constructor.
     */ 
    public CarModel() {
        // TODO: Initialise the object.
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
        xCoord = xCoordTmp;
        yCoord = yCoordTmp;
    }
    
    /**
     * Update the number plate details for this car instance.
     * @param numberPlatTmp - The number plate associated to
     * this vehicle.
     */
    public void assignNumberPlate(String numberPlatTmp) {
      numberPlate = numberPlatTmp;  
    }
}