package com.swingText.main;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.swingText.frame.MyJFrame;

/**
 * The ValidateInputDemo.
 * <p>
 * This class is to demo how to valid input
 * for a JTextField. Here we need to provide our
 * own implementation of the PlainDocument within
 * a JTextField. When the string is attempted to
 * be inserted, we intercept the string before it
 * is set to validate the input first.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class ValidInputDemo {

	/**
	 * The frame to add all components.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Input Validation Demo");
		frame.add(new MyJTextField());
		frame.setVisible(true);
	}
	
	/**
	 * The MyJTextField.
	 * <p>
	 * This class is a custom implementation of the JTextField.
	 * To allow for input validation, we need to provide our own
	 * default DocumentModel.
	 * <p>
	 * @author szeyick
	 */
	private static class MyJTextField extends JTextField {
		
		/**
		 * Constructor
		 */
		public MyJTextField() {
			super();
		}
		
		/**
		 * Provide your own PlainDocument model.
		 */
		@Override
		protected Document createDefaultModel() {
			return new MyPlainDocumentValidator();
		}
	}
	
	/**
	 * The MyPlainDocumentValidator.
	 * <p>
	 * This document validator replaces the default PlainDocument
	 * model for the JTextField. Before the string is added to the model
	 * it calls the insertString(...) method. Here we can do any form
	 * of input, or even strip the input before it is assigned to the model.
	 * <p>
	 * @author szeyick
	 */
	private static class MyPlainDocumentValidator extends PlainDocument {
		
		/**
		 * This method is called when a string is to be insert into the
		 * plain document model. Here we can do any type of validation on
		 * the input string before it is inserted into the model.
		 */
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	        String oldString = getText(0, getLength());
	        String newString = oldString.substring(0, offs)
	                + str + oldString.substring(offs);
	        
	        System.out.println(newString);
	        super.insertString(offs, newString, a);
		}
	}

}
