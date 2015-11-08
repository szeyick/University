## String Functions, DUAL, Aggregate OLAP Functions and Data Warehousing

### Tutorial Questions

- **1. Consider the following statement?**

```SELECT 100 / 200 FROM DUAL```

**a. What is DUAL?**

Dual is the default table that is defined in SQL. It allows you to use the calculation functions in SQL without creating a table since you need a table whenever you use the SELECT statement.

**b. Why is it used?**

It is used because you want to do some calculations but do not want to create an entire table.

**c. What is displayed?**

The output will be displayed of the sum of (100 / 200) with that also as the table column header.

```
100 / 200 // Column Heading
--------- 
0.5 // Cell Value
```

- **2. Describe a business process that requires more than one row in a table to be updated and/or inserted and/or deleted?**

If you had a table that contained a due payment date, the date that it would be paid would be entered into the value along with the value that was paid. OR transferring money from one account to another.

- **3. What is a database transaction?**

A database transaction is a sequence of SQL queries that are run together in a batch. These commands need to be all run and returned correctly for it to be considered as as successful transaction. Should any of the SQL statements in the transaction fail, the entire transaction has failed. The reason for this is to allow updating of multiple rows in multiple tables to be synchronised and to also not accidentally update parts of the data when it should all be updated. 

This keeps the data consistent.

- **4. Why is a transaction referred to as a 'logical unit of work'?**

It is referred to as such because it is considered as a set of instructions that are bounded together that achieve a process. For example, if you had to write a set of commands to process a customer order, you may wish to, update inventory, get the customer details for shipping, billing address and update the postage state. These commands are considered a logical unit of work because combined they contribute to the task of creating a purchase and shipping order.

- **5. Database transactions must be atomic? What does atomic mean?**

Atomic means that it needs to be all completed or not at all. There is no inbetween state for atomic, as the transaction is needed to be completed meaning all steps to be completed or none of the steps at all.

- **6. What could happen if a transaction was not atomic?**

If a transaction was not atomic you could get into a situation where the data was inconsistent or incomplete. For example if you were updating a customer information where you needed to change the details in the customerInfo table and the shipping information table, if the transaction was not atomic, you could run into a situation where you updated one table, the system threw an error on the second and returned. 

You would then have a table where the customer information would be inconsistent as one table tells you one part of the information and the other the other part. This leads to data inconsistency, which is one of the big no no's when it comes to databases.

- **7. What happens to a transaction when all statements in a transaction are succesfully completed?**

The transaction does not throw an error and the changes in the statements are written permanently into the database. It returns a success code.

- **8. What happens to a transaction when at least one statement in a transaction is not successfully completed?**

The transaction is marked as unsuccessful, the changes that were made in the prior statements would be undone leaving the database in the state it was in before the transaction was run.

- **9. Assume that this statement has been executed.**

```
INSERT INTO CUSTOMER (CustId, Date, Balance) VALUES ('C1, '1-June', 0);

Then sometime later:

INSERT INTO BANKTRANS (TransType, Date, CustId, Amt) VALUES ('Deposit', '3-June', 'C1', 1000);
UPDATE CUSTOMER 
SET Balance = Balance = 1000
WHERE CustId = 'C1';
```

**a. Why is it important that both of the above statements are executed?**

The importance of the commands being run is that the first statement is to add the customer into the CUSTOMER database table. The second statement is to record that a transaction has been made by that customer. There would be an assumption that the BANKTRANS table is linked ot the CUSTOMER table through the CustId FK:PK relationship.

If the first statement was not run, then the BANKTRANS statement would throw a FK:PK violation since it would not be able to find the CustId = 'C1' in the CUSTOMER table.

Additionally, the update would also not update.

Furthermore, the Amount set into the BANKTRANS is the transaction that the customer made, because these values exist in 2 different tables they would need to be reflected as such, otherwise the transaction made for 1000, would not show up in the CUSTOMER balance leading to data inconsistency.

**b. What are the repercussions if the first statement is executed but the second statement is not?**

Data inconsistency, the amount added through the BANKTRANS is not reflected in the CUSTOMER table. In real world situations, this would that you deposited 1000 dollars into your account, the bank has recorded the transaction, but when you go to check your customer details the amount does not appear.

