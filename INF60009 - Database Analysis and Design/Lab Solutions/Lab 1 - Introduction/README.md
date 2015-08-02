## Introduction to Databases

The topics that will be covered in this lesson will be: 

- Data
- Duplicated Data and Associated Problems
- ISQL Jr
- Background Survey

### Questions

- Perform tutor and student introductions:

Not required

- What is data?

Data is seen as individual pieces of information that can be measured, collected and reported, and analyzed.

- Why do organisations need to store data?

Organisations will need to store data because it is considered as information and information is important. Data for an organistation can mean staff records, pay, inventory and other metrics. These will need to be stored in an ordely manner.

- How long should data be kept for?

It should be kept for as long as it is required, it cannot be kept forever since there is a physical limitation on space and also storage.

- List five groups of people who might need to retrieve stored data?

1. Government organisations - retrieving tax information, statistical data (sensus).
2. Employees - Retrieving personal details, pay slips.
3. Banks - Retrieve loan information for customers, bank account information.
4. Doctors - patient records, immunisation records.
5. Phone Companies - Call records, billing information.

- Have you ever used any type of database before? If so, provide examples.

MySQL.

- Download nad open the Excel file named INF10002_LAB1_SUB9001.xlsx

OK

- How many female students are enrolled in the subject SUB9001?

5

- What is phone number of convenor?

x5959

- Do any of the students have identical surnames?

Yes, Warren and Jane Jenkin, Ella and Jessie Hooper.

- If yes, is this a problem? Why/Why not

No having the same name should not be a problem because there is enough information in the spreadsheet to determine who is who even if the surnames are the same.

- Do any of the students have identical id numbers?

Yes, Sam Lockwood and Belinda Carlisle have the same ID numbers.

- If yes, is this a problem?

Yes, in a realistic scenario having the same ID would be a issue as they could not identify one from the other if there was only the ID as information.

- Do spreadsheets have any simple method to determine if a student ID is duplicated?

Yes, using conditional formatting and selecting the duplicate value option will highlight the cells which are duplicated.

- Download and open the files named INF10002_LAB1_SUB9022.xlsx and INF10002_LAB1_SUB9037.xlsx, INF10002_LAB1_SUB9048.xlsx. Select the View->Arrange All-> Tiled options.

- Student Nik Kaloper appears in more than one spreadhseet.

OK

- Are there any obvious problems with the data stored about Nik?

The studentID is different in each of the files they appear in.

- Do spreadsheets have any simple method to determine if a student id entered incorrectly?

- How many subjects are convened by Gina Davis?

2

- What is Gina Davis' office number?

BA1232 and BA1322

- Is the office number identical in all subjects taught by Gina? If not, how could such a mistake occur?

No they are not identical, the mistake could be caused by human error from data entry.

- If Gina Davis changed her office number to BA1020 how many changes would need to be made to the spreadsheets.

2 changes will need to be made, both to the rooms that she is in, for the subjects she teaches.

- Is it possible that Gina's office number details could be stored in more un-opened spreadsheets? How could you find out?

You could possibly write a macro to check each of the files to see if her name appears, then check the cell for the office number. However I believe this would only be useful if she was the convenor and that the layout of the files were the same. If they were drastically different then you may need to manually check them all.

- Is storing Gina's office number in a number of spreadsheets a silly idea?

Yes, because it doesn't seem neccessary. You could easily put it all in a single file, and just have her name as the convenor for the subjects she teaches. This would be the equivelant of using her name in the other spreadsheets as the foreign key which links to the primary key of a single file of all the teachers that teach subjects.

- **Data Redundancy** is caused by needlessly storing the same data item multiple times. **Data Inconsistency** occurs when conflicting versions of the same data appear in different places. What are the problems of having inconsistent data?

Having inconsistent data everywhere would make it hard to determine which data is correct.

- What are the benefits of eliminating data redundancy?

It reduces the number of places where changes need to be made if something changes. For example, in the Gina Davis example, if her name was on 50 files, then if there were changes to her phone number or room number, then all 50 files would need to be changed. Whereas if there was only a single master reference and the rest of the files only had her name or something simpler, then if the room number of phone number changes, only the master file would need to be updated.

### ISQL Junior

- Where is the data stored? 

On the Oracle server located on site.

- How can this data be accesed? 

By using ISQL Jr through a web browser and through SQL statements.

- Does this data need to be saved before you log off?

No, data stored in the DB is there permanently until commanded to delete (DROP TABLE)
