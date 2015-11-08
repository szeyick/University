## Data Warehousing, Big Data and ERD

### Tutorial Questions

- **1. What is a TPS System and who are its main users?**

A TPS system or a "Transaction Processing System"" is a system that is used by organisations to record transactions such as sales and order fulfillment. It's main users would be the operational that are responsible for the day to day activities of an organisation. They would use the TPS to log things for individual sales and other things like that.

- **2. A typical query run on a TPS system accesses how many rows? A large or small number**

A query run on a TPS system would access a small number of rows. The reason for it being that it is not supposed to be used for aggregate queries, as that is the purpose of the data warehouse. Also it would eb slow to access all the rows in the TPS to do some particular query or it would also be very poor design.

- **3. Is it difficult to write a query to access data from multiple TPS systems? Why?**

Yes it would be because of the linking issues between different TPS. Aside from their obvious differences in possible DBMS's used, a single query or transaction requires the knowledge of the inner workings of multiple databases. Unless you know what you are updating in each of those tables you would run the risk of having inconsistent data.

- **4. Why do users want to use data from multiple TPS systems?**

Users would generally want to use data from multiple TPS systems because that is how large organisations are run. They would have many TPS systems that may each contain different types of sales or transactional data which business analysts or management would use to get aggregate information to allow to read sales, predictive forecasts and things like that. It is possible that management would want to see the total of all sales that exist in multiple systems rather than just one.

- **5. A Data Warehouse extracts and stores data from one or more TPS systems**

**A typical query on a DW system accesses how many rows, a small number or large number?**

The typical query would access a large number since the data in a DW would generally be used for aggregating data, which if you were taking sales data for a number of years would require access to millions of rows if the company was large.

- **6. How do typical users of a DW differ from typical users of a TPS**

Users of a DW are generally people in management or business analysts, their jobs are to view aggregate data to make business decisions. Their usage of data involves looking at long term forecasts, monthly sales to make business decicions. They are not particularly conerned with the finer details of each transaction or sale and are interested in the 'how many'. They would generally view data as a high level overview of an organisation.

Typical users of a TPS are users that would use the system on a day to day basis to log sales and transactions. They are not concerned with how many transactions have been made over a 5 year period and are concerned more with the details of each transaction such as who the customer was or if it was a sale, then the shipping addresses and all that.

- **7. What does ETL mean when discussing Data Warehousing?**

ETL stands for Extract, Transform and Load. It is the process behind moving data from a TPS to a Data Warehouse making it useable in the context for what a business analyst or manager would use it for.

E - Extract - Means to extract the data from the TPS or multiple TPS's
T - Transform - Change the data, combine the data and clean it up, removing columns and data that is not seen as relevant for the data warehouse or too low level.
L - Load - Load the data into the Data Warehouse.

- **8. Why is it neccessary ot transform data when transferring it from a TPS to a DW system?**

Because there may not be a 1:1 mapping between the structure of the TPS and the structure of the DW system. Also the extraction may have taken data from multiple TPS's so some transformation or cleanup of the data would be required before placing it into the DW tables. Otherwise you may risk having incomplete or inconsistent data, so missing data can prove to be detrimental when reusing the data in the DW for analysing.

- **9. Which type fo DW table is more like a strong entity? A Fact Table or Dimension Table?**

A Dimension Table is a table that usually contains un-normalised data meaning that it is possible for it to contain multiple values per cell. It can also take redundant data but that is fine for a data warehouse. 

The attributes in a dimension table are rather descriptive, meaning that they are more elated to a strong entity.

Less joins are required to look up data in a dimension table as it is all there.

- **10. Which type of table is more like a weak entity? A Fact Table or Dimension Table?**

A Fact Table is a table that refers to a dimension table, this sets up the relationship that it is the child and therefore more like a weak entity. 

A Fact Table contains data that someone may want to aggregate such as sales, purchases so transactional data. Compared to a Dimension Table that may just contain data like customer details and product details.

- **11. Which type of DW table typically stores measures / quantities? A fact or dimension table?**

Measures or quantities are values that can be aggregated, as such these would be contact within a Fact Table as those tables are generally used for the aggregation of data. This is also valid when you draw a DW diagram as the Fact Tables are the diamonds that connect multiple Dimension Tables (squares) together, meaning that they inherit data from them.

- **12. Which stores more rows in a typical DW, a Fact Table or Dimension Table?**

A Fact Table would be comprised of a set of Dimesion Tables, so this is either a trick question or it would be a Fact Table since it would contain all sales information and transactions compared to a Dimension Table which would contain information about the product or customer. 

A Dimension Table would not be used for any sort of quantity data.

- **13. Data in a DW is often aggregated. What does this mean? Why is it so?**

Aggregating data means to put it all together and counted. It provides a higher level overview of the transactions that are within the TPS, rather than bothering with information regarding individual transactions or sales, a DW would only contain details about total monthly sales or yearly sales that have been cleaned up.

The reason for this is that the people who use a data warehouse would generally not care too much about the finer details of each transaction but are more interested in the high level of totals.

- **14. Data in a DW Dimension Table is often de-normalised. What does this mean? Create a Dimension Table called DWCUST based on the following tables from an OLTP System**

```
REGION (RegionCode, RegionName, Population)
CUSTOMER (CustNo, CustName, CustAddress, RegionCode)
	FK RegionCode REFERENCES REGION
```

This question appears to mean to draw the diagram -

The idea here would be that the Dimension Table (Rectangles) contain non quantity/measurement.

De-normalised data refers to data that has not been normalised. In this instance it means that values in each cell for each row can possibly contain multiple values. 

- **15. What are the four V's of Big Data, What does each V mean?**

Velocity - The speed of the data coming in can you cope with it.
Variability - How to manage the velocity of data incoming (establishing patterns of fast and slow incoming data).
Variety - The data coming in from different sources and formats
Volume - The amount of data coming in.

- **16. Why is part of Big Data sometimes referred to as unstructured or semi-structured data?**

It is because data comes in from many different types of sources and it is simply stored. The initial part of big data makes no effort to make any sense of the data, it is purely stored in the form that it is arriving in. It is up to the big data scientists to make sense of it all.