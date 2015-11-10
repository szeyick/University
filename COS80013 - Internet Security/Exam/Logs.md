## Understanding Logs

### Web Server Logs

A web server logs all activity that has come through the server.

The conversation is as such

```
Client: Hello, how's it going are you there?
Server: Yes, hello I am here, what do you need?

Client: I need, somepage.html, and everything else in that page
Server: OK hang on

Server: OK here it is
Client: Thanks

Server: All good, it has been delivered
```

The server with each interaction with the client will log down what was done.

An example output log is as follows - 

```
59.33.254.98 - - [04/Aug/2008:03:36:59 +1000] "GET /scripts/..%255c%255c../winnt/system32/cmd.exe?/c+dir" 404 303 "-" "-"
59.33.254.98 - - [04/Aug/2005:03:37:07 +1000] "GET /msadc/..%c0%af..%c0%af..%c0%af../winnt/system32/cmd.exe?/c+dir" 404 305 "-" "-"
59.33.254.98 - - [04/Aug/2008:03:37:11 +1000] "GET /scripts/root.exe?/c+dir" 404 278 "-" "-" 
```

Explaining the columns -

IP - The IP address of the **Client** trying to make contact
Date/Time - The date and time of this request.
GET - The request method, it'll either be GET, POST, HEAD. GET means to "give me that file"
URI - The address of the page requested but not queries ?this=that (parameters) just the "folder reference"
URI Query - The ?this=that if there are any, the parameters
Status - The response the server made to that request. (404 = not found, 303 = maybe it is somewhere else, try another address, 302 = found, 278 = redirect) 

It appears in the above log that the client is attempting to randomly access directories that are all returning 404 not found.

Normal web traffic should look like -

```
212.209.212.66 - [29/Jul/2001:00:35:33 -0500] "GET /data-mining.htm HTTP/1.1" 200 11631 "http://internetmarketingengine.com/" "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)"
```

Status - If it is 200, it means OK.
Size - The 11631 means the size of the object returned in bytes.
URL - The URL following it is the referring URL (where it was accessed from (could be google or anywhere))
Browser - The browser/version and platform that the person was using.

### Buffer Overflow in Web Log

Below is an example of a web log that has a buffer overflow attempt.

```
211.144.33.202 - - [16/Mar/2008:03:58:53 +1100] "GET
/NULL.IDA?CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC%u0aeb%ub890%udacf%u00%u0000%u838b%u0094%u0000%u408b%u0564%u0150%u0000%ue0ff%u9090=x&\x90\x90\x90\x90\x90\x90\x90\x90\x90
<snip> \xa4\x90" 404 310 "-" "-" 
```

The URI contains a buffered text along with HEX code that would do something. However the status is returned 404, meaning that it has failed.

I suppose that if it had succeeded then the return code would be 200 rather than 404.

### Finding Things On Web Log 

```
58.102.151.119 - - [07/Aug/2006:03:37:56 +1000] "GET //cgibin/awstats/awstats.pl HTTP/1.1" 404 286 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)"
58.102.151.119 - - [07/Aug/2006:03:37:56 +1000] "GET //cgibin/awstats.pl HTTP/1.1" 404 278 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)"
58.102.151.119 - - [07/Aug/2006:03:37:57 +1000] "GET //cgi/awstats.pl HTTP/1.1" 404 274 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)" 
```

In the above log we have -

GET - The type of request
URI - The object to retrieve (including the HTTP/1.1)
Status - Return code
Browser - The browser used to make the request on operating system.

It appears that this person is trying to traverse the directory looking for awstats.pl. The file is a perl script that shows web statistics. 

All requests returned 404 meaning that it wasn't found.

### More Web Logs

```
host-207-248-240-118.block.alestra.net.mx - - [30/Jun/2005:04:48:59 +1000] "POST /cgi-bin/formmail.cgi HTTP/1.1" 404 286 "-" "Mozilla/4.06 (Win95; I)"
host-207-248-240-118.block.alestra.net.mx - - [30/Jun/2005:04:48:59 +1000] "POST /cgi-bin/form.pl HTTP/1.1" 404 281 "-" "Mozilla/4.06 (Win95; I)" 
```

POST - The type of request (POST will be to accept data)
URI - The object it is trying to access
Status - Return Code of the request, 200 for success, 404 for not found

```
81.168.228.132 - - [07/Aug/2006:22:07:17 +1000] "GET /sumthin HTTP/1.0" 404 1057 "-""-" 
```

This appears to be a request to access a folder directory that is not found. The 1057 return code is interesting because it doesn't say what it is.
