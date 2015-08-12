## Ant Basics

Ant allows us to compile, deploy and run a project automatically. It is the equivelant of "Make" but for Java.

The name Ant is short for "Another Neat Tool".

### Targets

Targets in Ant denote a set of commands that can be run. These can be named anything but usually follow the terminology that describes what the set of commands are supposed to do such as "compile", "run", "init" "clean".

To invoke the set of commands you run the command **ant <commandName>**, where <commandName> is "compile", "run" or whatever you named it as.

You can also set a default target, which will be triggered if you just type in **ant** without a command name. The targets are usually defined in the build.xml of a project.

### Actions

An action is an individual executable command such as "mkdir" and "javac". Actions usually take parameters to ensure that the input is found and the output is sent to the write area. It can be said that a Target is made up of a set of Actions.

### Properties

Properties in Ant function like constants that will be used through the execution of the Ant script. These constants can be anything but examples of what they can be used for is the location of the source directory and output directories.

Properties are defined by the following format -

```
<property name="propertyName" value="helloWorld"/>

```

and can be accessed by - 

```
${propertyName} 
```

### Project Structures

- Source files for a Java project are usually stored in a top level directory called "src".
- Compiled class files for the project are usually stored in a directory called "classes".
- Other directories usually referenced in a Java project are "resources" and "deploy".

### Time Dependencies

The Java compiler will recompile a source file if the .class file is missing or has an older timestamp but it may also sometimes retain the class files if it cannot find the corresponding .java file. The idea here is to try to use a clean compile, which deletes the classes directory before recompiling or the following command -

```
<target name="compile" depends="init">
	<depend srcdir="src" destdir="classes" cache="depcache" closure="yes"/>
	<javac srcdir="..." />
```

The depcache command will retain information from its previous runs.

### Comments

We can usually output information to the console with the **echo** command, however Ant has a built in mechanism to output information on the various targets with the **ant -p** command.

To make use of the command, we can add a **description="comments"** attribute to the target element

```
<target name="compile" description="Compiling source files">
```

So when we call ant -p it will output the contents in the description attribute.

### Example

```
<project name="My Demo" default="defaultTarget" basedir=".">
	<!-- Enter Project Properties -->
	<property name="mainClass" value="TestProject"/>

	<!-- Run the default target -->
	<target name="defaultTarget" description="Running the default target"/>

	<!-- Initialise the build --> 
	<target name="init" description="Initialise the project space">
		<mkdir dir="classes"/>
	</target>

	<!-- Compile the files in src and output to classes -->
	<target name="compile" depends="init" description="compile the project">
		<javac destdir="classes" srcdir="src" target="1.7"/>
	</target>

	<!-- Run the project -->
	<target name="run" depends="compile" description="Compile and run the ${mainClass}">
		<java classname="{$mainClass}" classpath="classes" fork="true"/>
	</target>
</project>
```

**Notes**

- The depends attribute in the target element, denotes the targets that it needs to run before it is executed. For example, the run target depends on compile, which means that the run will need to compile the src files before it can be successfully run.

- The fork attribute in the java action means that it should be run in another process.