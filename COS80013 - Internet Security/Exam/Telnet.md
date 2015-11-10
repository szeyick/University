## Telnet

### Emails

Telnet provides the capability to send emails.

```
telnet example.com 25 
ehlo example.com // This appears to just have the server identify itself and start the SMTP conversation.

mail from: username@example.com
rcpt to: friend@hotmail.com, friend2@hotmail.com, etc

data
Subject: My Telnet Email

Hello,

This is an email sent by using telnet

From,

Me

quit
```

This will send an email from telnet from the server.

Furthermore we can also use Telnet to check or read email

```
telnet example.com 110

user username@example.com
pass yourpassword

list
+OK 
1 897 // The 897 represents the size
2 5136
3 1884

retr 2 // Retrieve email 2 
dele 1 // Delete email 1
```

The return codes that follow - 250 means OK

```
telnet penguin.dlk.com.au 25 
Trying 203.36.129.244...
Connected to penguin.dlk.com.au.
Escape character is '^]'.
220 penguin.dlk.com.au ESMTP Sendmail 8.9.0.Beta5/8.8.7; Sat, 25 Apr
1998 19:03:57 +1000
helo tuan.dlk.com.au
250 penguin.dlk.com.au Hello damian@tuan.dlk.com.au [203.36.129.241], pleased to meet you
mail from:damian@tuan.dlk.com.au
250 damian@tuan.dlk.com.au... Sender ok

rcpt to:fred@penguin.dlk.com.au
250 fred@penguin.dlk.com.au... Recipient ok

data
354 Enter mail, end with "." on a line by itself
Hi Fred!
Just testing.
.
250 TAA11462 Message accepted for delivery
quit
221 penguin.dlk.com.au closing connection
Connection closed by foreign host. 
```

We appear to connect to penguin.dlk.com.au which is a mail server, but then we say helo to tuan.dlk.com.au is the SMTP server that we want to connect to and start chatting with.

354 - Is the response from the server when the "data" command has been entered. It will send all the data until the . on the line by itself.

### Telnet Web

Telnet can also be used to get to the root page of a website. Inputting HEAD or GET commands will return information about the page and more importantly the server that it sits on -

```
telnet opax.swin.edu.au 80
Trying 136.186.1.124...
Connected to bud.cc.swin.edu.au.
Escape character is '^]'.
HEAD / HTTP/1.0 

HTTP/1.1 200 OK
Date: Tue, 10 Nov 2015 02:01:11 GMT
Server: Apache/2.2.15 (Red Hat)
Last-Modified: Tue, 02 May 2006 04:08:26 GMT
ETag: "80106-18a-412c652a1c280"
Accept-Ranges: bytes
Content-Length: 394
Connection: close
Content-Type: text/html; charset=UTF-8

Connection closed by foreign host.
```

Once you type in telnet to establish the connection, you type "HEAD / HTTP/1.0". It returns the time on the server, the software version its running.

The GET command also can return you the session cookie. The connection closed just means that the request was completed with status code 200.