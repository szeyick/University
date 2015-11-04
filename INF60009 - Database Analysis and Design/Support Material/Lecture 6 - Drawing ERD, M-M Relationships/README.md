## Entity Relationship Diagrams

### Support Questions

- **1. Draw a fully expanded ERD solution based on the narrative**

Identifying Entities : 

Students (a group) // Not relevant to the database
Person (to survey)
TV Show

Tick Favorite Show From List of TV Shows (0 or more favorite)

Attributes :

TV Show - Code, Title
Person - personID, name

Cardinality :

ONE TV SHOW MAY BE LIKED BY MANY PERSONS
ONE PERSON MAY LIKE MANY TV SHOWS

TV SHOW >O-- BE LIKED O< PERSON

Expand Many to Many Relationships :

TV SHOW +---o< FAVORITE >o---+ PERSON

Test Data :

PERSON 
PersonId 	| Name
34				John
23				Gary
10				Fred

TV SHOW
Code 		| Title
1				Friends
2				Castle
3				NCIS

FAVORITE
Code 		| PersonId
1				34
2				34
3				34
1				23
2				10
3				10

Although the Code column in the FAVORITE table repeats, the PK includes the PersonId so it should be unique.

The narrative states that they are given a list of TV shows and they select which on is their favorite.

- **2. Convert the ERD to a Relational Schema**

TV SHOW (Code, Title)
	PK (Code)

PERSON (PersonId, Name)
	PK (PersonId)

FAVORITE (Code, PersonId)
	PK (Code, PersonId)
	FK1 (Code) REFERENCES TV SHOW
	FK2 (PersonId) REFERENCES PERSON

- **3. Draw a fully expanded ERD solution based on the narrative.**

Entities:

PROGRAMMERS
CUSTOMERS (various)
WORKED

Attributes:

CUSTOMERS - customerId, customerName
PROGRAMMER - programmerId, programmerName
TIME WORKED - hoursWorked, forCustomer (Weak Entity)

Relationships :

Programmer >o--WORK FOR--o< Customer

ONE PROGRAMMER MAY WORK FOR MANY CUSTOMER
ONE CUSTOMER MAY RECEIVE WORK FROM MANY PROGRAMMERS

Programmer +---o< TIME WORKED >o---+ Customer

Dummy Data:

Programmer:
programmerID 	|	programmerName
42						Darrel
45				| 		Bob
50				| 		Jules

Customer:
customerID 		| 	customerName
1					Blizzard
2					Activision
3					Konami

Time Worked
programmerID 	| 	customerID 		| Hours Worked 	| Date
42						1					7			10/10
42						2					7			10/10
42						1					3			11/10
48						3					2			10/12
50						1					1			10/10

The data in the time worked column fulfills the business narrative and is also uniquely identifiable when the primary keys are all entered. The primary key in this instance will never be NULL since the data is always entered unless someone is not doing any work.

- **4. Convert the ERD to Relational Schema**

PROGRAMMER (programmerID, programmerName)
	PK (programmerID)

CUSTOMER (customerID, customerName)
	PK (customerID)

TIME WORKED (programmerID, customerID, date, hoursWorked)
	PK (programmerID, customerID, date)
	FK1 (programmerID) REFERENCES PROGRAMMER
	FK2 (customerID) REFERENCES CUSTOMER

The date column is added as the PK to allow a Programmer to work on the same project but on a different day. If it were not part of the PK, then each time the Programmer worked for the same customer, it would not allow the row to be entered.