## Practice Exam

- **A1. The following is a web log entry:**

```
59.33.254.98 - - [04/Aug/2008:03:36:59 +1000] "GET /scripts/..%255c%255c../winnt/system32/cmd.exe?/c+dir" 404 303 "-" "-"
59.33.254.98 - - [04/Aug/2005:03:37:07 +1000] "GET /msadc/..%c0%af..%c0%af..%c0%af../winnt/system32/cmd.exe?/c+dir" 404 305 "-" "-"
59.33.254.98 - - [04/Aug/2008:03:37:11 +1000] "GET /scripts/root.exe?/c+dir" 404 278 "-" "-"
```

It can be stated that
A. This is an XSS event 

It should not be an XSS event since it does not appear to be adding anything and simply a GET request.

**B. This is a directory traversal attempt**

It appears that it is a directory traversal being conducted every 5 seconds. The GET request, attempts to find c+dir by requesting multiple directories in different structures.

C. This is a buffer overflow attempt

It should not be a buffer overflow attempt as the URI in the request does not appear to be long enough.

D. This is an example of CSRF

Cross-Site Request Forgery (CSRF) is a type of attack that occurs when a malicious Web site, email, blog, instant message, or program causes a user’s Web browser to perform an unwanted action on a trusted site for which the user is currently authenticated. Which is a browser hijacking.


- **A2. Given the following web log entry, it can be said:**

```
211.144.33.202 - - [16/Mar/2008:03:58:53 +1100] "GET /NULL.IDA?CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC%u0aeb%ub890%udacf%u77ee%u0000%u0000%u838b%u0094%u0000%u408b%u0564%u0150%u0000%ue0ff%u9090=x&\x90\x90\x90\x90\x90\x90\x90\x90\x90<snip> \xa4\x90" 404 310 "-" "-"
```

A. A buffer overflow has occurred

I would assume that if a buffer overflowed occured that it would not return a 404, and would instead return a 200 code if the GET request was successful. So it should not be an example of a buffer overflow that has succeeded.

**B. A buffer overflow was attempted but failed**

The concatenation of C's is a clear indicator that it is a buffer overflow attack, with a bunch of HEX commands attached to it. But it appears that whatever it was trying to do has failed since the return code is 404 (Not Found) rather than 200 (OK)

C. A mail blackhole occurred

This web log displays a GET request, and is not on a mail header. It should not be possible to tell that a mail blackhole has occured. An email black hole is basically a valid email address, where messages can be sent and then are immediately deleted. An example of a mail blackhole would be a the return address of a no-reply email.

D. A proxy attack was attempted 

From the information available, it is not possible to tell if it was a proxy attack. Usually if it was a proxy attack, the incoming IP would probably be hidden or masked. This request appears to be coming from an address that is displayed.

It could be the start of a proxy attack but we cannot be sure. Usually a proxy attack would be DOS or DDOS.

- **A3. Examine the following events:**

```
58.102.151.119 - - [07/Aug/2006:03:37:56 +1000] "GET //cgibin/awstats/awstats.pl HTTP/1.1" 404 286 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)"
58.102.151.119 - - [07/Aug/2006:03:37:56 +1000] "GET //cgibin/awstats.pl HTTP/1.1" 404 278 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)"
58.102.151.119 - - [07/Aug/2006:03:37:57 +1000] "GET //cgi/awstats.pl HTTP/1.1" 404 274 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)"
```

What sort of event is recorded above?

A. A normal web event

It doesn't seem to be a normal web event as it appears to be looking for the awtstats.pl file in a bunch of directories. Each request returns a 404, and they are very close together (each request) so it does not appear to be normal web activity.

**B. An attempt to find CGI problems**

It appears that they are looking for the particular file for awtstats.pl which contains log entry information.

C. A Windows 98 server event

The last column with Windows 98 just indicates that the clients browser is running the Windows 98 OS. It does not indicate a server event.

D. A problem with Mozilla 

The Mozilla line just indicates the clients browser details.

