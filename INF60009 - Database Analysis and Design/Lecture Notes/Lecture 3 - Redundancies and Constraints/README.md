## Redundancies and Constraints

A summary of the key terms from this lecture

- **Data Anomalies**

Where there is inconsistent data in the database, usually caused by insertion, deletion or update inconsistencies. This leads to a poor database.

- **Data Redundancy**

Where the same data is duplicated around the database in different tables. This causes problems when updating as you would need to update the same data in multiple spots, and if you forget, then it will lead to data anomolies. Likewise when deleting data, you may forget to delete some other reference to it meaning it will become redundant.

- **Insertion Anomaly**

Occurs when inserting data into a table and you don't have the primary key available but everything else. This occurs if the table is poorly designed, meaning that not all the data for the row is available at the same time for insertion.

- **Update Anomaly**

Occurs when updating a row entry in the table. If there is duplicate entries in a table, updating a single row and column might make the data inconsistent.

- **Deletion Anomaly**

Occurs when deleting a row entry in the table, if the database table is poorly designed, the deletion event may lead to other data mistakenly being deleted as well. This happens if the database table has a mixture of different types of data, perhaps if 2 entities were merged into 1.

- **Overcoming Anomalies and Redundancies**

To overcome anomalies, the best thing to do is to perform proper table design. The general idea here would be use the correct data types but also ensure that a single entity only refers to a single type of data. It is better to create a granular table then a tighly coupled table.

- **Spreadsheets**

The oldschool way of representing databases. Not the best way to use databases since it has limited functionality and can be slow on very large datasets. 

- **Databases**

Good for representing large sets of data and filtering the results. However they can be difficult to learn requiring a larger overhead.

- **Composite Primary Key**

When there are more than 1 row in a table that can be used as a primary key. This is usually represented in an ERD and relational model but not the actual model to be created. The reason for this is because there can be only 1 primary key in a given table.

- **VARCHAR2(n)** - 

A n length character string that provides no padding for spaces (1 leading byte for null, add 1 leading bye if not null for length indicator)

- **CHAR(n)** - 

A n fixed length character string. It is not chopped like the VARCHAR, meaning that if n was 100 and you had 50 characters in the string, then it will still be considered as length 100. 

- **NUMBER(o,d)** -

Used to store numeric data, where o is the number of digits and d is the number of digits after the decimal point.

- **DATE**

Stores both the date and time.

- **ALTER TABLE**

An SQL command that allows you to modify the table without removing the data, unless the column is removed. Has **ADD**, **MODIFY**, **DROP** commands.