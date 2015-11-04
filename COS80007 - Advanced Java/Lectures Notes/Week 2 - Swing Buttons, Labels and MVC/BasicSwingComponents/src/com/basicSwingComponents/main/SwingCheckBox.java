package com.basicSwingComponents.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingCheckBox.
 * <p>
 * This class provides a demonstration of how a checkbox
 * works. In this example, the checkbox will change the 
 * font of the text displayed in the label.
 * <p>
 * @author szeyick
 * @version 0.1
 *
 */
public class SwingCheckBox {

	/**
	 * The frame used to display the Swing components.
	 */
	private static Frame frame;
	
	/**
	 * The label that displays how many times the button
	 * has been pressed.
	 */
	private static JLabel label;
	
	/**
	 * The bold checkbox.
	 */
	private static JCheckBox boldCheckbox;
	
	/**
	 * The italics checkbox.
	 */
	private static JCheckBox italicsCheckBox;
	
	/**
	 * The checkbox listener.
	 */
	private static CheckboxActionListener checkboxListener;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Swing Checkbox");
		createCheckboxAndLabel();
		frame.setVisible(true);
	}
	
	/**
	 * Create a checkbox and a label. Upon selection, the
	 * checkbox selections will change the display of the 
	 * text in the label.
	 */
	private static void createCheckboxAndLabel() {
		label = new JLabel("This is my Swing label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkboxListener = new CheckboxActionListener();
		
		boldCheckbox = new JCheckBox("Bold");
		boldCheckbox.addActionListener(checkboxListener);
		italicsCheckBox = new JCheckBox("Italics");
		italicsCheckBox.addActionListener(checkboxListener);
		
		frame.add(label, BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.add(boldCheckbox);
		panel.add(italicsCheckBox);
		frame.add(panel, BorderLayout.SOUTH);
		updateLabelDisplay();
	}
	
	/**
	 * Update the display of the label.
	 */
	private static void updateLabelDisplay() {
		int fontStyle = 0;
		if (boldCheckbox.isSelected()) {
			fontStyle += Font.BOLD;
		}
		if (italicsCheckBox.isSelected()) {
			fontStyle += Font.ITALIC;
		}
		label.setFont(new Font("Serif", fontStyle, 50));
	}

	/**
	 * The CheckboxActionListener.
	 * <p>
	 * This is an inner class that is responsible for responding to
	 * user selection actions on the checkboxes in this example.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class CheckboxActionListener implements ActionListener {

		/**
		 * Update the checkbox states and the displayed label text.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			updateLabelDisplay();			
		}
	}
}
