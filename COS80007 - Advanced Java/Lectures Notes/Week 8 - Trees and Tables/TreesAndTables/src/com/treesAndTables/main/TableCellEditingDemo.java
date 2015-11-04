package com.treesAndTables.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.treesAndTables.frame.MyJFrame;

/**
 * The TableCellEditingDemo.
 * <p>
 * This class will demonstrate how to implement a custom
 * TableCellEditor.
 * <p>
 * @author szeyick
 */
public class TableCellEditingDemo {

	/**
	 * The frame in which components will be added to.
	 */
	private static JFrame frame;

	/**
	 * The program main. 
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Table Cell Editing Demo");
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
		table.setDefaultEditor(Color.class, new ColorTableCellEditor());
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
		
		/**
		 * Is the cell editable.
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
		
		   public void setValueAt(Object obj, int r, int c) { cellData[r][c] = obj; }
	}

	/**
	 * The ColorTableCellEditor.
	 * <p>
	 * This class is responsible for providing a cell editor. It is an editor that is 
	 * used for each individual cell. So it is called each time a cell is changed.
	 * <p>
	 * @author szeyick
	 */
	private static class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor {

		/**
		 * The panel to return to display.
		 */
		private JPanel panel;

		/**
		 * The selected colour.
		 */
		private Color color;

		/**
		 * A colour chooser
		 */
		private JColorChooser colorChooser;

		/**
		 * The dialog indicating chosen colour.
		 */
		private JDialog colorDialog;

		/**
		 * Constructor.
		 */
		public ColorTableCellEditor() {
			panel = new JPanel();
			colorChooser = new JColorChooser();
			colorDialog = JColorChooser.createDialog (
					null, "Planet Color", false, colorChooser,
					new ActionListener() // OK button listener
					{
						public void actionPerformed(ActionEvent event) 
						{ stopCellEditing(); }
					},
					new ActionListener() // Cancel button listener
					{
						public void actionPerformed(ActionEvent event) 
						{ cancelCellEditing(); }
					});
			colorDialog.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent event) 
				{ cancelCellEditing(); }
			});
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column)
		{
			// this is where we get the current Color value. We store
			// it in the dialog in case the user starts editing
			colorChooser.setColor((Color) value);
			return panel;
		}

		public boolean shouldSelectCell(EventObject anEvent)
		{
			// start editing
			colorDialog.setVisible(true);

			// tell caller it is ok to select this cell
			return true;
		}

		public void cancelCellEditing()
		{
			// editing is canceled--hide dialog
			colorDialog.setVisible(false);
			super.cancelCellEditing();
		}

		public boolean stopCellEditing()
		{
			// editing is complete--hide dialog
			colorDialog.setVisible(false);
			super.stopCellEditing();

			// tell caller is is ok to use color value
			return true;
		}

		public Object getCellEditorValue()
		{
			return colorChooser.getColor();
		}

	}
}
