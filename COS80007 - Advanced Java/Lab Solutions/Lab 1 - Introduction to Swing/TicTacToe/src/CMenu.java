/* TicTacToe Application
 *
 * CLASS CMenu
 *
 * (c) Ian Foley, version 1.1, 17th February, 2001
 *   adapted to lab exercise  2004
 *  (I think this class is too complicated except when you
 *   have say 10+ menu items in total.  Rob Allen)
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CMenu
   implements ActionListener, ItemListener
{
  private TicTacToe ttt;
  private JMenuBar mbar;

  public CMenu(TicTacToe ttt)
  {
    this.ttt = ttt;

    mbar = new JMenuBar();

    mbar.add(makeMenu("File",
     new Object[]
      {
        "New Game",
        new JCheckBoxMenuItem("Debug"),
        new JCheckBoxMenuItem("Computer Moves First"),
        "Quit"
      },
    this));

    mbar.add(makeMenu("Help",
     new Object[]
      {
        "Index",
        "About",
      },
    this));

  }

  public JMenuBar getMenuBar()
  {
    return mbar;
  }

  private static JMenu makeMenu(Object parent,
      Object[] items, Object target)
  {
    JMenu m = null;
    if (parent instanceof JMenu)
     m = (JMenu)parent;
    else if (parent instanceof String)
     m = new JMenu((String)parent);
    else
     return null;

    for (int i = 0; i < items.length; i++)
    {
      if (items[i] instanceof String)
      {  JMenuItem mi = new JMenuItem((String)items[i]);
          if (target instanceof ActionListener)
             mi.addActionListener((ActionListener)target);
          m.add(mi);
      }
      else if (items[i] instanceof JCheckBoxMenuItem
          && target instanceof ItemListener)
      {  JCheckBoxMenuItem cmi
             = (JCheckBoxMenuItem)items[i];
          cmi.addItemListener((ItemListener)target);
          m.add(cmi);
      }
      else if (items[i] instanceof JMenuItem)
      {  JMenuItem mi = (JMenuItem)items[i];
          if (target instanceof ActionListener)
             mi.addActionListener((ActionListener)target);
          m.add(mi);
      }
      else if (items[i] == null)
       m.addSeparator();
    }
  return m;
  }

  public void actionPerformed(ActionEvent evt)
  {
    JMenuItem c = (JMenuItem)evt.getSource();
    String arg = c.getText();
    if (arg.equals("Quit"))
     System.exit(0);
    else
    if (arg.equals("Index"))
     {}
    else
    if (arg.equals("About"))
     {}
    else
    if (arg.equals("New Game"))
     ttt.newGame();
    else
     System.out.println(arg);
   }

   public void itemStateChanged(ItemEvent evt)
   {
     JCheckBoxMenuItem c
         = (JCheckBoxMenuItem)evt.getSource();
     System.out.print(c.getText() + " ");
     if (!c.getState()) System.out.print("de");
     System.out.println("selected");
     if (c.getText().equals("Debug"))
     {
       if (c.getState())
        ttt.setDebug(true);
       else
        ttt.setDebug(false);
     }
     else
     if (c.getText().equals("Computer Moves First"))
     {
       if (c.getState())
        ttt.setComputerFirst(true);
       else
        ttt.setComputerFirst(false);
     }
     else
      System.out.println(c.getText());
   }

}

