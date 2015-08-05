## COS80013 - Internet Security - 

**Lab 1**

In this lab you will investigate Linux commands

### CentOS

**1.1 What is CentOS?**

CentOS is an operating system that is built from a Linux distribution. Its purpose is to function as a free, community supported enterprise operating system that is compatible with Red Hat Linx (Red Hat Enterpise Linux).

**1.2 Using a web browser, go to https://csg.ict.swin.edu.au/livecsg/help/Doc_mercury_student

(a) What is Mercury? Hint: it is NOT the mail server in XAMPP!**

Mercury is a Linux server that is used by Swinburne for teaching and research. It is available to both students and teachers.

**(b) Mercury does not support Telnet. What command must you use to get terminal access (login) to Mercury?**

Mercury supports SSH (Secure Shell) to gain terminal access to the system.

**(c) How is it different to Telnet?**

SSH uses an encrypted network protocol to start terminal sessions. This is different from Telnet as by default does not provide encryption functionality meaning that all data (including passwords) sent over the network are as is and is considered as not secured.

Telnet will send passwords across as text, whereas SSH will encrypt it meaning that it is very useful in an unsecured and unverified environment like the Internet. 

**(d) What version of CentOS is Mercury running?**

CentOS-6 Enterprise Linux - release 6.6

**(e) What is the URL of Mercury?**

mercury.ict.swin.edu.au

**1.3 Using Putty, log in to Mercury
Your username is s1234567 where 1234567 is your student ID.
Your default password will be your date of birth in the format: ddmmyy
You will need to change it - make sure you remember what you change it to.
Call 9214 5000 if you lock yourself out.


When you log in, read the banner.

What version of CentOS is Mercury running?
---no banner?
try cat /etc/redhat-release (Redhat only) or
cat /etc/issue**


**1.4 What do the following commands do? (Write down the answers here or in a notebook)

After running the command, try <command> --help
man <command> or info <command> for more information.
Typing q will get you out of the manual.

ls
ls –l**

This is the command for listing a directories contents. The -l option will force the contents to be listed as one item per line.

**pwd**

This gives the path to the current working directory or location of where you are in the file system.

**ps
ps -al**

This gives the list of processes that are currently running including their status. The -a in lower case will diplay information regarding all user processes. The -l will provide a list of additional information about the process (pid, ppid, cpu). 

**cd /
cd
cd ~
cd ..**

The change directory command in shell. The cd / will go all the back to the root directory or /, cd and cd ~ go to the current users "home" directory, cd .. will go back up one directory or "up one level"
 
**uname
uname –a**

Will provide the name of the operating system. Appending -a will print out information regarding the hardware, network node name, processor name and other informatino regarding the OS name and version.

**df
df –hi**

Will provide the amount of free disk space that is available. The -h will output the available space that is considered as "human readable", and -i will include statistics on available inodes.

**echo $PATH
echo $Path**

echo is the command to print to standard output. $PATH will print usually output the environment variables that the shell or other systems are using. $Path will not print anything as there is not variable assigned to it, the environment path variables are case sensitive

Is Linux case sensitive? Yes as demonstrated by echo $PATH and echo $Path

**history
history | more
history –c**

Lists the commands that have been inputted into the terminal session previously. Piping to more will provide the complete history that has currently been done in the session. The -c will clear the history of the session.

**Try a ping command: ping opax.swin.edu.au

What does it do?**

The ping command will send a request packet to the provided URL. It will provide output whether there is a connection succeeded or not. If it succeeds it will provide the time that it took to send and recieve the request packet.

**1.5 More advanced commands:**

**smbstatus**

This provides a report on the current Samba connections, which is an implementation of the Microsoft file and print services.
 
**dig telstra.com**

The dig command performs a DNS lookup of the provided URL returning information regarding the URL.

**nslookup -sil telstra.com**

The nslookup command will query the servers interactively.

**netstat
netstat | grep CONNECTED
netstat | grep ESTABLISHED**

The command shows the status of the current network and the network sockets. The grep ESTABLISHED filters and displays all the socket connections that have been established, and grep CONNECTED will filter on all the sockets that are connected.

**/usr/sbin/lsof**

This command returns the list of open files, to report all the open files and the processes that have opened them.

**Note: Executables in Linux have no extensions. Zip files have tar or gz extensions

To run a program, type it's name. If it is in the current directory, type ./program
Try these commands to find the ifconfig program:**

**locate ifconfig
which ifconfig
find / -name ifconfig
You can get rid of the error messages this way:
find / -name ifconfig 2>/dev/null
Where is ifconfig?**

 
**1.6 Type in the following command:**

**who
whoami

Who is logged in at the moment?**

I am currently logged in. The **who** comand will display all the people currently logged in.

**Try this command
finger jnewbigin

What can you find out about this user?**

