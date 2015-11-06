## Networking, Recursive Relationships, Indexes and Normalisation

### Network Diagrams

It is a visual method used to quickly show relations, relationships and foreign keys of a relational schema.

The diagram will show the following - 

- Relations - Represented by Rectangles
- Relationships - Represented by Arrows that connect the relations
- Foreign Keys - Text attached to the arrows (relationships)

The **relationship arrows** will always point from the foreign key to the primary key.

The diagram is usually drawn from the bottom up, or where the weakest table is at the bottom.

**Insert Image**

The diagram is similar to that of a basic class diagram that shows which table is associated to which table.

### Recursive Relationship

A recursive relationship is a single relationship between two instances of the same entity type.

In other words, it is a cyclic relationship, for example -

- An Employee may have a manager.
- A manager may managed many employees.
- A manager is an employee.

The recursive relationship here is that a manager is also an employee.

**Insert Image Recursive Relationship**

Because the manager is also an employee, the relationship loops back on itself.

### Self Referencing Foreign Key Constraint

A foreign key may reference a primary key that is in the same table. This is evident when describing a recursive relationship.

Because of this, the foreign key is required to have a different name to the primary key, using the **references** tag. Otherwise it would be impossible to differentiate between the primary key and the recursive foreign key.

```
CREATE TABLE EMPLOYEE (
	empno number PRIMARY KEY,
	name varchar(50),
	salary number,
	mgrno number,
	FOREIGN KEY (mgrno) REFERENCES EMPLOYEE
);
```

### Self Joins In SQL

The SQL select statement cannot access two rows from a single at the same time. To resolve this we will need to use aliases to join the two instances of the same table together.

```
SELECT emp.name, mgr.name
FROM EMPLOYEE emp
INNER JOIN EMPLOYEE mgr
ON emp.mgrno = mgr.empno;
```

Subsequently the same method can also be used for OUTER joins.

### Sequential Record Access

This is useful as a base for the understanding of how data is stored on a disk for a DBMS.

Rather than sequentially reading all rows from the beginning of a table until the correct information is found, a DBMS can use something called an **index**.

It is used to speed up access to data, similar to that of searching for a book in a library through a catalogue or category.

This is the equivelant of a **linear search** of an array.

### Inserting Data Into An Index

This is the process when we insert data into an **index**. We have a secondary table called "Index", this index is composed of 2 attributes (columns), the first being the **PK** and the second being the **Row Number**. The PK is obviously for the PK of the row object that we are inserting, whereas the row number is the row that the PK is found on in the table it is inserted into.

It appears to be similar to that example of the SortedFilterModel in how it uses the index to keep track of where the data is in the real table. This Index table is also sorted by **PK** value order.

### Searching the Index

Rather than searching the table data, as the PK's are not in any particular order, we can instead search the "Index" table. Because the table is sorted by key order, if we are looking for a particular key we can use techniques such as a  **binary search** to find the key which then returns the row value. 

We can then use this row value to look at a specific row in the actual table.

The actual data in the index table **cannot** be viewed, and is usually stored as a binary or compressed.

### Advantages of using an Index

It is faster to search for the requested row, uses less memory because the index table is smaller than the data table. It is also faster because there are less read/write cycles to the disk.

### Create An Index

An index can be created easily -

```
CREATE INDEX empsname ON employee(surname);
```

The above SQL command will create an index called **empsname** where the **PK** or key value will be **surname** in the employee table.

It is possible to use non-primary key values as the key value for the Index, however it will return the first row that it finds that matches. This means that it is also possible to store duplicate values as keys in the Index.

In the above example, using a search on the index will speed up the time to find the 1st row that matches a surname value.

### Index Cost

Using the SELECT * TABLENAME statement will indivually search all the rows of the table to find the desired result. However with an Index because it is in sorted order, it is possible to reduce that time because of the binary search. In computational terms it would of O(log(n)) for the search rather than a linear O(n).

Indexes are used to speed up the retrieval times of a row, so why not use them for all rows.

The reason that we do not is that memory is expensive so holding indexes for all columns in a table can be very expensive.

