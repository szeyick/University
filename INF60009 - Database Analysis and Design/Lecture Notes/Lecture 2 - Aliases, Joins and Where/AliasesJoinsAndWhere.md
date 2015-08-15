## Aliases, Joins, Where and Operators

This lecture contains notes about keys (primary and foreign), alises, joins and operations with the where clause.

### Foreign Keys

As discussed previously, foreign keys are the keys that allow us to link one table with another.

There is a distinct relationship between the foreign and primary key.

- **The Primary Key is the parent.**
- **The Foreign Key is the child.**

The Primary Key (parent) will be located at the **One** end of a **Many to One** relationship. In other words, each child has 1 parent.

The Foreign Key (child) will be located at the **Many** end of a **Many to One** relationship. In other words, each parent can have many children.

### Foreign Key Constraints

The constraint is where we can force the RDBMS to **validate** the input that is added to the foreign key column. If a foreign key value does not match a primary key value in the associated table, it will raise an error.

### Parent and Child Rules

- The parent cannot be deleted if it still has a child.
- The parent can be deleted if it has no children.

If a deletion is conducted that violates any of the above conditions, it will be invalided and an error will be raised.

### Null Foreign Keys

NULL is an acceptable foreign key value. This just means that there is an unknown relationship between the row entry and the associated table. If we use our Lecturer - Subject database, having a NULL foreign key in the Subject table will mean that the Subject is not currently assigned a Lecturer.

### Null Constraints

Although we can have NULL has a valid table value, there are instances where this is not the desired result and we wish to **always** have a value in the column.

To ensure that always have a value for the particularly row entry, we can add a **Not Null** property to the table definition when we create.

```
Create Table SUBJECT (
  SubjectCode Varchar2(10),
  Title       Varchar2(100),
  CreditPoints Number,
  LecId, Number **Not Null**,
  Primary Key (SubjectCode) ,
  Foreign Key (LecId) References LECTURER
);
```

In the example above, by adding **Not Null** to the LecId column, we enforce it to always take a value. If no value or a null is entered, the RDBMS will return an error and the insertion will fail. Any column in the table can have this constraint enforced upon it, and this is true of the Primary Key column, however this should always have a value and will not need the **Not Null** constraint added to the column definition.

### ERD Participation Constraints

In an ERD, a participation constraint is the same as the **Not Null** constraint of a Relational Model.

To illustrate this in the ERD, for each entity that we want to always exist, we draw a | line. This means that the entity **must exist** and cannot be null.

If we continue with the Subject - Lecturer relationship diagram

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%202%20-%20Aliases%2C%20Joins%20and%20Where/images/mandatoryRelatinship.png "Not Null Constraints")

The Lecturer entity contains the | so it means that a Subject **must** be convened by one Lecturer.

Conversely, we define an entity to be **optional** or **possibly null** by drawing a circle O.

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%202%20-%20Aliases%2C%20Joins%20and%20Where/images/optionalRelationship.png "Optional Constraints")

The Lecturer this time contains the O so it means that a Subject **may (could be NULL)** be convened by one lecturer.

### Cardinality and Participation Constraints

These constraints determine the number of tables that will be created in the database. It is an extention of the | and O properties from the ERD.

A **participation constraint** defines whether there will be any **non null constraints** in the table. It does not affect the number of tables or columns that are created.

So far we have drawn the ERD without the constraints, which means that the entities must always exist.

Somtimes, the **many** end of the **many to one** relationship has an **optional (O)** drawn with it. This means that a parent MAY have children. This is the best way to represent a **many to one** relationship in a ERD as a parent may or may not have children.

The reason that we do this is because if we didn't draw the O, it means that the entity relationship **MUST** exist otherwise the table cannot be constructed and values cannot be entered.

### SQL Calculations and Select Clause

The SELECT clause allows you to select information from the database. This is usually the first step when performing database queries, where you can select everything (*) or by selecting particular columns before using other expressions and clauses to filter out the data to construct the result set.

#### Column Headings

With the SELECT clause, rather than selecting everything with the *, you can select individual columns.

Furthermore you can provide **alternative column** headings to put things in for the result set by using the **AS** keyword. The **AS** keyword however is optional.

```
SELECT Name, Innings1Score, Innings2Score, Innings1Score+Innings2Score **AS** "TotalScore"
FROM MyTable
```

The statement above will output the following table

| Name        | Innings1Score | Innings2Score  | TotalScore |
| :---------: |:-------------:| :-------------:| :---------:|
| Stuart      | 100           | 20             | 120        |
| Kevin       | 75            | 45             | 120        |
| Bob         | 150           | 10             | 160        |

