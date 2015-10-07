## Swing Trees and Tables

### Simple Trees

A JTree is good for displaying and navigating data that can be represented in a heirachial context. It follows the **MVC** architecture pattern meaning you can provide it a data model and the JTree component will display it for you.

```
TreeModel model = ...
JTree tree = new JTree(model);
```

The TreeModel however is an interface and not a class, therefore we must construct our own data model that implements the interface.

It is also possibel to use a **DefaultTreeModel** that takes a TreeNode element (also an interface) as part of its constructor. This model is a **DefaultMutableTreeNode**.

The **DefaultMutableTreeNode** holds a **user object** that can basically be anything that will end up being displayed by the JTree. The tree itself will render the user objects for all the nodes.

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

### Editing Trees

Is the means of adding, deleting or changing nodes or its data with user input. To be able to modify a particular node, each node will be required to be uniquely identified.

This is where the **TreePath** class is used, as it manages a sequence of node references. These references are of type **Object** as there can be a custom implementation of the TreeModel interface that does not know or contain any TreeNode.

```
TreePath getSelectionPath()
TreePath getPathForRow(int row)
```

Will both return TreePath objects from a JTree.

If the TreeNode is known then we can return the path to the node with - 

```
TreePath path = new TreePath(myNode.getPath());
```

One you have the TreePath object, you can retrieve the leaf node with

```
path.getLastPathComponent();
```

### Adding Nodes

Once you have retrieve the correct path, you can add a node to the existing leaf.

```
treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
```

This will add the new node to the path and trigger the view to update itself. Subsequently you can delete a node with -

```
treeModel.removeNodeFromParent(selectedNode);
```

It should be noted that only the **DefaultTreeModel** will automatically fire a model change event to trigger the view to redraw. If we are implementing our own TreeModel interface, we will need to call **nodeChanged(changNode)** manually.

```
tree.makeVisible(path); // Will make the new node visible.
tree.scrollPathToVisible(path); // Will expand the tree to the new node.
```

**Refer to TreeEdit.java**

### Tree Traversal

The DefaultMutableTreeNode has enumeration objects allowing to iterate through all the nodes that are the child of the currently selected one. It can use either a BFS, DFS or preorder traversal.

### Rendering Nodes

A trees look and feel can be customised (icons, fonts, background colour) that will be used by the DefaultTreeCellRenderer, changes here will affect the entire tree. The renderer class can be extended to customise each individual node.

Furthermore, implementing the **TreeCellRenderer** interface can allow custom renderers to be installed on the tree.

### Tree Events

Nodes in a tree are usually paired with some type of data display, meaning that if you click on a node, some sort of information may appear in a subsequent panel.

For this we can install a tree selection listener by implementing the **TreeSelectionListener** interface, that provides the method -

```
void valueChanged(TreeSelectionEvent event);
```

This will be triggered each time the user selects or deselects a tree node.

There is an additional parameter for the selection mode which defines which nodes can be selected.

- SINGLE_TREE_SELECTION - Selects only a single node, each selection will deselect the previous one.
- CONTIGUOUS_TREE_SELECTION - A contiguous range of nodes can be selected.
- DISCONTIGUOUS_TREE_SELECTION - A arbritrary set of nodes can be selected.

**Refer to ClassBrowserTree.java** shows the listener in action.

### JTable

Displays a 2D grid of objects, like the JTree uses a data model called the **TableModel**.

A table can easily be constructed using a 2D array of objects, and the default JTable model will wrap it into the data model.

```
Object [][] cells = ...
String [] columnNames = ...
JTable table = new JTable(cells, columnNames);
```

**Refer to PlanetTable.java**

Rather than using the default table model and creating the 2D array to add the table data, you can create a custom table model by extending the **AbstractTableModel** class.

The class only requires the implementation of 3 methods -

- getRowCount();
- getColumnCount();
- getValueAt(int row, int column);

The getValueAt(...) can be used to look up and answer given the row and column.

### Sorted Tables

A model can be used to filter the data to give the appearance that the data is sorted. It applies a filter that changes the order but does not actually change the ordering within the table model itself.

A intermediate filter model is used to change the ordering of the data within the column to be sorted. This filter model stores a reference to the actual table model.

In the instances that a JTable needs to look up a value, the filter model will find out the actual row index to get the value from the TableModel.

```
// Within the filter model
public Object getValueAt(int row, int column) {
	// Some type of permutation is done.
	return tableModel.getValueAt(actual_row_index, column);
}
```

### Cell Rendering

A JTable can customise its columns depending on the type of the objects in the column. It can do that with the method -

```
public Class getColumnClass(int columnIndex);
```

It can then use this to provide a appropriate renderer (by default)

- ImageIcon - render the type as an image
- boolean - render the cell as a check box.
- Object - render the cell as a string.

Again we can also implement our own renderer by implementing the **TableCellRenderer** interface which implements -

```
Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column);
```

Which is called each time the table needs to draw the cell.

**Refer to TableCellRendererTest.java**

### Cell Editing

**Refer to TableCellRenderTest.java**

To enable cell editing, the table model must indicate which cells are editable - 

```
public boolean isCellEditable(int r, int c) {....}
```

If a cell has been marked as editable and doesn't have a renderer installed it will install a **DefaultCellEditor** for the column.

- If the cells in the column are type boolean it will install a checkbox editor.

- If the cells in the column are type of anything else, it will install a text field editor.

The cell editor works by passing the value into the cell through - 

```
field.setText(currentValue.toString());
```

This will create the appropriate object is passed to the field and can be retrieved through the **getCellEditorValue();** method.

**Refer to TableSelectionTest.java**