- **A4. Following is a web log entry:**

```
host-207-248-240-118.block.alestra.net.mx - - [30/Jun/2005:04:48:59+1000] "POST /cgi-bin/formmail.cgi HTTP/1.1" 404 286 "-" "Mozilla/4.06 (Win95; I)"
host-207-248-240-118.block.alestra.net.mx - - [30/Jun/2005:04:48:59 +1000] "POST /cgi-bin/form.pl HTTP/1.1" 404 281 "-" "Mozilla/4.06 (Win95; I)"
```

From this you can state

A. There is a problem with a Mozilla browser

We cannot tell if there is a problem with the browser with a web log, the Mozilla tag just tells you the browser version it is running.

**B. This is a malicious attack on CGI folders**

It appears that we are attempting to do a POST (send data) to the formail.cgi and form.pl files. However each time it is returning 404. It does not appear to be normal web requests as they are specifying weird files.

C. A port scan

It does not appear that this log contains the information that would result in this web log being a port scan.

D. Server has been compromised 

It has been returning 404 and not 200 so it should be ok that the server is returning Not Found codes for the requests.

- **A5. What is the purpose of an obfuscated url?**

A. Create SQL injection code

SQL injection code would be to just write the code into a text box in the website. 

B. Create a buffer overflow

Like SQL injection, a buffer overflow would not need to obfuscate a URL but would just append characters to it.

**C. Hide the real URL from the user**

Obfuscation means to hide something with jibberish so that the other person cannot see it. If you obfuscate a URL it means that you are hiding the real thing from the user.

D. None of the above 

No.

- **A6. Which of the following is correct?**

A. An Intrusion Detection System restricts ports only
B. An Intrusion Detection System restricts UDP packets only
C. An Intrusion Detection System only protects web servers
**D. An Intrusion Detection System examines packet contents for anomalies**

An Intrusion Detection System (IDS) is used as a gateway to monitor network packets that are coming into the network. It can be used to analyse incoming packets and to look at their contents to determine if it is safe to pass on. If it is not, then the details of the packet are logged and it may still be allowed to pass on.

The IDS is used to "detect" intrusions, logging traffic and all that, it is the job of a IPS (Intrusion Prevention System) to do the job of dropping packets or rejecting them in some other manner.

I believe that an IPS would be the one that is responsible for restricting ports and all that to prevent packets from getting in.

- **A7. Given the following output of the netstat command:**

```
Active Internet connections (w/o servers)
Proto Recv-Q Send-Q Local Address Foreign Address State
 tcp 0 124 appserver.dlk.co:smtp tuan.dlk.com.au:25149 ESTABLISHED
 tcp 0 0 appserver.d:netbios-ssn carpet.dlk.com.au:1025 ESTABLISHED
```

It can be said that:
A. A mail server is running
B. The site has a web server
C. Only Windows shares are being used
**D. Both Windows shares and mail are being used**

In the above we have a netstat command. Netstat is used to monitor the ports that are available on the current computer. The above we have a log entry from a netstat command call.

Proto - Defines the port connection type, which in this instance is tcp.
Recv-Q Send-Q - Defines the size of the data that is sitting in the queue waiting to be sent or received
Local Address - Is the address of the current machine that ran the netstat command
Foreing Address - The address of the machine that is connected.
State - Is the state of the connection.

So the first connection shows that someone is connected to the SMTP (Mail Transport), the second is a connection to the Microsoft Share. The Netbios host is a service that lets machines on a local network talk to each other. In this instance it is possible that there is someone doing some remote access work on the machine.

- **A8. Following is part of a log entry from a mail server :**

```
Mar 16 04:19:32 localhost dovecot: pop3-login: Aborted login: user=<abuse>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:33 localhost dovecot: pop3-login: Aborted login: user=<access>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:34 localhost dovecot: pop3-login: Aborted login: user=<abby>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:34 localhost dovecot: pop3-login: Aborted login: user=<abraham>, method=PLAIN, rip=::ffff:70.228.46.25
```

