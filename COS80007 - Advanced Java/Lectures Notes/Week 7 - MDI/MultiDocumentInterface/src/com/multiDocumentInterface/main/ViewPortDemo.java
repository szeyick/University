package com.multiDocumentInterface.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;

import com.multiDocumentInterface.frame.MyJFrame;

/**
 * The ViewPortDemo.
 * <p>
 * This class provides a demonstration of how a viewport
 * would be used. Its purpose is to make it look like you are looking
 * through a window onto something that is significantly larger, meaning
 * that you cannot see the whole of the outside but only what is defined
 * by the window (viewport)
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class ViewPortDemo {

	/**
	 * The frame that components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The viewport instance to move around.
	 */
	private static JViewport viewport;
	
	/**
	 * The program main.
	 * @param args - command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Viewport Demo");
		createViewPort();
		frame.setVisible(true);
	}
	
	/**
	 * Create a viewport.
	 */
	private static void createViewPort() {
		viewport = new JViewport();
		JPanel panel = new MyJPanel();
		viewport.add(panel);
		viewport.setView(panel);
		
		frame.add(viewport, BorderLayout.CENTER);
		
		JPanel buttonPanel =  new JPanel();
		JButton downButton = new JButton("Down");
		JButton upButton = new JButton("Up");
		ButtonActionEventListener listener = new ButtonActionEventListener();
		downButton.addActionListener(listener);
		upButton.addActionListener(listener);
		
		buttonPanel.add(downButton);
		buttonPanel.add(upButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Move the viewport around depending on the user selection.
	 * @param event - The button event.
	 */
	private static void moveViewport(ActionEvent event) {
		String buttonName = event.getActionCommand();
		Point viewportPoint = viewport.getViewPosition();
		switch (buttonName) {
		
		case "Down" :
			viewportPoint.y += 10;
			viewport.setViewPosition(viewportPoint);
			break;
		case "Up" :
			viewportPoint.y -= 10;
			viewport.setViewPosition(viewportPoint);
		default :
			break;
		}
	}
	
	/**
	 * The ButtonActionEventListener.
	 * <p>
	 * The purpose of this class is to listen for button press
	 * events. When there is a button pressed event, it fill trigger
	 * the action performed method.
	 * <p>
	 * @author szeyick
	 */
	private static class ButtonActionEventListener implements ActionListener {

		/**
		 * Method that is called when there is a button press
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			moveViewport(e);			
		}
	}
	
	/**
	 * The MyJPanel.
	 * <p>
	 * This panel is responsible for handling the drawing.
	 * <p>
	 * @author szeyick
	 */
	private static class MyJPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyJPanel() {
			setBackground(Color.WHITE);
			ImageIcon image = new ImageIcon("resources/image1.jpg");
			JLabel label = new JLabel(image);
			add(label, BorderLayout.CENTER);
		}
	}

}
