## Security Models

A security model covers the policies, procedures (how policies are implemented) and other security models that are implemented as part of an entire security system.

Security models provide a common language to define policies and procedures, used by security administrators and policy makers

### Policies

A policy defines what needs to be done and can include -

- **Subjects** - The people involved in the policy.
- **Objects** - The information or equipment required.
- **Actions** - What can and cannot be done.
- **Permissions** - The mapping of subjects, objects and actions together.
- **Protections** - The rules to implement that prevent breaching of policy.

In other words it just defines who does what with what.

### Discretionary Access Control (DAC)

This is where **users are allowed to set permissions on their own files and allow other users access**

This is similar to ACL (Access Control Lists) that are in Windows and Linux. Basically it is the file and folder permissions that you can set that can allow access to personal drives. Just like the website page on my home drive at work.

### Mandatory Access Control (MAC)

This is where **no users are allowed to set permissions, and a central authority sets them for you**.

Here we have **rule sets** that **map subjects (people) to objects (files, folders)**. In other words, the security administrator provides access rights to what you can and cannot do, such as remove adminstrator priveledges to stop you from installing programs.

This is an implementation of the "Trust No One" methodology where you think everyone is out to do evil things.

### Trust Management

Is a form of security policy where we define the following -

- **Actions** - The sensitive operations
- **Principals** - The actors (people involved)
- **Policies** - The rules that maps actions and principals (Similar to what Mandatory Access Control Does)
- **Credentials** - Digitally signed documents that map the allowed policies.

### Bell-La Padula Model

This security model **ensures confidentiality**. It does this by having multiple levels of classication for such things like documents (i.e. Unclassified, Confidential, Secret, Top Secret).

Then to access these documents you need to have a certain level of access clearence. Sort of like defence clearence to access certain types of classified documents.

The clearance levels are assigned per user, where you cannot read documents at a higher level than you're allowed to but also at the same time not allowed to write data to documents at a lower level. The reason for this is that false inputted data can affect what the people higher up can read which can result in poor uninformed decision making.

### Biba Model

This security model **ensures integrity**. It is based on multiple levels of **accuracy (integrity)** for objects (resources). For example this can mean that a document is more accurate at a data centre than it is on someones laptop.

The levels of integrity function similar to that of the access clearance, the higher the level the more accurate the data is considered to be. This is useful for policy makers, security adminstrators. Lower levels of integrity are reserved for people who do not care about the accuracy too much such as public access.

The Biba model provides progressively less reliable classifications of data, where users cannot write data to a higher level, and you cannot read data at a lower level.

It means that only people can make changes to the data at their own level, as their data cannot be trusted at higher levels to be accurate. In other words, unrealiable data cannot be made reliable.

### Other Security Models

Other security models that exist are the **Low Watermark Model** which is a relaxed form of the Biba Model. It allows users at higher levels to read data at lower levels even if it is unreliable.

There is also the **Clark Wilson Model**, that checks data integrity based on transactions. These transactions can be audited separately to ensure validity.

### Chinese Wall Model

It is a policy model that prevents conflicts of interest. People and resources are placed into the "Conflict of Interest" classes, meaning that people can only access what is in their class. However the person can be re-assigned between different classes.

### Trusted Systems

These are implemented using ACL, MAC, Bell-la Puda, where users are authenticated and given or restricted access.

It also provides secured hardware, AirGap protected by rings of security (defence in depth).

### Issues with AirGap

Because AirGap systems are isolated from a network, it is difficult for them to receive system updates. This is also applicable for mission critical systems that are supposed to never be rebooted. Meaning that for airgap systems, once you get through the initial permiter and gain access to the physical hardware it is easy to break into.

### Vulnerability Assessment

You access the security of your network and stuff by using a **Black Box** or **White Box** assessment.

Black Box assessment assumes that you know nothing about the internal workings of the system, the only information you have to go with is the documentation and binaries and it is your job to evaluate its security processes. A black box assessment would simulate a real attack as they would have no knowledge of the internal workings of your system.

White Box assessment however you get assess to the code, design documentation and it is the job here to use that to find vulnerabilities. Because you have access to the source code and system design documents, it can uncover zero day flaws.

Static Analysis is where you just look at a snap shot of the system, look at the source code, data and use tools to see if you can uncover potential flaws. It is done on a system that is not running so it isn't so good at unconvering architectural problems or processing problems.

Dynamic Analysis however is performed on a running system, where you look at the performance using tools to monitor VM's, network traffic and such. You can also use fuzz testing (inputting massive amounts of data) in an attempt to crash or find flaws int it.

