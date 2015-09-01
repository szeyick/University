## Key Facts and Terminology

- **Thread**

A individual task that a program can run. Multiple threads gives the impression that the computer is doing tasks simultaneously. Threads share the same heap, but have a separate stack.

- **Processes**

A process is the executing program. It can contain many different threads. Processes have their own individual set of variables.

- **Runnable**

In a Java application, a runnable is the object that manages what the thread is going to do. It contains the application code that the thread is to run.

- **Thread()**

The actual thread that will host the runnable task. It controls the life cycle of the thread.

- **Thread States**

Threads can exist in 4 different states, new, runnable, blocked and dead. 

- **New Thread**

A thread that has been initialised. In this state, it is not running.

- **Runnable**

A thread can be executed, but it does not meant that it is running. A thread in this state is given a slice of time to run and complete its task.

- **Blocked**

A thread is blocked, as it is not running, waiting or suspended. Threads in this state are not doing anything until they are told otherwise. A thread will transition out of this state and back into the runnable state by going back the same way that lead it to this blocked state.

- **Dead**

A thread that is no longer running or has died because of an exception.

- **Interrupt**

A thread can be interrupted as a means to stop it from running continuously. Threads should not be run continuously as it stops other threads from being able to be executed.

- **Thread Priority**

Thread priority allows the scheduler to determine which thread to execute next. A thread is determined based on this priority and also it being in the runnable state. If there is a tie in which thread is to run next, it will take it in turns through a round robin algorithm.

- **Running Threads**

Threads are run until they finish executing or when it is terminated, or somehow ended up in the blocked state. The next thread is then loaded in to run next.

- **Thread Groups**

Thread groups are objects that allow you to group a bunch of runnable tasks together so that they can be executed and controlled together. Interrupting the thread group will interrupt all the threads within it.

- **Synchronisation**

Is important because threads share the same data segment. This means that two threads can access an object or method at the same time which can lead to some inconsistent objects and results. Adding the syncrhonisation keyword to a method or object ensures that a thread runs through the method or finishes with the object first before another thread gains access to it.

- **Monitor**

The term applied to an object that has synchronisation methods.

- **Stopping Threads**

Threads should generally be allowed to run to finish, interrupted but never stopped by using the **stop()** method. This is a deprecated method that should not be used as it stops the thread immediately. This is bad because the thread can leave some objects in some weird states.

- **Suspending and Resuming Threads**

A safer way to stop/start threads is to use the suspend and resume methods. This will pause the thread at its current execution that will only resume if the method is called to resume. This can lead to deadlocks because the thread may want to access the same object again, but cannot do so because it has already locked it but also cannot resume.

- **Deadlock**

In concurrent programming, a deadlock is a situation in which two or more competing actions are each waiting for the other to finish, and thus neither ever does.

**Threads and Swing**

Swing is written to be non thread safe, however we have some SwingUtilities to allow us to execute Swing components on threads other than the EventQueue. The general rule would be to do as much work on displayed components on the event queue as possible and use the SwingUtilities classes if it is absolutely neccessary to do so.