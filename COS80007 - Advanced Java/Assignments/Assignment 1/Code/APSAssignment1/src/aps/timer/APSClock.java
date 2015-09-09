/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.timer;

/**
 *
 * @author szeyick
 */
public class APSClock implements IAPSTimerListener {
    
        /** Simulations are assumed to start at this time of day.
     This is added for display only -- internally time is done in ms
     starting from 0. */
    public static final int HOUR_ZERO = 8;
    
    private long t = 0;
    
    public APSClock() {
    }

    /**
     * Returns an 12-char string representing the simulated time of day.
     * @param t the simulated time (from 8am, in milliseconds)
     * @return eg "13:00:59.009"
     */
    public static String formattedTime(long t) {
        int ms = (int)(t % 60000);
        long r = t / 60000;
        int min = (int)(r % 60);
        r = r / 60;
        int hr = HOUR_ZERO + (int)(r % 24);
        return String.format("%02d:%02d:%06.3f", hr, min, ms/1000.f);
    }
    
    /**
     * Returns the current clock value in milliseconds.
     * This method is for use by very few objects that do not maintain their
     * own copy of the value, incremented by update() calls.
     */
    long getCurSimTime() {
        return t;
    }

    /**
     * Returns an 12-char string representing the current simulated time of day.
     * @return eg "13:00:59.009"
     */
    public String toString() {
        return formattedTime(t);
    }
        
    /**
     * Update the internal time. Adds dt to t.
     * @param dt increment of simulated time in millisec
     */
    @Override
    public void update(long dt) {
        t += dt;
        if (t%1000 == 0) {
            // System.out.println(this);
        }
    }
}
