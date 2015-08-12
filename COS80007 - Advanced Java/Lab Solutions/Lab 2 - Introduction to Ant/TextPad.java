/*
 * Class to add to Ant to enable TextPad to load java files
 * recursively from a directory
 *
 * The apache ant ant.jar file needs to be on the class path.
 *
 */

import java.io.*;
import java.util.*;

  // Imported from ant.jar
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class TextPad extends Task
{
  private File[] loadList;
  private File srcDir;
  private String msg;
  private String dir;
  private ArrayList list;
  private String arg = "";

    // The method executing the task
  public void execute() throws BuildException
  {
    System.out.println(msg);
    list = new ArrayList();
    findDirectories(dir);
    Runtime r = java.lang.Runtime.getRuntime();
    Iterator iter = list.iterator();
    while (iter.hasNext())
    {
      arg = (String)iter.next();
      try
      {
        r.exec("c:\\Program Files\\Notepad++\\notepad++.exe "+arg);
        System.out.println(arg);
      }
      catch(Exception e)
      {
        System.out.println("Error: " + e);
      }
    }
  }

    // The setter for the "message" attribute
  public void setMessage(String msg)
  {
    this.msg = msg;
  }

    // The setter for the "dir" attribute
  public void setDir(String dir)
  {
    this.dir = dir;
  }

    // Recursively locate all java files below the directory
    // specified storing the result in an ArrayList.
  private void findDirectories(String dir)
  {
    String fileName;

    try
    {
      File pathName = new File(dir);
      String[] fileNames = pathName.list();

      for (int i = 0; i<fileNames.length; i++)
      {
        File tf = new File(pathName.getPath(),fileNames[i]);
        if (tf.isDirectory())
        {
          //System.out.println(tf.getCanonicalPath());
          findDirectories(tf.getPath());
          //fileName = tf.getPath()+"\\*.java";
          //list.add(fileName);
        }
        else
        if (tf.isFile())
        {
          fileName = tf.getCanonicalPath();
          if (fileName.endsWith(".java"))
           list.add(fileName);
        }
      }
    }
    catch(IOException e)
    {
      System.out.println("Error: " + e);
    }
  }
}

