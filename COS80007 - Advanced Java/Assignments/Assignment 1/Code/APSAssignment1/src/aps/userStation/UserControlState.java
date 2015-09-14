package aps.userStation;

/**
 * The {@link UserControlState}.
 * <p>
 * This enum class represents the states that the user controller can be
 * in.
 * <p>
 * @author szeyick
 */
public enum UserControlState {
    
    /**
     * The user control is idle, meaning no interaction.
     */
    IDLE,
    
    /**
     * The user control is in pickup model. This means that someone has requested
     * to pick up their vehicle.
     */ 
    PICKUP,
    
    /**
     * The user control is in drop off mode. This means that someone has dropped
     * off their car to be allocated to a bay.
     */
    DROP_OFF;
}
