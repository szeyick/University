## Lecture 4 - Graphics 2D Part 2

### The Story So Far

The Graphics class provides properties that allow us to control what is drawn and how it is drawn. It is a class that is usually passed in with the paint() and paintComponent() methods.

The child of the Graphics class, Graphics2D is a more complex version of the graphics class that provides additional features.

### Rendering Hints

The rendering hints class allows you to make trade offs between speed and draw quality. The RenderingHints object is passed to the Graphics class to allow it to draw things with the predefined qualities, however we usually cannot really tell the difference in the quality without inspecting it closely.

**Anti Aliasing** is one of the main properties that RenderingHints tries to take care of. The idea with anti-aliasing is to remove the "jagged" lines that appear on curved lines or shapes. This is a noticeable quality on low resolution screens, turning anti-aliasing on will allow the graphics drawing to try and colour the pixels around a shape so as to shade it to smooth out the lines but at the cost of some performance.

The result of anti-aliasing will be a wider but smoother looking line. An example of how the RenderingHints code is used is shown below -

```
...
Graphics2D g2 = (Graphics2D) g;
RenderingHints hints = new RenderingHints(null);
hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g2.setRenderingHints(hints);
...
g2.draw(shape);
```

The RenderingHints object functions as a map of key, value pairs allowing for properties to be considered before drawing takes place.

### Text

The Java2D API allows various ways to render and support text drawing. It also allows you access to all the underlying fonts that are installed on the Operating System.

- **Glyphs** - 

Are shapes that represent characters and usually have a one-to-one correspondence with text.

- **Font** - 

Are a collection of glyphs, usually a subset of the Unicode character set of a defined language.

- **Font Metrics**

Are a collection of attributes that can be used to describe the font. These attributes can include ascent, descent and leading.

### Text Rendering

Text rendering can be performed on Swing components like JTextField, JTextArea or JEditorPane.

In those Swing components, the Graphics2D object is passed into the **drawString()** method. The Graphics2D will use the current font, and do a comparison between the current string character and the corresponding glyph character. It will then render the character as the glyph character as a shape just like it would when drawing normally. 

This means the process that Graphics2D uses to render text is the same as that of shape drawing.

For further customisation, you can use the **TextLayout** object to lay out the text. Properties in the TextLayout include mixing styles, bidirectional text alyout, care, highlighting and hit testing.

The glyphs can be manipulated directly like other Graphics2D shapes.

<<<<<<< HEAD
### Drawing Text

There are two different methods for drawing text on a screen -

```
void drawString(String s, int x, int y);
void drawString(String s, float x, float y);
```

The drawString methods draws the string starting at position (x,y), acting as the base line. A full example is as follows -

```
public void paint(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	Font font = new Font("Serif", Font.PLAIN, 96);
	g2.setFont(font);
	g2.drawString("Hello World", 40, 100);

	Line2D baseline = new Line2D.Double(40, 100, 310, 100);
	g2.draw(baseline);
}
```

There are additional methods to draw Strings onto the screen using **AttributedStrings**

```
public void drawString(AttributedCharacterIterator iterator, int x, int y);
```

This renders the string in whatever has been set into the Graphics2D context. The iterator specifies a font for each character again starting at the position x,y. The iterator functions like any other iterator in Java, meaning that you can step through each character in the String object and customise it. 

Example -

```
public void paint(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	String s = "Hello World";

	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	Font serifFont = new Font("Serif", Font.PLAIN, 48);
	Font sansSerifFont = new Font("Monospaced", Font.PLAIN, 48);

	AttributedString as = new AttributedString(s);
	as.addAttribute(TextAttribute.FONT, serifFont);
	as.addAttribute(TextAttribute.FONT, sansSerifFont, 2, 5); // Change characters at index 2,3,4 to use alternative font

	g2.drawString(as, 100, 200);
}
```

### Font Family

To access all the fonts that are available for use on the operating system use -

```
String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
```

### Fonts

Fonts in Java have 3 names -

- **Family Name** - Several fonts can belong to the same family like bold or italic versions of a font.
- **Font Name** - The direct access to the font name.
- **Logical Name** - The name of the font retrieved through the **getName()** method, this is mapped to an actual font on a machine.

