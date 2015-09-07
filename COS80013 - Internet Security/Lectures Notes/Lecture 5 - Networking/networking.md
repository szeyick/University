## Lecture 5 Notes

### OSI Model

Stands for Open Systems Interconnected, that comprises of 7 layers that allow any computer to talk to any other computer over a network or Internet. It uses standards to wrap data in packets as they head into the Internet and unwrap them when they come out.

Of the 7 layers, not all the layers are used, routers only use layers 1 - 3 and most internet services don't use layer 5.

![alt text](https://github.com/szeyick/University/blob/master/COS80013%20-%20Internet%20Security/Lectures%20Notes/Lecture%205%20-%20Networking/images/osiStack.png "OSI Stack")

The layers are as follows - 

- **Application Layer**

Consists of HTTP, SSH, POP, SMTP which interface with the program run by the user.

- **Presentation Layer**

This layer manages the port allocation, encryption and the variations in the application layer syntax.

- **Session Layer**

This layer manages the communications between processes, Windows file sharing and synchronising of data streams. Uses NetBIOS or RPC.

- **Transport Layer**

Consists of TCP, UDP. This layer manages the end to end communication.

- **Network Layer**

This layer sends packets from one IP address to another through IP, IPv6 or ICMP.

- **Data Link Layer**

This layer arranges the bits that are being sent. Comprises of SLIP, PPP, used by hubs, bridges and switches.

- **Physical Layer**

This layer is comprised of radio, ethernet, coax cables. The physical means to transmit the data across.

### OSI Layer Mapping

There are various means that map each layer together -

- MAC Address to IP - ARP
- Public IP to Public IP - Routing Protocols
- Public IP to Private IP - NAT
- IP to Domain Name - DNS

### OSI Security

We have mentioned previously that there is no implemented authentication on the Internet, this is reflected in the OSI model as well. The presentation layer is often responsible for encryption but is often not a layer that is implemented.

### MAC (Media Access Control)

Each NIC (Network Card) has a unique MAC address that is embedded into each card. It is a 48-bit hexadecimal number.

The ARP protocol is responsble for matching a MAC address to an IP address. Therefore it maps layer 3 with layer 2.

### DNS (Domain Name System)

A system of name servers that translate URL domain names to IP addresses.

### Hubs and Switches

A hub will echo all the packets it receives to all the devices that are connected to it.

A switch will echo all packets received to their devices as defined by the MAC address. It will use ARP to link the MAC address to the IP addresses of the packets. However switches will revert to a hub like behaviour if they are overloaded.

### Bridge and Routers

A bridge acts like a switch that can link 2 subnets or 2 parts of a network that use different physical layers or cables. It can easily create security issues as it can link trusted LANs to the Internet.

A router can pass packets to a particular interface by checking the destination IP address and figuring out the most successful path to get there.

### NAT (Network Address Translation)

Is a type of router that can open up an IP packet and re-address it. The incoming traffic is re-allocated to a different internal IP, which is driven by the port used by the incoming packet.

It is useful because it hides the internal details of the private network.

![alt text](https://github.com/szeyick/University/blob/master/COS80013%20-%20Internet%20Security/Lectures%20Notes/Lecture%205%20-%20Networking/images/NAT.png "NAT")

A NAT only exposes the public IP address which gives access to the NAT. Afterwards it will re-address everything and route the traffic to a private IP address to a device that is within the network.

### IP (Internet Protocol)

It is part of the network layer and functions as a IP addressable data container that is used to carry TCP and UDP packets through a network or Internet.

### ICMP (Internet Control Message Protocol)

Uses IP addresses and is generally used by OS's to exchange error messages. It does not use ports, and is used by tools such as Ping and TraceRoute.

### TCP/IP (Transmission Control Protocol)

Provides an asynchronous data container that is carried by IP. It gives reliable delivery without losing packets and will deliver the packets in the correct order in which they were sent. It is however not fast and will wait until it gets the correct packet in the sequence before continuing on to wait and receive the next one.

It will create connections (sockets) where the packets can be exchanged reliably. 

TCP/IP is a protocol that is used by FTP, HTTP, Telnet, POP.

To guarantee data delivery, it establishes a connection through a 3 way handshake. Both endpoints need to know the packet sequence number is to receive the data properly.

![alt text](https://github.com/szeyick/University/blob/master/COS80013%20-%20Internet%20Security/Lectures%20Notes/Lecture%205%20-%20Networking/images/TCPIP.png "TCP/IP")

### UDP (User Datagram Protocol)

UDP is another method of sending data over IP, but unlike TCP its intention is to send data as fast as it possibly can. The consequence of this is that UDP makes no guarantee that data transmission will be reliable as it will just send data without waiting for a connection response.

Because it just sends the data without establishing any sort of authentication, it is succeptable to spoofing as the client never checks where the source address is from before receiving the data.

### Ports

The most well known and standardised ports are in the range from 0 - 1023.

- Telnet (23)
- FTP (20, 21)
- DNS (53)
- Mail (110, 25)
- SSH (22)

Ports further up the range 1024 - 49151 are registered by companies for their own proprietary needs.

The highest of the ranges 49152 - 65535 are dynamic or private ports which are allocated during runtime.

Although ports are assigned for certain tasks, they are by no means bound. Meaning that it is entirely possible for a web server to use port 22 or a telnet server to use port 80.

### IP Addresses

The address of the local network card is always 127.0.0.1

D class multicast addresses are in the range of - 224.0.0.0 - 239.255.255.255

E class reserved addresses are in the range of - 240.0.0.0 - 254.255.255.255

The broadcast address for all local subnets is - 255.255.255.255

### Ping

Is a tool that is used to test network connections. It sends a ICMP echo packet to a specified IP address and waits for the response packet. It reports the time delay in milliseconds.

### Traceroute

Is a tool to track the path taken by IP packets. It sends a sequent of TCP or UDP packets with incrementally increasing TTL values. 

The TTL (time to live) timer is decremented by 1 each time it passes through a router. Once that timer gets to 0, it sends it back to the user with the routers IP, and the name of the router.

### IPConfig, IFConfig and WinIpCfg

It displays the local computer network interfaces (NIC) and the IP address of each card. It can be used to initiate a DHCP request or refresh the DNS cache.

### Netstat

Displays all current network connections and can be used to find presence of trojans and spyware phoning home.

### Packet Sniffing

They record IP packets that are on a network, originally used to diagnose problems within the network. Applications such as Wireshark or Snort will detail the packet contents and allows you to log them to view later.

### Network Attacks

All the layers in the OSI model are susceptible to attacks.

- **Application Layer** - Viruses, worms, trojans
- **Session Layer** - Null session attacks
- **Presentation Layer** - DNS DOS, DNS cache poisoning
- **Network Layer** - ARP cache poisoning, ARP spoofing
- **Physical/Data Link Layer** - Sniffing, playback attacks

### Sniffing a NIC/Hub

Capturing the IP packets and analysing them as they arrive into the network or hub.  This is what we essentially do with Wireshark.

### Ping of Death

Sending an oversized ping packet will crippled the network stack of old computers running Windows 95 causing them to crash. Also, flooding a network with a lot of pings will result in a DOS (Denial of Service).

### ARP Cache Poisoning

ARP maintains a table of MAC addresses and their equivelant IP address. It will send out queries as to which MAC will respond to which IP.

For a MITM attack, a attacker will send a ARP reply to nominate their MAC address as the one to respond to that IP address so the ARP will add the attackers MAC address to its table. Thus all the IP packets will come to the attackers computer first before they forward it onto the intended target.

### MAC Flooding

An attacker will try to overload a switch by sending multiple invalid ARP responses, this will cause the switch to overload reverting to hub mode where it will just send all packets to all interfaces meaning the attacker will have access to all the packets arriving at the switch.

### DOS (Denial of Service)

A type of network attack that is aimed at servers on the internet (HTTP, DNS, FTP servers). The goal is to prevent service to customers so they go somewhere else or abandon the service.

#### Ping Attacks

The easy way to trigger a DOS is to send a stream of pings (ICMP). However this can easily to combated by changing the IP address or blocking ICMP traffic or buying a router that drops ping packets.

#### SYN Attacks

Another way to trigger a DOS is to send a stream of SYN packets during a TCP/IP handshake. Each SYN will consume memory on the server as it sends a SYN_ACK packet to await its response. If the attacker never sends a ACK packet response and only sends SYN, the server will eventually run out of memory.

A way to combat this would be to use SYN cookies which send a cookie to the attackers machine. It will wait until it receives the cookie back in the ACK packet before it allocates memory for the connection.

#### Smurf Attack

Is a type of amplification attack where an attacker sends a ping request (ICMP) to the broadcast address of the victims network. This will send a ping to all the IP's that are on the subnet.

The original ping request has a spoofed return IP address which is the address of the victim. This means that each IP that is reached by the broadcast will return to the victims computer.

To combat this, would be to disable the broadcast address or filter it. Another way is to hide the IP addresses through the use of a proxy server.

#### UDP Flooding

Causes a DOS as UDP packets are sent to random ports of the victims network as fast as possible. The targeted machine will respond to each packet with a ICMP destination unreachable packet, which will eventually cause it to break.

To combat this would be ot turn off ICMP destination unreachable.

#### Teardrop

A DOS attack where a fragmented packet is sent but the packets have been corrupted and cannot be put together. The server will consume resources requesting the source to re-transmit and may eventually crash if enough of these requests are waiting.

Patching the OS is the general solution to this issue.

#### Land Attack

Is another type of amplification attack where a packet with a spoofed IP address is sent to the victim. The return address is also the same as the destination, meaning it will answer back to itself in a loop.

Patching the OS is the general solution to this issue.

#### Sock Stress

Is a post handshake protocol abuse. The idea here is that the server will reply to a SYN with a SYN-ACK and a size of window, but the client never increases the window size. The server will forever wait for the window to get bigger but never does.

### Distributed DOS

Simultaneous attacks on a single site by a bunch of bots controlled by a single bot herder. Each bot is actually a small program that has been installed on a machine that is capable of launching DOS attacks. These are usually spread by trojans, worms and viruses.

### DDOS Prevention

- Using cloud services to absorb or filter traffic
- Get new IP addresses, blackholing, rate limiting
- Sanitising PC, virus scanning, firewalls, packet sniffing.
- Update software and anti virus.
- Use admin and non-admin accounts to limit access.

### Port Knocking

The idea with port knocking is that all the ports are closed and a daemon will monitor all port traffic. A remote user will send packets to a particular sequence of ports, where the daemoon will recognise the knock and opens the ports for the users IP and connects.

### IPv6

It is a new protocol but also has new code and its own bugs. Many botnets tunnel over IPv6 to remain anonymous.

- IPv6 addresses include your MAC addresses
- It reduces the need for ARP tables
- It reduces the need for NAT

At the same time it also breaks anonimity since everyone that knows your IP will know your MAC address. However it is possible to download programs that will change your MAC address.
