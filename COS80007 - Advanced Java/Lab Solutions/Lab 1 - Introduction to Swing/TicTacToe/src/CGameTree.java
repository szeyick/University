 /* TitTacToe Application
 *
 *  CLASS CGameTree
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *
 * DESCRIPTION:
 *
 * Class to determine best move
 * This class is the heart of the game engine
 *
 * PUBLIC FEATURES:
 *
 * COLLABORATORS:
 *
 *
 */

class CGameTree
{
  public static final int PLUS = 1;
  public static final int MINUS = 2;
  private int rows;
  private int cols;
  private boolean debug;
  private int depth;

  public CGameTree(int depth,boolean debug)
  {
    rows = CBoardManager.ROWS;
    cols = CBoardManager.COLS;
    this.depth = depth;
    this.debug = debug;
    if (debug)
     System.out.println("Finding next move");
  }

    // If debug is true, output to console will include tracing info
  public void setDebug(boolean debug)
  {
    this.debug = debug;
  }

    // Display of board position for debug purposes
  public void displayBoard(CBoard b,int turnType)
  {
    int i,j;

    if (turnType == PLUS)
     System.out.println(" +");
    else
    if (turnType == MINUS)
     System.out.println(" -");
    else
     System.out.println();
    for (i=0; i < rows; i++)
    {
      for (j=0; j < cols; j++)
      {
        System.out.print(b.getLocation(i,j));
      }
      System.out.println();
    }
    System.out.println();
  }

    // Determine the best move for player (the MAX player)
    // b is the current board before the move is made
    // The method returns the board after the best move has been added
  public CBoard nextMove(CBoard b,int player)
  {
    CNode node;
    CNode best;
    CBoard board;
    int alpha;
    int beta;

    alpha = java.lang.Integer.MIN_VALUE + 1;
    beta = java.lang.Integer.MAX_VALUE;
      // Create the root of the game tree
    board = (CBoard)b.clone();
    node = new CNode(board,PLUS);
    if (debug)
    {
      System.out.println("Max Player " + player);
      System.out.println("Level 0");
      displayBoard(node.getBoard(),node.getTurnType());
    }
      // Start alpha-beta search
    best = alphabeta(node,alpha,beta,0,player);
    if (best == null)
     return null;
    else
     return best.getBoard();
  }

    // Performs alpha-beta search returning the best node
  public CNode alphabeta(CNode node,int alpha,int beta,int level,int player)
  {
    CNode best = null;
    CNode pBest;
    CNode child;
    CBoard board;
    int score;
    int value;
    int turnType;
    int nextPlayer;
    int numberOfMoves;
    int thisLevel;
    int i,j;

    value = node.getBoard().evaluate(player);
    if ((level == depth)      // Node is a leaf node. Evaluate it.
        || (java.lang.Math.abs(value) == 9)) // Win, shouldn't go deeper
    {
      if (debug)
      {
        System.out.println("Level " + level);
        System.out.println("Value " + value);
        displayBoard(node.getBoard(),node.getTurnType());
      }
      node.setValue(value);
      return node;
    }

    numberOfMoves = 0;
    for (i=0; i < rows; i++)
     for (j=0; j < cols; j++)
      if (node.getBoard().getLocation(i,j) == CLocation.EMPTY)
       numberOfMoves++;
    if (numberOfMoves == 0)   // No moves remain
     return null;

    score = alpha;
    boolean firstTime = true;
    for (i=0; i < rows; i++)
     for (j=0; j < cols; j++)
     {
       if (node.getBoard().getLocation(i,j) == CLocation.EMPTY)
       {
         board = (CBoard)node.getBoard().clone();
         board.setLocation(player,i,j);
         if (node.getTurnType() == PLUS)
          turnType = MINUS;
         else
          turnType = PLUS;
         child = new CNode(board,turnType);
         if (debug)
         {
           thisLevel = level + 1;
           System.out.println("Level " + thisLevel);
           displayBoard(child.getBoard(),child.getTurnType());
         }
         if (player == CLocation.CROSS)
          nextPlayer = CLocation.NOUGHT;
         else
          nextPlayer = CLocation.CROSS;
         pBest = alphabeta(child,-beta,-score,level+1,nextPlayer);
         if (pBest == null)
          value = -child.getBoard().evaluate(player);  // No more moves available
         else
          value = -pBest.getValue();
         if (firstTime)
         {
           best = child;
           child.setValue(value);
           node.setValue(-value);
           score = value;
           firstTime = false;
         }
         if (debug)
         {
           System.out.println("Level after alphabeta " + level);
           System.out.println("i " + i + " j " + j);
           System.out.println("alpha " + alpha + " beta " + beta);
           System.out.println("value " + value + " score " + score);
         }
         if (value > score)     // an improvement?
         {
           score = value;
           node.setValue(-score);
           best = child;
           child.setValue(score);
           if (debug)
           {
             System.out.println("Level " + level);
             System.out.println("Best value so far " + score);
             displayBoard(best.getBoard(),best.getTurnType());
           }
         }
         if (score >= beta)     // a cut-off?
          return best;
       }
     }
    return best;
  }

}