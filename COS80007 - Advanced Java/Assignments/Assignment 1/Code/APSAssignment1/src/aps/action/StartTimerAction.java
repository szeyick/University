package aps.action;

import aps.timer.APSTimer;

/**
 * The {@link StartTimerAction}.
 * <p>
 * This class is responsible for starting the timer.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class StartTimerAction implements IAction {
    
    /**
     * {@inheritDoc}
     */ 
    @Override
    public void executeAction() {
        APSTimer.getTimer().startTimer();
    }
}
