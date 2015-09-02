## Sample Test

- **Consider the following ER Diagram**

**INSERT IMAGE**

**When converted to a relational schema, the BLOB relation will contain how many columns?**

The blob entity has 2 attributes, the **BlobNo** and **BlobName**. As it is the parent of the THING entity, it should not have a reference to its child. The relational schema will be written as -

```
BLOB(BlobNo, BlobName)
```

Where the BlobNo is underlined as it is the primary key. There will be 2 columns in the BLOB relation.

- **Consider the following ER Diagram**

**INSERT IMAGE**

**When converted to a relational schema, what is the FK (foreign key) of THING?**

As the entity THING is at the ONE end of the MANY:ONE, it means that it is the parent. The parent will not have a FK as it does not need to know anything about the OTHER entity. The answer here will be, **no FK**

**What is the FK (foreign key) of OTHER?**

The entity OTHER is at the MANY end of the MANY:ONE relationship. This means that it is the child of this relationship. As the child, it needs a reference to the parent entity which is THING. 

The THING entity has 2 attributes that are used as the primary key meaning that it is a composite key. The FK of the OTHER entity will be PartA and PartB from THING.

- **Consider the following ER Diagram**

**INSERT IMAGE**

**When converted to a relational schema, how many foreign keys are there in total?**

The PLACE entity is the parent of PERSON, and has no other relations. This means that the PLACE entity has no Foreign Key.

The PERSON entity is the child of the PLACE entity, it has a single foreign key to the PERSON entity (State, City).

The PERSON entity is the child of the GROUPING entity, it has a single foreign key to the PERSON entity (Gid). 

The GROUPING entity is the parent of PERSON, and has no other relations. This means that the GROUPING entity has no foreign Key.

In total, **there will be 2 foreign keys**.

**Which relation has the most Foreign Keys?**

The PERSON entity has the most foreign keys. It is the only entity with foreign keys.

- **Consider the following ER Diagram**

**INSERT IMAGE**

**When converted to a relational schema, how many tables will have no foreign keys?**

The FUNCTION entity is the child of both the EMPLOYEE and DEPARTMENT entities therefore will have foreign keys.

The DEPARTMENT entity is the parent of FUNCTION and has no other relations, therefore has no foreign keys.

The EMPLOYEE entity is the parent of both FUNCTION and STATION and has no other relations, therefore has no foreign keys.

The STATION entity is the child of EMPLOYEE, and will therefore have at least 1 foreign keys.

So in total, there will be 2 tables with no foreign keys.

**Which table will have the most attributes?**

The FUNCTION entity has 2 attributes, and 2 foreign keys so will have 2 more attributes - 4.

The DEPARTMENT entity has 2 entities.

The EMPLOYEE entity has 3 attributes, and no foreign keys.

The STATION entity has 2 attributes and being the child of EMPLOYEE will have an additional attribute - 3

The table with the most attributes will be the FUNCTION entity.

- **Consider the following tables and data**

**INSERT IMAGE**

**How many rows are selected from the following SQL statements?**

**Select * from customer where location = 'Kew'**

3 rows will be returned displaying the entire row.

**Select * from customer where location = 'HAW'**

No rows will be returned as there are no values matching 'HAW'. There also is no modifier to uppercase the values in the rows either.

**Select * from customer where location = 'Haw'**

4 rows will be returned displaying the entire row where the location matches 'Haw'.

**Select * from customer where NOT (location = 'Haw')**

5 rows will be returned displaying all the locations that do not match Haw.

**Select * from customer where NOT (location = 'Haw' OR location = 'Kew')**

2 row will be returned only displaying the row that doesn't match 'Haw' or 'Kew'.

**Select * from customer where NOT location = 'Kew' OR location = 'Richmond'**

6 rows will be returned, displaying the locations that are NOT Kew or Richmond.

**Select * from customer where location = 'Kew' and sales < 100**

2 rows will be returned, as the NULL sales for Kew should not be returned.

**Select * from customer where location = 'Kew' OR sales < 200**

4 rows will be returned, including the NULL.

**Select * from customer where NOT (location = 'Kew' OR sales < 200)**

4 rows will be returned, no idea as to why 200 will be returned for < 200

**Select * from customer where NOT location = 'Kew' OR sales < 200**

Should return all rows.

**Select * from customer where sales < 100 OR sales > 400 AND location <> 'Kew'**

The <> means !=.

5 rows will be returned.

**Select * from customer where (sales < 100 OR sales > 400) AND location != 'Kew'**

This should return 3 rows. 

**Select * from customer where typecode = 1 OR typecode = 3**

6 rows will be selected. (including the NULL)

**Select distinct location from customer**

3 rows, all the unique locations.

**Select unique location from customer**

3 rows, all the unique locations, the same as the above.

**Select c.custname, t.title from customer c inner join type t on c.typecode = t.typecode**

This will display all the rows, but only display the customer name, and their type codes.

