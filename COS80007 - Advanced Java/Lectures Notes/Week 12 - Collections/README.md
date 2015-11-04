## Collections

### Introduction

The notes here really only apply to the changes that were added to Java 5, which was the collections Framework.

### Collections Hierachy

This is the top level interface of all collection classes (i.e. List, Map, Set). It is used as a container that can store a bunch of things called elements.

There are other collections such as Sets, that know when there are duplicates and will not add them.

Sub interfaces of the Collections class is -

- List - Provides an ordered collection. A user will access the elements in a list like they would in an array.
- Set - An unordered collection that contains no duplicates. A user will require to iterate through the Set to find the element they are looking for.
- Map - A collection that stores elements in a <key, value> pair

The concrete implemtnation of these lists include - LinkedList, ArrayList, Vector (Synchronised ArrayList), HashMap, HashSet, LinkedHashSet.

### Collections Class

The Collections class contains a bunch of static methods, like the Arrays class does.

- **public static void shuffle(List< ? > list) - Randomly permutates the provided list using default randomness.

- **public static <T extends Comparable < ? super T>> void sort(List<T> list)** - Sorts a list in ascending order according to the natural ordering. The elements in the list must extend Comparable.

- **public static < T > void sort(List < T > list, Comparator < ? super T > c)** - Sorts in the order of the given comparator.

Java 5 introduced the concept of Generics, so the same methods existed in Java 4, however just without the ? and T

### Comparisons

If you want to provide some form of customised, you need to implement you own **Comparator**. The object that it applies to must also implement the **Comparable** interface for it to be sorted.

```
public class MyObject implements Comparable {
	
	int compareTo(Object other) {
		// return < 0 if this current object is less than the other.
		// return 0 if they are equal
		// return > 0 if the current object is greater than the other.
	}
}

public class MyComparator implements Comparator {
	
	int compare(Object object1, Object object2) {
		object1.compareTo(object2);
	}
}
```

### Using Collections

The Collections interface has 2 main methods -

- boolean add(Object obj); // Add an object to the collection.
- Iterator iterator(); // Allows you to loop over the Collection.

The **iterator()** method returns an Iterator object, that has three main methods

- Object next(); // The next object in the Collection.
- boolean hasNext(); // Whether there is a next object.
- void remove(); Removes the element that was returned by the last call to next().

Your typical iterator would function like this - 

```
Iterator iterator = collection.iterator();
while (iterator.hasNext()) {
	
	Object obj = iterator.next();
	// Do something with the object.
}
```

### Collection Methods

The Collection framework introduced in Java 5, provides generic methods to access information in a collection without having to typecast.

- boolean add(T o) - Add the element to the collection.
- void clear() - remove all entries from the collection.
- boolean contains(T o) - returns true if the collection contains the element o
- boolean isEmpty() - returns true if the collection is true.
- Iterator iterator() - returns an Iterator object allowing to go over all the elements in the collection.
- boolean remove(T o) - Remove a single instance of an element, if it exists.
- T[] toArray() - returns an array of all the elements in the collection

### List Methods

Aside from the Collection methods that it inherits, the List interface provides a few of its own

- T get(int position) - return the object at the specified index.
- add(int position, T el) - add an element to a specified position.
- T remove (int position) - remove the element at the index, returns the object that was at the index.

### Map Methods

Aside from the Collection methods that it inherits, the Map interface provides a few of its own

- V get(K key) - return the object V associated to a given key K.
- V put(K key, V value) - add the object V to the map, against the key K.
- V remove(K key) - remove the data associated to the key K, return the object V that was removed.
- boolean containsKey(K key) - returns true if there is a Object V associated to the Key K.
- Set<K> keySet() - returns a collection of all the Keys in the Map.
- Collection<V> values() - Returns a collection of all the Values in the Map.

### Auto Boxing

Is the process in Java that automatically converts between objects and primitives wherever possible, that works for Integer, Float, Double, etc.

### Thread Safe Collections

Vector is a thread safe ArrayList, making it really slow since all the methods internally to it was synchronised.

None of the new Collections classes are thread safe, meaning that to make them, we need to wrap them in another object and provide access to that collection through sychronised method calls.

The Collections static classes provide a bunch of methods to return synchronised version of a collection, however we need to be careful when iterating over it, as we need to specifically put it in a synchronise on it.

```
List list = Collections.synchronisedList(new ArrayList());
// ... adding and removing is thread safe.

synchronised(list) {
	Iterator i = list.iterator(); // This iterator must exist within a synchronised block
	while (i.hasNext()) {
		doSomething(i.next());
	}
}
```

We need to be careful to ensure that no thread accesses the synchronised data structure through its original unsynchronised methods. The easiest way to do this is to not save any reference to the original object, but to pass the constructed collection to the wrapper.

The wrapper will only wrap the interface not the concrete implementation, and doing that only allows us access to the methods in the interface.

### Scanner (I/O Improvements)

The Scanner class reads free formatted text and parses it -

- nextInt() - reads the next integer as an integer.
- nextDouble() - reads the next double as a double.
- nextLine() - reads the entire line till the '\n'.
- next() - reads the next word as a String, so it is everything up to the white space.

The Scanner class can attach existing streams to it -

```
Scanner scan = new Scanner(System.in);
Scanner scan = new Scanner(new File("some_file.txt");
```

We can also use a BufferedReader to buffer the input before we pass it onto the Scanner

```
BufferedReader input = new BufferedReader(new FileReader("some_file.txt"));
Scanner scan = new Scanner(input);

scan.nextInt();
scan.nextDouble();
scan.hasNextInt();
scan.hasNextLine();
// etc etc

scan.useDelimiter(String regexp); // Allows you to split the line against anything.
```