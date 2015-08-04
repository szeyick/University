## Introduction to Swing

### Why Swing?

In the beginning, Java GUI programs were written in AWT. AWT used a concept called **native peers**, which would delegate the drawing to the native code of the operating system. This would mean that depending on the OS, a AWT component could look different on each one (Windows, Linux, OSX). With Swing, we use Java GUI components that are painted on blank windows, and it is only those blank windows that are drawn with native peers (native code).

The reason that Swing was chosen over AWT is that it has a more convenient set of UI elements, less dependent on the underlying OS and will provide a more consistent look and feel across platforms (OS). Swing also allows for customisation to the look and feel (Windows, Windows Classic, Motif, Java (Metal)).

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
		setLocation(screenHeight * 4, screenWidth * 4);
		
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


## Rough Lecture Notes

: means instance, _ underlining is the class (UML notation).

JComponents define particular areas on the window (screen), rather than going through the native OS code. It works in a way to directly interact with the computers graphics card to paint onto the screen. It is painted in pixel by pixel.

The top level windows have peers because they can exist as independent components on a OS desktop.

All JComponents are containers, which themselves are composites.

The Content pane is part of the JLayeredPane, they will be painted in the order they are added, so the last content pane added to the JLayeredPane will be drawn last.

Frame.add(component), by default adds the component to the content pane without having to retrieve the content pane to add the component.

Default behaviour for flow layout is to centre components onto the second line if it overflows.

JComponent is the top level class that knows about peers.
Component is the AWT component that talks to the operating system.


