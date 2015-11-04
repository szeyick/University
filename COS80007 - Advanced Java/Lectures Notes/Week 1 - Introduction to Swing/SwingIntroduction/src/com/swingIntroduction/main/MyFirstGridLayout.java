package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstGridLayout.
 * <p>
 * This is an example program that demonstrates how
 * components are aligned on the grid layout. 
 * <p>
 * @author szeyick
 * @version - 0.1
 */
public class MyFirstGridLayout {

	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("My First Grid Layout");
		frame.add(createGridLayout(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	/**
	 * Create a panel that aligns its components in a GridLayout. The buttons
	 * are filled up per row first.
	 * @return - the JPanel to display.
	 */
	private static JPanel createGridLayout() {
		// Create a 3 x 3 grid.
		int rows = 3;
		int columns = 3;
		JPanel panel = new JPanel(new GridLayout(rows, columns, 10, 10));
		
		// Add buttons to the layout.
		for (int i = 1; i <= rows * columns; i++) {
			JButton button = new JButton("Button " + i);
			panel.add(button);
		}
		return panel;
	}
}
