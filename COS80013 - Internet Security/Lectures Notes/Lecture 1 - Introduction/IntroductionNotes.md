# Introduction to Internet Security

Information technology encompasses everything to do with computers and the infrastructure -

- Desktops, Laptops
- Web Servers, File Servers (FTP), Database Servers
- Routers, Switches, Hubs and other pieces of hardware.

Anything that is essentially a computer is IT.

## What is Internet Security

There are several subclasses of security that make up Internet security.

**Information Security** - The process of keeping information private, unaltered and available.

**Computer Security** - The process of keeping IT devices (phones, computers) free of malicious code.

**Network Security** - The the process of keeping communications between IT devices private, unaltered and between authorised parties.

The meaning of "unaltered" would be that the data is not changed from one end to the other, or not intercepted and changed by a third party.

## CIA (Confidentiality, Integrity and Availability)

Does not mean **Central Intelligence Agency**, but instead is a term that comes from the Information Systems Industry

### Confidentiality

- Ensures that only the intented parties have access to the information.
- Technologies - Authorise, encrypt, access control, authenticate and restrict physical access.

Evesdropping is a type of threat that can erode confidentiality.

### Integrity

- Ensures that the information does not change and changes are immediately detected.
- Technologies - Data Backup, checksum, hashing, correction code.

Unauthorised alternation of data is a type of threat that can erode the integrity of a system.

### Availability

- Ensure that information is available to read/write to those who are authorised to do so without failure.
- Technologies - UPS, redundant network connections, data recovery, disaster planning.

Fail Open Authentication - It is the situation when user authentication fails but you are still allowed access to sections of a web application. These systems have allowed hackers to bypass certain authentication systems and crash the website.

DOS (Denial of Service) attacks is a type of threat that can prevent information being available.

## Trust (Assurance)

Is the ability for a system to trust that the information and behaviour is correct.

Technologies that build trust - Certificates, SSL and other authentication systems.
Technologies that erode trust - Spoofing, spam, phishing.

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

The idea will be protect all levels of a IT system from the data -> application -> OS -> network so if one breaks, the system is still somewhat secure.

###Probability Risk Analysis is the process of evaluating the risk that a target will come under attack. The probability is determined based on historical data of other similar attacks against the target

**Risk Factor = Probability of Risk * Probability of Severity** for the target, and then allocate a budget to protect against it. 

This calculation is generally not too helpful because attackers will use the same calculations and target the least probable targets as they will have the least budget set aside to protect against an attack.

###Perimiter Security

The process of adding a firewall to a network to prevent attackers from breaching the network. It generally does not work because once something passes the firewall then the whole system is vulnerable, in addition to many people in organisations that do not follow the process.

###Security Policy

The process of educating users of what they can and cannot do in a system. It reduces the changes of the network being comprimised if people are educated against it.

###Access Control

Enforcing security by only allowing access to certain parts of the system. This involves setting read/write access on accounts and things like that. Only allowing adminstrator access to install or update software, so general users do not have universal access.

###Reactive Security/Black Listing

Is the process of adding rules, blocking ports and black listing known threats of websites. This is useful in most cases but sometimes does not work because 0-day attacks with new threats will not be known ahead of time and therefore not added to a blacklist.

###Proactive Security/White Listing

Implementing rules that by default will block all unknown things/software until it has been authorised by the administrator. Authorised software or ports are added to a white list so that only things coming in that are on that list are allowed through.

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