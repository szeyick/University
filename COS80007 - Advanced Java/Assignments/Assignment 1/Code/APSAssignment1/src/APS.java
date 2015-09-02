
import aps.floor.FloorPanel;
import aps.timer.APSTimer;
import javax.swing.JFrame;

/**
 * The {@link APS}
 * <p>
 * This class is responsible for running the Automatic Parking Simulator. It
 * is the program main that initialises all the components that are required
 * as part of this assignment.
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
        System.out.println("Hello World");
        APSTimer timer = new APSTimer();
        
        // For some reason Netbeans requires this to persist the timer.
        JFrame frame = new JFrame();

        frame.setName("Car Test");
        frame.setSize(400, 400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FloorPanel panel = new FloorPanel();

	frame.getContentPane().add(panel);
	frame.setVisible(true);
    }
}