package com.graphics2D.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The AreasDemo.
 * <p>
 * This class provides a demonstration of how we can
 * combine different shapes together to create an
 * Area object. With Area's you can add, subtract
 * or intersect different shapes together to create
 * looking shapes.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class AreasDemo {

	/**
	 * The frame that will have components added
	 * to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Areas");
		frame.add(new AreaPanel());
		frame.setVisible(true);
	}
	
	/**
	 * The AreaPanel.
	 * <p>
	 * This class is responsible for providing a panel that
	 * can be drawn on. In this example, we will create an 
	 * Area object that is essentially just a big shape that
	 * has been drawn by combining a bunch of other shapes together
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	@SuppressWarnings("serial")
	private static class AreaPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public AreaPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw an Area shape onto the panel.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			Area area = new Area();
			
			// Add a rectangle and circle together.
			Rectangle2D rect = new Rectangle2D.Double((frame.getWidth() / 4), 0, frame.getWidth() / 2, frame.getHeight() / 2);
			Ellipse2D circle = new Ellipse2D.Double((frame.getWidth() / 4), getHeight() / 2, getWidth() / 2, getHeight() / 2);
			
			Area rectArea = new Area(rect);
			Area circArea = new Area(circle);
			
			area.add(rectArea);
			area.add(circArea);
			
			g2.fill(area);
		}
	}

}
