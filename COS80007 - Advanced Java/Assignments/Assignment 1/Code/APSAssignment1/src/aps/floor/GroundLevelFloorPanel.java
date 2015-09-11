/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import aps.car.CarModel;
import aps.timer.IAPSTimerListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * The {@link GroundLevelFloorPanel}.
 * <p>
 * This class represents the ground floor in which a car enter/exit the
 * Automatic Parking Simulator. It provides the component that will be drawn and
 * displayed on the screen.
 * <p>
 * @author szeyick
 */
public class GroundLevelFloorPanel extends JPanel implements IAPSTimerListener {

    int x1 = 0;
    int y1 = 0;
    int x2 = 200;
    int y2 = 250;
    //
    int x = x2;
    int y;
    int r = 6;

    int m = (y2 - y1) / (x2 - x1);

    /**
     * The shape to be drawn, representing the ground floor.
     */
    private final Shape groundLevelFloorLayout;

    /**
     * Default constructor.
     */
    public GroundLevelFloorPanel() {
        setBackground(Color.WHITE);
        groundLevelFloorLayout = new GroundLevelFloorView().getGroundFloorPlan();
        setVisible(true);
    }

    /**
     * Paint the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        AffineTransform oldTransform = g2.getTransform();

        Dimension d = getSize();
        Rectangle2D rect = groundLevelFloorLayout.getBounds2D();

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

        g2.draw(groundLevelFloorLayout);
        g2.setTransform(oldTransform);
        drawCar(g2);
    }

    /**
     * Draw the car onto the screen.
     */
    private void drawCar(Graphics2D g2) {
        CarModel model = new CarModel();

        Rectangle2D car = model.getShape();

        Dimension d = getSize();
        Rectangle2D layout = groundLevelFloorLayout.getBounds2D();

        float xScale = (float) (layout.getWidth() / car.getWidth());
        float yScale = (float) (layout.getHeight() / car.getHeight());
        float theScale = Math.min(xScale, yScale);

        System.out.println(xScale);

        // The X,Y coorindate will be in the car model.
        // This view will listen to changes in the car model
        // To update the draw.
        if (x <= x1) {
            System.out.println("Stop");
        } else {
            x--;
            y = m * x;
            System.out.println("x" + x + "  y" + y);
	    g2.drawOval(x, y, r, r);
            g2.drawRect(x, y, 10, 10);
        }

        g2.translate(10 * x, 10 * y); // The x and y (This will need to be updated on each update)
        g2.scale(theScale + 4, theScale + 4);
        g2.setColor(Color.RED);
        g2.draw(car);
    }

    @Override
    public void update(long dt) {
         repaint();
    }
}