It can be stated that:

 A This is a brute force attack
 B This is a buffer overflow attack
 C This is a cross-site scripting attack
 D It is a portscan 

 The above is a log from the mail server dovecot. It shows that someone has tried to log into the server with a bunch of usernames that have not worked. Because the usernames entered appear to be in a dictionary order, it can be stated that it is a brute force attack in an attempt to log into the email server.

 It is not a port scan because it does not return the information required.

 - **A9. Log event:**

```
81.168.228.132 - - [07/Aug/2006:22:07:17 +1000] "GET /sumthin HTTP/1.0" 404 1057 "-""-"

The above event represents:
A. A harmless web probe // I would have answered this one.
**B. A web probe that disclosed useful information**
C. A 404 error indicates that nothing happened // Or this one as the 404 returns nothing but the 1057 next to it is suspcious.
D. A server compromise 

The above is a web log of a person performing a GET request on the server. They are attempting to retrieve the location of a folder called /sumthin. It has returned a 404 error meaning that the resource is not found, however it has also returned a code 1057, which I am not sure what it means. 

I would have answered that this is a harmless web probe, however it is difficult to say. The 404 error also indicates that the probe didn't do anything mallicious but it has returned to the user the 1057 code along with the fact that it hasn't done anything.

- **A10. In relation to the possibility of sniffing a switched ethernet network, which of the following statements is correct?**

A. You cannot sniff switched networks
B. You can only sniff wireless networks
**C. Manipulation of the address resolution protocol is needed**
D. A sniffer must be installed on the victim’s computer 

This question appears to be in reference to sniffing a Switch. The function of a switch is to receive packets, see who it is for and then redirect it to the intended target. It uses Address Resolution Protocol (ARP) to link an IP to a Physical Machine (MAC).

If you would like to sniff the packets of a switch, you would need to change the ARP table so that the IP addresses will all map to your physical machine meaning that all the packets will be sent to your machine.

The other way to do it would be overload the switch so it runs out of memory and rather than using the ARP table to find out where the message goes, just broadcasts all the messages to everyone connected to the switch. This would mean that it functions like a hub.

**A11. Following is the partial output of a DNS query: dig -t ANY**

```
;; ANSWER SECTION:
nss.net.au. 86400 IN TXT "v=spf1 ip4:62.69.107.8 a mx a:www.nss.net.au mx:www.nss.net.au mx:CPE-62-69-107-8.vic.bigpond.net.au ~all"
```

Indicate the correct response below

 A. This is the SOA of the domain
 B. This represents a compromised DNS server
 C. This represents DNS cache poisoning
 **D. This relates to SPF authentication** 

 Dig is used to look up information about a DNS. The ANSWER section of a dig is the result of the query usually in the QUESTION section. 

 In this instance it appears that we have requested information about the Mail Server (MX) at the nss.net.au domain. 

 It does not appear to be SOA as it does not return any information regarding the details of the server in how often it refreshes, updates the cached zone file.

 The third address although strange contains the same ip as the IP4, so it could be a IPv6 address

 I think the v=spf1 is a SFP authentication which is used to authenticate the mail server but could be compromised and spoofed.

 - **A12. Buffer overflows and SQL injection are caused by the same basic problem (true or false?)**

 **A. True**
 B. False 

 In web security, a buffer overflow is caused when the input is not sanitized that allows a user to inject code into a format field. SQL injection is caused by someone being able to put pure SQL tags into an input field and then submitting, this causes the script the execute the SQL command.

 - **A13. In relation to software design, two possible approaches are a monolithic structure or discrete components. Which approach is likely to minimize security risks?**

 A. Monolithic
 **B. Discrete** 

 Designing discrete components meaning designing smaller components. If you design components on a smaller scale, it is easier to minimise the risk there because the problems can be isolated into the components. With monolithic components, the code size is so big that to do any proper testing on it would be a tedious and large task in itself and would result in flaws passing through the system undetected.

 Smaller component sizes result in less developer fatigue as the components can be sandboxed within the larger piece of software. Code is more likely to only affect the smaller sized component than leak out into the larger system.

 - **A14. Which of the following vulnerabilities poses the highest risk?**

 A. remote zero day exploit

A remote zero day exploit would mean that the hacker has already compromised your system to be able to inject the zero day exploit. It is something that is pretty serious and can open up the attacker to do anything they wished.

 **B. remote buffer overflow exploit**

 A remove buffer overflow exploit, would lead to a DOS or a DDOS attack as it could potentially crash the server. A buffer overflow can also be used to re-write the return address allowing a hacker to execute other pieces of code.

 I suppose the reason that this is a higher risk is because it is a remote buffer overflow, meaning that the hacker does not need access to your machine and can just do it over a network.

 C. zero day buffer overflow // I would have probably said this.

 A zero day buffer overflow is a new type of buffer overflow where there is no fix.

 D. cross-site scripting 

 Cross site scripting is where someone can inject code into your website. Creating links and redirecting users, they can also then use it to hijack their session cookies.

 - **A15. Some attempts to reduce SPAM include Spam filters on PCs, SPF and Blackholes. Which of these techniques uses a modification to the DNS?**

 A. Blackholes
 B. Spam filters
 **C. SPF**
 D. None of the above 

 A Spam Filter just uses techniques such as reading the header, pattern matching and stuff to determine if an email is spam or not. It does not require the modification of the DNS.

 A black hole is where the message would be dropped or deleted without notifying anyone. The email address that is the black hole is valid, however the sender receives no notification that the email was unsuccessfully delivered. It should not require the modification of the DNS.

 A SPF is a setting that you add to the DNS, it allows the Mail Exchanger (MX) to check if the domain the email is coming from is part of a list that has been authorised by the domain administrators. The list is published in the DNS record, so this would require the modification to the DNS.

- **A16. Hopster is a tool that:**

A. Poisons the tcp/ip tables
B. Uses SSH to tunnel out
**C. Exploits logical flaws in tcp/ip**
D. Is undetectable 

Apparently Hopster is a tool that finds flaws in TCP/IP

- **A17. Examine the following events and answer the question below:**

```
telnet penguin.dlk.com.au 25 // Connecting to a mail server (ESMTP)
Trying 203.36.129.244...
Connected to penguin.dlk.com.au.
Escape character is '^]'.
220 penguin.dlk.com.au ESMTP Sendmail 8.9.0.Beta5/8.8.7; Sat, 25 Apr 1998 19:03:57 +1000 // Connected to the mail server.

