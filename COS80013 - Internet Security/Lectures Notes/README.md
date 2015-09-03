## Lecture 5 Notes

This contains rough lecture notes on lecture 5.

### NAT (Network Address Translation)

It re-addresses IP packets and re-routes it somewehre else. It is good because only the router can be compromised as the IP addresses of servers and stuff cannot be targeted.

It only has a public IP address to access it, then the private IP address for everything within the NAT's network. It hides the traffic as soon as it comes into the network.

### Common Protocols

IP and ICMP both use IP addresses. ICMP used by ping, does not use ports at all.

### TCP/IP (Transmission Control Protocol)

Provides reliable delivery without losing packets and in the correct order, but is not fast. It will wait until it gets the correct packet before continuing to wait to receive the next one.

Both endpoints need to know the packet sequence number is to receive the data properly.

### UDP (User Datagram Protocol)

Really fast but no handshake to confirm connection, the data is just sent. No idea who sent it.

### Traceroute

The TTL (time to live) timer is decremented by 1 each time it passes through a router. Once that timer gets to 0, it sends it back to the user with the routers IP, and the name of the router.


