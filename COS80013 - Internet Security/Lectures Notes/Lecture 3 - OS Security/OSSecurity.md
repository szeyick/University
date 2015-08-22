## Operating System Security

### Operating Systems

Operating systems are the managers of interactions between applications and the hardware. This also includes access by users through the application, which in turn interact with the hardware.

Security flaws in an operating system can lead to mallicious programs being installed through web browsers, access priveledges being changed and anti-virus/firewalls being disabled allowing attackers to gain access to the system.

### Virtual Machines

It is a layer of abstraction, where the OS is running on a layer of software. This layer mimics the hardware specifications of a machine. The virtual machine hosts the operating system, and has access to the disk drives, RAM, CPU and other bits of hardware on the host machine.

There are benefits to VM's which include -

- A VM can host many different operating systems.
- Portable - It can be shutdown, transferred and restarted on a different machine.
- Secure - Because the OS sits on the VM, it does not have direct access to the computer hardware.

There are however drawbacks to VM's which include -

- CIA issues (Consistent, Integrity, Accessible)
- Hypervisor issues, breaking on into one VM can allow you access to different VM's and operating systems.
- Malware detection in the VM can get away undetected if the malware knows that it is infected a VM.

### Patches

A zero-day exploits is an exploit that has no solution, no fix or patch. They are particularly harmful because they are impossible to predict or anticipate because noone knows about it until it has occured. Usually when zero-day exploits are discovered, they are caught by white hats or antivirus vendors who will attempt to rectify and fix the exploit.

Most types of malware gain access to a PC by exploiting vulnerabilities in the software. All types of hardware devices will be succeptable to malware intrusions through all means, emails, documents, web browsers.

Patches as their name suggests are installed over the top of the existing software to rectify the bug, this does not require the software to be re-installed again. This is beneficial as it is quick and usually unobtrusive. The downside of this is that, because it is a patch it may cause regressions elsewhere rather than fixing the root cause of the issue.

Although patches do rectify zero-day exploits and other types of exploit, there is no way to force users to actually take the changes as they can always stop the download or not even be connected to the internet.

Sometimes, vendors hold off a fix for a known zero day exploit even if they know it will fix an issue. The reason for this is that releasing the patch will warn attackers that there is a vulnerability in that particular piece of software so they will be drawn towards looking at the area more closely to potentially find more weaknesses.

Obviously by holding off, you put a lot of consumer devices at risk, but this is generally seen as the practice. However, holding off may lead to third party companies rectifying and releasing the patch before the offical vendor does so.

### Protection

To ensure that you are adequately protected, following some simple rules -

- Never be an early adopter - First generation of products are usually very buggy and have lots of weakenesses, waiting a few generations will allow the vendor to perfect their product.
- Keep up to date - Be aware of security flaws and ensure that you have the latest patches.

Microsoft have a regular patch cycle, their fixes come out the second Tuesday of each month. Crackers have realised this and release their exploits the day after, if it hasn't been patched as it allows them a full 30 days for their exploit to run before it is corrected. Depending on how bad the exploit is, it may take another 30 days for Microsoft to rectify the issue.

These release cycles are much the same for many other vendors, so once crackers get onto how their release cycles work, they can try to release their exploits accordingly.

There are also instances where vendors patch other vendors software, like Google updating Flash plugins in Chrome, Firefox updating plugins and Apple updating Java. However this isn't always the case, as sometimes a vendor may not update the product for ages, such as Apple not updating or even installing Java.

Patches are not released all the time as releasing them frequently will degrade a consumers perception of the company making them think that their software is terrible.

### Authentication

Theres no authentication on the internet, it is usually build into the application or website. There are three general rules for secure communication -

- Secrecy - Only the sender and recipient know what the message is.
- Integrity - Communication should be between the intended people.
- Authentication - Identities should be established.

### Multi Factor Authentication

Is the process of using different means of authenticating that a person is indeed who they say they are. These factors generally include -

- Something you know - Such as a username or password.
- Something you are - Biometrics.
- Something you have - key card, RSA token.
- Someone to vouch for you - Verisign, web of trust.

