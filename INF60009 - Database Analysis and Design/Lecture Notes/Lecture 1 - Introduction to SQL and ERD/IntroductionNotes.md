## Introduction 

**Key Terms**

- Databases

Stores the business data.

- Database Management Systems (DBMS)

The software packages that manage databases.

- Structured Query Language (SQL)

The **language that is used by a DBMS

- Entity Relationship Modelling and Diagrams

The diagrams that model the **relationships** of real life entities.

- Relational Data Modelling and Normalisation

Data storage **design rules* for current day databases.

- Data Warehousing

An alternative **design** of a database that will be suited for **data mining** and **business intelligence** requirements

### Database Overview

A database is used to store **related data** about a number of different things (entities) that an organisation may find **intersting**. This can be things such as **student information**, **subject data**, **unit enrollment** and **unit results**.

These entities, are stored in a number of **related tables**, but what to store in those tables can be difficult to determine since we need to manually find out the relationship the data has with each other. One way that this can be achieved is through *Entity Relationship Models and Diagrams**.

### Entity Relationship Model and Diagram

An **entity relationship model** is a logical representation of the data in the database. It uses **entities** to represent people, objects, events and other bits of data to establish the relationships between them. The relationships are defined by the **business rules of the organisation**.

An **entity relationship diagram (ERD)** is the drawing of the relationship model. The diagram itself does not store any data, it is purely just to specify how the data will be stored in the database.

The ERD represents the structure of the information we want to store in the database as a diagram.

### Entity Relationship Diagrams

There are three main components of a E-R diagram. To describe an E-R diagram, we will use a class database as the scenario.

- **Entities**

The entity defines the "thing" or the container for the data to sit in. Examples of an entity for a class database would be the **subject (unit)** and the **lecturer**.

- **Attributes**

The attributes represent the "types" of data that sit within the entity. If we compare this to a data structure, this would be the equivelant to the instance variables that make up the properties of the data structure (entity).

To continue with the class example, the attributes would be the **subject code**, **title**, **credit points**, **lecturer name**.

- **Relationships**

The third component of an E-R diagram would be the relationship. This describes the relationship between entitities. In the class example, the relationship between the **subject** and **lecturer** entities will be that the lecturer **convenes** the subject.

### Other Considerations of a E-R Diagram

- **Sample Data**

To ensure that the E-R diagram makes sense, it would also be useful to consider the data that will be stored in each entity. This helps to see whether the entity is valid and complete.


### Business Rules

The entities in an E-R diagram may follow some rules, this will determine how each of the entities will relate to each other. Rules can be both **common** or **unique** to all organisations, and the design of the E-R diagram should generally follow these rules.

To continue with the **subject** - **lecturer** database, it may have the following types of business rules 

	- A subject must **only have one** convenor.
	- A lecturer may teach **many** subjects at once.

- **Cardinality Constraints**

Cardinality defines **how many** of an entity relates to another. Cardinality is always represented as a **one** or **many** type of relationship.

Below we illustrate the two types of cardinality relationships:

- ONE - -----has-----+
- MANY - ----has-----<- (crows feet)

In our example, we have defined two rules, which we can define a cardinality constraint.

	- A subject must **only have one** convenor : ONE
	- A lecturer may teach **many** subjects at once : MANY

If we illustrate it on our subject - lecturer E-R diagram, we will have the following statements

**One Subject is convened by one Lecturer**
**One Lecturer convents many Subjects**

If we begin each sentence with "One", it defines that a single entity will...

## Entities

### Entity Instances

Whilst an entity defines the container that will store our data, once we have data to put into it, it becomes an **entity instance**. It would be the equivelant of initialising a data structure, where the initialised structure will become an instance of the class.

### Identifiers

An identifier is an attribute within an entity that allows us to uniquely identify the entity instance. 

## Relational Data Model

Once we have defined our E-R diagram, we can convert it into a form that can allow us to programatically define the diagram. Our completed E-R diagram, for each entity will create a **relational schema**. All the schema is, is the entity name along with the attributes that will allow us to create an instance of the entity.

For example the finished Lecturer entity will spit out a schema in the form of - LECTURER (LectId, LecName, Age), where LectId is the unique identifier.

