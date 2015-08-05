 /* TitTacToe Application
 *
 *  CLASS CNode
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *
 * DESCRIPTION:
 *
 * Class holding the nodes in the game tree
 *
 * PUBLIC FEATURES:
 *
 * COLLABORATORS:
 *
 *
 */

class CNode
{

  private CBoard board;
  private int turnType;
  private CNode son;
  private CNode next;
  private int rows;
  private int cols;
  private int value;

  public CNode(CBoard b,int turnType)
  {
    board = b;
    rows = CBoardManager.ROWS;
    cols = CBoardManager.COLS;
    this.turnType = turnType;
    son = null;
    next = null;
    value = 0;
  }

    // Sets the static value of the node for a player
  public void setValue(int value)
  {
    this.value = value;
  }

   // Change the sign of the value
 public void minusValue()
  {
    value = -value;
  }

  public int getValue()
  {
    return value;
  }

  public CBoard getBoard()
  {
    return board;
  }

  public int getTurnType()
  {
    return turnType;
  }

  public void setTurnType(int turnType)
  {
    this.turnType = turnType;
  }

  public void addSon(CNode son)
  {
    this.son = son;
  }

  public CNode getSon()
  {
    return son;
  }

  public CNode getNext()
  {
    return next;
  }

  public void setNext(CNode next)
  {
    this.next = next;
  }
}