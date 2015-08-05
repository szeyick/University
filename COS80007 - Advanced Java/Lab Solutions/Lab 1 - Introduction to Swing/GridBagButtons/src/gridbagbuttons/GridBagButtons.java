package gridbagbuttons;

import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * The {@link GridBagButtons}.
 * <p>
 * This class represents the program main. It creates an 
 * instance of the {@link ButtonApplet} and sets it into
 * a JFrame so that it can be run as an application rather
 * than a Applet.
 * </p>
 * @author szeyick
 */
public class GridBagButtons {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JApplet buttonApplet = new ButtonApplet();
        buttonApplet.init();
        
        JFrame frame = new JFrame();
        frame.setSize(buttonApplet.getSize());
        
        frame.add(buttonApplet);
        frame.setVisible(true); 
    }
}
