package aps.floor;

import aps.config.Config;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * The {@link ParkingLevelFloorView}.
 * <p>
 * This class is responsible for generating the floor for a parking level
 * in the Automatic Parking Simulator.
 * <p>
 * This class will generate a floor plan based off a configuration and return
 * a shape object that will represent the floor that cars can park on.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class ParkingLevelFloorView {
    
    /***
     * The shape object representing the floor
     */ 
    private final GeneralPath parkingFloor;
    
    /***
     * Constructor. 
     */
    public ParkingLevelFloorView() {
        parkingFloor = new GeneralPath();
        initialiseFloorPlan();
    }
    
    /***
     * Draw out the floor plan onto the general path shape.
     */ 
    private void initialiseFloorPlan() {
        // Retrieve the bay width and length from the configuration.
        drawExternalWalls();
        drawAisles();
        drawNorthBay();
        drawSouthBay();
    }
    
    /***
     * Draw the external walls of the parking floor.
     */ 
    private void drawExternalWalls() {
        Config config = Config.getConfig();
        int numberOfNorthBays = config.NUMBER_OF_BAYS_NORTH;
        int numberOfSouthBays = config.NUMBER_OF_BAYS_SOUTH;
        int largestNumOfAisles = numberOfNorthBays > numberOfSouthBays ? numberOfNorthBays : numberOfSouthBays;
    
        // The total length should be dependant on whether there are south bays
        // but this should suffice.
        double totalLength = config.BAY_LENGTH * 3;
        // Has to take in either the lift centre X or south bay centre X to get distance away from wall the bays
        // start from.
        double southBayCentreX = config.SOUTH_BAY_CENTER_X;
        double liftCentreX = config.LIFT_CENTRE_X;
        double additionalWidth = southBayCentreX > liftCentreX ? southBayCentreX : liftCentreX;
        double totalWidth = (config.BAY_WIDTH * largestNumOfAisles) +  (additionalWidth - (config.BAY_WIDTH / 2));
        
        parkingFloor.moveTo(0, 0);
        parkingFloor.lineTo(totalWidth, 0);
        parkingFloor.moveTo(totalWidth, 0);
        parkingFloor.lineTo(totalWidth, totalLength);
        parkingFloor.moveTo(totalWidth, totalLength);
        parkingFloor.lineTo(0, totalLength);
        parkingFloor.moveTo(0, 0);
        parkingFloor.lineTo(0, totalLength);
    }
    
    /**
     * Draw the aisles 
     */
    private void drawAisles() {
        Config config = Config.getConfig();
        int numberOfNorthBays = config.NUMBER_OF_BAYS_NORTH;
        int numberOfSouthBays = config.NUMBER_OF_BAYS_SOUTH;
        int largestNumOfAisles = numberOfNorthBays > numberOfSouthBays ? numberOfNorthBays : numberOfSouthBays;
    
        double southBayCentreX = config.SOUTH_BAY_CENTER_X;
        double liftCentreX = config.LIFT_CENTRE_X;
        double additionalWidth = southBayCentreX > liftCentreX ? southBayCentreX : liftCentreX;
        double totalWidth = (config.BAY_WIDTH * largestNumOfAisles) +  (additionalWidth - (config.BAY_WIDTH / 2));
        
        // Draw the north aisle line.
        double aisleNorthY = config.BAY_LENGTH;
        double aisleNorthX = totalWidth;
    
        parkingFloor.moveTo(0, aisleNorthY);
        parkingFloor.lineTo(aisleNorthX, aisleNorthY);
    
        // Draw the south aisle line.
        double aisleSouthY = config.BAY_LENGTH * 2;
        double aisleSouthX = totalWidth;
        
        parkingFloor.moveTo(0, aisleSouthY);
        parkingFloor.lineTo(aisleSouthX, aisleSouthY);
    }
    
    /**
     * Draw the lines that represent an individual bay for the north bays. 
     */
    private void drawNorthBay() {
        Config config = Config.getConfig();
        
        // The centre of the first north bay has to be at least this distance
        // away.
        double liftCentreX = config.LIFT_CENTRE_X;
        int numberOfNorthBays = config.NUMBER_OF_BAYS_NORTH;
        double bayLength = config.BAY_LENGTH;
        double bayWidth = config.BAY_WIDTH;
        // The wall will be 1.1m away from the centre since the total width is 2.2
        double distanceFromEastWall =  liftCentreX - (bayWidth / 2);
        
        // Draw Initial line away from wall.
        parkingFloor.moveTo(distanceFromEastWall, 0);
        parkingFloor.lineTo(distanceFromEastWall, bayLength);
        
        // Draw the rest of the bay.
        // The last bay will be wider since it takes into account the largest
        // possible width as there may be more north bays than south and vice
        // versa, on top of the fact that the south wall has a different distnance.
        for (int bay = 1; bay <= numberOfNorthBays; bay++) {
            double xPos = (bayWidth * bay) + distanceFromEastWall;
            parkingFloor.moveTo(xPos, 0);
            parkingFloor.lineTo(xPos, bayLength);
        }
    }
    
    /**
     * Draw the lines that represent an individual bay for the south bays. 
     */
    private void drawSouthBay() {
        Config config = Config.getConfig();
        double centreXBay = config.SOUTH_BAY_CENTER_X;
        double bayXStart = centreXBay - (config.BAY_WIDTH / 2);
        
        
        // Draw the initial line away from the wall.
        parkingFloor.moveTo(bayXStart, config.BAY_LENGTH * 2);
        parkingFloor.lineTo(bayXStart, config.BAY_LENGTH * 3);
        
        int numberOfSouthBays = config.NUMBER_OF_BAYS_SOUTH;
        double bayWidth = config.BAY_WIDTH;
        
        // Draw the rest of the bay.
        // The last bay drawn is not used.
        for (int bay = 1; bay <= numberOfSouthBays; bay++) {
            double xPos = (bayWidth * bay) + bayXStart;
            parkingFloor.moveTo(xPos, config.BAY_LENGTH * 2);
            parkingFloor.lineTo(xPos, config.BAY_LENGTH * 3);
        }
    }
    
    /**
     * @return the floor layout.
     */ 
    public Shape getFloorLayout() {
       return parkingFloor; 
    }
}
