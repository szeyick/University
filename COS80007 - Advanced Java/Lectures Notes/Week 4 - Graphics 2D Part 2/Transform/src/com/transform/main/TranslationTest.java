package com.transform.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Testing how the translation works.
 * <p>
 * Translation will shift the current start point
 * of drawing (20, 20) by a factor of units in the x
 * direction and the y direction.
 * <p>
 * It is useful for repositioning things on screen to make
 * it relative to the display rather than changing its
 * actual x,y position values.
 * <p>
 * In this example, we start at (x,y) (20, 20) and translate
 * it by a 10 units on the x axis and y axis, meaning that the
 * subsequent draw is 20 + 10 away from the origin point, and 
 * so forth.
 * <p>
 * @author szeyick
 */
public class TranslationTest {

	private static int translateX;
	
	private static int translateY;
	
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
			translateX = 0;
			translateY = 0;
		}
		
		/**
		 * Draw something onto the screen.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLUE);
			g2.draw(rect);
			
			// Translate.
			g2.setColor(Color.GREEN);
			g2.translate(translateX, translateY);
			g2.draw(rect);
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
			translateX += 10;
			translateY += 10;
			fireChanges();
		}
	}
	
	private static class DecrementActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (translateX != 0) {
				translateX -= 10;
				translateY -= 10;
				fireChanges();
			}
		}
	}
}
