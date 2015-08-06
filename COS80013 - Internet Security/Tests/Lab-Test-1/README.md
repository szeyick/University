##Lab Test 1

- **What is a "honeypot"?**

A honeypot is a term that is used to describe a deliberate trap that is set up to detect unauthorised attempts to access sensitive information. The trap is designed to attract attackers towards it by seemingly offering valuable information, but its location and/or contents are isolated and monitored.  

- **What is "blackholing"?**

Blackholing is a term that is used to describe the process of blocking IP addresses and domains to reduce the amount of potentially mallicious data from entering a system or network from those addresses and domains. Its intention is to block incoming data from known threats so as to not flood a networks traffic with spam.

- **What is "air-gap security"?**

Air-gap security is a term that is used to describe how disconnected systems communicate with one another. Data transfer between these systems is performed by using a transportable device (Flash Drive, CD, Portable HDD). In addition to data transport from one device to another, there is often a strict process that needs to be followed to ensure that data is transferred safely.

It is a form of high level security designed to reduce the possible areas of vulnerability for attackers to gain access to a system and/or its data by literally creating an "air-gap" between systems.

- **What is a "one-time pad"?**

**Re-write**
In cryptography, the one-time pad (OTP) is an encryption technique that cannot be cracked if used correctly. In this technique, a plaintext is paired with a random secret key (also referred to as a one-time pad). Then, each bit or character of the plaintext is encrypted by combining it with the corresponding bit or character from the pad using modular addition. If the key is truly random, is at least as long as the plaintext, is never reused in whole or in part, and is kept completely secret, then the resulting ciphertext will be impossible to decrypt or break.[

- **What does "zero-day attack" mean?**

Zero-day attack is a term that describes a threat to a piece of software that exposes a vulnerability that is presently unknown. It is the time before the vulnerability is discovered, patched and addressed by the applications developers.

- **What are "ARP and DNS cache poisoning"?**

DNS cache poisoning is a term that describes the process of illegally modifying a DNS server's records to replace the existing IP addresses with a set of IP different addresses. The result of this is that visitors to a website that has had its records modified will be re-directed to the site which the hacker has defined.

ARP poisoning is a term that describes the process of illegally changing the MAC address (Media Access Control)  

**Re-write**
ARP poisoning is very effective against both wireless and wired local networks. By triggering an ARP poisoning attack, hackers can steal sensitive data from the targeted computers, eavesdrop by means of man-in-the-middle techniques, and cause a denial of service on the targeted computer. In addition, if the hacker modifies the MAC address of a computer that enables Internet connection to the network, access to Internet and external networks may be disabled.

Address Resolution Protocol poisoning (ARP poisoning) is a form of attack in which an attacker changes the Media Access Control (MAC) address and attacks an Ethernet LAN by changing the target computer's ARP cache with a forged ARP request and reply packets. This modifies the layer -Ethernet MAC address into the hacker's known MAC address to monitor it. Because the ARP replies are forged, the target computer unintentionally sends the frames to the hacker's computer first instead of sending it to the original destination. As a result, both the user's data and privacy are compromised. An effective ARP poisoning attempt is undetectable to the user. 

- **What is the purpose of the NSA PRISM program?**

The NSA PRISM program is designed to collect data about individuals from their Internet communications (email, video chat, photos, online files). The data is provided by large internet companies such as Google, Facebook, Microsoft and Apple. 

Legally, the data retrieved must match approved search terms but could technically be able to monitor any particular citizen or groups of individuals. It is supposed to retrieve data from targeted individuals.

"specifically authorizes intelligence agencies to monitor the phone, email, and other communications of U.S. citizens for up to a week without obtaining a warrant" when one of the parties is outside the U.S"

 The program is controversial because of its potential use to target American citizens or individuals that reside in the United States. 

- **In 2001 Bruce Schneier said "Complexity is the enemy of security". Explain what he meant.**

The more complex a system gets, the harder it is to secure. With too many “moving parts” or interfaces between programs and other systems, the system or interfaces become difficult to secure while still permitting them to operate as intended.

The Internet and all the systems we build today are getting more complex at a rate that is faster than we are capable of matching. So while security in reality is actually improving but the target is constantly shifting and as complexity grows, we are losing ground.

- **Explain the term "Defense in depth".**

Defense in depth is the act of using multiple security measures to protect the integrity of information. This method addresses vulnerabilities in technology, personnel and operations for the duration of a systems life cycle.

This is simply the use of multiple layers of defense placed throughout an information system. With multiple layers the outermost layers are the first line of defense and work well against the smallest and most common attacks. More powerful and unconventional attacks get past the first few layers but will be stopped by the deeper, more concentrated layers. The concept was conceived by the National Security Agency (NSA) as a comprehensive approach towards information and electronic safety. 

- **What is the "Principle of least privilege"?**

The principle of least privilege (POLP) is an information security term that refers to a design objective in computing that a given user should only be able to access the information and resources he or she requires for legitimate reasons

The user or component starts off with no privileges and is only given those considered necessary. While this seems like common sense, in reality the practice is difficult to achieve because more security systems assign rights based on membership in groups rather than as individuals. Steps for implementing POLP include: Map job functions privileges Avoid assigning privileges directly to a guest or the public Untangle the web of user entitlements Monitor privileges 

- **What is meant by "Security by Obscurity"?**

Security through obscurity (STO) is a process of implementing security within a system by enforcing secrecy and confidentiality of the system's internal design architecture. Security through obscurity aims to secure a system by deliberately hiding or concealing its security flaws.

STO is based on the idea that any information system is secure as long as security vulnerabilities remain hidden, making it less likely that they will be exploited by a malicious attacker. Obscurity means keeping the underlying system’s security loopholes a secret to all but the most important stakeholders, such as key developers, designers, project managers or owners. Typically, a hacker’s approach in exploiting a system begins with identifying its known vulnerabilities. If there is no public information on those weak areas, hackers will find the system more difficult to penetrate and will eventually delay or postpone its malicious objective.

- **Define and describe an "MITM" attack.**

A man-in-the-middle (MITM) attack is a form of eavesdropping where communication between two users is monitored and modified by an unauthorized party. Generally, the attacker actively eavesdrops by intercepting a public key message exchange and retransmits the message while replacing the requested key with his own. 

The MITM intercepts communications between two systems and is performed when the attacker is in control of a router along normal point of traffic. The attacker in almost all cases is located on the same broadcast domain as the victim.

- **What is a "cryptographic key" and how is it different to a "cryptographic hash"?**

A cryptographic key is a string of bits used by a cryptographic algorithm to transform plain text into cipher text or vice versa. This key remains private and ensures secure communication.

Cryptographic keys are symmetric or asymmetric. Symmetric encryption requires only one key, which is used to encrypt and decrypt data. Asymmetric encryption uses two different keys: one for encryption and one for decryption. A certificate authority (CA) provides public/private key pairs using the public key infrastructure.

A cryptographic hash function is a type of security mechanism that produces a hash value, message digest or checksum value for a specific data object.

Cryptographic hash functions are implemented in information security to evaluate the integrity of data, authentication control and other security mechanisms. Cryptographic hash functions work by generating the checksum value of a data object. If the data is intentionally or unintentionally modified, the checksum value is changed.

- **Define the term "exploit".**

An exploit is a general term for any method used by hackers to gain unauthorized access to computers, the act itself of a hacking attack, or a hole in a system's security that opens a system to an attack.

The term is very flexible and can be used both as a noun as a verb. As a noun, the exploit is the hole in the system that the hacker used to make the attack. Quite often, this is an OS vunerability from an unpatched server. As a verb, it refers to the act. For example, you might hear "the hacker posted details of his exploits on his blog to show just how easy it was to break into XYZ's servers."

- **What is a "vulnerability"?**

Vulnerability is a cyber-security term that refers to a flaw in a system that can leave it open to attack. A vulnerability may also refer to any type of weakness in a computer system itself, in a set of procedures, or in anything that leaves information security exposed to a threat.

Vulnerabilities a. re what information security and information assurance professionals seek to reduce. Cutting down vulnerabilities provides fewer options for malicious users to gain access to secure information.

- **In 1989 Clifford Stoll wrote a book called "The Cuckoo's Egg". Explain the relevance of the book's title.**

A cuckoo egg is a modified MP3 file that looks like a copyrighted song and is spread through the Internet without the consent of the copyright owner. The initial part of the song, typically the first 30 seconds, includes the original song. The rest of the song is replaced with a repetitive cuckoo clock sound or a combination of random voices that are not copyrighted. A cuckoo egg file also has the exact same playing time and file size as that of the original copyrighted MP3 file.

Cuckoo egg files are piracy deterrents that spread like viruses but do not harm computers.

- **Define dictionary attacks and brute force attacks. How are they similar, and different?**

A dictionary attack is a technique or method used to breach the computer security of a password-protected machine or server. A dictionary attack attempts to defeat an authentication mechanism by systematically entering each word in a dictionary as a password or trying to determine the decryption key of an encrypted message or document. 

A brute force attack is a trial-and-error method used to obtain information such as a user password or personal identification number (PIN). In a brute force attack, automated software is used to generate a large number of consecutive guesses as to the value of the desired data. Brute force attacks may be used by criminals to crack encrypted data, or by security analysts to test an organization's network security. 

Dictionary Attack: The attacker tries a list of known or commonly used passwords. Thus, s/he tries a list (dictionary) of passwords. Generally, dictionary attacks succeed because many people have a tendency to choose passwords which are short and easy to remember like superman, harrypotter, etc.

Brute Force Attack: Does not use a list of passwords; instead, it aims at trying all possible combinations in the password space.

In contrast to a brute force attack, where a large proportion of the key space is searched systematically, a dictionary attack tries only those possibilities which are deemed most likely to succeed

- **Explain the difference between a penetration test and a security audit.**

Pen-testing may be conducted to complement background investigations and ensure social engineering and networking safety.

Pen-testing is implemented by simulating malicious attacks from an organization's internal and external users. The entire system is then analyzed for potential vulnerabilities. A plan that communicates test objectives, timetables and resources is developed prior to actual pen-testing.

Every organization should perform routine security audits to ensure that data and assets are protected. First, the audit’s scope should be decided and include all company assets related to information security, including computer equipment, phones, network, email, data and any access-related items, such as cards, tokens and passwords. 

An information security audit occurs when a technology team conducts an organizational review to ensure that the correct and most up-to-date processes and infrastructure are being applied. 

- **Explain what is meant by the term "Perfect Forward Secrecy".**

Perfect Forward Secrecy (PFS) is a data encoding property that ensures the integrity of a session key in the event that a long-term key is compromised. PFS accomplishes this by enforcing the derivation of a new key for each and every session. 

PFS works on a very simple concept to ensure the safety of session keys from future exploitation by hackers. Encoded messages are encrypted using sophisticated mathematical formulas, where decryption would require massive processing, making building practically impossible with the current computing architecture. However, a hacker theoretically could save an encrypted message with the intention of decrypting it in the future, perhaps when greater computing power is available. PFS is designed to eliminate this threat by periodically creating new keys. So, even if a hacker exploits a private key in the future, he is unable to decrypt previously transmitted messages. 

- **What is a "replay attack"?**

A replay attack (also known as playback attack) is a form of network attack in which a valid data transmission is maliciously or fraudulently repeated or delayed. This is carried out either by the originator or by an adversary who intercepts the data and retransmits it, possibly as part of a masquerade attack by IP packet substitution (such as stream cipher attack).

Suppose Alice wants to prove her identity to Bob. Bob requests her password as proof of identity, which Alice dutifully provides (possibly after some transformation like a hash function); meanwhile, Eve is eavesdropping on the conversation and keeps the password (or the hash). After the interchange is over, Eve (posing as Alice) connects to Bob; when asked for a proof of identity, Eve sends Alice's password (or hash) read from the last session, which Bob accepts thus granting access to Eve.

A breach of security in which information is stored without authorization and then retransmitted to trick the receiver into unauthorized operations such as false identification or authentication or a duplicate transaction. For example, messages from an authorized user who is logging into a network may be captured by an attacker and resent (replayed) the next day. Even though the messages may be encrypted, and the attacker may not know what the actual keys and passwords are, the retransmission of valid logon messages is sufficient to gain access to the network.
