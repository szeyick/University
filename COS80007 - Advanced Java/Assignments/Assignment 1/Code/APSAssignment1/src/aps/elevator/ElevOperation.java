package aps.elevator;

/**
 * The ElevOperation.
 * <p>
 * This enumerator is responsible for providing the current operation that the
 * elevator is conducting in relation to its sub components. A sub component of
 * the elevator can be the elevator door or shuttle. This class will
 * transfer state depending on the current operation of the sub components.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public enum ElevOperation {

    /**
     * The elevator door is opening.
     */ 
    OPENING_DOOR, 
    
    /**
     * The elevator door is closing.
     */
    CLOSING_DOOR, 
    
    /**
     * The elevator is moving.
     */ 
    MOVING, 
    
    /**
     * The elevator has arrived at its destination floor.
     */
    ARRIVED_AT_FLOOR, 
    
    /**
     * The elevator is idle, awaiting instructions.
     */
    IDLE, 
    
    /**
     * The elevator has deployed the shuttle.
     */
    DEPLOY_SHUTTLE, 
    
    /**
     * The elevator is being reset to its default state.
     */
    RESET;
}
