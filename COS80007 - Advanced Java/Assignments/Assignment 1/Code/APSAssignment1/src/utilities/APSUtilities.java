package utilities;

import aps.config.Config;

/**
 * The {@link APSUtilities}
 * <p>
 * This class is responsible for providing convenience methods for calculations
 * that are used throughout the Automatic Parking Simulator.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class APSUtilities {
    
    /**
     * Calculate the distance the first north bay is from the east wall of the
     * car park.
     * @return - The calculated distance.
     */
    public static double getNorthBayDistanceFromEastWall() {
        Config config = Config.getConfig();
        
        // The centre of the first north bay has to be at least this distance
        // away.
        double liftCentreX = config.LIFT_CENTRE_X;
        double bayWidth = config.BAY_WIDTH;
        
        // The wall will be 1.1m away from the centre since the total width is 2.2
        return liftCentreX - (bayWidth / 2);
    }
}
