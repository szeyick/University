## Lecture 1 - Support Material

### Entities, Attributes and Identifiers

**Hints**

- The name of the entity is in the rectangle.
- Each of the balloons represents a single attribute.
- Any attribute that is underlined is part of the identifier.

- **List the name of each attribute in the ERD**

In an ERD, an attribute is the names that are above the balloons. If we consider this as a table, the attributes would represent an individual column name.

Thus in this ERD the attributes are as follows -

PERSON (PersonId, FirstName, Surname, YearStarted)

- **List the identifier of the ERD**

The identifier in a ERD, is the unique "key" that can identify an individual row in the entity. An entity is a "relation" or an individual table in a RDBMS.

The identifier is otherwise known as the **Primary Key**, and in an ERD is represented as an attribute with an underline. Thus in this ERD, the identifier is **PersonId**.

### Single Entity ERD to a Relational Schema Conversion

When converting an ERD to a Relational Schema do the following:
- Write the name of the entity in UPPERCASE letters.
- Write a left bracket
- Write the name of any underlined attribute (the primary key)
- Write the name of all the other attributes each separated by a coma.
- Write a right bracket.

- **Write the Relational Schema for the Person entity**

PERSON (PersonId, Firstname, Surname, YearStarted)
	PK (PersonId)

### Single Entity ERD to a Relational Schema Conversion

- **Convert this ERD to a Relational Schema**

PRODUCT (ProdId, CostPrice, Description, SellPrice)
	PK (ProdId)

### Relational Schema to Create Table SQL Statement

Consider the Relational Schema - PERSON (PersonId, Firstname, Surname, YearStarted)

**Hints** - When converting a Relational Schema to a Create Table SQL statement:
- Write CREATE TABLE followed by the name of the entity
- Write a left bracket
- Write the name of the identifier as the first column name
- Write the data type of the first column (i.e. varchar, number)
- Within brackets, write the maximum size of the length of data to be stored (i.e. Number(4), Varchar(30))
- Write the name, data type and size for the rest of the attributes
- Write the words PRIMARY KEY, followed by the name of the identifier in brackets, there needs to be a comma between the last column and the PK.
- Add a right brack and semi colon at the end of the statement.

- **Write the Create Table SQL statement above and verify in ISQL JR**

CREATE TABLE PERSON (
	PersonId NUMBER(10),
	Firstname VARCHAR(30),
	Surname VARCHAR(30),
	YearStarted NUMBER(4),
	PRIMARY KEY (PersonId)
);

### Drop Table SQL Statement

It is possible to accidentally create the wrong kind of table, to rectify mistakes and to start the table creation again we can use the DROP TABLE <TABLENAME> call.

- **Drop the table that you just created**

DROP TABLE PERSON;

### Relational Schema to Create Table SQL Statement

Consider the Relational Schema - PRODUCT (ProdId, Description, CostPrice, SellPrice)

- **Convert this schema into a Create Table SQL statement**

CREATE TABLE PRODUCT (
	ProdId NUMBER(10),
	Description VARCHAR(100),
	CostPrice NUMBER(4,2),
	SellPrice NUMBER(4,2),
	PRIMARY KEY (ProdId)
);

Presumeably in this instance the data types for the CostPrice and SellPrice could be NUMBER(4,2) to indicate that there can be decimal values.

### Inserting Rows into a Table

When inserting rows into a table, do the following -
- Write the word INSERT INTO followed by the table name.
- Write a left bracket
- Write the name of all the columns in the CREATE TABLE statement
- Write a right bracket
- Write the word VALUES followed by another left bracket
- Write the data value for every column separated by commas (Any value stored in the varchar() column is to be surrounded by '' single quotes)
- Write a right bracket.
- Add a semi colon.

- **Write an Insert SQL statement that adds these details to the Person Table that you have created**

Firstname - Sue
Surname - Davis
PersonId - 779
YearStarted - 2002

INSERT INTO PERSON (PersonId, Firstname, Surname, YearStarted) VALUES (779, 'Sue', 'Davis', 2002);

### Inserting Rows into a Table

- **Write two Insert SQL statements that adds these details to the product table**

ProdId: 1122, Description: 4GB USB stick, CostPrice: 5.00, SellPrice: 20.00
ProdId: 2256, Description Sunglasses, CostPrice: 6.00, SellPrice 34.99

INSERT INTO PRODUCT (ProdId, Description, CostPrice, SellPrice) VALUES (1122, '4GB USB stick', 5.00, 20.00);
INSERT INTO PRODUCT (ProdId, Description, CostPrice, SellPrice) VALUES (2256, 'Sunglasses', 6.00, 34.99);

