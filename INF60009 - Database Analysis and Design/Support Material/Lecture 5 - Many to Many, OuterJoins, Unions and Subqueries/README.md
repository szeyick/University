## Many to Many Relationships, Outer Joins, Subqueries and UNION

### Terms

- Strong Entity

A Strong entity is an entity where all rows within the entity are able to be uniquely identified with only the attributes within the entity.

- Weak Entity

A Weak entity is an entity where it is not possible to uniquely identify a row with only the attributes that are within the entity. Usually it would be required to borrow an entity from the parent to be able to uniquely identify a row.

- Intersection Entity

An intersection entity is the result of a split between a M:M relationship. Splitting the M:M results in 2 M:1 relationships with a weak entity inbetween. This middle entity is called the intersection entity.

- Characteristic Entity

It is an entity that is composed of only a single attribute. It is usually used as a means of binding another table to it through the FK relationship.

- Outer Join

An Outer Join is where you include all the rows in a table even if it is missing some data. SQL will replace the missing data with NULL entries. An outer join can either be a Left Outer Join or Right Outer Join. 

A Left Outer Join guarantees that all the rows in the left table are included in the result set, whereas the opposite is for the Right Outer Join.

In both cases the clause SELECT * FROM <TABLE 1> LEFT OUTER JOIN <TABLE 2> ON ... and SELECT * FROM <TABLE 1> RIGHT OUTER JOIN <TABLE 2> ON ... the left table is <TABLE 1> and the right table is <TABLE 2> the difference in which join is used just changes the result set to inclue all of the left or right tables.

- Sub Query

Is a query that is written in the WHERE clause. Because it is not possible to use aggregate functions within the WHERE clause, we need to use a sub query to extract the result first before passing it to the WHERE clause.

- UNION

Is where you combine the result sets of 2 different queries, UNION will return the combined result of adding both tables together but won't include duplicates, whereas UNION ALL will include all duplicates.

### Support Questions

**1. Consider the ERD**

**a. Describe in English, the ERD**

The ERD describes the number of employees that can work in a department. In this case, there can be more than 1 employee working in a department. Conversely, it also describes that a department is made up of many employees.

ONE EMPLOYEE MAY WORKS IN MANY DEPARTMENTS
ONE DEPARTMENT MAY HAVE MANY EMPLOYEES

**b. Can a M:M relationship be created in a Relational Schema**

No a relational schema cannot be used to describe a M:M relationship.

**c. What steps need to be taken to expand the M:M relationship**

To expand we need to do the following :

1. Remove the relationship between the two entities
2. Add a intersection entity and give it a name.
3. Add M:1 lines that join the original entities with the new one
4. Evaluate if the entity needs attributes of whether it inherits from the parents
5. Underline the relationship name and add the new entity as a weak entity.

**d. Expand the M:M relationship**

EMPLOYEE -> ASSIGNED TO -> DEPARMENT

**e. Convert the ERD to a Relational Schema**

EMPLOYEE (EmpId, EmpName)
	PK (EmpId)

DEPARTMENT (DeptCode, DeptName)
	PK (DeptCode)

ASSIGNED (EmpId, DeptCode)
	PK (EmpId, DeptCode)
	FK1 (EmpId) REFERENCES EMPLOYEE
	FK2 (DeptCode) REFERENCES DEPARTMENT

**2. Consider the ERD**

**a. What is the meaning of the braces around the languages**

The braces around the language attribute indicate that the attribute can contain more than 1 value. In a way it is shorthand for saying that there should be another entity that branches off. It is a **multi valued attribute**

**b. Can a column have many values in a relational schema**

No a column in a relational schema is only expected to have 1 value.

**c. What do you have to do to this diagram before converting it to a relational schema**

It needs to be expanded, the Language attribute is split off into its own **Characteristic** entity, that creates a M:M relataionship with the Programmer entity.

Then because you have created a M:M relationship between Programmer and Language, you would need to expand it to create another intersection entity before it can be converted to a relational schema.

**d. Expand the M:M portion of the ERD**

PROGRAMMER--<SKILL>--LANGUAGE

- **3. Can a relational schema have a M:M relationship**

No a relational schema cannot have a M:M relationship. It cannot be defined correctly.

- **4. Expand the M:M portion of the ERD**

PROGRAMMER--<SKILL>--LANGUAGE

- **5. Convert the ERD to a Relational Schema. Indicate all PK's and FK's**

PROGRAMMER (PID, PName)
	PK (PID)

LANGUAGE (Language)
	PK (Language)

SKILL (PID, Language)
	PK (PID, Language)
	FK1 (PID) REFERENCES PROGRAMMER
	FK2 (Language) REFERENCES LANGUAGE

- **6. What is a characteristic entity**

A characteristic entity is an entity that has a single attribute. It is usually used when there are multi-valued attributes that need to be separated off into their own tables. This attribute is known as a KEY and is usually used by another table to bind a foreign key to the PK of the characterstic entity.

