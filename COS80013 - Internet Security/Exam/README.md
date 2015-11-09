## Exam Topics

- **1. Protecting a web site**

BEAST - Browser Exploit that injects JS into the browser collecting headers, ciphertext to rebuild session ID.
CRIME - Injects JS to intercept compressed session cookies to send to user, sending different cookies bac to rebuilt.

Virus - A program that can copy itself without the users knowledge, usually infects through person to person.
Worm - A program that can copy itself without any human interaction and spread through a network.

IFrames - redirect to malware sites or download links.

Conficker Worm - Disables A/V, Windows Update, Packet Sniffers, filters other worms.
MyDoom - Emails itself around as an executable attachment, used to launch DDOS
Slammer - Performed DOS attacks on the Internet spreading through MSQL Server 2000 with UDP.

Trojans - A container that distributes the malware inside of it, maybe contained within a legit progarm (torrents, warez)
RootKit - A technology used by malware to evade detection, contains a bunch of patches to escalate priveledges, patching software to prepare for the installation of malware.

Browser Hijackers - Attach themselves through toolbars, media players, take over the browser by redirecting home pages.

Bots and Botnet - Remote control a computer, for spying DDOS, SPAM.
RAT - Remote Access Trojan includes backdoors onto servers for remote login. Can be used as part of a rootkit.

HTTP Sessions - Stateful information is passed back by a server through POST or GET. The browser sends information back each time including the cookie that the server remembers. Turn off JS, privacy plugins to stop cookies from being hijacked.

Cookies - Can be found using packet sniffing to take over the current cookie so the server doesn't know and lets you in anyway. Setting cookies to expire or using HTTPS (encrypted data) can mitigate against this.

- **2. Forensics**

Obtain evidence by locking down the crime scene securing computers, logging all computer activity with time stamps making sure that it does not switch of or self destruct.

Copy the hard drive contents and archive the old one as evidence.

Ethics, what are your responsibilities and are they legal.

Network Forensics - capture packet data and logs, record evidence whilst the crime is occuring.
Disk Forensics - get everything that is on the disk.
Memory Forensics - Retrieve the conents of the RAM, running processes and incoming and outgoing traffic and connections.

Check for evidence, deletion, hidden files, encrypted containers, keywords, emails, etc.
CHeck the hash to make sure it hasn't been modified (CHECKSUM)
File carving - retrieve deleted files and sectors.

Write everything that you found down along with dates and times, use wireshark to confirm timeline.

- **3. DDOS**

Sending massive amounts of pings can cause a DOS, since the server needs memory to respond to them all.

DOS - Send lots of SYN packets (SYN-SYNACK-ACK), the server stores the SYN whilst sending the SYN_ACK and awaiting response. But if you never send the ACK packet back, the server holds onto the memory. The resolution for this is to send the SYN back, and when you finally get the ACK along with the SYN you can allocate memory.

Smurf Attack - Sends ping to broadcast address of network, then it sends to all subnets that returns its responses to the spoofed return address, smashing the victims computer.

UDP Flood - Send UDP packets to random ports, the target machine will need to respond causing a DOS.
TearDrop - Send fragmented packet that cannot be put back together, the server sends requests for the missing packets but never receives.

DDOS - Simultaneous attacks on a single site by zombie (controlled) machines run with a bot. Each bot is installed on a zombie server that can launch DOS attacks.

Stop DDOS - Get new IP address, use cloud services to absorb (elastic beanstalk), get rid of bot nets, update A/V, firewalls, monitor traffic, white list ports, use non root accounts.

Port Knocking - All ports closed, daemon monitors port traffic and if it recognises a knock, then it opens the port. Dangerous because of MITM attacks that can capture packets and redistribute them or find out the correct knock.

- **4. Security Philosophies and Principles**

Firewalls and port blocking - default deny, white lists, black lists, packet sniffing, block only accepted ports.