### Single Entity ERD to Schema Conversion to Table with Data

- **Convert this ERD to a Relational Schema**

STAFF(StaffID, Name, Location, PhoneNo, Gender)
	PK (StaffID)

- **Write a Create Table SQL Statement**

CREATE TABLE STAFF (
	StaffID NUMBER(4),
	Name VARCHAR2(30),
	Location VARCHAR2(30),
	PhoneNo NUMBER(4),
	Gender VARCHAR2(1),
	PRIMARY KEY (StaffID)
);

- **Write an Insert statement to add these details to the table you created**

Name: Jack Dee, Location: BA1014, PhoneNo: 8653, StaffId: 193, Gender: M

INSERT INTO STAFF (StaffID, Name, Location, PhoneNo, Gender) VALUES (193, 'Jack Dee', 'BA1014', 8563, 'M');

### Relationships and Cardinality Symbols

**Hints**
- The line between two entities is called a **Relationship**
- The relationship line has a cardinality symbol (constraint) on each end.

The relationship is read in two directions, left to right and then right to left.

- Always begin the sentence by saying ONE instance of the entity on the left HAS then the cardinality constraint on the right side.
- Repeat for the other direction

- **Specify the relationship between the Subject and the Convenor by reading Left to Right**

ONE SUBJECT has ONE CONVENOR

- **Specify the relationship between the Subject and the Convenor by reading Right to Left**

ONE CONVENOR has MANY SUBJECT

### Two Entity ERD cardinality constraints

- **Specify the relationship between the entities by reading Left to Right**

ONE PRODUCT has ONE CATEGORY

- **Specify the relationship between the entities by reading Right to Left**

ONE CATEGORY has MANY PRODUCTS

### Two Entity ERD cardinality constraints

- **Specify the relationship between the entities by reading Left to Right**

ONE TEAM has MANY PLAYER

- **Specify the relationship between the entities by reading Right to Left**

ONE PLAYER has ONE TEAM

### Two Entity ERD to a Relational Schema conversion and Foreign Keys

**Hints**
- The MANY end of the ERD will have an additional column.
- This additional column must match the name of the Primary Key at the ONE end of the ERD
- Add a sentence to the schema that specifies the foreign key (i.e FK (columnName) references <ENTITYNAME>)

- **Write the schema at the entity of the ONE end of the relationship**

CONVENOR (ConvenorId, ConvenorName, Gender)
	PK (ConvenorId)

- **Write the entity and attributes at the MANY end of the relationship**

SUBJECT (SubjectCode, Title, ConvenorId)
	PK (SubjectCode)
	FK (ConvenorId) REFERENCES CONVENOR

- **What is the Primary Key of SUBJECT**

The primary key of SUBJECT is the attribute that has been underlined in the ERD - SubjectCode

- **What is the Primary Key of CONVENOR**

The primary key of CONVENOR is the attribute that has been underlined in the ERD - ConvenorId

- **Does CONVENOR have a Foreign Key column? If so, which column?

No it does not because it is not on the MANY end of the relationship. It only has a primary key.

- **Does SUBJECT have a Foreign Key column? If so, which column?

Yes it does because it is at the MANY end of the relationship. The Foreign Key is ConvenorId, which is the Primary Key of the CONVENOR entity.

### Two Entity ERD to a Relational Schema conversion and Foreign Keys

- **What is the Primary Key of PRODUCT**

The primary key of PRODUCT is the attribute that is underlined - ProdId

- **What is the Primary Key of CATEGORY**

The primary key of CATEGORY is the attribute that is underlined - CatId

- **Which entity is at the MANY end of the relationship?**

The entity at the MANY end of the relationship is the one with the Fork - PRODUCT

- **Which entity is at the ONE end of the relationship?**

The entity at the ONE end of the relationship is the one with the Cross - CATEGORY

- **When converted to a relational schema, which entity will have an additional Foreign Key Column?

When converted to a relational schema, the entity that will have the additional column will be the entity at the MANY end of the relationship. In this instance it is the PRODUCT entity.

- **Convert the ERD to a relational schema**

CATEGORY (CatId, CatName)
	PRIMARY KEY (CatId)

PRODUCT(ProdId, CostPrice, Description, SellPrice, CatId)
	PRIMARY KEY (ProdId)
	FOREIGN KEY (CatId) REFERENCES CATEGORY

The entity with no dependencies should be declared first otherwise the foreign key will not match up to another entity as it would not have been created yet.

### Two Entity ERD to a Relational Schema conversion and Foreign Keys

