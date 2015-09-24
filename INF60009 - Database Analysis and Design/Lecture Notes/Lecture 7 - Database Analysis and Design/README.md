## Database Analysis and Design

Below is a summary of the key points in the lecture.

- **Clear Entity Names and Meanings**

Defining entity names clearly and understanding their exact purposes is key for creating an ERD. The trick is to avoid confusing names for entities and that the name describes exactly what the data will be stored in it relates to.

- **Static and Variable Data (Attributes)

In a database, there will be data that once entered will never change. These things can include, Subject Codes, Account Login Numbers and Item Descriptions. 

If we have an entity that consists of both static and variable data attributes, it may be best to split that entity up into two so we avoid data redundancy.

- **Building ERD's**

There is no correct way to build an ERD, it usually takes practice. Depending on the business case, it may be better to draw out all the M:M relationships before dividing them up. 

- **Testing ERD's**

To ensure the ERD that has been drawn is correct, follow some general tests to ensure that the data flows correctly through it. This can include inserting test data and verifying that it meets all the business case requirements.

- **Views**

A view is a way to store a complex SQL query that can be used over and over again. It will store the query, but not the retrieved data.

It is a virtual table, but itself is not a table. Each time you call the view will re-run the query.

- **Creating Views**

Views can be created using the **CREATE VIEW viewName** statement. The structure of the views virtual table will depend on the statements that follow, where you can use joins and groups like in a select statement.

- **View Security**

We can limit visibility on a view by granting access priveledges on a view. You can grant read only or write only or both sets of priveledges to a view. This allows us to limit what certain poeple can see on a view or table.

If you make modifications to the view, it will update the rows in the tables that it refers to. 