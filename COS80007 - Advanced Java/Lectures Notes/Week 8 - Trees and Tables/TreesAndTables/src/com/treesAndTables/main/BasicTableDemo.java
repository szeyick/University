package com.treesAndTables.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.treesAndTables.frame.MyJFrame;

/**
 * The BasicTableDemo.
 * <p>
 * This class is a demonstration class of how to build a basic JTable.
 * The model here only comprises of a 2D array of strings, along with a
 * array of column names. It does not use any complex model.
 * <p>
 * @author szeyick
 */
public class BasicTableDemo {
	
	/**
	 * The frame in which components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main. 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Basic JTable Demo");
		createTable();
		frame.setVisible(true);
	}
	
	/**
	 * Create a JTable and add elements to it for it to be
	 * displayed.
	 */
	private static void createTable() {
		Object[][] cellData = {
				{"Row 1 Cell 1", "Row 1 Cell 2", "Row 1 Cell 3"},
				{"Row 2 Cell 1", "Row 2 Cell 2", "Row 2 Cell 3"},
				{"Row 3 Cell 1", "Row 3 Cell 2", "Row 3 Cell 3"}
		};
		String[] columnNames = {"Column 1", "Column 2", "Column 3"};
		JTable table = new JTable(cellData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
	}

}
