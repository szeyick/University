## M:M Relationships, Unions and Outer Joins

### Constraints

Previously we have looked at the USER_CONSTRAINTS table in the Oracle dictionary. This table shows us all the columns in each table of the database that have constraints applied to them. The CONSTRAINT_NAME column in the table tells us very little of the exact column it refers to.

We can make the CONSTRAINT_NAME column a little more legible by adding the constraint name when we create our table.

```
CREATE TABLE STUDENT (
	studentId NUMBER PRIMARY KEY,
	surname VARCHAR(30)
)

Will become

CREATE TABLE STUDENT (
	studentId NUMBER CONSTRAINT PK_STUDENT PRIMARY KEY,
	surname VARCHAR(30)
)
```

The constraint name follows the syntax of the type of constraint, which in this case is the primary key (PK) and the table we are referring to (STUDENT).

Constraints within the USER_CONSTRAINTS table can be viewed using the usual SELECT statement.

### Unique Constraints

We can use the same syntax for NOT NULL constraints, as well as **UNIQUE** constraints. The **UNIQUE** constraint is a keyword that ensures that the values in a column are never duplicated.

```
CREATE TABLE MOVIE (
	movieNum NUMBER,
	title VARCHAR(30) CONSTRAINT CC_MOVIE_TITLE UNIQUE
)
```

The unique constraint can be applied to more than 1 column in a table.

```
CREATE TABLE ACTOR (
	actorNum NUMBER PRIMARY KEY,
	surname VARCHAR(30) NOT NULL,
	firstname VARCHAR(30) NOT NULL,
	birthdate DATE,
	CONSTRAINT UC_ACTOR_NAME UNIQUE(surname, firstname)
)
```

The above snipped states that the combination of surname and firstname must never be repeated in the table.

### Check Constraints

A check constraint performs a "check" on data values, ensuring that only the defined values are set in the column

```
CREATE STUDENT (
	studentId NUMBER PRIMARY KEY,
	name VARCHAR(30),
	gender VARCHAR(1),
	CONSTRAINT CHK_STU_GENDER CHECK (gender IN ('M', 'F'))
)
```

The above snippet checks the gender column to ensure that only M or F are set as the values.

Checking constraints should only be used for values that are not going to change. The row will pass the checking constarint if it meets the condition set in the check, or is NULL.

The check constraint is similar to using WHERE, with the exception that you cannot refer to other values in other columns or tables.

### Many to Many Relationships

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%205%20-%20M-M%20Relationships%2C%20Unions%20and%20Outer%20Joins/images/ManyToMany.png "Many to many")

The incorrect method of defining the above schema is shown below -

```
CREATE TABLE SUBJECT (
	SubCode VARCHAR2(10) PRIMARY KEY,
	SubTitle VARCHAR2(40)
)

CREATE TABLE STUDENT (
	StudentId VARCHAR2(10) PRIMARY KEY,
	StuName VARCHAR2(40),
	SubCode VARCHAR2(10),
	FOREIGN KEY (SubCode) references SUBJECT
)
```

The reason that the above won't work is that a Student can only ever be enrolled into 1 subject, where in our image, we want a student to be able to enrol into many subjects.

The relational model **cannot** directly represent a many to many relationship. The solution is to convert the many to many relationship into **2 many to one relationships**

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%205%20-%20M-M%20Relationships%2C%20Unions%20and%20Outer%20Joins/images/MultiManytoOne.png "Multi Many to One")

### Converting Many to Many

To convert a many to many relationship into 2 many to one relationships, we need to go through the following steps -

- Remove the many to many relationship between the entities.

- In its place, add a new **weak** entity and give it a name.

- Add the many to one relationship lines to the new entity, where the original entities contain the mandatory 1 relationship. Name and underline the relationships.

### Identifying the new Entity

As the new entity has no attributes, we are required to **borrow** identifiers from other entities. Because we have underlined the relations in our diagram, it means we need to borrow identifiers from the other entities, which in this instance will be the StudentId and SubCode attributes.

When we convert the Enrolment entity to a relational model we'll find that only this entity has a foreign key.

```
ENROLMENT (StudentId, SubCode)
PK (StudentId)
FK (StudentId) REFERENCES STUDENT
FK (SubCode) REFERENCES SUBJECT
```

Once we start adding entries into the table, we can add as many subjects for each student as we want as all the subjects for each student are contained within the ENROLMENT entity. This is the same the other way round as we can see many students enrolled into a subject.

### Intersection Entity

When we expand a Many to Many relationship into 2 Many to One relationships, it is called an **Intersection Entity**. An intersection entity is defined as an entity that has no additional attributes and is the result of expanding a M:M relationship.

