package aps.floor;

import aps.car.CarModel;
import aps.elevator.Elevator;
import aps.elevator.ElevatorDoor;
import aps.events.EventType;
import aps.events.ParkingEvent;
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
 * @author szeyick StudentID - 1763652.
 */
public class GroundLevelFloorPanel extends JPanel {

    /**
     * The turntable model.
     */
    private final TurntableModel turntableModel;

    /**
     * The shape to be drawn, representing the ground floor.
     */
    private final Shape groundLevelFloorLayout;

    /**
     * The car model being drawn.
     */
    private CarModel carModel;

    /**
     * Constructor.
     * <p>
     * @param turntable - The turntable component.
     */
    public GroundLevelFloorPanel(TurntableModel turntable) {
        setBackground(Color.WHITE);
        groundLevelFloorLayout = new GroundLevelFloorView().getGroundFloorPlan();
        carModel = null;
        turntableModel = turntable;
        setVisible(true);
    }

    /**
     * Paint the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw all the components onto the panel.
        drawLayout(g2);
        drawCar(g2);
        drawTurntable(g2);
        drawElevatorDoor(g2);
        drawElevator(g2);
        drawShuttle(g2);
        drawTrolley(g2);
        drawUserStation(g2);

    }

    /**
     * Draw the layout onto the panel
     * <p>
     * @param g2 - The graphics drawer.
     */
    private void drawLayout(Graphics2D g2) {
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
        g2.setColor(getForeground());

        g2.draw(groundLevelFloorLayout);
        g2.setTransform(oldTransform);
    }

    /**
     * Draw the car onto the screen.
     * <p>
     * @param g2 - The graphics drawer.
     */
    private void drawCar(Graphics2D g2) {
        if (carModel != null && carModel.getFloor() == 0) {
            Rectangle2D car = carModel.getShape();
            AffineTransform oldTransform = g2.getTransform(); 
            Rectangle2D turntableBounds = turntableModel.getTurntableBounds();

            int scaleFactor = turntableModel.getScaleFactor() - 1;
            g2.translate(turntableBounds.getX() * scaleFactor, turntableBounds.getY() * scaleFactor);
            g2.draw(car);
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the turntable onto the panel.
     * <p>
     * @param g2 - The graphics drawer.
     */
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

            g2.drawOval(67, 89, (int) turntableBounds.getWidth(), (int) turntableBounds.getHeight());

            turntableModel.addScaleFactor(5);
            g2.scale(5, 5);

            g2.setStroke(new BasicStroke(1.0f / theScale));
            g2.setColor(getForeground());
            g2.drawOval((int) minX, (int) minY, (int) turntableBounds.getWidth(), (int) turntableBounds.getHeight());

            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the elevator doors opening and closing.
     * <p>
     * @param g2 - The graphics drawer.
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
    }

    /**
     * Draw the elevator if it is on the floor.
     * <p>
     * @param g2 - The graphics drawer.
     */
    private void drawElevator(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == 0) {
            AffineTransform oldTransform = g2.getTransform();

            g2.translate(25, 100);
            g2.scale(10, 10);

            g2.setStroke(new BasicStroke(1.0f / 10));
            g2.setColor(getForeground());
            g2.draw(elevator.getBounds());
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the shuttle if we are on the correct floor.
     * <p>
     * @param g2 - The graphics drawer.
     */
    private void drawShuttle(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == 0) {
            AffineTransform oldTransform = g2.getTransform();
            Shuttle shuttle = elevator.getShuttle();
            g2.translate(25, 100);
            g2.scale(10, 10);

            g2.setColor(Color.ORANGE);
            g2.draw(shuttle.getBounds());
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the trolley if we are on the correct floor.
     * <p>
     * @param g2 - The graphics drawer.
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
     * Draw the user station onto the display and display in text the current
     * event operation.
     * @param g2 - The graphics drawer.
     */ 
    private void drawUserStation(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.translate(25, 100);
        g2.setColor(Color.BLACK);

        Rectangle2D rect = new Rectangle2D.Float(70, 90, 15, 15);
        g2.draw(rect);
        String drawString;
        ParkingEvent event = APSControl.getControl().getCurrentParkingEvent();
        if (event != null) {
            if (EventType.ARRIVAL.equals(event.getEventType())) {
                drawString = "P";
            } else {
                drawString = "C";
            }
            g2.drawString(drawString, 75, 100);
        }
        g2.setTransform(oldTransform);
    }

    /**
     * @param carModel - The car model to draw onto the panel.
     */
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    /**
     * Draw method to repaint the component.
     */
    public void draw() {
        repaint();
    }
}
