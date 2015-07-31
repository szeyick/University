# Introduction to Internet Security

Information Technology (IT) encompasses everything to do with computers and their infrastructure -

It can include:

- Desktops, Laptops, Tablets, Phones.
- Web Servers, File Servers (FTP), Database Servers.
- Routers, Switches, Hubs and other pieces of hardware.

In other words, anything that can basically function as a computer or be part of a computer is considered as IT.

## What is Internet Security?

There are several subclasses of security that make up Internet security (IS).

**Information Security** - The process of keeping information private, unaltered and available.

**Computer Security** - The process of keeping IT devices (phones, computers and other hardware) free of malicious code and secure.

**Network Security** - The process of keeping communications between IT devices private, unaltered and between authorised parties.

The meaning of "unaltered" is that what is sent is what is received without being changed in between people with mallicious intent.

## CIA (Confidentiality, Integrity and Availability)

Does not mean **Central Intelligence Agency**, but instead is a term that comes from the Information Systems Industry

### Confidentiality

The information being sent is only visible to those that are authorised to have access to it. **Evesdropping** is considered a type of threat that can erode confidentiality in information, since if someone else hears about it, how secure if your data?

Technologies that can assist in keeping information confidential are - **authorisation**, **encryption** and **access control** 

### Integrity

Ensures that the information does not change between sender and receiver. If it is changed it should be immediately detected.

Technologies that can assist in keeping the integrity of data are - **data backup**, **hashing**, **checksum** and **correction code**

Unauthorised alternation of data is a type of threat that can erode the integrity of a system.

### Availability

Ensure that information is available to read/write to only those who are authorised to do so. This means to set the correct read/write access priveledges on files/folders on a system to prevent unauthorised modification.

Technologies that can assist in keeping the availability of information are - **UPS**, **data recovery**, **disaster planning**.

"Fail Open Authentication" - is a situation when user authentication fails but the user is still allowed access to sections of a web application. Systems designed with fail=open authentication have allowd hackers to bypass authentication systems and enact DOS attacks on a website to crash it.

## Trust (Assurance)

Is the ability for a system or individual using a system to trust the information it receives and the behaviour of the system is correct.

Technologies that can build trust are - **certificates**, **SSL** and other type sof authentication systems.

Spoofing, spam and phishing are types of threats that can erode trust in a system.

## Repudiation (Authenticity)

The way of enforcing commitments, contracts and agreements. It isn't a system that is designed for the Internet as it cannot enforce this as with commerce, access control or uploads.

Denial of contract is a type of threat that can hinder the authenticity of a system.

## Privacy (Anonymity)

Users of the Internet have an expectation of privacy that is provided by a stateless client/server model. Internet access is considered as a basic human right and is a means of freedom of speech but is not governed by any single country.

## Security Solutions

A good security solution should follow these rules -

1. Simple - Easy to implement, understand and maintain.

2. Fail-Safe - Should not "fail-open" and provide unauthorised access to content that requires secured access to.

3. Comprehensive - No loopholes

4. Open Design - Verfiable code that allows bugs to be easily tracked.

5. Separation of Parts - Component design, meaning that failure in one section does not result in a failure of the entire system.

6. Principle of Least Priveledge - Only allow access to what is required to complete the task, not everything.

7. No shared authentication - Do not allow for sharing of logins, passwords.

8. Good usability - System needs to be intuitive and user friendly otherwise people will try to find ways around it.

9. Easy to protect, hard to break - Asymmetric warfare, is where one side is stronger than the other and the weaker side uses guerilla tactics to weaken the other side.

10. Traceable - Find it easy to detect attacks, identify the attackers. 

## Security Paradigms

The following sections takes a quick peek at what can be done to ensure a system is secure. There is no magic bullet when it comes to security, but implementing all of the listed features in the list below can ensure that the system is sufficiently secure.

The idea will be protect all levels of a IT system from the **data -> application -> OS -> network** so if one breaks, the system is still somewhat secure.

##Probability Risk Analysis

Is the process of evaluating the risk that a target will come under attack. This is calculated based on historical data on similar types of attacks against the target.

The sum is - 

**Risk Factor = Probability of Risk * Probability of Severity** for the target

Once the risk factor is calculated, an organisation will alloate a budget to protect against the possible attack.

Whilst useful to evaluate the chance of attack, it is not particular helpful since attackers may have access to the same data and target systems that rate low on the risk factor as they will have less budget assigned to protect against an attack.

###Perimiter Security

Is the process of adding a firewall or IDS (Intrustion Detection System) to a network to prevent attackers from coming in. This tactical generally will not work because the whole system becomes vulnerable the moment the attackers breach the permiter. 

In addition, there are organisations where to gain access to other parts of the internal network, the firewall must be disabled. This will raise the chances of the permiter being breached.

###Security Policy

A security policy is generally implemented in many organisations. It is essentially the process of educating users of what they can and cannot do on a system. By educating the users, it reduces the chances of the network being breached due to rookie mistakes.

###Access Control

Enforcing security by only allowing access to certain parts of the system. This involves setting read/write access on accounts, super user accounts, administrator accounts.

Only allowing general users to use the system instead of modifying its contents can prevent malware from being installed.

###Reactive Security/Black Listing

Is the process of adding rules, port blocking and black listing known threats to prevent users from accessing them. Torrent ports, pornographic websites are considered things that will be black listed. 

This is useful in most cases but does not prevent 0-day attacks as new threats will not be known ahead of time and cannot be added to a blacklist.

###Proactive Security/White Listing

Essentially the opposite of the above. Rules will be implemented to allow access to certain websites and ports whilst blocking everything else. Additional sites will only be added to the white list when it has been investigated and approved by an administrator.

This sometimes runs into problems with management in a business as it can impact the overall running of a company.

## Encryption

The process of securing data by scrambling its contents. It uses a set of encryption keys to scramble the contents which can then be unscrambled (decrypted) with the another key.

Algorithms are used to encrypt data and fall into two main techniques **Mixing (Shuffling)** and **Substitution (S-Box)**

Mixing is vulnerable to known text attacks, whereas Substitution is vulnerable to statistical methods.

The strength of an encryption is usually determined by the length of the key and the complexity of the encrypting algorithm.

**Symetric Key** - The same key is used to encrypt and decrypt the data but the problem will be how to get the key to the recipient.

**Asymetric Key** - One key is used to encrypt and another to decrypt. The problem here will be how to get the public key to encrypt from the recipient.

### Hashing

Is considered as a one-way encryption because once the data is encrypted with the hash, it cannot be reversed. Like its use in programming langauges, as hash can be used to uniquely identify something without looking at it directly.

### Certificates

Is a combination of hashing, symetric and asymetric crytography and a trusted party to authenticate.

A trusted party is not always trusted since it can be compelled by another organisation to lie (governments). This data can also be stolen also, and dodgy code can result in certificates being flawed.

## Usability

If security is too hard, then users will not find the system very useful. Such things as login in repetitively, password type restrictions and pass keys will make users not want to use the system.

To get around this, shared logins (Single Sign On), Cloud storage and other shared services are implemented but they still have their own flaws also.