Firewall Type - Stateless (default deny), Stateful (monitor traffic patterns), Application Layer (packet sniffing, detection)

Proxy Server - Filters incoming packets, caches downloads, NAT traversal (convert many outgoing IP to 1 IP for security). It can also be used for re-directing traffic and intercepting.

IDS (Intrusion Detection System) - Uses sensors to detect network behaviour, packet sniffing. Because all traffic comes through the IDS, it can get flooded causing a DOS.

IPS (Intrustion Prevention System) - Works with firewall to detect DOS, scanning.

Rings of Security - Using hardware and software to protect against attacks, proxy server, firewall, security policies, anti-virus, IPS, locks.

It all works until someone violates some policy.

- **5. Authentication**

Three things required - secrecy, integrity and authentication

Secrecy - Only the people communicating should be able to read the message.
Integrity - Be able to assure that the message contents have not been altered.
Authentication - The identities of the sender and receiver must be known.

Multi-factor authentication - passwords, biometrics, cards, signatures, 2 forms would be nice.

Brute force the username and not the password since there would be no lockouts.

Servers - store password hashes and not the password, salt the hashes (prevent rainbow table attacks).

Salting hashes basically means adding more stuff to the end of the input string then hasing it.

- **6. OS Security and Buffer Overflow**

OS flaws can allow websites, other users to install malware, escalate privacy and disable security.

VM's like on Cloud have their advantages and vulnerabilities - geolocation, hypervisor attacks, shared hardware.

Patches - used by malware to exploit vulnerabilities. Or to override a particular section of the software to install vulnerbilities.
Patches - no way of getting everyone to update, but can auto-install in background like Chrome, Firefox.

C Programming - All data is a stream of bytes where a struct keeps track of its position. A program does nto know when it has reached its end of the byte stream unless its a string. You can run the risk of running over the allocated or unallocated memory. It will not throw a out of bounds exception like other languages.

Buffer overlow basically means that you write past the point of where the compiler has allocated you memory for your object. The allocated space for the pointer to the return address now points to the hackers code.

Code Red - Worm used buffer overflow by padding with N then adding hex code at the end. 

```
GET /default.ida?NNNNNNN%u9090%%u3243% etc /HTTP/1.0

Padding with N and then writing hex code into the buffer to execute.
```

