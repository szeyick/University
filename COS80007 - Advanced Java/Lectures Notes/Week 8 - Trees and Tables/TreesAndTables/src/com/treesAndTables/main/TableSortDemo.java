package com.treesAndTables.main;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.treesAndTables.frame.MyJFrame;

/**
 * The TableSortDemo.
 * <p>
 * This class is a demonstration class that will show how
 * to sort table data. Here we use a FilteredModel that is the
 * model that is being re-arranged, we do not touch the original TableModel
 * as it contains the references to the original order of the data. It is up
 * to the FilteredModel to correct implement the sort and lookup in the getValueAt(..)
 * method.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class TableSortDemo {

	/**
	 * The frame in which components will be added to.
	 */
	private static JFrame frame;

	/**
	 * The program main. 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Table Sort Demo");
		createTable();
		frame.setVisible(true);
	}

	/**
	 * Create a JTable and add elements to it for it to be
	 * displayed.
	 */
	private static void createTable() {
		TableModel tableModel = new MyTableModel();
		MyFilteredModel filteredModel = new MyFilteredModel(tableModel);
		JTable table = new JTable(filteredModel);
		
		// Add a mouse listener to the table
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			
			/**
			 * Find the column being selected for sorting.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					// Find the column that was selected
					int selectedColumn = table.columnAtPoint(e.getPoint());
					
					// This maps the selected column in the view to the one in the model
					int modelColumn = table.convertColumnIndexToModel(selectedColumn);
					filteredModel.sort(modelColumn);
				}
			}
		});
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
		 * The cell data.
		 */
		private Object[][] cellData;
		
		/**
		 * Constructor
		 */
		public MyTableModel() {
			cellData = new String[getRowCount()][getColumnCount()];
			for (int row = 0; row < getRowCount(); row++) {
				Random rand = new Random();
				int number = rand.nextInt(50) + 1;
				for (int column = 0; column < getColumnCount(); column++) {
					cellData[row][column] = String.valueOf(number);
				}
				
			}
		}
		
		/**
		 * The number of rows in the table.
		 */
		@Override
		public int getRowCount() {
			return 50;
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

			return cellData[rowIndex][columnIndex];
		}

		/**
		 * @return the name of the column.
		 */
		@Override
		public String getColumnName(int column) {
			return "Column " + column;
		}
	}

	/**
	 * The MyFilteredModel.
	 * <p>
	 * This class is responsible for maintaining the sorted order
	 * of a selected column. It is this model that gets manipulated
	 * whenever the columns are to be sorted.
	 */
	private static class MyFilteredModel extends AbstractTableModel {

		/**
		 * A reference to the original table model.
		 */
		private static TableModel myTableModel;

		/**
		 * The column that has been selected for sorting.
		 */
		private int selectedColumnToSort;
		
		/**
		 * The list of rows in the table. It is this collection
		 * that is to be sorted.
		 */
		private List<Row> rowList;

		/**
		 * Constructor.
		 */
		MyFilteredModel(TableModel model) {
			myTableModel = model;
			rowList = new ArrayList<>();
			initialiseRowList();
		}
		
		/**
		 * Initialise the list of rows to be displayed.
		 */
		private void initialiseRowList() {
			int numberOfRows = myTableModel.getRowCount();
			for (int i = 0; i < numberOfRows; i++) {
				Row row = new Row(i);
				rowList.add(row);
			}
		}
		
		/**
		 * Sort the rowList that should re-arrange the
		 * row order, but still contain the same mapping
		 * to the original table.
		 * @param selectedColumn - Is the selected column that we need to sort by
		 */
		public void sort(int selectedColumn) {
			selectedColumnToSort = selectedColumn;
			Collections.sort(rowList);
			fireTableDataChanged();
		}

		/**
		 * @return the row count - which is the original table row count.
		 */
		@Override
		public int getRowCount() {
			return myTableModel.getRowCount();
		}

		/**
		 * @return the column count - which is the original table column count.
		 */
		@Override
		public int getColumnCount() {
			return myTableModel.getColumnCount();
		}

		/**
		 * @return the value at the particular cell. This is the main part of the
		 * sorting, as we need to perform a correct lookup to find the corresponding
		 * row in the original data model.
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// Look up in the internal collection for the object at the row index
			// Then use that index to look up the value in the main table.
			int originalIndex = rowList.get(rowIndex).originalRowIndex;
			return myTableModel.getValueAt(originalIndex, columnIndex);
		}

		/**
		 * The Row.
		 * <p>
		 * This class is responsible for holding the row data. When intialised
		 * this row object will map to its corresponding row in the original
		 * table model. It is this object that will be sorted when a user selects
		 * to sort a column. Sorting a collection of these row objects will not 
		 * change the index that it references in the original table.
		 * <p>
		 * @author szeyick
		 *
		 */
		private class Row implements Comparable<Row> {

			/**
			 * The row index from the original table that this object
			 * maps to.
			 */
			private int originalRowIndex;

			/**
			 * Constructor.
			 */
			public Row(int originalRowIndex) {
				this.originalRowIndex = originalRowIndex;
			}

			/**
			 * Method to break ties to determine the order.
			 */
			@Override
			public int compareTo(Row otherRowObject) {
				Object currentRowObjectValue = myTableModel.getValueAt(originalRowIndex, selectedColumnToSort);
				Object otherRowObjectValue = myTableModel.getValueAt(otherRowObject.originalRowIndex, selectedColumnToSort);

				// Compare the values and return the result.
				if (currentRowObjectValue instanceof Comparable) {
					return ((Comparable) currentRowObjectValue).compareTo((Comparable)otherRowObjectValue);
				}
				else {
					return currentRowObjectValue.toString().compareTo(otherRowObjectValue.toString());
				}
			}
		}
	}
}
