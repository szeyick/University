
import aps.elevator.Elevator;
import aps.events.EventManager;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;
import aps.floor.ParkingLevelFloorPanel;
import aps.gui.MainFrame;
import aps.timer.APSClock;
import aps.timer.APSTimer;
import control.APSControl;
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
        APSTimer timer = new APSTimer();
        
        // For some reason Netbeans requires this to persist the timer.
        JFrame frame = new MainFrame(timer);
        
        APSClock clock = new APSClock();
        timer.addTimerListener(clock);
        
        APSControl control = new APSControl(timer);
    }
}