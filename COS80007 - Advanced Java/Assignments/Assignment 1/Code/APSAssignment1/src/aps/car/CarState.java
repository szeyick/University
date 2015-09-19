package aps.car;

/**
 * The {@link CarState}.
 * <p>
 * This enumeration class represents the states that the car can be
 * in within the simulation. A car can either be in idle, where it is not
 * moving, or moving where it is being moved by the driver or a component
 * of the car park (i.e. Elevator, Trolley, Shuttle).
 * <p>
 * @author szeyick
 * StudentID - 1763652.
 */
public enum CarState {
            
    /**
     * The idle state.
     */
    IDLE, 
    
    /**
     * The moving state.
     */ 
    MOVING;
}
