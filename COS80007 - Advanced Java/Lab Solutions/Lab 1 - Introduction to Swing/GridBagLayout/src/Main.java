
import javax.swing.JApplet;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author szeyick
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JApplet quadApplet = new QuadApplet();
        quadApplet.init();
        
        JFrame frame = new JFrame();
        frame.add(quadApplet);
        
        frame.setSize(quadApplet.getSize());
        frame.setVisible(true);
    }
    
}
