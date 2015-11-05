Questions:

- **1. Swing Foundations**

**a) What	is	the	difference	between	lightweight	and	heavyweight	components	in	Swing?**

A lightweight component is considered to be a component that does not rely on the operating system to manage any of its behaviour for drawing, colouring. It is a component that can have its behaviour defined that is independent of the underlying operating system that Java is installed on. In Swing all components with the exeception of the top level components such as JFrame, JRootPane are all lightweight components.

A heavyweight component is a component that has a part of its behaviour that is defined by the operating system. Top level components in Swing are considered as heavyweight as they rely partially on their AWT parent classes. AWT classes are dependent on the underlying operating system.

**b) Explain the relationship between the glassPane, the contentPane, the menuBar, the JLayeredPane and the JRootPane in the structure of a JFrame.**

The relationship between these components defines the layered display of Swing.

The order of the layers from top to bottom are - glassPane, contentPane, , menuBar, JPayeredPane, JRootPane. All these components are added to the JFrame to give the look and feel of a Swing application. Individual components such as JLabels, JPanels, JTables are usually added to the content pane. 

The glass pane is the top most component layer in Swing. Components that are displayed on the glass pane will make the application modal, meaning that nothing underneath it on the contentpane will be interactive until the component has been removed from the glass pane

**How can you create a JFrame whose	size is	determined by the underlying components	rather than	by the setSize() method	that the JFrame	inherits from the Component	class?**

By calling the pack(); method on the JFrame, it will change its size to the combined size of all the components that have been added to the content pane.

- **2. Threads**

**a) Explain the benefits of using threads and discuss the kinds of situations when using threads really provides an advantage** 

The benefits of using threads in a multi-core environment is that you can make more use of the cores that you have on the hardware of a machine to run tasks in parallel. In addition, for Java applications using threads will reduce the load that is managed by the main Event Thread as it would be managing the drawing updates, logic calculations and basically the processing of the entire application.

Using threads allows you to divide up the processing between multiple threads, each managing a smaller piece of the application reducing overall load on a thread. 

Applications that have GUI's are ideal for using multiple threads as the threads can be divided up to handle the applications business logic and managing the user input. By splitting it up, it allows for the event thread to always be responsive to user input and interactions, whilst having another thread handle the business logic will allow it to process in parallel.

Other situations that are handy to use threads is when you want to do complex calculations. It is not preferable to do time consuming calculations on the main Swing thread as it locks up the thread so it will not be able to do any drawining or respond to user input giving the idea that the application is frozen. By placing complex calculations onto another thread will allow the CPU to only execute that thread when it has been given time to do so, whilst freeing up the main thread for the rest of the application.

**b) The following code	fragment outlines the structure	for	the	implemented	run() method of	a threaded class. Explain why each part	is needed.**

```
public run()
	{
		try
	{ …
		while (!interrupted() && more work)
		{ do more work }
	}
	catch(InterruptedException e) { ... }
	}
```

The code snipped is invoked whenever the thread has been allocated time by the CPU to execute its task. The contents of the run() method detail the task that is to run when the CPU has given it time to do so. The try/catch block is to catch any exceptions that may be raised if the thread is interrupted during its running. 

Other threads are capable of calling an interrupt to pause or stop the thread from executing. When this happens, the contents of the catch section of the method will be executed. It is to indicate that another thread has interrupted this currently running thread during its execution.

The contents within the try appears to be a complex calculation. The !interrupted() method is to check whether the state of the thread. If the thread has been interrupted, then it will not continue processing, instead giving up its CPU time and passing control back. The contents of the while() will only continue executing if the thread is in the correct state to do so, if another thread has interrupted it, it will stop processing.

The reason that we need the !interrupted() is because even if another thread has set the current thread to interrupt, it will not immediately halt the processign of the run. Instead it will wait until the CPU runs that particular thread again before it checks the internal state.

**c) What is the effect	of using the synchronized keyword on a method? Why is it useful?**

The syncrhonised keyword is used to make a method thread safe. It is useful in a multi-threaded environment as it does not allow multiple threads access to the method at the same time. By synchronising a method, you essentially create a queue for access to that particular method. 

When a thread accesses the method, it places a lock on the object. Subsequently other threads that want to access the same method object whilst a lock has been placed on it will enter a access queue for that method. When the original thread finishes its processing, it gives up the lock allowing the next method in the queue access to it. 

