## Introduction to SQL and ERD

The key terms in this lecture along with a short definition -

- **Databases**

The component that stores the business data.

- **Database Management Systems (DBMS)**

The software that allows you to manage databases.

- **Structured Query Language (SQL)**

The language that is used by the DBMS to manage the database.

- **Entity Relationship Modelling and Diagrams**

An **entity relationship model** is a logical representation of the data in the database. It uses **entities** to represent people, objects, events and other bits of data to establish the relationships between them. The relationships are defined by the **business rules of the organisation**.

- **Relational Data Modelling and Normalisation**

Data storage **design rules** for current day databases.

- **Data Warehousing**

An alternative **design** of a database that will be suited for **data mining** and **business intelligence** requirements

- **Entities**

The entity defines the "thing" or the container for the data to sit in. It is the data structure.

- **Entity Instances**

Whilst an entity defines the container that will store our data, once we have data to put into it, it becomes an **entity instance**. It would be the equivelant of initialising a data structure, where the initialised structure will become an instance of the class.

- **Attributes**

The attributes represent the "types" of data that sit within the entity. If we compare this to a data structure, this would be the equivelant to the instance variables that make up the properties of the data structure (entity).

- **Relationships**

This describes the relationship between entitities.

- **Business Rules**

The entities in an E-R diagram may follow some rules, this will determine how each of the entities will relate to each other. Rules can be both **common** or **unique** to all organisations, and the design of the E-R diagram should generally follow these rules.

- **Cardinality Constraints**

Cardinality defines **how many** of an entity relates to another. Cardinality is always represented as a **one** or **many** type of relationship.

- **Indentifiers**

An identifier is an attribute within an entity that allows us to uniquely identify the entity instance. 

- **Nulls**

The value that we place into a database table if the real value is unknown, not available or not required.

- **Primary Keys**

The primary key is the unique identifier that each row in a table is required to have. This key is what will allow us to specifically find an entry in a database table.

- **Foreign Keys**

A foreign key is the key that allows an entry in one table to refer to a primary key in another. Generally this foreign key will match the primary key in the another table. It is how we establish the link from one table to another.
