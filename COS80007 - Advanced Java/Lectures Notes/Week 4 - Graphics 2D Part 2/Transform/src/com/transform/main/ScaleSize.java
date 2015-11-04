package com.transform.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The purpose of this class is to demonstrate
 * how to correctly scale a shape when a window
 * is dragged in and out.
 * 
 * @author szeyick
 *
 */
public class ScaleSize {

	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new JFrame();
		
		JPanel panel = new MyPanel();
		
		frame.add(panel);
		frame.setTitle("Test Scale");
		frame.setSize(panel.getSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * A panel to be drawn onto.
	 * 
	 * @author szeyick
	 *
	 */
	@SuppressWarnings("serial")
	private static class MyPanel extends JPanel {
		
		/**
		 * The rectangle shape.
		 */
		private Rectangle2D rect;
		
		private int originalWidth = 100;
		
		private int originalHeight = 100;
		
		/**
		 * Constructor
		 */
		public MyPanel() {
			setBackground(Color.WHITE);
			setSize(originalWidth, originalHeight);
			rect = new Rectangle2D.Float(0, 0, 10, 10);
		}
		
		/**
		 * 
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			double newWidthScale = frame.getWidth() / originalWidth;
			double newHeightScale = frame.getHeight() / originalHeight;			
			double min = Math.min(newWidthScale, newHeightScale);
			
			// Never let scale less than 0
			if (min == 0) {
				min = 1; // Default			
			}
			
			// Position in the middle of the frame.
			double x = this.getWidth();
			double y = this.getHeight();
			
			System.out.println("X: " + frame.getWidth() + " Y: " + frame.getHeight());
			System.out.println("Scale : " + min);
			System.out.println("RectX: " + ((x / 2)) + " RectY: " + ((y / 2)));
			// Scale shape based on the size of the frame.
			Graphics2D g2 = (Graphics2D) g;
			
			g2.translate((x / 2), (y / 2));
			g2.scale(min, min);
			g2.draw(rect);
		}
	}

}
