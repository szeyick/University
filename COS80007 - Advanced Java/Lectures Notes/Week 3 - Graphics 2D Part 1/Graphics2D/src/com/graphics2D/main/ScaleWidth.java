package com.graphics2D.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The ScaleWidth.
 * <p>
 * This class is responsible for demonstrating
 * how to scale to the width of the panel that
 * it is drawn in whenever the paintComponent is called.
 * <p>
 * @author szeyick
 */
public class ScaleWidth {

	/**
	 * The frame that all components will be drawn on.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguements
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Scaling to Width");
		drawPanel();
		frame.setVisible(true);
	}
	
	/**
	 * Create a custom panel that will always scale its 
	 * internal shapes to the width of the frame.
	 */
	private static void drawPanel() {
		frame.add(new MyJPanel());
	}
	
	/**
	 * The MyJPanel
	 * <p>
	 * This panel is responsible for displaying a 
	 * square that will always rescale to the size of the
	 * the width of the display.
	 * <p>
	 * @author szeyick
	 */
	private static class MyJPanel extends JPanel {

		/**
		 * The rectangle the draw
		 */
		private static Rectangle2D rect;
		
		/**
		 * Constructor.
		 */
		public MyJPanel() {
			rect = new Rectangle2D.Float(0, 0, 10, 10);
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw the rectangle on the screen.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.draw(rect);
			
			// Draw the rectangle that will match the width of the
			// parent frame even if it is resized.
			Dimension panelDimension = getSize();
			int panelWidth = (int) panelDimension.getWidth();
			
			// The scale factor is defined by how many times the rectangle will
			// need to be expanded to fit.
			int scaleFactor = (int) panelWidth / (int) rect.getWidth();
			
			AffineTransform originalTransform = g2.getTransform();
			BasicStroke stroke = new BasicStroke(1 / scaleFactor);
			g2.setStroke(stroke);
			g2.scale(scaleFactor, 5);
			g2.draw(rect);
			g2.setTransform(originalTransform);
		}
	}

}
