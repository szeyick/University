## Swing Multiple Document Interface (MDI)

### Split Panes

A split pane is a way to divide a component into two parts with a boundary that can be adjusted. This means that each part can be resized to take up more or less space.

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

- tabbedPane.insertTab(...) - Add a component (i.e. JPanel) to any index within the JTabbedPane.
- tabbedPane.addTab(...) - Add a component (i.e. JPanel) to the last index of the JTabbedPane.
- tabbedPane.removeTabAt(index) - to remove a particular tab.
- tabbedPane.setSelectedIndex(index) - to display a particular tab.
- tabbedPane.addChangeListener(listener) - to register for changes in the tabbed pane selection.

**To respond to tab selection events, you can implement the ChangeListener class and the state changed method**

```
	private class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			int selectedIndex = tabbedPane.getSelectedIndex();
			label.setText("Selected tab: " + selectedIndex);
			
		}
	}
```

**Refer to TabbedPaneTest.java**

### JInternalFrame

Swing provides components for implementing MDI programs (Multiple Document Interface).

It provides a desktop that is represented as a JDesktopPane, this functions as a main window, where internal frames are able to exist within the JDesktopPane as JInternalFrames. All the programs that we have written till this point have had a single frame, with a desktop, we can have multiple frames within the same application represented as a JInternalFrame. 

The JInternalFrame functions the same as a normal JFrame, as it still maintians the open, close, minimise and maximise icons, just that they exist within the deskop.

Furthermore, we also have a DesktopManager that can change the look and feel.

**Refer to SimpleInternalFrame.java**

JInternalFrames are expected to be used within a JDesktopPane, so adding them to other components could raise issues.

You can add panels and such to JInternalFrames much like you would with a normal JFrame, however laying out the components is tricky.

**Refer to InternalFrameTest.java**

A JInternalFrame is the only Swing component that has **constrained properties** called **BoundProperties**. Whenever these properties change, it will fire a **propertyChangeEvent** which can be accessed when we implement a **VetoableChangeListener**.

This event is usually triggered, whenever the maximise, close, icon, selected properties are selected. It allows for the change listener to do something before the property is fully changed. For example, if the close (x) was pressed, it would notify the change listeners first before closing, giving a change for a listener to do something before it closes (i.e. confirm exit, save, etc).

If you want the listener to veto the selection, then it will through a PropertyVetoException.

- **Calling pack() on a frame allows for it to take its size from the total of its child components rather than explicitly defining it.**

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

The Viewport functions like a window, providing you with a view of a particular section of whatever it is holding within it. 

For example, an image might be placed inside the viewport, but the image is way larger than the viewport can display, so rather than scaling the image to fit, the viewport only gives you a section of the image to look at. You can move the viewport around to see other parts of the large image, but adjusting the viewports position with the following code -

```
	/**
	 * Move the viewport around depending on the user selection.
	 * @param event - The button event.
	 */
	private static void moveViewport(ActionEvent event) {
		String buttonName = event.getActionCommand();
		Point viewportPoint = viewport.getViewPosition();
		switch (buttonName) {
		
		case "Down" :
			viewportPoint.y += 10;
			viewport.setViewPosition(viewportPoint);
			break;
		case "Up" :
			viewportPoint.y -= 10;
			viewport.setViewPosition(viewportPoint);
		default :
			break;
		}
	}
```

All we are doing is updating the position of the viewport so it looks at another part of whatever is within it. 

Basically a Viewport is **another type of container**, meaning that you need to put components within it to make use of it, then update its view position to see other parts of the larger component within.

### JScrollPane

Provides the ability to scroll through particular Swing components like a JTextArea, JList and JCombobox since the contents within them can be very long. A **JPanel however cannot be set into a JScrollPane**.

The idea here would be add the component to the scroll pane, then add the scroll pane to the content pane.

```
JTextArea textArea = new JTextArea(100, 100);
JScrollPane scrollPane = new JScrollPane(textArea);
getContentPane().add(scrollPane);
```

A JScrollPane can be thought of as a type of Viewport, but with scrolling bars. Thus when a JScrollPane is created, and a component is added to it, it will automatically create an instance of a JViewport. The scrollbars will appear when the view (component inside the scrollpane) is larger than the viewport.

The JViewport within the scrollpane gives you a view of a section of the component the scroll pane contains (i.e. TextArea). The default behaviour of this is that the scrollbars will automatically appear if the view within the scrollbar is larger than the viewport.

Like with the JViewport, when we add components to the JScrollbar, we need to specify the **setViewportView** so the scrollbar knows what it is looking at.

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

If you set the values to **true**, it will disable the scrollbars being displayed on that axis, no matter how small you make the viewport

- getScrollableUnitIncrement(...)

Controls how far you scroll for each mouse click on the arrow.

- getScrollableBlockIncrement(...)

Controls how far you scroll for each mouse click on the scroll bar area.

**Refer to ScrollableExample.java**

### Zooming Viewport

If we want to give the impression of zooming, we would usually rescale a component and change the size of the viewport. Doing this would require
the scaling and transform to be done within the **paintComponent()** method. Doing so may also change the centre of the viewport.

Another method to handle zooming is to create custom scrollbars, and add a **AdjustmentListener()** to it. Each time the scrollbar is clicked, it will trigger this listener that will recompute the location and re-draw the content within the panel. It will update the viewport also.

Care needs to be taken when doing this since we should only want the ChangeEvent to be called once so we do all the calculations and transforms in one go.

**Refer to GraphZ.java (Useful for Assignment 2)** 

