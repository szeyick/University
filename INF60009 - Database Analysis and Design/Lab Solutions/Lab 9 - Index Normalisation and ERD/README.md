## Indexes, Normalisation and ERD's

### Tutorial Questions -

- **1. What is an Index?**

An Index is a separate table that consists of 2 columns. The first column being a key, and the second column being a reference to the row.

It maps the key, to a row entry in the table. The key can be any column of the table and not just a primary key. This key will be kept in sorted order in the Index allowing for a faster lookup as you search for the key, who's value in the second column is a reference to the row number that the entry is in.

It functions like a Map.

- **2. Describe the contents of an Index based on the primary key of this table**

```
STUDENT TABLE (
	StuNo Number Primary Key,
	FirstName 	Varchar(30),
	SurName 	Varchar(30),
	Phone		Varchar(30),
	Gender 		Varchar(1)
);
```

The index will be created with the command - ```CREATE INDEX studentIndex ON STUDENT TABLE(StuNo)```

The contents of the Index will be -

```
Key 		RowNo
1010101		2
1893220		6
2020202		5
2666662		4
3330000		3
7070707		1
```

- **3. How can an index improve the performance of a database system?**

It can improve it by the reducing the number of read/write (I/O) steps that a computer needs to do. There only needs to be a step to load the index into memory and then to search it. 

If you are searching an entire database table, it performs many more read/writes as it would probably need to do so for each row. 

In addition, because an Index is in sorted order by key, we can use a binary search to search for the key rather than a linear search on a table as the rows can be in any particular order. The search speed goes from O(n) to O(logn) for the search as the search space in a binary search reduces by half at each step.

- **4. How can index be detrimental to the performance of a database system**

Because an index contains reference to a row that is in sorted order, performance of an index can be detrimental when there are updates, deletions and insertions into the database.

Because of the selected key, it is not always guaranteed that the new row that is inserted will be placed at the end of the index. It could be placed at the first index, which means that all the entries in the index need to be shuffled down. Likewise for the deletion as the index will need to be reordered. This becomes detrimental to performance because of the number of instructions that take place when performing these tasks.

- **5. Write the code that would create an index on Surname in the above table**

```
CREATE INDEX studentIndex ON STUDENT(Surname);
```

- **6. Write the code that would remove the index created in the above question?**

```
DROP INDEX studentIndex
```

- **7. Write the code that would create an index on Firstname and Surname in the above table**

```
CREATE INDEX studentIndex ON STUDENT(Firstname, Surname);
```

- **8. Consider the data in the table and answer the questions**

**a. Which of the following insert statements would cause a duplicate primary key constraint error?**

```
INSERT INTO student VALUES (2856, 'Emma', 'Jones', 'F', 2);
```

This will cause a primary key constraint error because the StuId 2856 is already assigned to Rachel Hooper

**b. How many rows do you need to look at to list the first name and tutegrp of all the students whos surname is Cortez?**

All of them.

**c. How many rows had the surname of Coretez?**

3 although it will go all the way to the end.

- **9. Imagine that the Index1 was created on the Primary Key (StuID) and the Index2 was created on (Surname)**

**a. Which of the following statements would cause a duplicate primary key constraint error?**

```
INSERT INTO student VALUES (9805 'Davis', 'Sue', 'F', 1);
```

**b. How many rows in the student table do you need to loo kat to list the firstname and tutgrp of all the students name whose name is Cortez?**

11 if you are going top down, log(n) if you are using a binary search.

**c. How many rows had the surname of Cortez?**

3

- **10. What is a recursive relationship?**

A recursive relationship is a an entity that references itself. An example of this would be the Employee - Manager relationship, as a Manager is also an Employee.

**It is a FK in a table that references the PK of the same table**

- **11. Write the CREATE TABLE statements for the ERD**

```
CREATE TABLE BRANCH (
	BNO 	VARCHAR2(2),
	BName 	VARCHAR2(30),
	PRIMARY KEY (BNO)
);

CREATE TABLE WORKER (
	Wno 			VARCHAR(2) PRIMARY KEY,
	Surname 		VARCHAR2(30),
	Firstname 		VARCHAR2(30),
	BNO 			VARCHAR2(2),
	SupervisorNo 	VARCHAR(2),
	FOREIGN KEY (SupervisorNo) REFERENCES WORKER,
	FOREIGN KEY (BNO) REFERENCES BRANCH
);
```

- **12. Write Insert statements to add the following branch details**

```
B1, Branch One
B2, Branch Two

INSERT INTO BRANCH (BNO, BName) VALUES ('B1', 'Branch One');
INSERT INTO BRANCH (BNO, BName) VALUES ('B2', 'Branch Two');
```

- **13. Write Insert statements to add the following**

```
W1, Smith, Alan, B1 - no supervisor
W2, Brown, Sue, B2 - no supervisor
W3, Grant, Doug, B1 - supervised by Alan Smith
W4, Jones, Julie, B2 - supervised by Sue Brown
W5, McCartney, Paul, B1 - supervised by Sue Brown

INSERT INTO WORKER (Wno, Surname, Firstname, BNO, SupervisorNo) VALUES ('W1', 'Smith', 'Alan', 'B1', NULL);
INSERT INTO WORKER (Wno, Surname, Firstname, BNO, SupervisorNo) VALUES ('W2', 'Brown', 'Sue', 'B2', NULL);
INSERT INTO WORKER (Wno, Surname, Firstname, BNO, SupervisorNo) VALUES ('W3', 'Grant', 'Doug', 'B1', 'W1');
INSERT INTO WORKER (Wno, Surname, Firstname, BNO, SupervisorNo) VALUES ('W4', 'Jones', 'Julie', 'B2', 'W2');
INSERT INTO WORKER (Wno, Surname, Firstname, BNO, SupervisorNo) VALUES ('W5', 'McCartney', 'Paul', 'B1', 'W2');
```

