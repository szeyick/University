## Surrogate Keys and More ERD's

### Tutorial Questions

- **1. What is a surrogate key**

A surrogate key is a made up key that could be used as the primary key that can uniquely identify rows within a table. It is not a value that comes from the business narrative and is simply "made up".

- **2. Why might you introduce a surrogate key?**

You may want to introduce a surrogate key as a means to make the definition of a primary key simpler. So far we have created primary keys as composites from the other attributes in the entity. However in the real world, this may be complex, thus it may sometimes just be easier to "make up" a surrogate key to identify the row in the table.

An example of this would be a enrollment table. Rather than generating a primary key from a combination of names, subject code, year, and whatever, it may simply be easier just to generate an "enrollment number" attribute and use that as the primary key.

- **3. Should you ever introduce a surrogate key into a 'Draw-an-ERD' style question in the tutorial or exam?**

NO

- **4. Create an ERD from the business narrative**

**Assume that we now want to record every score recorded by a player for a game, what changes are required**

Example - At 8pm Scott scored 8630 points for Tetris on the PC. At 8:15 he scored 9100 for the combination.

- **5. Read the business narrative and construct the ERD and Relational Schema**

