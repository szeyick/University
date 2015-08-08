##Lab Test 1

- **What is a "honeypot"?**

A honeypot is a term that is used to describe a deliberate trap that has been set up to lure attackers into a isolated and monitored area of a computer system or network. The lure of this trap is that it offers seemingly valuable information in an attempt to divert a attackers attention towards a part of a system that is controlled and closely monitored so any attempts of unauthorised access will be detected.


- **What is "blackholing"?**

Blackholing is a term that is used to describe the process of blocking IP addresses and domains to reduce the amount of potentially mallicious data from entering a system or network from those addresses and domains. Its intention is to block incoming data from known threats so as to not flood a networks traffic with spam that may originate from those addresses.

- **What is "air-gap security"?**

Air-gap security is a term that is used to describe how disconnected systems communicate with one another. Data transfer between these systems is performed by using a transportable device (Flash Drive, CD, Portable HDD). In addition to data transport from one device to another, there is often a strict process that needs to be followed to ensure that data is transferred safely.

It is a form of high level security designed to reduce the possible areas of vulnerability for attackers to gain access to a system and/or its data by literally creating a physical gap between systems.

- **What is a "one-time pad"?**

The term one time pad is used to describe an encryption technique where each character of a text message is encrypted with a corresponding character from a secret key. For example, to encrypt the nth character in the text message, the nth character in the secret key is used to encrypt the text. The two letters are added together to generate the cipher text, the reverse is done to decrypt the cipher text.

The contents of the key should be generated randomly and should be as long as the text message that it is to encrypt, this way each character in the text message is encrypted with a different character from the key. 

- **What does "zero-day attack" mean?**

A zero-day attack is a term that is used to describe a vulnerability in a piece of software that is unknown to its creator. This vulnerability can be exploited by attackers to conduct malicious activities before it is discovered, patched and corrected by the software developers.

- **What are "ARP and DNS cache poisoning"?**

DNS cache poisoning is a term that describes the process of illegally modifying a DNS server's records to replace the existing IP addresses with a set of IP different addresses. The result of this is that visitors to a website that has had its records modified will be re-directed to the site which the hacker has defined.

ARP (Address Resolution Protocol) poisoning is a term that describes the process of illegally changing the MAC address (Media Access Control) of computer or server on a network to redirect network traffic to the attackers computer. This results in communications on the poisoned network to be passed through to the attackers computer allowing them to retrieve the contents of the incoming/outgoing messages.

- **What is the purpose of the NSA PRISM program?**

The NSA PRISM program is a US government surveillance program that is designed to collect data about individuals from their Internet communications (i.e. email, online chat logs, images, documents). The data is provided by large US technology companies such as Google, Facebook, Microsoft and Apple. 

It is a highly controversial program because the surveillance is conducted without the knowledge of the individuals and there is little information on how the program operates as it could be used to specifically target individuals or groups without any legal justification.

- **In 2001 Bruce Schneier said "Complexity is the enemy of security". Explain what he meant.**

Modern day computer systems are becoming increasingly complex in their design and function. The result of this is that there are more lines of code and infrastructure in place to create, manage and maintain these systems. The problem with this from a security perspective is that, as the complexity of a system increases, the possible number of vulnerabilities within the system will also increase. Thus the task of finding and correcting security flaws in a large complex system also becomes a large complex process.

- **Explain the term "Defense in depth".**

The term defence in depth refers to the process of implementing multiple layers of security to protect information in a system. The security measures in this type of system are like an structured like an onion, where breaching each layer only exposes another layer of complex security.

The purpose of this is to avoid the scenario where a single breach exposes the entire system. Its design to address multiple types of vulnerabilities in a computer system that can involve both technology and personnel.

- **What is the "Principle of least privilege"?**

The term principle of least privileged is used to describe the idea of only providing a user with enough permissions to access the information and resources that they require to complete their task. The purpose of this is to not allow a user to potentially access areas of a system that they are not permitted to.

- **What is meant by "Security by Obscurity"?**

The term security by obscurity describes the process of keeping a systems design and architecture a secret and only known to those who are authorised to know about it. The idea behind this is that the less people that know about a systems design and security flaws, the less likely information regarding vulnerabilities will be leaked out and exposed by attackers. If an attacker does not know about potential areas of vulnerability, then they will not know where to begin to target a system.

