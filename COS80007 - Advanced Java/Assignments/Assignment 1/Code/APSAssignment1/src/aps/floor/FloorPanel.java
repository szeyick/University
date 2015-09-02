/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.floor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.swing.JPanel;

/**
 *
 * @author szeyick
 */
public class FloorPanel extends JPanel {

    /***
     * Default constructor. 
     */
    public FloorPanel() {
        setSize(400, 400);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /***
     * Paint the panel.
     **/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        ParkingLevelFloorView floorView = new ParkingLevelFloorView();
        Shape floor0 = floorView.getFloorLayout();
        
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getHeight() / 2, getWidth() / 2);
        g2.setStroke(new BasicStroke(0.5f));
        g2.scale(10, 10);
        g2.setColor(Color.BLACK);
        g2.draw(floor0);
    }
    
}