### Identity

Proving that you are in fact you. Most websites on the Internet use at least one of the following mechanisms to prove identity

- Username + Password - Email is sometimes used as the username.
- Usernumber + Pin - For Internet banking.
- Card + Pin - Withdrawing funds from an ATM.
- Photo + Signature + Card - Passports
- Paywave - Not a very secure system, since it is only a single factor authentication, so long as you have the card.

#### CVV / CSC

The number on the back of a credit card as a means of adding an additional factor of authentication. It is useful for when someone only has the card numbers and expiry date, possibly obtained through a card skimmer. Since the CVV/CSC are not stored on the card strip itself, the attacker would also need the printed number to gain full usage of the card. It is seen as proof that the person has the card with them and not just making the details up.

#### Passwords

Useful, but not unbreakable. Because they require input from the user, they can be succeptable to key logging, resulting in brute force or dictionary based attacks. Even if a password, through social engineering, it is possible for someone to backtrace to retrieve a username or something else to go along with the password. Bruteforce attacks, whilst can be time consuming, depending on the size of the password may not be terribly hard to crack, for example -

- 4 digit pin can be broken in 5000 tries.
- 6 digit pin can be broken in 500,000 tries.
- 6 character password (a-z) in lowercase takes 150 million tries.

All combinations that would not take a computer very long to crack, given a known username.

The general rule with passwords is to make them as long as conveniently possible with a combination of alphanumeric and symbol characters -

- 6 character password (upper, lowercase, numbers and symbols) will take 28 billion tries to break.
- 8 character password (upper, lowercase, numbers and symbols) will take 110,000 billion tries to break.

It isn't completely secure, but it would take even a computer a very long time to break in.

To create a safe password use a mixture of characters, never re-use passwords and especially don't store them anywhere where someone can find it.

#### Cracking Passwords

Well constructed passwords that are of sizeable length, made up of upper case, lower case, numbers and symbols will generally take a computer a significant amount of time to brute force. Many websites of applications will generally have a try limit before the account is either locked out or temporary locked for a period of time. Attackers usually get around this problem by brute forcing user names with the same password, rather than brute focing with the password since it will lock them out.

If you try to log in, using the same dictionary or bruteforce keyword as a password with a rotating number of usernames, you may get in eventually since there will inevitibly be someone who uses an easy to guess password.

#### Server Passwords

If ever maintaining a back end database of user information, to access the data one would require someone to log in. Rather than storing the passwords into the database, it would be better to store the hashed passwords rather than the passwords themselves.

Doing this is called **salting the hash**, where a password is hashed by some algorithm and then the resulting output is stored in the database. Therefore when the user logs in, the entered password will again go through this hash to retrieve the associated information. For additional added security, a random hardcoded salt maybe appended to the hashed password and then added to the database so it makes it even harder to retrieve information should it be retrieved.

Furthermore, if creating user accounts and passwords, it would be advisable to let the users know to choose longer passwords and to change them regularly. Usernames as email addresses are generally not safe either, and never include the password in a "request password reset or forgot password" email. The reason to not do this is because if a email address is compromised, then all their subsequent accounts will be too.

### C Programming

Is a programming languaged developed in the 1970's. Its successor, C++ is supposed to be backwards compatible. It is generally seen as fast, powerful and flexible. 

C is built on top of assembly language, allowing programmers to directly manipulate memory registers in a higher language.

In C, all data is treated as a stream of bytes and everything created in C (objects, variables, functions etc) are kept track of by knowing its starting position. For everything in the language aside from C-strings, there is no way a program will know that it has reached the end of its byte stream. References are used to locate objects in memory (pointers).

Arrays in particular are required to be monitored by a developer as the language itself will allow you to write past the end of the specified array, unlike in Java where you will get an out of bounds exception if you attempt to write further than the defined length.

In C everything other than 0 is treated as logical TRUE.

