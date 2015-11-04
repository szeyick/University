package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstBorderLayout
 * <p>
 * This is a demonstration of a border layout. We can
 * set to where the components are laid out to.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class MyFirstBorderLayout {

	/**
	 * The program main.
	 * @param args - command line arguments.
	 */
	public static void main(String[] args) {
		Frame frame = new MyJFrame("My First Border Layout");
		frame.add(createBorderLayoutPanel());
		frame.setVisible(true);
	}
	
	/**
	 * Create a return a panel where the internal components
	 * are laid out in the border layout fashion.
	 * @return the created panel to add to the frame.
	 */
	private static JPanel createBorderLayoutPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		JButton northButton = new JButton("North");
		panel.add(northButton, BorderLayout.NORTH);
		
		JButton southButton = new JButton("South");
		panel.add(southButton, BorderLayout.SOUTH);

		JButton eastButton = new JButton("East");
		panel.add(eastButton, BorderLayout.EAST);

		JButton westButton = new JButton("West");
		panel.add(westButton, BorderLayout.WEST);
		
		JButton centreButton = new JButton("Centre");
		panel.add(centreButton, BorderLayout.CENTER);
		
		return panel;
	}
}