### Text Layout

This class is considered a more advanced version of the drawString() method as it allows you to perform more complex text display.

```
public void paint(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	String s = "Hello World";

	Font font = new Font("Serif", Font.PLAIN, 32);
	TextLayout textLayout = new TextLayout(s, font, g2.getFontRenderContext());

	textLayout.draw(g2, 40, 80);
}
```

The TextLayout allows you to draw on a different canvas rather than the normal Graphics2D. We can do fancy things with the layout including finding out the closest character to a mouse click that returns a TextHitInfo object.

A **caret** denotes the current point where a character will be inserted.

### Coordinate Transformation

When drawing occurs, all the x,y coordinates are converted into pixel positions. There are 4 transformations that take place for a shape -

- **Scale** - Increase or decrease the distance of all the points.
- **Rotate** - Rotate all the points around a fixed center point.
- **Translate** - Move all points by a fixed amount.
- **Shear** - Leave one line fixed and slide the lines parallel to it by an amount.

Mirroring a transformation involves scaling with negative values.

This type of transformation is called **affine transformation**.

The setToxxxx in the AffineTransform object tells the object to disregard everything currently in it and use the new values.

The transform methods work as a stack, meaning the first ones declared are executed last.

### Scaling

Scaling will change user coordinates (world coordinates) to device coordinates (pixels). By default there is a 1:1 scaling meaning that user coordinates are also pixels.

```
public abstract void scale(double sx, double sy);
```

The scale method in the Graphics2D class is equivelant to using **g2.transform(a)** where a is an AffineTransform object with the following values in its matrix -

```
[ sx 0  0 ]
[ 0  sy 0 ]
[ 0  0  1 ]
```

Where -

- **sx** - The amount that the x coordinates have been multiplied by to scale.
- **sy** - The amount that the y coordinates have been multiplied by to scale.

### Images

Images are represented as a rectangular array of coloured pixels. Java supports **GIF**, **PNG**, and **JPEG**. To read an image file we can use the ToolKit object

```
String name = "helloWorldImage.gif";
Image image = ToolKit.getDefaultToolkit().getImage(image);

g.drawImage(image, x, y, null);
```

The image object retrieved can be displayed with the **drawImage** method where x, y represent the top left corner where the image should begin. This method of retrieving an image will generate a seperate thread that will complete the image loading, which means that for large files, the image may not finish loading before it is redrawn. This leads to incremental rendering like on a really slow web page.

The 4th parameter in the **drawImage** method references an **ImageObserver** object that listens to the progress of the image loading. It can be useful to create progress bars or when an image render is complete.

If we only want to know when an image has completed loading we can use a **ImageTracker**.

```
Image image = ToolKit.getDefaultToolkit().getImage("HelloWorldImage.gif);
MediaTracker track = new MediaTracker(this);
tracker.addIMage(image, 0); // Where 0 is the ID for the image HelloWorldImage.gif

try {
	tracker.waitForID(0);
}
catch (InterruptedException e) {}
```

The above code will wait for the image to complete loading before proceeding.

If you want to know the size of the image, you can use -

```
int imageWidth = image.getWidth(imageObserver);
int imageHeight = image.getHeight(imageObserver);
```

The image observer is usually the current panel.

### Other Image Loading

You can use the **ImageIcon** class to load an image -

```
ImageIcon imageIcon = new ImageIcon("HelloWorldImage.gif");
Image image = imageIcon.getImage();
```

This uses the MediaTracker class internally when loading the image.

### Buffered Image

This is a subclass if Image, and allows you to access the image data directly.

BufferedImage functions much like TextLayout and Graphics2D where you draw on the BufferedImage class rather than the Graphics2D.

```
public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	Dimension d = getSize();
	BufferedImage bufferedImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);

	big = bi.createGraphics();
	....
	big.drawImage(image, x, y, null);

	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(bi, 0, 0, null);
	big.dispose();
}
```

The idea with the BufferedImage is that you draw everything you need to draw before you put it onto the Graphics2D.