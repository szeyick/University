## Swing Buttons, Labels and Modes

In this lesson we will discuss more about Swing components and the MVC (Model, View, Controller) pattern.

### Swing Labels

In Swing, the basic label that we use is the JLabel. This label is a simple component in that it has no underlying data model that you can assign advanced properties to.

The JLabel functions like a container and the label text can be aligned (like AWT label). 

By default, a JLabel has its opacity set to **false**. This means that it is invisible, so setting the background colour will not display it, we will need to set the opacity to **true** before we can see it. 

In the example below we have a simple JLabel with the basic properties that we can assign to it.

```
Container container = getContentPane(); // Retrieve the pane to set components to.

JLabel label = new JLabel("LabelName", SwingConstants.CENTER); // Default alignment for text only is left
label.setOpaque(true); // Sets the opacity allowing background colours to be visible.
label.setBackground(Color.white);

container.add(label);
```

#### JLabel Properties

For a simple JLabel we can set some other basic properties such as -

- **font and font size**
- **icons**
- **label text positioning**

```
ImageIcon image = new ImageIcon("image.gif"); // Loads the image from file.
JLabel label = new JLabel("JLabel", image, SwingConstants.RIGHT); // Default alignment with text and image is centre.
label.setVerticalTextPosition(SwingConstants.TOP);
label.setOpaque(true);
label.setBackground(Color.white);
container.add(label);
```

In regards to the label text and sizing, in the constructors string we can also use HTML formatting to set the font properties.

```
JLabel label = new JLabel("<HTML><h1><font color="red">Label Name</font></h1></HTML>");
```

#### Fields and Hot Keys

We can assign hot keys to a label that contains a text field. Pressing the hot key (alt+<key>) will force the focus to jump to the text field.

```
JPanel panel = new JPanel();
...
JLabel label = JLabel("My Label", SwingConstants.RIGHT);
label.setDisplayedMnemonic('n');

panel.add(label);
JTextField textField = new JTextField(20);

panel.add(textField);
content.add(panel); // Add the panel to the content pane.

label.setLabelFor(textField); // It should display the hot key mnemonic.
```

### Model View Controller (MVC)

The underlying architecture of Swing is based on the MVC design pattern. The idea for this is to seperate the components into 3 separate sub components.

- **Model**

The model is responsible for maintaining the data that is relevant for the components state. It is also responsible for notifying other interested components of changes through **model change events**.

- **View**

The view is responsible for representing the models data onto the display. It is kept up to date by receiving events (model change events) from the model or directly from the controller. The view should be considered as the **UI class**.

- **Controller**

The controller is responsible for the behaviour of a component. Its job is to determine whether a component should react to a given user input event (keyboard or mouse event) and what it should do with those incoming events.

### Buttons

Buttons in Swing follow the MVC pattern, meaning that the underlying state of the button is managed by a **model**. 

By default this button model is the **DefaultButtonModel** that implements the **ButtonModel** interface. It has methods that can determine the state of the button (i.e. ButtonPressed, ButtonSelected, etc).

The **DefaultButtonModel** is used across all buttons that can be pushed such as radio, checkbox and menu buttons. It is essentially an extension of the JLabel, as it contains all of the JLabel methods as well.

Whenever we create a new button, it will create a new DefaultButtonModel to assign to the button. 

```
JButton button = new JButton("Button Name");
button.getModel(); // By default retrieves the DefaultButtonModel.
```

Because the DefaultButtonModel extends the ButtonModel interface, it can be used to retrieve various properties regarding the state of the button

- isSelected
- isPressed
- mnemonic

The JButton methods to retrieve state (i.e. isSelected()) actually asks the model for the current state of the button.

### Replacing Models

The handy part of using the MVC architecture is that models can be switched in and out. For buttons, this means that the DefaultButtonModel can be replaced with another model that may provide additional data or state information.

**Note - Not all Swing components have models. Containers such as JFrame and JApplet do not have data models.**

In our JButton example, as long as the new model extends DefaultButtonModel, we can replace the existing model with our own one.

```
// Our Custom Button Model.
public class MyButtonModel extends DefaultButtonModel {
	
	// Counter to hold the number of times the button has been clicked.
	protected int count = 0;

	// Return the number of times the button has been pressed.
	public int getCount() {
		return count;
	}

	// Override the method so we can increment the count.
	public void setPressed(boolean b) {
		super.setPressed(b);
		// b is TRUE if the button is pressed, FALSE if released.
		if (b) {
			count++
		}
	}
}

// Our Custom Button.
public class MyCustomButton extends JButton {
	
	...
	public MyCustomButton(String buttonText, Icon icon) {
		super(buttonText, icon);
		setModel(new MyButtonModel());
	}

	// Return the number of times the button has been pressed.
	public int getCount() {
		return ((MyButtonModel)model).getCount();
	}
}
```

In the **getCount()** method, the model is directly accessible since it is a protected instance variable in the parent JButton class. We need to cast the model to our custom model type since the default type of the model is DefaultButtonModel that has no visibility to the **getCount()** method that we added.

The internal structure of the JButton manages the state change events as it has a **BasicButtonListener** that listens for changines to the DefaultButtonModel. When we replace the DefaultButtonModel with our custom model, the JButton stops listening to the old model and listens to our new one.

