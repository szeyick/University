package com.plant.uml;

/**
 * @startuml

interface Runnable
interface IEventManagerListener 

Runnable o-- EventManager  : implements
EventManager "1" -- "*" IEventManagerListener
APSControl o-- IEventManagerListener : implements

note top of EventManager 
for all IEventManagerListener in EventMananger update()
end note

@enduml 
 */
public class EventManager {

}