helo tuan.dlk.com.au // Establish connection with mail server to start SMTP session
250 penguin.dlk.com.au Hello damian@tuan.dlk.com.au [203.36.129.241], pleased to meet you // Mail Server Response OK

mail from:damian@tuan.dlk.com.au // Sending email command.
250 damian@tuan.dlk.com.au... Sender ok // Verified OK

rcpt to:fred@penguin.dlk.com.au // Adding recipient
250 fred@penguin.dlk.com.au... Recipient ok // Recipient OK.

data // Enter the content of the email.
354 Enter mail, end with "." on a line by itself
Hi Fred!
Just testing.
. // Finishes the email and sends to the mail server.

250 TAA11462 Message accepted for delivery
quit // Disconnect from mail server.
221 penguin.dlk.com.au closing connection
Connection closed by foreign host.
```

From the following statements, select the one that is definitely correct:

**A. SMTP conversation**
B. Abnormal mail conversation
C. SPAM
D. None of the above 

It is not an abnormal mail conversation as the output is just to send an email using Telnet.

It isn't SPAM because we do not have the information that will tell us that it is SPAM, there is no information from the message headers in the Telnet log. 

The helo tuan.dlk.com.au starts a SMTP conversation with the user and the mail exchange.

- **A18. In relationton the events in Question 17 above, how many mailservers are involved in this example?**

 A. 1
 **B. 2**
 C. None
 D. Unable to determine from the data presented 

 There appears to be 2 mail servers, the first one you connect to with the Telnet command as the response is ESMTP. Then you say helo to tuan.dlk.com.au which starts the SMTP conversation with another mail server.

 - **A19. Examine the following event:**

``` 
telnet open.web.proxy.org 80 // Connecting to web server.
Trying 192.168.1.1...
Connected to 192.168.1.1.
Escape character is '^]'.
CONNECT host.org:25 HTTP/1.0
HTTP/1.0 200 Connection established
220 host.org ESMTP Sendmail 8.11.6/8.11.6; Tue, 19 Feb 200214:16:51 -0800 (PST)
```
The above event represents:

 A. Normal network connection
 **B. Use of proxy for email**
 C. A break-in to a mail server
 D. A break-in to a proxy server 

The above command is to log into a web server using Telnet, and the host that we connected to is a Mail Exchange. Effectively we have tunneled into a server to send email. It is not a break-in because we have simply connected to it and the log outputs no mallicious text.

- **A20. Following is an extract from a mail log:**

```
Aug 10 10:29:14 tuan postfix/smtpd[11005]: reject: RCPT from unknown[210.110.151.124]: 554 Service unavailable; [210.110.151.124]
blocked using bl.spamcop.net, reason: Blocked – see http://www.spamcop.net/bl.shtml?210.110.151.124; from=jones@yahoo.com to=damian@dlk.com.au
```
Indicate what it represents

A. Yahoo mail is not working
B. A postfix error
C. dlk.com.au is blackholed
**D. A remote site is blackholed** 

So the hostname of the sender is unknown (210.11.151.124), that address is blocked. The website bl.spamcop.net indicates that the IP has been blocked for some reason, so that it is black holed or marked as a place where known SPAM attacks have come from.

It is not a case of Yahoo mail not working because otherwise it would not have gotten this far, the email has been redirected somehow along the way possibly using an open mail server.

- **B1. Web servers are involved in a variety of security issues at present. Two of these are Cross Site Remote Forgeries (CSRF) and Cross Site Scripting (XSS). Explain the difference between these two potential exploits. How would you manage web servers to eliminate these problems?**

Cross Site Request Forgery is a type of attack that happens when an external source (Website, Email, Blog, IM) causes the current users web browser to do something that the user did not want to do. It is an example of Browser Hijacking, where the mallicious program has taken over the clients browser and starts to do unauthorised things with it. CSRF can be used to take over the browser so that if a user is on a trusted website (Internet Banking), the browser can start sending information back to its creator or even take over the controls and start handling the input for the user. In a way it can be seen as a mallicous remote desktop, so that when the user is on an important site, the program takes over.

Corss Site Scripting (XSS) on the other hand makes modifications to the website that the user is on. It allows to inject client side scripts into the website. It mixes the mallicious content with the real content that is being delivered. 

Reflected XSS (Non persistent XSS) is where parameters from a HTTP form are used immediately on the server side without checking the input at all. If the page is then to redisplay the inputted text, then the code written in the form will be displayed. 

Stored XSS is where the changes injected by the script remain on the site permanently. Happens on a user forum mainly since the text is then added, if not properly sanitised then it can be used to place links and all that stuff. An example of this would be filling in a online profile and in a section of text you add your script. Because scripts are not shown to the user, when someones else goes to load the page and the script is run in the background allowing you to steal their session data or even create re-directs.

Thus the difference between CSRF is that it uses the browser, and XSS uses the webpage.

- **B2. In relation to the security of a PC various network pathways that are open at any point in time. What paths could traffic flow both into and out of a PC? How does malicious software utilize these pathways to exploit the system for its own use? **

This question is more about the applications that always keep ports open at any given time. Things that are listening and always open rather than specifically which ports are open.

There are many different applications that maintain persistent network pathways, two from an example would Email and Instant Messaging. Because both of these have ports open all the time to send/receive messages, they can be vulnerble to mallicious software as they can hijack those ports. In particular web traffic is served through port 80, meaning that this port is generally not filtered allowing data to come in and out as they please. Mallicious software can use this to its advantage as it knows that it is always going to be open so it would be free to send/receive data through it.

Also most of the traffic through port 80 would seem to be trusted meaning that it will not be dropped if it was being used for mallicious purposes. Furthermore mallicious software could also hijack the ports being used by email and IM and send messages out on those ports, it could also use those ports as incoming ports and the mallicous program can just listen to that port instead.

- **B3. There has been much discussion recently about the design of software, in particular web browsers. Some newer browsers such as Google Chrome have taken a different path to software design. In this regard it is similar to other software we have studied such as Postfix. Explain the concepts of sandboxing and monolithic vs discrete components when designing software. Why is this approach being used in new software design?**

Sandboxing - The concept of isolating system resources such as memory and disk space to be used for that application only. Because of the threat of buffer overflows, it is conceivable that in a shared memory environment, you can jump from the application memory reserved for one application to the OS memory, allowing you to execute commands on the OS itself. This is a big issue with buffer overflows as the return address for a function could be the location of a system DLL or system file that allows for the escalation of user priveledges. By using the sandbox concept, the memory allocated to that application is seperated from the memory of the rest of the system, with the intention that the memory that it needs to use is all within there and should not be able to access anything else.

Monolithic vs Discrete Components - The concept for keeping software components as small as possible to reduce the possibilities of vulnerabilities being introduced in the code. With smaller components, the code itself is supposed to be more isolated, reducing the possible areas of vulnernaility. Discrete components also reduce developer fatigue in designing software so that they hopefully will introduce less bugs since the vulnernabilities is a consequence of software being designed by people.

This concept is being used becaue it is seen as more agile, smaller components allow for faster fixes since their components and design should be isolated from the rest of the system. Also sandboxing isolates the system the application is running in from the rest of the OS reducing the possibilities of exploits creeping out.

- **B4. In providing effective security of a network it is advisable to use a layered approach. Explain the difference between security at the network level compared to security at the desktop computer level. A firewall may deployed at both levels. Why is this?**

Security at the network level would include IPS (Intrusion Prevention System), Firewalls, IDS (Intrusion Detection Systems), Switches, NATS and all that because it protects the network from the outside. All traffic travelling in and out of the system will be monitored, with both IDS and IPS having the capability to inspect a packets contents before it goes out of a network or back into a network. It can be used to protect against DOS, DDOS, Worms and other types of mallicious attacks like that. 

Security at a desktop computer level involves anti-virus, personal firewalls, encryption, user logins and things like that. The security here is more of a end user security where if all else fails that have this system as a last defence. It is more to prevent what the individual user can and cannot do on the network, rather than protecting the network as a whole. 

A firewall may be deployed at both ends incase something gets through, and also the fact that some people disable their personal firewalls because it is seen as a hinderence. Security at a desktop computer level can also include encrypting hard drives, and passwords which are not neccessary at the network level because it has nothing to do with traffic, as security at the desktop level is not only against outside threats but also to physical internal threats. 

- **B5. Buffer overflows have been an ongoing cause of security vulnerabilities. What is a buffer overflow? Explain three different techniques that can be used to reduce the impact of buffer overflows. In your answer differentiate between measures that manage symptoms with measure that treat the underlying causes.**

A buffer overflow is where the an attacker has written past the point of the allocated memory that the system has allowed. For a particular task, the system has allocated a finite level of resources, but because these resources share the same location in memory as the rest of the system, it is entirely possible especially in C/C++ programs to write off the end of the allocated buffer.

By overflowing the buffer, it allows you to override sections of code that sit in the next address space, sometimes containing return addresses of functions or could also contain random bits of data from another application.

Three techniques that can be used to reduce the impact of buffer overflows -

Using correct programming - Checking the size of the input before allocating it to the buffer would reduce the possibilities of it happening.
Sanitizing Input - Cleaning up the input to prevent oversized input from being entered.
Sandboxing - Sandboxing the application would mean dedicating resources to it, overflowing the buffer would only overflow into the sandboxed environment. 
Keeping Software up to date - Make sure that you have the latest version of software.
Address Space Randomisation - Relocate the critical DLL's on each reboot so the hacker will have a harder time to find something to do with their buffer overflow.
NX bit - Adding things into your non-executable so pieces of memoy cannot execute.

### Practice Exam 2

- **1. Explain the difference between the information that can be obtained by netstat and dig. Which tool provides a more accurate indicator of services running on a host? Why?**

Both netstat and dig return the same information. However dig provides a more accurate indicator of services running on a host, because it does.

- **2. 'Script kiddie' type security events are the most common events recorded at present. Explain the types of risks posed by such intruders, with specific reference to two applications: a web server and a payroll system.**

Script kiddie generally a amatuer who just downloads and runs scripts for fun, they do not actually write any code themselves. They generally download programs and find exploits on the net and use them as instructed.

The types of risk that these script kiddes pose is that may not neccessarily be aware of what they are doing. In regards to a web server, many script kiddies think that for fun they can download the script and then run it, inputting the web server address and then trying to run the script, this may lead to DOS or DDOS attacks.

Because they also do it for fun or profit, they  may not also know what they are doing which may end up hurting someone. They can be inadvertently running a program on behalf of someone else leading to DDOS attacks.

- **4. Below is an extract of an Apache web server log. Examine the events logged and answer the questions below.**

```
203.89.231.199 - - [18/Jul/2002:03:55:11 +1000] "GET /scripts/..%%35c../winnt/system32/cmd.exe?/c+dir HTTP/1.0" 400 286 "-" "-" 
203.89.231.199 - - [18/Jul/2002:03:55:12 +1000] "GET /scripts/..%25%35%63../winnt/system32/cmd.exe?/c+dir HTTP/1.0" 404 303 "-" "-" 
203.89.231.199 - - [18/Jul/2002:03:55:12 +1000] "GET /scripts/..%252f../winnt/system32/cmd.exe?/c+dir HTTP/1.0" 404 303 "-" "-" 
209.164.24.120 - - [20/Jul/2002:04:55:41 +1000] "GET /default.ida?NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u9090%u8190%u00c3%u0003%u8b00%u531b%u53ff%u0078%u0000%u00=a~ HTTP/1.0" 400 323 "-" "-"
```

(a) Two different types of events are represented above. What are they?

One is trying to traverse the web directory, trying to open a directory that contains cmd.exe. It is going through the system32 or locations that it thinks that system32 is to execute that file. The command request results in 400, meaning it was a bad request, possible due to the input, the other 2 requests being 404 means that it was not found at that location.

The other event is an attempted buffer overflow where it has put into the URI a buffer of N's followed by some hex code. Again it has returned 400 saying that the input was not recognised or the server did not understand it.

(b) What are the implications of these events for an Apache server? Why?

The implication of being able to execute cmd.exe will allow the attacker to know that they can run the command prompt. This allows then for customer applications or full directory traversal since the command prompt would have full access to the server and its directories possibly allowing for a mallicious attack or escalation of priveledges. 

The buffer overflow attack on the other hand depending on what the HEX code does could lead to an escalation of priveledges or a fail open scenario that allows access to resources on the web server that were otherwise unreachable. Buffer overflow attacks can also cause DOS to take down the server.

(c) Explain possible management options to deal with such events.

To deal with the first one, you could black hole requests that go to some addresses meaning that they do not actually get a response. In addition, you could also not place the cmd.exe files in folders that are accessible by the web server from external requests. Not entirely sure you could sanitise the input because it is just a web request, however you could also try doing that but the best idea would be to move system files such as that to a location that is not accessible from the outside, meaning usually not to place exe's in the www folder.

Supposedly you could introduce some type of sanitation of the events before you do any type of request this would reduce the possibilities of a successful buffer overflow attack. 

You could also implement a IPS or IDS on the server side to inspect the packets before they reach the web server meaning that packets that are seen to be mallicious can be dropped and would never reach the server at all.

(d) Explain possible risks associated with the above events.

Escalation of priveledge, DOS, DDOS, injection attacks, XSS.

- **5. The nature of email means that it is hard to verify email sources. How do spammers exploit these weaknesses?**

Using Telnet to send emails, logging into proxy servers to send emails. Using PHP to generate headers, using open email exchanges to allow sending of email from basically anywhere.

Also bots can be used to generate legitimate emails from verified sources (Yahoo, Gmail, Hotmail), it is difficult to say what is legimate and what is not even if it has not been routed. It is also completely possible for emails to be sent from a legimate source but the victims computer has been compromised.

- **6. Discuss the contents of a web server log and outline the types of events that might indicate a security problem.**

A web server log should contain the following

Client IP - Date Time - Request Type - URI - Request Status - Browser and OS History

The events that may indicate a problem would be the client IP, if it is a known black listed site, and also if the requsts are coming from the same client in rapid succession

The contents of the URI usually give away whether it is a specific type of attack as it would be trying to access resources that appear out of the ordinary. The request status will give an indication of whether the request as successful or not, 404, 400 usually meaning that it was not successful, 200 means that it has successfully returned the requested object to the user.

A buffer overflow would be shown in the URI as it would have the normal request plus some long buffer and hex code.

- **7. The problem with a lot of log files is that they contain too much detail. Explain how you would go about sifting the relevant from the irrelevant in a log.**

Look for patterns, remove certain fields that may not be neccessary, look for return codes.

- **8. Determining the services running on a server is an essential first step in checking security. Explain how you would go about identifying active services.**

Using netstat would provide an indicator of the current services or ports that are open and connected. It can be used to verify the number of active connections, anything that appears out of the ordinary will be listed in the netat output. 

Services that are active would have a ESTABLISHED, with the SendQ and PrevQ or something that will show the content that is awaiting to be sent. Services that connect to the outside world will show up in this log. 

- **9. If you wanted to survey a network, how would you go about it? Explain the procedures and tools that you would use.**

This is about network reconnaissance

Port Sniffing 
Driving Around For Wifi
Social Engineering
Footprinting with Telnet (finding software versions)
Enumeration
Ping - Looking for vulnerable targets
Whitebox, Blackbox Testing
Traceroute
NMap also gives you a list of open/closed ports.

- **10 . "It is safer to check for viruses at the edge of the network." Do you agree? Do you also need workstation-based virus scanners? Explain.**

It is better to have all round checks, however it would be better to have virus checkers at work stations because it is more known that humans are the ones who open the files and things that generally start the infection. It would be good to scan the packet for potential viruses and drop it if it contained something mallicious but yeah. All round defence in depth would be the right way to go, so doing virus checking in 2 places isn't always the worst thing considering it also is entirely possible for users to switch off their anti-virus especially if it impedes with work.

- **11. Some of the most common alleged "intrusions" of networks are actually port scanning events such as the use of Netcat or Nmap. Explain the implications of the use of tools. What information do they provide?**

NMap or Network Mapper is a tool to perform network scans, or to find what services a host is running. 

With the right options you can also see the MAC Address and devices at a particular IP. It can take a guess at the OS

Basic NMap will provide -

```
Host is up (0.0037s latency).
rDNS record for 61.9.173.37: go.bigpond.com
Not shown: 986 filtered ports
PORT     STATE  SERVICE
80/tcp   open   http
111/tcp  closed rpcbind
135/tcp  closed msrpc
139/tcp  closed netbios-ssn
443/tcp  open   https
445/tcp  closed microsoft-ds
1433/tcp closed ms-sql-s
1434/tcp closed ms-sql-m
4444/tcp closed krb524
4662/tcp closed edonkey
6346/tcp closed gnutella
6666/tcp closed irc
6667/tcp closed irc
6668/tcp closed irc
```

Here we can see that only ports 443 and port 80 are open, this will be for normal web traffic. 