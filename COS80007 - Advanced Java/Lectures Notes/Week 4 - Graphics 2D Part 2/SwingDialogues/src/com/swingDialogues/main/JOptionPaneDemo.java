package com.swingDialogues.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingDialogues.frame.MyJFrame;

/**
 * The JOptionPaneDemo.
 * <p>
 * This class will demonstrate the functionality of a
 * JOptionPane. A panel will contain a button that will
 * invoke the JOptionPane to be displayed.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class JOptionPaneDemo {

	/**
	 * The frame that everything will be drawn onto.
	 */
	private static JFrame frame;
	
	/**
	 * The panel that the option pane will be invoked from.
	 */
	private static JPanel panel;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("JOptionPane Demo");
		createButtonPanel();
		frame.setVisible(true);
	}
	
	/**
	 * Create a button panel and add it to the frame. The
	 * button will invoke a JOptionPane.
	 */
	private static void createButtonPanel() {
		panel = new JPanel();
		JButton button = new JButton("Press Me");
		button.addActionListener(new ActionEventListener());
		
		panel.add(button);
		frame.add(panel);
	}
	
	/**
	 * The ActionEventListener.
	 * <p>
	 * This listener is responsible for invoking and displaying the
	 * JOptionPane when it is pressed. 
	 * <p>
	 * @author szeyick
	 *
	 */
	private static class ActionEventListener implements ActionListener {

		/**
		 * The method that will be invoked when the button is pressed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(panel, "This is a modal option pane!");
			JOptionPane.showMessageDialog(panel, "Next pane is not shown until the first one is removed");
			int choice = JOptionPane.showConfirmDialog(panel, "Do you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION);
		
			// If the user has selected yes
			if (choice == JOptionPane.YES_OPTION) {
				String name = JOptionPane.showInputDialog(panel, "Please your name here", "Input", JOptionPane.QUESTION_MESSAGE);
				JOptionPane.showMessageDialog(panel, "Goodbye " + name);
				System.exit(0);
			}
		}
	}
}
