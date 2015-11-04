package com.graphics2D.main;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * The AvailableFontDemo.
 * <p>
 * This class is a demonstration to display all the fonts
 * that are available to the operating system. It will output
 * the results to the console.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class AvailableFontDemo {

	/**
	 * The program main.
	 * @param args - Command line arguements.
	 */
	public static void main(String[] args) {
		Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (int i = 0; i < allFonts.length; i++) {
			Font font = allFonts[i];
			System.out.println("================================");
			System.out.println("Font Name: " + font.getFontName());
			System.out.println("Family Name: " + font.getFamily());
			System.out.println("Logical Name: " + font.getName());
		}
		System.out.println("================================");
		System.out.println("Total number of fonts avilable - " + allFonts.length);
	}

}
