package com.swingText.main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swingText.frame.MyJFrame;

/**
 * The JPasswordFieldDemo.
 * <p>
 * This class provides a demonstration of the JPasswordField
 * @author szeyick
 *
 */
public class JPasswordFieldDemo {

	/**
	 * The frame that all components will be added to
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Basic JPasswordField Demo");
		createPasswordTextField();
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Create the text field.
	 */
	private static void createPasswordTextField() {
		JTextField field = new JPasswordField(30);
		frame.add(field);
	}
}
