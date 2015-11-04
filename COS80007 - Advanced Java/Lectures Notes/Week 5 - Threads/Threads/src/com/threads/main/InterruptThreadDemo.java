package com.threads.main;

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
import javax.swing.JPanel;

import com.threads.frame.MyJFrame;

/**
 * The InterruptThreadDemo.
 * <p>
 * This class is a demonstration class for interrupting threads.
 * In this example, we have another button that calls interrupt
 * on the animation thread that will delay the drawing as it moves
 * the animation thread into the BLOCKED state.
 * <p>
 * @author szeyick
 */
public class InterruptThreadDemo {

	/**
	 * The frame that everything will be drawn onto.
	 */
	private static JFrame frame;
	
	/**
	 * Panel to draw text onto the screen.
	 */
	private static MyJPanel animationPanel;
	
	private static Thread animationThread;
	
	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Single Thread Demo");
		createPanel();
		frame.setVisible(true);
	}
	
	/**
	 * Create a panel to illustrate the thread example.
	 */
	private static void createPanel() {
		animationPanel = new MyJPanel();
		frame.add(animationPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		ActionListener listener = new MyEventListener();
		JButton startButton = new JButton("Start");
		JButton stopButton = new JButton("Exit");
		JButton interruptButton = new JButton("Interrupt");

		startButton.addActionListener(listener);
		stopButton.addActionListener(listener);
		interruptButton.addActionListener(listener);
		
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(interruptButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Method invoked if a button is pressed.
	 * @param event - The event originating from the button
	 */
	private static void buttonPressed(ActionEvent event) {
		String buttonName = event.getActionCommand();
		switch (buttonName) {
		case "Start" : 
			AnimationCounterRunnable runnable = new AnimationCounterRunnable();
			animationThread = new Thread(runnable, "Animation Thread");
			animationThread.start();
			break;
		case "Exit" : 
			System.exit(0);
			break;
		case "Interrupt" :
			animationThread.interrupt();
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
		private static int count;
		
		/**
		 * Constructor.
		 */
		public MyJPanel() {
			setBackground(Color.WHITE);
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
		
		/**
		 * Increment the counter that redraws the panel.
		 */
		public void incrementCount() {
			count++;
			paint(this.getGraphics());
		}
	}
	
	/**
	 * The AnimationCounterRunnable.
	 * <p>
	 * This class is responsible for providing the execution steps when
	 * the thread allows it to execute. When it does, the thread will call
	 * this runnable's run() method so it is here where we can continue
	 * the animation drawing.
	 * <p>
	 * @author szeyick
	 */
	private static class AnimationCounterRunnable implements Runnable {

		/**
		 * When this is called, we update the animation panel.
		 */
		@Override
		public void run() {
			for (int i = 0; i < 2000; i++) {
				if (!Thread.interrupted()) {
					try {
						animationPanel.incrementCount();
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
