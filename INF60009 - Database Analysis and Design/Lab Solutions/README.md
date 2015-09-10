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
INSERT INTO ENROLMENT (StuNoSubCode, StuNo, SubCode, Grade) VALUES ('STU101INF10002', 'STU101', 'INF10002', NULL)
```
