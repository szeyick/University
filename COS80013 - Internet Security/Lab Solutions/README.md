## Lab 2 Notes

- Wher eis fstab located

fstab is located at /etc/fstab

- What does fstab do

fstab is a configuration file that contains information of all the partitions and storage devices in your computer. The file is located under /etc, so the full path to this file is /etc/fstab. /etc/fstab contains information of where your partitions and storage devices should be mounted and how.

- what is the default ip address of the server

192.168.100.104

- what is the loopback address

127.0.0.1

- how many errors have occured

0

- list the daemon processes that are running on the server (only the ones ending in D)

keventd, kapmd, kswapd, kupdated, mdreoveryd, kjournald, khubd, kjournald, vmtoolsd, cupsd, vmtoolsd, klogd,

- which ones has vulnerabilities

- find access_log

/var/log/httpd/access_log

- read the log and find how many times you've accessed

0

- What is the IP address of the windows machine

192.168.100.130

- Type in netstat and view how many TCP connections

0

- Check the access and error logs, what changes

There is a change because there has been a request packet sent from the XP machine to the linux box and it is recorded in the access_log.

- Using the web brower check access the Linux VM and check the logs again, what has changed.

It has retrieved images and icons from a GET request that is from the browser.

- Using wireshark start a session and determine which colour is for valid web traffic

yellow, the black denotes a failure in this instance the entered URL is unreachable.

- Using WS FTP attempt to establish a FTP session and then view Wireshark, what colours are there

Purple denotes traffic of request and response, the grey is ACKs appear to be data transfer

- Using telnet log into the Linux machine and check the wire shark display

Telnet is the new protocol that is used, the colours dont appear to change.

- Try netstat again, how many connections

3 connections

- Try netstat from the linux machine, how many connections

2

- On the host machine, how many connections (netstat)

A lot, 15

- Using tracert mercury.ict.swin.edu.au, what is the IP address of the router between you and the mercury server

136.186.15.2

- How many hops

There are 2 hops, so the router, 2 hops then mercury server.


