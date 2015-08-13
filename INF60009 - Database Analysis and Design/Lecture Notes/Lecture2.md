## Lecture 2 Notes

This contains rough notes for Lecture 2 that will need to be moved.

### Foreign Keys

There is a distinct relationship between the foreign and primary key.

- The Primary Key is the parent.
- The Foreign Key is the child.

The parent will be at the **one** end of a many-one relationship, in other words this means that a child has 1 parent.

The child will be the **many** of the many-one relationship, in other words this means that a parent can have many children.

### Foreign Key Constraints

This is where we can force the the RDBMS to **validate** the input that is placed into the foreign key column. An error will be raised if the entered foreign key does not match anything in the other table.

### Parent and Child Rules

- The parent cannot be deleted if a child exists.
- The parent can be deleted if it has no children.

### Null Foreign Keys

- Null is an acceptable foreign key value. It just means that there is an unknown relationship between the row and the foreign key, or in relation to the lecturer-subject table, that the subject currently has no lecturer.

### Null Constraints

Although null is a perfectly valid value to enter into a table, there are instances where it the entry in the row should be non-null or mandatory.

To enforce the entry, when we define the table we can specify and additional property of **Not Null**

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

The above example for the LecId states that the number is mandatory. If a null is entered, the RDBMS will return an error. Any column in the table can enforce values to be entered.

### ERD Participation Constraints

An ERD participation constraint is equivelant to the **Not Null** constraint. 

To illustrate this for the entity we draw a | line, which means that the entity **must** exist or must be not null.

To continue with the subject - lecturer relationship, if we drew the diagram as such -

```
Subject->---Convened By---||-Lecturer
```

The | means that the subject **MUST** be convened by a lecturer entity.

If we have a circle O, it denotes that an entity **MAY** exist, or that it is optional.

```
Subject ->---Convened By---O|-Lecturer
```

This O means that the subject **MAY** be convened by a lecturer (or it may be null).

### Cardinality and Participation Constraints

These determine the number of tables that will be created in the database.

A **participation** constraint just states whether there are any **non null constraints** in the table. It does not affect the number of tables or columns that are created.

Sometimes, the **MANY** end of the relationship has an **optional (O)** that means that a parent MAY have children. This should be drawn at the end of the many side of the relationship (**RECOMMENDED**).

**Mandatory** participation sometimes causes problems as it definitively states that the relationships **MUST** exist otherwise the table cannot be constructed and values cannot be entered.

### SQL Calculations and Select Clause

The selection clause allows you to select information from the database. You generally select by column names first then use the other expressions or calculations to filter out the data that you do not want.

#### Column Headings

With the Select clause, rather than selecting the entire table with the *, you can select individual columns.

You can provide **alternative column headings** to the output result by using the **AS** keyword.

```
SELECT Name, Column1, Column2, Column1+Column2 **AS** "Total"
FROM MyTable
```

You can rename columns in the output by doing that

```
SELECT Name "Student Name", Test1, Test2, Test1+Test2 "Total Score"
FROM StudentResults
ORDER BY Name;
```

This will output the results, replacing the "Name" with "Student Name" and adding a final column "Total Score" that outputs the appended results to it.

### Table Name Qualification

In our original example we select column names with the following statement

```
SELECT LecId, LecName, Age FROM Lecturer
```

However we can use a shortcut to specificy the table who's columns we are choosing with the following syntax

```
SELECT Lecturer.LecId, Lecturer.LecName, Lecturer.Age FROM Lecturer
```

It allows us to easily select columns when we select two or more tables.

### Table Aliases

The names of tables in SQL can be assigned **temporary aliasas**, meaning that it is only valid for the currently entered SQL statement, meaning for the following statements it is no longer valid and you will need to refer to the table by the original table name.

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

Sometimes you want to get information from multiple tables.
