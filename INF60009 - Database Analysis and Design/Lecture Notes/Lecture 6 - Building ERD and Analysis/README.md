## Lecture 6

Drawing ERD's, M:M Relationships and Visio

### Designing ERD's

There are many types of ERD notations, however even with all the different types, it follows the similar path for ERD design.

#### Identifying Entities

For something to be defined as an entity, it must have **more than 1 instance**, be a **noun** and will usually be written in **uppercase**. 

In addition, it must also have multiple properties that can consist of its own attributes or relationships with other entities.

A **strong entity** can be identified by is own attributes, and a **weak entity** borrows its identity from other entities.

### Identifying Attributes

An attribute is something that can be identified by a single word or phrase. Generally we use **underscores** or **capitals**. An attribute cannot have properties, otherwise it might be an entity. 

Also, the attribute must depend on the entity to exist.

### Attaching Attributes to Entities

Once we have identified, the attributes and entities in our problem statement, we can begin to associate the attributes to entities with the same notation that we have currently used.

**Insert Image**

We need to be careful as some things that can seem like attributes may not be. The idea with the attribute is that it can be used multiple times.

### Identifying Relationships

An entity as we've learnt so far can have a relationship with another entity. The idea here will be to make the names of the relationships **relevant** and **worth recording**. The relationship must also be **persistent** or **permanenent**.

### Determine Cardinality and Participation Constraints

Now that we've established the type of relationship, we need to determine whether it is a M:1 or 1:1 or M:M relationship between the entities.

### Attach Remaining Entities and Relationships

If we have a M:M relationship, we will most likely have left ove attributes that will form part of the middle relationship between the M:M, that will break it up until 2 M:1 relationships.

**Insert Image**

### Expand Many to Many Relationships

Here we create the 2 M:1 relationships if we have a M:M relationship between our entities. The new entity will be a weak entity. We need to then update the diagram.

**Insert Image**

### Attach Attributes

Our remaining entities from the previous stages will most likely be added to our new entity. We also then need to add the borrowed identifiers since it will be a weak entity.

**Insert Image**

### Convert to Relational Schema

Now that we have the completed ERD, we can construct the relational schema.

```
PATRON (PatronNo, Name, Gender)
  PK: PatronNo

TOY (ToyNo, Description, YearAquired)
  PK: ToyNo
  
LOAN (PatronNo, ToyNo, DateBorrowed, DateReturned)
  PK: PatronNo, ToyNo, DateBorrowed
  FK1: PatronNo REFERENCES PATRON
  FK2: ToyNo REFERENCES TOY
```

The reason that the LOAN PK contains 3 values as having PatronNo and ToyNo will not allow a Patron to borrow the same toy more than once as it will not create a unique entry in the LOAN entity.

### Testing the ERD

To ensure that we have the correct ERD and relational schema we can test it by inserting some sample data into the strong entities.

