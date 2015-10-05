## Network Security 2

### DNS - Domain Name System

Is a system of name servers that translate domain names into IP address, located all over the internet. It is a system that is very susceptible to an attack (DNS attacks).

In the olden days (before Internet), to access a website people would need to know the exact IP address that they wished to visit. However the more it grew, the more tedious this became since remembering 100's of IP addresses would be very time consuming. Instead the initial idea was to save the host and domain name to a HOSTS file which would be used to look up the IP address, essentially creating a map. This file would then be downloaded and updated on a local machine when a popular website was visited.

DNS automated this lookup system where DNS servers would communicate with each other through UDP port 53.

### Host Files

On a computer, the addresses listed in the hosts file will take precedence over any external DNS query. Usually this file is empty with probably the exception of the localhost address for the machine.

#### Browser Hijacking

A common strategy by spy/adware, hijackers or pharmers is to modify this host file and change the entries so that correctly entered URI's will redirect you to another website.

#### Web Filtering

A basic but common strategy to prevent access to forbidden pages is to update the hosts file with forbidden sites and the default IP address (loopback) to the host machine. Thus when the user enters the URI, they will be always redirected to the loopback address.

### DNS Cache Poisoning

The idea here is that the attack will send many DNS queries and replies simulataneously where the replies will spoof the IP of the name server. The downside to this is that you'll need to obtain the DNS query ID

### Kaminsky Attack

An attack that is based on querying non-existent hosts on the targeted domain address. The result of this is that the DNS client will cache an additional record for that non-existent host query.

With this attack it is easier to guess the DNS query ID.

A **defense** against a Kaminsky attack would be to increase the randomness in how DNS ID's are generated such as combining the UDP paket ID with the generated DNS ID to increase the ID range. Also allow for local DNS servers to only accept queries from inside the network and not just from anywhere.

### DNS Security (DNSSec)

To add additional security to the DNS query or response, we can digitally sign them. This level additional level of authentication would be used to prevent replies from being spoofed.

However adding this extra layer adds further complexity and is difficult to set up. For it, we require a **public key cryptography** and a **chain of trust**, things that can also be stolen.

### Ping 

This command sends a ICMP echo request packet to a particular IP address. If the address is found, it sends a response back with packet send/recieve information along with round trip information.

### NSLookup

This command queries a DNS to find the IP address of a particular domain name. So if we typed in the domain name, it will return the IP address.

If we do not provide the NSLookup command a domain name, it will just return the name and IP of the default DNS server.

If we provide the command with additional **type=mx** parameter, we can get information regarding mail servers of the specified domain name.

If we provide the command with additional **type=soa** parameter, we can get additioanl information regarding the entered domain name.

If we provide the command with additional **type=all** parameter, we can get complete information of the entered domain name.

Additional types that we can try to add to the command will be **NS, txt, hinfo**.

NSLookup will work for different levels of a DNS (i.e. swin.edu.au, edu.au, au etc), but will only work on domain names and not any host names.

Aside from NSLookup, we can use the command **dig** which will return the same DNS information.

### Reverse DNS Lookup

We use this to verify that an IP points to a domain name. 

Here the idea is to reverse the IP, add the string **in-addr.arpa** and perform a lookup of the PTR record.

So if we wanted to find the domain name for the IP address 123.456.789, the reverse lookup command will look something like this -

```
nslookup -type=ptr 987.654.321.in-addr.arpa
```

### Whois

This command will return the name server info and details of the system adminstrators (owners) of a domain. It works by accessing the database of a web registar to find the information.

### Firewalls

The process of filtering incoming traffic to a set of rules that can involve, destination IP addresses, protocols, port numbers and the contents of the incoming packet. It can be used as a means to protect against traffic coming in from particular IP addresses and ports.

In the beginning the default firewall setting as **default allow**, which would all ports open to all IP addresses, then rules will be created to filter the traffic.

Now days, the default firewall setting would be to **default deny** which blocks everything with the exception of the ports needed for particular services like HTTP, FTP, SSH. It will then create rules to enable access to filter traffic through to a particular port restricting where the incoming traffic can go.

There are 3 types of firewalls -

- Stateless

These are the fastest types of firewalls, but thhey don't prevent SYN flooding and port scanning.

- Stateful

Can be used to detect message patterns and can combat against flooding, scanning. Generally provides a greater set of rules.

- Application Layer

Can inspect an entire packets contents, and can flag/classify the traffic depending on the packets content.