### Associate Entity

An associative entity is similar to an intersection entity, with the exception that it has its own properties.

### Not Expanding Many to Many Relationships

There are some ER diagrams that do not expand the relationship. There are a few advantages to this which include -

- The weak intersection entity is hidden, allowing for a less cluttered diagram and less entities.
- Fewer relationships.
- The relationship name completely describes the relationship between the entities.

The disadvantages of this are -

- Hard to figure out how many tables are required without expanding.
- The weak entity is not created so the detail in how the entities are joined is lost.

### Multi Value Attributes

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%205%20-%20M-M%20Relationships%2C%20Unions%20and%20Outer%20Joins/images/MultiValuedAttributes.png "Multi Value Attributes")

A multi valued attribute is used when an attribute may have more than 1 value defined for it. Multi valued attributes are defined using the **{ attributeName }**.

An example of when this is used would be defining an employees skillset, where they may be experienced in a bunch of different skills.

In the above example, it is a shortcut way of saying that an **Employee** has **many Skills**. This creating a M:M relationship for that attribute and its entity.

### Characteristic Entity

A characteristic entity is an entity that only contains a single attribute. Creating an additional entity for this situation is a little redundant since the expansion would result in the weak entity containing all the information of the characterstic entity anyway since the characteristic entity contains nothing other than the 1 attribute.

A advantage of having a characteristic entity however would be that you're binding the foreign key value to a primary key value, meaning it must always exist.

### SQL Subqueries

A subquery is complete SQL statement within an existing SQL statement. It is used when a result cannot be calculated with a single SQL statement.

```
SELECT * FROM MOVIE
WHERE runtime > (SELECT AVG(runtime) FROM MOVE);
```

The reason that we do this is because we cannot use aggregate functions (i.e. AVG(runtime)) within the WHERE clause, but it is acceptable to use a subquery.

The reason that this works is because the subquery is calculated first within the where statement and it returns a single value. This returned value is then evaluated against the rest of the WHERE clause.

### Outer Joins

An outer join **guarantees** that all rows from a selected table will be included in the result set.

If we use a **left outer join** clause, it guarantees that all rows from the left table will not be eliminated. The left table is defined as the table that is listed first in the clause.

```
FROM actor a LEFT OUTER JOIN casting c ON ...
```

Inner joins have a limitation in that they do not retrieve a row if there is no matching data in the other table. Because inner joins rely on the value existing in both tables, if a value does not exist in a table in which the inner is taking place, the result set will not contain it.

However if we use an outer join, if we specify the table to take the values from (i.e. LEFT OUTER JOIN) it will return all the rows from the left table, even if there is no corresponding value in the right table. The blank values will be replaced with NULL.

```
SELECT a.actorno, a.surname, a.firstname, c.movieno
FROM actor a
LEFT OUTER JOIN casting c
ON a.actorno = c.actorno;
```

To list all the rows from the right table in the result set, we can use the RIGHT OUT JOIN statement instead.

### Union and Union All

The union operators combine the results of two queries into a single result set. The number of and the data types returned by each query has to be the same, but the column lengths can be different.

A union will return all the rows by either query.

A union all will return all the rows selected by either query that includes duplicates.

In other words, with a union, duplicate values from the query will not appear in the result set.

```
SELECT name FROM STUDENT
UNION 
SELECT name FROM TUTOR

SELECT name FROM STUDENT
UNION ALL
SELECT name FROM TUTOR
```

The data types of the columns must match, whereas their properties (length, and precision) can vary. The queries may require a conversion function, TO_CHAR(...), CAST(), CONVERT() to ensure that the data types match.

### Intersect

Intersect causes all **unique** rows by selected by both individual queries to be added to the result set. It will be the values contained in the intersection of the queries.

```
SELECT name FROM STUDENT
INTERSECT
SELECT name FROM TUTOR
```

Referring to the snippet above, the result set will only include the names found in both tables.

### Minus

The minus operator will select all the unique rows selected by the first query, but not the second. It will return all the rows selected by the first query that are not selected by the second query.

```
SELECT name FROM STUDENT
MINUS
SELECT name FROM TUTOR
```

The above will only include the names found in the first query that are not in the second query.

### Literals

Sometimes when using unions or intersects, there may be non-matching columns. To get around this, we can use **literal values**. These are just filler columns that will fill out the result set with a 0 value. This value is not computed but rather simply hard coded.

```
SELECT name, purchase_amount
FROM SALES
UNION
SELECT name, 0
FROM enquiry
ORDER BY 1 ASC;
```

To use unions, the number of columns must match along with the data types. 
