## ERD: Strong and Weak Entities & SQL: Aggregation Function

### Terms

- **Strong Entity**

A Strong Entity is an entity in a ERD, where each row in the table can be uniquely identified with its own attributes. It means that if we add additional rows to the table, it can allow us to do so without any PK errors.

- **Weak Entity**

A Weak Entity is an entity in a ERD, where each row in the table cannot be uniquely identified just by its own attributes. It needs to borrow an additioanl identifier (usually the FK of its parent) for each row to be uniquely identified.

- **Primary Key**

Is the column in a table, that acts as the unique identifier for that row entry. It means that irrespective of the rest of the data in the table, the primary key cannot exist more than once.

- **Foreign Key**

Is a column in a child table that links to the primary key of the parent. It defines the parent-child relationship

- **Composite Key**

A composite key is usually used by weak entities to establish their primary key. It is where there is a need for more than 1 column to make up the primary key for the entity (table).

- **Count**

Is an SQL aggregate function that is used to count the number of rows in a table. The statement COUNT(*) will count all the rows in the table, whereas the statement COUNT(COLUMN_NAME) will count all the rows in the table where the column entry is NOT NULL.

- **Sum, Avg**

Is an SQL aggregate function that is used to calculate the Sum and Average of a particular column. It requires the entries in the column to be NUMBERS otherwise it will throw an error saying that it cannot calculate. Aggregate functions will only return a single value, meaning that they cannot be combined simply with non-aggregate functions without using the GROUP BY clause.

- **Min, Max**

Is an SQL aggregate function that is used to calculate the smallest and largest value in the column. It only counts the NON NULL rows.

- **Group By**

Used when combining aggregate functions with non-aggregate functions, it allows us to manipulate the result set by grouping the aggregate function results against a particular non-aggregate function column.

- **Having**

Used in conjunction with the aggregate function, as a way of using aggregate functions to filter the result set. It is the aggregate function equivlant of using the WHERE clause, however unlike the WHERE, HAVING works on the result set. WHERE filters to build the result set.

### Support Questions

- **1. What is an aggregate function?**

An aggregate function is a SQL function that performs a calculation on a column or the row within a table that calculates and returns a single value. Such aggregate functions include, COUNT, AVG, MIN, MAX, SUM will count the rows and return a single value.

- **2. Describe how an aggregate function could be used with the above table and the column named salary?**

An aggregate function could be used to find the AVG, MIN, MAX salary will finds the average, min, max salaries in the table. If we used SUM it will add all the salaries together, and COUNT will just count all the non-null rows.

- **3. What does the aggregate function Count(*) do?**

The Count(*) function will count all the NON-NULL rows that are in the table. In other words it should return the number of rows that are in the table.

- **4. What does the aggregate function Count(AGE) do?**

The Count(AGE) will count all the rows in the AGE column that have NON-NULL values, in this example it will return 2.

- **5. What does the aggregate function Count(NAME) do?**

The Count(NAME) will count all the rows in the table where there are NON-NULL values in the Name column. In this example it will return 3.

- **6. What value is returned by : SELECT SUM(Salary) FROM Employee?**

It will select all the rows in the table, where the Salary is NON-NULL and return the total sum. In this example it will be 225000

- **7. What value is returned by : SELECT MIN(Salary) FROM Employee?**

It will select all the rows in the table where Salary is NON-NULL and return the row with the smallest salary. In this example it will be 50000

- **8. What value is returned by : SELECT MAX(Salary) FROM Employee?**

It will select all the rows in the table where Salary is NON-NULL. From those rows, it will calculate which row has the highest salary. In this example it will be 100000

- **9. Can a WHERE clause be used in conjunction with a aggregate function?**

It can be used since it appears that the aggregate function works on the result set, whereas the WHERE clause is used to build the result set.

- **10. What is the purpose of a GROUP BY clause**

It allows an aggregated value to be returned for EACH distinct value specified in the GROUP BY clause.

- **11. Which of the following are a AGGREGATE expression? (Salary, Avg, Min, DeptNo)**

The Avg and Min are non aggregate functions

- **12. Which of the following are a NON AGGREGATE expression? (Gender, Count, Empid, Sum, Max)**

The Gender, Empid are non aggregate expressions, they do not return a single item.

- **13. What are the main guidelines to remember when using a GROUP BY clause**

In a GROUP BY clause, we usually use it when we want to also use non-aggregate functions. The main guideline here will be that when using the GROUP BY clause, that we columns that we put in the GROUP BY are the same ones that we put into the SELECT statement.

The aggregate function should not be in the GROUP BY clause. It is to be placed in the HAVING clause.

- **14. Show the total salary paid to each department**

SELECT DeptNo, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DeptNo;

- **15. Show the total salary paid to each department in descending total sequence**

SELECT DeptNo, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DeptNo
ORDER BY SUM(salary) DESC;