Because the Index is in sorted order, any changes to the reference table will require changes to the Index. If we remove a row, we need to update the order of the Index which can be slow, also if we are adding it could also be slow as it needs to keep the Index in order. Updating could also change the order depending on the column that was selected as the key value.

However Indexes are independent of the reference table meaning that they can be deleted without issue.

### DBMS Optimisers

An optimiser is a method to decide the quickest and most efficient way to access data.

It is possible to have multiple indexes for a database, however it is the optimiser that decides whether it should use it. With small databases, it might be faster to just read all the rows of the table than it would to load the index into memory then search it.

It is also possible to create multi-level indexes, where you break up the problem into smaller pieces. It is somewhat similar to using multiple Maps to look for a particular item.

### Normalisation

Design technique to generate Relational Schemas, used as an alternative to an ERD, requiring no diagrams.

The objective of normalisation is to achieve **3rd Normal Form (3NF)**.

### Normalisation Stages

Tables that have not been normalised may have potential anomalies in their Insert, Update and Delete operations.

This can be avoided by **normalising** the database. There are several stages of normalisation, where as each stage a potential source of anomalies is removed.

- **Stage 1 - Remove Repeating Groups**

To begin, we have an unnormalised table, which appears to just be a normal table of data. However in this table, there can be columns where there are multiple values in a each cell. Multiple values in a cell are called **repeating groups** and this is **Unnormalised Form or 0NF**.

To remove the repeating groups, we extract out the repeated values and put them into their own row in the table. The result expected from this state is that all rows will have a maximum of 1 value in each column.

Once completed this is called the **First Normal Form or The Universal Relation**

After this, we need to find the **primary key** for this First Normal Form table before we can continue. 

To identify the primary key, we do the same thing as we would do with an ERD and its attributes, but this time we use the column names. Although the column names are in fact just attributes anyway. The objective to find the PK is to use the fewest columns possible, the PK is called the **Universal Key**

Once we have created the **Universal Table** and found the **Universal Key**, we have achieved 1NF.

- **Stage 2 - Remove Part Key Dependencies**

This is the beginning of the 2NF, we need to identify the **functional dependencies**. This is where one attribute can be **determined** by another, this is to ensure that for the PK, there can only be one value for that PK. 

The question should be - "Is there only ONE < column name > for that < primary key > (Is there only ONE student name for that studentID)"

If the answer is YES, then there is a functional dependency.

A **part key dependency** is where a column in the **Universal Table** is only partly dependent on the **Universal Key**. This may happen if we have multiple columns for our **Universal Key** and the answer to the above question is only YES to some of the columns in the key.

In other words, if one of the columns only dependent on part of the Universal Key. So if a key had 2 columns, if any of the columns in the Universal Table only depend on one of the 2 keys, then it is seen as a **part key dependency**.

From our Universal Key, we remove the column that is causing the **part key dependency**. The remaining key becomes the **determinant or PK** of a new table. 

We define this table by giving it a name < TABLE NAME > (PK, < dependent column name >).

We then add more < dependent column names > to the Table Name so long as they have a functional dependency on the **determinant**. You can see this is slowly forming a **Strong Entity** in a Relational Schema. This is also "removing the part key dependencies" from the Universal Table. In other words, all the columns that are dependent on the PK, are extracted out of the Universal Table, leaving the remaining data from the original table. We leave the **determinant** in there as it functions as the FK of the Univeral Table.

Once we have removed the part key dependencies from the Univeral Table, and created a new table, we have essentially created a new relation. We then proceed to do the same thing with the other half of the original Univeral Key.

Once we have done this, we only have the remaining data left in the Universal Table with other entities being created.

We have now achieved 2NF.

- **Stage 3 - Remove Non Key Dependencies**

A non-key dependency is where a column is dependent on another column but the other column is not part of the universal key.

You then do the same thing with those remaining columns to try and find a **determinant** that the columns are dependent on. This is a repeat of the 2NF step of finding a Primary Key to create a **determinant**.

Once this is done with the rest of the data, and it has been split up so that only keys remain in the Univeral Table, we have achieved **normalisation and 3NF**. The non-key dependencies should not exist in the Universal Table.