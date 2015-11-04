package com.multiDocumentInterface.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.multiDocumentInterface.frame.MyJFrame;

/**
 * The TabbedPaneDemo.
 * <p>
 * This is a demonstration of how the tabbed panes
 * work. 
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class TabbedPaneDemo {

	/**
	 * The frame that everything will be drawn onto.
	 */
	private static JFrame frame;
	
	/**
	 * The tabbed pane to add/remove components from.
	 */
	private static JTabbedPane tabbedPane;
	
	/**
	 * Label to indicate which tab is selected.
	 */
	private static JLabel label;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Tabbed Pane Demo");
		createTabbedPane();
		frame.setVisible(true);
	}
	
	/**
	 * Create a panel to illustrate the thread example.
	 */
	private static void createTabbedPane() {
		label = new JLabel("Selected tab: 0");
		tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener(new MyChangeListener());
		for (int i = 0; i < 5; i++) {
			JPanel panel = new MyJPanel(i);
			tabbedPane.addTab("Panel " + i, panel);
		}
		frame.add(tabbedPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		ActionListener listener = new MyEventListener();
		JButton addPanelButton = new JButton("Add Panel");
		JButton removePanelButton = new JButton("Remove Panel");
		
		addPanelButton.addActionListener(listener);
		removePanelButton.addActionListener(listener);
		
		buttonPanel.add(addPanelButton);
		buttonPanel.add(removePanelButton);
		buttonPanel.add(label);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Method invoked if a button is pressed.
	 * @param event - The event originating from the button
	 */
	private static void buttonPressed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		switch (buttonName) {
		case "Add Panel" : 
			int tabCount = tabbedPane.getTabCount();
			JPanel panel = new MyJPanel(tabCount);
			tabbedPane.addTab("Panel " + tabCount, panel);
			break;
		case "Remove Panel" : 
			if (tabbedPane.getTabCount() > 0) {
				tabbedPane.remove(tabbedPane.getTabCount() - 1);
			}
			break;
		default : 
			break;
		}
	}
	
	/**
	 * The MyEventListener.
	 * <p>
	 * This listener is responsible for updating user actions
	 * when the buttons are pressed.
	 * <p>
	 * @author szeyick
	 */
	private static class MyEventListener implements ActionListener {

		/**
		 * Update the buttons
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			buttonPressed(e);			
		}
	}
	
	/**
	 * 
	 * @author szeyick
	 *
	 */
	private static class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			int selectedIndex = tabbedPane.getSelectedIndex();
			label.setText("Selected tab: " + selectedIndex);
			
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
		 * The current value in the loop
		 */
		private int count;
		
		/**
		 * Constructor.
		 */
		public MyJPanel(int panelNumber) {
			setBackground(Color.WHITE);
			count = panelNumber;
		}
		
		/**
		 * Create and display a string that increments
		 * as the count increments.
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
			g2.drawString(String.valueOf(count), getWidth() / 2, getHeight() / 2);
		}
	}
}
