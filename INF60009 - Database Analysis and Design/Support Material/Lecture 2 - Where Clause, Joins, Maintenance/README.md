## SQL: The Where Clause, Joins, Table Maintenance, Data Maintenance

### Familiar Terms

- Upper

The Upper(columnName) is a SQL command that is usually used in the WHERE part of the statement to change all the entries in that column to uppercase. This is handy if we are looking for matches where there may be a mix of upper and lower case letters.

For example, if we had the names 'John Smith' and 'john smith', if we just did 

```
...rest of statement
WHERE customerName = 'john smith'
```

It would only find the 'john smith' and not the 'John Smith'. Changing it all to uppercase in the result set solves this problem.

- And

A logical operation when we want to join conditions together. An AND statement means that both conditions on either side of the AND must evaluate to TRUE for the statement to be TRUE.

- Or

A logical operation when we want to join conditions together. An OR statement means that either of the conditions on either side of the OR must evaluate to TRUE for the statement to be TRUE.

- Like

An operation that works like a regular expression that allows us to find partial matches. The statement can start -

```
WHERE UPPER(customerName) LIKE 'JOHN%'
```

Which means we want to find all customer names that start with JOHN. The '%' at the end denotes that we don't care what comes after it.

- Is Null

A condition where we want a particular value in the row to be NULL.

- Not

Reverses the condition result that is directly to the right of the NOT.

- Distinct

Removes duplicate entries from the result set.

- Insert 

Insert a row into the table.

- Primary Key Constraint

The primary key constraint is that the primary key must exist in the table.

- Foreign Key Constraint

The foreign key constraint is that unless the value is NULL, it must have a valid value in there that matches to the primary key in the parent table.

- Referential Integrity Constraint

Contraints by references, this is where the Foreign Key links to the Primary Key, which defines the relationship between the primary key and foreign key.

- Table Alias

The method of refering to a table by a shorter form -

```
SELECT C.CUSTID, P.PURCHASE
FROM CUSTOMER C
INNER JOIN PURCHASE P
ON C.CUSTID = P.CUSTID
...
```

An alias allows us to refer to a table by a different name.

- Join

Joining 2 or more tables together through their constraints.

- Create Table

The SQL statement - 

```
CREATE TABLE <TABLE NAME> (
	COLUMN1 DATA_TYPE,
	COLUMN2 DATA_TYPE,
	...,
	PRIMARY KEY (COLUMN1)
	FOREIGN KEY (COLUMN_N) REFERENCES <OTHER_TABLE_NAME>
);
```

- Drop Table

Remove the table from the RDBMS with the SQL Command - DROP TABLE <TABLE NAME>;

It will permanently delete the table, unless there is still a child reference to it.

- Alter Table

Update a row in the table.

### Support Material Questions

These questions should be attempted before the tutorial material.

### Reading an ERD

**Hints** 
- The circle cardinality constraint represents MAY
- The line cardinality constraint represents MUST

- **1. From left to right, what does the ERD read?**

ONE EMPLOYEE MUST Work in ONE BRANCH 

- **From right to left, what does the ERD read?**

ONE BRANCH MAY employ MANY EMPLOYEE

### Cardinality Constraints

- **2. Provide the correct sentences for the ERD**

ONE MANAGER supervises MANY PROJECTS
ONE PROJECT is supervised by ONE MANAGER

**NOTE: If there is no cardinality constraint listed, we do not use the MAY/MUST the statement**

- **Provide the correct sentences for the ERD**

ONE MANAGER MAY supervise MANY PROJECTS
ONE PROJECT MUST be supervised by ONE MANAGER

- **3. Consider the diagram for the above, which statements are TRUE/FALSE

One manager could supervise two projects - TRUE - As the cardinality implies 0 or more.
A project may exist that has not been assigned to a mananager - FALSE - No because the relationship is that it MUST be supervised.
It is possible for a manager not to have any projects at the moment - TRUE - As the circle cardinality implies 0 or more
When the ERD is converted to a Relational Schema, the FK will be created in the PROJECT relation - TRUE (It is at the many end of the relationship)

