package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstActionEvent.
 * <p>
 * This is a demonstration of how to implement an
 * action event. ActionEvent classes are usually applied
 * to JButtons, JTextFields and JMenu items when there
 * is a user selection on it.
 * <p> 
 * @author szeyick
 * @version 0.1
 */
public class MyFirstActionEvent {

	/**
	 * The label to display which button has been selected.
	 */
	private static JLabel label;
	
	/**
	 * The action event listener to listen for button events.
	 */
	private static ActionEventListener actionEventListener;
	
	/**
	 * The program main.
	 * @param args - The command line arguments.1
	 */
	public static void main(String[] args) {
		Frame frame = new MyJFrame("My First Border Layout");
		frame.add(createBorderLayoutPanel(), BorderLayout.CENTER);
		frame.add(createLabelPanel(), BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	/**
	 * Create a return a panel where the internal components
	 * are laid out in the border layout fashion.
	 * @return the created panel to add to the frame.
	 */
	private static JPanel createBorderLayoutPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		actionEventListener = new ActionEventListener();

		JButton northButton = new JButton("North");
		northButton.addActionListener(actionEventListener);
		panel.add(northButton, BorderLayout.NORTH);

		JButton southButton = new JButton("South");
		southButton.addActionListener(actionEventListener);
		panel.add(southButton, BorderLayout.SOUTH);

		JButton eastButton = new JButton("East");
		eastButton.addActionListener(actionEventListener);
		panel.add(eastButton, BorderLayout.EAST);

		JButton westButton = new JButton("West");
		westButton.addActionListener(actionEventListener);
		panel.add(westButton, BorderLayout.WEST);

		JButton centreButton = new JButton("Centre");
		centreButton.addActionListener(actionEventListener);
		panel.add(centreButton, BorderLayout.CENTER);

		return panel;
	}
	
	/**
	 * Create a panel with a label on it that will be updated
	 * whenever there is a user clicking on a button.
	 * @return the button with a label in it.
	 */
	private static JPanel createLabelPanel() {
		JPanel panel = new JPanel();
		label = new JLabel("Click on a button");
		panel.add(label);
		
		return panel;
	}
	
	/**
	 * The ActionEventListener.
	 * <p>
	 * This class is responsible for listening to action events. In
	 * this example, the listener is applied to each of the buttons
	 * and when the buttons are pressed will invoke the actionPerformed(...)
	 * method contained within this inner class.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class ActionEventListener implements ActionListener {

		/**
		 * Perform an action when the button is pressed
		 * @param event - the action event.
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// The ActionCommand by default returns the buttons text
			String buttonName = event.getActionCommand();
			label.setText("The " + buttonName + " button has been pressed");
		}
	}
}