The button pressed events (press and release) update the model, which then notifies the listener who then notifies people who are interested in responding to button pressed events.

### AbstractButton

The JButton implements a button push mechanism which it inherits from an **AbstractButton** class. This abstract class serves as the parent of other types of buttons such as -

- JButton

- JToggleButton (JCheckBox, JRadioButton)

This type of button can contain two states, **selected or deselected**, which is implemented through the **setSelected(boolean b)** and retrieved through the **isSelected()** method. The JToggleButton acts as the parent of JCheckBox and JRadioButton.

- JMenuItem (JMenu (sub menu), JRadioButtonMenuItem, JCheckBoxMenuItem)

- ButtonGroup

This allows for a group of JToggleButtons to function like a single button. This means that selecting of the buttons in the group, automatically selects the chosen button and deselects every other button. It ensures that only one button is ever selected at a time from the group. 

It should be noted that only a single ActionEvent is fired when a new button is selected. It does not fire a event for the de-selection of the button.

To ensure that a group of buttons only triggers a single event, we add all the buttons to a ButtonGroup object.

```
public class FooBar {

	private ButtonGroup; 
	private JPanel buttonPanel;
	private JLabel label;
	private int DEFAULT_SELECTION = 1;

	// Constructor.
	public FooBar() {
		group = new ButtonGroup();
		buttonPanel = new Panel();
		label = new JLabel();

		addRadioButton("One", 1);
		addRadioButton("Two", 2);
		addRadioButton("Three", 3);
	}

	// Add buttons to the button group.
	public void addRadioButton(String buttonName, final int labelText) {

		boolean isSelected = (labelText == DEFAULT_SELECTION); 
		JRadioButton radioButton = new JRadioButton(buttonName, isSelected);
		group.add(radioButton);

		buttonPanel.add(radioButton);
		radioButton.addActionListener(new ActionListener() 
		{
			// Respond to action click events
			public void actionPerformed(ActionEvent event) {
				label.setText(labelText);
			}
		}); 
	}
}

```

By default, JToggleButtons are not selected, this means that if you put it into a ButtonGroup, nothing will appear to be selected, thus it needs to be explicitly defined.

The "final" before the variable type and name is to denote that it should be treated as a constant.

### CheckBoxes

A check box is essentially a box with a tick in it when it is selected and no tick when it is not. Because it is a type of JToggleButton we can use it perform actions where the properties that we want is to "turn on" and "turn off".

We can react to selection events from the checkbox by adding an **ActionListener**

```
ActionListener listener = new ActionListener() {
	
	// Override action performed to set custom behaviour
	public void actionPerformed(ActionEvent event) {
		int mode = 0;
		if (bold.isSelected()) mode += Font.BOLD;
		if (italic.isSelected()) mode += Font.ITALIC;
		label.setFont(new Font("Serif", mode, FONTSIZE)); 
	}
}

bold = new JCheckBox("Bold");
bold.addActionListener(listener);
italic = new JCheckBox("Italic");
italic.addActionListener(listener);
```

In the example above, the use of += Font.BOLD and Font.ITALIC uses bit shifting to set the correct font to use. The pattern += when adding bits together is equivelant of using an OR statement (|=), which is the **preferred way** of adding bits.

### Borders 

Borders like their namesake allows you to define a border. In Swing we have a Border and BorderFactory class that makes creating borders easier.

- BorderFactory

Only contains static methods that allows us to create different border types.

```
Border etched = BorderFactory.createEtchedBorder();
Border titled = BorderFactroy.createTitledBorder(etched, "Border Title");

panel.setBorder(titled);
```

- Border

The types of borders that can be created.

### Menus

Menus in Swing are treated like types of buttons, you click on menu and a label with a bunch of other menus (buttons) is displayed. The basic code is as follows -

```
JMenuBar menuBar = new JMenuBar();
frame.setJMenubar(menuBar);

JMenu fileMenu = new JMenu("File");
menuBar.add(fileMenu);

JMenuItem openMenuItem = new JMenuItem("Open");
openItem.addActionListener(new ActionListener() {...});

// Add Open menu under the file menu.
fileMenu.add(openMenuItem);
...
```

We can shortcut the menu creating process by calling menu.add(anAction), which will automatically create a menu item.

#### Menu Accelerators

We can add shortcut keys to invoke actions in menu items by calling the **setAccelerator(KeyEvent)** on the JMenuItem. This not only allows us to create keyboard shortcuts for menu items, but also creates a label that describes the shortcut next to the menu item itself.

#### Menu Mnemonics

In a menu, a mnemonic is the shortcut letter that allows us to access that menu item directly. It is usually denoted by underlining the particular character in the menu item, meaning that key selection of that underlined character will select the corresponding menu item.

```
JMenu myMenu = new JMenu("Test");

Action helpAction = new TestAction("Help); // TestAction extends AbstractAction
helpAction.putValue(Action.MNEMONIC_KEY, new Integer('H')); // It will underline the H in help creating the shortcut

myMenu.add(helpAction);

JMenuItem menuItem = new JMenuItem("Another Menu Item");
menuItem.setMnemonic('A');
myMenu.add(menuItem);

```
