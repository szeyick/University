## Constraints, M:M Relationships and Outer Joins

A summary of the key terms from this lecture

- **Constraints**

Oracle maintains a seperate table USER_CONSTRAINTS that stores all the columns in all the tables in the database that have NON-NULL constraints applied to them.

- **Unique Constraints**

The UNIQUE keyword when defining a column on table creation, states that the values that are entered in the table for that column are required to be unique meaning that there cannot be duplicate entries for that column.

- **Check Constraints**

The CHECK () keyword when defining a column on table creation, states that the values that are entered in the table for that column have to fulfill the conditions within the CHECK () brackets.

- **Many to Many Relationship**

Is where the relationship between two entities is defined by multiples on both ends. The way to deal with this is to break up the many to many relationship by inserting a weak entity in between the two therefore creating 2 many to 1 relationships.

- **Intersection Entity**

Is the weak entity that is the result of breaking up a Many to Many relationship. It is an entity that has no attributes of itself and inherits all its attributes from the entries. 

- **Associate Entity**

An associative entity is similar to an intersection entity, with the exception that it has its own properties.

- **Characteristic Entity**

Is an entity that only contains a single attribute. Usually found when splitting up a many to many relationship. Sometimes can seem a little redundant since the characterstic entity will contain the same attributes as the intersection entity. However can be useful as it guarantees that the entities will always exist as it is linked to the primary key.

- **SQL SubQueries**

Is a SQL query that is contained within another SQL query. Used when a result cannot be calculated within a single SQL query. Can be useful to use within WHERE statements.

- **Outer Joins**

An outer join **guarantees** that all rows from a selected table will be included in the result set. An outer join can be used to select all the rows from either the left or right table.

- **Union**

Will return the result set from two SQL queries. The data types of the two SQL queries must match.

- **Union All**

Will return the result set from two SQL queries. The difference with this compared to a union is that it will not return duplicate values that are found in both SQL queries.

- **Intersect**

Intersect causes all **unique** rows by selected by both individual queries to be added to the result set. It will be the values contained in the intersection of the queries.

- **Minus**

The minus operator will select all the unique rows selected by the first query, but not the second. It will return all the rows selected by the first query that are not selected by the second query.