## Layout Managers

The following contains a brief overview of the different layout managers that are available in Swing.

### BoxLayout

Allows for components to be added horizontally or vertically to a single row or column. The horizontal layout is similar to flow layout, but the components do not wrap when one row is full.

```
  // Constructor
  BoxLayout(Container c, int axis);
  
  e.g: panel.setLayout(panel, BoxLayout.Y_AXIS);
```

Where:
- c - The container that needs components laid out.
- axis - The direction the components are to be laid out (BoxLayout.X_AXIS, BoxLayout.Y_AXIS)

In the BoxLayout, the components that we put inside the container can be aligned within its row/column. The default X alignment is left, and can contain a value between 0.0 -> 1.0, where 1.0 is right align.

```
JButton myButton = new JButton("Button");
myButton.setAlignmentX(1.0f);
panel.add(myButton);
```

#### Box Layout Spacing

The spacing of the components is dependent of its neighbours. We can add spacing, by adding an empty border around the components or by inserting invisible components in between.

For the BoxLayout we have helper methods:

- Box.createHorizontalGlue();
- Box.createVerticalGlue(); // Spacing between components will change if container is resized.
- Box.createVerticalStrut(15); // Constant Vertical spacing of 15 pixels
- Box.Filler(minSize, prefSize, maxSize);

**Add Image**

### GridBagLayout

It extends GridLayout to also use constraints. The grid is comprised of equal rectangular pieces (like a brick wall) and places a component in each of those pieces.

The constraints here is called a **GridBagConstraints**, and is an object that will be supplied along with each component to correct its spacing.

```
JFrame frame = new Frame();
Container container = frame.getContentPane();

container.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

// Add constraints for each component
gbc.insets = new Insets(2,2,2,2);
```

The **insets** constraint will add exterior padding around a component. If you use negative values, it will force the component to be sized larger than the cell it is in.

#### Alignment to a GridBag

```
// Place a component in column 0,0 (top left)
gbc.gridx = 0;
gbc.gridy = 0;

// Increase a components width and heigh by 10 pixels (5 on each side). It adds **interior** padding, increasing the preferred size of a component.

gbc.ipadx = 5;
gbc.ipady = 5;

// When we add a component with the grid bag constraint, we can immediately re-use the gbc instance variable as the constraints are coped (cloned).

container.add(new JButton("Another Button"), gbc);
gbc.gridx = ...;

// Specify how much extra space a component can occupy in its cell. The weight values range from 0.0 -> 1.0, and specifies both vertical and horizontal space. The default value is 0.0.

gbc.weightx = 1.0;
gbc.weighty = 1.0;

// We can also specify the number of cells a component can expand into, both horizontally and vertically. The below example will state that the component will occupy 2 columns and 1 row.

gbc.gridwidth = 2;
gbc.gridheight = 1;

// We can also specify how a component is aligned within a cell, much like how a border layout works. There are 9 possible alignments, NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, CENTER. The default is CENTER.

gbc.anchor = SOUTHEAST;

// We can also have a component fill out the entire cell, either NONE (default), HORIZONTAL, VERTICAL, BOTH, which will take out the remaining space in the cell.

gbc.fill = BOTH;
```

