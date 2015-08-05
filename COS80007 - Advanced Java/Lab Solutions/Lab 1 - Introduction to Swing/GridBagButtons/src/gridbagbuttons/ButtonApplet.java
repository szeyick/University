// ButtonApplet.java
//<applet code=ButtonApplet.class width=300 height=180> </applet>
package gridbagbuttons;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JApplet;
import javax.swing.JButton;

/**
 * The {@link ButtonApplet}
 * <p>
 * This class is responsible for implementing the contents of the
 * frame. It comprises of a series of buttons that are laid out
 * in the GridBagLayout to demonstrate how the grid bag layout
 * manager is used.
 * </p>
 * <p>
 * <b>Warning: </b> It should be noted here that because we re-use
 * the {@link GridBagConstraints} object, that the parameters set
 * will need to be cleared each time, otherwise the next component
 * that is set with the same GBC will take on the previously set
 * parameters.
 * </p>
 * @author szeyick
 */
public class ButtonApplet extends JApplet {
    
    // Buttons to add to the layout. 
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    
    /**
     * Initialise the Applet.
     * <p>
     * This method will initialise the components and
     * add them to the ContentPane.
     **/
    @Override
    public void init() {
        // Declare the GridBagConstraints instance variable.
        GridBagConstraints gbc = new GridBagConstraints();
        Container pane = getContentPane();
        setSize(500, 200);
        
        // Use Grid Bag Layout.
        pane.setLayout(new GridBagLayout());
     
        // Create and set the properties for the first button.
        button1 = new JButton("HorizontalButton");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Occupy the space of 2 cells (wide).
        gbc.gridheight = 1; 
        gbc.weightx = 1; // Occupy the additional horizontal space.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill out the remaining horizontal space.
        pane.add(button1, gbc);
        
        // Create and set the properties for the second button.
        button3 = new JButton("AAA");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset the component to only occupy 1 cell.
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE; // Reset the fill to default.
        gbc.anchor = GridBagConstraints.WEST; // Left align the component in the cell.
        pane.add(button3, gbc);
        
        // Create and set the properties for the third button.
        button4 = new JButton("BBB");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Right align the component in the cell.
        pane.add(button4, gbc);

        // Create and set the properties for the fourth button.        
        button2 = new JButton("LargeRectangleButton");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL; // Fill the remaining horizontal space.
        gbc.anchor = GridBagConstraints.WEST; // Left align the component in its cell.
        pane.add(button2, gbc);
    }
}
