package com.swingText.main;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.swingText.frame.MyJFrame;

/**
 * The JTextFieldDemo.
 * <p>
 * This class provides a basic demonstration
 * of the JTextField.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class JTextFieldDemo {

	/**
	 * The frame that all components will be added to
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Basic JTextField Demo");
		createTextField();
		frame.setVisible(true);
	}
	
	/**
	 * Create the text field.
	 */
	private static void createTextField() {
		JTextField field = new JTextField("Default Text", 30);
		frame.add(field);
	}
}
