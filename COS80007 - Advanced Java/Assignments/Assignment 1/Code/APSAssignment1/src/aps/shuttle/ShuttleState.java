package aps.shuttle;

/**
 * The {@link ShuttleState}.
 * <p>
 * This enumerator is responsible for providing the states that the shuttle
 * can be in.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public enum ShuttleState {
    
    /**
     * The shuttle is deployed from the elevator.
     */ 
    DEPLOYING, 
    
    /**
     * The shuttle is locked to the elevator.
     */
    LOCKED, 
    
    /**
     * The shuttle is unlocked from the elevator.
     */
    UNLOCKED, 
    
    /**
     * The shuttle has returned to the elevator.
     */ 
    RETURNED, 
    
    /**
     * The shuttle is currently idle awaiting instruction.
     */ 
    IDLE;
}