```
int main() {
	int x = 12;
	int array[16];
	int i;
	for (i = 0; i <=16; i++) {
		array[i] = i*64321;
	}
	printf("x was set to 12, now x = %i\n", x);
	return 0;
}
```

In the above code snippet, the size of the array is defined as 16 elements. Our loop however will write to the array 17 times, since array indices in C will start at 0. As a result, this will write over the allocated array and in fact overwrite the value of x which was supposed to be 12.

### Overflowing Data Types in C

For integers in most programming languages, the left most byte is usually reserved to define whether it is a positive or negative value, 0 for positive, 1 for negative. If you overflow an integer in C, you can accidentally change the sign of the left most digit.

```
int main() {
	int x = 214783646; // Maximum value of an integer in C.

	do {
		printf("before increment: x = %i\n", x);
		x++;
		if (x > 0) {
			printf("x is positive. now x = %i\n", x);
		}
		else {
			printf("Integer overflow: x = %i\n", x);
		}
		} while (x > 0)
}
```

The code above will keep on incrementing the value of x until we reach a buffer overflow. After the overflow is reached, the number will magically flip to be a negative.

### Memory Allocation in C

In C, it is the developers duty to allocate the neccessary memory for each variable, object or struct. Usually the compiler will check whether enough space has been added to support the variable/struct. Because it is entirely possible to write past the allocated memory in C, it is possible for functions and other objects to be overwritten by the overflow meaning that code can be malliciously injected into the program.

If written properly, a function return address can be overwritten and redirected to another area in address space where another piece of code can be run instead.

### Von Neumann Architecture

Is the architecture that most modern computers are built from, this style states that both data and code share the same memory address space. In C, because we use pointers to locate data and code, it can mean that data can be treated as code and vice versa.

### Buffer Overflow

A really simple description of a buffer overflow is shown below.

** Insert Image **

Here, the attacker will input executable code into a text field. Using gets() which is unsafe, it allows the number of characters entered to exceed the size of the input array. By writing past the allocated size of characters, it means that the arguments and return addresses are also overwritten.

It can therefore change the return address of where the function is supposed to go to once it has finished executing. Exploiting the stack in this way allows an attacker to gain access into a system, however because the stack implementation is different on different OS's and hardware, they are requiredd to understand the type of computer they are attacking first.

### Advanced Buffer Overflow

If an attacker knows the location of OS libraries such as .dlls, they can exploit code to make it return to these areas rather than normal program execution flow. By rerouting the return address to the dll or other libraries, it can allow an attacker to open TCP sockets, override access priveledges and the sort.

### Data Execution Prevention

Is a feature that allows particular areas of memory to be tagged NX (non executable) or XD (execution disabled) so things in there cannot be directly executable. If this feature is enabled, particular programs can be added to a white list allowing them to circumvent this checking.

### Address Space Layout Randomisation (ASLR)

Successful code that causes a buffer overflow requires knowledge of how things are mapped in a computers memory. ASLR is the process of changing the layout of the heap, stack and other memory areas after each boot up meaning it should always be different. This makes a hackers job harder to locate particular processes or executable programs.

Although it changes the layout each time, there still needs to be a record of where things are, so attackers will generally aim their efforts to this map.

### Trusted Platform Modules

A hardware chip attacked to a motherboard of a PC that has a **eeprom** and dedicated hardware to allow it to perform cryptographic functions on behalf of the BIOS and OS. Because it is hardware, it is harder to intercept or sniff exchanges between the two. Fingerprint metrics, certificates, keys and passwords can be carried on it but should never be displayed. It is not really used because it enforces copy protection meaning that once its lost, it will need to be recreated and backups cannot be made.

### Preventing Buffer Overflows

Buffer overflows do not only occur in C, they can occur in web programming as well since it is entirely possible to send URI requests that are larger than the allocated space. It is also possible in other programming languages.

The best practice here would be verify the input payloads and also to use good programming practices. For C in particuarly, it is useful to use the strn() functions as it only will set the first n characters and anything after will be ignored. This will prevent buffer overflows as all inputted text after the limit will not be stored in a variable.