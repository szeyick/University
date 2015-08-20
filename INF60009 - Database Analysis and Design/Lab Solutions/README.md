### Lab 4 Solutions

This contains rough notes and answers for the Lab 4.

### Terms

- Insertion Anomaly

An insertion anomaly is where you do not have the data to insert as a primary key, but have the rest of the data that you wish to insert for a particular entry. This is often found in tables that mix designs together.

- Update Anomaly

An update anomaly is where you update a particular row in a table, and the data ends up contradicting something else in the table. This is often found in duplicate row entries where you can update a single value in the duplicate that confuses the data.

- Deletion Anomaly

A deletion anomaly is where you delete a particular row and you accidentally delete other information that is within the row that you may not have want to have deleted because of the poor data mixing.

- Data Redundancy

The process of adding the same data over and over again throughout your database.

- Data Inconsistency

The process where data appears multiple times in the database, but the data contained within it is different each time. For example, you could have a "main_phone_number" column in a database, and have duplicate rows by accident, but you update one of the duplicates phone number, this creates inconsistency as you have everything else identical other than the phone number, which should be the same.

- Composite Key

The idea of having more than 1 column in a table that is suitable for being the primary key. This is defined within the relational schema only has a SQL table cannot have multiple primary keys.

### Tutorial Questions

- Consider the ERD

**Write the create table statements once the ERD has been converted to a relational schema, ensure all primary and foreign key constraints are included.**

WORKER (WID, WORKERNAME)
TASK (TASKID, TASKDESCRIPT, WID)

```
CREATE TABLE WORKER (
  WID NUMBER(1),
  WORKERNAME VARCHAR2(10),
  Primary Key (WID)
);

CREATE TABLE TASK (
  TASKID VARCHAR2(1),
  TASKDESCRIPT VARCHAR(10),
  WID NUMBER(1) NOT NULL,
  Primary Key (TASKID),
  Foreign Key (WID) REFERENCES WORKER
);
```

**Write insert statements to add this data to the tables:**

```
INSERT INTO WORKER (WID, WORKERNAME) VALUES (1, 'Dave');
INSERT INTO WORKER (WID, WORKERNAME) VALUES (2, 'Emma');
INSERT INTO WORKER (WID, WORKERNAME) VALUES (3, 'Fred');

INSERT INTO TASK (TASKID, TASKDESCRIPT, WID) VALUES ('W', 'Cleaning', 1);
INSERT INTO TASK (TASKID, TASKDESCRIPT, WID) VALUES ('X', 'Painting', 2);
INSERT INTO TASK (TASKID, TASKDESCRIPT, WID) VALUES ('Y', 'Polishing', 1);
```

**Write a single query that lists each task description and the workername who is to perform that task**

```
SELECT T.TASKDESCRIPT, W.WORKERNAME
FROM TASK T
INNER JOIN WORKER W
WHERE T.WID = W.WID;
```

- Consider the simple ERD

**Write the schema for the entity**

LOCATION(AreaCode, Section, LocName) // Where AreaCode and Section are underlined

**How many Primary Keys does this schema have?**

1

**What is the Primary Key of Location?**

AreaCode or Section

**Write the CREATE TABLE statement**

```
CREATE TABLE LOCATION (
  AreaCode VARCHAR2(20),
  Section VARCHR2(20),
  LocName VARCHAR2(20),
  Primary Key (AreaCode)
);
```

- Consider this ERD

**How many Primary Keys does EMPLOYEE have?**

1

**How many Foreign Keys does EMPLOYEE have?**

1

**Write the schema for the Location relation**

LOCATION(AreaCode, Section, LocName)

**Write the schema for the Employee relation**

EMPLOYEE(empid, empname, AreaCode)

**Write the CREATE TABLE statement for EMPLOYEE**

```
CREATE TABLE EMPLOYEE (
  empid NUMBER(5)
  empname VARCHAR2(20)
  LocName VARCHAR2(20) 
  Primary Key (empid),
  Foreign Key (LocName) REFERENCES LOCATION
);
```

**Write a query that lists all employee names and the location name that they belong to**

```
SELECT E.empname L.LocName 
FROM EMPLOYEE E
INNER JOIN LOCATION L
WHERE E.LocName = L.LocName
```

- Why is Data Inconsistency Bad?

It is bad because if it is really inconsistent then you do not know which data is the correct one. It reduces the customers confidence in your product.

- How can Data Inconsistency be eliminated from a database?

Split the table into 2, one for branch and one for worker. 

- Are the values in the column BranchID an example of redundant data?

Yes it is because the data is duplicated, each ID refers to the same branch which could be extracted out into its own table to allow for better lookup and reducing duplicate values.
