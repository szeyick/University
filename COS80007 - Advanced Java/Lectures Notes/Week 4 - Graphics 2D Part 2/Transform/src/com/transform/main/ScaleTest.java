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
 * Testing how scaling works.
 * <p>
 * For scaling, the factor that it scales all the values
 * is defined by the scale(x,y). It multiplies the current
 * x value by the scaleX, and the y value by the scaleY.
 * <p>
 * For the height and width it does the same thing, where
 * the height is multiplied by the scaleY, and the width multiplied
 * by the scaleX.
 * <p>
 * @author szeyick
 */
public class ScaleTest {

	private static int scaleX;
	
	private static int scaleY;
	
	private static MyPanel drawPanel;
	
	/**
	 * Program main.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JPanel buttonPanel = new JPanel();
		drawPanel = new MyPanel();
		
		addButtons(buttonPanel);
		frame.add(drawPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		
		frame.setSize(400, 400);
		frame.setTitle("Test Translation");
		
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
		 * The rectangle shape.
		 */
		private Rectangle2D rect;
		
		/**
		 * Constructor.
		 */
		public MyPanel() {
			setBackground(Color.WHITE);
			rect = new Rectangle2D.Float(20, 20, 10, 10);
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
			
			// Scale.
			g2.setColor(Color.GREEN);
			g2.scale(scaleX, scaleY);
			g2.draw(rect);
			
			g2.setTransform(transform);
			
			g2.setColor(Color.RED);
			g2.scale(1,  1);
			Rectangle2D newRect = new Rectangle2D.Float(20 * scaleX, 20 * scaleY, 10, 10);
			g2.draw(newRect);
			
			System.out.println("X: " + scaleX + " Y: " + scaleY);
			System.out.println("RectX: " + rect.getX() + "RectY: " + rect.getY());
		}
		
		/**
		 * Repain the component.
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
			scaleY += 1;
			fireChanges();
		}
	}
	
	private static class DecrementActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (scaleX != 0) {
				scaleX -= 1;
				scaleY -= 1;
				fireChanges();
			}
		}
	}

}
