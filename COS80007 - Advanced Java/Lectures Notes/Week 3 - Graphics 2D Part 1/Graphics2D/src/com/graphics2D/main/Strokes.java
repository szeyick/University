package com.graphics2D.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The Strokes.
 * <p>
 * This class provides a demonstration of how to 
 * use Strokes. A stroke is essentially a pen that
 * allows you to change the look of the line of a 
 * shape, if you want to add thickness, dashed lines
 * and different ways to join a line.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class Strokes {

	/**
	 * The frame to draw onto.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Strokes");
		frame.add(new MyPanel());
		frame.setVisible(true);
	}
	
	/**
	 * The MyPanel.
	 * <p>
	 * This class is responsible for holding being
	 * the panel in which the drawing will be performed.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	@SuppressWarnings("serial")
	private static class MyPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * The method that is called whenever paint() is
		 * called. It triggers a re-draw of the panel.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Dimension dimension = frame.getSize();
			int x = (int) dimension.getWidth() / 2;
			int y = (int) dimension.getHeight() / 2;

			System.out.println(x + " " + y);
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform transform = g2.getTransform();
			
			// Draw the original rectangle with stroke width 1.
			g2.drawRect(x, y, 100, 20);

			// Draw the rectangle again with different stroke width.
			BasicStroke stroke = new BasicStroke(5);
			g2.setStroke(stroke);
			g2.drawRect(x, y + 30, 100, 20);
			
			// Set the transform back to its original state.
			g2.setTransform(transform);
		}
	}
}
