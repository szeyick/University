package com.swingIntroduction.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstFlowLayout.
 * <p>
 * This program demonstrates how the flow layout
 * can be used to lay out components to be displayed.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class MyFirstFlowLayout {

	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new MyJFrame("FLow Layout Example");
		
		// Set one panel to the north, the other to the south to demonstrate the difference
		// in the FlowLayout alignment.
		frame.add(createPanelWithFlowLayout(FlowLayout.CENTER, 20, 20), BorderLayout.NORTH);
		frame.add(createPanelWithFlowLayout(FlowLayout.RIGHT, 20, 20), BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	/**
	 * Create a JPanel that demonstrates flow layout.
	 * @param alignment - The alignment of the components.
	 * @param horizGap - The horizontal gap between added components.
	 * @param vertGap - The vertical gap between added components.
	 * @return - The JPanel to add.
	 */
	private static JPanel createPanelWithFlowLayout(int alignment, int horizGap, int vertGap) {
		JPanel panel = new JPanel(new FlowLayout(alignment, horizGap, vertGap));
		
		JButton button1 = new JButton("Button 1");
		JButton button2 = new JButton("Button 2");
		JButton button3 = new JButton("Button 3");
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		return panel;
	}

}
