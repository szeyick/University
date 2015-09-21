package aps.action;

import aps.timer.APSTimer;

/**
 * The {@link SlowDownTimerAction}.
 * <p>
 * This class is responsible for allowing a user to slow down the simulation.
 * When this action is invoked, the controlling timer that triggers all the
 * components in the simulation to update will be slowed down by 100 milliseconds.
 * There is no maximum time that the simulation can be slowed down.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class SlowDownTimerAction implements IAction {

    /**
     * {@inheritDoc 
     */ 
    @Override
    public void executeAction() {
        APSTimer.getTimer().slowDownTimer();
    }  
}
