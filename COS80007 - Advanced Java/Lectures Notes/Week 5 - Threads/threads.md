## Threads

Multithreaded programs extend an operating systems idea of multitasking. It allows individual programs to appear to be doing multiple things at the same time. Each of these tasks is called a **thread**.

**What is the difference between multiple processes and multiple threads?**

- A process has its own complete set of variables.

- A thread shares the same heap but separate stack, so there is a risk of concurrently updating the same variables. This also means that threads can have the same access to the data.

### Thread Advantages

- Creating and destroying threads is more efficient than doing the same with processes. 

- Communicating between threads is also faster than communicate between processes.

- Easier to create threads in Java as garbage collection is automatic.

- In a single threaded application, nothing can get in the way of the current execution until it has finished. The program cannot even quit.

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

It is entirely possible to subclass Thread and override the run() method within the subclass rather than creating a new runnable, since the Thread.run() delegates the run to the Runnable anyway, however it is better practice to separate the Runnable (task) from the mechanism that is responsible for executing it (Thread).

### Thread States

Threads can exist in 4 major states

![alt text](https://github.com/szeyick/University/blob/master/COS80007%20-%20Advanced%20Java/Lectures%20Notes/Week%205%20-%20Threads/images/threadCycle.png "Thread States")

#### New

This is the initial state for all Threads when they are created. In this state, the Thread is not running yet, it is essentially just saying that it is a NEW Thread. It needs to allocate memory, do some book keeping and set up the Thread object ready for the CPU to exeucte it. 

#### Runnable

This is the state that the Thread transitions into after the **start()** method is called. In this **RUNNABLE** state, the Thread may not be running but it is telling the CPU that it is **ready**.

In this state, the Thread can either be running or not running, the Java documentation does not differentiate between the running and ready to run states and classifies them both as RUNNABLE.

For Threads that are in this state, the JVM gives it a slice of time to execute whatever it needs to do, before going onto the next runnable thread.

Execution of a Thread in the runnable state can be stopped if is put into the blocked or yield states.

#### Blocked

A thread can enter this state if one of the following happens -

- The thread calls **sleep()**
- The thread calls **wait()**
- The thread calls an operation that is blocked on input or output and cannot continue until these operations are complete. (i.e. waiting on user)
- The thread tries to access an object currently locked by another thread (being used).
- The thread calls **suspend()** (SHOULD NOT BE USED)

If a Thread finds itself in the **BLOCKED** state and wants to go back to **RUNNABLE** to continue execution, it needs to take the opposite route that it took to get into the BLOCKED state.

This means - 

- If a thread has called **sleep()**, it needs to wait till the sleep expires before returning to RUNNABLE.
- If a thread is waiting on input or output operations, it needs to wait until those are executed before returning to RUNNABLE.
- If a thread has called **wait()**, then it needs to wait until another thread has called **notify()** or **notifyAll()** before returning to RUNNABLE.
- If a thread is trying to access an object that another thread has locked (through synchronisation), it needs to wait until the lock is removed before returning to RUNNABLE.
- If a thread has called **suspend()**, then another thread is required to call **resume()** on it before it can return to RUNNABLE. (SHOULD NOT BE USED)

#### Dead

A thread transitions to **DEAD**, if it has successfully completed its task in the **run()** method. It however can also transition to this state if an exception terminates the run method or it is stopped.

It is the only safe way to terminate a Thread since we're partially guaranteed that all the objects that it uses has been cleaned up  properly and the data is changes is completely changed. 

### Interrupting Threads

Threads should generally not be allowed to run continuously as they should allow other threads to run. This is usually not an issue if it is run within an operating system that manages thread times but you should call **yield()**, **sleep()** or **wait()** regularly. 

A Thread that has had **sleep()** called on it, cannot check its own state therefore it does not know if it should terminate or not. However another Thread can call **interrupt()** on the sleeping thread to unblock it. If it was in the **BLOCKED** state, an InterruptException will be called on it.

If a thread is in the RUNNING state, and someone calls **interrupt()** on that thread, an InterruptExeception will not be raised until either **wait()** or **sleep()** has been called on the thread.

In this instance we can use the **interrupt()** method to check if someone has called interrupt on that particular thread. This method exists in the **Thread** class.

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

- **If a thread is in the middle of executing a section of code and needs some other resource or value, it can call wait(). By calling wait() it surrenders its lock on the object and goes back into the waiting list for the object**

- An object that can control how a thread accesses it is called a **monitor**. Usually objects that are synchronised or have synchronised methods are called monitors.

- **It is important that some thread calls notifyAll() otherwise all threads that have entered the wait() state will wait forever. 

### Stopping Threads

The **stop()** method will terminate a thread immediately. However this method is not used as stopping a thread immediately can leave objects in weird states. The only safe way to stop a thread running is to let it finish.

It immediately gives up all the objects it has a lock on.

### Suspending and Resuming Threads

The **suspend()** and **resume()** method will not cause the same damage to objects by can cause deadlocks. This happens if you suspend an object that has a lock on an object, this object remains unavailable until resume is called. It will deadlock if the method aquires the same object before calling resume as it cannot gain access as it is waiting for it to be unlocked.

### Threads and Swing

Swing is delibrately written so that it is not thread safe. However it can be by following some simple rules

After swing components are displayed, they should only be modified by code executed in the event handling thread.

If for whatever reason another thread needs to modify a swing component,  you can use the **SwingUtilities.invokeAndWait(Runnable target)** or **SwingUtilities.invokeLater(Runnable target)** that will invoke it safely. 

- The invokeAndWait() method puts the queue onto the event queue and waits until it is executed before returning.

- The invokeLater() method returns immediately.

If we need to update Swing components (i.e. drawing, label updates, etc) we should use the invokeAndWait() or invokeLater() rather than creating a new thread to do it. We should do all this within the AWT-Event-Queue. We can push other complex calulations into different threads.
