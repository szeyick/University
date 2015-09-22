package aps.action;

import aps.timer.APSTimer;

/**
 * The PauseTimerAction.
 * <p>
 * This class is responsible for pausing the simulation timer.
 * <p>
 * @author szeyick
 */
public class PauseTimerAction implements IAction {
    
    /**
     * Pause the timer if the action is executed.
     */
    @Override
    public void executeAction() {
        APSTimer.getTimer().pauseTimer();
    }    
}
