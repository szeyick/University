package com.multiDocumentInterface.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

import com.multiDocumentInterface.frame.MyJFrame;

/**
 * The ScrollPaneDemo.
 * <p>
 * This class is a demonstration of how a scroll pane
 * works. It is basically a JViewport with scrollbars.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class ScrollPaneDemo {

	/**
	 * The frame that all components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Basic ScrollPane demo");
		createScrollPane();
		frame.setVisible(true);
	}
	
	/**
	 * Create a JScrollPane. 
	 * When we create a scrollpane, we add content into it so it
	 * allows us to scroll around whatever is inside the scrollpane
	 * so long as the component inside is larger than the scrollpane.
	 */
	private static void createScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// As with a viewport, the scrollpane needs to set the viewport view when the component
		// is added so it knows what to look at.
		JPanel panel = new MyPanel();
		scrollPane.add(new MyPanel());
		scrollPane.setViewportView(panel);
		frame.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * The MyPanel.
	 * <p>
	 * This class is responsible for displaying an image. To be able to be
	 * used within a JScrollPane, it needs to implement the Scrollable interface.
	 * <p>
	 * @author szeyick
	 */
	private static class MyPanel extends JPanel implements Scrollable {

		/**
		 * Constructor.
		 */
		public MyPanel() {
			ImageIcon image = new ImageIcon("resources/image1.jpg");
			JLabel label = new JLabel(image);
			
			add(label, BorderLayout.CENTER);			
		}
		
		/**
		 * The size that we would want the viewport to be.
		 */
		@Override
		public Dimension getPreferredScrollableViewportSize() {
			return new Dimension(100, 100);
		}

		/**
		 * The size of each scroll when you click on the arrows.
		 */
		@Override
		public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
			return 10;
		}

		/**
		 * The distance moved when you click on the scrollbar area.
		 */
		@Override
		public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
			return 1;
		}

		/**
		 * Do you want scrollbars to appear in the vertical direction.
		 * Setting to <code>true</code> will disable scrollbars to be visible to
		 * scroll on the horizontal
		 */
		@Override
		public boolean getScrollableTracksViewportWidth() {
			return true;
		}

		/**
		 * Do you want scrollbars to appear in the horizontal direction.
		 * Setting to <code>true</code> will disable the scrollbars from
		 * appearing on the vertical.
		 */
		@Override
		public boolean getScrollableTracksViewportHeight() {
			return false;
		}
	}
}
