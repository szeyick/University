package control;

import aps.timer.IAPSTimer;
import aps.timer.IAPSTimerListener;

/**
 * The {@link APSControl}.
 * <p>
 * This class is responsible for managing the sequence of events in the 
 * Automatic Parking Simulator. It responds to the events received
 * from the {@link EventManager} to start the adding or removal of a car
 * from the car park.
 * <p>
 * @author szeyick
 */
public class APSControl implements IAPSTimerListener {

    /**
     * When the timer is here, check to see if the system is ready
     * to take the next request popped off the queue.
     */
    @Override
    public void update(long dt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
