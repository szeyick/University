// QuadApplet.java
//<applet code=QuadApplet.class width=300 height=180> </applet>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 Example Applet illustrate BorderLayout and GridLayout
 and (later) GridBagLayout.  It is based on unit 1 slide 27
 and unit 2 slide 5.

 @version 3.0,  14 Jul 2005 swing. rka
 @author Rob Allen
 */

public class QuadApplet extends JApplet implements ActionListener 
{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JLabel testLabel = new JLabel("Hello", SwingConstants.LEFT);
    
    public void init() 
    {
        GridBagConstraints gbc = new GridBagConstraints();
        Container pane = getContentPane();

        setSize(300, 300);
        // Use Grid Bag.
        pane.setLayout(new GridBagLayout());
        button1 = new JButton("NW");
       
        gbc.gridx = 0;
        gbc.gridy = 0;
        pane.add(button1, gbc);
        
        button2 = new JButton("SW");
       
        gbc.gridx = 0;
        gbc.gridy = 2;
        pane.add(button2, gbc);

        button3 = new JButton("NE");
       
        gbc.gridx = 2;
        gbc.gridy = 0;
        pane.add(button3, gbc);
        
        button4 = new JButton("SE");
       
        gbc.gridx = 2;
        gbc.gridy = 2;
        pane.add(button4, gbc);
        
        testLabel.setBackground(Color.white);
        testLabel.setOpaque(true);
        testLabel.setVerticalAlignment(SwingConstants.TOP);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(testLabel, gbc);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
    }
   
    public void actionPerformed(ActionEvent event) 
    {
        if (event.getSource() == button1) // n
        {
            testLabel.setHorizontalAlignment(SwingConstants.LEFT);
            testLabel.setVerticalAlignment(SwingConstants.TOP);
        }
        else if (event.getSource() == button2)  // s
        {
            testLabel.setHorizontalAlignment(SwingConstants.LEFT);
            testLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        }
        else if (event.getSource() == button3)  // e
        {
            testLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            testLabel.setVerticalAlignment(SwingConstants.TOP);
        }
        else if (event.getSource() == button4) // w
        {
            testLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            testLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        }
    }
}
