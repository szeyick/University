package com.basicSwingComponents.main;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingButtons.
 * <p>
 * Here we will demonstrate how we can override the internal
 * data models of a JButton to provide our own additional behaviour
 * to it. The additional behaviour in this example is to keep a 
 * count of how many times the button has been pressed.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SwingButtons {

	/**
	 * The frame used to display the Swing components.
	 */
	private static Frame frame;
	
	/**
	 * The label that displays how many times the button
	 * has been pressed.
	 */
	private static JLabel label;
	
	/**
	 * The button.
	 */
	private static JButton button;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Swing Button Models");
		createButtonAndPanel();
		frame.setVisible(true);
	}
	
	/**
	 * Create a JButton with a custom defaultButtonModel
	 * that keeps track of how many times the button has been
	 * pressed. Each time the button is pressed, an update
	 * is sent to the button listener to update a label.
	 */
	private static void createButtonAndPanel() {
		button = new JButton("Press Me");
		button.setModel(new MyButtonModel());
		button.addActionListener(new ButtonListener());
		button.setVisible(true);
		
		label = new JLabel("The button has been pressed 0 times", SwingConstants.CENTER);
		frame.add(button, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
	}
	
	/**
	 * Update the display of the label.
	 */
	private static void updateLabelText() {
		MyButtonModel buttonModel = (MyButtonModel) button.getModel();
		label.setText("The button has been pressed "  + buttonModel.getButtonHitCount() + " times");
	}
	
	/**
	 * The MyButtonModel.
	 * <p>
	 * This class is responsible for holding the data for the
	 * button. It has been extended from the defaultButtonModel to
	 * provide additional behaviour of keeping track of the number
	 * of times the button has been pressed.
	 * <p>
	 * @author szeyick
	 * @version 0.1.
	 */
	@SuppressWarnings("serial")
	private static class MyButtonModel extends DefaultButtonModel {
		
		/**
		 * The number of times the button has been pressed.
		 */
		private int buttonHitCount;
		
		/**
		 * Constructor.
		 */
		public MyButtonModel() {
			buttonHitCount = 0;
		}
		
		/**
		 * Method is invoked when a button is pressed.
		 * @param buttonPressed - has the button been pressed. 
		 * <code>true</code> if it has, <code>false</code> if the
		 * button has been released.
		 */
		@Override
		public void setPressed(boolean buttonPressed) {
			super.setPressed(buttonPressed);
			if (buttonPressed) {
				buttonHitCount++;
			}
		}
		
		/**
		 * @return - The number of times the button has been pressed.
		 */
		public int getButtonHitCount() {
			return buttonHitCount;
		}
	}
	
	/**
	 * The ButtonListener.
	 * <p>
	 * This class is responsible for listening for button press
	 * events. When the button is pressed, the actionPerformed(...) method
	 * will be invoked. In this example we will update the label to increment
	 * the number of times the button has been pressed.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class ButtonListener implements ActionListener {

		/**
		 * Method that is called when the button is pressed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			updateLabelText();			
		}
		
	}
}
