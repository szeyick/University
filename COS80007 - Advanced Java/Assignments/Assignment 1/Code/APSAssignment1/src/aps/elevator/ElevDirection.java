package aps.elevator;

/**
 * The ElevDirection.
 * <p>
 * This enumeration class is responsible for providing the movement state of the
 * elevator.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public enum ElevDirection {

    /**
     * The elevator is moving up.
     */
    UP, 
    
    /**
     * The elevator is moving down.
     */
    DOWN, 
    
    /**
     * The elevator is not moving.
     */
    IDLE, 
    
    /**
     * The default state of the elevator.
     */ 
    DEFAULT;
}
