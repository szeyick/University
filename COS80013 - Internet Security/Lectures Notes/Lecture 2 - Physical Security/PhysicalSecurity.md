## Physical Security

### Locks

Physical locks come in all shapes and sizes -

- Pin tumbler locks (key locks)
- Radial locks (computers)
- Combination locks (low security)
- Electronic locks (keypad, biometric)

Every lock requires a **Access Control Policy** which defines a bunch of properties relating to the usage and management of the keys -

- Who has what key.
- How are keys issued.
- What happens if someone leaves or has access revoked.
- Lost, Stolen and Master keys.

### Lock Picking

Locks themselves are not a method to control security, rather they are a deterrent as the presence of a lock would reduce ones likelihood of wanting to take what is inside it. Physical locks are subject to many forms of lock picking, from your basic pick to copying keys, brute force attacks, bump keys and pick guns. There are many devices that are available to pick a physical lock.

Aside from picking the lock itself, you could also get around it with other means but breaking through the door, removing hinges or drilling through the side. 

In relation to entry gate access, tail gating or impersonating delivery staff is another way to circumvent locks. This is called a **side channel attack**, where you get around the locking mechanism by other means.

### Biometrics

Biometrics is a form of security where a human characteristic (fingerprint, eyes) is measured or scanned then compared with existing information in a database. The measured characteristic itself must be common such that everyone who is using the system has the particular characteristic, for example fingerprints since almost everyone has a fingerprint.

It must produce the following qualities -

- Universal - Everyone must have this characteristic.
- Distinctive - Although everyone has it, it must be different for everyone.
- Permanent - People must always have it and it shouldn't change over time.
- Collectable - It must be able to be collected easily.
- Low False Positive - The system must be able to get it right the majority of the time.
- Hard to Forge - The measured characteristic must not be easily copied.

Examples of biometrics can be, **signatures**, **fingerprints**, **iris and retina**, **handprints** and **voice/speech patterns**.

Although the biometric characterstics can be hard to forge, the database that contains all these details is not invulnerable to attacks.

### Physical Attacks on Computers

DOS (Denial of Service) attacks is basically where a service (eg. website) becomes unavailable. Physical threats such as fire and flood near the server would be enough to create a DOS attack. In emergency situations such as fire and flood, aside from the enviromental damage, the emergency exits become entry doorways for unauthorised people.

Eavesdropping, is where you listen into a communication. This can be achieved through MITM (man in the middle), phone tapping, key logging and WiFi sniffing. These are physical things that can be done to retrieve information that may otherwise be private.

There are many other ways to gain access to private data -

- Live Boot CD's - Hypervisor attacks, jumping from OS to OS to gain access to another part of the system.
- Rainbow Table Attacks - Retrieve hashed passwords.
- Windows Booting - Booting into safe mode or reinstalling the OS will reset the passwords to default allowing access to previously stored data.
- Authentication Bypass - Using **KonBoot** will allow you to bypass authentication protocols.
- Forensic Analysis - Allows you to recover even deleted information off a HDD.
- RAM Freezing
- Electron Microscope Analysis - Look at the physical hard drive platters to retrieve information.

### Key Logging

The idea to log keystrokes to indentify common patterns to match up for the intended use. It can be both hardware and software based. This process is legal with consent, however is generally associated with stealing passwords.

Software keyloggers would be a form of malware (spyware) that transmits the keystrokes back to an attacker.

Hardware keyloggers require physical access to the machine, but can be installed easily. It can be used to monitor all the activity on a computer and not just the keyinput.

### Card Skimmers

Usually installed on ATMs to read the information on the magnetic stripe. It is installed over the actual card reader itself so people are unaware when they push their card into an ATM. Not only is a card skimmer installed over the ATM, but a physical key logger may also be installed to record both the pin number and the card number.

### Air Gap Security

The main idea with air gap security is to prevent all forms of network intrusion. The only access between two communicating machines is through physical means where access is limited to keyboard, CD-ROM or USB.

The output is usually to a screen, a CD or USB, but never transmitted over a network.

High security places such as missle silos, military bases and power stations may implement air gap security as potential hacking can be catastrophic.

### Semi Air Gap

Is a slightly less intense than air gap security. In this instance there is small amounts of network access but traffic is usually only conducted one way, meaning there are no return messages, handshakes or feedback. Again it is usually used for protecting critical infrastructure such as dams and gas pipes.

### Air Gap Failures

Although used to defend against network based attacks, there have been instances where air gap security has resulted in failure. Because communication is still required by physical means, the transportation device is an area of vulnerability. USB sticks, CD's can come with installed malware that can infect an air gap system as soon as it is plugged in.

### Social Engineering

Sensitive information can be obtained without the use of a computer. Usernames, passwords can be easily obtained if the right questions are asked or even in a low security environment. Stores sometimes have phone numbers, passwords posted on the side of a monitor which can be easily seen by anyone.

People who also do not destroy information correctly (i.e. paper shredding) can have it pieced back together or even collected. Although it is usually shipped off to a rubbish site, it doesn't stop people from gathering what can be highly sensitive information easily.

General shortcuts of processes, such as tailgating through security gates, leaving doors open for others are generally easy ways for attackers to gain access to a building. Once inside, they have access to unsecured computer systems including the phone directory where they can impersonate a staff member to gain information.

Information can easily be gleamed off websites, even some government service departments can provide the information without much difficulty.

Other forms of social engineering to retrieve data include -

- Dumpster diving
- Google Eart/Street View - Find out where bins are, scout locations.
- Accessing public surveillance cameras
- Calling helpdesks
- Social Media Sites (LinkedIn, Facebook, Twitter etc)
- Blackmail

### Rings of Security

Using both hardware and software an enterprise can filter out dangerous things on the internet. 

- Firewalls, proxy servers protect the network from the internet.
- Personal firewalls can protect individual network domains within an organisations network.
- Anti-virus and Anti-Malware can scan hard drives to find threats.
- Patches - rolling out of new tested software to defend against known vulnerabilities.
- Locks/Keypasses - Gate entry and also protecting computers with keypass entry so that only the person who has a pass is allowed access.

However even with all this protection, lapses in policy and judgement can make the system fall over. Employees plugging in their phones, USB sticks and other infected devices into a network can lead to a trojan horse scenario where one infect machine can tunnel through an entire network.

It is even more dangerous if the attacker has gained physical access into a building, as they can plug in their own hardware to the network or server to view its contents. What a hacker can do with physical access to a computer or network is near limitless, and will most likely go undetected as most safeguards are setup to prevent outside attacks and not attacks from the inside.

Even cheap devices like Raspberry Pi's can be used to breach a enterprise system.

### Preventing Security Breaches

At the end of the day, common sense generally is the best solution to prevent security breaches.

- Encrypting communications - SSL, HTTPS and SCP protects data transfer over a network.
- Social Engineering - Common sense, reporting suspicious activity, don't talk about confidential information in public etc.
- Locking Hardware - Lock computers to desks, encrypt hard drives, destroy HDD's (burn) and encrypt portable devices.

### Forensics

Although you delete something through the recycle bin, it doens't neccessarily mean it is gone forever.

For computer crimes, the general process is as follows -

- Office/Home is searched for computers and paper based records.
- Hardware is seized, hard drives are copied, and the investigation begins with the copy, the original is kept as evidence.
- Powered Hardware is monitored, possibly plugged into a UPS to continue the monitoring upon transport.
- All removed evidence is bagged and tagged.

All evidence in computer crimes must be collected and presented in a way that it cannot be dismissed in court.