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
