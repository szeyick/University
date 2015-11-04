package com.multiDocumentInterface.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.multiDocumentInterface.frame.MyJFrame;

/**
 * The InternalFrameDemo.
 * <p>
 * This is a demonstration class that displays how JInterlaFrames
 * are added to a JDesktop pane to give the impression of multiple
 * frames running within a single application.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class InternalFrameDemo {

	/**
	 * The frame that the application is contained in.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("JDesktopPane/JInternalFrame Demo");
		createDesktop();
		frame.setVisible(true);
	}
	
	/**
	 * Create a new desktop with internal frames.
	 */
	private static void createDesktop() {
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setPreferredSize(new Dimension(600, 800));
		
		JInternalFrame frame1 = new JInternalFrame("Internal Frame 1", true, true, true);
		JInternalFrame frame2 = new JInternalFrame("Internal Frame 2", true, true, true);
		
		frame1.add(new MyPanel("Panel 1"), BorderLayout.CENTER);
		frame2.add(new MyPanel("Panel 2"), BorderLayout.CENTER);

		// Like with normal frames, set the visibility to true to make it visible.
		frame1.setVisible(true);
		frame2.setVisible(true);
		
		// Packing a frame allows it to inherit its size from its child components. 
		frame1.pack();
		frame2.pack();
		
		desktopPane.add(frame1);
		desktopPane.add(frame2);
		
		frame.add(desktopPane, BorderLayout.CENTER);
	}
	
	/**
	 * The MyPanel.
	 * <p>
	 * This panel class is responsible for displaying
	 * some kind of information through the paintComponent(...)
	 * <p>
	 * @author szeyick
	 */
	private static class MyPanel extends JPanel {
		
		/**
		 * The text to display in the panel.
		 */
		private String displayText;
		
		/**
		 * Constructor
		 * @param the text to render in this panel.
		 */
		public MyPanel(String displayText) {
			this.displayText = displayText;
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(300, 200));
		}
		
		/**
		 * Draw something onto the panel.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			Font font = new Font("Serif", Font.PLAIN, 30);
			
			g2.setFont(font);
			g2.drawString(displayText, getWidth() / 2, getHeight() / 2);
		}
	}

}
