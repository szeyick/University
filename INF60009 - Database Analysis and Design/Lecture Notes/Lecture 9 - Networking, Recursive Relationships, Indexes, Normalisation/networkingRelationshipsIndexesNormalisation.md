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

### Inserting Data Into An Index

When the **primary key** is specified, an **index** is created based on that primary key. As a row is added, the DMBS will automatically insert the **search key** and **row location** into the index file, where the search key is usually the **primary key**.

This sort of works like how a hash table would work.

This is repeated for every row that is inserted, and the primary key index is **sorted** to maintain an order.

### Searching the Index

Rather than searching the table data which can be cumbersome and time consuming, we can search the index table for the required row much faster. Additionally, because the index table is already in primary key order we can use things such as a **binary search** to quickly find the primary key and its corresponding row.

After this, we can use the row number to look up in the data table to return the requested information.

The actual data in the index table **cannot** be viewed, and is usually stored as a binary or compressed.

### Advantages of using an Index

It is faster to search for the requested row, uses less memory because the index table is smaller than the data table. It is also faster because there are less read/write cycles to the disk.

### Create An Index

An index can be created easily -

```
CREATE INDEX empsname ON employee(surname);
```

The above command will create an index that stores all the employee surnames in a sorted sequence. An index will allow duplicate values unless specified otherwise and can contain non primary key values.

In the above example, using a search on the index will speed up the time to find the 1st row that matches a surname value.

### Index Cost

Indexes are used to speed up the retrieval times of a row, so why not use them for all rows.

The reason that we do not is that memory is expensive so holding indexes for all columns in a table can be very expensive.

In addition, the update time of a table can be slow, since updating a table will cause the index to also be updated to especially if a new row is added. Therefore it will need to update every single index. Basically all operations that modify a table that contains an index will require the index to also be changed (insert, update, delete).

Indexes can be created and dropped (deleted) without affecting the table data.

### DBMS Optimisers

An optimiser is a method to decide the quickest and most efficient way to access data.

It is possible to create multiple indexes, however the DMBS optimiser decides whether it is beneficial to use any of them. 

Although with larger datasets, a index may be helpful however with small datasets with only a handful of entries it may not be neccessary to use one, this is where the optimiser will make the decision.

### Normalisation

It is a database design technique used to generate relational schemas, usually used as an alternative to Entity Relationship Diagrams (ERD).

The objective of normalisation is to achieve **3rd Normal Form (3NF)**.

### Normalisation Stages

Tables that have not been normalised may have potential anomalies in their Insert, Update and Delete operations.

This can be avoided by **normalising** the database. There are several stages of normalisation, where as each stage a potential source of anomalies is removed.

- **Stage 1 - Remove Repeating Groups**

We begin with an unnormalised table where multiple values can exist in a single cell, this is otherwise called **repeating groups**.

To do this, we remove all repeating groups and add them as their own row in a new table. As a result, all the cells in the new table will only have a maximum of 1 value.

This First Normal Form is also called **The Universal Relation**.

After this the **primary key** for this new table needs to be determined before further normalisation can continue.

From the 1NF columns we need to determine the primary key, once this has been identified it is called the **Universe Key**.

- **Stage 2 - Remove Part Key Dependencies**

To achieve this stage, we need to identify the **functional dependencies**. This is where one attribute can be **determined** by another attribute.

For example - StudentID determines the StudentName

This is because there should only ever be 1 StudentName for the StudentID.

**If in doubt, ask yourself** - Is there only ONE StudentName for that StudentID, if the answer is **YES** then there is a functional dependency.

A **part key dependency** is where a column in the Universal Relation is dependent on **only a part** of the Universal Key.

In other words, if one of the columns only dependent on part of the Universal Key. So if a key had 2 columns, if any of the columns in the Universal Table only depend on one of the 2 keys, then it is seen as a **part key dependency**.

To remove, we need to place the **determinant (StudentID)** in a new table, where it becomes the primary key of the new relation. Then we add the other functional dependencies into the new relation.

- **Stage 3 - Remove Non Key Dependencies**

A **non key dependency** is where a column has a dependency on another column and that column is **NOT** part of the Universal Key. In this, we do the same as in Stage 2, and split off the non-key dependencies with the column that it is dependent on that is not part of the Universal Key.

Once this step is complete, the table is Normalised.