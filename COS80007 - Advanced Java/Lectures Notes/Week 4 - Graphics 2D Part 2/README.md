## Key Facts and Terminology

- **Rendering Hints**

A class that allows you to provide properties to speed up or slow down the drawing by defining the quality of the draw.

- **Glyphs**

Shapes that have a 1:1 association with text.

- **Font**

Is a collection of glyphs, usually a subset of the Unicode character set for a language.

- **Font Metrics**

A collection of attributes that you can use to describe a font.

- **Text Rendering**

Drawing of text can be done on a variety of Swing Components like a JTextFIeld, JTextArea or JEditorPane.

- **Text Drawing**

Much like how shapes are drawn with the Graphics2D draw method, displaying text on screen can be done with the Graphics2D.drawString() method.

- **AttributedCharacterIterator**

A special iterator that is used with the drawString() method to allow for customisation of each individual character in a string.

- **Font Family**

A collection of all the available fonts in the system grouped by a particular category like Bold, Italics, etc.

- **Font Name**

The name of the font, can be used to access the font directly.

- **Logical Name**

The name of the font that can be used to retrieve a particular font from the machine.

- **Text Layout**

A special type of layout that is used specifically for drawing text, considered as a more advanced version of the drawString() method.

- **Caret**

The current point where a character will be drawn.

- **Coordinate Transformation**

The act of getting a shape or text and performing some sort of transformation with it such as scaling, rotating, translating (moving) and shearing.

 - **Images**

 Images are a rectangular array of coloured pixels and can be loaded from the system, comprised of **GIF**, **PNG** and **JPEG**. Retrieved by using the Toolkit object.

 - **Image Observer**

 A special kind of listener that responds when an image is loading or finished loading.

- **Media Tracker**

Another type of listener that will stop the system until an image has completed loading.

- **Image Icon**

An easier way to load an image from the file system. Internally it uses the MediaTracker object to monitor how the file is loading.

- **Buffered Image**

A subclass of Image that allows you to access image data directly. It functions like its own canvas allowing you to draw things on it before passing it onto the Graphics2D to draw onto the display. Sort of like an in memory buffer.
