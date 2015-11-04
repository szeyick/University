## Exam Notes

### Things to be careful of

- Operator precedence, remember that AND is higher than OR

- Operator precedence is relational operators (<, > , =, etc), brackets, NOT, AND, OR

- In ORDER BY, if ASC or DESC is not specified, it is always ASC.

- Create the statements propertly, don't forget commas.

- If we are adding column numbers together, if one of the columns for the row is NULL, it is not included in the result set.

- LIKE '%SOMETHING%' also will include 'SOMETHING', it appears that it is % is 0 or more instances.

- Be careful when identifying data that may be redundant. It may actually just be the minimal amount of information you need to link to another table and that link ends up being the FK.

- What the difference between Foreign Keys and INNER JOINS

- A Strong entity is an entity that can be uniquely identified from its own attributes.

- A Weak entity is required to borrow an attribute to make it identifiable, in an ERD, this borrowing is highlighted by the underline in the relationship name. It borrows from the **ONE** end of the relationship.

- The purpose of a weak entity is to allocate an instance of one entity to an instance of a second entity. For example, the Student-Enrollment-Subject ERD, the Enrollment entity is the weak entity. The purpose of it is to **enrol** an instance of **Student** into an instance of **Subject**.

- The statement SELECT COUNT(*) FROM MOVIE, counts the number of rows. It appears that we cannot add any column to it since the other columns will not return a single value like the **aggregate function** does. The count will only count **NON NULL** entries.

- The other aggregate functions SUM, AVG, MIN and all that will only calculate against NON NULL entries. For example, if we had 4 rows with values, 1,2,NULL, 4 and we wanted to take the average with AVG(COLUMN_NAME), it will return (1+2+4) / 3 = 2.333 since it does not factor in the NULL row.

- The only way it appears you can mix aggregate functions (COUNT, AVG, MIN, MAX) and non-aggregate functions (COLUMN NAMES) is by using the **GROUP BY** clause.

- When using aggregate functions with multiple non-aggregate functions, we need to add the non-aggregate functions to the GROUP BY clause. The aggregate function is **NOT ADDED** to the GROUP BY.

- The **HAVING** clause keeps a row in the result set, if the value meets a particular criteria (aggregate functions). This clause specifies which rows in the result set are kept or eliminated. This is different to the WHERE clause as the WHERE specifies which rows are used to build the result set. The HAVING clause filters the result set.

- The ORDER BY can only contain non-aggregate functions, the HAVING clause needs to contain the same aggregate function described in the SELECT

- CHECK constraints in the CREATE table statement follows the syntax CHECK(columnName IN ('value', 'value2', etc))

- CHECK constraints should only be used on columns where the values are fixed and will never change. 

- **A row will pass a check constraint rule if the condition is TRUE or NULL**

- **A CHECK syntax is the same as WHERE, except a check constraint cannot refer to values in other rows or tables**

- **A CHECK syntax is the same as WHERE, except a check constraint cannot contain subqueries**

- **A CHECK syntax is the same as WHERE, except a check constraint cannot call SQL function**

- The expanded WEAK entity is called an **intersection entity**, it is one that has no attributes of its own other than the weak entities.

- The expanded WEAK entity that has attributes is called an **associative entity**

- An entity that only contains a single attribute is called a **characteristic entity**

- A characteristic entity is useful because it forces a reference to a table. Allowing for only specified values to be entered.

- Aggregate functions are not allowed in a WHERE clause unless it is part of a subquery. it needs to be within its own query.

- **Left Outer Join : guarantees that the rows in the LEFT table will not be eliminated from the result set**

- **Left Outer Join : In a Left Outer Join, the LEFT table is the table listed first in the statement. (The one after the FROM clause)**

- **Right Outer Join: guarantees that rows in the RIGHT table will not be eliminated from the result set**

- **Right Outer Join: In a Right Outer Join, the RIGHT table is the table listed on the right of the RIGHT OUTER JOIN**

- UNION and UNION ALL...UNION returns all non duplicate rows from joining 2 queries, whereas UNION ALL will. The data types in the Union need to be the same.

- INTERSECT returns all the queries found in both result sets. (i.e. Same name found in both)

- MINUS returns all the unique queries selected by the first query and not the second.

- **Be careful when using OUTER JOINS especially right outer joins.**

- **When deciding on where to place an attribute, think as to whether that attribute can have multiple values, if it can, then it probably doesn't below in a row entry for that entity.**

- **To verify the relationships, create some sample data to enter into the entities to see if they match up. If you can uniquely identify a row, then it should be ok.**

- **To ensure that you don't add too many attributes to the entity, make sure that you cannot enter NULL values to the PK otherwise it will fail.**

- When constructing an ERD, try to separate the static and variable data.

- When deciding on whether you need a 'OFFERING' entity, ask yourself - 'Is one instance of this entity offered multiple times'

- If we add || ' ' || in the SELECT statement between 2 columns (i.e. surname || ' ' || firstname AS "ACTOR NAME") it appends the result of the 2 columns together by whatever is in between the ' '.

- When drawing ERD's from business cases, identify if it requires an "OFFERING", is it offered multiple times, if it is then it will need one.


### Steps to Convert a M:M into a 2 M:1

- 1. Remove the M:M relationship line.
- 2. Replace it with a new **weak** entity and give it a name
- 3. Draw 2 new relationship lines where the weak entity is the Many side, underline the relationship since it needs to **borrow** attributes
- 4. The new relationship must have a MAY and MANY o< on the weak side, and a MUST and ONE ++ on the parent side.
- 4. Add any additional attributes if required.