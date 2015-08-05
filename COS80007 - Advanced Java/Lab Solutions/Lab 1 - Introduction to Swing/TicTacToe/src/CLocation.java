 /* TitTacToe Application
 *
 *  CLASS CLocation
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *
 * DESCRIPTION:
 *
 *  Class to hold information about each location on the
 *  game board
 *
 * PUBLIC FEATURES:
 *
 * COLLABORATORS:
 *
 *
 */

class CLocation
 implements Cloneable
{
  public static final int EMPTY = 0;
  public static final int NOUGHT = 1;
  public static final int CROSS = 2;
  private int location;

  public CLocation()
  {
    location = EMPTY;  // The location is empty
  }

  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException e)
    {
      System.out.println("In CLocation clone() " + e);
      return null;
    }
  }

  public int getLocation()
  {
    return location;
  }

  public void setLocation(int location)
  {
    this.location = location;
  }
}