package com.plant.uml;

/**
 * @startuml
[*] -> Elevator

state Elevator { 
	state "Idle" as idleState
	state "Moving" as movingState
	state "ArrivedAtFloor" as arrivedAtFloor
	idleState --> movingState : [elevatorState = MOVING]
	movingState --> UP : [currentFloor < targetFloor]
	movingState --> DOWN : [currentFloor > targetFloor]
	UP --> arrivedAtFloor : [currentFloor == targetFloor]
	DOWN --> arrivedAtFloor : [currentFloor == targetFloor]
	
}

state ElevatorDoor {
	state "Opening" as opening
	state "Closing" as closing
	
	opening --> Opened : [t = 3s]
	closing --> Closed : [t = 3s]
	
	
	[*] --> opening : [doorState = OPEN]
	[*] --> closing : [doorState = CLOSE]
}

state Shuttle {
	state "Idle" as idle
	state "Deploying" as deploying
	state "Deployed" as deployed
	state "Returning" as returning
	state "Returned" as returned
	
	[*] --> deploying : [state = DEPLOY]
	deploying --> deployed : [shuttleCentreX == carCentreX]
	
	[*] --> returning : [state = RETURN]
	returning --> returned : [shuttleCentreX == elevatorCentreX]
	
	returned --> idle : [state = IDLE]
}

state Trolley {
	state "DockedShuttle" as idle
	state "Locked" as locked.
	state "Locking" as locking
	state "Deploying" as deployingTrolley
	state "Returning" as returning
	
	[*] --> deployingTrolley : [shuttleState = DEPLOY]
	deployingTrolley --> locking : [trolleyCentreX = carCentreX]
	locking --> locked : [t = 1s]
}

state Comb {
	state "Unlocking" as unlockComb
	state "Locked" as lockedComb
	state "Unlocked" as unlockedComb
	state "Locking" as lockingComb
	
	[*] --> unlockComb : [combState = unlocking]
	unlockComb --> unlockedComb : [t = 0.3s]
	unlockedComb --> lockingComb : [t = 0.3s]
	lockingComb --> lockedComb : [t = 0.3s]
	
}


Elevator --> ElevatorDoor : [currentFloor == targetFloor && doorState = OPEN]
Elevator --> ElevatorDoor : [currentFloor == targetFloor && doorState = CLOSE]

ElevatorDoor --> Elevator : [doorState = OPENED || doorState = CLOSED]
Elevator --> Shuttle : [shuttleState = DEPLOY]
Shuttle --> Trolley : [trolleyState = DEPLOY && shuttleState = LOCKED]

Trolley --> Comb : [trolleyState = LOCKING && combState = UNLOCKING]
Comb --> Trolley : [combState = LOCKED]

Trolley --> Shuttle : [TrolleyCentreX = ShuttleCentreX]
Shuttle --> Elevator : [ShuttleCentreX = ElevatorCentreX]

 
@enduml
 */
public class StateTransition {

}
