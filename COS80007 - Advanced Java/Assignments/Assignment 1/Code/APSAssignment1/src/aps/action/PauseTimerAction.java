/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.action;

import aps.timer.IAPSTimer;

/**
 * The {@link PauseTimerAction}.
 * <p>
 * This class is responsible for pausing the simulation timer.
 * <p>
 * @author szeyick
 */
public class PauseTimerAction implements IAction {

    /**
     * The simulation timer.
     */ 
    private final IAPSTimer timer;
    
    /**
     * Constructor
     * @param timer - A reference to the simulation timer.
     */
    public PauseTimerAction(IAPSTimer timer) {
        this.timer = timer;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void executeAction() {
        timer.pauseTimer();
    }    
}
