package com.basicSwingComponents.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingRadioButtons.
 * <p>
 * This class shows an example of Swing Radio Buttons
 * being used as a ButtonGroup. This means that only one
 * radio button can be selected at any given time.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SwingRadioButtons {

	/**
	 * The frame containing the displayed components.
	 */
	private static JFrame frame;
	
	/**
	 * The panel that will have its background colour
	 * changed depending on the selected radio button.
	 */
	private static JPanel colouredPanel;
	
	/**
	 * The radio button to change the 
	 * panel colour to red.
	 */
	private static JRadioButton red;

	/**
	 * The radio button to change the 
	 * panel colour to green.
	 */
	private static JRadioButton green;
	
	/**
	 * The radio button to change the 
	 * panel colour to blue.
	 */
	private static JRadioButton blue;
	
	/**
	 * The radio button to change the 
	 * panel colour to black.
	 */
	private static JRadioButton black;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("My Swing Button Groups");
		createRadioButtonGroups();
		frame.setVisible(true);
	}
	
	/**
	 * This method will create a button group with a 
	 * few radio buttons that will change the background colour
	 * of a panel.
	 */
	private static void createRadioButtonGroups() {
		colouredPanel = new JPanel();
		colouredPanel.setBackground(Color.BLACK);
		
		RadioButtonActionListener listener = new RadioButtonActionListener();
		
		// Create the radio buttons and add them to the button group.
		ButtonGroup buttonGroup = new ButtonGroup();
		red = new JRadioButton("Red");
		green = new JRadioButton("Green");
		blue = new JRadioButton("Blue");
		black = new JRadioButton("Black");
		
		// Set the black radio button as default selection.
		black.setSelected(true);

		// Add the listener to the buttons are responsive
		red.addActionListener(listener);
		green.addActionListener(listener);
		blue.addActionListener(listener);
		black.addActionListener(listener);
		
		// Add the buttons to the button group to ensure
		// only one can be selected at a time.
		buttonGroup.add(black);
		buttonGroup.add(red);
		buttonGroup.add(green);
		buttonGroup.add(blue);
		
		JPanel buttonGroupPanel = new JPanel();
		buttonGroupPanel.add(black);
		buttonGroupPanel.add(red);
		buttonGroupPanel.add(green);
		buttonGroupPanel.add(blue);
		
		frame.add(colouredPanel, BorderLayout.CENTER);
		frame.add(buttonGroupPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Update the colour of the panel.
	 */
	private static void updatePanelColour(ActionEvent e) {
		String selectedColour = e.getActionCommand();
		switch (selectedColour) {
		case "Black":
			colouredPanel.setBackground(Color.BLACK);
			break;
		case "Red":
			colouredPanel.setBackground(Color.RED);
			break;
		case "Green":
			colouredPanel.setBackground(Color.GREEN);
			break;
		case "Blue":
			colouredPanel.setBackground(Color.BLUE);
			break;
		default:
			break;
		}
	}
	
	/**
	 * The RadioButtonActionListener.
	 * <p>
	 * This class is responsible for listening to update to the radio
	 * button selection and updating the panel colour.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class RadioButtonActionListener implements ActionListener {

		/**
		 * The method invoked when a radio button is selected.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			updatePanelColour(e);			
		}
	}

}