If the attacker knows the location of OS libraries (i.e. dll's), they can buffer overflow to point to those sections in memory to escale priveledges, take down anti-virus and firewalls.

DEP - Data Execution Prevention, allows for sectors in memory to not be executable.
ASLR - Address Space Layout Randomisation, changing heap and stack layout making it harder for hackers to locate useful memory addresses. However this mapping needs to be recorded somewhere, so attacking that instead is better.

URI - the length of a URL, used in GET commands to retrieve values from HTML forms. Very long URI's can cause buffer overflows.

Shell Code - allows admins to remotely execute tasks on the server, provides a set of commands that can allow you to do anything.

- **7. Script Injection (XSS)**

Allows spammers to inject executabel code onto forums, that can then be executed to redirect users to install malware or session info.

XSS DOM - Scripts in the browser have access to the browsers DOM that contains info regarding everything that is stored on each webpage. It can be used to add options, change settings, contents of the page, send data access other tabs. Sanitizing input is the best way to go to prevent XSS.

Prevention of XSS and script injection is to store user uploaded data to a seperate location to that which is used to server web pages.
Disable directory browsing on the web server.

Do not use cookies to store user data.

XML SOAP - Can have shell scripts wrapped within XML and passed to the server to be executed.

Pharming - Setting up websites that users will log into - can be used in XSS to replicate the real website.
Phishing - Baiting someone to click on links or respond to something.

- **8. Secure Communication**

SSH - Secure Shell using TCP connections that encrypt data and user information. Encrypts payload and sends by TCP using certificates.

SSH - connects, client and server exchange encryption capabilities, exchange secret keys and authentication capabilities, data transfer.

SSH - Send ciphertext and MAC (like checksum), MAC used to check integrity.

SSL- Secure Socket using TCP connections that exchange encryption keys and certificates.

IpSec - Adding a layer ontop of IP packets to verify identity and integrity with public key. Logs IP, but breaks NAT because NAT rewrites IP.

VPN - Tunnel traffic through a public network, encrypts packets to send over TCP/IP. No protection in, if endpoint is infected it can send dodgy information to the client.

SSL - connects, server sends certificate + key, client checks against cache and accepts, client creates session key with public key and sends it back. All encrypted afterwards with public key.

SSH - MAC added to plain text then encrypted. 

Telnet - used to communicate and connect to servers (unencrypted, unsecure). Can be used to connect to any port, can be used to send anonymous email. It also can be used to scrape website headers with HEAD/GET to return information about the web server if you telnet into the server.

- **9. Attacks and Mitigation Strategies**

DNS (Domain Name System) has an inbuilt cache of IP to Domain Names. DNS cache poisoning is overwriting the DNS entry so it points to another IP address. You could also override the computers HOSTS file to redirect to a website, because the entries in the HOST file always takes precedence over the DNS server.

Mitigate it withn DNSSec, where queries, replies are digitally signed to prevent spoofing.

TearDrop, DDOS, Network Attacks.

- **10. Physical Attacks**

Physical locks are by not means a way to ensure that something is secure, they can always be broken if not picked.

Access cards, barcodes, RFID tokens and all that are always means of physical security.

Biometrics, are good but can invade privacy but also lots of false positives. Must not be able to change and hard to forge.

DOS - flood the place, set it on fire, where the server is just destroy it.

Eavesdropping - key loggers, wifi sniffing, MITM attacks and phone tapping.

Rainbow Table - precomputed table to reverse hashes through brute force

Install over the existing OS, this will reset the passwords.

RAM freezing, electron microscope analysis of hard disk, BIOS reset, flashing, memory dump.

AirGap - Prevents network intrusion, data entry by keyboard or USB, data take out through eyes only or removable media.

- **11. Social Engineering Attacks**

Dumpster diving, asking the right questions filling out random surveys.

Phone books, tailgating, looking over someones shoulder, help desk, password hints, social media.

- **12. Network Attacks**

Daisy chain the attack, tunnel through to another computer, compromise that and then begin the attack, hides the real originator address.

MAC - Media Access Control - Each Network Card has a unique MAC address.
Hub - Echo's all packets received to all connected devices
Switch - Echo's all packets received to their destinations as defined by the MAC.
Bridge - Like a switch links 2 subnets together.
Router - Passes packest around the network.
NAT - Network address translation, re-addresses the IP packet so you can hide the internal network.
ICMP - Internet Control Message Protocol - used by the OS and network tools to exchange errors and response from Ping and TraceRoute.
UDP - User Datagram Protocol - Just sends information without waiting for connection.

Ports - Telnet (23), FTP (20, 21), DNS (53), HTTP (80), HTTPS (443), SSH (22)

ART Cache Poisoning - Maintains a table of MAC and IP. ARP sends out a who has and records the response. A MITM attack will intercept the ARP packet, and puts their own IP in. Future traffic going to that MAC will go to the hackers IP instead.

MAC Flooding - Switches use ARP to map MAC to IP, if overloaded, the switch goes to hub mode to broadcast all packets it receives.

Traceroute - Identifies the path taken by an IP packet, sends packets out with increasing TTL collects the time out returns.

- **13. Network Reconnaissance**

Enumeration - obtaining lists of things, usernames, passwords, emails.
Port Scan - Finding the ports that are open/closed that are listening to things. (telnet, port scan). It is done to identify which ports are open and accepting packets, can be used to see the software versions running on the server.
Footprinting - Discovering names of servers and the software versions they're running.

Identify Targets - Finding hosts on a network, look for all IP's (Use ping, NSLookup, Whois, etc)
Identify Vulnerbilities - User port scan to find open ports and what they're listening to. Use footprinting.
Find Vulnerbilities - For the identified services, find known vulnerbilities and exploits.
Social Engineering - Using non computer means to find exploits in system.
Fuzz Testing - If no vulnerabilities are found, fuzz test to spam the target, inputting massive data to get it to crash.

Write Code - custom exploits and code to exploit the vulnerability.
Attack - Launch the attack.

Symmetric Key - the same key is used for encryption and decryption
Public Key - encryption key is public, decryption key is private. 

Digital Signature - Bob encrypts his message with his private key, sends the message and digital signature to Alice. Alice verifies the message by applying Bobs public key to the encrypted message, then checks that the decryption is the same as the encryption. This means that it must be Bob that signed it.

RSA - Is a public key encryption algorithm. Bob puts his public key out to a CA that Alice retrives to encrypt her message with. This breaks TNO as CA"s can't be trusted.

Message Digest - Use fix length signature, apply hash to message to get the fixed sized hash. 

Certification Authority (CA) - Retreiving public keys from a "trusted source". 

Encrypting Email - Generate symmetric keys, encrypt message with key, then encrypt again with bobs public key, send both encrypted messages to bob. Bob decrypts with private key to get Alice's symmetric key, then decrypt the message.

Signatures - Alice digitally signs the message, sends message and digital signature to bob.

Secrecy, Sender Authentication, Integrity - Alice hashes and encrypts the hash with her private key (signature). Adds the message and encrypts it with the session key, encrypts the session key with bobs public key and sends the whole thing along.

Bob gets the session key with his private key, to get the signature and message with the session key, and uses Alices public key to extract the hash from the singature.

Diffie Hellman - Session key generated by server and client and sends to each other. After the exchage they have both and use them to create a shared key.

IPSec : Network Layer - Authenticaion Header and Encapsulation SEcurity Payload protocol.

SSL - conatin pre shared key and RSA hash. The first time you go, you are prompted to cache the certificate, then each time you go after that you don.t. The next time you connect it can compare it with the certificate it has on the server.

- **14. Security Models**

Policies, procedures and models.

Policies - what must be done.
DAC - Discrentionary Access Control - You set your own permissions
MAC - Mandatory Access Control - You get them set for you.

Trust Management -

Vulnerbaility assessments - black box, white box.

Kerberos - Ticket granting ticket, service ticket, KDC (key distribution centre)

SPAM - Sender Policy Framework - Is a field in a DNS record to authenticate a mail server, easy to spoof and does not check for email integrity or encryption.

- **15. Network Tools and their Outputs (Netstat, Ping, NSLookup, etc)**

Ping - Sends an internet request to a specified IP address or domain that returns the replies (domain names are returned)

NSLookup - Used to query a DNS to find an IP address. (Enter domain name (not host), get IP back along with the DNS server details)

Reverse NSLookup - nslookup -type=ptr < reverse IP > .in.addr.arpa (Find the domain name given an IP address)

WhoIs - returns server into + system admin details (whois -h < registar name > < domain name >) whois -h whois.aunic.net swin.edu.au

Netstat - displays the incoming and outgoing TCP connections to the current computer. Shows the ports that are listening.

Tracert - Used to trace the route between yourself and the destination server.

// Read output of these logs...seriously

- **16. Cloud**

Web Services - distributed web applications that send XML commands to each other without human interaction.
IaaS - Get EC2 instance to install whatever
PaaS - Develop software, websites and applications without having to worry about the hardware
SaaS - Provide software as it was sitting on the desktop, but through a browser (website) like Microsoft Word, google Docs.

CIA risk with cloud - confidentiality, integrity, availability.

Encrypt, backup and protect against DDOS.

3-2-1 rule, 3 backups, 2 types of media, 1 offsite.

### TODO:

Go through Labs, Exam and answer the questions...with the lecture notes...