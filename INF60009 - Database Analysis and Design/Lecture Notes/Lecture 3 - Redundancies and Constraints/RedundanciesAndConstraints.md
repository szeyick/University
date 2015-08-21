## Redundancies and Constraints

This lecture mainly goes through how poor database design and data entry can make a database not useful.

### Data Anomalies and Data Redundancies

A data anomaly is where **data inconsistency** is introduced into a database.

For example, this can mean that a student scores multiple grades for a single subject, or the deletion of a customer if they cancel an order.

This is usually attributed to poor database design.

Poorly designed schemas allow for **data redundancy**. This also means that there can be time where the same data is stored multiple times within the database, like customer names, emails and contact details.

### Data Redundancy

**INSERT TABLE**

In the above table, we can see that the Kew branch has its detailed stored twice, where the EBranch, BranchAddr, BranchPh are details that are stored twice within the table. The fact that it is stored twice and is the same data makes the second address **redundant**.

### Insertion Anomaly

**INSERT TABLE**

This occurs when **some data values cannot be inserted because the primary key value is not known**. So if we only know all the information regarding the table except for the primary key, then it causes an insertion anomaly as the row cannot be inserted on a NULL primary key. The way that the above table is designed does not allow a branch contact information to be inserted without knowing an employee number.

### Update Anomaly

**INSERT TABLE**

This occurs due to **data redundancy**. If we follow the above example, even though the Kew branch details are repeated, we can individually update a row, meaning we an change the **BranchPh** column which will make the same branch have different branch numbers. This will ensure that the data is **inconsistent**.

Having inconsistent data is one of the worst things you can have in a database.

### Deletion Anomaly

**INSERT TABLE**

This occurs when deleting some data causes data about something else to be also deleted. If we continue with the above table, if we delete *Fred Brown** from the table, we inadventently delete the information regarding the Box Hill branch. This means that we cannot delete a employee without deleting the branch information.

### Overcoming Anomalies and Redundancies

The best way to overcome anmalies and redundancies is to design the database tables properly. We need to create good relational models so that each entity only refers to a single type of information.

To create a process, we can follow these steps

### Better ERD Design

If we continue with the employee and branch example, if we split the employee and branch into 2 separate entities we would have the following ERD.

**Insert ERD Model HERE**

### Better Relational Schema

With our modified and improved ERD, we can construct a better relational schema. We can create the relationships between the entities correctly, where the Employee entity refers to the Branch entity for table lookups.

```
BRANCH(BranchCode, BranchArr, BranchPh)
EMPLOYEE(EmpNo, Name, BranchCode, Age, Foreign Key (BranchCode) References BRANCH);
```

### Why Anomalies and Redundancies Occur

Generally good database programmers will be able to avoid these sorts of mistakes. The problem becomes more obvious in spreadsheet or word processing applications where there is not really a concept of foreign key constraints. Within a spreadsheet program you will find numerous examples of data redundancies occuring especially if you try to put everything into the same spreadsheet page.

### Limitations of Spreadsheets

Spreadsheets are generally not great for filtering or viewing big pieces of data. They have some of the following issues.

- Limit on rows that can be stored.
- All rows are loaded, viewed and saved making it potentially slow for significantly large spreadsheets.
- Filtering and searching can be tedious.
- Hard to put restrictions on fields, also hard to validate.
- Lack of tracking changes, if someone deletes a row, history is usually not kept.
- Lack of concurrency controls.

### Advantages of Databases

Databases are designed to rectify some of the shortcomings of spreadsheets

- Good for large sets of data.
- Can be used for simple and complex datasets
- The database is usually hidden behind a front end that allows user entry and retrieval.

### Disadvantages of Databases

Databases whilst good at a lot of things, are not so good at everything

- Difficult to start up and use.
- Steep learning curve.

### Choosing a Primary Key

Choosing a good primary key is the basis of avoiding anomalies. They should have the following properties

- Be unique
- Cannot be NULL
- Are unlikely to change (static)

The reason that we want primary keys that do not change often is because if it changes, foreign keys that refer to this primary key will also need to be changed.

It is possible for a row to have multiple columns that contain suitable values for primary keys. These are called **candidate keys**. The designer simply chooses one of them as the primary key and continues on.

### Bad Types of Data To Store

- Age 

This will require frequent updates, and can be updated more than once. It would be better to store their birthdate, so then you can use the system functions to figure out their age.

- Addresses

Storing an entire address would require the declaration of a very large varchar(200). By storing an entire address in a single line, it is difficult to filter, search and aggregate. For example, it would be hard to search for someone who lives in the same street and same suburb if you put the entire address in a single line.

```
Address varchar2(200)
INSERT INTO ADDRESSBOOK (Address) VALUES ('17 High St Key Vic 3102 Australia');
```

The better solution here would be to store each part of an address, into its own variable.

```
Street varchar2(100)
Suburb varchar2(50)
State varchar2(50)
Postcode varchar2(10)
Country varchar2(50)
```

- Numeric or Text Columns

