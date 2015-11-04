package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstJPanel.
 * <p>
 * This is a demonstration program of adding a JPanel
 * to a JFrame. This is an example of the basics of
 * adding components to a frame that can then be
 * displayed to the user.
 * <p>
 * By adding a basic JPanel to the JFrame, we can see
 * how we can use this method to slowly build up a GUI.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class MyFirstJPanel {

	/**
	 * Constructor.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("My First JPanel");
		
		// create a JPanel and add it to the frame. 
		frame.add(createAndAddJPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	/**
	 * Create a JPanel with a single label.
	 * @return the created panel.
	 */
	private static JPanel createAndAddJPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("This is a label inside the panel");
		
		panel.add(label);
		return panel;
	}
}
