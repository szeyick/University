/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import aps.car.CarModel;
import aps.elevator.Elevator;
import aps.elevator.ElevatorDoor;
import aps.shuttle.Shuttle;
import aps.shuttle.Trolley;
import aps.turntable.TurntableModel;
import control.APSControl;
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
public class GroundLevelFloorPanel extends JPanel {

    int r = 6;

    private TurntableModel turntableModel;

    /**
     * The distance things are from the wall;
     */
    private final int WALL_PADDING = 5;

    /**
     * The shape to be drawn, representing the ground floor.
     */
    private final Shape groundLevelFloorLayout;

    private CarModel carModel;

    private ElevatorDoor door;

    /**
     * Default constructor.
     *
     * @param turntable - The turntable component.
     */
    public GroundLevelFloorPanel(TurntableModel turntable) {
        setBackground(Color.WHITE);
        groundLevelFloorLayout = new GroundLevelFloorView().getGroundFloorPlan();
        setVisible(true);

        carModel = null;
        turntableModel = turntable;
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
        drawTurntable(g2);
        drawElevatorDoor(g2);
        drawElevator(g2);
        drawShuttle(g2);
        drawTrolley(g2);
    }

    /**
     * Draw the car onto the screen.
     */
    private void drawCar(Graphics2D g2) {
        if (carModel != null) {
            Rectangle2D car = carModel.getShape();
            AffineTransform oldTransform = g2.getTransform();

            Dimension d = getSize();
            Rectangle2D layout = groundLevelFloorLayout.getBounds2D();

            float xScale = (float) (layout.getWidth() / car.getWidth());
            float yScale = (float) (layout.getHeight() / car.getHeight());
            float theScale = Math.min(xScale, yScale);

            System.out.println("Car X: " + carModel.getCurrentXPosition() + " Y: " + carModel.getCurrentYPosition() + " Scale:" + theScale);

            float minX = (float) (car.getX());
            float minY = (float) (car.getY());

            g2.scale(theScale, theScale);
            g2.translate(minX, 0);
            g2.draw(car);
            g2.setTransform(oldTransform);
        }
    }

    private void drawTurntable(Graphics2D g2) {
        if (turntableModel != null) {
            AffineTransform oldTransform = g2.getTransform();
            Rectangle2D layout = groundLevelFloorLayout.getBounds2D();

            Rectangle2D turntableBounds = turntableModel.getTurntableBounds();

            // Calculate the scale.
            float xScale = (float) (layout.getWidth() / turntableBounds.getWidth());
            float yScale = (float) (layout.getHeight() / turntableBounds.getHeight());
            float theScale = Math.min(xScale, yScale);

            float minX = (float) (turntableBounds.getX());
            float minY = (float) (turntableBounds.getY());

            System.out.println("X, Y: " + minX + " " + minY + " Scale " + theScale);
            // g2.scale(theScale, theScale);
            g2.translate(minX, minY);

            g2.setStroke(new BasicStroke(1.0f / theScale));
            g2.setColor(getForeground());
            g2.drawOval((int) minX, (int) minY, 10, 10);
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the elevator doors opening and closing.
     */
    private void drawElevatorDoor(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();

        Elevator elevator = APSControl.getControl().getElevator();
        ElevatorDoor elevatorDoor = elevator.getDoor();

        int doorLength = elevatorDoor.getDoorLength();
        if (doorLength == 0) {
            doorLength = 1;
        }
        g2.setColor(getForeground());
        g2.fillRect(40, 140, (10 * doorLength), 10);

        g2.setTransform(oldTransform);
        // door.toggleDoorState();
        // The elevator door should only open - 
        //  - if the car has arrived on the turntable.
        //  - the door opens, and the shuttle comes to get the car.
    }

    /**
     * Draw the elevator if it is on the floor.
     */
    private void drawElevator(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == 0) {
            AffineTransform oldTransform = g2.getTransform();

            // Translate the elevator to the correct position before drawing.
            g2.translate(25, 100);
            g2.scale(10, 10);

            g2.setColor(getForeground());
            g2.fill(elevator.getBounds());
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the shuttle if we are on the correct floor.
     */
    private void drawShuttle(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == 0) {
            AffineTransform oldTransform = g2.getTransform();
            Shuttle shuttle = elevator.getShuttle();
            g2.translate(25, 100);
            g2.scale(10, 10);

            g2.setColor(Color.ORANGE);
            g2.fill(shuttle.getBounds());
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the trolley if we are on the correct floor.
     */
    private void drawTrolley(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == 0) {
            AffineTransform oldTransform = g2.getTransform();
            Trolley trolley = elevator.getShuttle().getTrolley();
            g2.translate(25, 100);
            g2.scale(10, 10);

            g2.setColor(Color.PINK);
            g2.fill(trolley.getBounds());
            g2.setTransform(oldTransform);
        }
    }

    /**
     * @param carModel - The car model to draw onto the panel.
     */
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public void draw() {
        repaint();
    }
}