It gives me their name, login, the shell they're using, home directory and when they've been logged on.

It belongs to John Newbigin, using bash shell and they have been online since 11:26 5/8.

## 2. RedHat 7.3 Linux VM:

**2.1 Start the VM (Start/Virtual Machine Launcher/COS30015/ RedHat Linux with
local network)
You don't have an account on this Linux server, but you can use the student account.
log in as student
student**

**2.2 Try out these commands:**

**top
What does it do?**

The command provides an updating list of information regarding the processes that are currently running.

**history | more
What does | more do?**

The | more displays the list from history but one page at a time to allow you to scroll through.

**ls
ls –l

How many files are executable? (look for x )**

Type the name of one
e.g hello1
Doesn't work?
Use file hello1 to see what sort of file it is.

**Linux uses the search path (type echo $PATH to see it) to decide where an
executable program can be found.
Type pwd to see where you are.
Is this location in the search path?
Preceding a program with ./ tells Linux to ignore the search path
and run the program found in the current directory.
Try:
./hello1**

##2.3 To create a text file:

**cat > <filename> where <filename> is the name of a new file
...type stuff...
Ctrl+C (stop)
To see what's in a file:
cat <filename>
rm -i <filename> (delete the file)
You can also create an empty file this way:
touch <filename>**

## 2.4 Edit the file:
**vi <filename>
vi commands:
 <insert> - toggle between insert and replace mode
 <esc> - go back to command mode
 <delete> - delete characters
 : - enter a command
e.g.
 :w - write file
 :q - quit file
 :wq - write and then quit a file
Try editing hello1.asm - what sort of file is it? **


##2.5 Linux Directories are equivalent to Windows folders.

**mkdir <dirname>
rmdir <dirname>**

## 2.6 Which of these commands can you access? Write down what they do.

**locate access_log
updatedb &
find / -name access_log
find / -name ifconfig > temp && more temp
(this takes a while)
which ifconfig
If you are refused permission, tru 'su' (substitute user) to escalate your privileges to
root.
type in
su root
security (logs you in as a the root user)
Try those commands again.**

##3. Shut down

**3.1 Try these:

exit - logs you out of the su shell
halt - shuts the Linux VM down. –but this leaves the VM running with the OS shut
down.
Then use the VMWare menu - Player – Power – Shut down guest.
While in Linux, try poweroff – the best way to shut down
halt –p does the same as poweroff.**

##3.2 If you get this:

**There are stopped jobs.
the root password
Note: su is not a user name. It only
works after you have logged in. It
changes your current user name to root
(default) or whatever you type after su.
e.g. su <enter> -changes you to root,
su jim <enter> – changes you to jim.
You still need the password.
 
You have left a process running – use
ps –l
to see what it is
then type
fg <CMD> where <CMD> is the name of the process you started
 (what you typed to run it)
to bring the process into the foreground.
e.g. fg cat
Stop it the correct way: Ctrl+C for most programs.
If this doesn't work, use ps to get the PID number, and try
kill <PID>
where <PID> is the PID of the process you want to kill**

##4. NETWORKING without DNS (try at home)

Instructions for testing your HOSTS file:
1. You will need to have the DNS client service running in Windows, and administrator access (to \windows\system32).

2. If you want to use HOSTS to map a public site, you may have to bypass your ISP's proxy which may cache DNS replies. Change your browser settings to "no proxy" or "direct connection" or something similar. If it is a site on your ISP's domain or your own domain, the proxy server will not be used.

3. Open a console window (START Run cmd) and ping a domain you want to create a hosts entry for.

4. Note the IP address.

5. Open C:/Windows/system32/drivers/etc/HOSTS in a text editor

6. Scroll down to the bottom of the file and type in the IP address followed by the fully qualified domain name (e.g. host.domain.com), or the short version you want to
use.

7. Save the file the process that is still running look for this 

8. Refresh the local DNS service cache: ipconfig -flushdns

9. Open a browser and type in the domain name or the short version and see if it takes you there. You should be redirected to the IP address you added to the HOSTS file. Try pinging a blocked site to get its IP address.  Add it to your hosts file to see if you can bypass the blocked list. To use your HOSTS file to block domains, add the loopback address 127.0.0.1 followed by the domain name to your HOSTS file. If it doesn't work, you probably have Windows 8. I'm sorry.

host/ domain name: IP you will get it to redirect to:

##Write a one-page report on this lab covering the following:
1. Summarize the topics you explored and the activities you did during this lab.

2. Classify (group) these topics and actions under appropriate headings. Do not just copy the headings used in the instructions.

3. Discuss the relevance of these topics and actions in terms of Internet security. i.e. How do the things in this lab contribute to your understanding of Internet security and the IT industry overall?

4. Why do you need to understand (and use) Linux commands?
