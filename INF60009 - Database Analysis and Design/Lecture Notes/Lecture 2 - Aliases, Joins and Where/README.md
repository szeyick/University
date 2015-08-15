## Foreign Keys, Aliases, Joins and Where

A summary of the key terms from this lecture

- **Foreign Keys**

The key that connects one table with another. It functions as a **child** in a Many to One entity relationship as the **One** end.

- **Primary Keys**

The main identifier for each row in a table. It functions as a **parent** in a Many to One entity relationship as the **Many** end.

- **Foreign Key Contraints**

Is the mechanism that allows us to validate the input that is entered in the foreign key column to ensure that it actually matches the primary key in the associated table.

- **Null Constraints**

We can ensure that a column always contains a value by using the **Not Null** property when we define a column when creating a table. If a null value is entered for the column, the insertion is not permitted and the RDBMS raises an error.

- **ERD Participation Constraints**

This is how an ERD represents the null constraint relationships. The | implies that the relationship **must** exist, an O implies that the relationship **may or may not** exist. The O is usually drawn on the side of a **many** relationship as it implies that it **may or may not have children**.

The reason that this is done so as to not specifically define that an entity **must** have a child for it to be a valid relationship.

- **SELECT Statements**

How we begin an SQL query, we can select all the columns in the table or just a subset of column names.

- **Selecting Column Headings**

We can rename selected columns in the output set and even add additional columns through the **AS** keyword. 

- **Table Name Qualification**

Allows us to refer to a table by a different name, usually called an "alias".

- **Alias**

A shortcut to how we want to refer to a table. Rather than typing out the entire table name each time we want to refer to it, we use a shortcut called a **temporary alias**. This is particulary useful when we are dealing with columns of the same name in different tables. This mechanism allows us to tell SQL which columns in which table we are referring to.

- **Cartesian Product**

The returned result set where all the rows in one table are matched with all the rows in the second table. Usually shows the incorrect way of retrieving data from multiple tables.

- **Inner Joins**

The correct way to join data from multiple tables together. This method ensures that the primary key and foreign key relationship in the queried tables satisfies a given condition. This condition is usually just the foreign key in one table matches the primary key in the other table.

- **Where**

Part of the SQL query that allows us to filter data based on some sort of condition. Used in conjunction with logical AND, OR operators and comparators.

- **Upper and Lower Functions Operator**

Converts the values in a selected column to upper or lower case. This is useful when we want values such as 'Stuart' and 'STUART' to be the same since we convert one into the other.

- **Between Operator**

Used with the WHERE statement rather than specifying something with an AND statement. Generally not used as often as applying <= and >=.

- **Like**

Allows you to find partial matches by using the WHERE ColumnName LIKE '%A' to replace characters. The % means "any character".

- **In Operator**

Functions like a logical OR operator, allows you to define a list of values, anything that matches will be outputted in the result set.

- **Is Null / Is Not Null**

Allows you to define whether values exist or do not exist in the columns, flips the condition directly after it.

- **Distinct**

Removes all duplicate entries from the result set.

- **Unique**

Removes all the duplicate entries from the result set, generally Distinct is used in favour of Unique.