To represent our relational schema, we can use a Relational Data Model. In this model, the data is represented in a 2 dimensional table called a **relation**. The relation contains the following properties

- The columns define the **attributes** where each column is uniquely named.
- The rows (tuples) are unnamed/unnumbered and the order is irrelevant, meaning that if the row order is shuffled, the table is still the same.

For the row, we only allow cell for each attribute (column) and the cell can only contain one value.

All Relational Database Management Systems (RDMS) use the Relational Data Model.

### Example

If we continue with our LECTURER entity, we currently have the following schema defined - LECTURER (LectId, LecName, Age). If we put this into our relational data model, we get the following output:

| LectId          | LecName           | Age   |
| :-------------: |:-----------------:|:-----:|
| 207             | John Smith        | 37    |
| 119             | Jane Pitt         | 26    |
| 475             | Gary Tan          | 42    |

The row equates to an entity instance. You can see that even if we changed the row order, the table still remains the same. The **attributes from the E-R diagrams become the attributes in the column headers** and the **entity becomes the relation**.

### Nulls

Sometimes we may have incomplete information regarding an entity instance, as with most data related things, we sometimes need place holders for unknown or not applicable attributes. This is where we can use the **NULL** value. It simply means "not available" or "unknown" and has no value.

### Primary Keys

Previously we stated that each entity instance must have a unique identifier. This identifier is otherwise known as the **primary key** and allows us to uniquely identify a particular entity instance in the relation **table**.

In our example, we use the LectId as the primary key, to identify this as the primary key in the schema definition, we usually **underline** the attribute.

### Converting Entity Relationships (Foreign Keys)

Whilst it is pretty simply to convert the entity instance to a relation (table), it is a little bit more tricky to convert the relations between entities. In our example as have a **many to one** relationship between our subject and lecturer entities.

To convert the E-R diagram relationship to a relational model, each relationship creates another column in the model. This column is known as the **foreign key** column.

For our subject - lecturer entities, it means that one of the tables (relations) will have an additional column. The relation that will have the additional column will be the relation, whos entity contains the **many** part of the relationship. In this case it will be the **subject** entity.

This makes the Subject schema extend to be - SUBJECT (SubjectCode, Title, Credit Points, LecId). The **foreign key** will typically be the same name as the **primary key** of the entity that it is linked to. The Subject relation model will look something like this:

| SubjectCode          | Title             | CreditPoints   | LecId |
| :------------------: |:-----------------:|:--------------:|:-----:|
| INF00001             | Intro to Web      | 12.5           | 207   |
| INF00002             | Intro to CS       | 12.5           | 119   |
| INF00003             | Into to DB        | 12.5           | 475   |

The DBMS will use the **foreign** key to look up the primary key value in the Lecturer table. This basically means that for any subject we can find which Lecturer is running the course based on the LecId primary key.

The foreign key **must** link to a primary key otherwise an error is generated. It is a constraint on the DBMS. Like primary keys, the foreign key can also be a **NULL** value, which basically implies that we have no idea who takes the Subject.

## Relational Database Management System (RDBMS)

There are two major components of a Relation DMBS, the **database** and **database management system**. The database is the structure that holds the data, and the DBMS is the software that creates, maintains and accesses the data.

RDBMS has its own terminology, which is similar to what we've already gone through


| Relational Terminology     | RDBMS Terminology    | Other Terms   |
| :------------------------: |:--------------------:|:-------------:|
| Relation                   | Table                | File          |
| Attribute                  | Column               | Field         |
| Tuple or Row               | Row                  | Record        |

## Relational Schema to RDBMS Table

In our example, we have 2 relational schemas -

- LECTURER (LecId, LecName, Age)
- SUBJECT (SubjectCode, Title, CreditPoints, LecId)

Every relation (table) in the relational model will require a **Create Table** statement. The general rule will be to create the tables that **do not** have foreign keys, then create the other tables.

As with converting the E-R diagram to the relational model, to convert the model to the RDBMS table, each attribute will be a column with the addition of a **data type**.

## Structured Query Language (SQL)

Is a language that is used by all RDBMS to create and access the data in a relational model. In the language, statements can be classified into two groups.

