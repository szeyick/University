## Swing Trees and Tables

### Simple Trees

A JTree is good for displaying and navigating data that can be represented in a heirachial context. It follows the **MVC** architecture pattern meaning you can provide it a data model and the JTree component will display it for you.

```
TreeModel model = ...
JTree tree = new JTree(model);
```

The TreeModel however is an **interface** and not a class, therefore we must construct our own data model that implements the interface.

It is also possible to use a **DefaultTreeModel** that takes a TreeNode element (also an interface) as part of its constructor. This model is a **DefaultMutableTreeNode**.

The **DefaultMutableTreeNode** represents an individual entry in a tree. It also holds a **user object**, that can be anything that will end up being displayed by the **JTree**.

The **DefaultMutableTreeNode** are added together to create a tree structure. The root of this tree is then passed to the **JTree** instance so it can render the tree with all the nodes.

The basic way to create a JTree would be to - 

```
new DefaultMutableTreeNode()
```

and add the user object to that particular node or add other nodes to it through its add() method. Basically, the tree model is comprised of zero to many DefaultMutableTreeNodes, where each node contains exactly 1 user object.

**Refer to example SimpleTree.java**

The appearance of the lines in the tree can be changed to look different through setting client properties -

```
tree.putClientProperty("JTree.lineStyle", "Angled");

Other line styles - None, Horizontal

// Adding handles (expand/collapse buttons) to the root node
tree.setShowsRootHandles(true);

// Hide the root element
tree.setRootVisible(false);
```

As with all other components, we can add it to a **JScrollPane** so that we can scroll around and view the tree if it becomes larger than the viewport.

### Editing Trees

We can add, delete or update nodes or the data within each node. To be able to modify a particular node, we are required to find it and also uniquely identify it.

This is where the **TreePath** class is handy as it creates a path from the root of the tree to the destination node. The path is defined as a bunch of **Object** references rather than TreeNode references, since we can provide custom TreeModel implementation. 

```
TreePath getSelectionPath()
TreePath getPathForRow(int row)
```

Will both return TreePath objects from a JTree.

If the TreeNode is known then we can return the path to the node with - 

```
TreePath path = new TreePath(myNode.getPath()); // Returns the path from the root to the node.
```

One you have the TreePath object, you can retrieve the leaf node with

```
TreePath path = tree.getSelectionPath();
path.getLastPathComponent();

OR

TreePath path = tree.getSelectionPath();
DefaultMutableTreeNode selectedNode = path.getLastSelectedPathComponent(); // Retrieves the last selected node.
```

### Adding Nodes

To add a node to a tree, you need to find the correct path to add it to. This means that you need to find the node that you want to add it to. From the example above, we can use the **path.getLastSelectedPathComponent()** to find the node that we want to add to.

```
DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
DefaultMutableTreeNode selectedNode = tree.getLastSelectedPathComponent();
treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount()); // Add it to the end of the selected node.
```

This will add the new node to the path and trigger the view to update itself. Subsequently you can delete a node with -

```
DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
DefaultMutableTreeNode selectedNode = tree.getLastSelectedPathComponent();
treeModel.removeNodeFromParent(selectedNode);
```

It should be noted that only the **DefaultTreeModel** will automatically fire a model change event to trigger the view to redraw. If we are implementing our own TreeModel interface, we will need to call **nodeChanged(changNode)** manually.

Sometimes the newly added node may not be visible, so we need to make a call for the path to be displayed.

```
TreeNode[] nodes = model.getPathToRoot(newNode);
TreePath path = new TreePath(nodes);
tree.makeVisible(path); // Will make the new node visible.
tree.scrollPathToVisible(path); // Will expand the tree to the new node.
```

**Refer to TreeEdit.java**

### Tree Traversal

The DefaultMutableTreeNode has enumeration objects allowing to iterate through all the nodes that are the child of the currently selected one. It can use either a BFS, DFS or preorder traversal.

It returns essentially a list of all the nodes in the selected order.

### Rendering Nodes

A trees look and feel can be customised (icons, fonts, background colour) that will be used by the DefaultTreeCellRenderer, changes here will affect the entire tree. The renderer class can be extended to customise each individual node.

Furthermore, implementing the **TreeCellRenderer** interface can allow custom renderers to be installed on the tree.

### Tree Events

If we wish to be notified when a user selects a tree node, we add a **TreeSelectionListener** to the **JTree**. All we need to do here is to create a class that implements the **TreeSelectionListener** interface and overidde the **valueChanged**. The TreeSelectionEvent will return the JTree as the source event.

From the JTree we can retrieve the TreeModel and the selected node.

```
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				System.out.println(selectedNode.getUserObject());
			}
		}
```

This will be triggered each time the user selects or deselects a tree node.

There is an additional parameter for the selection mode which defines which nodes can be selected.

- SINGLE_TREE_SELECTION - Selects only a single node, each selection will deselect the previous one.
- CONTIGUOUS_TREE_SELECTION - A contiguous range of nodes can be selected.
- DISCONTIGUOUS_TREE_SELECTION - A arbritrary set of nodes can be selected.

