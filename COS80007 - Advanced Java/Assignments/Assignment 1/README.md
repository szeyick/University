## Case Study

### Notes

- **Underline the noun**

Find the possible objects to use/create by underlining each noun or noun phrase.

- **Identify Causal Objects**

Find sources of actions, events and messages (to send) and things that can control those actions. Find the things that sends messages and causes things to happen, these will translate into method calls, object command queues.

- **Identify Services**

Find the things that will respond to messages, actions or events. Also things that will return some data or information when asked (possible getters).

- **Identify Messages and Information Flow**

Find the sender, recipient and the types of messages that are sent, draw it up into a table.

- **Identify Real World Items and Physical Devices**

Draw the diagram that represents the case study.

### Automatic Car Loader

- **Nouns**

Car, Forecourt, Turntable Room, Mirror, Green Light, Front Wheels, Wheel-Stop, Red Light, Driver, Handbrake, Engine, Credit Card, User Point, Pin Number, Boom Gate, Prongs, Display Screen, Beeper, Supervisor, Alarm, Sliding Door, Trolley, Shuttle, Destination Floor, Lift, System, Human Friendly Bay, Lift Cage, Supervisor Console, User Point.

- **Causal Objects**

Green Light, Red Light, Car, Driver, User Point, Wheel-Stop, Turntable, Sliding Door, Trolley, Shuttle, Prongs, Lift, System.

- **Identifying Services**

Car, Driver, Turntable, Sliding Door, User Point, Display Screen, Trolley, Shuttle, Lift Cage.

- **Identifying Messages and Information Flow**

| Message Source        | Message Target           | Message                        |
| :-------------------- |:------------------------:| :-----------------------------:|
| Car (Car Wheel)       | Wheel Stop               | Am I Far Enough Over Turntable |
| Wheel Stop            | Indicator Light          | Turn Green or Red              |
| Indicator Light       | Driver                   | Keep Moving Forward / Stop     |
| Display Screen        | Driver                   | Reverse Car / Forward Car      |
| Display Screen        | Driver                   | Turn Car Left / Turn Car Right |
| Beeper                | Driver                   | Keep Moving Forward/ Stop      |
| Alarm                 | Supervisor               | Assist Driver                  |
| Alarm                 | Supervisor               | Unknown Issue With Car         |
| Supervisor            | Driver                   | Move Forward / Backward etc    |
| Driver                | Car (Accelerator)        | Do I Move Forward or Backwards |
| Driver                | Car (Steering Wheel)     | Do I Turn Left or Right        |
| Driver                | Car (Handbrake)          | Engage/Disengage Handbrake     |
| Driver                | Car (Engine)             | Stop Engine                    |
| Driver                | Driver (Disembark)       | Is It Safe To Disembark Car    |
| Driver                | Car (Lock)               | Lock Car                       |
| Driver                | User Point (Swipe)       | Swipe Card (Card Details)      |
| Driver                | User Point (Pin)         | Provide Pin Number             |
| User Point            | Boom Gate (Turn Table)   | Open/Close Boom Gate           |
| User Point            | Driver                   | Account Accepted (Leave)       |
| System                | Wheel Stop               | Raise / Lower                  |
| System                | Turntable                | Align With Lift                |
| Turntable             | System                   | Aligned With Lift              |
| System                | Lift Door                | Open / Close                   |
| System                | Shuttle                  | Mobilise Trolley               |
| System                | Trolley                  | Move Under Car / Centre Table  |
| Trolley               | System                   | Under Car                      |
| Trolley               | Prongs                   | Prepare For Lift / Enter Slot  |
| Trolley               | System                   | Ready To Lift Car              |
| System                | Trolley                  | Lift Car                       |
| Trolley               | Prongs                   | Engage Prongs To Lift Car      |
| Prongs                | Trolley                  | Car Raised                     |
| Trolley               | System                   | Car Successfully Raised        |
| System                | Shuttle                  | Recall Trolley                 |
| Shuttle               | Trolley                  | Retract                        |
| Trolley               | Shuttle                  | Returned To Base               |
| System                | Shuttle                  | Move To Lift                   |
| Lift                  | System                   | Recieved Shuttle               |
| System                | Lift                     | Move To Floor n                |
| Lift                  | System                   | Arrived At Floor               |
| System                | Floor                    | Get Empty Bay                  |
| System                | Lift                     | Move To Bay (Move Across n)    |
| Lift                  | System                   | Arrived At Bay n               |
| System                | Shuttle                  | Unload Car                     |
| Shuttle               | Trolley                  | Unload Trolley Into Bay        |
| Trolley               | Bay                      | Trolley Within Bay Bounds      |
| Trolley               | Shuttle                  | In Position                    |
| Shuttle               | Trolley                  | Retract Prongs                 |
| Shuttle               | Trolley                  | Recall Trolley                 |
| Driver                | User Point               | Retrieve Car                   |
| User Point            | Driver                   | ETA On Car Recall              |
| System                | Lift                     | Move To Foor n                 |
| System                | Shuttle                  | Move To Exit Bay               |
| System                | Driver                   | Safe To Collect Car            |
| Turn Table            | Prongs (4 Set)           | Align Car                      |

Here the **System** object will function as the **control**. 

- **Objects**

Car

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Wheel                 | Wheel                      | 
| Hand Brake            | Boolean (On / Off)         |
| Lock                  | Boolean (Locked / Unlocked |
| Steering Wheel        | Steering Wheel             |
| Seat                  | Boolean (Driver In/Out     |
| Engine                | Boolean (Start / Stopped   |

Driver

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Card                  | Card  (Contains Pin)       | 

Wheel Stop

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Touching Car Wheel    | Boolean (Yes / No)          | 
| Raised/Lowered        | Boolean (Raised / Lowered  |

Display Screen 

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Command               | String (Command To Driver) | 

Indicator Light 

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Colour                | Colour                     | 

Alarm

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Notify Driver         | Beep                       | 
| Notify Supervisor     | Beep                       | 

User Point 

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Card Number           | Integer                    | 
| Pin Number            | Integer                    | 

Boom Gate

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Up / Down             | Boolean (Up / Down)        | 

Turn Table

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Aligned Lift         | Boolean (Yes / No)          | 
| Aligned Park         | Boolean (Yes / No)          | 

System / Controller

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| States                | Current Device States      | 

Shuttle

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Trolley Retrieved     | Boolean (Yes / No)         |

Trolley

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Prongs Deployed       | Boolean (Yes / No)         |
| Prongs Raised         | Boolean (Yes / No)         |

Lift

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Door  Open            | Boolean (Yes / No)         |
| Current Floor         | Integer                    |
| Recieved Payload      | Boolean (Yes / No)         |

Bay

| Attributes            | Type                       |  
| :-------------------- |:--------------------------:| 
| Bay Full              | Boolean (Yes / No)         |