- **14. Write the SQL statement to list every worker and his/her supervisor**

```
SELECT S.Firstname ||' '|| S.Surname AS "WorkerName", W.Firstname ||' '|| W.Surname AS "Supervisor"
FROM WORKER S
LEFT OUTER JOIN WORKER W
ON S.SupervisorNo = W.WNO;
```

**Note** - When doing recursive relations, it is best to draw the table out that links the FK of the child table with the PK of the parent table. Then inner join on that, or outer join if you want to show all FK's that do not match a FK.

### Normalisation -

- **16. The table is in 1NF, the key is StudentNo + SubjectCode which is the Part Key Dependency**

**a. What is a Part Key dependency?**

A part key dependency is where a column in the Universal Relation (Original Table 1NF), only depends on one of the keys that have been identified as the **Universal Key**.

**b. Which column has a PartKey dependency?**

The Convenor is dependent on Subject Code, so is Convenor Office

**c. how do you "remove the part key dependency?**

You take the part of the Universal Key that it matches up with, and take the universal key portion along with the columns that are part key dependencies and you create a Relational Schema for it with the syntax ```< TABLE NAME > (UNIVERSAL KEY HALF, COLUMN_1, COLUMN_2, etc) ```

Once that is done, you can remove the part key dependencies from the Universal Table.

**d. Write the schema after the removal of the part key dependency**

SUBJECT (SubjectCode, ConvenorName, ConvenorOffice);
	PK (SubjectCode)

UNIVERSAL (StudentNo, SubjectCode, Grade); // Possible Weak Entity ??
	PK (StudentNo, SubjectCode)
	FK (SubjectCode) REFERENCES SUBJECT

- **16. The tables are in 2NF, the key is StudentNo + SubjectCode, Key for Subject Table is SubjectCode. The Subject Table has a Non-Key Dependency**

**a. What is a Non Key Dependency?**

A non-key dependency is a column that depends on another column but that column is not part of the Universal Key

**b. Which column(s) has a non key dependency?**

Convenor Office

The others appear to have a 1:1 relationship with the entry in the row.

**c. How do you "remove the non key dependency"**

You find the column and the column that it relates to, create a TABLE NAME and put the columns as attributes in this new table.

**d. Write the schema after the removal of the non key dependency**

CONVENOR (ConvenorName, ConvenorOffice)
	PK (ConvenorName)

SUBJECT (SubjectCode, ConvenorName)
	PK (SubjectCode)
	FK (ConvenorName) REFERENCES CONVENOR

UNIVERSAL RELATION (StudentNo, SubjectCode, Grade)
	PK (StudentNo, SubjectCode)
	FK (SubjectCode) REFERENCES SUBJECT

- **17. The table is in 1NF, the key for the universal table is DepartmentCode**

**a. Are there any Part Key Dependencies?**

There should not be any part key dependencies because there is only 1 univeral key. The columns will either be dependent on the key or not meaning that it is a non-key dependency.

**b. Why is it impossible for this table to have any part key dependencies**

Because it is only 1 key in the universal key, meaning that they are either dependent on the **determinant (PK)** or they are non-key dependencies meaning that they depend on another column instead.

**c. Are there any non-key dependencies in the table?**

The ManagerPhone is a non-key dependent because it is dependent on the ManagerName. This is because ONE value of ManagerPhone is dependent on ONE value of the ManagerName.

**d. Write the new schema of the two tables after the removal of non key dependency**

MANAGER (ManagerName, ManagePhone);
DEPARTMENT (DepartmentCode, Manager); // This is still part of the Univeral Table

- **18. The table below is best described as**

The table is first normal form in that the data has been split into individual rows for duplicate (repeating) rows.

It is not the second normal form because the part key dependencies are still present, and is not third normal form because it is not second normal form and there are still non-key dependencies in the table.

- **19. The table below is best described as**

3rd NF as there are no non-key dependencies.

- **21. The table below is best described as**

The table is unnormalised because the TextBooks column contains multiple values.

- **22. The table below is best described as**

The table is in 1st normal form as it contains duplicates, and the date does not depend on anything nor KM reading.

### ERD Questions

- **23. Draw the ERD for the narrative and convert to Relational Schema**

STUDENT (ID, Name);
	PK(ID)

SUBJECT (Code, Title);
	PK(Code)

EXAM (Date, ID, Code, Time); //Weak Entity
	PK (Date, ID, Code)
	FK1 (ID) REFERENCES STUDENT
	FK2 (Code) REFERENCES SUBJECT

ATTEMPT (AttemptNo, Date, ID, Code, Result)
	PK (AttemptNo, Date, ID, Code)
	FK1(Date, ID, Code) REFERENCES EXAM

ANSWERS (Question, AttemptNo, Date, ID, Code, Answer)
	PK (Question, AttemptNo, Date, ID, Code)
	FK (AttemptNo, Date, ID, Code) REFERENCES (ATTEMPT)





- **24. Draw the ERD for the narrative and convert to a Relational Schema**