Should a column that stores numbers store it in numeric or alphanumeric. Numeric values are better for storage as you do not need to use '' symbols since it may contain letters. It also requires less storage space than characters.

Joining strings in an numeric datattype may require using string functions.

Also sorting numbers that are stored as strings would not neccessarily return the required order. It is done usually by comparing the characters at a time like in a fixed sized array.

If we did want to store numbers as strings, the idea would be to append 0's to the front, so when they are sorted they will appear in "numeric" order.

```
0001
0002
0019
0119
0310
1700
2199
```

However this can also lead to problems dealing with really large numbers and also some spreadsheets may automatically convert the numbers.

### Composite Primary Key

There may be instances where no single attribute (column) can be used as a primary key. This may be because the primary key column maybe built up of acronyms, where 2 words have the same acronym.

To get around this we can combine multiple attributes (columns) to create a **composite primary key**.

In a relational schema, we can denote a composite primary key by underlining both attributes in the definition. It denotes **that it is ONE primary key, not multiple.**

A general rule is that a table (relation) can only have **ONE primary key.**

### SELECT ALL

The word **ALL** after the select statement is an optional word and is used in conjunction with the * symbol. 

The following snippet is identical meaning.

```
SELECT ALL * FROM ____
SELECT * FROM ____
```

### Data Types

- VARCHAR2(n) - 

A n length character string that provides no padding for spaces (1 leading byte for null, add 1 leading bye if not null for length indicator)

- CHAR(n) - 

A n fixed length character string. It is not chopped like the VARCHAR, meaning that if n was 100 and you had 50 characters in the string, then it will still be considered as length 100. 

- NUMBER(o,d) -

Used to store numeric data, where o is the number of digits and d is the number of digits after the decimal point.

- DATE

Stores both the date and time.

### ALTER TABLE

This statement is used to modify the structure of a table. Using this ALTER command will not result in any lost data unless you remove a column.

The syntax is as follows -

```
ALTER TABLE <tableName>
  ADD <columnName> <type> ...
  MODIFY <columnName> <type> ...
  DROP < columnName> ...
  
  eg.
  ALTER TABLE EMPLOYEE
    ADD (MARRIED_STATUS VARCHAR2(1)) NOT NULL;
```

### Listing All Tables

To list all the tables that you have currently created within SQL you can use the following -

```
SELECT table_name FROM user_tables;
OR
SELECT table_name FROM tabs;
OR
SHOW TABLES;
```

### DELETE Command

To remove a single row from the table we can use the following format

```
DELETE <table-name>
WHERE <condition>

e.g. DELETE student WHERE UPPER(fees_paid)='N';
```

Care needs to be taken here as not using a WHERE condition with the DELETE will result in all the rows in the table being deleted.

### UPDATE Command

The update command allows you to update a row in a table. It follows the format -

```
UPDATE <table-name>
SET <column-name> = <value>, <column-name2> = <value2> ...
WHERE <condition>

e.g. 
UPDATE student
SET Fees_Paid = 'Y'
WHERE StuID = 1122334;
```

Again like the delete command, if you do not include a WHERE clause, it will update all the columns in the table with the same value.

### Multiple Relationships Between Two Entities

It is possible to show different relationships between to entities.

This is only possible when the both relations are a M:1 relationship and it is a fixed number of relations.

** COPY IMAGE HERE**

In the instaces where there are multiple relations between entities, we need to treat them individually, meaning that we need to write both the relationships out. In our example above we get -

ONE CAR MUST be purchased from ONE DEALEER
ONE DEALER MAY sell MANY CARS
ONE CAR MAY be serviced by ONE DEALER
ONE DEALER MAY service MANY cars

This is applicable in the ERD which we can use to convert it into a relational model.

To continue with the example from above, we will create **2 foreign keys**

So far in our entities, we have created foreign keys that have the same name as the primary key in the table that it references. If we have 2 foreign keys that reference a single table, this will not be possible. In our example above we will have -

```
CREATE TABLE CAR (
  RegNo VARCHAR2(10) Primary Key,
  Model VARCHAR2(10),
  Colour VARCHAR2(10),
  DealerName VARCHAR2(20),
  DealerName VARCHAR2(20),
  Foreign Key (DealerName) references DEALER,
  Foreign Key (DealerName) references DEALER
);
```

SQL will not allow two attributes to have the same name. The solution for this will be to simply change the name of the attribute since it will still reference the same DEALER table.

```
CREATE TABLE CAR (
  RegNo VARCHAR2(10) Primary Key,
  Model VARCHAR2(10),
  Colour VARCHAR2(10),
  PurchaseDealerName VARCHAR2(20),
  ServiceDealerName VARCHAR2(20),
  Foreign Key (PurchaseDealerName) references DEALER,
  Foreign Key (ServiceDealerName) references DEALER
);
```

This works because the important thing here is the value and not the name of the attribute, so long as they are the same they will be considered as a match.

If you want to refer to a table with different foreign keys, the idea here will be to use **Aliases** and treat them like 2 different tables.
