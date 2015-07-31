## COS80013 – Internet Security

### Linux Crash Course

**Summarise the topics you explored and the activities that you did during this lab.**

The topics in this lab covered the basics of navigating around a Linux system through a terminal session. The activities included, file and folder manipulation, file searching, directory navigation, network, user logon and process lookup.

**Classify (group) these topics and actions under appropriate headings. Do not just copy the headings used in the instructions.**

**Directory and File Information Commands**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| ls (list directory) | cd (change dir) | pwd (working directory) |
| file (file info)    |                 |                       |

**System Information Commands**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| ps (process list) | df (free disk space) | echo (system out) |
| uname (PC info)   | lsof (open files)    | fg (process foreground) |
| top (running processes)   | cat (concatenate and print file output    | kill (kill process) |

**Network Information Commands**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| ping (request | netstat (status) | dig (DNS lookup) |
| nslookup (server query)    |    smbstatus (Samba status)             |                       |

**User Information and Logon**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| who (user login) | whoami (current user) | finger (user lookup) |
| history (shell history)    |     su (logon user)            |                       |

**Searching File System**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| locate (find file) | which (find program in user dir) | find (find file along path) |

**File and Folder Manipulation**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| mkdir (create dir) | rmdir (delete dir) | rm (delete file) |
| touch (create empty file)    |                 |                       |

**Vim Commands**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| vi (start vim | w (save file) | q (quit) |

**Terminal Session Commands**

| Commands      |               |       |
| :-------------: |:-------------:| :-----:|
| exit (exit) | halt (shutdown VM) |

**Discuss the relevance of these topics and actions in terms of Internet security. (i.e. How do the things in this lab contribute to your understanding of Internet security and the IT industry overall?)**

The topics covered were relevant in relation to Internet security as the actions performed in this lab were on a server that could be accessed through the unsecured Internet. 

The beginning of the lab required a remote SSH login session to connect securely to the server before the tasks in the lab were to look around and perform various tasks that would be important when looking for possible malicious activity on a remote server.

The actions undertaken in this lab included, looking at network traffic, current users logged on, logon duration, file permissions, running processes and location of secured important files. In a real world scenario, these would be the most likely commands that an administrator would run to ensure that a system maintains its integrity.

IT industry, many systems in particular servers are located far away without UI’s and are usually Linux based, thus learning to navigate the system through the terminal is required.

**Why do you need to understand (and use) Linux commands?**

It is important to understand and use Linux commands because in practice, there are a large number of servers that run a version of the Linux distribution, so it is very likely that you’ll come across a Linux based system in the real world.

Also, connecting to a remote system through a terminal is significantly faster than connecting using a VM and starting up a complete GUI, which depending on your network connection could result in very slow performance.

Furthermore understanding and using Linux commands allows you to see file system properties and operating system performance without also having access to a GUI.
