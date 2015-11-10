## Netstat Logs

Netstat provides a set of tools to provide you a indication of the health of your network.

For example we may have -

```
Active Internet connections (w/o servers)
Proto Recv-Q Send-Q Local Address Foreign Address State
tcp 0 124 appserver.dlk.co:smtp tuan.dlk.com.au:25149 ESTABLISHED
tcp 0 0 appserver.d:netbios-ssn carpet.dlk.com.au:1025 ESTABLISHED 
```

The above snippet shows the current connections that exist between your machine and other machines, as well as sockets LISTENING for connections from other machines. It can show you which programs are currently active on your network.

Proto - 

The socket type (either TCP or UDP)

Recv-Q and Send-Q - 

How much data is in the socket waiting to be read (recv) or send (send). If 0, everything is OK, if non-zero then we may have a problem.

Local Address and Foreign Address -

Which hosts and the ports that are connected. The local end is always the computer running netstat, the foreign address is about the other computer.

State - 

The state of the sockets, LISTEN (means a socket is waiting for someone to connect), ESTABLISHED (ready for communication)

In the above, it seems that someone is connected to the mail server (SMTP), the netBIOS appears to be allowing machines to communicate with each other over a LAN.

