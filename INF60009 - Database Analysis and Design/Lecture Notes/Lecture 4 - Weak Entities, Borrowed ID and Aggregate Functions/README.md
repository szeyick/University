## Lecture 4 - Aggregates, Groups and Entities

### Column Constraint

A column constraint is a constraint that is defined in the create table definition. An example of a column constraint is the setting of an attribute to the **Primary Key**.

```
CREATE TABLE STUDENT (
  studentId NUMBER Primary Key,
  firstName VARCHAR(30),
  surname VARCHAR(30)
);
```

It is something that is used to constrain a single column.

### Table Constraint

A table constraint is like a column constraint except that it is declared after all the columns of the table have been defined.

Again like the column constraint, it applies to a single column

```
CREATE TABLE STUDENT (
  studentId NUMBER,
  firstName VARCHAR(30),
  surname VARCHAR(30)
  Primary Key (studentId)
);
```

A table constraint should be used whenever the primary key is a **composite key**, where more than a single column is defined as the primary key.

```
CREATE TABLE STUDENT (
  studentId NUMBER,
  firstName VARCHAR(30),
  surname VARCHAR(30)
  Primary Key (studentId, firstName)
);
```

### Viewing Constraints

Details of constraints are stored in the **Oracle Data Dictionary** table that is called **USER_CONSTRAINTS**. It can be retrieved through the normal SELECT TABLE statement.

### Joining Tables and Foreign Key Constraints

We are required to establish relationships when joining tables, we need to specify **foreign keys constraints** that link a foreign key to another tables primary key, and also specifying the inner joins to link tables together.

**We are required to do both because we can join tables that aren't related by their foreign key constraints and can come from different databases.**

### Retrieving Data From Multiple Tables

**INSERT DIAGRAM**

In the above diagram, we have 2 tables that have no direct relationship with each other. So how could we retrieve a list of workers that were born in the same country that the product was made in?

Here we can use an **inner join**.

```
SELECT p.id, p.pdescription, w.wname, w.bonrincountry
FROM PRODUCT p
INNER JOIN WORKER w
ON p.countryMade = w.bornincountry
```

This demonstrats how we can retrieve information from different tables that seemingly have no relationship with each other.

### Integrity Constraints and Retrieving Data

A foreign key and inner joins have nothing to do with each other.  The foreign key is a relational model concept that enforces constraints to validate new data. This relationship requires the parent-child relationship to be maintained.

An inner join is an SQL instruction that can be used to match data from multiple tables, meaning you can match any data in any column with any data in any other column of another table.

### Strong and Weak Entities

**INSERT IMAGE**

The term entity refers to the box in an ERD, that is essentially the table or "thing". Entities can however be categorised into two groups, **strong** and **weak** entities.

A **strong entity** is defined as an entity that can be defined by its own attribute.

A **weak entity** is where we cannot use any of its attributes to identify it.

### Borrowed Identifiers

Borrowing Identifiers involves borrowing an attribute from another table to assist in the identification of the current entity.

With the BUILDING-ROOM relationship, we borrow the BuildingCode attribute from the entity BUILDING to establish an identifier for ROOM which will be **BuildingCode + RoomNo**. This relationship allows the child to borrow an attribute from the parent as part of its identifier.

The way that we illustrate a borrowed identifier is by underlining the relationship name like below -

**INSERT IMAGE**

### Weak Entities

It is defined as an entity that borrows part or all of its identifier from somewhere else.

To illustrate a weak entity in the ERD, we double outline the entity.

**INSERT IMAGE**

Continuing from the example above, we can find out which entity has the borrowed identifier by examining the ERD.

The ROOM entity is double boxed, meaning that there is part of it that is borrowed, then the "Contains" is underlined which means that it must be borrowing from its parent, so it would be borrowing the BuildingCode attribute.

When converting it to a relational model, the borrowed attribute is listed first 

```
ROOM(BuildingCode, RoomNo, RoomDesc, Capcity)
BUILDING(BuildingCode, BuildingTitle)
```

### Relations

Suppose we have two tables with information that we want to link together. We can come up with a relation table that allows us to link these tables together. In the example we have the MOVIE and ACTOR tables.

From the relationships we can figure out a relation

**INSERT IMAGE**

We can create a relation table that links the two tables together. 

This new table **MOVIEACTOR** can have the following relational schema

```
MOVIEACTOR(actorNo, movieNo)
  Primary Key is actNo, movieNo
  Foreign Key 1 is actorNo which references ACTOR 
  Foreign Key 2 is movieNo which references MOVIE
```

Things to note here are that actorNo and movieNo are foreign keys for the MOVIEACTOR table and also part of the primary key of the MOVIEACTOR (since we have 2).

### MOVIEACTOR Entity

This entity is a weak entity, this is because we cannot determine any information without the MOVIE and ACTOR tables, and also it borrows the identifier from the other tables.

**Insert Image**

Again to recap, if a relationship name is underlined, it is the **many** side of the relationship that borrows the identifier from the one end of the relationship.

### Relationships

Sometimes, when you have a bunch of strong entities that interact with each other, you will need some weak entities.

**Weak Entities** borrow their identifier from the entity that is on the other end where the relationship line is underlined.

**INSERT IMAGE**

### SQL Aggregate Functions

An aggregate function is a function that is applied over a number of rows. It **must** be used in the **SELECT** clause.

The function **COUNT(*)** is an example of an aggregate function as it counts the number of rows. These functions can be used along with the rest of the clauses to filter out the returned set.

```
SELECT COUNT(*) 
FROM MOVIE
WHERE relyear >= 2000
AND relyear <= 2009
```

The * in the COUNT function just selects all the columns, we can use it to select individual columns and just counts the number of NOT NULL values or whatever conditions follow.

Other functions other than count can include

- **SUM(columnName)** 
- **AVG(columnName)**
- **MIN(columnName)**
- **MAX(columnName)**
- **SUM(columnName)**

The rule when using the above statements is that they must have the **column name**.

### SQL Round Function

When dealing with large numbers after the decimal place, we can use the **Round()** function. The syntax of the function is as follows -

```
ROUND(number, [decimal places])

e.g. SELECT ROUND(AVG(run_time), 2) FROM MOVIE;
```

### SQL Aggregate vs Non-Aggregate

The items that are selected in the SELECT clause are generally -

**Non Aggregate** expressions - selecting the column names
**SQL Aggregate** expressions - using the SQL functions.

### Group By

The Group By clause must **always** be used with an **aggregate expression** other than COUNT().

We add the **non aggregate** expression to both the **select** and **group by clause**, and only add the **aggregate** expression to the **select**.

```
SELECT rating_code, COUNT(*)
FROM movie
GROUP BY rating_code
```

**INSERT IMAGE**

### Ordering

Aside from the ORDER BY clause that we have learnt earlier, we can order by the result set.

```
SELECT relyear, COUNT(*)
FROM movie
GROUP BY relyear
ORDER BY 2 DESC
```

In the above snippet, the value 2 in the ORDER BY refers to the 2nd column in the result set.

### Group By Having

This **HAVING** clause only keeps a row in the result set if a value meets a criteria. 

```
SELECT relyear, COUNT(*)
FROM movie
GROUP BY relyear
HAVING COUNT(*) >= 5
```

**INSERT IMAGE**

The above snippet will filter the result set. You might wonder why we can't do this with the WHERE clause, this is because the WHERE clause specifies the rows from the MOVIE table that will be built into the result set. We cannot use the WHERE clause on a column (COUNT()) that has not been created yet as it is part of the result set.
