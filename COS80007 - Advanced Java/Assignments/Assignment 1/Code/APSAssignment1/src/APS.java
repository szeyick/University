
import aps.gui.MainFrame;
import aps.control.APSControl;

/**
 * The APS
 * <p>
 * This class is responsible for running the Automatic Parking Simulator. It is
 * the entry point into the application and initialises all the components that
 * are required as part of the simulation.
 * <p>
 * @author szeyick 
 * StudentID - 1762652
 */
public class APS {

    /**
     * The program main.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        APSControl.getControl();
        new MainFrame();
    }
}