It is useful so as to not allow random access to an object since the data might be in the middle of changing or not having its contents completely changed before another thread accesses it and changes it again. By not synchronising methods or objects that are used by multiple threads, you can raise problems with correupt or inconsistent data.

- **Swing Text**

**a) Outline using pseudo-code how you can use the JTabbedPane to display multiple views of different text data.**

```
createJTabbedPane (JTabbedPane tabbedPane = new JTabbedPane())
	retrieveViewList (List<JPanel> panelList ...)
	iterate through viewList (for (JPanel panel : panelList))
		add views to JTabbedPane (tabbedPane.add(panel))

	setTabbedPaneView to n (tabbedPane.setSelected(0))
	addJTabbedPane to contentPane
```

**b) How would you implement scroll	bars when using	JTextArea (support your answer with	code fragments)?**

```
JScrollPane scrollPane = new JScrollPane();
JTextArea textArea = new JTextArea(10, 60);

// May want to add code for display of horizontal or vertical scrollbars
scrollPanel.setLineWrap(true); // Will remove the horizontal scrollbars since text will wrap
scrollPane.add(textArea);
scrollPane.setView(textArea);
getContentPane().add(scrollPane);
```

**c) How can you implement input data validation on	the	actual data	model for a class you create which extends JTextField?**

Input validation would be written by creating a custom JTextField class.

In addition, you would need to override the default ```Document createDefaultModel()``` method that creates the data model that the input text will be saved in.

By creating a custom PlainDocument class and returning that as the default. It allows you access to the ```insertText(...)``` method. This method is called whenever a character is entered into the text area. You can retrieve the old string, along with the new string (which is the new string minus the previous character entered) to validate the input as you are entering text into it. 

Once satisfied with the text, you can call super.insertText(...) that will set the text to the PlainDocument.

-**4. Swing	Tables**

Suppose	we want	to display the identifying number (idNum),	the	code and name of UnitofStudy objects in a JTable. The columns are labelled “Number”, “Unit Code” and “Unit	Name”.		

Example: 
Number  Unit Code Unit Name 
0 HIT3087 Advanced Java
1 HIT3309 Software Practices and Project Management

The	UnitOfStudy objects	are	kept in	one	place, a MapData instance.	

As the program	runs, more UnitOfStudy objects are created and added into the  MapData. We	want the JTable	view to	automatically become longer	so	that it  always	shows the set of units	of study.

Below is some incomplete code to set up	the	table.			

Assume
class UnitOfStudy has methods:
public	String	getCode()
public	String	getName()
class	MapData	has	methods:
public	UnitOfStudy getUnitOfStudy(int	idNum)		
//	idNum	=	0,	1,	2,	…,	(size-1)
public	int	getSize()		//	returns	the	number	of	units	of	study stored

```
class MyTableFrame extends JFrame
{
	public MyTableFrame()
 	{
 		MapData data = new MapData();
 		// assume other code is here ...
 		//
 		TableModel model = new UnitOfStudyModel(data);
 		JTable table = new JTable(model);

 		getContentPane.add(new JScrollPane(table), BorderLayout.CENTER);
 	}
}

class UnitOfStudyModel extends AbstractTableModel
{
 	private MapData data;
 	
 	public UnitOfStudyModel(Mapdata dataTmp)
 	{
 		this.data = dataTmp;
 	}

 	// Return the number of rows.
 	public int getRowCount()
 	{
 		return data.getSize();
 	}

 	// Return the number of columns
 	public int getColumnCount()
 	{
 		return 3; // Is this read from somewhere
 	}

 	// Return the value at a given cell (row, column)
 	public Object getValueAt( int row, int column)
 	{
 		UnitofStudy selectedUnit = data.getUnitofStudy(row);
 		switch (column) { :
 			case 0:
 				return String.valueof(row);
 			case 1:
 				return selectedUnit.getCode();
 			case 2:
 				return selectedUnit.getName();
 			default:
 			return null;
 		}
 	}

 	// The name of the column
	public String getColumnName( int column )
 	{
 		switch (column) { :
 			case 0:
 				return "Number";
 			case 1:
 				return "Unit Code";
 			case 2:
 				return "Unit Name";
 			default:
 			return null;
 		}
 	}
}
```

- **5. Graphics	2D**

Explain	the	purpose	and	the	use/operation of the following methods:

**a) the Graphics2D	setRenderingHint() method**

This method allows you to set properties in the Graphics2D to outline how you want it to draw, it allows you to choose between drawn quality and speed giving you access to settings such as anti-aliasing. The RenderingHints object is like a map, that contains defined key/value pairs to allow you to turn settings on/off to speed up the draw.

