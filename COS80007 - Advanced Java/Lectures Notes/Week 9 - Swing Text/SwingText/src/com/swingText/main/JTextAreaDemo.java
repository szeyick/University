package com.swingText.main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.swingText.frame.MyJFrame;

/**
 * The JTextAreaDemo.
 * <p>
 * This class provides a demo of how to add a ScrollPane
 * to the TextArea.
 * <p>
 * @author szeyick
 */
public class JTextAreaDemo {

	/**
	 * The frame that all components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Text Area Scroll Demo");
		JTextArea textArea = new JTextArea(10, 50);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}
}
