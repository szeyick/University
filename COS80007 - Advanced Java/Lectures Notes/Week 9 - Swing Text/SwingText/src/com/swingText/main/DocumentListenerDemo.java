package com.swingText.main;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.swingText.frame.MyJFrame;

/**
 * The DocumentListenerDemo.
 * <p>
 * This class demonstrates how a DocumentListener works
 * to be able to read input.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class DocumentListenerDemo {

	/**
	 * The frame all components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("The Document Listener Demo");
		createTextField();
		frame.setVisible(true);
	}
	
	private static void createTextField() {
		JTextField textField = new JTextField(30);
		textField.getDocument().addDocumentListener(new MyDocumentListener());
		frame.add(textField);
		
	}
	
	/**
	 * The MyDocumentListener.
	 * <p>
	 * This listener class is responsible for listening to updates that
	 * have been inputted into the text field.
	 * <p>
	 * @author szeyick
	 *
	 */
	private static class MyDocumentListener implements DocumentListener {

		/**
		 * Method called when a character is added.
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document document = e.getDocument();
			try {
				// This will output the whole text after the character has been entered
				String inputtedText = document.getText(0, document.getLength());
				System.out.println(inputtedText);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}			
		}

		/**
		 * Method called when a character is removed.
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
			Document document = e.getDocument();
			try {
				String inputtedText = document.getText(0, document.getLength());
				System.out.println(inputtedText);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		/**
		 * Method called when some configuration to the text
		 * field has been changed.
		 */
		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
