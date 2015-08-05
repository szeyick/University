 /* TicTacToe Application
 *
 *  CLASS TBoardManager
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 * modified 14/07/05 by Rob Allen -- lab exercise skeleton
 *
 * DESCRIPTION:
 *
 *  This is the Game Manager and does display
 *  using paintComponent()
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TBoardManager extends JPanel
{
  static final long serialVersionUID = 2L;
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
  private String[][] b;
  private int rows;
  private int cols;
  private int player;
  private int lookLevel;

  public TBoardManager(TicTacToe ttt)
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
        
    b = new String [rows][cols];
    for (int i=0; i < rows; i++)
     for (int j=0; j < cols; j++)
     {
       b[i][j] = "";
     }
    // addMouseListener...
      addMouseListener(new MouseAdapter() {

          /**
           * 
           * @param e 
           */
        @Override
        public void mouseClicked(MouseEvent e) {
            
            Point mousePoint = e.getPoint();
            clicked((int) mousePoint.getX(), (int) mousePoint.getY());
        } 
      });
  }

    // Initialize for new game
  public void newGame()
  {
    for (int i=0; i < rows; i++)
     for (int j=0; j < cols; j++)
     {
       b[i][j] = "";
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
         b[i][j] = "";
        else
        if (board.getLocation(i,j) == CLocation.NOUGHT)
         b[i][j] = "O";
        else
        if (board.getLocation(i,j) == CLocation.CROSS)
         b[i][j] = "X";
      }
      repaint();
  }

 
  private void clicked(int i, int j)
  {
    int value;

    Dimension panelDimension = getSize();
    int cellWidth = (int) panelDimension.getWidth() / 3;
    int cellHeight = (int) panelDimension.getHeight() / 3;
    
    int x = i;
    int y = j;
    
    i = x / cellHeight;
    j = y / cellWidth;
    
    // j becomes row, i becomes column.
    if (i == 3) {
        System.out.println("i is 3");
        i--;
    }
    if (j == 3) {
        System.out.println("J is 3");
        j--;
    }
    System.out.println("Column: " + i + " Row: " + j);
    if (player == CLocation.CROSS)
    {
        if (board.getLocation(i,j) == CLocation.EMPTY)
        {
            b[i][j]  = "X";
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
            b[i][j] = "O";
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
    repaint();
    displayBoard();
    if (board.isFull())
       ttt.setLabel("Game Drawn");
 
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
        Dimension panelDimension = getSize();
        int cellWidth = (int) panelDimension.getWidth() / 3;
        int cellHeight = (int) panelDimension.getHeight() / 3;
        
        // Draw horizontal lines
        g.drawLine(0, cellHeight, 3 * cellWidth, cellHeight);
        g.drawLine(0, cellHeight * 2, 3 * cellWidth, cellHeight * 2);
        
        // Draw vertical lines
        g.drawLine(cellWidth, 0, cellWidth, cellHeight * 3);
        g.drawLine(cellWidth * 2, 0, cellWidth * 2, cellHeight * 3);
        
        // Iterate over the 2D Array and draw.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String value = b[i][j];
                if (value == "X") {
                    System.out.println(value + " " + i + "," + j);
                    int x1 = cellWidth * i;
                    int y1 = cellHeight * j;
                    int x2 = x1 + cellWidth;
                    int y2 = y1 + cellHeight;
                    g.drawLine(x1, y1, x2, y2);
                    
                    x1 = cellWidth * i;
                    y1 = (cellHeight * j) + cellHeight;
                    x2 = x1 + cellWidth;
                    y2 = (cellHeight * j);
                    
                    g.drawLine(x1, y1, x2, y2);
                }  
                else if (value == "O") {
                    System.out.println(value + " " + i + "," + j);
                    int x = cellWidth * i;
                    int y = cellHeight * j;
                    g.drawOval(x, y, cellWidth, cellHeight);
                    setOpaque(debug);
                }
            }
        }
  }

}