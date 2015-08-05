## Lab 01 - Introduction to Swing

The following contains brief notes regarding the lab conduct.

### Compiling and Running

- javac <fileName>.java - To compile.
- appletviewer <fileName.java> - To run the compilation as a applet.

Please note that for the file to be runnable, it has to not contain the package name. In this instance the following files should be run as an applet.

- TicTacToe.java
- QuadApplet.java
- ButtonApplet.java

The other files are program mains that can be used within an IDE to run through the IDE.

Furthermore, to ensure that it runs from an IDE, the **setSize(...) property of the applet will need to be set since it will be run as an application and will not read the HTML size tags.

### TicTacToe

- The TicTacToe application/applet has a small bug with how it calculates the exact cell, we lose a bit of accuracy by converting to integers so some cell selections will appear to go to the cell above rather than the desired cell.

### QuadApplet

The QuadApplet is a demonstration of how to use the GridBagLayout. The implementation demonstrates, how to display buttons in the 4 corners of a window, whilst having a panel in the middle. 

- The panel is initially not white because a JPanel by default is transparent. This can be fixed by **setOpaque(true)** to allow the background colour to be visible.

- Also be careful when re-using the GridBagConstraints object since properties that were previously set to component will added to the current component if the properties are not reset. Ensure that the properties are reset to default to ensure that unwanted properties do not filter through to subsequent components.

- Here we also play around with the gbc.weightx, gbc.weighty to ensure that the JPanel in the centre takes up the most of the extra horizontal and vertical space.

### ButtonApplet

- The ButtonApplet is a demo of how to layout buttons within a cell, how to align them to specific corners and how to have buttons take up more than 1 cell vertically or horizontally.

- The AAA and BBB buttons push away from each other because the HorizontallyExpandingButton will always occupy the full width of the cells. Because the AAA and BBB buttons are left and right aligned respectively, they will always stick to the left and right walls of their cells even though the cells are ever widening when a user resizes the window.
