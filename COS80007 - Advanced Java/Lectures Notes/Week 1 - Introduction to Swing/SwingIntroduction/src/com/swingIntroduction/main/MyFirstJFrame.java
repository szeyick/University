package com.swingIntroduction.main;

import java.awt.Frame;

import com.swingIntroduction.frame.MyJFrame;

/**
 * The MyFirstJFrame.
 * <p>
 * This is a demonstration program to display a 
 * Swing Frame that will be displayed in the middle
 * of the screen. 
 * <p>
 * A Frame is one of the backbones of Swing being the
 * base of which Swing components are drawn onto. When
 * displayed it will just be an empty box as we have
 * not added anything to it yet.
 * <p>
 * @author - szeyick
 * @version - 0.1
 */
public class MyFirstJFrame {

	/**
	 * Constructor.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		Frame frame = new MyJFrame("My First Swing Frame");
		frame.setVisible(true);
	}
}
