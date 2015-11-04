package com.graphics2D.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The ImageDemo.
 * <p>
 * This class provides a demonstration of how the Image object
 * works, along with the BufferedImage. The buffered image can
 * be used to draw graphics in memory, using the BufferedImage as
 * a buffer.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class ImageDemo {

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
		frame = new MyJFrame("Image Demo");
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

			// Very inefficient to create a buffered image each redraw
			BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			Random random = new Random();
			int x = 0;
			int y = 0;
			for (int i = 0; i < 20; i++) {
				x = random.nextInt(getWidth());
				y = random.nextInt(getHeight());
			}
			graphics.drawOval(x, y, 10, 10);
			g2.drawImage(bufferedImage, 0, 0, null);
			graphics.dispose();
		}
	}
}

