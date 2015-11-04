package com.plant.uml;

/**
 * @startuml

JPanel <|-- "1" UserStationPanel : extends
UserStationPanel "1" *-- "1" AdditionalInfoPanel : contains
UserStationPanel "1" *-- "1" UserInputPanel : contains
UserStationPanel "1" *-- "1" UserButtonPanel : contains

UserButtonPanel "1" *-- "1" DropOffButton : contains
UserButtonPanel "1" *-- "1" PickupButton : contains
UserButtonPanel "1" *-- "1" CarLoadedButton : contains
UserButtonPanel "1" *-- "1" DepartButton : contains

UserInputPanel "1" *-- "1" UserInputField :c contains
UserInputPanel "1" *-- "1" RegistrationLabel : contains

AdditionalInfoPanel "1" *-- "1" CarInfoLabel : contains 

DropOffButton "1" *-- "1" CreateDropOffEventAction : contains
PickupButton "1" *-- "1" CreatePickupEventAction : contains
CarLoadedButton "1" *-- "1" LoadCarAction : contains
DepartButton "1" *-- "1" DepartCarAction : contains


@enduml 
 */
public class UserStationPanel {

}
