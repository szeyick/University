## NSLookup

NSLookup is a set of tools for looking at DNS records.

Using the -type=any command will return everthing on the DNS

```
- nslookup swin.edu.au
Server:		192.168.0.1
Address:	192.168.0.1#53

Non-authoritative answer:
Name:	swin.edu.au
Address: 136.186.1.12
```

This just provides the IP address of the domain name that we entered.

When you first run nslookup, it does a lookup of your local DNS server, which in this case is just the machine that you're on. 

The "Non-authoritative answer" section is that it queried an external DNS server to resolve my input.

### Mail Records (MX)

We may want to look for a specific type of DNS record, in this instance the mail records for the domain. This is to point to an organisations mail server. 

This is the server that emails wil be sent to if they wanted to send emails to Swinburne.

```
- nslookup -type=mx swin.edu.au
Server:		192.168.0.1
Address:	192.168.0.1#53

Non-authoritative answer:
swin.edu.au	mail exchanger = 10 mx1.swin.edu.au.

Authoritative answers can be found from:
swin.edu.au	nameserver = ns3.swin.edu.au.
swin.edu.au	nameserver = neddy.newcastle.edu.au.
swin.edu.au	nameserver = ns1.swin.edu.au.
swin.edu.au	nameserver = ns2.swin.edu.au.
mx1.swin.edu.au	internet address = 136.186.1.12
ns1.swin.edu.au	internet address = 136.186.1.111
ns1.swin.edu.au	has AAAA address 2001:388:6080:64::35:6f
ns2.swin.edu.au	internet address = 136.186.20.9
ns2.swin.edu.au	has AAAA address 2001:388:6080:14::35:9
ns3.swin.edu.au	internet address = 136.186.1.115
ns3.swin.edu.au	has AAAA address 2001:388:6080:64::35:73
```

Again the non-authoritative answer just states that it had to enquire from another DNS. However if you did log into another machine on the same domain (i.e Mercury), then those lines will all disappear.

The value (10) in front of mx1.swin.edu.au just states the priority of the mail server. The lower the number, the higher the preference if there were multiple. If there were more mail servers, then it would go to the one with the lowest value first.

### Name Server (NS) record

Using the -type=ns we can retrieve the map of domain name to a list of DNS servers for that domain.

```
nslookup -type=ns swin.edu.au
Server:		136.186.1.111
Address:	136.186.1.111#53

swin.edu.au	nameserver = ns1.swin.edu.au.
swin.edu.au	nameserver = ns2.swin.edu.au.
swin.edu.au	nameserver = ns3.swin.edu.au.
swin.edu.au	nameserver = neddy.newcastle.edu.au.
```

### Start of Authority (SOA)

This command -type=soa provides the authoritative information about the domain, email address, serial number, etc.

```
nslookup -type=soa swin.edu.au
Server:		136.186.1.111
Address:	136.186.1.111#53

swin.edu.au
	origin = ns1.swin.edu.au
	mail addr = hostmaster.swin.edu.au
	serial = 2015102201
	refresh = 10800
	retry = 1800
	expire = 2592000
	minimum = 43200
```

Mail Addr - The email address of the domain admin.
Serial - The revision number system
Refresh - The number of seconds the secondary DNS will probe the primary DNS to see if the serial has increased (if changed)
Retry - The interval to reconnect with the primary DNS
Expire - The time the secondary DNS will keep the cached zone file
Minimum - The time the secondary DNS should cache the zone file.

### Reverse DNS

You can reverse a DNS lookup by providing an IP address.

```
nslookup 136.186.1.12
Server:		136.186.1.111
Address:	136.186.1.111#53

12.1.186.136.in-addr.arpa	name = gpo.swin.edu.au.
```