- **4. Outline the Relational Schema based on the ERD from the previous question**

MANAGER (MgrID, FirstName, Surname)
	PRIMARY KEY (MgrID)

PROJECT (ProjectId, Title, Cost, MgrID)
	PRIMARY KEY (ProjectId)
	FOREIGN KEY (MgrID) REFERENCES MANAGER

- **5. When a Relational Schema has a PK:FK Relationship**

**Which table based on the Relational Schema is the Parent?**

The relation with the PK is said to be the **Parent Relation**

The MANAGER

**Which table based on the Relational Schema is the Child?**

The relation with the FK is said to be the **Child Relation**

The PROJECT

- **6. Are the following statements TRUE/FALSE**

**Note** 

The parent row cannot be deleted if a child row exists that has a matching FK:PK value.
The child row can be deleted at any time.

**Manager Ted Ball can be deleted?** : FALSE he cannot because PROJECT ProjectId 11 still references MgrId 2 (Ted's MgrId)

**Project 33 can be deleted?** : TRUE it can because it is a child row in the child table.

**Alice Davice can be deleted?** : TRUE she can because nothing in the PROJECT table references her MgrId

**All of Emma Dunns Projects can be deleted** : TRUE because they are child PROJECTS.

**Manager Ted Ball can be deleted only after Project 11 is deleted** : TRUE because after that, there are no references to his ID in PROJECT

- **7. Consider the ERD, are the following statements TRUE or FALSE**

**One MobilePhone must be owned by One Person** : FALSE, the cardinality constraint is a o, so it "MAY" be owned by ONE PERSON

**One Person may own Many MobilePhones** : TRUE

**When the M:1 relationship is converted to a relational schema, the FK will be created in the Person relation** : FALSE, it is on the ONE side.

**When tables are created, the Person table must be created first** : TRUE, because it does not have the FK.

**The Person entity is considered to be the Parent entity** : TRUE

- **8. Complete the Relational Schema based on the ERD above.**

PERSON (PersonId, FirstName, Surname)
	PRIMARY KEY (PersonId)

MOBILEPHONE (PhoneId, Model, Manufacturer, PersonId)
	PRIMARY KEY (PhoneId)
	FOREIGN KEY (PersonId) REFERENCES PERSON

- **9. A CONSULTANT table has 1 column named surname, with 1 row, with 'Jones', what will happen when the following statements are run**

**SELECT surname FROM CONSULTANT** : Will display the column with 'Jones'

**SELECT UPPER(surname) FROM CONSULTANT** : Will display the result set with 'JONES'

**SELECT LOWER(surname) FROM CONSULTANT** : Will display the result set with 'jones'

**SELECT surname FROM consultant WHERE surname = 'JONES'** : Will return nothing since the result set matches no row with 'JONES'.

**SELECT surname FROM consultant WHERE surname = 'jones'** : Will return nothing since the result set matches no row with 'jones'.

**SELECT UPPER(surname) FROM consultant WHERE surname = 'Jones'** : Will return JONES. (This one is a trick as the WHERE still gets 'Jones')

**SELECT UPPER(surname) FROM consultant WHERE surname = 'JONES'** : Will return nothing as surname is still 'Jones'

**SELECT LOWER(surname) FROM consultant WHERE surname = 'Jones'** : Will return 'jones' as the surname has not changed yet. The WHERE does not use the data in the result set.

**SELECT LOWER(surname) FROM consultant WHERE surname = 'jones'** : Will return nothing since surname is still 'Jones'

- **10. Consider the table, which customers are selected by each of the following statements?**

**SELECT Name FROM CUST WHERE TotOrders >= 16**

Winkus Oddpick, Herione Weasley

**SELECT Name FROM CUST WHERE (TotOrders = 5 AND TotOrders = 13)**

Nothing since orders cannot total 5 AND 13 for the same row.

**SELECT Name FROM CUST WHERE TotOrders = 0**

Dudley Dursley

**SELECT Name FROM CUST WHERE (TotOrders = 13 OR TotOrders = 5)**

Millicent Bagnold, Neville Longbottom, Viktor Krum, Agatha Chub

**SELECT Name FROM CUST WHERE (TotOrders >= 10 OR SalesYTD >= 1000)**

Edgar Bones, Millicent Bagnold, Winkus Oddpick, Cho Chang, Viktor Krum, Hermoine Weasley, Neville Longbottom

**SELECT Name FROM CUST WHERE (TotOrders >= 10 AND SalesYTD >= 1000)**

Millicent Bagnold, Winkus Oddpick, Hermoine Weasley.

- **11. Which customers are selected by each of the following SQL statements**

**SELECT Name from CUST WHERE Gender ='m'** : Winkus Oddpick

**SELECT Name FROM CUST WHERE Gender < > 'M' ** : 

(< > means !=) Millicent Bagnold, Winkus Oddpick, Cho Chang, Viktor Krum, Hermoine Weasley, Agatha Chub

**SELECT Name FROM CUST WHERE UPPER(Gender) = 'M'**

Edgar Bones, Winkus Oddpick, Argus Filch, Dudley Dursley, Neville Longbottom

**SELECT Name FROM CUST WHERE UPPER(Gender) = 'M' AND UPPER(Gender) = 'F'** : Nothing as the row cannot have both.

**SELECT Name FROM CUST WHERE LOWER(Gender) = 'm' OR LOWER(Gender) = 'f'**

Edgar Bones, Millicent Bagnold, Winkus Oddpick, Argus Filch, Cho CHang, Dudley Dursley, Hermoine Weasley, Neville Longbottom, Agatha Chub

**SELECT Name FROM CUST WHERE NOT Gender = 'M'**

Millicent Bagnold, Winkkus Oddpick, Cho Chang, Viktor Krum, Hermoine Weasley, Agatha Chub.

**SELECT Name FROM CUST WHERE NOT (Gender = 'M' OR Gender = 'F')**

Winkus Oddpick, Viktor Krum, Hermoine Weasley.

**SELECT Name FROM CUST WHERE Gender < > 'M' OR Gender < > 'F'**

Winkus Oddpick, Viktor Krum, Hermoine Weasley

**SELECT Name FROM CUST WHERE Gender < > 'M' OR Gender < > 'F'**

Everyone

**12. Which customers are selected by each of the following SQL statements?**

**SELECT Name FROM CUST WHERE TotOrders >= 10 AND Gender = 'F'** 

Millicent Bagnold, Cho Chang

**SELECT Name FROM CUST WHERE TotOrders < 10 OR SalesYTD < 1000**

Edgar Bones, Cho Chang, Argus Filch, Cho Chang, Dudley Dursley, Viktor Krum, Agatha Chub

**SELECT Name FROM CUST WHERE TotOrders > 20 AND TotOrders < 30**

Winkus Oddpick

**SELECT Name FROM CUST WHERE SalesYTD > 1000 AND NOT Gender = 'F';

Winkus Oddpick, Viktor Krum, Hermoine Weasley

**SELECT Name FROM CUST WHERE TotOrders > 20 OR TotOrders < 30;

Everyone

**SELECT Name FROM CUST
WHERE (Gender='M' AND TotOrders>=0) AND TotOrders<=10;**

Edgar Bones, Argus Filch, Dudley Durlsey

**SELECT Name FROM CUST
WHERE totorders <= 10 OR (salesYTD >= 1000 AND gender = 'F')**

Edgar Bones, Millicent Bagnold, Argus Filch, Cho Chang, Dudley Dursley, Viktor Krum, Agatha Chub

**SELECT Name FROM CUST
WHERE (totorders >= 10 AND totorders <= 30) OR (salesYTD >=500 AND salesYTD <= 999)**

Edgar Bones, Millicent Bagnold, Winkus Oddpick, Argus Filch, Cho Chang, Neville Longbottom, Agatha Chub

**SELECT Name FROM CUST
WHERE UPPER(GENDER) = 'M' OR (UPPER(GENDER) = 'F' AND salesYTD <= 1000) **

Edbar Bones, Winkus Oddpick, Argus Filch, Cho Chang, Dudley Dursley, Neville Longbottom, Agatha Chub

- **13. Which customers are selected by each of the following SQL statements?**

**SELECT Name FROM CUST WHERE Name LIKE 'A%';**

Argus Filch, Agatha Chub

**SELECT Name FROM CUST WHERE Name LIKE '%a%';**

Edgar Bones, Millicent Bagnold, Cho Chang, Hermoine Weasley, Agatha Chub (1,2,5,8,10)

**SELECT Name FROM CUST WHERE Upper(Name) LIKE '%A%';** (This can mean that AGATHA is valid)

Edgar Bones, Millicent Bagnold, Argus Filch, Cho Chang, Hermoine Weasley, Agatha Chub (1,2,4,5,8,10)

**SELECT Name FROM CUST
WHERE Upper(Name) LIKE '%LE%' OR Upper(Name) LIKE '%NG%'; **

Dudley Dursley, Cho Chang, Hermoine Weasley, Neville Longbottom

- **14. How many rows are selected by each of the following SQL statements?**

**SELECT Name FROM CUST WHERE QTY IS NULL;**

Emma

**SELECT Name FROM CUST WHERE QTY IS NOT NULL;**

Colin, Sophie, Madeleine

**SELECT Name FROM CUST WHERE SCORE > 0;**

Colin, Madeleine

**SELECT Name FROM CUST WHERE SCORE<= 0;**

Emma

**SELECT Name FROM CUST WHERE QTY + SCORE > 0;**

Colin, Madeleine (It appears that you cannot add NULL)

- **15. Now consider the following SQL statement?**

**What does the symbol P mean? Why is it used?**

The P is an table alias, it is used as a means to refer to the table P rather than writing PERSON all the time and having clashes if the attribute names are the same across tables that are joined.

**What is the purpose of the line ON P.DeptCode = D.DeptCode**

It links the primary key with the foreign key, allowing for the RDMBS so that it doesn't randomly merge it, maintaining Referential Integrity Constraint

**What output is generated by this statement? **

Ed Lau, John Brown, Parah Shah, Yin Min Tan

**Using the same tables as above, show the result set produced by this statement**

SELECT P.Name, P.Age, D.Title
FROM Person P
INNER JOIN Dept D
ON P.DeptCode = D.DeptCode
ORDER BY D.Title, P.Name 

John Brown 21 Admin
Parag Shah 42 IT
Ed Lau 34 Sales
Ying Min Tan Sales

**Using the same tables as above, show the result set produced by this statement**

SELECT P.Age, P.Name
FROM Person P
INNER JOIN Dept D
ON P.DeptCode = D.DeptCode
WHERE D.Suburb = 'Hawthorn'
ORDER BY 1 Desc 

42 Parah Shah
21 John Brown

**Using the same tables as above, show the result set produced by this statement**

SELECT P.Name, P.Age, D.Suburb
FROM Person P
INNER JOIN Dept D
ON P.DeptCode = D.DeptCode
WHERE P.Age < 30 OR (P.Age > 40 AND D.Suburb = 'Hawthorn')
ORDER BY 2 

Yin Min Tan 19 Kew
John Brown 21 Hawthorn
Parah Shah 42 Hawthorn

- **16. Consider the STUDENT table, what is the output of each of these SQL statements?**

**SELECT Name, Ass1+Ass2+Ass3 FROM STUDENT**

Name Ass1+Ass2+Ass3 (Column Name)
Jim 12
Jane 6
Helen 14

**SELECT Name, Ass1+Ass2+Ass3 FROM student ORDER BY 2 DESC**

Name Ass1+Ass2+Ass3(Column Name)
Helen 14
Jim 12
Jane 6 

**SELECT Name AS "Student Name", Ass1+Ass2+Ass3 "Total Score"
FROM student
ORDER BY 2;**

Student Name, Total Score
Jane 6
Jim 12
Helen 14

###

SQL STATEMENTS

DROP TABLE PERSON;
DROP TABLE DEPT;

CREATE TABLE DEPT (
	DeptCode VARCHAR(1),
	Title VARCHAR2(30),
	Suburb VARCHAR2(30),
	PRIMARY KEY (DeptCode)
);

CREATE TABLE PERSON (
	PersonId NUMBER(2),
	Name VARCHAR2(30),
	DeptCode VARCHAR(1),
	Age NUMBER,
	PRIMARY KEY (PersonId),
	FOREIGN KEY (DeptCode) REFERENCES DEPT
);

INSERT INTO DEPT (DeptCode, Title, Suburb) VALUES ('A', 'Admin', 'Hawthorn');
INSERT INTO DEPT (DeptCode, Title, Suburb) VALUES ('B', 'Sales', 'Kew');
INSERT INTO DEPT (DeptCode, Title, Suburb) VALUES ('C', 'IT', 'Hawthorn');

INSERT INTO PERSON (PersonId, Name, DeptCode, Age) VALUES (35, 'John Brown', 'A', 21);
INSERT INTO PERSON (PersonId, Name, DeptCode, Age) VALUES (11, 'Ed Lau', 'B', 34);
INSERT INTO PERSON (PersonId, Name, DeptCode, Age) VALUES (47, 'Parag Shah', 'C', 42);
INSERT INTO PERSON (PersonId, Name, DeptCode, Age) VALUES (20, 'Ying Min Tan', 'B', 19);

CREATE TABLE CONSULTANT (
	surname VARCHAR2(30),
	PRIMARY KEY (surname)
);

INSERT INTO CONSULTANT (surname) VALUES ('Jones');

DROP TABLE CUST;

CREATE TABLE CUST(
	CustNo NUMBER(3),
	Name VARCHAR2(30),
	TotOrders NUMBER(2),
	Gender VARCHAR2(1),
	SalesYTD NUMBER(6),
	PRIMARY KEY (CustNo)
);

INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (23, 'Edgar Bones', 10, 'M', 850);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (46, 'Millicent Bagnold', 13, 'F', 1100);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (17, 'Winkus Oddpick', 24, 'm', 2500);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (28, 'Argus Filch', 1, 'M', 500);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (134, 'Cho Chang', 10, 'F', 0);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (5, 'Dudley Dursley', 0, 'M', 0);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (24, 'Viktor Krum', 5, 'X', 2600);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (78, 'Hermoine Weasley', 31, 'f', 4300);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (53, 'Neville Longbottom', 13, 'M', 950);
INSERT INTO CUST (CustNo, Name, TotOrders, Gender, SalesYTD) VALUES (34, 'Agatha Chub', 5, 'F', 700);

DROP TABLE CUST;

CREATE TABLE CUST (
	IdNo NUMBER(2),
	Name VARCHAR(30),
	Qty NUMBER(2),
	Gender VARCHAR2(1),
	Score NUMBER(2),
	PRIMARY KEY (IdNo)
);

INSERT INTO CUST (IdNo, Name, Qty, Gender, Score) VALUES (23, 'Colin', 20, 'M', 5);
INSERT INTO CUST (IdNo, Name, Qty, Gender, Score) VALUES (46, 'Emma', NULL, 'F', -1);
INSERT INTO CUST (IdNo, Name, Qty, Gender, Score) VALUES (35, 'Sophie', 35, 'F', NULL);
INSERT INTO CUST (IdNo, Name, Qty, Gender, Score) VALUES (10, 'Madeleine', 10, 'F', 4);