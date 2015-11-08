## Lecture 6 - 

This contains rough notes on the lecture

### NSLookup

Provides information regarding a name server including the duration, TTL, expiry. It only works on domain names.

### Reverse DNS Lookup

It is used to verify that an IP address points to a domain name. It is used with the NSLookup but with the IP address in reverse.

### WhoIs

Looks up domain name records, who sets up the DNS and registered the domain name. It works by looking at a domain name registars database for the domain name records.

### Firewalls

Firewalls can be configured for each port, meaning that you can specifically block traffic on a specific port. It allows you to set up a bunch of rules to accept/reject packets based on some defined criteria.

It can look at the contents of the payload and perform virus scanning. Default deny will prevent zero days so it will default deny packets.

Stateless firewalls don't detect patterns, it doesn't remember anything like with HTTP requests. But is really fast.

Application layer firewalls will detect everything. 

### Tunnelling

SSH is the encrypted version of Telnet, and does authentication which SSL does not. SSL encryptes the data but not the authentication. 

HTTPS is HTTP with SSL. SSL is now known as TLS (Transport Layer)

MAC is the long string of hex digits that are outputted when using SSL/SSH.

Block cipher will encrypt a sequence of text in a messassge with the same key.

Stream cipher will encrypt a message with the generated cipher text of the previous block and the encrytpion key.  

### Terminology

Footprinting is practicing on an exact replica of the system that you are trying to crack into. Like getting a replica of the safe that you are trying to break. 

Network recon scenario may be on the exam, as to how to find out about a network with only an IP address, server and stuff. Also think about social engineering principals a long with looking at the hardware.
