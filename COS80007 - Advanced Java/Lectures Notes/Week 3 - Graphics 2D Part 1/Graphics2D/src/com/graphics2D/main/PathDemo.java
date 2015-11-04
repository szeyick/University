package com.graphics2D.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The PathDemo
 * <p>
 * This class is a demo of the GeneralPath, where we
 * can draw a path filled with lines and also add shapes
 * to it.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class PathDemo {

	/**
	 * The frame containing all the components that
	 * will be displayed.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D General Path");
		frame.add(new MyGeneralPathPanel());
		frame.setVisible(true);
	}
	
	/**
	 * The MyGeneralPathPanel.
	 * <p>
	 * This class is responsible for holding the GeneralPath
	 * shape that will be drawn onto the display.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	@SuppressWarnings("serial")
	private static class MyGeneralPathPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyGeneralPathPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw a general path object onto the panel.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			RenderingHints hints = new RenderingHints(null);
			hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			GeneralPath path = new GeneralPath();
			
			// A general path is a shape made up of a bunch of line segments
			// with other shapes that can be added in.
			path.moveTo(getWidth() / 2,  0);
			path.lineTo(getWidth() / 3, getHeight() / 3);
			path.moveTo(getWidth() / 3, getHeight() / 3);
			path.lineTo(0, getHeight() / 3);
			path.moveTo(0, getHeight() / 3);
			path.lineTo(getWidth() / 3, getHeight() / 2);
			path.moveTo(getWidth() / 3, getHeight() / 2);
			path.lineTo(0, getHeight());
			path.moveTo(0, getHeight());
			path.lineTo(getWidth() / 2, getHeight() / 2);
			path.lineTo(getWidth() / 2,  0);
			
			// Add another shape to the path. 
			Rectangle2D rect = new Rectangle2D.Double((getWidth() / 2) + 20, (getHeight() / 2) + 20, 10, 10);
			path.append(rect, false); // If we add true, it will connect a line to the rectangle
			
			g2.setRenderingHints(hints);
			g2.draw(path);
		}
	}

}
