package com.graphics2D.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The FontDemo.
 * <p>
 * This class is a demonstration of how to draw fonts or
 * text onto the display using the Graphics2D.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class FontDemo {

	/**
	 * The frame that components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Font Drawing");
		frame.add(new MyTextPanel());
		frame.setVisible(true);
	}
	
	/**
	 * The MyTextPanel.
	 * <p>
	 * This class is responsible for being the component that
	 * is drawn on.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyTextPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyTextPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw some text onto the screen and demonstrate
		 * anti-aliasing with RenderingHints.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			RenderingHints hints = new RenderingHints(null);
			
			Font font = new Font("Serif", Font.PLAIN, 96);
			hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// With anti-aliasing off
			g2.setFont(font);
			g2.drawString("Hello World", getWidth() / 6, getHeight() / 2);

			// With anti-aliasing on
			g2.setRenderingHints(hints);
			g2.drawString("Hello World", getWidth() / 6, getHeight() / 4);
		}
	}
}
