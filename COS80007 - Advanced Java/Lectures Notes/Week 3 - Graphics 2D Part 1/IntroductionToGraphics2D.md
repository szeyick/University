## Introduction to Graphics 2D

### Swing Rendering

As we have learnt from previous lessons, whenever a panel is required to be drawn, its paint() method is called. This subsequently calls paintComponent() which goes onto draw all the different little components that make up the panel, including its children, grandchildren and so forth.

### AWT Rendering

The AWT rendering pipeline is a little different to that of Swing.

Swing renders components through paint() -> paintComponent() -> paintChildren() and continues through all the components.

AWT renders components through update() -> paint() -> **call OS to render** -> update() -> etc (it thrn goes through its children.).

### Redrawing

Once a panel is drawn, certain system or user driven events will require the panel to redrawn. A redraw maybe required from some of the following events -

- Panel resized or moved.
- User interaction with panel or components within panel.
- Panel changed by another panel.

When a redraw is required the **repaint()** method is called. This doesn't trigger the redraw to happen immediately though as the request is placed on the **AWT event thread** to execute when it can.

### Graphics 2D

In Swing, the Graphics class provides the drawing operations. An instance of the Graphics object is passed through the **paint(Graphics g)** and **paintComponent(Graphics g)** methods. It automatically allows you to draw onto the drawing area (i.e. panel), allowing you to set colours, define shapes to be drawn and other functions.

The Graphics class provides the drawing operations that includes methods to draw and fill shapes - 

- **g.fillOval(x,y,w,h)**
- **g.drawLine(x,y,x1,y1)**

Swing provides a more robust and complex class called **Graphics2D**. This is a child of the Graphics class so it inherits all of its parents methods along with providing a few of its own. In fact, the Graphics object that is passed through the **paint** and **paintComponent** methods is in fact an instance of a Graphics2D object, so to use the additional methods, we will need to cast the incoming Graphics object to a Graphics2D.

In the Graphics2D object, all the shapes we can draw require class objects of the shapes to be passed in and are not called through methods.

For any Swing component, if we want to change how it draws, we need to **override the paintComponent() method**. This should be the case for all times you want to apply specific drawing to a Swing component, with the exception of the **content pane for a JApplet**. In the case of a JApplet, we only need to override the **paint()**.

However it is only in that case where we should override **paint()** rather than **paintComponent()**.

### Double Buffering

Double buffering is the concept of preventing graphics to flicker when being displayed. This usually happens when the components to be drawn take longer to draw than the refresh rate of the screen. This results in the screen appearing to flicker as the graphics cannot be drawn fast enough. With double buffering, the idea is that the drawing is done on one buffer, whilst the presentation is displayed on another. This allows additional time for drawing to take place and also for the complete image to be displayed rather than a half baked one.

### Rendering Pipeline (Sequence)

To draw a shape onto the screen we usually go through the following steps -

- **Firstly access to the Graphics2D object in the paintComponent(Graphics g)**

```
public void paintComponent(Graphics g) {
	
	// Fill background with whatever was default set.
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g; // Cast
}
```

- **Use g2d.setRenderingHints(...) to set hints to increase/decrease draw quality to speed up draw time.**

```
RenderingHints hints = ...;
g2d.setRenderingHints(hints);
```

- **Use g2d.setStroke(...) to increase/decrease the thickness and style of a shapes line**

```
Stroke stroke = ...;
g2d.setStroke(stroke)
```

- **Change the colour the shape is drawn in by calling g2d.setPaint(...). Set fill properties, hues or patterns**

```
Paint paint = ...;
g2d.setPaint(paint);
```

- **Use setClip(...) to set the clipping region, this will cut off drawing outside of the clipping zone.**

```
Shape clip = ...;
g2d.clip(clip);
```

- **Use setTransform(...) to transform from user space to device space. This includes translation, rotation, sheer.**.

```
AffineTransform transform = ...;
g2d.transform(transform);
```

- **Use setComposite(...) to set a rule that describes how to combine new pixels with existing pixels (i.e. fade)**

```
Composite composite = ...;
g2d.setComposite(composite);
```

- **Create the shape that you want to draw by using objects that implement the Shape interface**

```
Shape shape = ...;
g2d.draw(shape); // Draws the shape outline.
g2d.fill(shape); // Fills the interior of the shape.
```

### Points

The **Point2D** represents a Point in user space. It is an abstract class that has two public inner classes **Point2D.Float** and **Point2D.Double** that offer differing precision when it comes to selecting a point.

If you want to only use integer precision when defining a point, you can use the AWT **Point** class.

### Shapes

The Shapes interface and subsequent concrete classes, replace the methods that were provided with the original Graphics class. These shape objects can be passed to the Graphics2D object to draw specific shapes. This includes

- Line2D
- Rectangle2D
- RoundRectangle2D
- Ellipse2D
- Arc2D
- QuadCurve2D
- CubicCurve2D

The coordinate system used with those classes is in pixels, so it usually defines the location, width and height of the shapes to draw.

The **coordinates of the shapes are drawn in user space**, and the **default transform assumes its moving in pixels**.

### Path2D

Path2D is a special implementation of the Shape class that allows us to define a set of points in which it will draw the points. The object allows us to essentially define a dot to dot, that the Graphics2D object will eventually trace.

```
Path2D.Float path = new Path2D.Float();
path.moveTo(10,20);
path.lineTo(10,20); // This is how to draw a line between 2 points.
path.moveTo(20,40);
path.lineTo(30,20); // This is how to draw a line between 2 points.
path.moveTo(30,60);

g2d.draw(path);
```

The Path2D object provides different methods to allow us to draw different types of lines to the next point, **quadTo**, **curveTo**. 

Lastly if you want to close the path, call **closePath** so it will join the first point with the last point. However it should be noted that the path object does not need to be closed.

Also if you want to add other random shapes to the Path2D object, you can through the **append(Shape)** method. It allows you to add a shape to the end of the path.

```
path.append(curve, true); // True if the new shape should be connected to the last point.
path.append(diamond, false); // False if the new shape should not be connected to the last point.
```

### Areas

Since shapes can be things other than just squares and circles, we can use the Area object to define complex shapes. It provides 4 methods that allow us to construct the Area -

- add
- subtract
- intersect
- exclusiveOr

### Strokes

Graphics2D will draw the outline of the shape with the default Stroke. This is a solid line that is 1 pixel wide. The Stroke class allows us to change these properties to allow us to draw thicker or dotted lines. The API provides us with a **BasicStroke** object that allows us to provide different widths, line types (dotted or solid) and ways to join the lines.

```
BasicStroke(float width, int cap, int joint, int miterlimit, float[] dash, float dash_phase);

```
**miterlimit** defines the number of pixels for the miterjoin.
**dash_phase** defines the offset in pixels from the beginning of the dash that drawing the line starts.

### Paint

The Paint interface allows you to paint in (fill) a shape. You pass the concrete Paint to the setPaint(...) method to do this. There are three types of Paint classes.

- Color (fills in one colour)
- GradientPaint (blends from one colour to another)
- TexturePaint (fills with image)

### Transparency

When we fill in a shape, we can also set it to a certain level of transparency. We have learnt previously that we can display or not display a background colour of a panel by calling the **setOpaque(...)** method. Setting this to true will allow the background of the panel to be displayed in the set colour.

We also have this property when it comes to filling shapes. The objects that we add to Graphics2D (shapes), can be partially transparent, allowing us to see what is under it. This is done through the setting of the **alpha** value, where **0 means fully transparent and 1 means fully opaque**.

