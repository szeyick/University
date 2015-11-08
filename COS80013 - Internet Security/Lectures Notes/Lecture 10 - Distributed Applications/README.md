## Distributed Applications

### SPAM

It unsolicated email that has been distributed to a wide range of people. It is used for data collection for a bunch of purposes such as identity theft, phishing, advertising, malware distribution (open this file!).

In the early days, SPAM had obfuscated headers that can from random addresses, however these days bots take over public email services to send more legit emails (Yahoo, Gmail, Hotmail).

Spammers would likely send their emails out during the weekend when network adminstrators are not working so that their mail servers will not be blocked for a few days.

### Spam Bot

Its a type of program that is used to send spam. It can create email addresses and search the web for targets. More advanced once can crack passwords, and install malware on other hosts.

### Tracing Spam 

You can identify spam origins by the path that it takes to travel to your inbox. In every email there is a header listing the names and IP addresses of each mail server it has passed through on the way.

You can verify the sender by doing a reverse DNS lookup on their IP. You can then look at a IP on the web to see if it has been black listed as a spam server.

The **Received: from** section in an email header, allows you to trace back to see the path it has taken to travel to you. The first one would be the origin point of the user to sent it.

### Message Source Code

Within the message source you may sometimes see obfuscated code, you can see what it is and sometimes it can be harmful.

### Black Lists and Open Relays

System administrators can subscribe to **black lists**, these are just lists of places that have been identified that produce spam.

**Open Relays** are public anonymous email servers that allow anyone to send email. This is sort of like what Bit Torrent used to do to allow you to send emails from random addresses. They do not always have to be mail servers as individual computers that are attacked can also suffer the same problems and be used as a spam relay or origin source. This may lead to your email account or personal server being black listed.

### CAPTCHA (Completely Automated Public Turing Test to tell Computers and Humans Apart)

Used to try and stop bots from being able to authenticate themselves. It is usually a picture or something that you need to enter along with validating yourself. 

It is possible for a bot to avoid it by tricking a reaul user to decode the puzzle for them, especially if the puzzle has been generated through JavaScript.

### Email Harvsesting

Collecting emails is part of an enumeration process as they can reveal usernames and domain names.

They just go through the web looking for email addresses and emails are then sent to the ones that are found.

It is also possible for a Spam Bot to randomly generate emails to send spam to them, if it records that a user has responded to a link within the email then it can sell the email address. 

### Spam Filtering

Being able to filter a legitimate email from a spam email is difficult. It often involves keyword matching (pattern matching) and machine learning techniques to identify consistent structures within spam.

### SQL

DDL - Data Definition Language used to create the tables.
DML - Data Manipulation Language used to retrieve update and delete data from the table.

For websites that run backend databases, they can be succeptible to **SQL Injection**. It is basically a database attack that allows SQL code to be injected into a query to cause a buffer overflow to execute the entered code.

This occurs when you do not sanitise the input before passing it into the SQL command string. You can prevent injection attacks by only using stored procedures (views), sanitising the input to prevent raw input from the user being placed into the database.

### Database Security

Databases have systems in place to maintain privacy, availability and integrity.

**Two Phase Committ** - 

Allows simultanous writing without risk of corruption. 

The first **request phase** uploads the proposed changes and locks the records, if it cannot lock it will discard the changes.

The second **commit phase** changes the records its locked and returns a success code, if it goes wrong it'll revert.

### Database Access Control

DBMS's can use ACL or permission attributes to control who has access to what in the database. You can create Views in SQL that allow you to do that. 

You can use DAC (Discrentary Access Control) to allow a user to allow other users to access a view as its "owner" but only those views.

### Database Encryption

You can create a symmetric key by hashing the users password, adding salt. The input data can then be encrypted with the key, but never store the key in the database.

This means that each user has a different key that is only assigned during the session. This is a relatively safe way to create and store a symmetric hash but it is always possible for a hacker to get the key from stealing the session data.

You can also use public key cryptography that uses a randomly generated symmetric key. You can encrypt the symmetric key with a public key and store that in the database with the data. The private key can then be used to decrypt the symmetric key and read the data.

However because this will result in unique encryption for all users, the admin cannot recover the data. However again it is also not safe to store the key or symmetric key in the database.

### Email Security

Email requires three main protocols, clients and mail servers.

The protocols are - SMTP, POP3 or IMAP.

POP3 or IMAP is used in communications between the Mail Server and the Mail Client, whereas SMTP is used between the Mail Server and the Internet. The Mail Server is the one that is responsible for deliverying the email.

To ensure email privacy we should encrypt the message, digitally sign it, uses public keys or certificates to verify and hashed.

Authenticating a user should rely on public key cryptography. This means that everyone needs a certifiate but again this isn't used all too often.

Authenticating a user within an organisation is easier as the certificate is embedded in the gateway so it is easier to implement.

### Sender Policy Framework

The SPF field in the DNS record can be used to authenticate an email server. However it is easy to spoof and does not check the message contents for integrity. It also does not provide encryption nor support mail forwarding.

### Digital Rights Management (DRM)

Protects the rights of copyright holders, film companies, games companies.

It restricts the rights to copy, sell, broadcast content (like on CD's, DVD's).

DRM schemes incldue copy protection, not allowing CD drives to read on PC's, and scrambling of data.