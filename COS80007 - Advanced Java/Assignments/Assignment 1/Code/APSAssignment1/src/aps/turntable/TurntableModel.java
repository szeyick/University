/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.turntable;

import aps.config.Config;
import java.awt.geom.Rectangle2D;

/**
 * The {@link TurntableModel}.
 * <p>
 * This class represents the model for the turntable for the Automatic Parking
 * Simulator. The turntable is located on the ground floor and will be responsible
 * for rotating the car to the correct angle.
 * <p>
 * @author szeyick
 */
public class TurntableModel {
    
    /**
     * The diameter of the turntable. 
     */
    private double turntableDiameter;
    
    /**
     * The X centre of the turntable.
     */
    private double turntableCentreX;
    
    /**
     * The Y centre of the turntable.
     */
    private double turntableCenterY;
    
    private Rectangle2D bounds;
    
    /**
     * Constructor.
     * @param config - The offline configuration.
     * @param wallPadding - The padding from the walls.
     */
    public TurntableModel(int wallPadding) {
        turntableDiameter = Config.getConfig().TURNTABLE_DIAMETER;
        turntableCentreX = Config.getConfig().TURNTABLEX + wallPadding;
        turntableCenterY = (turntableDiameter + wallPadding) / 2;
        
        bounds = new Rectangle2D.Double(turntableCentreX, turntableCenterY, turntableDiameter / 2, turntableDiameter / 2);
    }

    /**
     * @return the bounds of the turntable.
     */
    public Rectangle2D getTurntableBounds() {
        return bounds;
    }
    
    public double getTurntableCentreX() {
        return turntableCentreX;
    }
    
    public double getTurntableCentreY() {
        return turntableCenterY;
    }
}
