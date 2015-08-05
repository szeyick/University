 /* TitTacToe Application
 *
 *  CLASS CBoard
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *
 * DESCRIPTION:
 *
 *  Class to hold the total game board status
 *
 * PUBLIC FEATURES:
 *
 * COLLABORATORS:
 *
 *
 */

 import java.awt.*;
 import java.awt.event.*;

class CBoard
 implements Cloneable
{
  private CLocation [][] board;
  private int rows;
  private int cols;

  public CBoard()
  {
    int i,j;

    rows = CBoardManager.ROWS;
    cols = CBoardManager.COLS;
    board = new CLocation[rows][cols];
    for (i=0; i < rows; i++)
     for (j=0; j < cols; j++)
     {
       board[i][j] = new CLocation();
     }
  }

    // Implements the Cloneable interface
  public Object clone()
  {
    int i,j;

      CBoard b = new CBoard();
      for (i=0; i < rows; i++)
       for (j=0; j < cols; j++)
       {
         b.board[i][j] = new CLocation();
         b.board[i][j].setLocation(board[i][j].getLocation());
       }
      return b;
  }

    // Initializes game board for new game
  public void setInitial()
  {
    int i,j;

    for (i=0; i < rows; i++)
     for (j=0; j < cols; j++)
     {
       board[i][j].setLocation(CLocation.EMPTY);
     }
  }

    // Store information at board position i,j
  public void setLocation(int location,int i,int j)
  {
    board[i][j].setLocation(location);
  }

    // Tests if board is full
  public boolean isFull()
  {
    int numberOfMoves = 0;

    for (int i=0; i < rows; i++)
     for (int j=0; j < cols; j++)
      if (getLocation(i,j) == CLocation.EMPTY)
       numberOfMoves++;
    if (numberOfMoves == 0)   // No moves remain
     return true;
    else
     return false;
  }

    // Retrieve contents of board at position i,j
  public int getLocation(int i,int j)
  {
    return board[i][j].getLocation();
  }

    // Returns the value of a line (row, column or diagonal)
    // on the game board.
  private int aValue(int cross,int nought,int player)
  {
    int value = 0;

    if (player == CLocation.CROSS)
    {
      if ((cross == 1) && (nought == 0))
       value++;
      else
      if ((cross == 2) && (nought == 0))
       value += 2;
      else
      if ((nought == 1) && (cross == 0))
       value --;
      else
      if ((nought == 2) && (cross == 0))
       value -= 2;
      if (cross == 3)
       value = 9;
      else
      if (nought == 3)
       value = -9;
    }
    else
    if (player == CLocation.NOUGHT)
    {
      if ((cross == 1) && (nought == 0))
       value--;
      else
      if ((cross == 2) && (nought == 0))
       value -= 2;
      else
      if ((nought == 1) && (cross == 0))
       value++;
      else
      if ((nought == 2) && (cross == 0))
       value += 2;
      if (cross == 3)
       value = -9;
      else
      if (nought == 3)
       value = 9;
    }
    return value;
  }

  /* Evaluate the board.
     The evaluation function is a key aspect of two player
     game trees. Here the value is positive for the current
     player and negative for the other player.
     If a player occupies all locations in a row, column or diagonal
     the value is 9. If the opponent, then the value is -9.
  */
  public int evaluate(int player)
  {
    int i,j;
    int cross;
    int nought;
    int value = 0;
    int v;

      // Count the crosses and noughts in each row
      // and evaluate each row accumulating the total.
    for (i=0;i < rows;i++)
    {
      cross = 0;
      nought = 0;
      for (j=0;j < cols;j++)
      {
        if (board[i][j].getLocation() == CLocation.CROSS)
         cross++;
        else
        if (board[i][j].getLocation() == CLocation.NOUGHT)
         nought++;
      }
      v = aValue(cross,nought,player);
      if (Math.abs(v) == 9)
       return v;
      value += v;
    }
      // Repeat for columns
    for (j=0;j < cols;j++)
    {
      cross = 0;
      nought = 0;
      for (i=0;i < rows;i++)
      {
        if (board[i][j].getLocation() == CLocation.CROSS)
         cross++;
        else
        if (board[i][j].getLocation() == CLocation.NOUGHT)
         nought++;
      }
      v = aValue(cross,nought,player);
      if (Math.abs(v) == 9)
       return v;
      value += v;
    }
      // Evaluate the diagonal, top left to bottom right
    cross = 0;
    nought = 0;
    if (board[0][0].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[0][0].getLocation() == CLocation.NOUGHT)
     nought++;
    if (board[1][1].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[1][1].getLocation() == CLocation.NOUGHT)
     nought++;
    if (board[2][2].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[2][2].getLocation() == CLocation.NOUGHT)
     nought++;
    v = aValue(cross,nought,player);
    if (Math.abs(v) == 9)
     return v;
    value += v;
      // Evaluate the diagonal, top right to bottom left
    cross = 0;
    nought = 0;
    if (board[0][2].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[0][2].getLocation() == CLocation.NOUGHT)
     nought++;
    if (board[1][1].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[1][1].getLocation() == CLocation.NOUGHT)
     nought++;
    if (board[2][0].getLocation() == CLocation.CROSS)
     cross++;
    else
    if (board[2][0].getLocation() == CLocation.NOUGHT)
     nought++;
    v = aValue(cross,nought,player);
    if (Math.abs(v) == 9)
     return v;
    value += v;
    return value;
  }

}