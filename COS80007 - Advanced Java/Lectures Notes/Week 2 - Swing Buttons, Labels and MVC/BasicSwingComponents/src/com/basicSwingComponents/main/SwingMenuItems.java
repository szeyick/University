package com.basicSwingComponents.main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.basicSwingComponents.frame.MyJFrame;

/**
 * The SwingMenuItems.
 * <p>
 * This class is a demonstration of how to create menus
 * in Swing.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class SwingMenuItems {

	/**
	 * The frame that the components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Swing Menu Items");
		createMenu();
		frame.setVisible(true);
	}
	
	/**
	 * Create a basic menu and add it to the frame.
	 */
	private static void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Open");
		
		menu.add(menuItem);
		menu.addSeparator();
		menu.add(new JMenuItem("Save"));
		menu.add(new JMenuItem("Exit"));
		
		JMenu editMenu = new JMenu("Edit");
		JMenu optionsMenu = new JMenu("Options");
		JMenuItem optionMenuItem1 = new JMenuItem("Menu Item 1");
		JMenuItem optionMenuItem2 = new JMenuItem("Menu Item 2");
		JMenuItem optionMenuItem3 = new JMenuItem("Menu Item 3");
		
		optionsMenu.add(optionMenuItem1);
		optionsMenu.add(optionMenuItem2);
		optionsMenu.add(optionMenuItem3);
		
		editMenu.add(optionsMenu);
		
		menuBar.add(menu);
		menuBar.add(editMenu);
		frame.setJMenuBar(menuBar);
	}

}
