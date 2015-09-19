package aps.turntable;

import aps.config.Config;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The {@link TurntableModel}.
 * <p>
 * This class represents the model for the turntable for the Automatic Parking
 * Simulator. The turntable is located on the ground floor and will be responsible
 * for rotating the car to the correct angle.
 * <p>
 * @author szeyick
 * StudentID - 1763652.
 */
public class TurntableModel {
    
    /**
     * The diameter of the turntable. 
     */
    private final double turntableDiameter;
    
    /**
     * The X centre of the turntable.
     */
    private final double turntableCentreX;
    
    /**
     * The Y centre of the turntable.
     */
    private final double turntableCenterY;
    
    /**
     * The bounds of the turntable.
     */ 
    private final Rectangle2D bounds;
    
    /**
     * The coordinates of the turntable.
     */
    private final Point2D point;
    
    /**
     * The scale this shape is scaled y.
     */
    private int scale;
    
    /**
     * Constructor.
     * @param wallPadding - The padding from the walls.
     */
    public TurntableModel(int wallPadding) {
        turntableDiameter = Config.getConfig().TURNTABLE_DIAMETER;
        turntableCentreX = Config.getConfig().TURNTABLEX + wallPadding;
        turntableCenterY = (turntableDiameter + wallPadding + (20)) / 2;
        
        bounds = new Rectangle2D.Double(turntableCentreX, turntableCenterY, turntableDiameter, turntableDiameter);
        point = new Point2D.Double(turntableCentreX, turntableCenterY);
    }

    /**
     * @return the bounds of the turntable.
     */
    public Rectangle2D getTurntableBounds() {
        return bounds;
    }
    
    /**
     * @return the centre X coordinate of the turntable.
     */
    public double getTurntableCentreX() {
        return turntableCentreX;
    }
    
    /**
     * @return the centre Y coordinates of the turntable.
     */
    public double getTurntableCentreY() {
        return turntableCenterY;
    }
    
    /**
     * @return - The coordinates of the turntable.
     */
    public Point2D getPoint() {
        return point;
    }
    
    /**
     * Add the scale that the dimensions are scaled by
     */ 
    public void addScaleFactor(int scale) {
        this.scale = scale;
    }
    
    /**
     * @return the scale factor of this shape.
     */
    public int getScaleFactor() {
        return scale;
    }
}