**Refer to ClassBrowserTree.java** shows the listener in action.

### JTable

Displays a 2D grid of objects, like the JTree uses a data model called the **TableModel**.

A table can be constructed without creating a customised **TableModel**. Just create a 2D array for the **cell data**, that will represent each row in the table, along with another array that defines the column names. Pass the two arrays into the constructor for a JTable and the internals of the table will create the TableModel for you.

To ensure that we also have the column headings, remember to place the **JTable inside a JScrollPane**.

```
		Object[][] cellData = {
				{"Row 1 Cell 1", "Row 1 Cell 2", "Row 1 Cell 3"},
				{"Row 2 Cell 1", "Row 2 Cell 2", "Row 2 Cell 3"},
				{"Row 3 Cell 1", "Row 3 Cell 2", "Row 3 Cell 3"}
		};
		String[] columnNames = {"Column 1", "Column 2", "Column 3"};
		JTable table = new JTable(cellData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
```

The default implementation will allow cells to be selected and modified along with resizing of column headings. If placed inside a JScrollPane, then the scroll bars will appear when appropriate.

**Refer to PlanetTable.java**

### Custom Table Model

Rather than creating 2 separate arrays to define the row data and column names, you can create your own **TableModel** by extending the **AbstractTableModel** class.

By extending this class, we only need to provide the concrete implementation for 3 methods -

- getRowCount(); // The number of rows in the table.
- getColumnCount(); // The number of columns in the table.
- getValueAt(int row, int column); // The value at the corresponding cell index.

In the getValueAt(row, column) method, you return whatever value it is that belongs in that particular cell.

```
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
```

### Sorted Tables

Another TableModel can be used to filter the data that is contained within the main TableModel. For example, if we wanted to sort or represent the data in the table in a different way, we would manipulate another TableModel rather than change the original model.

In a table sort, we would use an intermediate filter model that keeps an array of the re-arranged rows indices. This filter model contains a reference to the original table model where it can be used to do a lookup.

If we are allowing sorting, when the JTable goes to look up a value, it looks at the FilterModel's **getValueAt(...)** method. It is up to this model to then find the actual row, column index from the original data model to find the value.

```
// Within the filter model
public Object getValueAt(int row, int column) {
	// Some type of permutation is done.
	return tableModel.getValueAt(actual_row_index, column);
}
```

In this FilterModel, there is a Row object that maintains a reference to its place in the original TableModel. When the sort is called, it is the collection of these Row objects that is sorted, but since it still contains a reference to the data in the original table model, it still returns the correct values.

Therefore in the Row objects compareTo(Row anotherRow), it will need to compare to the values in the original table to determine the order.

### Cell Rendering

In a JTable you can customise the look of all the cells in a column. You can do that by overriding the following method in the **TableModel** class.

```
// In AbstractTableModel
public Class getColumnClass(int columnIndex);
```

This method describes the type of data that exists in a particular column

By default, the JTable offers default renderers for the following object types:

- ImageIcon - for columns that contain objects of type ImageIcon.class (renders the cell as an image).
- Boolean - for columns that contains objects of type Boolean.class (renders the cell as a checkbox).
- Object - for columns that contain objects of type Object.class (renders the cell as a String).

If you wish to render your own cell, then return the appropriate class name to render all the cells in that column with your cell renderer with the **getClass()** method.

```
public Class getColumnClass(int columnIndex) {
	return columns[columnIndex].getClass();
}
```

In addition to overriding the **getColumnClass(int columnIndex)** method in the AbstractTableModel, you would need to implement your own cell renderer that allows you to return a component (i.e. JPanel, JLabel, etc) that will be displayed in that cell.


```
public class MyTableCellRenderer extends JPanel implements TableCellRenderer {

	Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// Create some component and return it to be displayed in that cell.
	}	
}
```

The **getTableCellRendererComponent(...)** method is called each time a cell needs to be rendered. 

The last thing we need to do is to register the custom table cell renderer with the **JTable** so it knows which columns it needs to respond to.

```
JTable table = new JTable(tableModel);
table.setDefaultRenderer(Color.class, new MyTableCellRenderer);
```

This makes sure that the table knows to use the MyTableCellRenderer when it comes across cells of type Color.class.

**Refer to TableCellRendererTest.java**

### Cell Editing

**Refer to TableCellRenderTest.java**

To allow cell editing, the TableModel must indicate which cells are editable

```
// In the AbstractTableModel
public boolean isCellEditable(int r, int c) {....}
```

If a cell has been declared as editable and it doesn't have a renderer installed for that column it will use the **DefaultCellEditor**.

- If the cells in the column are of type Boolean.class, it will install a checkbox editor.
- If the cells in the column are anything else, it will use a text field editor.

The cell editor works by passing the value into the cell, which eventually calls -

```
// In DefaultCellEditor
field.setText(currentValue.toString());

// Then it makes a call to the table model to update 

// In the AbstractTableModel
setValueAt(...)

// And the values can be retrieved through the getCellEditorValue(); method.
// In the DefaultCellEditor
Object getCellEditorValue();
```

**Refer to TableSelectionTest.java**

