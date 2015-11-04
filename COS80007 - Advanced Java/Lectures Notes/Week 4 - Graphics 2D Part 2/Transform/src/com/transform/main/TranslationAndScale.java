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
 * This tests using the combination of translation and scale.
 * <p>
 * Depending on the order in which you use then, the result can
 * differ.
 * <p>
 * For example, if you translate, then scale, the scaling will be
 * applied to the x,y values after it has been translated.
 * 
 * If we originally had x, y = 10,10
 * and we translate 10 units in the x and y then scale by 2
 * 
 * It will first add 10 to x, y = 20, 20, then scale it by 2
 * resulting in a new x,y = 40, 40
 * <p>
 * Whereas if you scaled, then translated -
 * Originally x,y = 10, 10
 * Scale by 2 = 20, 20
 * Translate by 10 = 30, 30
 * <p>
 * So depending on the order in which they are called, it will
 * result in a different affine transformation. It seems that
 * the step of scaling, then translating is the correct one to
 * do.
 * <p>
 * @author szeyick
 */
public class TranslationAndScale {

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
			
			// Translate, scale and draw.
			g2.setColor(Color.GREEN);
			g2.scale(2, 2);
			g2.translate(10, 10);
			g2.draw(rect);
			
			g2.setTransform(transform);
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle2D.Float(60, 60, 10, 10));
			
			// Scale, translate and draw.
			g2.setColor(Color.RED);
			g2.translate(10, 10);
			g2.scale(2, 2);
			g2.draw(rect);

			g2.setTransform(transform);
			g2.setColor(Color.YELLOW);
			g2.draw(new Rectangle2D.Float(50, 50, 10, 10));
			
			
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
