## Exam Topics

- **1. Protecting a web site**

BEAST - Browser Exploit that injects JS into the browser collecting headers, ciphertext to rebuild session ID.
CRIME - Injects JS to intercept compressed session cookies to send to user, sending different cookies bac to rebuilt

- **2. Forensics**

Obtain evidence by locking down the crime scene securing computers, logging all computer activity with time stamps making sure that it does not switch of or self destruct.

- **3. DDOS**

- **4. Security Philosophies and Principles**

Firewalls and port blocking - default deny, white lists, black lists, packet sniffing, block only accepted ports.

Firewall Type - Stateless (default deny), Stateful (monitor traffic patterns), Application Layer (packet sniffing, detection)

Proxy Server - Filters incoming packets, caches downloads, NAT traversal (convert many outgoing IP to 1 IP for security). It can also be used for re-directing traffic and intercepting.

IDS (Intrusion Detection System) - Uses sensors to detect network behaviour, packet sniffing. Because all traffic comes through the IDS, it can get flooded causing a DOS.

IPS (Intrustion Prevention System) - Works with firewall to detect DOS, scanning.

Rings of Security - Using hardware and software to protect against attacks, proxy server, firewall, security policies, anti-virus, IPS, locks.

It all works until someone violates some policy.

- **5. Authentication**

- **6. OS Security and Buffer Overflow**

- **7. Script Injection (XSS)**

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


- **14. Security Models**

- **15. Network Tools and their Outputs (Netstat, Ping, NSLookup, etc)**

Ping - Sends an internet request to a specified IP address or domain that returns the replies (domain names are returned)

NSLookup - Used to query a DNS to find an IP address. (Enter domain name (not host), get IP back along with the DNS server details)

Reverse NSLookup - nslookup -type=ptr < reverse IP > .in.addr.arpa (Find the domain name given an IP address)

WhoIs - returns server into + system admin details (whois -h < registar name > < domain name >) whois -h whois.aunic.net swin.edu.au

Netstat - displays the incoming and outgoing TCP connections to the current computer. Shows the ports that are listening.

Tracert - Used to trace the route between yourself and the destination server.

- **16. Cloud**