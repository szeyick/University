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
