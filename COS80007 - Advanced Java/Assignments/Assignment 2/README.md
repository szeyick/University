## APS Assignment 2

**Due Date: 28/10/15 22:00**

**Demo Date: 27/10/15**

**Research Report Due Date: 01/11/2015**

### Overview 

This assignment is an extension from assignment 1. The main changes for this assignment include -

- Add a JTable view of **cars** and a Java3D view of the system.
- A small number of threads to be used aside from the AWT-Event thread.

### Extension Requirements

- Create a new thread called SimTimer that takes over the timing tasks.
- Create a new thread called TrafficGenerator that handles the traffic generation.
- The lights (red and green) are required by the car entering the turntable room.
- The trolley combs are required to be displayed.
- Addition of a Java3D view of the system within the main frame.
- The building in the 3D view should be a wireframe.
- The components (lift, shuttle, trolley, door, gate, etc) to be shown as solid boxes.
- The cars should be shown as solid models.
- The Java3D view should provide zoom, pan, rotation, etc.
- The trolley shall be red, the combs yellow.
- A separate JFrame should contain a JTable showing **all** cars, number plate, floor, bay number, etc.
- The JTable should be sortable by column headings and scrollable.
- Existing 2D plans should still be used to represent the cars
- The number plates of vehicles shall be visible on the plan view and interactive.
- The pin to access the parking station will now be the number plate that is generated. The user can press a button on the simulation to signify that a user has exited their car and entered their details into the user point. A timeout should exist to then proceed to the next step.
- This same idea will be used to depart a car.
- The door to enter the exit bay is now required.
- Single step through the simulation is required.
- Continuous statistics display.
- Ouput to system out and timestamp to display significant events.
- Traffic generation from file.
- A parameter added to detail the number of cars already parked in the building before the simulation begins.

### Research Report

- How does the assignments relate to continous simluation.
- How does the assignments relate to discrete event simulation.
- How does the assignments relate to MVC.
- How would you do it differently if you could.

### Corrections From Assignment 1

- Correct layout and movement.
- Add turntable spinning
- Add boom gate
- Correct scaling and movement.
- Understand how G2 transform works
- Update graphics 
- Add current speed indicator.
- Trolley should have an instance of the current car.
- Classes with a view or panel should be moved to aps.gui
- Use Switch statement rather than bunch of IF
- UserStationControl should use >= delay and not == delay.

### TODO

- Get up to date with lectures.
- Rectify assignment 1 issues (particularly scaling and movement).
- Read how G2 transform is supposed to work.
- Retest assignment to ensure it still works.
- Breakdown assignment 2 tasks.