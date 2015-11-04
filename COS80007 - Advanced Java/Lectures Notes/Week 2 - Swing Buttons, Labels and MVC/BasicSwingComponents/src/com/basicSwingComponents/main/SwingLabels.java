package com.basicSwingComponents.main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingLabels.
 * <p>
 * This is a demonstration program to show the different ways
 * that labels can be displayed in Swing.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SwingLabels {

	/**
	 * The JFrame to add the components to.
	 */
	private static Frame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Different JLabels");
		frame.setLayout(new GridLayout(2, 2, 10, 10));
		createDefaultLabel();
		createCentredLabel();
		createHtmlLabel();
		createCompositeLable();
		frame.setVisible(true);
	}
	
	/**
	 * Create and display the default label.
	 */
	private static void createDefaultLabel() {
		JLabel label = new JLabel("Label One");
		label.setBackground(Color.BLUE); // Background is not displayed as it is opaque
		label.setForeground(Color.ORANGE);
		frame.add(label);
	}
	
	/**
	 * Create and display a label with centre aligned text.
	 */
	private static void createCentredLabel() {
		JLabel label = new JLabel("Label Two", SwingConstants.CENTER);
		label.setBackground(Color.DARK_GRAY);
		label.setOpaque(true);
		frame.add(label);
	}
	
	/**
	 * Create and display a label with text set with HTML.
	 */
	private static void createHtmlLabel() {
		JLabel label = new JLabel("<HTML><h1><FONT COLOR=" + "red" + ">Label Three</FONT></h1></HTML>");
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		frame.add(label);
	}
	
	/**
	 * Create and display a label with text and another panel
	 */
	private static void createCompositeLable() {
		ImageIcon image = new ImageIcon();
		JLabel label = new JLabel("Label Four", image, SwingConstants.RIGHT);
		label.setOpaque(true);
		label.setBackground(Color.YELLOW);
		
		frame.add(label);
	}
}
