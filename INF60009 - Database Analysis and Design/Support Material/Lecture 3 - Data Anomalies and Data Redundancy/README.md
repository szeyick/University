##RM: Data Anomalies and Data Redundancy SQL: Table and Column Constraints

### Terms

- Insertion Anomaly

An insertion anomaly occurs when you try to insert data into a row but you cannot because you don't have a primary key.

- Update Anomaly

An update anomaly is where you update a row and the information in one of the rows contradicts the information that you just inserted. Or that you forgot to change the other row also.

- Deletion Anomaly

A deletion anomaly is when you delete some information and you accidentally cause other information to be deleted along with it that you didn't want to delete.

- Data Redundancy

Is the process of where you have the same data copied all over the place.

- Data Inconsistency

Is the process where table data in one cell contradicts the data in another cell even if they are supposed to be referring to the same place.

- Composite Key

Is where you need more than 1 attribute in a table to be able to define the primary key.

### Questions

- **1. What does the term data redundancy mean**

The term data redundancy refers to the state of a database where the same data is found all over the place, either in the same table or in a different table. It becomes redundant because of the number of copies is unneccessary.

- **2. How can Data Redundancy lead to Data Inconsistency**

Because with data redundancy you have the same data all over the place, which at some point you may want to update or delete. If you forget to update the data in all the places that it appears in, then it'll become inconsistent.

- **3. Consider the table and answer the questiosn**

**The name 'John' appears multiple times in the column named FName. Is this an example of Data Redundancy?**

No it is not because each row that contains John has a different employee ID and would appear to be referencing a different person.

**The name 'Smith' appears multiple times in the column named SName. Is this an example of Data Redundancy?**

No, because Smith is a fairly common surname, and each row that contains Smith refers to a different EMPID.

**The name 'John Smith' appears multiple times in the combined columns named FName & SName. Is this an example of Data Redundancy?**

No it is not a example of data redundancy because the EMPID is different, and would be considered to be referring to different people

**The year 2010 appears multiple times in the column named Commenced.  Is this an example of Data Redundancy?**

No it is not, because they refer to different people.

**The value 'Acc' appears multiple times in the column named DeptId.  Is this an example of Data Redundancy?**

No because it is the smallest amount of information that we need in this table to tell us about the department, consider it a FK.

**The value 'Accounting and Finance' appears multiple times in the column named DeptDesc. Is this an example of Data Redundancy?**

Yes it is, it should be extracted out along with the DEPTID into a different table.

**The value 'Level 3' appears multiple times in the column named DeptLocation.  Is this an example of Data Redundancy?**

Yes, it should be extracted out along with the DEPTID, DESCRIPTION and LOCATION.

- **4. A user modifies the name of Sue Rubys department location to Level 5, what sort of problem does it lead to**

It leads to an update anomaly because changing the DEPTLOCATION for Sue when the Acc DEPTID is supposed to be on Level 3, as with the other employees that are working in the same department.

This will lead to data inconsistency as the new level contradicts where the Acc department is supposed to be at.

- **5. What is an update Anomaly? Provide an example with the table above**

AN update anomaly is where updating the table causes the data to contradict itself. An example of this would be to change the location of the department or even change its namewhilst leaving the deptID the same. If you only do this for one row and not the rest of the rows that also match that department, then it is an update anomaly that leads to data inconsistency.

- **6. What is a deletion anomaly? Provide an example using the above table**

A deletion anomaly is where you delete infomration and it inadvertenely deletes other information that might be useful. IN the case of the table above, deleting Emma Harris from teh table will lose all information regarding the IT department and its location. Thus if someone wanted to find the location of that department, they'd have to re-enter all the details again.

- **7. What is an insertion anomaly? Provide an example with the above table**

An insertion anomaly is where you try to insert information into a table but you do not have the primary key information. For the table, if we wanted to add a new department to the table, we would be required to add an employee there as well because the mployeeID is the primary key for the table. This means that without an employees details, we cannot add a new department to this table.

- **8. Assume that the create table statement is**

CREATE TABLE student (
  STUID VARCHAR(7)   PRIMARY KEY
, STUNAME VARCHAR(20)
, TEST1 NUMBER
, TEST2 NUMBER
, GRADE VARCHAR(1)   
);

- **9. Write an SQL statement that updates the studen ttable. It must set the Grade to 'A' for all students**

UPDATE TABLE student (
	SET GRADE = 'A'
);

- **10. Write an SQL statement that updates the student table. It must set the Grade to "B" for all students who have a total score of less than 15**

UPDATE TABLE student
	SET Grade = 'B'
	WHERE TEST1+TEST2 < 15;

- **11. Write an SQL statement that deletes all rows in the student table where both Test1 and Test2 values are equal to 0.**

DELETE student
	WHERE TEST1+TEST2 = 0;

- **12. Write an SQL statement that deletes all rows in the student table**

DELETE student;

- **13. Consider the following Relational Schema**  

Person(Firstname, Surname, Gender, MobileNo) (both Firstname and Surname are underlined)

**What is the Primary Key of Person?** :The PK of Person table is Firstname, Surname.

**How many Primary Keys does this table have?** : It has 1 but it is a composite PK made up of 2 attributes.

**What name is given to a Primary Key that is made up of more than one column?** A composite key

- **14. Consider this ERD and relational schema and data examples**

**Describe the relationships in the above ERD**

ONE STUDENTS MOTHER MUST be born in ONE COUNTRY
ONE STUDENTS FATHER MUST be born in ONE COUNTRY
ONE COUNTRY MAY have their mother birth from ONE STUDENT
ONE COUNTRY MAY have their Father birth from  ONE STUDENT

**What are the foreign key column in the Student Table?**

The foreing key column in the Student table will be the CountryID of the COUNTRY table.

**Does the Foreign Key name in a table have to be spelt identically to the Primary Key it references?**

No it does not, so long as it is correctly references through the REFERENCES tag, the RDBMS will make the link

**What are the country names that Fred’s parents were born in?**

Australia and USA

**What are the country names that Dave parents were born in?**

India, China