## Introduction to Swing

### UML Notation

: means instance, _ underlining is the class (UML notation).

### Why Swing?

In the beginning, Java GUI programs were written in AWT. AWT used a concept called **native peers**, which would delegate the drawing to the native code of the operating system. This would mean that depending on the OS, a AWT component could look different on each one (Windows, Linux, OSX). With Swing, we use Java GUI components that are painted on blank windows, and it is only those blank windows that are drawn with native peers (native code).

The top level windows in Swing all have peers because they can exist as independent components on an OS desktop.

The reason that Swing was chosen over AWT is that it has a more convenient set of UI elements, less dependent on the underlying OS and will provide a more consistent look and feel across platforms (OS). Swing also allows for customisation to the look and feel (Windows, Windows Classic, Motif, Java (Metal)).

Swing components also interact directly with the graphics card, therefore allowing for its own custom look and feel. All Swing components will extend the AWT Component class, which knows about the OS.

### Rendering in Swing

When a screen is required to be drawn in Swing, it goes by the following sequence -

```
	panel -> paint()
		-> paintComponent() // paint will call the panels paintComponent to draw or fill in colours.
		-> paintChildren() 
			-> paint()
			-> paintComponent()
				-> paintChildren() 
				...
```

A panels paint will first call paintComponent() which will perform colour filling and drawing on the panel. It will then iterate through all the components that have been added to the panel with the paintChildren(), who then will call their respective paint methods and proceed to paint all the components of a panel.

### Swing Frames

In Swing, all the components are written in lightweight Java. Most of the **top level windows** will extend a AWT (native peer) component.

- JWindow extends **java.awt.Window**
- JFrame extends **java.awt.Frame** (blue top level bar)
- JDialog extends **java.awt.Dialog**
- JApplet extends **java.awt.applet.Applet** (Requires browser or appletviewer which provides a frame).
 
The following code example will demonstrate how to create a frame to appear in the centre of the screen.

```
/**
* Custom frame to display in the middle of the screen.
*/
class CenteredFrame extends JFrame {
	
	/**
	* Constructor.
	*/
	public CenteredFrame {
		setTitle("Centered Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit tk = ToolKit.getDefaultToolKit();
		Dimension dim = tk.getScreenSize();
		int screenHeight = dim.height;
		int screenWidth = dim.width;
		
		setSize(screenHeight /2, screenWidth/2);
		setLocation(screenHeight / 4, screenWidth / 4);
		
		Image img = tk.getImage("image.gif");
		setIconImage(img);
	}
}
	
/**
* Test the implementation of the CenteredFrame class.
*/
public class CenteredFrameTest {
	
	/**
	* Program Main.
	*/
	public static void main(String args[]) {
		JFrame frame = new CenteredFrame();
		frame.setVisible(true);
	}
}
```

The things to take notice of here is that our **CenteredFrame** extends JFrame rather than the AWT frame. 

### JFrame Structure

A JFrame is comprised of many different layers as illustrated below -

**Insert Layer Image**

Components (panels, buttons, etc) are added to the content pane rather than directly to the JFrame, JApplet or JRootPane. In other words, we do not add it to the top level component, we add **into** it.

There are different ways to add a component to the content pane, the most common being the two listed in the example.

```
// Method 1 - Retrieve the content pane to add the component to.
Container contentPane = frame.getContentPane(); // frame is a JFrame, JApplet, JRootPane
Component c = ...; // A JComponent (button, panel, etc)
contentPane.add(c);

// Method 2 - Add directly to the frame (The frame class automatically adds to the content pane) (Java 5+)
Component c = ...;
frame.add(c);
```

- GlassPane - Is at the front of the JFrame and functions like a modal view. When visible, it will intercept all mouse events.

- JLayeredPane - Allows components to be placed in separate layers to be drawn in a particular order. By default it contains a panel (content pane) and an optional menu bar.
 
### JComponent Inheritance Hierarchy

All J-classes inherit from JComponent which in turn inherits from AWT Component Container. The JComponents themselves provide a large range of functionality and do not need to be extended, but can be **mixed and matched to create composite objects**.

The idea that all the J-classes are containers, meaning that other J-classes can be added into it to build complex components.

