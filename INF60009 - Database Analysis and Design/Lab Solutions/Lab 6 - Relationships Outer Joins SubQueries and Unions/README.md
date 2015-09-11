## Lab 6 Notes

- **Consider the following ER diagram**

**Insert Image**

**Convert this ERD to a Relational Schema, Indicate the PK and FK**

```
STUDENT (StuNo, StuName)
  PK: StuNo

ENROLMENT (StuNo, SubCode, Grade)
  PK: StuNo SubCode
  FK1: StuNo REFERENCES STUDENT
  FK2: SubCode REFERENCES SUBJECT

SUBJECT (SubCode, SubName)
  PK: SubCode
```

**What is the PK of ENROLMENT**

The primary key of ENROLMENT is borrowed from both the STUDENT and SUBJECT entity so it will be StuNo and SubCode

**Write an SQL statement to create a student: STU101 Henry Bloggs**

```
INSERT INTO STUDENT (StuNo, StuName) VALUES ('STU101', 'Henry Bloggs');
```

**Write an SQL statement to create a subject: INF10002 Database Analysis and Design**

```
INSERT INTO SUBJECT (SubCode, SubName) VALUES ('INF10002', 'Database Analysis and Design');
```

**Write the SQL statement to create an enrolment**

Enrol Heny into Database Analysis and Design

```
INSERT INTO ENROLMENT (StuNo, SubCode, Grade) VALUES ('STU101', 'INF10002', NULL)
```

**After doing very poorly in the Final Exam, Henry scores a "N". Write the SQL statement that records this information**

```
UPDATE ENROLMENT
SET Grade = 'N'
WHERE StuNo = 'STU101'
```

**Some months later, Henry sits another exam for INF10002 and obtains a "D". Write the SQL statement that records this information**

```
UPDATE ENROLMENT
SET Grade = 'D'
WHERE StuNo = 'STU101'
AND SubCode = 'HIT1402'
```

**Can this database design record the fact that Henry scored an "N" in one semester, then "D" in the next semester? Why?**

No this database cannot record this fact, the reason being is that when we UPDATE enrolment we update the same row. Because it is a weak entity that borrows its identifiers, the primary keys cannot differentiate different results in different semesters.

- **Consider the following ER Diagram**

**Insert Image**

**Convert the ERD to a Relational Schema, indicate all PK's and FK's**

```
PERSON (pid, PersonalName)
	PK: pid

LOAN (pid, ecode, LoanDate, ReturnDate)
	PK: pid, ecode, LoanDate
	FK1: pid REFERENCES PERSON
	FK2: ecode REFERENCES EQUIPMENT

EQUIPMENT (ecode, Description)
	PK: ecode
```

**What is the PK of LOAN**

The PK of loan will be pid, ecode, LoanDate. It has its own primary key but also borrows identifiers from PERSON and EQUIPMENT.

**Write the SQL statement to add a Person: P357 Sue Jones**

```
INSERT INTO PERSON (pid, PersonalName) VALUES ('P357', 'Sue Jones');
```

**Write the SQL statement to add an item of Equipment: DC100 Nikon Digital Camera**

```
INSERT INTO EQUIPMENT (ecode, Description) VALUES ('DC100', 'Nikon Digital Camera');
```

**Write the SQL statement to record Loan information: Sue borrows the DC100 on June 1**

```
INSERT INTO LOAN (pid, ecode, LoanDate, ReturnDate) VALUES ('P357', 'DC100', '1 June', NULL);
```

**Write the SQL statement to record more Loan Information: Sue returns the DC100 on 6 June**

```
UPDATE LOAN
SET ReturnDate = '6 June'
WHERE pid = 'P357'
```

**Write the SQL statement to recrod another Loan: Sue borrows the DC100 on July 20**

```
INSERT INTO LOAN (pid, ecode, LoanDate, ReturnDate) VALUES ('P357', 'DC100', '20 June', NULL);
```

- **The following ERD has been designed**

**Insert Image**

**Fully expand the M:M Relationships**

**Insert Image**

**Convert the ERD to a Relational Schema. Indicate all the PK's and FK's**

```
STUDENT (StuNo, Name)
	PK: StuNo

VISITS (StuNo, Organisation)
	PK: StuNo, Organisation
	FK1: StuNo REFERENCES STUDENT
	FK2: Organisation REFERENCES WORKPLACE

WORKPLACE (Organisation, Address)
	PK: Organisation

ATTENDS (StuNo, BDate)
	PK: StuNo, BDate, BTime
	FK1: StuNo REFERENCES STUDENT
	FK2: BDate, BTime REFERENCES BRIEFING SESSION
```

**What is an intersection entity**

An intersection entity is the resulting weak entity that is created from breaking up a M:M relationship into 2 M:1 relationships.

**Which of the entities in this ERD is an intersection entity?**

The CASTING entity is the intersection. It is the weak entity. 

**What is a characteristic entity**

A characteristic entity is an entity that contains a single attribute.

**Which of these entities in the ERD is a characteristic entity**

The DEPT entity is the characteristic entity as it is the entity that contains the 1 attribute.

- **Consider the ERD and relational schema and data**

**Using a UNION clause, write an SQL statement that list the names and genders of all staff and students in a single result set. The list must be in gender / name sequence**

```
SELECT StuName, StuGender FROM STUDENT
UNION
SELECT StaffName, StaffGender FROM STAFF
ORDER BY 2, 1
```

**Using a SUBQUERY statement, write an SQL statement that displays all rows from the Attendance table only if the score given is less than the average of all scores.**

```
SELECT * FROM ATTENDENCE
WHERE SCORE < (SELECT AVG(Score) FROM ATTENDENCE)
```

**Using an LEFT OUTER JOIN statement, write an SQL statement that displays a list of ALL staff names and seminar numbers of any seminars that they have presented. The list must include staff who are not allocated to any presentations.**

```
SELECT S.StaffName P.Seminar
FROM STAFF S
LEFT OUTER JOIN PRESENTATION P
ON S.StaffNo = P.StaffNo
ORDER BY S.StaffName
```

**Using an RIGHT OUTER JOIN statement, write an SQL statement that displays a list of ALL student names and shows the title of any seminar that they attended. Note: This list must include students who attended no seminars.**

```
SELECT STU.StuName, SEM.Title
FROM STUDENT STU
INNER JOIN ATTENDANCE ATT
ON STU.SemId = ATT.SemId
RIGHT OUTER JOIN STUDENT STU
ON ATT.StuNo = STU.StuNo
ORDER BY STU.StuNo;
```
