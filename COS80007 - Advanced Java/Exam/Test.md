Questions:

- **1. Swing	Foundations**

**a) What	is	the	difference	between	lightweight	and	heavyweight	components	in	Swing?**
**b) Explain the relationship between the glassPane, the	contentPane, the menuBar, the JLayeredPane and the JRootPane in	the	structure of a JFrame.**
**How	can	you	create	a	JFrame	whose	size	is	determined	by	the	underlying	components	rather	than	by	the	setSize()	method	that the	JFrame	inherits from	the	Component	class?**

- **2. Threads**
**a) Explain	the	benefits	of	using	threads	and	discuss	the	kinds	of	situations	when	
using	threads	really	provides	an	advantage** 

**b) The	following	code	fragment	outlines	the	structure	for	the	implemented	run()	method	of	a	threaded	class.	Explain	why	each	part	is	needed.**

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

**c) What	is	the	effect	of	using	the	synchronized	keyword	on	a	method?	Why	is	it useful?**

- **Swing	Text**

**a) Outline	using	pseudo-code	how	you	can	use	the	JTabbedPane	to	display	multiple	views	of	different	text	data.**
**b) How	would	you	implement	scroll	bars	when	using	JTextArea	(support	your answer	with	code	fragments)?**
**c) How	can	you	implement	input	data	validation	on	the	actual	data	model	for	a class	you	create	which	extends	JTextField?**

-**4. Swing	Tables**

Suppose	we	want	to	display	the	identifying	number	(idNum),	the	code	and	name	
of	UnitofStudy	objects	in	a	JTable.		The	columns	are	labelled	“Number”,	“Unit	
Code”	and	“Unit	Name”.		

Example: 
Number  Unit Code Unit Name 
0 HIT3087 Advanced Java
1 HIT3309 Software Practices and Project Management

The	UnitOfStudy objects	are	kept	in	one	place,	a	MapData	instance.	

As	the	program	runs,	more	UnitOfStudy objects	are	created	and	added	into	the	
MapData. We	want	the	JTable	view	to	automatically	become	longer	so	that	it	 always	shows	the	set	of	units	of	study.		 

Below	is some	incomplete	code	to	set	up	the	table.			

Assume
class UnitOfStudy has	methods:
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
 	
 	public UnitOfStudyModel( ?1 )
 	{
 		?2
 	}

 	public int getRowCount()
 	{
 		return ?3;
 	}
 	public int getColumnCount()
 	{
 		return ?4;
 	}
 	public Object getValueAt( ?5 )
 	{
 		?6
 	}
	public String getColumnName( ?7 )
 	{
 		?8
 	}
}
```

- **5. Graphics	2D**

Explain	the	purpose	and	the	use/operation	of	the	following	methods:

**a) the	Graphics2D	setRenderingHint()	method**
**b) the	Graphics2D	setStroke()	method**
**c) the	Graphics2D	setPaint()	method**
**d) the	Graphics2D	setClip()	method**
**e) the	Graphics2D	setComposite()	method**

- **6. Graphics	2D**

**a) Write	a	simple	paintComponent	method	to	make	a	GeneralPath	object	appear	on	the	screen.	 Assume	this	object	has	been	set	up	previously	and	is	an	instance	variable.**

**b) Outline	how	to	scale	the	object	during	drawing	so	that	it	is	as	wide	as	the	panel	that	has	the	paintComponent	method.**

- **7. Ant**

Briefly	describe	what	Ant	is	and	how	to	use	it	in	Java	software	development.

- ***8. Performance**

**a) Carefully	define	“garbage”.**
**b) Describe	the	Java	Garbage	Collector	(GC)	and	its	operation.**
**c) What	are	the	symptoms	of excessive	garbage?**