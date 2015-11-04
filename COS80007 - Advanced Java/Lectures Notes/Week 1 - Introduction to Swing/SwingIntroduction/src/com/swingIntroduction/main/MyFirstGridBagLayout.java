package com.swingIntroduction.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstGridBagLayout.
 * <p>
 * This is a demonstration program used to illustrate
 * how GridBagConstraints are used in a GridBagLayout
 * to provide further properties to line components
 * against.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class MyFirstGridBagLayout {

	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("My First Grid Bag Layout");
		frame.add(createGridBagLayout());
		frame.setVisible(true);
	}

	/**
	 * Create a panel that uses the GridBagLayouts
	 * @return the created panel.
	 */
	private static JPanel createGridBagLayout() {
		JPanel panel = new JPanel(new GridBagLayout());
		
		// GridBagConstraints can be applied to each individual cell.
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		panel.add(new JButton("1"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		panel.add(new JButton("2"), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		
		panel.add(new JButton("3"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		// Second row of buttons.
		panel.add(new JButton("4"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		panel.add(new JButton("5"), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		
		panel.add(new JButton("6"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		// Third row of buttons
		panel.add(new JButton("7"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		panel.add(new JButton("8"), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		
		panel.add(new JButton("9"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL; // Fill out horizontal space of that cell.
		gbc.gridwidth = 3;
		panel.add(new JButton("0"), gbc);
		return panel;
	}
}
