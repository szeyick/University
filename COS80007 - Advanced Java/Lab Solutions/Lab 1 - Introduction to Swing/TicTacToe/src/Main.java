
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
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.init();
        ticTacToe.setSize(300, 300);
        
        JFrame frame = new JFrame();
        frame.add(ticTacToe);
        frame.setSize(ticTacToe.getSize());
        
        frame.setVisible(true);
    }
    
}