### Disclosure

From vulnerbility assessments it is up to you what you do with the results that you find. There are two type of disclosure, responsible disclosure and full disclosure.

Responsible disclosure is where you reveal what vulnerbilites to the vendor, where you do it for a reward or just for the kindness. You can also suggest fixes to the vendor.

Full disclosure is where you make the results of what you find immediate which forces the vendor to update. This is seen as not as great as hackers can also gain access to this information.

### Secure Administration

To ensure that you have a secure system, implement the right security model.

Use the principle of least priveledge (only access to what you are allowed to), choose a correct access control system, passwords and crytography. Basically use common sense when it comes to implementing security.

### Physical Security

This is just all the hardware that involves computer systems (CD, USB, Servers, Doors, Locks etc)

The idea with physical security is to minimise the area of attack. Design it in such a way that if a section is compromised, the compromised area is as minimal as possible. 

Implement DMZ, Internal Firewalls, AirGap, SPAM filtering, etc.

### Network Auditing

Within a business you would need to look at the password policy, how are the passwords implemented, SSO, strong passwords, and what happens if users are locked out, how are they recovered.

### Penetration Testing

It is a simulated attack on a network or computer system, either through a blackbox or whitebox means. Usually this is done by legal means and not by hackers.

Penetration testing strategies can include -

- Enumeration of passwords, network reconnaissance, IP scanning, port scanning and DNS records.
- Network vulnerability analysis, port scans, footprinting to find vulnerable services and ports.
- Web Application Testing, SQL injection, XSS testing and fuzz testing

### Kerberos

It is an authentication protocol and has a software suite to implement it.

It uses symmetric cryptography to authentice clients to services and vice versa.

Windows servers actually use Kerberos to authenticate along with Active Directory as a means to centralise user information. 

It can also be used to authenticate machine to machine login through a local network, authenticate to web services, emails and printers. Basically it can be used as a means to authenticate a user to a any type of service.

Kerberos uses **tickets** to identify users. These are digical documents that will store a session key.

During authentication, the client receives a **ticket granting ticket** which is a global ID, and a **service ticket** that authenticates a user to the selected service. 

In other words, you get given 2 tickets, one to say that you have been issued a ticket, and the other the ticket to the actual place you're going to.

This system is run through a centralised place called a **Key Distribution Centre (KDC)** that contains the **Authentication Server** and **Ticket Granting Server**. Transmissions between the server and client are encrypted and compares password hashes rather than the password.

An advantage of this system is that because tickets are time stamped, they prevent playback attacks if someone were to obtain the ticket as it has a cache of tickets to see who has passed through and it uses symetric keys.

A disadvantage of this system is that because everything is run through the KDC if it goes down then noone can get into anywhere as they cannot authenticate. Also because it is all in one location, if the KDC is taken over then the attackers have access to everything. Also it requires everyone to be synchronised. 

### Encryption

Adobe and Microsoft offer password encryption for files.

Windows also has an encrypted file system (EFS) that uses public/private keys. Each document can store multiple copies of a decryption key each for different public keys to allow many users to modify the document (collaboration).

Windows EFS also provides Data Recovery Applets to allow documents to be recovered by decrypting. However the encryption only works on the files contents and not the filename or its metadata, so you can still see what the name of the document is and the meta data information.

Also the cached and temporary files are unencrypted and can be recovered, and it also uses the Windows password as part of the private key.

### Encrypting Hardware

- **Truecrypt** - can be used to encrypt a hard drive. 

- **Bit Locker** - Creates a plain text partition for the boot sequence and the rest of the drive is encrypted for the Windows data. It uses a **Trusted Platform Module**  to manage the keys, it is a piece of hardware.

- **Free Compusec** - Encryptes/Decrypts the read/write commands that go between the memory and hard drive. 

The limitations to the encrypting techniques listed above is that they keep a symmetric key in memory. It is possible to recover the password through RAM sniffing, to extract the key to retrieve the contents of the drive. However it does require physical access to the machine.

### Trust Platform Modules (TPM)

Is a small chip that is attached to the motherboard of a PC that contains a small amount of **eeprom** and has its own dedicated processor, random number generator and ROM. It is used for cryptographic functions that are supposed to be performed by the BIOS and OS and is delegated to the TPM.

This chip can be used to store fingerprint metrics, certificates, passwords and other things that it can be used for. 

It is a good solution however it is expensive and it uses copy protection so if you lose it or its faulty you cannot get the data off it.
