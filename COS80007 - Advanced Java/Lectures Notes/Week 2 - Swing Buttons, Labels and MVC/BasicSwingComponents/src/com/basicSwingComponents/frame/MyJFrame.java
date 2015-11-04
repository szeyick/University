package com.basicSwingComponents.frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The MyJFrame.
 * <p>
 * This class is responsible for being the frame that
 * will be displayed.
 * <p>
 * @author szeyick
 * @version 0.1
 */
@SuppressWarnings("serial")
public class MyJFrame extends JFrame {

	/**
	 * Frame constructor.
	 */
	public MyJFrame(String frameTitle) {
		setTitle(frameTitle);

		// Ensure that the program exits if closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Retrieve the screen size of the device.
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		int screenHeight = (int) screenDimensions.getHeight();
		int screenWidth = (int) screenDimensions.getWidth();

		// Set the size of the frame and display in centre of the screen.
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation((screenWidth / 4), (screenHeight / 4));
	}
}

