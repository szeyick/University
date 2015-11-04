## Entity Relationship Diagrams

This week there are no lecture support material so it just goes straight to the tutorial questions.

### Tutorial Questions

- **1. How man columns will be in the result set when the following statement is executed?**

SELECT M.title, M.mvyear, A.surname || '' || a.firstName As "Actor Name"
FROM movie m
INNER JOIN casting c
ON c.movieno = m.movieno
INNER JOIN actor a
ON c.actorno = actorno
ORDER BY 1,3

There will be 3 columns displayed in the result set - title, mvyear and Actor Name.

- **2. What is a view?**

A view is a SQL command that allows you to create an entire query and save it as a view so that it can be referenced later without having to write out the query again. 

It will only store the query and not the data, meaning that each time the query is run it will grab the data again.

```
CREATE VIEW EMPAVG AS
	SELECT d.deptname, Avg(e.salary) "Average Salary"
	FROM EMPLOYEE e,
	INNER JOIN DEPARTMENT d
	ON e.deptid = d.deptid
	GROUP BY d.deptname
	HAVING Avg(e.salary) >= 50000
```

- **3. What occurs when the following statement is executed?**

CREATE VIEW mview AS
SELECT M.title, M.mvyear, A.surname || ' ' || a.firstname AS "Actor Name"
FROM movie m
INNER JOIN casting c
ON c.movieno = m.movieno
INNER JOIN actor a
ON c.actorno = a.actorno
ORDER BY 1,3

It creates a view called mview with that query.

Therefore when the command SELECT * FROM mview will provide the same output as that query above.

It selects all the columns, and combines it all and orders the result first by the title, then if the title is the same, then it'll order those ones by the third column

- **4. How many columns will be in the result set when the following statement is executed?**

SELECT * FROM mview;

It will display 3 columns, it just runs the mview query from the above question - title, mvyear, Actor Name

- **5. What will occur when this statement is executed?**

SELECT * FROM mview
WHERE mvyear = 1998;

It will run the statement to get movie information but in the result set will only show the results where the row's mvyear is 1998.

- **6. List 3 reasons why views are useful?**

1. You can create shortcuts to complex queries without having to type the queries over and over again.
2. Provide security as you can provide read/write access to the view
3. No need to learn SQL all you need to do is run the view.

- **7. Does a view store any data?**

No the view does not store any data. It is simply a saved template query, and it will fetch the data each time.

- **8. What statement is used to delete the view named mview**

DROP VIEW mview

- **9. Can you insert rows by using a view name instead of a table name?**

Yes it is possible to do but it is not recommended. The reason being that the view may containing joins to other tables so knowing all the entries to store into is difficult. Also if you don't provide the right number of attributes to set to values it will not work.

- **10. Can you delete rows using a view name**

Yes it is possible to do, but not sure what the effect of it would be.

- **11. Draw an ERD diagram, relational schema and sample data based on the business narrative?**

Entities -

OWNER
RENTER
HOUSE

Attributes -

OWNER - name, phoneNum
HOUSE - address, bedroomNo, bathroomNo
RENTER - name, phoneNum

Unassigned - percentageOwned

ERD M:M-

OWNER >--OWNS--<HOUSE

HOUSE >--RENTED BY--< RENTER

ERD Expansion

OWNER ----< HOUSE OWNER >---- HOUSE

HOUSE OWNER - percentageOwned (associative entity)

HOUSE ----< RENTING RECROD >---- RENTER

RENTING OWNER - Intersection entity 

Relational Schema -

OWNER (ownerName, ownerPhoneNo)
	PK (ownerName)

HOUSE (address, bedRoomNo, bathroomNo)
	PK (address)

RENTER (renterName, renterMobileNo)
	PK (renterName)

OWNS (ownerName, address, percentageOwned)
	PK (ownerName, address)
	FK1 (ownerName) REFERENCES OWNER
	FK2 (address) REFERENCES HOUSE

RENT INFO (address, renterName)
	PK (address, renterName)
	FK1 (address) REFERENCES HOUSE
	FK2 (renterName) REFERENCES RENTER

- **12. Build an ERD to solve the problem described**

Entities :