### Proxy Servers

Has 3 primary functions -

- Filter packets based on content, IP addresses or domain
- Caches downloads so websites that are regularly accessed will load faster as it is cached.
- Performs a NAT traversal to allow the sharing of a single external IP address that can be used by many internal network hosts.

A side effect of the third function of a proxy server is that it re-addresses the source IP of each outbound packet so the person within the network that sent it will not be known.

It can also be used for intercepting and logging internet traffic as all outbound traffic first goes through the proxy server before going out so the contents of the packet can potentially be known to the server.

### SSH (Secure Sockets Handler)

An encrypted version of Telnet that provides user authentication and multiplexing by default.

This also includes SCP, SFTP which is an application layer protocol that encrypts the application payload to send over TCP.

It encrypts the data through keys and certificates but requires a **chain of trust** to store the keys.

The stages of an SSH session be something like -

- TCP connection is established.
- Client and server exchange encryption capabilities.
- Secret key exchange (i.e. Diffie-Hellman)
- Client and server exchange authentication capabilities that can be checked against other cached public keys.
- Encrypted service is started (session starts)

### SSL (Secure Sockets Layer)

Uses a certificate based key exchange but only encrypts the data (transport layer)

The stages of an SSL session be something like -

- TCP connection is established.
- The Client requests a connection to the server
- The server sends a certificate and a public key to the client.
- The client compares the key with what it has in its cache, otherwise the user is asked to accept the cache and key.
- The client creates a session key and encrypts it with the servers public key and sends it back to the server.
- All subsequent communication between the client and server is encrypted with the session key.


### HTTPS (HTTP tunneled through SSL)

Otherwise known as TLS.

### IP Security (IPSec)

Adds an **authentication header** to an IP packet. 

It verifies the integrity and identify of a packet by using public key cryptography to verify the identify of the sender. 

It hashes the payload and IP address to verify the identity of the sender. The consequence of this is that it breaks a NAT since the NAT will change the IP before sending a message out, making the initial hash of the payload and IP address incorrect.

Aside from adding an authentication header, we can also add an **ESP - Encapsulating Security Protocol** to a payload. This method adds a header and trailer to a payload.

Unlike the authenticaion header, it does not hash the IP address therefore not breaking NAT. It however does hash the port numbers so the NAT will not be able to do port forwarding but is able to do IP forwarding.

### VPN (Virtual Private Network)

Tunnells all traffic over a public network and encrypts the IP packets to send over a public TCP/IP as encrypted payloads.

A VPN provides separation between the outside (Internet) and the internal network (private network). It however does not provide protection for the internal network. Therefore if the endpoint is infected, then malware can pass through the tunnel to the rest of the network without any barriers, basically allowing free access to the internal private entwork.

### Intrusion Detection

- IDS uses sensors to detect strange behaviour and a IDS manager will interpret the reports.
- NIDS is a network IDS that is positioned at the gateway of a network to do deep packet inspection to detect malware.
- HIDS is a host IDS valid for 1 PC only to monitor system logs, file hashes and behaviour
- IPS is a intrustion prevention system that works alongside the firewall to detect DOS and port scanning activities.

### IDS

Because it functions as a gateway, it can be a target for attack. Since it uses a IDS manager to log events, an attacker can DOS the IDS by flooding it with loggable events to take up all the memory.

### Terminology

- Enumeration

The process of obtaiing lists of information (usernames, email addresses, passwords etc)

- Port Scan

A way of finding out which ports are open and attached to listening and responding services/daemons

- Footprinting

Finding out names and versions of software running on a victims computer.

### Network Reconnisaince

- Probe IP addresses on a network to find hosts that will respond (ping, traceroute, Wifi detection)
- Enumerate through the host/network
- Probe other IP's within the targeted IP address (subnets)
- Find vulnerable ports on the host using telnet or other tools (nmap) or port scanners
- Retrieve the services that are found through enumerating.
- Find vulernabilities online for particular services and the versions of software.
- Find exploits to launch the attack.

If no exploit can be found, we can set up a mirror (exact replica of a targets network) to fuzz test or reverse engineer it to find a bug.

### Wifi Security

Open WIFI (usually public free wifi) has no authentication or encryption.

WEP is a type of security for Wifi that allows for encryption key to be shared.

WPA and WPA2 uses a unique key making brute forcing keys difficult.

WPS is a standard to synchronising wireless routers and devices but is seen as providing weak securty because it splits the key.
