
import aps.gui.MainFrame;
import aps.timer.APSClock;
import aps.timer.APSTimer;
import control.APSControl;

/**
 * The {@link APS}
 * <p>
 * This class is responsible for running the Automatic Parking Simulator. It 
 * is the entry point into the application and initialises all the components
 * that are required as part of the simulation.
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
        APSTimer timer = APSTimer.getTimer();
        APSControl.getControl();
        
        new MainFrame();
        
        APSClock clock = new APSClock();
        timer.addTimerListener(clock);
    }
}