As part of the JLayeredPane, components added to the ContentPane will be painted (drawn) in the order in which they are added. This means that the last component added to the content pane will be painted last. We need to be careful sometimes when we add components to the ContentPane so as to achieve the correct overlapping effect.

### Look and Feel

With AWT, the look and feel was dependent on the operating system that was running the application. With Swing, this is independent and can be customised. By default, Swing provides a **Metal** UI but can be configured to look like Motif, Windows or OSX through inbuilt references.

The entire look and feel can be changed through a call to the **UIManager**

```
String lookAndFeel = "";
// If event source or some selection
if (source == metalButton) {
	lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
}
else if (source == windowsButton) {
	lookAndFeel = "javax.swing.plaf.windows.WindowsLookAndFeel";
}
...
try {
	UIManager.setLookAndFeel(lookAndFeel);
	SwingUtilities.updateComponentTreeUI(this);
}
...
```

The code above takes a selection from some input event and updates the look and feel. The final call to **updateComponentTreeUI** will trigger all the components to repaint themselves to match the selected look and feel.

### Size and Positioning

The general principle is to use the Java layout managers to position components. Adding components without a layout manager can be a tedious process. The layout coordinates are in pixels relative to the container, meaning (0,0) is the top of the container that will store the component rather than any parent frame.

```
aContainer.setLayout(new SomeLayoutManager());
aContainer.add(component);

// rather than
aContainer.setLayout(null);
aContainer.add(aComponent);
aComponent.setBounds(250, 250, 250, 250);
```

A JComponent provides 6 methods to return information about size (e.g. getMaximum, getMinimum, getPreferred...). **Preferred** is the most important of the lot. The size however is dependent on what is in them using the layout managers rather than their exact size.

**Layout Managers** are preferred as they manage the resizing automatically and the JComponent does not have lots of setBounds(...) calls to lay out components making it easier.

### Layout Management

There are two different layout managers, **LayoutManager** and **LayoutManager2**.

- **LayoutManager** - Provides the simple layouts (FLowLayout, GridLayout) where components are added one next to the other.
- **LayoutManager2** - Provides **constraints** that contain additional information about position and sizing of a component.

An example of the constraints for LayoutManager2 is shown below -

```
...
pane.setLayout(new FlowLayout(FlowLayout.LEFT));
...
pane.add(leftButton);
...
```

The constraint is the FlowLayout.LEFT that is added to the FlowLayout constructor. It basically describes where the components should be added to (left align). The default behaviour of a FlowLayout is that it centres the components on the following line if it overflows.


### Grid Layout

Creates a grid layout with specified rows and columns. The LayoutManager2 provides a set of different constructors that allow attributes to the grid to be set.

```
GridLayout(int rows, int columns, int x, int y) // where x, y are the horizontal and vertical pixel height gaps.
GridLayout(int rows, int columns) // creates grid with specified row and columns. Horizontal and vertical gap is default 0

component.add(c); // In grid layout will fill from left -> right, top -> bottom.
```

### Border Layout

Creates a border layout where you can layout components in NORTH, EAST, WEST, SOUTH, CENTER.

```
BorderLayout(int x, int y) // Creates the border layout where x, y are the horizontal and vertical pixel gaps
BorderLayout() // Default horizontal and vertical pixel gaps are 0.

component.add(c, "North"); // Add a component c to a particular quadrant of the border layout.
```

- The height of the North and South quadrants is dependent on added components preferredSize.
- The width of the East and West quadrants is dependend on the added components preferredSize.
- **The size of the CENTER quadrant takes the remaining space after the N,E,W,S components are calculated.

### Event Handling

Event handling is automatically managed by the Java API. Components can fire **actionPerformed** events to interested parties as long as they have been added as a action listener to the component through the **addActionListener** method. It follows the **publish-subscribe** pattern.

Subclasses of AWTEvent may have access to -

- getSource() - The origin of the event.
- toString() - The trigger class of the event.
- getWhen() - When the event occured. (Only in InputEvent)
- getX(), getY() - The location of the mouseEvent (Only in MouseEvent)

The events come into the system as AWT events so it is up to the developer to figure out what the event is.

#### Event Listeners

The names of the event listeners in Swing follow a simple convention **xxxEvent** and **xxxListener**. The methods that these interfaces follow pretty obvious names, **mouseMouse(...)**, **keyPressed(...)**, **actionPerformed(...)**.

