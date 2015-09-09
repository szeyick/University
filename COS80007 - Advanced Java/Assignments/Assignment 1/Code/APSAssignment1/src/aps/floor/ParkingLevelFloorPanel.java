/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * The {@link ParkingLevelFloorPanel}.
 * <p>
 * This class represents a floor in which a car can be parked for the
 * Automatic Parking Simulator. It provides the component that will
 * be drawn and displayed on the screen.
 * <p>
 * @author szeyick
 */
public class ParkingLevelFloorPanel extends JPanel {

    /**
     * The shape to be drawn, representing the parking level floor.
     */
    private final Shape parkingLevelFloorLayout;
    
    /**
     * Default constructor.
     */
    public ParkingLevelFloorPanel() {
        setBackground(Color.WHITE);
        parkingLevelFloorLayout = new ParkingLevelFloorView().getFloorLayout();
        setVisible(true);
    }

    /**
     * Paint the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        Dimension d = getSize();
        Rectangle2D rect = parkingLevelFloorLayout.getBounds2D();

        float xScale = (float) (d.getWidth() / rect.getWidth());
        float yScale = (float) (d.getHeight() / rect.getHeight());
        float theScale = Math.min(xScale, yScale);

        float minX = (float) (rect.getX());
        float minY = (float) (rect.getY());

        g2.translate(0.0f, (float) (d.getHeight()));
        g2.scale(theScale, -theScale);
        g2.translate(-minX, -minY);

        g2.setStroke(new BasicStroke(1.0f / theScale));
        g2.setColor(getForeground());  // probably black

        g2.draw(parkingLevelFloorLayout);
    }
}
