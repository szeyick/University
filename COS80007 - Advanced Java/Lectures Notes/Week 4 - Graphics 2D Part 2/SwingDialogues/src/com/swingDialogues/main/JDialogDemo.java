package com.swingDialogues.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingDialogues.frame.MyJFrame;

/**
 * The JDialogDemo.
 * <p>
 * This is a demo class that will provide an example of how
 * a JDialog works. A dialogue is similar to an JOptionPane
 * that allows you to display some data in its own frame. However
 * it provides you with more freedom to create your own kind of
 * option or dialogue windows.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class JDialogDemo {

	/**
	 * The frame that everything will be drawn onto.
	 */
	private static JFrame frame;
	
	/**
	 * The panel that the option pane will be invoked from.
	 */
	private static JPanel panel;
	
	/**
	 * The dialog to display that acts as as prompt panel.
	 */
	private static MyDialog dialog;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("JDialog Demo");
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
		
		dialog = new MyDialog();
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
			dialog.displayDialog();
		}
	}
	
	/**
	 * The MyDialog.
	 * <p>
	 * This is a custom dialog that will be displayed
	 * when a user presses the button.
	 * <p>
	 * @author szeyick
	 */
	private static class MyDialog extends JDialog {
		
		/**
		 * The panel that will hold the data.
		 */
		private JPanel dialogPanel;
		
		/**
		 * Constructor.
		 */
		public MyDialog() {
			super(frame, "My Dialog", true);
			dialogPanel = new JPanel();
			JButton button = new JButton("Exit");
			button.addActionListener(new ActionListener() {
				
				/**
				 * Exit the program when the button is pressed.
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			dialogPanel.add(button);
			dialogPanel.setSize(200, 100);
			add(dialogPanel);
			setSize(200, 100);
		}
		
		/**
		 * Display the dialog window.
		 */
		public void displayDialog() {
			setVisible(true);
		}
	}
}
