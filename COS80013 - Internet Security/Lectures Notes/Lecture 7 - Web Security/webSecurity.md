## Web Security

###HTTP Sessions

Is particularly useful when stateful information needs to be maintained when a server and client interact. The general process of how this happens is through the following steps -

- Information is passed back to the client through POST or GET HTTP requests.
- The client sets a cookie and puts information in it, it sends it back with each request.
- The server sets a cookie that contains the session ID, it compares this session ID with the list of sessions it has started and "remembers" data for that particular client.

It is a process that is vulnerable to sniffing.

###HTTP Session Hijacking

An attacker can possible hijack a HTTP session by packet sniffing to find out the session ID. It can then use the found session ID and take over the session by writing the cracked ID into its own cookie.

To mitigate against this type of hijacking, we can set cookies to expire or use HTTPS.

Additionally a attacker can use XSS (Cross Session Scripting) to read the cookies of authenticated visitors to a site. They can then use the same tactic to take the session ID for their own use and imbed into their own cookie.

To mitigate against this type of hijacking, the server can filter the input/output and the client can disable javascript.

###Sandboxing

Is the concept of setting up a virtual container that restricts the access rights of a program. A virtual machine is a type of sandbox since the programs installed on it only access virtualised hardware and not the real physical hardware of the machine.

This however can still be exploited by a concept called sandbox escaping. The way that it works is something like below -

- The sandboxed application runs code in the sandbox.
- The code runs an application on the operating system.
- The application on the operating system then runs the malicious application.

###Cross Site Scripting

Allows spammers to inject executable code on web pages that can be used to download and install malware, steal cookies (session ID) or other pieces of hidden data on the client machine.

**Reflected XSS Attack**

Is where the loaded webpage contins an executable script. This script is then used to download the malware. It is effective on sites that appear to be script heavy with prompts to change browser settings.

**Stored XSS Attack**

Is where the mallicious script to install malware is stored on a server. The script is then executed when the request is made to access the script/page is done by the user.

**DOM Based Attack**

Scripts within the browser have access to the browsers DOM (document object model) that contains everything displayed on a webpage. Mallicious scripts can then be used to modify the displayed webpage, send stored data and change links on a page. 

This type of attack avoids storing and transmitting scripts on a server so it is not detected.

A type of attack is where the user provided text is not filtered or cleaned up before sending it back to the server -

```
http://www.something.com?name=<script>alert(document.cookie);</script>
```

In the above example, the URL passes a user entered parameter "name" back to the server. However the inputted text is an entire script, this may allow users to access information on a page/server that they may not normally have access to. To protect against this, the user input should be checked before the request is sent to the server.

###JQuery Injection

JQuery is a set of API's that makes programming in JS easy. However a pitfall to it is that it can be used to hide mallicious functions and can be used to reveal hidden page content (fields, passwords, secrets). You can mitgate against this by reviewing code, sanitising input and not using unsafe functions.

###Passthrough Functions

PHP and MySQL have functions that will allow you to pass values (i.e. filenames) through to the OS. If this isn't checked it can allow a user to read, write, upload and download files on a server.

To mitigate against this, we can restrict file access to read or execute only and never run a server as root.

###Web Security Best Practices

- Restrict web users to only be able to execute scripts and read HTML pages, restrict source code access of scripts to other scripts only.

- Store scripts and files in a folder that cannot be access through the web.

- Filter characters that can be used to generate scripts, they should be properly escaped to make them inert.

- Restrict database access to the web server, the client should not be able to access a database directly.

- Never run a web server as root and always change the password.

- Uploaded content (images, files, videos etc) should be stored on a different domain that the website is served on, it prevents scripts from accessing the main domain.

- Do not host transaction systems and main website systems on the same host to prevent cross accessing.

- Disable directory browsing of a web page, this essentially stops browsing of a web page like a file system.

- Filter and check all user input to ensure scripts are injected.

- Encrypt passwords or use SSL, and never store passwords on a server, only store their hashes.

- SQL commands should not be sent as part of the URI, scripts should execute on the users behalf.

- Debug and comments should be disabled on production servers, it gives attackers insights into what works properly and what doesn't.

- Use POST to sent information and GET to navigate.

- Authorisation certificates should be kept up to date, and cookies should not record user data. Terminate a session if input seems dodgy.

- Maintain login state with cookies, but terminate as soon as the session is over ($_SESSION['user'] = $user).

###XML and SOAP

SOAP is an XML protocol for transmitting data and executing functions on remote objects through port 80. It allows remote execution of code that bypasses port blocking since port 80 would always be open.

Scripts can be wrapped in XML, passed through port 80 as web traffic and executed on the server.

###Phishing

The idea of baiting a user to provide information. Usually through methods of emails and other types of spam.

###Spear-Phishing

The idea of targeting particular people or companies rather than just mass emailing like with normal phishing. 

###Whaling

The idea of spear phishing but with high profile targets (i.e. politicians, CEO's and celebrities).

###Watering Hole Attacks

Targeting sites and forums where people who have been targeted by phishing scams discuss their issues and target those sites to provide misinformation to their users to redirect people to their own sites.

###Pharming

The idea of setting up fake websites for users to enter information to. This can be done through registering domain names that are similar to a real one and making it look identical. This is called **cyber squatting**.

Another way to do it is to perform DNS cache poisoning to redirect correctly entered URL's to the fake website.

The prevent falling for pharming attacks, you will need to look out for inconsistencies in the website, perform a **traceroute** to check for the location of the website or download plugins that will detect changes in certificates (SSL).

###Log Files

There are various methods to detect if you are being attacked -

- Check bandwidth usage - attacks will raise the bandwidth used.
- Netstat/Wireshark will detect connections and traffic coming in and out of a server.
- Tripwire, sfc (in Windows) and ArpWatch will detect modifications of system files.
- Read system logs and use LogCheck/LogWatch to monitor log files for unexpected access.