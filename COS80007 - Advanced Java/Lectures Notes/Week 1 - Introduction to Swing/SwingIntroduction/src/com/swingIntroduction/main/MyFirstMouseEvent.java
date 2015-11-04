package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstMouseEvent
 * <p>
 * This class is responsible for demonstrating how a
 * mouse event is to be implemented. In this example
 * we install a mouse listener onto a panel that will
 * update the displayed coordinates whenever the mouse
 * is moving around the panel.
 * <p>
 * @author szeyick
 * @version 0.1
 *
 */
public class MyFirstMouseEvent {

	/**
	 * The label to display the mouse coordinates and interaction.
	 */
	private static JLabel label;
	
	/**
	 * The program main.
	 * @param args - The command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("My First Mouse Event");
		frame.add(createMouseEventPanel(), BorderLayout.CENTER);
		frame.add(createLabelPanel(), BorderLayout.SOUTH);
		frame.setVisible(true);

	}
	
	/**
	 * Create a panel that a mouse event listener will be installed
	 * on.
	 * @return - The panel that we will listen for mouse events.
	 */
	private static JPanel createMouseEventPanel() {
		JPanel panel = new JPanel();
		
		panel.addMouseListener(new MyMouseEventListener());
		panel.addMouseMotionListener(new MyMouseMotionListener());
		panel.setBackground(Color.BLACK);
		panel.setOpaque(true);
		
		return panel;
	}
	
	/**
	 * 
	 * @return
	 */
	private static JPanel createLabelPanel() {
		JPanel panel = new JPanel();
		label = new JLabel("Mouse Information");
		
		panel.add(label);
		return panel;
	}
	
	/**
	 * The MyMouseEventListener
	 * <p>
	 * This class is responsible for listening to mouse events. When
	 * there is an interaction on the panel, it will trigger this
	 * listener to respond.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyMouseEventListener implements MouseListener {

		/**
		 * The mouse has been clicked.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			label.setText("Mouse button has been clicked...");
		}

		/**
		 * The mouse has been pressed.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			label.setText("Mouse button has been pressed");
		}

		/**
		 * The mouse button has been released.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			label.setText("Mouse button has been released");
		}

		/**
		 * The mouse has entered the panel bounds.
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			label.setText("Mouse has entered the panel bounds");
		}

		/**
		 * The mouse has exited the panel bounds
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			label.setText("Mouse has exited the panel bounds");
		}
	}
	
	/**
	 * The MyMouseMotionListener
	 * <p>
	 * This class is responsible for listening to mouse motion events
	 * that are coming from the panel. When a mouse dragged or moved
	 * event happens, this listener will be notified and update the
	 * label to display which method is being called.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyMouseMotionListener implements MouseMotionListener {

		/**
		 * The mouse is dragged through the panel.
		 * @param e - The mouse event.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			label.setText("Mouse drag event at: " + e.getX() + " ," + e.getY());
		}

		/**
		 * The mouse is moving through the panel.
		 * @param e - The mouse event.
		 */
		@Override
		public void mouseMoved(MouseEvent e) {
			label.setText("Mouse move event at: " + e.getX() + " ," + e.getY());
		}
	}
}
