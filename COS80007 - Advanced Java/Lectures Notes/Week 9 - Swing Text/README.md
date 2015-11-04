### Swing Text

All Swing text components decend from **JTextComponent** which in itself decends from **JComponent**

There are 5 types of Swing text components

- JTextArea - Supports multiple lines
- JTextField 
- JPasswordField
- JEditorPane - Supports multiple lines
- JTextPane - Supports multiple lines

The JTextArea, JTextField, JPasswordField only allow one colour and font to be displayed at a time.

The JTextArea, JTextField retain the same behaviour as their AWT parents.

### Text Components

The JEditorPane and JTextPane allowed for components within it to be styled, meaning that they can have multiple colours and multiple fonts displayed

The JEditorPane can display different types of content, including HTML and RTF.

The JTextPane can embed images and other components.

### Text Input

The JTextField allows for a single line of text to be entered and retrieved.

The JPasswordField is a child of JTextField, where characters can be echoed.

To retrieve the inputted text from a JPasswordField we use the following method call -

```
char[] getPassword();
```

The reason that the password is not returned as a String is because a String may stay in the JVM since it holds onto Strings within the internal String pool. The array should set each index to 0 once it has been read so the data cannot be retrieved.

To use a JTextField we do the following -

```
JTextField textField = new JTextField("defaultText", 20);
textField.setText("Enter more text here");
String text = textField.getText().trim();
```

The JTextField is essentially a view of a data model that lies underneath. In this instance the underlying model is just a String object which the view reads and displays.

The model for all text components implements a **Document** interface. Thus we need to ask the Document object and not the text component when we want to know when the data is changed.

To do this, we need to install a **DocumentListener** onto the text field document.

```
textField.getDocument().addDocumentListener(listener);
```

### Document Listener

The interfaces in the DocumentListener are -

- insertUpdate() - called when characters are added.
- removeUpdate() - called when characters are removed.
- changedUpdate() - called when changes are detected in formatting (not for text fields)

```
class MyListener implements DocumentListener {
	
	public void insertUpdate(DocumentEvent e) {
		Document d = e.getDocument();
	}

	public void removeUpdate(DocumentEvent e) {...}
	public void changedUpdate(DocumentEvent e) {...}
}
```

The **e.getDocument()** will return the document that has been modified.

Since we cannot get a reference to the JTextField from the document, we are required to install separate listeners for each JTextField to retrieve the changed text.

If we want to valid the text before it is entered into the JTextField we can implement our own **PlainDocument** class which takes over as the default model in the **JTextField**. To do this however we need to create our own JTextField class.

### Text Input

In JTextComponent based classes (inherited from), changes are broadcasted from the model (Document) through a **DocumentEvent** which is then received and processed by **DocumentListeners**.

The **DocumentEvent** gives the location of the change, and the kind of change.

### Text Area (JTextArea)

If we want to listen for changes in the text, we do the same thing as the JTextField and add a document listener.

More importantly however is that a JTextArea does not have scrollbars, so to have them we need to put it inside a JScrollPane, and set up the viewport views again as per usual.

It allows us to do this because the JTextArea implements the Scrollable interface.

```
JTextArea textArea = new JTextArea(10,60); // 10 lines of 60 characters
JScrollPane scrollPane = new JScrollPane(textArea);
getContentPane().add(scrollPane, "Center");
```

By default, line wrapping is off, to turn it on will make the horizontal scrollbars disapear

```
textArea.setLineWrap(true);
```

### Text Validation

It is possible to filter the text or even check it before it is assigned to the internal model of a JTextField. Here we would need to provide our own implementation of the JTextField and provide our own **PlainDocument**.

```
public class MyTextField extends JTextField {
	
	// Provide the model that the text will be assigned to.
	protected Document createDefaultModel() {
		return new MyPlainDocumentModel();
	}
}

public class MyPlainDocumentModel extends PlainDocument {
	
	// Perform some type of validation or filtering before we call super.insertString(...)
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		// Validate or do something with the input.
		super.insertString(offs, newString, a);
	}
}
```