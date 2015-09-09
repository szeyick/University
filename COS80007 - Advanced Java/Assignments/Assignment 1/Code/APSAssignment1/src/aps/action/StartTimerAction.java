package aps.action;

import aps.timer.IAPSTimer;

/**
 * The {@link StartTimerAction}.
 * <p>
 * This class is responsible for starting the timer.
 * <p>
 * @author szeyick
 */
public class StartTimerAction implements IAction {

        /**
     * The simulation timer.
     */ 
    private final IAPSTimer timer;
    
    /**
     * Constructor
     * @param timer - A reference to the simulation timer.
     */
    public StartTimerAction(IAPSTimer timer) {
        this.timer = timer;
    }
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void executeAction() {
        timer.startTimer();
    }
}
