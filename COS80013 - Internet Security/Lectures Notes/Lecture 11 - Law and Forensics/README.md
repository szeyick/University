## Law and Forensics

### Law

Internet specific laws before 2000 were not really needed since most illegal activity online could be covered by non internet laws, such as fraud, harassment, theft, etc.

Identity theft however is an emerging problem so new laws need to be developed to catch up with the times. For example in Australia, a lot of the Internet regulations are related to media and communications.

With the increase dependency on the Internet, there are more ways for people to conduct illegal activites, such as cyber terrorism, black hat hacking, DDOS and extortion (i.e. Ashley Madison) are increasing. Even legimate claims of government activites can be seen as unlawful such as the data retention scheme and packet reading.

### Australian Law

In 2000 there were several laws that governed the use of copyright. You were allowed to photocopy copyrighted notes for "fair deals" such as study, research and reviewing.

ISP's were not responsible for the activities of their users and therefore were not responsible for their users copyright infringments. It was also OK to re-transmit and backup content.

A new cybercrime act introduced in 2001 updated the terms to include distribution through USB, network storage and WiFi, outlines to manage DOS attacks and the accidental breach of security was not always considered an offence.

But this act also allowed ASIS and DSD immunity from prosecution for just doing their job of spying and asking people to help them conduct their tasks. Unauthorised penetration testing was also made illegal unless with written permission.

The Spam act of 2003 outlined that Spam was illegal and emails sent must be authorised, and users must be given a link to opt out.

The Surveillance Act of 2004, allowed the police to use trojans, keyloggers and RATS to gather evidence.

The Copyright Law was ammended in 2006 to allow music and such to be recorded and played once, however lending of recordings was now considered illega. Backups of content were also allowed, along with backing up to devices.

A law in 2008 allowed employers to read their employees email without their consent, whereas previously only the police or spying agencies (ASIS and DSD) were allowed.

### Cybercrime Legislation Amendment Bill 2011

Allows agencies to request communications that phone companies store to be kept so they can be accessed with a warrant. It created more cooperation between overseas agencies for the investigation of cybercrime.

### Telecommunications Amendement Bill 2015 (Data Retention Scheme)

Request ISP's and Telco's to store metadata for 2 years, which includes sender, recipient, time of phone calls, email. Also to record their IP and the information gathered was to be shared between partnered agencies.

It was only to store meta-data and not actual emails, videos, or video conversations.

### US Laws

DMCA in 1998 protects copyright owners that prevents the fair use policy as we have in Australia. Making backups, reverse engineering was also considered as illegal.

### Patriot Act (2001)

Allows the US govnerment to monitor its citizens by tapping phone and internet without probable cause. So they just could just share your information with other agencies. 

Voice mail was re-classified as data allowing them to monitor your messages and also a "Canivore" was deployed at ISP's to search web traffic for key words and records who types them.

### Cyber Security Enhancement Act (2002)

Allows police and government agencies to tap phones and networks without a warrant and forces their ISP's to hand over customer private data.

### CAN-SPAM Act (2003)

Classifed that spam was legal so long as it had a legitimate sender name and address, that it was for commercial purposes, contained an opt out and was not a relay. 

This change in the definition of SPAM actually lead to the increase of spam.

This was again redefined in 2008 to better define the sender, and that the sender was responsible for the opt-out and was to include corporations.

### Cybercrime Act (2007)

Conspiracy is now an offence, and illegal action from a computer (sending spam, DDOS attacks) was considered illegal if it was conducted with more than 10 computers. This was introduced to protect people who had compromised machines from being charged.

Cyber extortion was added to the list of crimes.

### Cybersecurity Act (2009)

The President was given poweres to shut down internet traffic and the secretary of commerce was given the power to access anything regardless of privacy laws.

### Cybersecurity Information Sharing Bill (2014)

Allowed the NSA access to even more personal data. It also allowed sharing of data between government and private sector companies, but once this information was given to the government they could do whatever they wanted with it.

### Fraud

There are many times of fraud, usually for financial gain. Although these can include, eBay Scams, Nigerian Email Scams, Romance Scams. To prevent this, use some common sense.

### Ethics

There are many ethical dilemmas that network admins face when trying to figure out what is legal and illegal

- Reading user emails - Legal
- Usering remote desktop - Legal
- Printing kids assignments - Illegal
- Sending private emails - Illegal
- Downloading torrents - Illegal

Dilemmas will be faced when working with potentially illegal websites and being the adminsitrator for businesses that do shady things.

### Identity Theft

The attacker collects information about a victim through varoius means to impersonate them possibly to gain access to financials, passports. 

To prevent this just use common sense, and clean up after yourself online. If anything use TOR, VPN to hide your identity online. 

### Spam

Some companies who spam advertising and use browser hijackers claim that they are legal. But they do operate in a grey area, usually with the help of other parties.

### Stuff you can't do legally

You are technically not allowed to do the following -

- Perform a DOS on a spammer
- Release a virus that disables bots and zombies
- Release a worm that patches software vulnerbilities
- Contact an owner of a zombie.
- Penetration test your own network if it is on the Internet.

### Stuff you can do legally

You are legally allowed do the following -

- Block incoming packets from a known blacklisted IP
- Clean up someones computer
- Contact the ISP of a known spammer
- Report worms, torjans, scams and hijackers

### Forensics

The discovery and analysis of computer crimes, or crimes involving computers.

### Network Forensics

Capture packets, logs during the crime taking place. Also can do cloud forensics that will grab data from the cloud but requires cooperation

### Disk Forensics

Boot into the OS to aquire the drive or disk contents.

The idea is to retrive as much of the data as possible including the meta-data as it can tell you who did what, when and where.

### Memory Forensics

Retrieve the contents of the RAM and logging what is currently in the memory, network connections and traffic.

### Steps

The idea is to first secure the crime scene and record all the processes running on the computer.

Record the system time, partition details and drive mapping and try to take an image of the drive. If you cannot then use a "write blocker" that stops meta-data files from being changed.

**Toolkits** - There are a bunch of toolkits that can be used, Linux Distros, FTK (forensic ToolKit), (TSK, The Sleuth Kit), Autospy, etc.

- **1. Make a forensic copy of the drive**
- **2. Calculate a hash of the copy**
- **3. Record the system time and compare with the current time**
- **4. Impound the original drive as evidence**
- **5. Note down everything in the process**

### Analysis of Disk Image

- **Load the image and check the hash**
- **Search for deleted, renamed files**
- **Search for encrypted containers and search the drive for passwords**
- **Search for keywords in files**
- **Search the rest of the contents (email, cached websites, favorites, etc)**

### File Carving

- **Search for keywords in the deleted file space as things can still turn up when deleted**
- **Find sectors containing key words**
- **Find iNotes containing sectors**
- **Copy sector range to file and view it**

### What to do with evidence

It something is found, log it all and record the time and it helps establish a timeline of events.