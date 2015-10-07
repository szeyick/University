## Functional Dependencies, Constraints and Procedures, Ternary Relationships

### Narratives and Surrogate Keys

When we design an ERD from a business case, only use the information that is available. Do not invent information, rules or constraints that otherwise would not exist. Do not invent identifiers (**surrogate keys**) that are not mentioned in the business case.

### Difficult Validation Problems

Sometimes there can be situations where using table constraints is not enough to restrict the input values. 

For example, in the appointment scenario, the specific start time of an appointment can be constrained, however we can enter in appointments that overlap each other which can still be inserted into the table. The result of this would be that a doctor would have concurrent appointments taking place even though they start at different times.

To resolve these types of validation problems we would use **stored procedures** or **stored functions**. It is **code** that is stored within the database and not in an external file.

### Stored Procedures and Functions

The code for these are written in SQL, and is made up of conditional operations (IF), iterators (Loops), variables, paramters and error handling like in most other programming languages.

To access the procedure or function, it makes a call to the function name that will validate the input before inserting it into the database table.

### Functional Dependencies

A functional dependency is a **constraint** between two sets of attributes in a relation.

For example - 

```
R(X, Y, Z)

Where R = relation

Can also be written as -

X ? Y
```

Means that X is said to **functionally determine** another attribute Y, if and only if, each value is associated with precisely one Y value.

In other words it means - "X is the **primary key** for Y".

Here we define each of the letters as follows -

- **Determinant X**

X is the determinant. In the above example, the **primary key** X is always the determinant.

A determinant can also be a **composite** (made up of more than 1 attribute). It would mean that for the particular composite primary key, there is exactly **one** value for another attribute.

- **Dependant Y**

Y is the dependant. In the above example, the **primary key** X is never the determinant.

### Null vs Default Values

Sometimes we may want set default values into a table. An example of this would be an attendence table, whereby we default all entries to N for attendence since in the beginning noone has attended anything. Once someone attends, we then change the value to Y.

This is a valid method of determining who has attended or not, however it is a lot of work if you have a large database of people who did not attend since each time you'd have to set the value to N if they did not attend. An alternative would be to set all initial values for the attendence column to NULL.

The issue with this is that you may not get the desired result of displaying all people that did not attend. An alternative solution to this would be to not add a user row if they do not need to be.

### Ternary Relationship

Is essentially another entity that allows you to capture information and link tables together of required information. It could be seen as a composition of other attributes from other entities.

A ternary relationship follows the pattern - 

```
ONE entity AND ONE entity MAY have MANY Ternary Entity
```

If we use this pattern to describe the image below we end up with -

**Insert Image**

```
ONE MUSIC STUDENT AND ONE INSTRUMENT MAY have MANY GROUPS
ONE GROUP AND ONE INSTRUMENT MAY involve MANY MUSIC STUDENT
ONE GROUP AND ONE MUSIC STUDENT MAY only have ONE INSTRUMENT
```

The format follows the same as we have been doing all this time.

