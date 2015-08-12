## Lab 02 - Introduction to Ant

The following contains brief notes regarding the lab content.

The main files that were modified for this lab are -

- build.xml - The script that Ant runs.
- TextPad.java - Modifications were made to open Java files in Notepad++.

### Installing Ant

Setting the ANT_HOME environment variable

This is done through the MS DOS command **set** or through the Environment Variable window in Settings.

- Set **ANT_HOME=(location)**, where (location) is the root directory for Ant.

- Set ANT_HOME on the path with **path=(location)\bin;%PATH%;. It ensures that it doesn't just override the existing path entries.

- Set ANT_HOME on the classpath so we can recompile Java files that reference Ant - **set CLASSPATH=.%CLASSPATH%;%ANT_HOME%\lib\ant.jar**

### Ant Comments

Add the attribute description="" to each target element to set comments so when you run ant -p it outputs all the tasks that can be run and what they do as it is read from the comments.

### JavaDoc

To compile JavaDoc we created a new target called **javadocs**. This means that if we invoke the command **ant javadocs** it will run the target in the Ant file that corresponds to that name.

The action that we use in this instance will be **javadoc** which is required to take in the following attributes -

- **destdir** - Where to output the compiled JavaDocs.
- **classpathref** - The classpath of where the Java files are located, which is generally the current directory.
- **packageset** - The location of the root source package.

### Executing Outside Programs

Ant can execute Java files that perform other types of execution. In this instance we have a Java file that uses **runtime.exec** to execute an external program (Notepad++).

To ensure that it runs, we have to make sure that the ANT_HOME is set in the CLASSPATH variable since the Java files references files in the ant.jar. 

Furthermore, the modified file is required to be recompiled before it can be used by Ant.

Another modification is required in the build.xml, where the **taskdef** references the Java class that we want to execute. Here we need to provide the location of the file through the **classpath=** attribute. Since the file is in the current directory, we can reference this with the ${basedir} property.