- **Define and describe an "MITM" attack.**

A MITM (man in the middle) attack is a term that is used to describe where an attacker intercepts communications between two parties without their knowledge. The attacker sits in the middle between the two parties, intercepts the message, before forwarding it onto the intended target. 

- **What is a "cryptographic key" and how is it different to a "cryptographic hash"?**

A cryptographic key is used by a cryptographic algorithm to encrypt or decrypt a plain text message. As long as this key remains private, it ensures that communications remain secure. There are two types of cryptographic keys, symmetric and asymmetric. An symmetric key describes a an encryption key that is used to both encrypt and decrypt a message, whereas an asymmetric key is where a different key is used to encrypt and decrypt a message. 

A cryptographic hash is a function that a message is passed through to ensure its integrity. Before a message is sent, it is passed through a hash function to generate a value for the message. When received, the message is again passed through the same hash function, the outputted value on the receiving end should match the one generated by the sender. If the values do not match, then it would imply that the message has been altered between the sender and receiver.

The difference between a cryptographic key and a cryptographic hash is that the cryptographic key is used to encrypt and decrypt a messages contents, whereas the cryptographic hash is used to validate the messages integrity and does not necessarily care about the messages contents.

- **Define the term "exploit".**

The term exploit is used to describe the methods used by attackers to gain unauthorised access to a computer system.

- **What is a "vulnerability"?**

The term vulnerability is used to describe a flaw or weakness in a system that can leave it exposed to an attack.

- **In 1989 Clifford Stoll wrote a book called "The Cuckoo's Egg". Explain the relevance of the book's title.**

A cuckoo is a type of bird that lays its eggs in another birds nest so that when it hatches it is cared for by its foster parents without their knowledge. This concept is similar to that of an malware program that is installed on a computer system that is designed to take the place of another program. Users of the computer are not aware that this change has been made and continue to use the infected machine, unaware that the installed malware may be monitoring their activities.

- **Define dictionary attacks and brute force attacks. How are they similar, and different?**

A dictionary attack is a term that is used to describe the method of systematically entering words from a dictionary as passwords in an attempt to break into a password protected computer. The words in the dictionary usually comprise of well known and commonly used words that people may use as passwords. 

Dictionary based attacks can be successful due to people generally using easy to remember and common words as passwords.

A brute force attack is a term that is used to describe a trial and error based approach to gain access to a system or information. In this approach, an attacker generates all possible permutations of a set of word combinations in an attempt to enter the combinations one by one until the right combination grants access to the desired information.

A dictionary attack shares similarities with a brute force attack as it follows the same process of trial and error until the right dictionary word is used to gain system access. The way that it differs from a brute force attack is that a brute force attack does not use a predefined list of common words and instead uses all possible combinations of characters that can fit into the password.

- **Explain the difference between a penetration test and a security audit.**

Penetration testing is a type of testing that is conducted by an organisation that simulates malicious attacks to its systems to test the strength of its security. The result of this is to determine if there are any potential vulnerabilities that may exist that can be exposed by real attackers.

A security audit is where an organisations current security measures and processes are evaluated and reviewed to assess whether they are at an adequate level to protect the organisations data and IT assets. The scope of the audit and its pass rate is determined beforehand so as to determine definitively whether the security audit passes or fails as soon as all IT assets are reviewed

The difference is that a penetration test, tests the integrity of an organisations system to find potential vulnerabilities and a security audit reviews the current systems and processes to determine if it meets a predefined criteria.

- **Explain what is meant by the term "Perfect Forward Secrecy".**

The term perfect forward secrecy is used to define the process of generating a new key for every session of communication (data transmission) between two parties such as a browser and a web server. The keys are randomly generated so should one session key be compromised, then it is only the messages sent during that session using that key that are compromised as the key cannot be used to generate a possible future key.

- **What is a "replay attack"?**

The term replay attack refers to a type of network attack where valid data is intercepted by an attacker and reused later to trick the intended recipient into thinking that it is from the same originator. It is a method of falsifying an identity with legitimate credentials to gain access into a system. 

An example of a replay attack would be intercepting a user name and password combination that allows a user to log into a network. The credentials are stored to be used again at a later stage by an attacker to gain access into the same network to conduct their malicious activities.