RECIPE
INGREDIENTS
CUSTOMER
DOWNLOADS
SUPPLIERS
AUTHORS

Attributes :

RECIPE - id, recipeName
INGREDIENTS - name
CUSTOMER - name, email, custid
SUPPLIER - name, address
AUTHORS - name

Outlying Attributes :

Download Date

Initial ERD -

AUTHOR>---writes---<RECIPE
RECIPE>---composed---<INGREDIENTS
INGREDIENTS>---bought from---<SUPPLIER
CUSTOMER>---downloads---<RECIPE

Weak Entities -

AUTHOR ---< SUBMITS >--- RECIPE
CUSTOMER ---< DOWNLOADS >--- RECIPE
INGREDIENTS ---< PURCHASED >--- SUPPLIER
RECIPE ---< COMPOSED >--- INGREDIENTS

Relational Schema - 

CUSTOMER (custID, custName, custNumber)
	PK (custID)

RECIPE (recipeID, recipeName)
	PK (recipeID)

INGREDIENTS (ingredientName)
	PK (ingredientName)

SUPPLIER (supplierName)
	PK (supplierName)

AUTHOR (authorName)
	PK (authorName)

DOWNLOADS (downloadDate, custID, recipeID)
	PK (custID, recipeID)
	FK1 (custID) REFERENCES CUSTOMER
	FK2 (recipeID) REFERENCES RECIPE

PURCHASED (ingredientName, supplierName)
	PK (ingredientName, supplierName)
	FK1 (ingredientName) REFERENCES INGREDIENTS
	FK2 (supplierName) REFERENCES SUPPLIER

COMPOSED (recipeID, ingredientName)
	PK (recipeID, ingredientName)
	FK1 (recipeID) REFERENCES RECIPE
	FK2 (ingredientName) REFERENCES INGREDIENTS

SUBMITTED (authorName, recipeID)
	PK (authorName, recipeID)
	FK1 (authorName) REFERENCES AUTHOR
	FK2 (recipeID) REFERENCES RECIPE

- **13. Draw the ERD and Relational Schema for the described business case**

Entities:

SUBJECT
LECTURER
STUDENT
ENROLLMENT
DISCUSSION BOARD

Attributes:

SUBJECT - subcode, title, description, semester run (OFFERING class as it repeats)
LECTURER - lectId, name
STUDENT - studentID, studName
DISCUSSION BOARD - post, date, time
ENROLLMENT - grades, year, subject

### Lab Work

- **1. Using the movie table, create a query that only lists the movies from years 2000-2010 that have a runtime of at least 120 minutes**

SELECT * FROM MOVIE
WHERE RUNTIME >= 120
AND (RELYEAR >=2000 AND RELYEAR <=2010)
ORDER BY RELYEAR, RUNTIME;

- **2. Create a view named mov2000 based on the above query**

CREATE VIEW mov2000 AS
SELECT * FROM MOVIE
WHERE RUNTIME >= 120
AND (RELYEAR >=2000 AND RELYEAR <=2010)
ORDER BY RELYEAR, RUNTIME;

- **3. Write a query that selects all the data from mov2000**

SELECT * FROM mov2000;

- **4. Write a query that selects all the movies from mov2000 with an 'M' rating**

SELECT * FROM mov2000
WHERE RATING_CODE = 'M';

- **5. Write a query that uses movie, actor and casting. The query must list every row in the casting table and also display the move name and actor name associated with the row.**

SELECT * FROM
CASTING C
LEFT OUTER JOIN MOVIE M
ON C.MOVIENO = M.MOVIENO
INNER JOIN ACTOR A
ON C.ACTORNO = A.ACTORNO
ORDER BY C.MOVIENO ASC;


- **6. Create a view named castview based on the above query**

CREATE VIEW castview AS
SELECT * FROM
CASTING C
LEFT OUTER JOIN MOVIE M
ON C.MOVIENO = M.MOVIENO
INNER JOIN ACTOR A
ON C.ACTORNO = A.ACTORNO
ORDER BY C.MOVIENO ASC;

- **7. Write a query that selects all rows from castview where the movie year is 1999**

SELECT * FROM castview
WHERE RELYEAR = 1999;