By using the **AS** keyword, we create another output column named "TotalScore" that aggregates the score.

We can also rename existing columns in the output set by doing something similar.

```
SELECT Name "Player Name", Innings1Score, Innings2Score, Innings1Score+Innings2Score "TotalScore"
FROM StudentResults
ORDER BY Name;
```

| Player Name | Innings1Score | Innings2Score  | TotalScore |
| :---------: |:-------------:| :-------------:| :---------:|
| Stuart      | 100           | 20             | 120        |
| Kevin       | 75            | 45             | 120        |
| Bob         | 150           | 10             | 160        |

The new output will display the results, replacing the selected "Name" column with "Player Name" and adding the "TotalScore" same as before.ults to it.

### Table Name Qualification

In our original Subject - Lecturer example, we can select columns with the following SELECT statement.

```
SELECT LecId, LecName, Age FROM Lecturer
```

However we can use a shortcut to specify which tables columns we are trying to retrieve by using the following syntax - 

```
SELECT Lecturer.LecId, Lecturer.LecName, Lecturer.Age FROM Lecturer
```

Although it is a little redundant to do this when we only select columns from a single table, it will be useful when we select columns from two or more tables.

### Table Aliases

Using the shortcut defined above to specify the table is called a **temporary alias**. This shortcut does not permanently change how you refer to table columns but is only for the current SQL query. Therefore, as soon as you press ENTER to run the query, anything after that you'll have to set up the alias again or refer to the tables by their actual names.

The code example below displays how we use table aliases when we perform SQL queries.

```
// No alias used.
SELECT lecturer.LecId, lecturer.LecName, lecturer.Age, 
FROM lecturer
WHERE lecturer.age => 50;

// Alias used.
SELECT L.LecId, L.LecName, L.Age
FROM lecturer L
WHERE L.age => 50;

```

In the above example, we are using L as the alias for the lecturer table.

### Querying Multiple Tables

So far we have only performed queries of data on one table, however what happens when we want to select information from multiple tables. For example in our Subject - Lecturer table, we may want to **get all subject codes and the names of the lecturers that teach them**. This would require accessing both the Subject and Lecturer table.

#### The Incorrect Way

The incorrect way to select data from multiple tables would be to do the following -

```
SELECT SubjectCode, LecName FROM Subject, Lecturer
```

This will return the incorrect information as it will match every row in the first table with every row in the second table.

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%202%20-%20Aliases%2C%20Joins%20and%20Where/images/cartesianProduct.png "Incorrect Cartesian Product")

This result set is called a **cartesian product** and is probably not what you want to return.

The reason that this occurs is because the SELECT statement ignores all the constraints that may have been implemented in the table definition. SQL Select statements do not know about the relationship between the select tables other than the foreign and primary key relationship. So it will just try to select everything and match it up with everything else.

To rectify this, we must tell SQL directly how the tables are related with every select statement. This is done through **joins**.

### Inner Joins

An inner join is where the result set from the SQL query contains only the data that satisfies the foreign key - primary key relationship.

The syntax is as follows -

```
SELECT <column names>
FROM   <table-name1>
INNER JOIN <table-name2>
ON <join-condition>
```

The join condition is usually in the format of

```
<foreign-key column-name> = <primary-key column name>
```

So to continue with the Lecturer - Subject example, say we had the following statement

```
SELECT S.SubjectCode, L.LecName
FROM Subject S
INNER JOIN Lecturer L
ON S.LecId = L.LecId
```

This will mean that each row in the Subject entity will only be joined with a row from the Lecturer entity where the Foreign-Key in the Subject matches the Primary-Key in the Lecturer table.

The result set will be filtered to only display a single copy of the retrieved data.

We can also filter it further by using the WHERE clause

```
SELECT S.SubjectCode, L.LecName
FROM Subject S
INNER JOIN Lecturer L
ON S.LecId = L.LecId
WHERE L.Age < 35
```

It will essentially filter the result table even further.

### Old Style Join

An old style join is where you use the WHERE clause to determine which columns you want to join together. This is generally not the best thing to do.

```
SELECT S.Subjectode, L.LecName
FROM Subject S, Lecturer L
WHERE S.LecId = L.LecId
```

### Column Ambiguity

Column ambiguity is where the primary key and foreign key in 2 tables have the same name. In this case we will need to attach aliases to the column name otherwise SQL will generate an error as it will have no idea which table it is to insert things or find things on.

So if we had multiple tables with the same column that we want to select from, we will need to provide the alias so SQL knows which table you are referring to.

```
SELECT S.SubjectCode, L.LecturerName
FROM Subject S
INNER JOIN Lecturer L
ON S.LecId = L.LecId
WHERE Age < 35 
```

