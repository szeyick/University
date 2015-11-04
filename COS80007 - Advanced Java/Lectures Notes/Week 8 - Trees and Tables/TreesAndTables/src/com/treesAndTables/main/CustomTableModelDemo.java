package com.treesAndTables.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.treesAndTables.frame.MyJFrame;

/**
 * The CustomTableModelDemo.
 * <p>
 * This is a demonstration class that provides the same data
 * as that of the BasicTreeDemo, but this time rather than
 * providing the default constructor our data, we implement our
 * own table model.
 * <p>
 * Of note here is the getValueAt(row, column) method that will
 * automatically provide back the value to display in that particular cell.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class CustomTableModelDemo {

	/**
	 * The frame in which components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main. 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Custom Table Model Demo");
		createTable();
		frame.setVisible(true);
	}
	
	/**
	 * Create a JTable and add elements to it for it to be
	 * displayed.
	 */
	private static void createTable() {
		TableModel tableModel = new MyTableModel();
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * The MyTableModel.
	 * <p>
	 * This class is responsible for providing a table model that
	 * will return the appropriate data to fill our a cell. It replaces
	 * the 2D array of data from the BasicTreeDemo implementation along
	 * with the array defining the column names.
	 * <p>
	 * @author szeyick
	 */
	private static class MyTableModel extends AbstractTableModel {

		/**
		 * The number of rows in the table.
		 */
		@Override
		public int getRowCount() {
			return 3;
		}

		/**
		 * The number of columns in the table.
		 */
		@Override
		public int getColumnCount() {
			return 3;
		}

		/**
		 * @return the value to be set into the cell.
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			StringBuilder builder = new StringBuilder();
			builder.append("Row " + rowIndex);
			builder.append(" ");
			builder.append("Cell " + columnIndex);
			return builder.toString();
		}
		
		/**
		 * @return the name of the column.
		 */
		@Override
		public String getColumnName(int column) {
			return "Column " + column;
		}
	}
}