- **Data Definition Language (DDL)**

The statements used to maintain the database structures (i.e. CREATE TABLE, ALTER TABLE, DROP TABLE).

- **Data Manipulation Language (DML)**

The statements that are used to modify the data in the database (i.e. Inserting Data, Querying Data, Updating Data, Deleting Data).

### SQL Syntax

The following sections contain how to create, manipulate and modify a database.

#### Create Table 

The statement to create a table (relation) in a RDBMS. The syntax is as follows -

```CREATE <table-name> (<column-name1> <data-type> [<max length>], <column-name2> <data-type> [<max length>] ..., PRIMARY KEY (<column-name>), FOREIGN KEY <column-name> REFERENCES (<table-name>));```

Where -

- UPPERCASE words are Oracle keywords.
- Words within < ... > are user defined.
- Words within [ ... ] are optional.

In our example we will have: 

	CREATE TABLE LECTURER (
		LecId 		number,
		LecName 	varchar(50),
		Age			number,
		Primary Key (LecId)
	);

	CREATE TABLE SUBJECT (
		SubjectCode 	varchar(10),
		Title 			varchar(100),
		CreditPoints	number,
		LecId 			number,
		Primary Key 	(SubjectCode),
		Foreign Key 	(LecId)
		References 		LECTURER
	);

The idea here will be to create the LECTURER table first because if we created the SUBJECT, the Foreign Key would not know what to reference.

#### Insert Statement

We can insert a new row into a table with an **insert** statement. 

To insert into a table, we need to provide:

- The table name to insert into.
- The column names to fill.
- The values to insert.

The syntax is as follows -

```INSERT INTO <table-name> (<column-name1>, <column-name2>, ...) VALUES (<value1>, <value2> ...);```

In our example we will have:

	INSERT INTO LECTURER (LecId, LecName, Age)
				VALUES (207, 'John Smith', 37);

	INSERT INTO SUBJECT (SubjectCode, Title, CreditPoints, LecId)
				VALUES ('INF00001', 'Intro to Web', 12.5, 207);

String values need to be surrounded by ' '.
If a value is unknown, we can insert **Null** into the values section.

#### Select Statement

Using the select statement, we can extract and display the data in the database tables.

The syntax is as follows -

```
SELECT <item>
FROM <table name>
```

Where ```<item>``` defines the columns that you want to select, and ```<table name>``` is the name of the table.

In our example we will have:

	SELECT LecId, LecName, Age
	FROM LECTURER;

If we want to select all table columns we can use the * syntax rather than listing out all the columns.

**Ordering**

From our select statement, we can return the data in a defined order (ascending and descending order).

- ASC for data to be returned in ascending order.
- Desc for data to be returned in descending order.

In our example we will have:

	SELECT LecName, Age FROM Lecturer
	Order By LecName Desc;

	or

	SELECT LecName, Age From Lecturer
	Order By Age ASC;

Also we can order a return by its column number. The column number we refer to is one of the columns in the selection.

For example, if we want to select the 3rd column (Age) in the select clause we can use -

SELECT LecId, LecName, Age
FROM Lecturer
Order By 3 Desc;

This will select the 3rd column to order by.

This can be used to build complex queries, and we can order by a bunch of columns.

SELECT LecName, Age, FROM Lecturer Order By Age, LecName.

This means it'll order first by Age, then by LecName.

#### Optional Select Clauses

We can add additional keywords to a select statement to filter additional data.

WHERE is another keyword that specifies that a columm value must satisfy to appear in the result.

In our example we will have:

	SELECT *
	FROM Lecturer
	WHERE age < 35

This searches all columns in the lecturer table and only returns the rows where the Age value is less than 35. This clause can have the usual comparison operators (=, <> (!=), <, <=, >, >=).

### String Comparison

Depending on the DBMS used, comparing string values may result in different results.

In Oracle or MySQL, 'smith' and 'SMITH' are not considered equal, whereas in SqlServer, MSAccess, 'smith' and 'SMITH' are equal.

### Table Access

- DESCRIBE ```<table name>``` will return the list of columns and the defined attributes of the table.
- DROP TABLE ```<table name>``` will delete the table from the database.

