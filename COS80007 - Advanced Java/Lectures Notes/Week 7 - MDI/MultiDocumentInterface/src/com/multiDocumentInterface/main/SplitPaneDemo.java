package com.multiDocumentInterface.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.multiDocumentInterface.frame.MyJFrame;

/**
 * The SplitPaneDemo.
 * <p>
 * This is a demonstration class to see how the split
 * pane functions. In this example, we will have 2 panels
 * within a split pane that can be adjusted to give it more or
 * less space.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SplitPaneDemo {

	/**
	 * The frame to display all content.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Split Pane Demo");
		createSplitPane();
		frame.setVisible(true);
	}
	
	/**
	 * Create a split pane that contains 2 additonal
	 * panes.
	 */
	private static void createSplitPane() {
		JPanel topPanel = new MyJPanel("Top Panel");
		JPanel bottomPanel = new MyJPanel("Bottom Panel");
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		splitPane.setContinuousLayout(true);
		frame.add(splitPane, BorderLayout.CENTER);
	}
	
	/**
	 * The MyJPanel.
	 * <p>
	 * This class is responsible for providing a panel
	 * that can be drawn onto.
	 * <p>
	 * @author szeyick
	 */
	private static class MyJPanel extends JPanel {

		/**
		 * The text that this panel should draw.
		 */
		private String displayText;
		
		/**
		 * Constructor.
		 * @param displayText - The text to display.
		 */
		public MyJPanel(String displayText) {
			this.displayText = displayText;
			setSize(frame.getWidth(), frame.getHeight() / 2);
		}

		/**
		 * Paint the text onto the screen.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			RenderingHints hints = new RenderingHints(null);
			hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			Font font = new Font("Serif", Font.PLAIN, 20);
			g2.setFont(font);
			g2.setRenderingHints(hints);
			g2.drawString(displayText, getWidth() / 2, getHeight() / 2);
		}
	}

}
