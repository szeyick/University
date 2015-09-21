package aps.action;

import aps.events.EventType;
import control.APSControl;

/**
 * The {@link CreateDropOffEventAction}.
 * <p>
 * This class is responsible for allowing a user to create a pickup action. A
 * pickup event is the process of having a car wait in the forecourt of the
 * ground floor and automatically parked in the car park.
 * <p>
 * @author szeyick StudentID - 1766352
 */
public class CreateDropOffEventAction implements IAction {

    /**
     * {@inheritDoc
     */
    @Override
    public void executeAction() {
        APSControl.getControl().createParkingEvent(EventType.ARRIVAL);
    }
}
