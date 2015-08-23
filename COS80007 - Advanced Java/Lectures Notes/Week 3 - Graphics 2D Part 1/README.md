## Key Facts and Terminology

- **Swing Rendering**

The process of painting a component to display onto the screen. The paint() method, calls the paintComponent() and so forth for the current component, then its children.

- **AWT Rendering**

The process of painting a component to display onto the screen. The update() method, calls the paint() method and so forth for the current component, then its children.

- **Redrawing**

Once a panel is drawn, any sort of interaction such as moving or resizing within the panel will trigger it to be redrawn. When a redraw is required, the repaint() method is placed on the AWT event thread to await redrawing.

- **Graphics2D**

The class that allows us to draw things that can be displayed on the screen. It is a subclass of Graphics, but allows for greater functionality. In Swing, within the paint(Graphics g) and paintComponent(Graphics g), it is usually this Graphics2D object that is being passed around.

- **Double Buffering**

The process in Swing that prevents drawn components to flicker when they take too long to draw. Double buffering ensures that the image is fully available to be displayed before it is displayed onto the screen. One buffer displays the drawn image onto the screen, and the other buffer is used to draw the image. When an image is ready to be displayed, the buffers flip.

- **Rendering Hints**

A type of object to be used with the Graphics2D to allow for draw quality to be set, this can be handy when there are complex drawing to be done and you'd want to sacrfice some quality to ensure it is completely drawn and drawn fast.

- **Stroke**

A type of object to be used with the Graphics2D to allow to set the thickness and style of a line. This line is what draws the outline of the shape or thing that needs to be displayed.

- **Paint**

A type of object to be used with the Graphics2D that will allow you to define the colours and painting properties that the component will be drawn in. This paint object allows you to define fill patterns, fill, hues and other painting properties.

- **Clip**

A type of object to be used with the Graphics2D that will allow you to clip an object that is to be drawn. This means anything outside of the defined clip area will not be drawn nor displayed.

- **Transform**

A type of object to be used with the Graphics2D that will allow you to transform a shape that needs to be drawn. Transformation properties allow you to rotate, translate and apply other properties on the shape.

- **Compsite**

A type of object to be used with the Graphics2D that will allow you to define what happens with older or existing pixels that have already been drawn.

- **Shape**

A type of object to be used with the Graphics2D that will allow you to draw shapes onto the display.

- **Points**

A type of object to be used with the Graphics2D that will allow you to define points on the display. These points can be used for a variety of things, including allowing the Graphics2D to draw a line between them or to define mouse coordinates.

- **Path2D**

A type of object to be used with the Graphics2D that will allow you to draw a lined path on the display. Each move will define where the line will draw to next.

- **Areas**

A type of object to be used with the Graphics2D that will allow you to define custom shapes.

- **Transparency**

A property of the Graphics2D object when drawing something to screen. Rather than using setOpaque to define whether something is visible or invisible, adding an alpha value will allow to be semi transparent from a range between 0 and 1, where 0 is fully transparent and 0 is opaque.

This section contains brief notes from the lecture.

Graphics g in the methods paintComponent(Graphics g) is actually a Graphics2D object which allows greater functionality when drawing onto the screen.

The UML statechart, has a secion within it that allows us to define actions or states that do not fit into any of the main boxes. 
