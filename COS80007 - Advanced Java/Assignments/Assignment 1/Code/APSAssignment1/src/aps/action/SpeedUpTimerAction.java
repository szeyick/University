package aps.action;

import aps.timer.APSTimer;

/**
 * The SpeedUpTimerAction.
 * <p>
 * This class is responsible for providing the user the ability to 
 * increase the speed of the simulation. The simulation can be sped
 * up in increments of 100 milliseconds. The maximum speed that the
 * timer can be triggered is 100 milliseconds, the simulation will not
 * allow for less than 100 millisecond update times.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public class SpeedUpTimerAction implements IAction {

    /**
     * Speed up the timer if the action has been executed.
     */
    @Override
    public void executeAction() {
        APSTimer.getTimer().speedUpTimer();
    } 
}
