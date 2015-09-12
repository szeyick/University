package utilities;

/**
 * The {@link APSUtilities}
 * <p>
 * This class is responsible for providing convenience methods for calculations
 * that are used throughout the Automatic Parking Simulator.
 * <p>
 * @author szeyick
 */
public class APSUtilities {
    
    /**
     * Calculate the gradient
     * @param x1 - The x coordinate of the current point.
     * @param y1 - The y coordinate of the current point.
     * @param x2 - The x coordinate of the destination point.
     * @param y2 - The y coordinate of the destination point.
     * @return - The gradient represented as m.
     */
    public static double calculateGradient(double x1, double y1, double x2, double y2) {
        return (y2 - y1) / (x2 - x1);
    }
}
