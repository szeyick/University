package aps.action;

import aps.events.EventType;
import control.APSControl;

/**
 * The {@link CreatingPickupEventAction}.
 * <p>
 * This class is responsible for allowing a user to create a parking event that
 * allows them to pick up a car that has been parked in the car park.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class CreatePickupEventAction implements IAction {

    /**
     * {@inheritDoc
     */
    @Override
    public void executeAction() {
        APSControl.getControl().createParkingEvent(EventType.DEPARTURE);
    }
}
