package aps.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ControlPanelActionListener}.
 * <p>
 * This class is responsible for responding to input events from user
 * interaction.
 * <p>
 * @author szeyick
 */
public class ControlPanelActionListener implements ActionListener {

    /**
     * The action to invoke. 
     */
    private final IAction action;
    
    /**
     * Constructor.
     * @param action - The action to be invoked.
     */ 
    public ControlPanelActionListener (IAction action) {
        this.action = action;
    }
    /**
     * {@inheritDoc  
     */ 
    @Override
    public void actionPerformed(ActionEvent e) {
        action.executeAction();
    }
    
}