- **Convert the ERD to a relational schema**

TEAM (TeamId, TeamName)
	PRIMARY KEY (TeamId)

PLAYER (PlayerId, PlayerName, Salary, TeamId)
	PRIMARY KEY (PlayerId)
	FOREIGN KEY (TeamId) REFERENCES TEAM

### Two Relational Schema with Foreign Keys - Conversion to Create Table SQL

Consider the following relational schemas

CONVENOR (ConvenorId, ConvenorName, Gender)
	PRIMARY KEY (ConvenorId)

SUBJECT (SubCode, Title, ConvenorId)
	PRIMARY KEY (SubCode)
	FOREIGN KEY (ConvenorId) REFERENCES CONVENOR

- **Convert the relational schemas into CREATE TABLE statements**

CREATE TABLE CONVENOR (
	ConvenorId NUMBER(4),
	ConvenorName VARCHAR2(30),
	Gender VARCHAR2(1),
	PRIMARY KEY (CovenorId)
);

CREATE TABLE SUBJECT (
	SubCode NUMBER(7),
	Title VARCHAR(30),
	ConvenorId NUMBER(4),
	PRIMARY KEY (SubCode),
	FOREIGN KEY (ConvenorId) REFERENCES CONVENOR
);

### Two Relational Schema with Foreign Keys - Conversion to CREATE TABLE SQL

- **Convert the Relational Schema into CREATE TABLE SQL**

CREATE TABLE CATEGORY (
	CatId NUMBER(4),
	CatName VARCHAR2(30),
	PRIMARY KEY (CatId)
);

CREATE TABLE PRODUCT (
	ProdId NUMBER(4),
	Description VARCHAR(30),
	CostPrice NUMBER(4,2),
	SellPrice NUMBER(4,2),
	CatId NUMBER(4),
	PRIMARY KEY (ProdId),
	FOREIGN KEY (CatId) REFERENCES CATEGORY
);

### Insert data with Foreign Keys

- **Add the data in the table to the correct created tables**

INSERT INTO CONVENOR (ConvenorId, ConvenorName, Gender) VALUES (1, 'Fred Smith', 'M');
INSERT INTO CONVENOR (ConvenorId, ConvenorName, Gender) VALUES (3, 'Sue Davis', 'F');

INSERT INTO SUBJECT (SubCode, Title, ConvenorId) VALUES ('HBT1111', 'Accouting', 1);
INSERT INTO SUBJECT (SubCode, Title, ConvenorId) VALUES ('HIT2233', 'Networks', 3);
INSERT INTO SUBJECT (SubCode, Title, ConvenorId) VALUES ('HIT6600', 'Software Testing', 3);

We need to make sure that the ConvenorId matches up to something that is in the CONVENOR table, otherwise it will return an error. However it is perfectly acceptable to also put NULL as the FK value as it could be possible that a SUBJECT has no CONVENOR assigned to it.

### Insert data with Foreign Keys

- **Add the data in the table to the correct created tables**

INSERT INTO CATEGORY (CatId, CatName) VALUES (1, 'Electronic');
INSERT INTO CATEGORY (CatId, CatName) VALUES (5, 'Fashion Accessories');

INSERT INTO PRODUCT (ProdId, Description, CostPrice, SellPrice, CatId) VALUES (1122, '4GB USB Stick', 5.00, 20.00, 1);
INSERT INTO PRODUCT (ProdId, Description, CostPrice, SellPrice, CatId) VALUES (2256, 'Sunglasses', 6.00, 34.99, 5);
INSERT INTO PRODUCT (ProdId, Description, CostPrice, SellPrice, CatId) VALUES (4387, 'Optical Mouse', 17.00, 45.00, 1);

### ERD with a relation convert to SQL statement

- **Consider the relational schema, and create and execute the SQL table statements**

CATEGORY (CatId, CatName)
	PRIMARY KEY (CatId)

PRODUCT (ProdId, Description, CostPrice, SellPrice, CatId)
	PRIMARY KEY (ProdId)
	FOREIGN KEY (CatId) REFERENCES CATEGORY

CREATE TABLE CATEGORY (
	CatId NUMBER(4),
	CatName VARCHAR2(30),
	PRIMARY KEY (CatId)
);

CREATE TABLE PRODUCT (
	ProdId NUMBER(4),
	Description VARCHAR2(30),
	CostPrice NUMBER(4,2),
	SellPrice NUMBER(4,2),
	CatId NUMBER(4),
	PRIMARY KEY (ProdId),
	FOREIGN KEY (CatId) REFERENCES CATEGORY
);