Such examples would be SKILL.

- **7. When a characterstic entity creates a table with one column, what is the point of having a one column table**

It works as a KEY. This key can be used by a child entity to establish a relationship between a value in its column to a value in the characteristic entity.

For example, in the SKILL characterstic entity, if we have a child entity that references the SKILL entity and we try to enter an invalid skill into the child entity, it will not work because the dependency has been created to the SKILL entity. Because it will be possibly part of the PK, it will raise an error since the illegal value would not exist in the SKILL table.

- **8. Describe the purpose of this SQL statement. Describe how it works?**

SELECT * FROM MOVIE
WHERE IMDB_SCORE > (AVG(IMDB_SCORE))
ORDER BY IMDB_SCORE

This statement will not work because the sub query is not complete.

However were it to be -

SELECT * FROM MOVIE
WHERE IMDB_SCORE > (SELECT AVG(IMDB_SCORE) FROM MOVIE)
ORDER BY IMDB_SCORE

Then it would first evaluate the subquery, finding the average store from all the non-null rows added up. Return that value to the WHERE clause for it to construct the result set.

It would then run the outer query, selecting all the rows to put into the result set, where the IMDB_SCORE is > than the average found, then puts it into ascending order.

- **9. Describe the purpose of this SQL statement. Describe how it works**

SELECT StuName AS "Name"
FROM student
UNION
SELECT TutName
FROM tutor
ORDER BY 1

It is a UNION statement so it takes the results from both queries, combines them and then removes all the duplicates. The result set contains a combination of both queries without the dupliates.

It creates the result set from the first query, selecting all the StuName rows from the Student table and adding to a result set called "Name".

It then creates a result set from the second query, selecing all the TutName rows from the Tutor table and adding it to a result set.

It then combines the two, removing the duplicate entries.

- **10. What is the purpose of the expressions 'Student', 'Tutor' and 'Role' in the following query**

SELECT StuName As "Person Name", 'Student' As "Role"
FROM student
UNION
SELECT TutName, 'Tutor'
FROM tutor
ORDER BY 1

The purpose of Student and Tutor is to set a literal value for that row entry.

That row entry is referred to as the Role column. So for all entries returned in the student query, it will have a new column filled with 'Student' and for the tutor result set, it will add a new column called "Role" and have a 'Tutor' entry in it.

- **11. What is the purpose of an OUTER JOIN**

The purpose of an Outer Join is to ensure that all the rows from one of the tables in either the left or right selected are included in the result set. This is included even if there is no matching PK/FK combination.

- **12. What is the purpose of a LEFT OUTER JOIN**

A LEFT OUTER JOIN is responsible for adding all the rows in the left table to the result set. If there are additional rows defined, and there is no corresponding value from a row entry in the left table, it will add NULL.

- **13. Is the LEFT table the first table listed or the second table**

The left table in a left outer join is the table that is named after the FROM clause and between the LEFT OUTER JOIN clause.

- **14. What appears in the result set when executed**

SELECT a.actorno, a.surname, a.firstname, c.movieno
FROM actor a
LEFT OUTER JOIN casting c
ON c.actorno = a.actorno
ORDER BY a.surname

LEFT TABLE = actor
RIGHT TABLE = casting

Result Set:

ACTORNO | SURNAME | FIRSTNAME | MOVIENO
4         Barrett   Dana        
5         Joshua    Peter       7
1		  Lampert   Regina      7
3         Ryerson   Ned         6

I think if it was an inner join, it would not include the entry for Barrett Dana since the entry will need to exist for both tables.

- **15. What is the purpose of a RIGHT OUTER JOIN**

A RIGHT OUTER JOIN is where all the rows in the right table are included in the result set even if they are missing data. The missing data will be filled with NULL

- **16. Is the RIGHT table the first table listed or the second table listed**

The RIGHT table is the second table listed, it is the table listed after the RIGHT OUTER JOIN statement and before the ON statement.

- **17. What appears on the result set when executed?**

SELECT c.movieno, m.title, c.actorno
FROM casting c
RIGHT OUTER JOIN movie m
ON c.movieno = m.movieno
ORDER BY m.title

LEFT TABLE = casting
RIGHT TABLE = movie

Result Set:

MOVIE NO | TITLE       | ACTOR NO
7		  Charade        1
7         Charade        5
6         GroundHog Day  3
8         The Matrix     

- **18. What appears in the result set when executed**

SELECT m.title, a.surname, a.firstname
FROM casting c
INNER JOIN actor a
ON c.actorno = a.actorno
RIGHT OUTER JOIN movie m
ON c.movieno = m.movieno
ORDER BY m.title

LEFT TABLE = casting
RIGHT TABLE = movie

Result Set: 

TITLE     		| SURNAME 	    	| FIRSTNAME
Charade       	  Lampert		      Regina
Charade           Joshua			  Peter
GroundHog Day	  Ryerson			  Ned
The Matrix