#### ActionEvent

Events that are triggered when there is some interaction with a component (JComponent, List, JTextField, JMenuItem). If the event is triggered from a button, the **getActionCommand()** method returns the buttons text (name) making it easy to determine where the event triggered from.

The ActionListener interface provides the following skeleton to recieve and react to triggered events.

```
interface ActionListener {
	
	void actionPerformed(ActionEvent evt);
}
```

The concrete implementation will simply need to fill in the **actionPerformed** method to "do something" when an action is performed.

#### ItemEvent

Is triggered when an item is selected (JComboBox, JCheckBox, List). The subsequent **ItemEvent** contains a useful getItem() method to return the selected Object.

```
interface ItemListener {
	
	void itemStateChanged(ItemEvent evt);
}
```

Again, in the concrete implementation, it is up to the developer to fill in the **itemStateChanged** method.

#### ChangeEvent

Responds to a Swing event usually from a JSlider.

```
interface ChangeListener {
	
	void stateChanged(ChangeEvent evt);
}
```

The value can be retrieved through a **getValue()** call on the slider directly.

#### MouseEvent

All components, when a mouse action is performed will fire a MouseEvent. Methods within the MouseEvent class can return specific information about the event such as the mouse position, keyboard presses (if they are held down with the mouse) and click counts.

For mouse events we have 2 types of listeners, the **MouseMotionListener** and the **MouseListener**. The **MouseMotionListener** will only respond to motion events, whereas the **MouseListener** will respond to mouse click, enter and exit events.

#### WindowListener

Will respond to interactions that are performed on entire window, including the title bar (minimise, maximise, close).

```
interface WindowListener {
	
	public void windowOpened(WindowEvent e);
	public void windowClosing(WindowEvent e);
	....
	public void windowIconified(WindowEvent e); // Minimised
	...
}
```

#### Other Events

There a bunch of other events that we can listen and respond to -

- ListSelectionEvent - For items in a JList.
- KeyEvent - For keyboard events.
- FocusEvent - For switching between text fields with Tab.
- TextEvent (AWT) / DocumentEvent (Swing) - For changes in a text component before Enter is pressed.

### Inner Classes

A class that is defined within another class. Unlike an external class, it has access to all the methods and instance variables of the outer class that it resides in. It is useful for implementing EventAdapters so it can respond to events by directly triggering the outer class.

```
public class MouseAppletTest extends JApplet {
	...
	private JTextField field;
	private MousePositionAdapter adapter = new MousePositionAdapter();
	
	// Inner class.
	private class MousePositionAdapter extends MouseMotionAdapter {
		
		// Respond to a mouse event by updating the outer class field.
		public void mouseMoved(MouseEvent evt) {
			field.setText(evt.getX() + "," + evt.getY());
		}
	}
	
	public void init() {
		panel.addMouseMotionListener(adapter);
	}
}
```

#### Anonymous Inner Class

An anonymous inner class is an inner class just without the specific class definition along with the instance variable declaration. It can be used if there is only going to be a single instance of the inner class that we do not want to re-use.

```
public class MouseAppletTest extends JApplet {
	...
	private JTextField field;
	
	public void init() {
		// Declare the anonymous inner class.
		panel.addMouseMotionListener(
			new MouseMotionAdapter() 
			{
				public void mouseMoved(MouseEvent evt) {
					field.setText(evt.getX() + "," + evt.getY());
				}
			}
		);
	}
}
```

### Applets and Applications

To make a program run as both an applet and application we can do the following -

- Create a class that extends JApplet
- In the program main, create a JFrame and add the applet instance to the frame.

```
// Applet class.
public class MyApplet extends Applet {
	...
}

// Program main,
public class ProgramMain {
	
	// Program main.
	public static void main(String args[]) {
		JApplet applet = new MyApplet();
		JFrame frame = new Frame();
		
		frame.add(applet);
		...
		frame.setVisible(true);
	}
}
```

There can possible be an issue with this as it can accidentally call **getAppletContext()** which may return a null pointer exception if it is not started within a browser.

To overcome this, we need to implement **AppletStub()** and **AppletContent()**

At the front of the JFrame, we have a **GlassPane that functions like a modal view. When it is visible (active), it will intercept all mouse events as it floats above all the other components of the JFrame.
