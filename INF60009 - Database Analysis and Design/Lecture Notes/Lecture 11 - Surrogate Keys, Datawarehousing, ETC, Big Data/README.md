## Surrogate Keys, Datawarehousing, ETL, Business Intelligence and Big Data

### Composite Keys

When we've been drawing ERD's to this point we usually end up with a WEAK entity that has lots of FK identifiers as part of its primary key. 

The benefit of this is that we enforce business rules in the entities because they need to adhere to the PK:FK relationship. However this does result in extremely long PK and FK names.

This is where **surrogate keys** are beneficial. Usually the composite key is replaced by one of these which is usually generated somewhere randomly to give the uniqueness of a PK, but they have no meaning in relation to the table.

They are easier to use, faster for searching and take up less memory. The downside to it is that they cannot enforce the PK:FK relationship because they don't mean anything to the context of the database.

### TPS Database vs Data Warehouses

For business analysts, databases built from TPS are too slow to run aggregate queries on. It also interrupts daily operations by running report things on a live system and there is just too much meaningless data.

This is why we would create a data warehouse that only stores the relevant data of a TPS. It contains fewere tables, allows redundant data to be stored and is optimised to run large queries and is also independant of the TPS meaning business operations are not affected.

Also, in real world sitautions an organisation would have many databases, meaning that systemtically collecting information from each one and aggregating them is inefficient. Thus with a data warehouse we can just take what we want from all of them and put them into the warehouse. The single location allows for consistency in the data.

### ETL (Extract, Transform, Load)

- **Extract the data from the source systems (databases)**
- **Transform the data to meet the design of the data warehouse**
- **Load it into the correct tables in the datawarehouse**

The main issues with this process is that it is very hard to retrieve the data since the data can come from many sources that may not be compatible with each other making data extractino difficult.

Inconsistent data or missing data can make big impacts on analysis of the data once it has been placed into the data warehouse. Depending on location the data may also need to be transformed (metric to imperial, celcius to fahrenheit). Although this process is usually automated anyway.

### Data Warehouses

Within a data warehouse, data is stored in a couple of tables

**Dimension Tables**

These are often de-normalised tables meaning that rows can contain multiple values and often redundant data, just used for faster lookup.

A dimension table can be represented in an ERD as a **Square that connects to a diamond**. Where the diamond is the fact table.

The attributes are usually quite descriptive, based on a strong entity from an ERD and is just used as a lookup table (i.e Customer, Produce, Student).

```
CUSTOMER (CustID, CustName, City, RegionName, RegionManager)
PRODUCE (ProdID, ProdName, ProdCategory, Price, Tax)
```

These tables are not completely normalised, usually in 2nd NF meaning they still contain non-key dependencies. It is fine for a data warehouse to store this type of data as it is seen as static data that rarely needs to change. (historical data)

An advantage of storing redundant data is that there are less joins required to gather the data as it is not split into different tables making it faster.

**Fact Tables**

These tables contain data that users may want to quantity or aggregate.

A fact table is represented in an ERD as a **diamond**.

Fact tables contain more weak entity or transactional data, such as Events and Activites, Sales, Purchases, Results

```
SALES (CustID, ProdID, SaleDate, Qty, SellingPrice);
```

The fact tables should refer to the dimension tables. Where each fact table can refer to a separate dimension table.

If we end up drawing the data warehouse ERD, the Fact Tables will be the child entities of the Dimension tables.

### Big Data

The 4 V's of Big Data

- **Volume**

Storage is now cheap, so we have so many more sources for data to come from, Internet, Social Media, Machine Data, etc.

- **Velocity**

The speed in which data arrives, online shopping, social media, all these data transmission is a matter of how organisations can react to how fast the data is coming in.

- **Variety**

Data comes in all different shapes, sizes and types. Images, Documents, Videos, etc. How do we store it all and how to analyse it all.

- **Variability of Data**

The velocity or speed that the data comes in can vary, look at Twitter for example when events happen that trend to when its quiet. How does an organisation handle these loads.

The problem with big data is how to store it so it is accessible and analysable quickly. Raw data on its own is somewhat useless, we need to structure it so that it makes sense to allow us to make concious business decisions.

### Business Intelligence Tools

There are tools available to analyse big data, Cognos, Jedox, Power Pivot.

These tools provide capabilities of data mining, visualisation, analytics and preditive analytics.