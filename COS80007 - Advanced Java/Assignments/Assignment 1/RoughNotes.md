## Assignment 1 Rough Notes

- Continuous Simulation

This is part of the application that should run continuously.

- Swing Timer

A javax.swing.Timer will be used to update the system, this thread will trigger updates. Each object in the simulation will have a time state, with **void update(long dt)** implemented, where dt is in milliseconds.

- Lift

The lift does not need to be overly detailed, but must include **2 LockingClamps**, which will lock it to the current floor and lock the shuttle when the lift is moving.

- Trolleys

The trolley also does not need to be overly detailed, the classes that represent the trolley should know how to move, how far they have moved and when to stop. The moving parts for the trolley should only have 3 speeds, 0, +v, -v.

- Cars

The cars can drive forward. They will be visible in the forecourt, then lined up to enter the turntable, they do not require turning.
The cars should only be viewed from top down only and shown as an outline, different colours could be nice.
The details of a car in a bay should be visible with right click or mouse over (tooltip).

- Wheelstop

The wheelstop on the turntable does not need to be simulated.

- Coordinate Directions

X is along an aisle.
Right/East on the plan view on the screen.
Y is traverse to the aisle, updwards on the screen or north.
Z is upwards, zero at pavement level of floor 0.
The parked car is facing south.

- Parking Bay Layout

The parking bay layouts are all the same except for floor 0.
The user shall also be able o view a plan of any floor (e.g. Using a tabbed pane).
When a lift stops at a floor, that floor should automatically be visible.

- Driver 

The driver does not need to be animated, however the system should simulate adequate time between the red light going on and the driver using the UserPoint. This should be the same for when the driver wants to collect the car.

- Traffic

The number of cars waiting **outside** to come into the carpark and the number of drivers waiting to collect their cars should be displayed.

- User Point

The userpoint has 2 buttons, park and collect. The driver enters a 4 digit pin that represents the number plate.

Each card that is swiped should have a different colour. If the pin is invalid, as in the car is already there or is not there to collect, then error message is presented.

- Simulated Traffic

A 10.0 5 0.6
D 20.0 1 0 

This means -

10 minutes after the start, one car arrives and 4 others follow in intervals of 0.6 minutes.
20 minutes after the start, one driver requests collection of their car.

There can be many of these lines, in non-descending order of minutes, meaning that there can be overlaps. It is up to a priority queue to figure out how this is ordered.

- Number Plates

The cars arriving can have their number plates allocated sequentially.
The cars departing can have their number plates randomly chosen from the parked cars not requested to leave.

- GUI

Should display the running time starting from 8:00:00 and speed factor (initially 1.00). There should be a place/pause button and a way to change the speed. It should have a limit on how fast it can be sped up or slowed down.

- Boomgate

When the gate starts closing, a user can click near the door. The door should immediately change to opening, complete opening then wait 2 seconds before closing again.

- Statistics

Continuous display of stats for the run, total number of cars, cars collected and maximum collection time.

Collectino time is measured from pin entry to the driver allowed to collect the car. It ignores the time the person queues at the user point.

The time for collection should be displayed continuously for the current car being moved.

- Main Class

Should be named APS in the default package, packages should be named accordingly.

- Configuration Data

Should be managed by a class aps.Config and read from a configuration file named APS.properties using the Properties class.