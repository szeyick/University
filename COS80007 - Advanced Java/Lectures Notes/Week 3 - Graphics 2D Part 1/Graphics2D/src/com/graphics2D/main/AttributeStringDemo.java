package com.graphics2D.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.graphics2D.frame.MyJFrame;

/**
 * The AttributeStringDemo.
 * <p>
 * This class is to demonstrate how we can use an AttributeString
 * to individually set attributes to individual characters within
 * a string. The attributes will then be reflected to when the text
 * string is drawn onto the display.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class AttributeStringDemo {

	/**
	 * The frame that components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Graphics2D Attributed String");
		frame.add(new MyAttributedStringPanel());
		frame.setVisible(true);
	}
	
	/**
	 * The MyAttributedStringPanel.
	 * <p>
	 * This class is responsible for being the component that
	 * is drawn on.
	 * <p>
	 * @author szeyick
	 * @version 0.1
	 */
	private static class MyAttributedStringPanel extends JPanel {
		
		/**
		 * Constructor.
		 */
		public MyAttributedStringPanel() {
			setBackground(Color.WHITE);
		}
		
		/**
		 * Draw some text onto the screen and demonstrate
		 * anti-aliasing with RenderingHints.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			RenderingHints hints = new RenderingHints(null);
			
			Font serifFont = new Font("Serif", Font.PLAIN, 50);
			Font sansSerifFont = new Font("Monospaced", Font.PLAIN, 50);
			
			hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			AttributedString string = new AttributedString("Hello World");
			
			// Set "Hello" to display in serif font.
			// Set "World" to display in sansserif font.
			string.addAttribute(TextAttribute.FONT, serifFont, 0, 5);
			string.addAttribute(TextAttribute.FONT, sansSerifFont, 6, 11);
			string.addAttribute(TextAttribute.FOREGROUND, Color.BLUE, 6, 11);

			g2.setRenderingHints(hints);
			g2.drawString(string.getIterator(), getWidth() / 4, getHeight() / 2);
		}
	}

}
