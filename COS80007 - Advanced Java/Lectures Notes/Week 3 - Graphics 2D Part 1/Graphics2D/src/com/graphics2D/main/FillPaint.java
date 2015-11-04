package com.graphics2D.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.graphics2D.frame.MyJFrame;

/**
 * The FillPaint.
 * <p>
 * This class provides a basic demonstration of how
 * the Paint object works in Graphics2D. It allows for
 * a object that is being drawn onto the display to be filled
 * either by a colour, gradient or pattern.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class FillPaint {

	/**
	 * The frame in which components will be added
	 */
	private static JFrame frame;
	
	/**
	 * The colour that this will fill the shape with.
	 */
	private static Paint paint;
	
	/**
	 * Paint panel to hold the components that will be
	 * filled different depending on the selected paint.
	 */
	private static MyPaintPanel myPaintPanel;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Paint Filling");
		paint = Color.BLUE; // Default paint colour.
		myPaintPanel = new MyPaintPanel();
		frame.add(myPaintPanel, BorderLayout.CENTER);
		createButtonGroups();
		frame.setVisible(true);
	}
	
	/**
	 * Method to create button groups to change the
	 * paint of a object.
	 */
	private static void createButtonGroups() {
		JPanel panel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		MyActionListener listener = new MyActionListener();
		
		JRadioButton radioButton1 = new JRadioButton("Colour");
		radioButton1.addActionListener(listener);
		radioButton1.setSelected(true);
		JRadioButton radioButton2 = new JRadioButton("Gradient");
		radioButton2.addActionListener(listener);
		JRadioButton radioButton3 = new JRadioButton("None");
		radioButton3.addActionListener(listener);

		// Add the buttons to the group so only one can be selected.
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		
		// Add the buttons to the panel.
		panel.add(radioButton1);
		panel.add(radioButton2);
		panel.add(radioButton3);
		
		frame.add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Update the paint colour/style, depending on which radio button
	 * has been selected.
	 * @param event - The event originating from the button.
	 */
	private static void updatePaint(ActionEvent event) {
		String selectedButtonName = event.getActionCommand();
		switch (selectedButtonName) {
		case "Colour":
			paint = Color.BLUE;
			break;
		case "Gradient":
			// Gradient paint transitions from one colour to another.
			Point point1 = new Point(0, 0);
			Point point2 = new Point(frame.getWidth(), frame.getHeight());
			paint = new GradientPaint(point1, Color.BLUE, point2, Color.YELLOW);
			break;
		default:
			paint = null; // Will default it to black.
			break;
		} 
		myPaintPanel.repaint();
	}
	
	/**
	 * The MyPaintPanel.
	 * <p>
	 * This panel is responsible for demonstrating how the Paint()
	 * object is used to provide different looks for a shape that
	 * is being drawn on the display.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 *
	 */
	@SuppressWarnings("serial")
	private static class MyPaintPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyPaintPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw shapes and decorate them with the Paint
		 * object.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
			
			// Set the paint colour to fill the object in with.
			g2.setPaint(paint);
			g2.fill(circle);
		}
	}
	
	/**
	 * The MyActionListener.
	 * <p>
	 * This class is responsible for responding to actions that have been
	 * invoked by selecting the radio buttons on the panel. Depending on
	 * the button that is selected, it will trigger an update as to how
	 * the components will be repainted on the display.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyActionListener implements ActionListener {

		/**
		 * The method that is called when a radio button is selected.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			updatePaint(e);			
		}
		
	}

}
