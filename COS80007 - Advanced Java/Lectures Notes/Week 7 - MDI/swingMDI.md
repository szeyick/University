## Swing Multiple Document Interface (MDI)

### Split Panes

Are used to split a component into two parts with an adjustable boundary in between, meaning that you can resize the size of each component to take up more/less space.

We can split the panes vertically and horizontally depending on the initialisation parameters and resize.

**Refer to SplitPaneTest.java**

When constructing a split pane, you define the split orientation (horizontal or vertical) with the following parameters -

```
JSplitPane.HORIZONTAL_SPLIT or JSplitPane.VERTICAL_SPLIT
```

The resulting constructor will be something like -

```
JSplitPane pane = new JSplitPane(orientation, component1Pane, component2Pane);
```

- **Split Pane Properties**

You can set additional properties to a split pane, such as -

```
splitPane.setOneTouchExpandable(true);
```

Will allow a pane to be expanded or contracted on a single click on an arrow.

You can also set the panes to continously repaint rather than only repainting when dragging stops with - 

```
splitPane.setContinuousLayout(true);
```

**Refer to SplitPaneTest.java**

### Tabbed Panes

A tabbed pane allows you to click on different tabs to view different content. The tabbed pane is first constructed, then other panes can then be added to it through the **addTab** method. Retrieving a component added to a tab pane is like accessing an element in an array, the added panes are referred to its order index that it has been added in.

In addition to adding a component to a tab pane, you can also add an icon.

Tabs can also provide the following behaviour -

- tabbedPane.insertTab() - to position a tab anywhere within the existing tab.
- tabbedPane.removeTabAt(index) - to remove a particular tab.
- tabbedPane.setSelectedIndex(index) - to display a particular tab.
- tabbedPane.addChangeListner(listener) - to register for changes in the tabbed pane selection.

**Refer to TabbedPaneTest.java**

### JInternalFrame

Swing provides components for implementing MDI programs (Multiple Document Interface).

Swing includes a desktop that is represented as a JDesktopPane where the internal frames to the desktop are represented by the JInternalFrame.

The JInternalFrames sit inside the JDesktopPane and can be opened, closed, minimised and moved within the JDesktopPane.

Furthermore, we also have a DesktopManager that can change the look and feel.

JInternalFrames are called as such as they duplicate the appearance of a frame and sit "internally" within another container (i.e. JDesktopPane).

**Refer to SimpleInternalFrame.java**

JInternalFrames are expected to be used within a JDesktopPane, so adding them to other components could raise issues.

You can add panels and such to JInternalFrames much like you would with a normal JFrame, however laying out the components is tricky.

**Refer to InternalFrameTest.java**

A JInternalFrame is the only Swing component that has **constrainted properties** called **Bound Properties**. It fires a property change event each time it has been modified. However these changes can be blocked by using a **vetoable change listener**.

**Refer to VetoingInternalFrame.java**

### JLayeredPane

Provides components the ability to be placed in specific layers, allowing for component depth to be controlled more accurately. Usually the **z-order** of a component depends on how it is shown compared to other components on that layer. This results in the overlapping effect we have seen.

Layering is managed through a numeric value, where layers with higher values are placed above layers with lower values.

You can drag elements within a JLayeredPane to translate them across the code example is as follows -

```
Point press = new Point();
...
if (dragging) {
	Component c = (Component) event.getSource();
	Point loc = c.getLocation();
	Point point = new Point();
	point.x = event.getX() + (loc.x - press.x);
	point.y = event.getY() + (loc.y - press.y);
	c.setLocation(pt.x, pt.y);
	c.getParent().repaint();
}
```

### JViewport

The cornerstone of Swing scrolling, providing a porthole which a particular region of the view is displayed.

The position of the view can be changed so different regions of the view can appear within the viewport at the same time.

**Refer to AnjinAndMariko.java and AMChange.java**

The viewport functions like a telescope providing you the view to a particular section of a view. This can be moved around giving you different sections of a view to look at. For example, looking at an image through a microscope, the microscope can be seen as the viewport and the thing you are looking at the view, moving the microscope changes what you see in the view.

### JScrollPane

Provides the ability to scroll through particular Swing components like a JTextArea, JList and JCombobox since the contents within them can be very long. A JPanel however cannot be set into a JScrollPane.

The idea here would be add the component to the scroll pane, then add the scroll pane to the content pane.

```
JTextArea textArea = new JTextArea(100, 100);
JScrollPane scrollPane = new JScrollPane(textArea);
getContentPane().add(scrollPane);
```

When the scrollpane is instantiated, a JViewport instance is created where the JTextArea is added to the viewport.

The JViewport within the scrollpane gives you a view of a section of the component the scroll pane contains (i.e. TextArea). The default behaviour of this is that the scrollbars will automatically appear if the view within the scrollbar is larger than the viewport.

The JScrollPane can also support row and column headers allowing you to put things within the scroll bar.

**Refer to Grapes.java**

### Interface Scrollable

The interface allows you to control how far you want to scroll up and down.

If you use the pack() method on a component (i.e. JPanel), the JFrame will be resized to fit the JPanel so the scrollbars will not appear.

If we implement the interface Scrollable, we have 5 methods that follow it.

- getPreferredScrollableViewportSize()

Is the size the component would want the viewport to be.

- getScrollableTracksViewportWidth() and getScrollableTracksViewportHeight()

Can control whether the scrollbars will be displayed for vertical or horizontal, you can set them to not be displayed.

- getScrollableUnitIncrement(...)

Controls how far you scroll for each mouse click on the arrow.

- getScrollableBlockIncrement(...)

Controls how far you scroll for each mouse click on the scroll bar area.

**Refer to ScrollableExample.java**

### Zooming Viewport

If you want to give the impression of zooming, usually you would need to rescale a component then change the viewport size. This would require the scaling and transform to be recomputed within the **paintComponent()** method. Thus the centre of the viewport may need to be adjusted.

When the view parameters change (zoom or scroll), the JViewport object fires a ChangeEvent, we can add a listener to listen to these changes.

When the listener is triggered, we would need to recalculate the viewport and possible force the scroll to change.

Care needs to be taken when doing this since we should only want the ChangeEvent to be called once so we do all the calculations and transforms in one go.

**Refer to GraphZ.java (Useful for Assignment 2)** 

