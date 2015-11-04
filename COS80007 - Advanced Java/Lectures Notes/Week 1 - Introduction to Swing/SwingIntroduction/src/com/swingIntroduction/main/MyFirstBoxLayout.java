package com.swingIntroduction.main;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstBoxLayout
 * <p>
 * This is a demonstration of how the box layout
 * works. The box layout places all items within it
 * into a single line horizontally or veritcally depending
 * on how the layout has been initialised. It can be seen as
 * similar to that of the FlowLayout.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class MyFirstBoxLayout {

	/**
	 * The program main.
	 * @param args - The command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("My First Box Layout");
		frame.add(createBoxLayout());
		frame.setVisible(true);

	}
	
	/**
	 * Create a panel with box layout elements.
	 * @return
	 */
	private static JPanel createBoxLayout() {
		JPanel panel = new JPanel();
		
		// Layout the components along the Y Axix (Top to Bottom)
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Add some components to the layout.
		panel.add(new JButton("Button 1"));
		
		// Add some glue (Glue between buttons will expand if the container size is increased)
		// It will further expand the space (push the 2 buttons further apart)
		panel.add(Box.createVerticalGlue());
		panel.add(new JButton("Button 2"));
		panel.add(new JButton("Button 3"));
		
		// A strut will create a gap that is consistently sized even if the panel is resized.
		panel.add(Box.createVerticalStrut(20));
		panel.add(new JButton("Button 4"));
		
		JButton button = new JButton("Button 5");
		button.setAlignmentX(0.5f); // 0.5 alignment will centre it (0.0 - 1.0)
		
		panel.add(button);
		return panel;
	}

}
