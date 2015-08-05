// TicTacToe Applet
// <applet code=TicTacToe.class width=200 height=240> </applet>
/*  CLASS TicTacToe
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *   adapted to lab exercise  2004
 *
 * DESCRIPTION:
 *
 * GUI Version using Swing with Computer player
 * Uses game tree with alpha-beta pruning
 * Game uses labelled buttons to represent each position.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JApplet
{
  private CMenu menu;
  private JMenuBar mBar;
  private TBoardManager boardManager;
  private JLabel status;

  public void init()
  {
    setLayout(new BorderLayout());
    menu = new CMenu(this);
    mBar = menu.getMenuBar();
    setJMenuBar(mBar);
    boardManager = new TBoardManager(this);
    add(boardManager,"Center");
    status = new JLabel("Start");
    add(status,"South");
  }


    // Store label for display on bottom of screen
  public void setLabel(String s)
  {
    status.setText(s);
  }

    // Toggle Debug Output Dump
  public void setDebug(boolean debug)
  {
    boardManager.setDebug(debug);
  }

    // Set or unset computer move first
  public void setComputerFirst(boolean computerFirst)
  {
    boardManager.setComputerFirst(computerFirst);
  }

    // Initialize for new game
  public void newGame()
  {
    boardManager.newGame();
  }
}