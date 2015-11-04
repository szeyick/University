## Performance Notes

- String is immutable and stays in the String Pool, if you change the reference, it will make another String.
- String is thread safe.

- StringBuffer is mutable meaning that you can manipulate the String that you put in there.
- StringBuffer is synchronised meaning that it is thread safe.
- StringBuffer has all the same methods as StringBuilder.

- StringBuilder is mutable meaning that it can be manipulated.
- StringBuilder is faster than StringBuffer since it does not have the overhead of synchronisation.

### String Concatentation vs StringBuilder (StringBuffer)

Consider the following example -

```
String s= "" ;
	for(int i= 0 ; i < 10 ; i++ ) {
    	s+= "a" ;
	}
```

The JVM will create an instance of StringBuilder, however it will use it to grab the current string, append the existing string, then call toString() to allocate it to the parameter S. This means that in this loop, there will be 10 instances of new String created, along with 10 instances of StringBuilder.

It does this because the compiler is not smart enough to know that the loop is just copying the same string over and over again, meaning it will be forced to create the StringBuilder and String objects.

Now consider this -

```
StringBuilder sb= new StringBuilder() ;
	for(int i= 0 ; i < 10 ; i++ ) {
    	sb.append( "a" );
	}
String s= sb.toString() ;
```

StringBuilder will assign the character to its internal array. It is only when the toString() method is called is when it actually creates the new String object.

In this case, we only have 1 instance of StringBuilder which is created before we enter the loop, and a single instance of the String object, which happens when the toString() is called. 

Because **append("a")** only places the character into StringBuilders internal array, it does not create any new String objects.