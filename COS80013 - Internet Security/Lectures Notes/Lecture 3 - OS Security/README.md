## Terminology

- **Operating System**

A software layer that sits between the hardware and applications as a means of managing the interactions between the two.

- **Virtual Machine**

A piece of software that emulates hardware, operating systems can be installed within a virtual machine to prevent direct access to a machines hardware. 

- **Patches**

Fixes to exploits released by vendors or third party companies that fix known exploits. 

- **Protection**

The process of always updating software, being aware of security flaws and never being a first generation adopter. Adopting later in the cycle will allow for security flaws and bugs to be ironed out further.

- **Authentication**

The process of correctly verifying that a person is who they say they are. There is no authentication on the Internet, and authentication systems are usually built into the applciation. There are three general rules to authentication, **secrecy**, **integrity** and **authentication**.

- **Multi Factor Authentication**

Using different means of correctly verifying an identity. Use a combination of passwords, usernames, cards, tokens and biometrics to ensure that breaches in one will not allow instant access.

- **Passwords**

The most common way to authenticate a user with their username. General rule would be to use hard to guess passwords with a combination of lower case, upper case, numbers and symbols. The more difficult and longer the combination, the harder it is for an attacker to bruteforce or dicitionary attack your account.

- **Server Passwords**

Never store passwords on a back end server. Poor server design would result in raw text passwords being stored on the server. The better practice would be to hash the passwords (salt) and only store the salted passwords on the server. The algorithm used to generate the hash is kept secret and not known to the outside world, meaning even if they steal the hash it would be difficult for them to reverse engineer it to the original password. An additional level of security would be to add another salt to the raw text password before it is hashed.

- **C Programming**

A higher level programming languaged built on top of assembly. It is the most common programming language, where lots of different applications are written in. Even some other programming languages since are built from C or built with C. As a programming language, it does not keep track of when a byte stream for an object ends, only its beginning. This allows attackers to exploit programs written in this language by triggering buffer overflow attacks.

C allows you to write completely past the allocated space for an object or variable, meaning you can accidentally override other pieces of memory.

- **Buffer Overflow**

The process of writing past the allocated memory for an object or variable. Writing past the allocated space allows a attacker to input their custom code that may override return values and other pieces of memory allowing them to execute their own code or even system code.

- **Advanced Buffer Overflow**

The same as buffer overflow but rather than executing their own code, an attacker changes the return address to access system processes or system commands, allowing them to open up the application or operating system to give themselves greater access.

- **Data Execution Prevention**

Features built into hardware and some operating systems that tags certain areas of memory to not be executable. Programs that exist in this area will need to be white listed if this feature is enabled to allow them to be executed. It is intended to be a security measure to not allow other applications controlled by attackers to execute programs sitting in there.

- **Address Space Layout Randomisation**

The process of randomising location of the heap, stack and memory areas such that attackers will have a harder time locating address locations of important processes that they might wish to exploit. Because the OS will still need to know where the memory areas are, attackers will generally go after this location map rather than trying to find the exact memory locations manually as the map will tell them where things are.

- **Trusted Platform Modules**

A hardware chip attached to the motherboard that handles cryptographic tasks on behalf of the BIOS and OS. Harder to intercept or sniff exchanges between the TPM and hardware, but not commonly used as information on device is protected not allowing to be copied, meaning that if you forget any passwords or usernames, the hardware it is protecting is going to be useless. 

- **Preventing Buffer Overflow**

Using correct programming techniques will resolve many of the potential buffer overflow issues that one may encounter when writing an application.



