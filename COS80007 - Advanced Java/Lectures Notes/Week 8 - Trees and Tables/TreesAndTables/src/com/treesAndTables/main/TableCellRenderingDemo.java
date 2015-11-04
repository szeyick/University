package com.treesAndTables.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.treesAndTables.frame.MyJFrame;

/**
 * The TableCellRenderingDemo.
 * <p>
 * This is a demo class of how to apply a custom TableCellRenderer
 * to a tableModel to decorate a table cell.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class TableCellRenderingDemo {

	/**
	 * The frame in which components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main. 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Table Cell Rendering Demo");
		createTable();
		frame.setVisible(true);
	}
	
	/**
	 * Create a JTable and add elements to it for it to be
	 * displayed.
	 */
	private static void createTable() {
		MyTableModel tableModel = new MyTableModel();
		JTable table = new JTable(tableModel);
		table.setDefaultRenderer(Color.class, new MyTableCellRenderer());
		JScrollPane scrollPane = new JScrollPane(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * The MyTableCellRenderer.
	 * <p>
	 * This class is responsible for rendering an individual cell in
	 * the table. For each cell that is to be drawn onto the display, it
	 * calls getTableCellRendererComponent. It is here that we return a
	 * component (i.e. Panel, Label, etc) that will be drawn in that 
	 * cell.
	 * <p>
	 * @author szeyick
	 */
	private static class MyTableCellRenderer extends JPanel implements TableCellRenderer {

		/**
		 * For each cell in the table, we return a component that will
		 * be displayed.
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			setBackground((Color)value);
			return this;
		}
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
		 * The values for each cell.
		 */
		private Object[][] cellData = {
				{"Row 1 Cell 1", "Row 1 Cell 2", "Row 1 Cell 3", Color.YELLOW, true},
				{"Row 2 Cell 1", "Row 2 Cell 2", "Row 2 Cell 3", Color.RED, false},
				{"Row 3 Cell 1", "Row 3 Cell 2", "Row 3 Cell 3", Color.GREEN, true}
		};
		
		/**
		 * The column names.
		 */
		private String[] columnNames = {"Column 1", "Column 2", "Column 3", "Colour", "Checkbox"};
		
		/**
		 * The number of rows in the table.
		 */
		@Override
		public int getRowCount() {
			return cellData.length;
		}

		/**
		 * The number of columns in the table.
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
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
			return columnNames[column];
		}
		
		/**
		 * Return the class type of the object that is
		 * sitting in that particular column. It is used
		 * for customised cell rendering.
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return cellData[0][columnIndex].getClass();
		}
	}
}
