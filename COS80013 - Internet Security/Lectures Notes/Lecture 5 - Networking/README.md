##Terminology

- **OSI Model**

Stands for Open Systems Interconnected, defines the interface stack that allows any computer to talk to any other computer that is based on a network or the Internet.

- **OSI Application Layer**

The 7th layer of the OSI stack, this is the layer that interfaces with the users application. Consists of HTTP, POP, SMTP, FTP.

- **OSI Presentation Layer**

The 6th layer of the OSI stack, used for encryption, port allocation and combining the different interfaces of the application layer.

- **OSI Session Layer**

The 5th layer of the OSI stack, used to manage communications between processes through RPC or NetBIOS calls. Also used by Windows File Sharing to synchronise data streams.

- **OSI Transport Layer**

The 4th layer of the OSI stack, this layer manages the end to end communications through TCP or UDP packets.

- **OSI Network Layer**

The 3rd layer of the OSI stack, this layer transports packets from IP address to another. Performed through IP, IPv6 and ICMP.

- **OSI Data Link Layer**

The 2nd layer of the OSI stack, this layer is responsible for ordering the bits that need to be sent. Comprises of SLIP, PPP, used by hubs, bridges and switches.

- **OSI Physical Layer**

The 1st layer of the OSI stack, this layer outlines the physical means that the transmission will take place, radio, ethernet, coax.

- **MAC (Media Access Control)**

The 48 bit hexadecimal number that is assigned to each NIC (Network Interface Card). 

- **ARP (Address Resolution Protocol)**

Responsible for maintaining the mapping between MAC addresses and IP addresses.

- **DNS (Domain Name System)**

Translates domain names into IP addresses.

- **Hub**

A physical networking device that will re-route (echo) all the packets it receives to all the devices that are connected to it.

- **Switch**

A physical networking device that will re-route (echo) the packets it receives only to the devices that match the intended MAC address - IP address. Switches when overloaded will revert to hub like behaviour and simply re-route all packets to everyone.

- **Bridge**

A physical networking device that will link 2 subnets or 2 parts of a network together.

- **Router**

A router can pass the packets it receives to a particular address by checking its intended IP address and figures out the most successful path to that address.

- **NAT (Network Address Translation)**

Is a special kind of router that can open up a IP packet and re-address it. It is useful as the public facing end of a network as all traffic will come in through the NAT, which then re-addresses it to its intended target within the private network. 

- **IP (Internet Protocol)**

It is part of the network layer and functions as a data container that is used to carry TCP and UDP packets through a network or Internet.

- **ICMP (Internet Control Message Protocol)**

Uses IP addresses and is generally used by OS's to exchange error messages. It does not use ports, and is used by tools such as Ping and TraceRoute.

- **TCP/IP (Transmission Control Protocol)**

Provides a asynchronous data container over IP to carry data over a network or the Internet. TCP/IP is secure and guarantees that data sent will be received in the order it is sent. A reliable connection is first established through a SYN-SYN-ACK-ACK sequence between the client and server before data is sent.

- **UDP (User Datagram Protocol)**

Another method of transmitting data over IP. This is less reliable than TCP/IP as it does not rely on establishing connections through handshakes and just sends the data. It is however faster than TCP/IP.

- **Ports**

Ports are assigned for different types of network services, SSH, HTTP, FTP etc. Although there are industry standards for which port does what, they are not tightly bound meaning that it is possible to use port 80 for SSH or FTP rather than HTTP. The consequence of this is that it might be very difficult for someone to connect to your network.

- **TraceRoute**

A network tool that is designed to map a path through a network that a packet takes. It works by sending a sequence of TCP or UDP packets with a TTL (Time to Live) counter on it. This value is decremented each time it passes through a router and once it hits 0, it will send back the router information.

- **Netstat**

A network tool that is used to monitor the current network connections. Useful for monitoring against malware and spyware intrusions.

- **Packet Sniffing**

The concept of unbundling a packet to see its contents. Software such as Wireshark will tell you information about the packet and log it. Can also be used by attackers to see the contents of the packet for their own mallicious purposes.

- **Ping Of Death**

The method of sending an oversized TCP packet to a target address causing a crash in the network stack. 

- **ARP Cache Poisoning**

Where a attacker replaces the MAC address in the ARP table with their own, so incoming messages to a particular IP that is linked to the MAC address will be re-routed to the attackers computer. They can read it before forwarding the message back onto the intended recipient. This is the basis for a MITM attack.

- **MAC Flooding**

The process where an attacker will overload a switch by sending a bunch of invalid ARP responses. An overloaded switch will function like a hub and not bother to filter incoming packets and will instead send them to everyone connected.

- **Denial of Service**

The process of preventing access to a network address or website by flooding it with messages and taking it offline.

- **Ping Attack**

A method to cause a DOS, which is to repetitively send ping requests to a server. Since a server is required to respond to them all, a heavy volume of ping requests may overload the server.

- **SYN Attack**

A method of causing a DOS, during a TCP/IP handshake, a server may allocate memory during the SYS call, and wait for the ACK to continue processing. If the client never sends an ACK and keeps on sending SYS calls, the server will eventually run out of memory.

- **Smurf Attack**

A method of causing a DOS by sending ICMP packets to a victims broadcast address. This will then broadcast the ping to all the connected devices that share the same subnet. The return IP address is usually spoofed to be the victims IP address so all responses from the subnet will be re-directed to the victims computer.

- **UDP Flooding**

A method of causing a DOS by sending as many UDP packets as quickly as possible of random ports to a victims network. The target machine will need to respond to all the messages with a destination host not found, causing an overload.

- **Tear Drop**

A method of causing a DOS by sending a fragmented packet that cannot be put back together. The server will issue a re-transmit to the client but the client never responds, enough calls like this will cause the server to overload since it is still allocating memory aside waiting for the re-transmit to send the data back.

- **Land Attack**

Similar to that of the smurf attack however the destination and response IP addresses are the target victims computer causing it to enter a loop.

- **Sock Stress**

A post handshake protocol abuse so useful for TCP/IP connections. During the handshake the server sends a window size variable of 0 to the client, which is supposed to modify it, but the client never does. Meaning that the server will forever wait until the variable changes value before processessing.

- **Distributed Denial of Service (DDOS)**

Simulataneous attacks on a single victim through the use of multiple computers/servers that are called bots. All these bots are controlled by a bot herder, which can coordinate DOS attacks by commanding each of the zombie computers to send ping requests. The bots are usually spread by using trojans, worms and other types of malware.

- **Port Knocking**

A preventitive measure against DOS attacks. All ports are closed initially and a daemon listens for incoming requests. It needs to match a pattern of ports before access is granted.

- **IPv6**

The new protocol for IP, however still has its own issues. It removes the need for MAC addresses, ARP tables and NAT's. This becomes a problem as it removes the idea of anonymity over the Internet. 