package com.graphics2D.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.graphics2D.frame.MyJFrame;

/**
 * The ClipDemo.
 * <p>
 * This class is a demonstration of how to use the Graphics2D clipping.
 * If we set a clipping area, it means that only the components that are
 * within the clipping area will be drawn, everything outside of it will
 * not be drawn.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class ClipDemo {

	/**
	 * The frame containing all components.
	 */
	private static JFrame frame;

	/**
	 * The panel the graphics will be drawn onto.
	 */
	private static JPanel clippingPanel;

	/**
	 * Is the drawing area to be clipped.
	 */
	private static boolean clipDrawingArea;

	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Clipping Demo");
		clipDrawingArea = false;
		clippingPanel = new MyClippingPanel();
		frame.add(clippingPanel, BorderLayout.CENTER);
		createClippingRadio();
		frame.setVisible(true);
	}

	/**
	 * Create a panel with radio buttons to manage the
	 * view of clipping and non-clipping areas
	 */
	private static void createClippingRadio() {
		JPanel radioButtonPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		MyActionEventListener listener = new MyActionEventListener();
		JRadioButton button1 = new JRadioButton("No Clipping");
		button1.addActionListener(listener);
		JRadioButton button2 = new JRadioButton("Clip");
		button2.addActionListener(listener);

		button1.setSelected(true);
		buttonGroup.add(button1);
		buttonGroup.add(button2);

		radioButtonPanel.add(button1);
		radioButtonPanel.add(button2);

		frame.add(radioButtonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Update the radio button state depending on which is selected.
	 * @param e - The action event.
	 */
	private static void updateRadioButtonState(ActionEvent e) {
		String selectedButton = e.getActionCommand();
		switch (selectedButton) {
		case "No Clipping":
			clipDrawingArea = false;
			break;
		default:
			clipDrawingArea = true;
			break;
		}
		clippingPanel.repaint();
	}

	/**
	 * The MyClippingPanel.
	 * <p>
	 * This class is responsible for providing the surface for drawing.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyClippingPanel extends JPanel {

		/**
		 * Constructor.
		 */
		public MyClippingPanel() {
			setBackground(Color.WHITE);
		}

		/**
		 * Draw some shapes in and outside of the clipping
		 * area.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D circle = new Ellipse2D.Double(getWidth() / 3, getHeight() / 4, 200, 200);

			if (clipDrawingArea) {
				Rectangle2D clipRect = new Rectangle2D.Double(getWidth() / 3, getHeight() / 4, 200, 200);
				g2.setClip(clipRect);
				g2.draw(clipRect);
			}
			g2.draw(circle);
			g2.drawString("No Clipping", getWidth() / 3, getHeight() / 5);
		}
	}

	/**
	 * The MyActionEventListener.
	 * <p>
	 * This class is responsible for listening to user triggered events
	 * on the radio buttons.
	 * <p>
	 * @author szeyick
	 */
	private static class MyActionEventListener implements ActionListener {

		/**
		 * Method that will be invoked when there is a selection
		 * on the radio buttons.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			updateRadioButtonState(e);
		}
	}
}
