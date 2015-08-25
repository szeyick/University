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

### Rough Notes

The setToxxxx in the AffineTransform object tells the object to disregard everything currently in it.

The transform methods work as a stack, meaning the first ones declared are executed last.
