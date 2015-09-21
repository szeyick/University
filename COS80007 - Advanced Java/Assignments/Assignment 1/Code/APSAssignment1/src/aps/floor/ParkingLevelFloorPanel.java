package aps.floor;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.elevator.Elevator;
import aps.shuttle.Shuttle;
import aps.shuttle.Trolley;
import control.APSControl;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * The {@link ParkingLevelFloorPanel}.
 * <p>
 * This class represents a floor in which a car can be parked for the Automatic
 * Parking Simulator. It provides the component that will be drawn and displayed
 * on the screen.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class ParkingLevelFloorPanel extends JPanel {

    /**
     * The shape to be drawn, representing the parking level floor.
     */
    private final Shape parkingLevelFloorLayout;
    
    /**
     * An integer representing the current floor.
     */
    private final int FLOOR_NUMBER;

    /**
     * Constructor.
     * @param floorNum - The floor that this panel represents.
     */
    public ParkingLevelFloorPanel(int floorNum) {
        setBackground(Color.WHITE);
        FLOOR_NUMBER = floorNum;
        parkingLevelFloorLayout = new ParkingLevelFloorView().getFloorLayout();
        setVisible(true);
    }

    /**
     * Paint the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        drawFloorLayout(g2);
        drawParkedCars(g2);
        Elevator elevator = APSControl.getControl().getElevator();
        if (elevator.getCurrentFloor() == FLOOR_NUMBER) {
            drawElevator(g2);
            drawCar(g2);
            drawShuttle(g2);
            drawTrolley(g2);
        }
    }

    /**
     * Draw the floor layout for the parking level.
     */
    private void drawFloorLayout(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();

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
        g2.setColor(getForeground());

        g2.draw(parkingLevelFloorLayout);
        g2.setTransform(oldTransform);
    }

    /**
     * Draw the elevator if it has arrived on the floor.
     * <p>
     * @param g2 - The graphics 2D
     */
    private void drawElevator(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        AffineTransform oldTransform = g2.getTransform();

        // Translate the elevator to the correct position before drawing.
        g2.translate(0, 125);
        g2.scale(5, 5);

        g2.setColor(getForeground());
        g2.draw(elevator.getBounds());
        g2.setTransform(oldTransform);
    }

    /**
     * Draw the trolley on to the panel. 
     */
    private void drawTrolley(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        AffineTransform oldTransform = g2.getTransform();
        Trolley trolley = elevator.getShuttle().getTrolley();
        g2.translate(0, 125);
        g2.scale(5, 5);

        g2.setColor(Color.PINK);
        g2.fill(trolley.getBounds());
        g2.setTransform(oldTransform);
    }

    /**
     * Draw the car onto the screen.
     */
    private void drawCar(Graphics2D g2) {
        CarModel carModel = CarModelManager.getModelManager().getCurrentCarModel();
        if (carModel != null && carModel.getFloor() == FLOOR_NUMBER) {
            Rectangle2D car = carModel.getShape();
            AffineTransform oldTransform = g2.getTransform();
            g2.draw(car);
            g2.setTransform(oldTransform);
        }
    }

    /**
     * Draw the shuttle if we are on the correct floor.
     */
    private void drawShuttle(Graphics2D g2) {
        Elevator elevator = APSControl.getControl().getElevator();
        AffineTransform oldTransform = g2.getTransform();
        Shuttle shuttle = elevator.getShuttle();
        g2.translate(0, 125);
        g2.scale(5, 5);

        g2.setColor(Color.ORANGE);
        g2.draw(shuttle.getBounds());
        g2.setTransform(oldTransform);
    }

    /**
     * Draw the cars parked on this floor.
     */
    private void drawParkedCars(Graphics2D g2) {
        List<CarModel> carModelList = CarModelManager.getModelManager().getCarModelsForFloor(FLOOR_NUMBER);
        if (carModelList != null && !carModelList.isEmpty()) {
            for (CarModel parkedCar : carModelList) {
                // Do not draw the car that is currently moving.
                Rectangle2D car = parkedCar.getShape();
                AffineTransform oldTransform = g2.getTransform();

                g2.draw(car);
                g2.setTransform(oldTransform);
            }
        }
    }

    /**
     * Invoking of this method will trigger the
     * panel and all its contents to be redrawn.
     */ 
    public void draw() {
        repaint();
    }
}