**Select c.custname, t.title from customer c inner join type t on c.typecode = t.typecode where sales < 100**

3 rows, it will filter all the rows based on the criteria where sales < 100. It will only display the customer name and type.

**Select c.custname, t.title from customer c inner join type t on c.typecode = t.typecode where sales > 100 OR sales < 400 and t.typecode = 1**

5 rows will be returned

**Select c.custname, t.title, g.description from customer c inner join type t on c.typecode = t.typecode inner join grade g on t.gradeid = g.gradeid**

Displays all the rows with the selected 3 columns.

- **Write a single SQL statement to list all the customer names and sales amounts in descending sales amount sequence**

```
SELECT CUSTNAME, SALES
FROM customer
ORDER BY SALES DESC;
```

- **Write a single SQL statement to list all customer numbers, customer names and locations in ascending location sequence. If two or more rows have the same location, list those rows in ascending customer name sequence**

```
SELECT CUSTNO, CUSTNAME, LOCATION
FROM customer
ORDER BY LOCATION ASC, CUSTNAME ASC;
```

- **Write a single SQL statement to list all the locations in the customer table. Each location must only appear once in the list**

```
SELECT DISTINCT LOCATION
FROM customer;
```

- **Write a single SQL statement to list the names of customers who have a sales value in the following range 25 - 100 OR 500 - 900**

```
SELECT CUSTNAME
FROM customer
WHERE (SALES >= 25 AND SALES <= 100)
OR (SALES >= 500 AND SALES <= 900)
```

- **Write a single SQL statement to list the names of customers have the letter N in their name. Allow for both upper case and lower case characters**

```
SELECT CUSTNAME
FROM customer
WHERE UPPER(CUSTNAME) LIKE '%N%'
OR UPPER(CUSTNAME) LIKE 'N%'
OR UPPER(CUSTNAME) LIKE '%N'
```

- **Write a single SQL statement to list the customer name of any customer that has a null sales amount value**

```
SELECT CUSTNAME
FROM customer
WHERE SALES IS NULL
```

- **Write a single SQL statement to list each customer name and the type name that he/she has.**

```
SELECT C.CUSTNAME, T.TITLE
FROM customer C
INNER JOIN TYPE T
ON C.TYPECODE = T.TYPECODE
```

- **Write a single SQL statement to list each customer name only if they have a typename that contains the word 'Under'**

```
SELECT C.CUSTNAME, T.TYPECODE, T.TITLE
FROM customer C
INNER JOIN TYPE T
ON C.TYPECODE = T.TYPECODE
WHERE UPPER(T.TITLE) LIKE 'UNDER%';
```

- **Write a single SQL statement to list the customer name of any customer that belongs to a TYPE where the TYPE belongs to a grade that contains the word children in its description.**

```
SELECT C.CUSTNAME, G.DESCRIPTION
FROM customer C
INNER JOIN TYPE T
ON C.TYPECODE = T.TYPECODE
INNER JOIN GRADE G
ON T.GRADEID = G.GRADEID
WHERE UPPER(G.DESCRIPTION) LIKE '%CHILDREN%';
```

- **Insert a new customer. Customer 35, using your name, valid type name and sales to 0 and location to Haw**

```
INSERT INTO customer (CUSTNO, CUSTNAME, TYPECODE, SALES, LOCATION) VALUES (35, 'Sze', 3, 0, 'Haw');
```

- **Update customer. Customer 35, set sales amount to 120**

```
UPDATE customer
SET SALES = 120
WHERE CUSTNO = 35;
```

- **Update customer. Customer 35, set the sales amount to be 50% greater than the current value**

```
UPDATE customer
SET SALES = SALES * 1.5
WHERE CUSTID = 35;
```

- **Delete all customers that belong to a type that has the letter S anywhere in the type title**

```
DELETE C.customer
FROM customer C
INNER JOIN TYPE T
ON C.TYPECODE = T.TYPECODE
WHERE UPPER(T.Title) LIKE '%S';

OR

DELETE
FROM customer
WHERE IN (	SELECT DISTINCT C.TYPECODE, T.TITLE
                FROM TYPE T
                INNER JOIN CUSTOMER C
                ON C.TYPECODE = T.TYPECODE
                WHERE UPPER(T.TITLE) LIKE '%S'
              );
```

- **5% of sales made by each customer is to be paid to the sale staff as commission. Write a query that displays the customer no, name, sales and the 5% of sales**

```
SELECT CUSTNO, CUSTNAME, SALES, (SALES * 0.05) AS "COMMISSION"
FROM customer;
```

- **Write the relational scehma based on the following ERD. Include all primary and foreign keys**

```
PLACE (State, City, Population) // Underline both State, City as the primary keys. No foreign keys.
PERSON (Pid, PName, State, City, Gid) // Underline Pid as the primary key, State, City and Gid are the foreign keys.
GROUPING(Gid, GName) // Underline the Gid as the primary key. No foreign keys.
```