- **10. How do the database needs of a Business Analyst differ form the database needs of the same organisations Operational Staff?**

A database analyst's responsibility is to look for trends, commonalities through the aggregation of data. Whereas the operational staff who use the database system daily, their needs for the database is to just enter the data in, enter transactions, update inventory, delete stock lines and all that stuff.

A business analyst is to use that collected data to make business decisions. Thus they are not overly concerned with the fact that John Smith from 108 Smith Street made a transaction. They are more concerned with the aggregated data, as to how many people made transactions, which products are selling the most, and where does most of the business come from. They require a higher level of the data compared to the operational staff.

- **11. List 3 different corporate software applications that would use a database to store data**

A payroll system, a employee contact list, service desk problem logging, GAIA, ClearQuest, Transaction Billing.

- **12. What is a TPS system or application? How is a database involved?**

A TPS system or "Transaction Processing System" is an application that is used for processing transactions. It is used as a means of tracking and logging transactions that are made so thing such as sales. A database is involved because it uses it as a backend of the system, the transaction data from each sale, is logged into the database and stored for future reference.

- **13. Why are corporate software applications and databases often referred to as hetrogeneous?**

This means that they are "diverse". The reason that they are as such because most organisations are incredibly large and have many different departments. Each department would have a separate use for their own database, so the sales department might have a differnet use for a customer database than that of the marketing department, along with the billing department. 

Also because they have different uses, they would have additional tables, data points and all that within their own databases. Because this is the case it makes it very infeasible for a single corporation to have a single database because the data would be very clustered and contain lots of irrelevant data for individual departments.

- **14. List 2 reasons why an organisation would want to copy data from their operational TPS database to a Data Warehouse?**

The first reason would be that it would not create an interruption to normal business operations. The process of copying a live database to a data warehouse is often time consuming, meaning that whilst it was being done noone could really use the operational TPS database. This is not practical in real world situations since the business may need to stop functioning for a while whilst the data was being migrated.

The second reason would be that by copying data from a TPS to a DW, would be that the data being transferred can be cleaned up, filtered and aggregated before being copied into the data warehouse allowing business analysts to quickly make sense of the data. They could also then do whatever they needed to the data without affecting business operations.

Anothe reason would be that you may want to copy from multiple TPS databases and aggregate the data from multiple sources rather than running individual. Queries coming from multiple tables are slower than that of the ones that come from a single table.

- **15. Assume that the table EMP has the following data**

```
EmpNo	Surname		Firstname
1		Smith		Fred
2		Jay			Emma
3		Phelps		Mark
```

Consider the statement -

```
SELECT Firstname || ' ' || Surname
FROM EMPLOYEE;
```

**What is displayed?**

For each row, it will select the value in the Firstname column and Surname column and concatenate them together with a space inbetween.

```
Firstname || ' ' || Surname
---------------------------
Fred Smith
Emma Jay
Mark Phelps
```

- **16. Consider the following statement**

```
SELECT Firstname || ' ' || Surname
FROM EMPLOYEE
WHERE INSTR(Firstname, 'm') > 0
```

**What is displayed?**

The string command INSTR is used to find "Instance of String". The other form that it could be used would be ```INSTR(String, StringToMatch, NumberOfMatches)``` which should return TRUE/FALSE.

However in this instance, INSTR(String, StringToMatch) will return the number of matches that it has. For this example, we are looking at each row, and in the Firstname column if the value has more than 0 'm' then we will add the concatenated Firstname + Surname to the result set.

```
Firstname || ' ' || Surname
Emma Jay
```
It should not match Fred Smith because there are no m's in Fred, and should also not match Mark since his name contains a M not m.

- **17. Consider the following statement**

```
SELECT Firstname || ' ' Surname
FROM EMPLOYEE
WHERE INSTR(UPPER(Firstname), 'M') > 0
```

**What is displayed?**

This will for each row, take the firstname, change all the characters into capitals then evalauate if they contain the letter M.

```
Firstname || ' ' || Surname
Emma Jay
Mark Phelps
```

I do not think it will output EMMA JAY, MARK PHELPS since that was not specified in the SELECT statement at the top. It was only used as a instruction for the WHERE.

- **18. Draw an ERD based on the narrative**