**b) the Graphics2D	setStroke() method**

This method allows you to set the stroke properties of how shapes are drawn onto the display. It allows you to change properties such as the stroke size which determines the width of the shapes that are drawn. Useful to change when an object is scaled as the stroke size will need to be decreased. It also allows you to set other properties for the stroke such as dashed lines and things like that.

**c) the Graphics2D	setPaint() method**

The set paint method allows you to set the colour which the shape will be filled. It allows you to set a colour as a Color object, or load textures from an external file. Whichever is set will be the paint that will be used when the next shape is drawn.

**d) the Graphics2D	setClip() method**

The set clip method allows you to define a clipping area that will be used. Objects that exist outside of this clipping area will not be drawn onto the display, whereas objects that are within the bounds of the clipping area will be drawn and displayed.

**e) the Graphics2D	setComposite() method**

The set composite method allows you to change what you do with the pixels?

- **6. Graphics	2D**

**a) Write a simple	paintComponent method to make a	GeneralPath	object appear on the screen.  Assume this object has been set up previously	and	is an instance	variable.**

// Not sure whether we should add the moveTo(x,y) and lineTo(x,y)
public void paintComponent(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	g2.draw(generalPath);
}

**b) Outline how to	scale the object during	drawing	so that	it is as wide as the panel that	has	the	paintComponent method.**

```
// Retrieve the dimension size of the panel that the paintComponent is in.
// Retrieve the width of the object that is to be drawn
// Calculate how many times the object width fits into the panel width
// The calculated size of the scale size.
// Call g2.setScale(x,y) where the x is the width to scale it to.
// Call g2.draw(object)

Dimension d = getSize();
int widthScale = (int) d.getWidth() / o.getWidth();
g2.setScale(widthScale, 0);
g2.draw(o);
```

- **7. Ant**

**Briefly describe what Ant is and how to use it in Java software development.**

Ant stands for 'Another Neat Tool'. Ant can be used for automatic tasks and deployment, and functions much like a script. Ant basically provides all the commands that you would want to use to move around a command prompt along with being able to execute command line tools. It can be used in Java to automate compilation and distribution.

With Ant, you can set up tasks, with each task capable of doing something different. In the Java context, this may involve running javac, java or even javaDoc. With Ant you can put each of these in its own task and execute it with a command. Also you can set up dependencies meaning that certain defined tasks cannot run unless the other tasks are run first which ensures that a user cannot accidentally run a task.

Furthermore Ant can also be used to clean up, which in the Java context can be used to remove the .class files after the application is run.

- ***8. Performance**

**a) Carefully define “garbage”.**

Garbage is defined as objects that exist in the Java heap that noone has a reference to anymore. This may include objects created within a method that has finished executing or an object that has been dereferenced. It is called garbage because there are other objects within the running application that wants to use it anymore, and in fact cannot use it because it no longer as a reference to it.

**b) Describe the Java	Garbage	Collector (GC) and	its	operation.**

The Java Garbage Collector is responsible for locating these objects that noone has a reference to anymore and freeing up the memory that these unwanted objects are using. This freed up memory is then placed back onto the Java Heap to allow the application to use it again.

It functions as such -

Objects consume memory, which initially is placed onto the short term memory queue. 

During the applications life cycle, these objects will be used and references will be kept. Periodically when the CPU is free, the JVM will do a garbage collection to go through this short term memory queue to see if there are any objects in there that are no longer referenced by anyone. If there are, then these will be cleaned up. Whereas objects that are still being referenced are moved along to the next place the middle term or older generation memory queue.

The process is repetead quite often for the short term memory queue, it is quick because objects in there are dropping in and out of memory.

The same process is repeated for the older generation memory queue, where objects no longer being used are cleaned up and ones that are still being used placed into another queue for long term memory queue.

Because the JVM only has a finite space for memory, if there are too many objects in this long term memory queue, then it will do a global garbage collection or a 'stop the world gc'. This is where the system will completely freeze as the garbage collector will attempt to recover memory from this last queue.

**c) What are the symptoms of excessive garbage?**

Excessive garbage will lead to memory running out, which leads to crashes due to insufficient heap space for the JVM to run all the applications processes. Other things will be the application will seem to freeze as the GC is busy reclaiming memory.

Poor performance and slowing down of the application are symptomso f excessive garbage as the garbage collector is going into overtime with reclaiming.