- **16. What is the purpose of the HAVING clause**

The HAVING clause allows you to filter the result set to ommitt entries to be displayed. It allows us to filter based on an aggregate function.

- **17. A HAVING MUST be used in conjunction with a GROUP BY clause true/false?**

False apparently.

- **18. A HAVING clause MUST use the same aggregate expression used in the select statement true/false?**

TRUE, the HAVING clause is to use the same as the aggregate function.

- **19. What is the output generated by this statement? **

SELECT DEPTNO, SUM(Salary)
FROM EMPLOYEE
GROUP BY DEPTNO

The output will be -

DEPT NO | SUM(salary)
3			150000
2			70000
1			20000

- **20. What output is generated by this statement?**

SELECT DEPTNO, SUM(salary)
FROM EMPLOYEE
WHERE GENDER = 'M'
GROUP BY DEPTNO

The output will be - 

DEPT NO | SUM(salary)
3			50000
1			20000

- **21. What is the ouput generated by this statement?**

SELECT DEPTNO, SUM(salary)
FROM EMPLOYEE
WHERE GENDER = 'M'
GROUP BY DEPTNO
ORDER BY 1

The output will be -

DEPT NO | SUM(salary)
1			50000
3			20000

- **22. What output is generated by this statement?**

SELECT DEPTNO, SUM(salary)
FROM EMPLOYEE
GROUP BY DEPTNO
HAVING SUM(salary) > 50000

The output will be -

DEPT NO | SUM(salary)
3			150000
2			70000

- **23. What output is generated by this statement?**

SELECT DEPTNO, SUM(salary)
FROM EMPLOYEE
WHERE GENDER = 'F'
GROUP BY DEPTNO
HAVING SUM(salary) > 80000

DEPT NO | SUM(salary)
3 			100000

- **24. What is a Strong entity**

A Strong entity is an entity where each row can be uniquely identified by the attributes that the entity contains. It does not need to borrow attributes from another entity to be able to uniquely identify a row.

- **25. What is a Weak entity**

A Weak entity is where each row cannot be uniquely identified with the attributes that the entity contains. It is required to borrow an attribute from another (parent) entity for it to be able to uniquely identify each row.

- **26. Is this a strong or weak entity**

It is a Strong entity. The reason for that is because the entity box in the diagram is not surrounded by another box. The identifier in the entity is BuildingCode which is illustrated by the underline.

- **27. Consider the ERD**

**a. When this ERD is converted, will there be any foreign keys, how do you now which has the foreign key and which end contains it**

The Office entity has the foreign key, and we know that it does because it is on the MANY end of the relationship. However the FK is also the borrowed entity for the Office since the relationship is underlined.

**b. Does this ERD have any weak entities**

Yes the Office entity is the weak entity. It is illustrated as the double box with the underline in the relationship.

**c. What is the identifier for each entity**

Building - BuildingCode
Office - OfficeNo, BuildingCode

**d. Convert to a Relational Schema**

Building (BuildingCode, BuildingName)
	PK (BuildingCode)

Office (OfficeNo, BuildingCode, SquareMeters)
	PK (OfficeNo, BuildingCode)
	FK (BuildingCode) REFERENCES Building

- **28. Consider the ERD**

**a. How many foreign keys will exist, which relations will have foreign keys, write the relational schema**

BUILDING (BuildingCode, BuildingName)
	PK (BuildingCode)

OFFICE (OfficeNo, BuildingCode, SquareMeters)
	PK (OfficeNo, BuildingCode)
	FK (BuildingCode) REFERENCES BUILDING

EMPLOYEE (EmpNo, OfficeNo, BuildingCode, EmployeeName)
	PK (EmpNo, OfficeNo, BuildingCode)
	FK (OfficeNo, Buildingcode) REFERENCES OFFICE

There will be 2 foreign keys, one for OFFICE and one for EMPLOYEE. The foreign keys are composite keys made up of more than 1 column

### SQL Commands

DROP TABLE EMPLOYEE;

CREATE TABLE EMPLOYEE (
	EmpId NUMBER(1),
	Name VARCHAR2(30),
	Salary NUMBER(6),
	Gender VARCHAR2(1),
	Age NUMBER(2),
	DeptNo NUMBER(1),
	PRIMARY KEY (EmpId)
);

INSERT INTO EMPLOYEE (EmpId, Name, Salary, Gender, Age, DeptNo) VALUES (1, 'Fred', 50000, 'M', 26, 2);
INSERT INTO EMPLOYEE (EmpId, Name, Salary, Gender, Age, DeptNo) VALUES (2, 'Sue', 100000, 'F', NULL, 2);
INSERT INTO EMPLOYEE (EmpId, Name, Salary, Gender, Age, DeptNo) VALUES (3, 'Emma', 75000, 'F', 21, 1);