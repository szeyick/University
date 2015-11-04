package com.basicSwingComponents.main;

import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingTextFields.
 * <p>
 * Here we have an example of a text field and label
 * working with mnemonics. It allows us to create
 * shortcuts to text fields that are associated to 
 * labels.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SwingTextFields {

	/**
	 * The JFrame to add the components to.
	 */
	private static Frame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Labels and Text Fields");
		createLabelWithTextField();
		frame.setVisible(true);
	}
	
	/**
	 * Create a label and an associated field with a keyboard
	 * mnemonic shortcut.
	 */
	private static void createLabelWithTextField() {
		JPanel panel = new JPanel(); // Composite panel containing the label and textfield.
		
		JLabel label = new JLabel("Enter Text");
		label.setDisplayedMnemonic('n'); // Request the focus when Alt-N is pressed
		
		panel.add(label);
		JTextField textfield = new JTextField(10);
		
		panel.add(textfield);
		panel.add(new JTextField(20));
		frame.add(panel);
		
		label.setLabelFor(textfield);
	}
}
