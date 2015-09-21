package aps.action;

import aps.timer.APSTimer;

/**
 * The {@link PauseTimerAction}.
 * <p>
 * This class is responsible for pausing the simulation timer.
 * <p>
 * @author szeyick
 */
public class PauseTimerAction implements IAction {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void executeAction() {
        APSTimer.getTimer().pauseTimer();
    }    
}