### ERD with Mulitple Relationships

So far we have only dealt with relationships between 2 entities, however in more complex databses, there will usually be a third or forth or more entities that are within a single relationship.

If we modify the Subject - Lecturer example, we will add another entity into the relationship, this one being an Office entity that provides details information about the office that the lecturer is in.

The resulting ERD we will have is 

![alt text](https://github.com/szeyick/University/blob/master/INF60009%20-%20Database%20Analysis%20and%20Design/Lecture%20Notes/Lecture%202%20-%20Aliases%2C%20Joins%20and%20Where/images/ERDWithMultipleEntities.png "Multiple Entities")

The new relationship here we follow the same naming convention -

- One Lecturer **may** be located in **One** Office.
- One Office **may** contain **Many** Lecturers.

Another point to note is that the Foreign Key is always contained within the **Many** end of a **Many - One** relationship.

#### Converting a ERD to Relational Model

The new ERD has 2 entities that have a **many relationship**. This means that the relational schema will have 2 Foreign Key as each M:1 relationship will generate a Foreign Key.

```
OFFICE (OfficeNo, YearPainted, SqMeters)

LECTURER (LecId, LecName, Age, OfficeNo)
ForeignKey OfficeNo References OFFICE

Subject (SubjectCode, Title, CreditPoints, LecId)
Foreign Key LecId References LECTURER
```

### Joining Queries From Multiple Tables

Suppose we want to know how large the office is for the convenor of the subject INF11002, how do we access the three tables. We can use **inner joins** again to retrieve the data from the tables.

```
SELECT S.SubjectCode, L.LecName, O.SqMeters
FROM Subject S
INNER JOIN Lecturer L
ON S.LecId = L.LecId
INNER JOIN Office O
On L.OfficeNo O.OfficeNo
ORDER BY L.LecName, S.SubjectCode
```

The code example above will output the request data sorted by lecturer name and subject code.

### Complex Queries

We can come up with more complex queries by filtering on the WHERE clause. The WHERE clause uses logical operators (AND, OR).

```
SELECT * FROM Lecturer
WHERE Age >= 20 AND Age <= 29
OR LecId > 500
```

The idea here is that if we want to create complex queries mixed with both AND's and OR's to put them into brackets to split them. The statements in the brackets are always evaluated first.

### SQL Operators

The following is a list of basic operators used in the WHERE clause for SQL.

- = equals
- <> not equals
- != not equals (may not work on some DBMS)
- < less than
- > greater than
- <= less than or equal to
- >= greater than or equal to

### NOT Operator

The NOT operator negates the expression that follows (to the right)

```
SELECT * FROM Employee WHERE NOT (name = 'John').
SELECT * FROM Employee WHERE NOT (name = 'John' OR gender = 'F').
```

### Order of Precedence

Logical operators are evaluated in a particular order -

- Relational Operators (<,>, =, etc)
- Brackets
- NOT
- AND
- OR

### UPPER() & LOWER()

The Upper() function takes all the characters within the bracket and changes them to uppercase.
The Lower() function takes all the characters within the bracket and changes them to lowercase.

The functions do not change the values within the table but just temporarily changes the values to evaluate in the result set.

These functions are useful when you want to evaluate the data within the tables consistently as 'Smith' and 'SMITH' will be considered as different values.

### Between Operator

This can be used rather than creating an greater than and less than statement

```
WHERE age BETWEEN 30 and 40

is equivelant to

WHERE age >= 30 AND age <= 40
```

### Like Operator

This operator allows you to find partial matches for text values.

For this operator we use the % to substitute 1 or more characters, and is never used with an '=' 
since you cannot partially match and absolute value.

```
WHERE UPPER(name) LIKE 'J%'
```

Will find all names that begin with J.

### Special Operators

SQL contains a bunch of special operators that allow you to use with the WHERE clause.

####In Operator

The In operator allows you to define a list of values that you want to match in a column. It essentially functions as an OR statement.

```
WHERE upper(name) IN ('John', 'Jerry', 'Bob', 'Stuart')
```

Will return if any of those names exist in the name column.

#### Is Null / Is Not Null

This operator will allow you to find if there are values set or not set in a column.

```
WHERE height IS NULL
WHERE name is NOT NULL
```

If we want to find whether a value has been set in the column or not, do not use the equals or 'null'.

#### Distinct

The DISTINCT keyword will remove duplicate entries from the result set.

```
SELECT DISTINCT country FROM student
```

#### Unique

Does the exact same as the DISTINCT keyword, but DISTINCT is preferred.