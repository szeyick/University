## Threads

Multithreaded programs extend an operating systems idea of multitasking. It allows individual programs to appear to be doing multiple things at the same time. Each of these tasks is called a **thread**.

**What is the difference between multiple processes and multiple threads?**

- A process has its own complete set of variables.

- A thread shares the same heap but separate stack, so there is a risk of concurrently updating the same variables.

### Thread Advantages

- Creating and destroying threads is more efficient than doing the same with processes. 

- Communicating between threads is also faster than communicate between processes.

- Easier to create threads in Java as garbage collection is automatic.

- In single threaded applications, nothing can interfere with the current executing task until it has finished.

### Adding Threads

Threads can be added by creating a new **Runnable()** and a **Thread** object. The runnable defines the task that is going to run on the thread.

```
class BallRunnable implements Runnable 
{
	...
	public void run() {
		try {
			while (true) {
				ball.move(component.getBounds()); // Calculate the new position within the panel.
				component.repaint(); // Requests the AWT-Thread to redraw the panel
				Thread.sleep(DELAY);
			}
		}
		catch(InterruptedException e) {
			...
		}
	}

}
```

The **Thread()** object creates and controls a thread (start(), interrupt()). The run() from the **runnable()** object defines the life of the thread.

### Thread States

Threads can exist in 4 major states

![alt text](https://github.com/szeyick/University/blob/master/COS80007%20-%20Advanced%20Java/Lectures%20Notes/Week%205%20-%20Threads/images/threadCycle.png "Thread States")

#### New

This state is when the thread is created, it is currently doing nothing.  Included in this is the threads **start()** method that does some initial thread setup work including memory allocation and thread setup.

#### Runnable

Once the threads **start()** has been invoked, it goes into the **runnable** state. In this state, the thread is **ready** to be run but isn't neccessarily running. The JVM gives each thread in this state a slice of time to execute its task after which it goes onto the next thread.

#### Blocked

A thread can enter this state if one of the following happens -

- The thread calls **sleep()**
- The thread calls **wait()**
- The thread calls an operation that is blocked on input or output and cannot continue until these operations are complete.
- The thread tries to access an object currently locked by another thread (being used).
- The thread calls **suspend()**

When transitioning from **blocked** to **runnable**, the thread must go back along the path that lead it to being blocked. This means if **sleep()** was called, for it to get out of blocked, it must go through it again. Likewise if the thread was told to **wait()**, the thread it was waiting on must call **notify or notifyAll**.

#### Dead

A thread transitions to this state if it successfully finishes its **run()** execution. It can also transition to this state if an exception terminates the run, or is stopped.

Returning from run is the only safe way to terminate a thread, the other ways are frowned upon. This means that within the run, we should check every so often whether we should terminate the thread.

### Interrupting Threads

Threads should generally not be allowed to run continuously as they should allow other threads to run. This is usually not an issue if it is run within an operating system that manages thread times but you should call **yield()**, **sleep()** or **wait()** regularly. 

Another thread can also call **interrupt()** on the target thread control object to unblock the thread.

If a thread was in the **runnable** state and the interrupt is called, it does not cause an interrupt exception until the call to **wait()** or **sleep()**.

### Thread Priority

All threads inherit their priority from their parent thread - 

- NORM_PRIORITY is 5
- MIN_PRIORITY is 1
- MAX_PRIORITY is 10

The **setPriority()** allows a thread to change priority. The thread scheduler decides which thread to execute next based on the priority and if its in the **runnable** state. If there is a tie in this calculation of which thread to execute, each thread gets a turn.

### Running Threads

Threads will run until they are no longer in the runnable state because they died, became blocked or is yielded through the **yield()** method. This thread is then replaced by the next thread that is scheduled to run.

### Thread Groups

Threads can be grouped and controlled together. 

A thread group can be constructed together with this -

```
ThreadGroup tg = new ThreadGroup(idString)
```

Where the idString identifies the group. This allows you to assign a newly created thread to a group. We can find out information about a group of threads with -

```
if (tg.activeCount() == 0) {
	...
}
```

To interrupt all the threads within the thread group -

```
tg.interrupt();
```

### Synchronization

This is where threads share the same data segment. It will allow a thread to access any object which it has a reference to, this also means that two threads can reference the same object.

The CPU can switch threads at any time and can be run at the time if there are multiple cores. As they can run simultaneously, there can be instances where threads interfere with each other.

Because threads can access the same parts of data at the same time, there can be instances where the data is corrupted. The prevent this we can use the **synchronised** tag.

```
public synchronized void transfer(int from, int to, int amount) {
	accounts[from] -= amount;
	accounts[to] += amount;
}

or by synchronising a code block 

synchronise (this) {
	...
}
```

For code blocks that have the synchronised tag, it ensures that a method will finish running before another thread can execute the same method or use the same object. It **locks** the object, and every object has this capability.

If a thread is told to wait, it loses its lock on an object and goes back into the waiting list. An object that has synchronised methods is called a **monitor** as it can control access to threads.

```
public synchronized void transfer(int from, int to, int amount) {
	try {
		while (accounts[from] < amount) {
			wait(); // Tells a thread to wait until another thread changes the balance.
		}
			accounts[from] -= amount;
			accounts[to] += amount;
			ntransacts++;
			notifyAll(); // Notifies all the threads currently blocked.
			...
	}
	catch (InterruptedException e) {
		...
	}
}
```

As a rule, the **wait()**, **notify()** and **notifyAll()** can only be invoked from synchronised methods.

### Stopping Threads

The **stop()** method will terminate a thread immediately. However this method is not used as stopping a thread immediately can leave objects in weird states. The only safe way to stop a thread running is to let it finish.

### Suspending and Resuming Threads

The **suspend()** and **resume()** method will not cause the same damage to objects by can cause deadlocks. This happens if you suspend an object that has a lock on an object, this object remains unavailable until resume is called. It will deadlock if the method aquires the same object before calling resume as it cannot gain access as it is waiting for it to be unlocked.

### Threads and Swing

Swing is delibrately written so that it is not thread safe. However it can be by following some simple rules

After swing components are displayed, they should only be modified by code executed in the event handling thread.

If for whatever reason another thread needs to modify a swing component,  you can use the **SwingUtilities.invokeAndWait(Runnable target)** or **SwingUtilities.invokeLater(Runnable target)** that will invoke it safely. 

The invokeAndWait() method puts the queue onto the event queue and waits until it is executed before returning.

The invokeLater() method returns immediately.
