package aps.shuttle;

/**
 * The TrolleyState.
 * <p>
 * This enumerator is responsible for providing the states the the trolley
 * can be in.
 * <p>
 * @author szeyick
 * StudentID - 1763652
 */
public enum TrolleyState {
    
    /**
     * The trolley is docked with the shuttle.
     */ 
    DOCKED_SHUTTLE, 
    
    /**
     * The trolley is currently deployed.
     */
    DEPLOYING, 
    
    /**
     * The trolley is currently returning to the shuttle.
     */
    RETURNING, 
    
    /**
     * The shuttle is locking itself to the car.
     */
    LOCKING, 
    
    /**
     * The shuttle is locked with the car.
     */
    LOCKED;
    
}
