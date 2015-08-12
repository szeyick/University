## Key Facts and Terminology

- **JLabel Opacity**

By default, JLabel backgrounds are invisible. The property **setOpaque(...)** needs to be set for the background colours to be visible.

- **JLabel Properties**

A JLabel can have different properties set to it. **Font Position and Size**, **Icons** and **Label Text Positioning (only the label text)**.

Furthermore the label string can be defined by using HTML formatting.

- **Fields and Hot Keys**

Labels that contain a text field can have a hot key assigned to it. If the associated key is pressed, the focus will jump to the field.

- **MVC (Model View Control)**

The underlying architecture of Swing, designed to split the component into three separate sub components.

- **MVC (Model)**

The model is responsible for maintaining the state of the component and other data. If data in the model changes, it fires a model change event to notify the view to change the UI.

- **MVC (View)**

Responsible for representing the models data. The view is kept up to date by listening to changes in the model.

- **MVC (Controller)**

Responsible for the controlling the behaviour of the component. It determines if it should react to a user input event and what it should do with it.

- **JButtons**

Follows the MVC pattern meaning that it also contains a model. The model can be switched out with a custom model as long as it also extends the **DefaultButtonModel**.

- **AbstractButton**

Most button types in Swing (JButton, JToggleButton, JMenuItem, ButtonGroup) inherit from AbstractButton. This allows for them all to contain models that can react to state changes.

- **CheckBoxes**

A type of JToggleButton that has 2 states, selected and not selected. The model will trigger events when it is selected and not selected.

- **Borders**

Allows to create borders around panels. Generally uses the Border and BorderFactory classes to create displayable borders.

- **Menus**

Are a type of button, it essentially displays a panel of other menus when selected. Buttons can have accelerators installed on them so they can respond to keyboard shortcuts so the user isn't required to select through a sequence of 

- **Bit Addition**

The pattern += when adding bits together can be done using |= (OR) (Preferred Way)

- **Final Modifier**

The "final" before the variable type and name is to denote that it should be treated as a constant (e.g final int someInt = 3;)