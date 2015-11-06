## String Functions, Dual Table, Transactions, Data Warehousing

### Combining Text/Strings

We can concatenate strings by doing < COLUMN NAME > || ' ' || < COLUMN NAME 2 > and we can repeat this for as many as we want. This will concatenate the output result as well.

We can also use a substring function to only take a partial of a string ``` SELECT SUBSTR(<COLUMN NAME>, startIndex, endIndex) ```

We can combine the piping and substring into a single call that will shorten the output ```SELECT actorno, SUBSTR(firstname, 1,1) || ' ' || surname FROM ACTOR ```

The startIndex for the start of a string appears to be 1 and it includes white space as a character.

There is also the **INSTR** which returns the position of a substring in a string.

```
INSTR(< STR1 >, < STR2 >, < START POS >, < Nth APPEARANCE OF STRING 2 >)

The < START POS > and < Nth APPEARANCE OF STRING 2 > are optional.
```

We also have the **CAST** function that casts a datatype to another data type

```
CAST ('123' AS NUMBER)
CAST ('22-Aug-2015' AS DATE) = 22/AUG/2015
CAST (456 AS VARCHAR2) = '456'
```

### Dummy Table

The SELECT statement is the normal way that we return and display data. However it means that we must always select a table.

So apparently we can do things like this - ```SELECT 1 + 1 ``` although this is not possible in Oracle because we are always forced to select a table along with the SELECT.

We have a **DUAL TABLE** for this purpose. It just has 1 column called DUMMY and one row. The structure of the table is unimportant just that it is a **DUAL** table and called as such.

The point of it is to allow us just to call random SQL functions like SUBSTR, 1+1, INSTR, || ' ' || and CAST.

### Database Transactions

It is something that is used to ensure that we keep databases consistent. Because sometimes to keep data consistency, we may need to Insert into one table and Update another. For example we may want to Insert a new BANK EVENT row to log a transaction made, but then also UPDATE the customer balance in the CUSTOMER table.

To do this we would normally have to write to separate statements.

Or we can use **Transactions**. These are just multiple SQL statements that make up a "logical unit of work" or a sequence.

A **successful transaction** guarantees that ALL statements within the transaction are completed and data is committed and set. Unsuccessful means the data was not saved and none of the operations in the transaction were completed.

The DBMS ensures that this is the only two things that happen, success or fail. This means that if a transaction only half executes it automatically fails that the DBMS will not update the values.

```
START TRANSACTION
	INSERT INTO BANKEVENT (EventId, EventDateTime, CustId, Event, Amount) VALUES (7061, 4 June 2pm, 101, Withdraw, 50);
	UPDATE CUSTOMER SET BALANCE = BALANCE - 50;
END TRANSACTION
```

If there was a check constraint on the BALANCE to ensure that it has more than 0, this will be raised by the DBMS and the transaction will not successfully finish.

### ACID Properties (Atomicity, Consistency, Isolation, Durability)

These are the terms to describe Transactions

- **Atomicity**

A transaction is **indivisible**, meaning that it is fully completed or not at all. Success means COMMIT, Unsuccess means ROLLBACK

- **Consistency**

It goes from one consistent state to another, executing all statements one after another or none at all. Does not only do half a transaction.

- **Isolation**

Works in isolation by itself, and do not interact with other transactions.

- **Durability**

upon success, the data is committed. It is final and never be lost.

### Business Intelligence and Data

Different people in an organisation may use data in a database differently. 

Managment may want to know the high level details of information within a databas, whereas the staff may want to know the more low level.

An **OLTP** or **Online Transaction Processing System** is something that is used in businesses to manage tasks such as -

- **Sales Systems**
- **Purchasing**
- **Library Loans**
- **Banking**

It is just a transaction processing system. Information from these systems drive business and operational decisions.

For high level information, you'd typically want to run aggregate queries (i.e. How many of these items were sold, Average time of users doing something etc)

**Transaction Processing Systems** are usually designed for Operational users (people who use it everyday). Most queries are made based off the data within this system.

Data in these systems are based off ERD and Relational Schemas and stored in many tables to ensure minimal data redundancy, making it slow to access aggregated information since you need to look across lots of tables.

As a result **business analysts and people who want a higher level view of data** need their data stored differently.

### Data Warehousing

Used for a higher level view of the database used by managers and business analysts. Data from a transaction processing system is sent to the data warehouse at COB everyday.

**Data Warehouse Properties**

- **Data is stored across a few tables**
- **Tables may store redundant data**
- **No verification is needed as the data has already been verified by the transaction processing system.**
- **Foreign Key constraints are removed**
- **Only relevant data is stored in an aggregated format**.

Data in a data warehouse is usually used to look at forecasting and prediction as they can see data over a long period of time. Data in a TPS is more for day to day activites.

**Basically running complex queries to aggregate data on a TPS is slow**

**Not all data from a TPS is transferred over, only the data that is seen as relevant to higher level (aggregate, average, sum) should be transferred. Thus only the summarised data and not a copy of the entire database**.

### OLAP Functions (Online Analytical Processing)

This is how we group data and remove the finer grained details from the TPS to prepare it for storage in a data warehouse.