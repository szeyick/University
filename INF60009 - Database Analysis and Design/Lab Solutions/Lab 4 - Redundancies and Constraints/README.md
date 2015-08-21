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

|WID     | WNAME   | BRANCHID | BRANCHNAME | BRANCHSIZE| WGENDER | WSALARY|
|:------:|:-------:|:--------:|:----------:|:---------:|:-------:|:------:|
|1       | Dave    | R        | Richmond   | 15        | M       | 50000  |
|2       | Sally   | H        | Hawthorn   | 10        | F       | 65000  |
|4       | Karen   | R        | Richmond   | 15        | F       | 37000  |
|8       | Kim     | H        | Hawthorn   | 10        | F       | 61000  |
|11      |Chris    | R        | Richmond   | 15        | M       | 54000  |

Split the table into 2, one for branch and one for worker. 

- Are the values in the column BranchID an example of redundant data?

Yes it is because the data is duplicated, each ID refers to the same branch which could be extracted out into its own table to allow for better lookup and reducing duplicate values.

- Give an example of a situation that would cause an update anomaly?

In the above example, the table will receive an update anomaly if you update any of the BranchID, BranchName and BranchSize columns for an individual row. For example, if you updated Karen's row, and changed the BranchName without changing the BranchID or BranchSize, you would have created an anomaly because when you look up details for the BranchID, the R would refer to something else instead rather than Richmond.

- Convert this single table into two individual tables complete with Primary Key and Foreign Key so that data redundancy is eliminated.

```
CREATE TABLE EMPLOYEE (
  WID NUMBER(3),
  WNAME VARCHAR2(10),
  WGENDER VARCHAR2(1),
  WSALARY NUMBER(6),
  BRANCHID VARCHAR2(1),
  Primary Key (WID),
  Foreign Key (BRANCHID) References BRANCH
);

CREATE TABLE BRANCH (
  BRANCHID VARCHAR2(1),
  BRANCHNAME VARCHAR2(10),
  BRANCHSIZE NUMBER(2),
  Primary Key (BRANCHID)
);
```

The converted table will result in a MANY:ONE relationship from the EMPLOYEE (MANY) to BRANCH (ONE).

- List the data values that would be in each row of the two tables.

**Refer to the previous question**

**Consider the ERD**

Assume the ERD has been converted into a Relational Schema.

- How many Foreign Keys does the COUNTRY relation have?

The COUNTRY relation has no foreign keys because it is the parent. It is on the ONE side of the MANY:ONE relationship with the PRODUCT which means that it should have no foreign key relation.

- How many Foreign Keys does the PRODUCT relation have?

The product relation has 2 foreign keys since it is the child in the relationship containing the MANY side of the relation. It is perfectly acceptable for an entity to have 2 foreign keys in an ERD.

- Write the CREATE TABLE statement for PRODUCT.

```
CREATE TABLE PRODUCT (
  ProdId NUMBER(5),
  ProdName VARCHAR2(30),
  DesignedInCountryId NUMBER(5),
  ManufacturedInCountryId NUMBER(5),
  Primary Key (ProdId),
  Foreign Key (DesignedInCountryId) REFERENCES COUNTRY,
  Foreign Key (ManufacturedInCountryId) REFERENCES COUNTRY
);
```

We can use multiple foreign keys to reference the same table, but the idea here would be that we need to change the attribute name since you can put duplicate attribute names in a table. This is perfectly fine as the **Foreign Key** reference that we write will define that the attribute refers to the primary key in another table.

To extract it out and make it useful, we would need to use aliases to refer to the table twice rather than once. This sort of tricks SQL into thinking that we have 3 entities rather than 2. They will still refer to the same primary key however.

- Assume that the following data has been inserted into the Country table.

1 Australia
2 New Zealand
3 China

Write insert statements -

INSERT INTO PRODUCT (ProdId, ProdName, DesignedInCountryId, ManufacturedInCountryId) VALUES (111, 'Picture Frame', 'Australia', 'China');
INSERT INTO PRODUCT (ProdId, ProdName, DesignedInCountryId, ManufacturedInCountryId) VALUES (222, 'Coffee Cup', 'New Zealand', 'China');
INSERT INTO PRODUCT (ProdId, ProdName, DesignedInCountryId, ManufacturedInCountryId) VALUES (333, 'Door Mat', 'Australia', 'China');

**Consider this department table**

|Location      | DeptId     | Manager Name |
|:------------:|:----------:|:------------:|
|Melbourne     | 1          | Fred         |
|Melbourne     | 2          | Dave         |
|Sydney        | 1          | Sue          |
|Sydney        | 2          | Emma         |

- Who is the manager of department 1 in Melbourne?

Fred

- Who is the manager of department 1 in Sydney?

Sue

- How many primary keys does the Department Table have?

If anything, it would be 1 since Manager Name is the only unique value in the table. However you could combine it with Location and DeptId to make a composite key.

- How many columns does the primary key have?

1

- What is the primary key of the department table?

It would be the Manager Name. 

- Write the create table statement for Department

```
CREATE TABLE Department(
  ManagerName VARCHAR2(20),
  Location VARCHAR2(20),
  DeptId NUMBER(2),
  Primary Key (ManagerName)
);
```

### Lab Questions

- Implement the Worker / Task Problem 

**Refer to the above**

- Create an SQL Script

```
DROP TABLE employee;
CREATE TABLE employee (
Empid NUMBER PRIMARY KEY
, empname VARCHAR(20)
, fav_colour VARCHAR(20)
, salary NUMBER
, year_commenced NUMBER
, bonus NUMBER
);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (1, 'Sam', 'Red', 60000, 2004, 0);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (2, 'Ellen', 'Blue', 75000, 2015, 5000);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (3, 'Donna', 'Grey', 45000, 2013, 850);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (4, 'Sam', 'Red', 65000, 1997, 5000);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (5, 'Greg', 'Blue', 30000, 2015, 0);
INSERT INTO employee (empid, empname, fav_colour, salary, year_commenced, bonus)
VALUES (6, 'Sophie', 'Red', 88000, 2013, 2500);
```

- List all rows in the employee table

SELECT * FROM employee;

- List the empid, salary and bonus columns. Also display a total income column, this is calculated by adding salary and bonus.

```
SELECT Empid, salary, bonus, salary+bonus AS "Total Income" 
FROM employee
ORDER BY Empid ASC;
```

- Write a single update statement to set the bonus of employee 3 to zero.

```
UPDATE employee
SET bonus = 0
WHERE Empid = 3;
```

- Write a single update statement to set the salary of employee 1 to 70000.

```
UPDATE employee
SET salary = 70000
WHERE Empid = 1;
```

- Write a single update statement to set these values for employee 2: Salary:80000, Bonus:8550

```
UPDATE employee
SET salary = 80000, bonus = 8500
WHERE Empid = 2;
```

- List all of the rows in the mployee table to check your updates have worked

```
SELECT * FROM employee;
```

- Write a single update statement to increase every employees salary by 10%

```
UPDATE employee
SET salary = (salary * 1.1)
```

- List all of the rows in the mployee table to check your updates have worked

```
SELECT * FROM employee;
```

- Write a single delete statement to remove employees who have favorite colour to Blue

```
DELETE FROM employee
WHERE UPPER(fav_colour) = 'BLUE';
```

- List all of the rows in the mployee table to check your updates have worked

```
SELECT * FROM employee;
```
