##Lab 03 - Introduction to Java2D

### Graphics2D

- Adding Gradient

The following code snippet allows you to add a gradient that slops from the top right to bottom left of the screen. The first colour defines the colour that will be presented at the top coordinates, and the second colour will be displayed at the bottom coordinates. The Graphics2D internals will manage the gradient change of the colour.

Another hint here would be that we use the g2.fill(...) rather than the g2.draw(...). The reason for this is that by drawing, only the outline will be decorated with this gradient paint colour, whereas the desired result is to actually fill in the shape with the gradient colour. 

The code is as follows -

```
public void paintComponent(Graphics g) {

	Graphics2D g2 = (Graphics2D) g;

	// Create a GradientPaint object that will transition from white to grey.
	// We also set the paint colour to the Graphics object before we draw.
    Paint paint = new GradientPaint((float)getWidth(), 0, Color.WHITE, 0, (float) getHeight(), Color.GRAY);
    g2.setPaint(paint);
    
    // Fill the screen space with a rectangle, do not use draw since it will outline the rectangle rather than fill.
    g2.fill(new Rectangle2D.Float(0, 0, (float)getWidth(), (float)getHeight()));

    ...Rest of code
}
```

The idea to do this before the rest of the code is that we want the rectangle background to be painted first, then everything else painted after will sit on top of it.

- Bouncing Ball

The bouncing ball example uses a thread that constantly moves an object around the screen. The parameter SLEEP_TIME will determine how far the ball is moved before it is redrawn, the longer the SLEEP_TIME the further away the draw occurs, this results in a non-smooth flashy sort of draw.

The simulation can be slowed down as the ball position is recalculated based on the defined speed of gravity at 9800 cm/sec/sec. Lowering this value will make the ball seem like it is moving slower.