package aps.action;

import aps.timer.APSTimer;

/**
 * The StartTimerAction.
 * <p>
 * This class is responsible for starting the timer.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class StartTimerAction implements IAction {
    
    /**
     * Start the timer if the action has been executed.
     */ 
    @Override
    public void executeAction() {
        APSTimer.getTimer().startTimer();
    }
}
