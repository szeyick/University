## SQL: There Where Clause, Joins, Table and Data Maintenance

- *Consider the ERD Below*


- **When converted to a relational schema**

The foreign key is the child, meaning that it has to have a parent.

**How many Foreign Keys will exist in the Task relation?**

The foreign key represents the child in the many to one relationship. The task entity has the following statement

One Task can be made up of **many** projects.

Because the Task entity is only linked to the Project entity, it means that it shall only have **one** foreign key.

**How many Foreign Keys will eixst in the **Project** relation?**

The Project relation is the **many** side of the relationship between the Leader - Project. But is only the singular side of the Project - Task relationship.

It is defined as follows -

One Project has **many** Tasks
One Project has **one** Leader

Again, the Project is the many side, so it shall have **one** foreign key. 

- **Consider the Project Relation (Project Entity)**

**What is the name of the Foreign Key in this relation?**

The foreign key will be the TaskId since the relation is that the Project entity will be the parent to the Task entity.

**Would this foreign key be allowed to have null values?**

This foreign key should be allowed to have null values, since it can have multiple children and is denoted by the O meaning it can be null.

- **Consider the Task Relation (Task Entity)**

**What is the name of the Foreign Key in this relation?**

As the child, the relation to its parent (Project entity) is that the parent **must** exist. 

**Would this foreign key be allowed to have null values?**

The foreign key to the task would be the Project entities ProjectId. This cannot be null since the ERD in the relationship is denoted with a |. The | states that the Project **must** exist.

- **Imagine that you now want to build the database based on the Relational Schema.**

```
TASK (TaskId, Description, Cost)

PROJECT (ProjectId, Title)
FOREIGN KEY TaskId References Task

LEADER (LeaderId, Name)
FOREIGN KEY ProjectId References Project
```

**Which table would you have to create first? Leader, Project or Task**

From the relational schemas above, because we need to reference the foreign key in each stage the TASK table will be created first since it has no relationships with either of the other tables.

**Which table would you have to create second? Leader, Project or Task**

Now that the TASK table is created, we can create a foreign key reference to it, which means we can create the PROJECT table. We cannot create the LEADER table since the LEADER table requires the PROJECT table as a reference to the foreign key.

**Which table would you have to create Last? Leader, Project or Task**

Since the PROJECT task is created, we can now create the last table, the LEADER.

- **True or False**

**Leader is a parent of Project.**

True, Leader is the parent because it is on the **one** side of the one - many relationship between the tables. Also the Leader has the ProjectId as its foreign key meaning that it is the parent.

**Task is a parent of Project.**

False, Task is the child of the Project because it is on the **many** side of the one - many relationship between the tables. Also, the primary key of the Task, is the foreign key reference from the Project, meaning that the Project can reference the Task but not the other way round.

As far as SQL is concerned, there is no relationship from the Task to the Project, only the other way round.

**Leader is a parent of Task.**

False, there is no direct reference from Leader to Task. The foreign key from Leader references Project, and the foreign key from Project references Task. As far as SQL is concerned, there is no relationship between Leader and Task.

Unless the concept of grand parents in databases is possible, then yes this would be true, however as a direct parent, this would be false.

**Task is a parent of Leader.**

False, there is no direct reference from Task to Leader. It would be the same as above. 

- **Consider the following table**

**How many rows are selected by the following queries?**

**SELECT Name QtyA+QtyB AS "Qty Total"
FROM person
WHERE QtyA+AtyB < 10;**

In other words this means, select from the person table, quantityA and quantityB where the aggregate is less than 10

5 rows will be selected.

**SELECT Name
FROM person
WHERE QtyA >= 5 AND QtyB < 5;**

In other words this means, select name column from the person table where quantityA is greater or equal to 5, AND quantityB is less than 5

2

**SELECT Name
FROM person
WHERE NOT (QtyA >=5 AND QtyB < 5);**

In other words this means, select name column from the person table where it is the opposite of the statement above.

9

**SELECT Name
FROM person
WHERE NOT (QtyA + QtyB) < 10
AND Gender = 'F';**

In other words this means, select the name column from the person table, where the aggregate of QtyA + QtyB is NOT less than 10 and the gender of that person is a female.

2

**SELECT Name FROM person
WHERE UPPER(Name) Like '%RI%'
OR UPPER(Name) Like '%IN%'**

In other words, this means select the name column from the person table, where the name (converted to upper case), contains either RI or IN in it somewhere in the middle of the name.

3

**SELECT Name FROM person
WHERE LOWER(Name) Like '%or%'**

In other words, select the name column from the person table, where the name (converted to lower case) contains an 'or' somewhere in the middle of the name.

4

**SELECT Name FROM person
WHERE UPPER(Name) Like '%Son'%

In other words, select the name column from the person table, where the names (converted all to upper case) contains 'Son' somewhere in the middle of the name.

0, since the names converted to upper case would not contain any lower case like with 'Son'.

**
SELECT Name FROM person
WHERE (Gender = 'M' OR QtyA > 4)
AND (Gender = 'F'OR QtyB < 5);
**

In other words, select the name column from the person table, where the gender is (male or qtyA is greater than 4) AND (gender is female OR qtyB is less than 5)

4

**
SELECT Name FROM person
WHERE (QtyA > 2 AND Gender = 'F')
OR Gender = 'M'
**

In other words, select the name from the person table, the QtyA has to be greater than 2 AND the Gender is female. The outcome of this is then OR'd against whether the Gender is male.

