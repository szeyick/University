package com.transform.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Test that a car object can be moved horizontally
 * to a defined centre point.
 * <p>
 * The idea here for the parking simulator would be to 
 * make sure the centre of the car lines up with the centre
 * X position of the car spot it is supposed to park in. The
 * rectangle2D object provides a centre point.
 * <p>
 * Furthermore, the car will incrementally move per time period
 * if in that time period, the movement will make it go over
 * the centre point target, it should just be automatically set
 * to the centre point of the parking spot.
 * <p>
 * Also, when you update the rectangle position, it will set the
 * X,Y as the top left corner, meaning the X, Y position will need
 * to be subtracted by half its width, to ensure the centre point
 * of the new rectangle matches the centre point of the parking spot.
 * @author szeyick
 */
public class ParkingTest {
	
	/**
	 * The car size.
	 */
	private static Rectangle2D car;

	private static int scaleY;
	
	private static int scaleX;
	
	private static MyPanel drawPanel;
	
	private static JFrame frame;
	
	/**
	 * The rectangle shape.
	 */
	private static Rectangle2D rect;
	
	/**
	 * Program main.
	 * @param args
	 */
	public static void main(String[] args) {
		frame  = new JFrame();
		frame.setSize(400, 400);
		frame.setTitle("Test Translation");
		
		JPanel buttonPanel = new JPanel();
		drawPanel = new MyPanel();
		
		addButtons(buttonPanel);
		frame.add(drawPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Add buttons to the panel.
	 * @param buttonPanel
	 */
	private static void addButtons(JPanel buttonPanel) {
		JButton incrementButton = new JButton("Increment Translate");
		incrementButton.addActionListener(new IncrementActionListener());
		buttonPanel.add(incrementButton);
		
		JButton decrementButton = new JButton("Decrement Translate");
		decrementButton.addActionListener(new DecrementActionListener());
		buttonPanel.add(decrementButton);
	}
	
	public static void fireChanges() {
		drawPanel.draw();
	}
	
	/**
	 * The panel to draw onto.
	 * <p>
	 * @author szeyick
	 */
	private static class MyPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyPanel() {
			setBackground(Color.WHITE);
			rect = new Rectangle2D.Float(frame.getWidth() / 2, 20, 30, 30);
			car = new Rectangle2D.Float(0, 60, 10, 10);
			
			scaleX = 0;
			scaleY = 0;
		}
		
		/**
		 * Draw something onto the screen.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			AffineTransform transform = g2.getTransform();
			
			g2.setColor(Color.BLUE);
			g2.draw(rect);
				        
	        g2.setTransform(transform);
			
	        g2.setColor(Color.RED);
	        g2.draw(car);
	        
			System.out.println("X: " + scaleX + " Y: " + scaleY);
			System.out.println("RectX: " + rect.getX() + "RectY: " + rect.getY());
		}
		
		/**
		 * Repaint the component.
		 */
		public void draw() {
			repaint();
		}
	}
	
	private static class IncrementActionListener implements ActionListener {

		/**
		 * Increment the translation.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			scaleX += 1;
			double x = car.getCenterX();
			double spotCentreX = rect.getCenterX();
			
			if ((x + scaleX) >= spotCentreX) {
				x = spotCentreX;
			}
			else {
				x += scaleX;
			}
			car.setRect(x - 5, car.getY(), 10, 10);
			fireChanges();
		}
	}
	
	private static class DecrementActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				scaleX = 0;
				car.setRect(scaleX, car.getY(), 10, 10);
				fireChanges();

		}
	}

}
