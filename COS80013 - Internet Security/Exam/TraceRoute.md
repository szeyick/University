## Trace Route

Trace route works by providing the path it takes to connect to a website. The signal starts at your local computer, then moves to the ISP, then onto the main network, and through a bunch of other points until it gets to the webserver that contains your request.

It displays the path the signal took as it travels around the internet to the website and displays the response times where it hit each stop point along the route.

The below is the output log of a trace route to www.ign.com

```
traceroute to www.ign.com (203.5.76.17), 
30 hops max, 60 byte packets

 1  136.186.255.129 (136.186.255.129)  0.545 ms  0.534 ms  0.533 ms
 2  * * *
 3  gigabitethernet1.er1.swinburne.cpe.aarnet.net.au (202.158.200.141)  23.289 ms  23.176 ms  23.447 ms
 4  ge-1-1-0.bb1.a.mel.aarnet.net.au (202.158.200.197)  1.950 ms  2.021 ms  1.926 ms
 5  ae9.pe1.wmlb.vic.aarnet.net.au (113.197.15.94)  1.914 ms  1.885 ms  1.892 ms
 6  et-1-3-0.pe1.mcqp.nsw.aarnet.net.au (113.197.15.8)  19.654 ms  18.469 ms  18.701 ms
 7  et-5-1-0.pe1.brwy.nsw.aarnet.net.au (113.197.15.5)  15.727 ms  15.862 ms  15.858 ms
 8  113.197.15.103 (113.197.15.103)  17.047 ms  17.034 ms  17.004 ms
 9  a203-5-76-17.deploy.akamaitechnologies.com (203.5.76.17)  16.005 ms  16.637 ms  16.164 ms
```

Each row here represents a hop along the route.

The columns as described as follows -

Hop Number - The junction number from the origin point (your computer) to the destination.
Domain/IP - The IP address of the router it has gone through or the domain name it has gone through.
RTT (Round Trip Time) - The time it took to get to that point and back to you. The 3 times indicate the 3 packets it sent.

The idea is to have consistent RTT times.

If we see * in the RTT it means that we may have lost some packets along the way. If we also get an increase in the RTT, then there may be an issue with the router at that point.