5, so long as the evaluation order is correct.

**
SELECT Name FROM person
WHERE (IdNo > 0 AND Id No < 20)
OR QtyB < 5;
**

In other words, select the name from the person table, the IdNo has to be greater than 0 but less than 20. The outcome of this is then OR'd against whether the QtyB is less than 5.

7

**
SELECT Name FROM person
WHERE (Gender = 'M' OR QtyA > 4)
AND (Gender = 'F' OR QtyB < 5);
**

In other words, select the name from the person table, the Gender must be male or QtyA greater than 4. This is AND'd against, the gender being female OR QtyB less than 5.

4

**
SELECT Name FROM person
WHERE (Gender='M' AND Gender='F')
OR (QtyB > 0 AND QtyB <5);
**

In other words, select the name from the person table, the Gender has to be male and female. This result is OR'd against whether the QtyB is between 0 and 5.

5

- **Assume that a relational schema and database has been build based on the ERD in Question 1**

**Write the SQL statement using INNER JOIN clauses and Aliases that displays the following data for each row in the task table:**

**Task Id, Task Description, Project Title and Project Leader Name.**

SELECT T.TaskId, T.Description, P.Title, L.Name
FROM TASK T
INNER JOIN PROJECT P
ON T.ProjectId = P.ProjectId
INNER JOIN LEADER L
ON T.LeaderId = L.LeaderId

### Lab Questions

- **Listing Rows**

**List all the rows of the Movie table**

SELECT * FROM MOVIE;

**List all the rows of the Rating table**

SELECT * FROM RATING;

**List all the rows from the Colourtype table**

SELECT * FROM COLOURTYPE;

**List all columns of movies that have a title that begins with the word 'the'. Make it work for any combination of titles**

SELECT title FROM Movie
WHERE lower(title) LIKE 'the%';

**Same as the above but allow the 'the' to appear anywhere in the title**

SELECT title FROM Movie
WHERE lower(title) LIKE '%the%';

**List all the rating_codes in the movie table. Each rating should only appear once in the list.**

SELECT rating_codes FROM MOVIE
WHERE DISTINCT;

**Using the Movie table, and a single SQL statement, list the title of the movies that have any type of the following MovieNo values:

**137, 314, 9502, 44214**

SELECT title FROM Movie
WHERE MOVIENO IN (137, 314, 9502, 44214);

**Using the Movie table, list the title, year, runtime and rating of rows that meet all the criteria**

**Ratingcode is "M", Length of the film is 120 minutes or longer**

SELECT title, reyear, rating_code FROM Movie
WHERE rating_code = 'M'
AND runtime >= 120;

**List all movies that have the text DAY appear in the title (handle all variations)**

SELECT * FROM Movies
WHERE upper(title) LIKE '%DAY%';

**List all movies that have an "M" as part of the rating_code**

SELECT * FROM Movies
WHERE rating_code = 'M';

**List all movies that do not have "M" as part of the rating_code

SELECT * FROM Movies
WHERE NOT rating_code = 'M';

**Write a single SQL statement to list the title, rating_code, imdb_score, colour_code of all movies where the criteria is met**

**Rating 'PG' and imdb_score is greater than 8.0**
**Rating is 'M' and the imdb_score is greater than 7.7**

SELECT M.title, M.rating_code, M.imdb_score, C.colour_code
FROM Movies M
INNER JOIN ColourType C
ON M.colour_code = C.colour_code
WHERE (M.rating_code = 'PG' AND M.imdb_score > 8.0)
OR (M.rating_code = 'M' AND M.imdb_score > 7.7);

**Modify the query above to only list movies that have a colour_code of 'B'**

SELECT M.title, M.rating_code, M.imdb_score, C.colour_code
FROM Movies M
INNER JOIN ColourType C
ON M.colour_code = C.colour_code
WHERE M.colourCode = 'B';

Would you even need to join the tables since Movies will already have the colour code.

**List each movies title, year and runtime where the following criteria is met**

**Relyear is one of the following 1999, 2006** 
**Runtime is >= 140**

SELECT title, relyear, runtime
FROM Movies
WHERE relyear in (1999, 2006)
AND runtime >= 140;

**For each row in the Movie table, display the title, relyear, rating_code and the matching short desc from the Rating table.**

SELECT M.title, M.relyear, M.rating_code, R.shortdesc
FROM Movie M
INNER JOIN Rating R
ON M.rating = R.rating;

**For each row in the Movie table, display the title, year, rating_code and the matching longdes from the rating table where the relyear is less than 1980**

SELECT M.title, M.relyear, M.rating_code, R.longdesc
FROM Movie M
INNER JOIN Rating R
ON M.rating = R.rating
WHERE M.relyear < 1980;

**List the title, relyear, rating_code, longdesc for all movies that have the text '15' located within the longdesc.**

SELECT M.title, M.relyear, M.rating_code, R.longdesc
FROM Movie M
INNER JOIN Rating R
ON M.rating = R.rating
WHERE R.longdesc LIKE '%15%';

**For each row in the Movie table, display the title, year, rating, short description and the colourname column from the colour type table.**

SELECT M.title, M.relyear, M.rating_code, R.shortdesc, C.colourname
FROM Movie M
INNER JOIN Rating R
ON M.rating = R.rating
INNER JOIN ColourType C
ON M.colour_code = C.colour_code;