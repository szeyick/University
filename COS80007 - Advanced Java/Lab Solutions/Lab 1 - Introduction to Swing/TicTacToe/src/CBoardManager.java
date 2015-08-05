 /* TicTacToe Application
 *
 *  CLASS TBoardManager
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *
 * DESCRIPTION:
 *
 *  This is the Game Manager and does display
 *  using array of JButtons
 */

 import java.awt.*;
 import java.awt.event.*;
import javax.swing.*;

class CBoardManager extends JPanel implements ActionListener
{
    // Board dimensions
  public static final int ROWS = 3;
  public static final int COLS = 3;
    // Maximum depth of game tree.
    // For TicTacToe the maximum number of player moves is 9.
    //  - 4 and a half turns.
  public static final int LOOKLEVEL = 4;

  private TicTacToe ttt;
  private CBoard board;
  private CBoard bd;
  private CGameTree gt = null;
  private boolean debug;
  private boolean computerFirst;
  private JButton[][] b;
  private int rows;
  private int cols;
  private int player;
  private int lookLevel;

  public CBoardManager(TicTacToe ttt)
  {
    this.ttt = ttt;
    rows = ROWS;
    cols = COLS;
    lookLevel = LOOKLEVEL;
    debug = false;
    computerFirst = false;
    setLayout(new GridLayout(rows,cols));
    player = CLocation.CROSS;
    board = new CBoard();
        
    b = new JButton [rows][cols];
    for (int i=0; i < rows; i++)
     for (int j=0; j < cols; j++)
     {
       b[i][j] = new JButton("");
       this.add(b[i][j]);
       b[i][j].addActionListener(this);
       b[i][j].setFont(new Font("SansSerif",Font.BOLD,32));
     }
  }

    // Initialize for new game
  public void newGame()
  {
    for (int i=0; i < rows; i++)
     for (int j=0; j < cols; j++)
     {
       b[i][j].setText("");
       board.setInitial();
     }
    ttt.setLabel("Start");
    if (computerFirst)
    {
      computerMove(CLocation.NOUGHT);
      displayBoard();
    }
    player = CLocation.CROSS;
  }

    // Set or Unset debug output dump
  public void setDebug(boolean debug)
  {
    this.debug = debug;
    if (gt != null)
     gt.setDebug(debug);
  }

    // Set or unset computerFirst
  public void setComputerFirst(boolean computerFirst)
  {
    this.computerFirst = computerFirst;
  }

    // Work out the computer's move
  private void computerMove(int player)
  {
    int value;

    if (player == CLocation.NOUGHT)
     ttt.setLabel("Nought's turn");
    else
     ttt.setLabel("Cross's turn");
    gt = new CGameTree(lookLevel,debug);
    bd = gt.nextMove(board,player);
    if (bd == null)
     ttt.setLabel("Game drawn");
    else
    {
      board = bd;
      value = board.evaluate(player);
      if (player == CLocation.NOUGHT)
      {
        if (value == 9)
         ttt.setLabel("Nought Wins");
        else
         ttt.setLabel("Cross's Turn");
      }
      else
      {
        if (value == 9)
         ttt.setLabel("Cross Wins");
        else
         ttt.setLabel("Nought's Turn");
      }
    }
  }

    // Display the game board
  private void displayBoard()
  {
    if (board != null)
     for (int i=0;i < rows;i++)
      for (int j=0;j < cols;j++)
      {
        if (board.getLocation(i,j) == CLocation.EMPTY)
         b[i][j].setText("");
        else
        if (board.getLocation(i,j) == CLocation.NOUGHT)
         b[i][j].setText("O");
        else
        if (board.getLocation(i,j) == CLocation.CROSS)
         b[i][j].setText("X");
      }
      repaint();
  }

  public void actionPerformed(ActionEvent evt)
  {
     Object src = evt.getSource();
     for (int i=0;i < rows;i++)
      for (int j=0;j < cols;j++)
       if (src == b[i][j])
       {
         clicked(i, j);
       }
  }
  
  private void clicked(int i, int j)
  {
    int value;
    if (player == CLocation.CROSS)
    {
        if (board.getLocation(i,j) == CLocation.EMPTY)
        {
            b[i][j].setText("X");
            board.setLocation(CLocation.CROSS,i,j);
            value = board.evaluate(CLocation.CROSS);
            if (board.isFull())
                ttt.setLabel("Game Drawn");
            else
            if (value == 9)
                ttt.setLabel("Cross Wins");
            else
            {
                player = CLocation.NOUGHT;
                computerMove(player);
                player = CLocation.CROSS;
            }
       }
    }
    else
    {
        if (board.getLocation(i,j) == CLocation.EMPTY)
        {
            b[i][j].setText("O");
            board.setLocation(CLocation.NOUGHT,i,j);
            value = board.evaluate(CLocation.NOUGHT);
            if (board.isFull())
                ttt.setLabel("Game Drawn");
            else
            if (value == 9)
                ttt.setLabel("Nought Wins");
            else
            {
                player = CLocation.CROSS;
                computerMove(player);
                player = CLocation.NOUGHT;
            }
        }
    }
    displayBoard();
    if (board.isFull())
       ttt.setLabel("Game Drawn");
  }
  

}