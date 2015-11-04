## Research Report

### Goal -

Analysis - That Stuxnet and Flame were jointly created using shared components. (2 tools, 1 purpose) of espionage and destroying remote targets.

Evaluation of Analysis Outcome - Codebase appears to share similarities, in the initial infection method, it is difficult to conclusively say that they were jointly created. Many existing products, are capable of re-using components or exploits, information could have been shared based on code (independent contractors perhaps, but who knows?).

This following contains rough notes regarding the Stuxnet Virus

**Title** : Stuxnet Malware Analysis Paper
**Author** : Amr Thabet
Link - http://www.codeproject.com/KB/web-security/StuxnetMalware/Stuxnet_Malware_Analysis_Paper.pdf
Date - 20/10/2015

### Notes

Stuxnet was one of the first viruses to completely change the game in what viruses are designed to do.

It was suspected to have been created to sabotage Iran's nuclear program.

It is comprised of **4 zero day vulnerabilities in Windows OS** and uses tricks to avoid being detected by behaviour blocking anti virus programs.

It is designed to infect PLC's (programmable logic controllers) that control the machines at the Iranian nuclear reactor.

### Payload

Stuxnet uses Siemens default passwords to gain access to systems that run WinCC and PCS7 programs.

These programs control and modify the code of the PLC's which in turn control the machines themselves.

Stuxnet has two stages of infection. The first stage uploads configuration information about the Siemens system to a **command and control server**. Once this has been uploaded, attackers are able to reprogram the way that the PLC works.

The attackers decide how they want the PLC to work, then send the code to the infected machines that will then forward those commands to the PLC to change how it operates.

The purpose of it was to speed up the spin rate of the Iranian nuclear centrifuges along with slowing them down.

### Technical Breakdown

#### 1. Main Dropper (~WTR4132.TMP)

This is the entry point for Stuxnet. It is a **dll (dynamic link library)** file that is loaded into Explorer.exe. It begins the execution by searching for a section with a **.stub**.

This section contains the main Stuxnet dll. The dll itself contains all the functions, mechanisms, files and rootkits that Stuxnet will use.

It also includes the configuration data for Stuxnet which describes the mechanism on how it will spread and update.

Once it finds the .stub section, the Stuxnet dll is loaded.

How it is loaded is the following -

- **Allocating Memory Buffer**

It allocates a memory buffer for the dll to be loaded.

- **Patching ntdll.dll API**

It then patches the ntdll.dll API's with the following names

**NOTE - Find out what ntdll.dll does**

ZwMapViewOfSection, ZwCreationSection, ZwOpenFile, ZwClose, ZwQueryAttributesFile, ZwQuerySection.

The patched API will trick ZwOpenFile to read from this .stub section like it is reading from the hard drive. 

The patched files will make LoadLibraryA load a DLL file from a place in memory rather than a hard disk location. Presumebly it will allow the Stuxnet dll to be loaded in this way.

It will call LoadLibraryA with the DLL name to then load the main stuxnet DLL file.

### Main Stuxnet DLL

Once the main DLL is loaded, it will **unupx itself (the dll is upxed)** and checks the environment it has been loaded in to see whether it should continue executing or not.

If the configuration of the system it has been installed in is the correct type of environment, it will then check to see if it has admin rights. If it does not, then it'll use either of two zero day vulnerabilities to escalate the system priveledges to adminstrator level.

### Admin Access Vulnerabilities

**CVE-2010-2743 (MS-10-073) - Win32K.sys Keyboard Layout Vulnerability**

**CVE-xxxx-xxxx (MS-xx-xxx) - Windows Task Scheduler Vulnerability

The aforemtnioned vulernabilities will allow the worm to esclate priveledges and run in a new process or new task.

It also checks whether the system is a 32 or 64 bit system.

All the tasks up until now are for the **preperation** for the system to be infected with stuxnet. The next step will then for it to inject itself into another process to then begin the installation of stuxnet.

### First Step

When the system is primed and ready to install stuxnet, it will search for any anti-vuris applications that are installed on the machine. 

Depending on the installed anti-virus (if there is any), stuxnet will choose the process to inject itself into. If there is none installed it will use the **lsass.exe** process.

It doesn't use the task manager to find the process to inject itself into, but rather creates a new process from the target process and sets it to suspend.

It will then inject itself but unloading the targeted process from memory and load its own process from the stuxnet DLL in its place. The newly loaded process will be of the same size of the one it has unloaded. Lastly it copies the stub section and the main DLL to the process to inject and writes it into the memory buffer.

### Symatec Breakdown (Norton)

Link - http://www.symantec.com/security_response/writeup.jsp?docid=2010-071400-3123-99&tabid=2
Title - Win32.stuxnet
Date - 20/10/2015
Author - Symatec

Originally discovered July 13, 2010.

Affects Windows 2000, 7, 95, 98, Me, NT, Server 2003, Server 2008, Vista, XP.

**It does not use the autorun feature to install itself**.

The addresses are used to download updates for itself -

www.premierfutbol.com
www.todaysfutbol.com

### Infection Method

- It exploits the Microsoft Windows Shortcut 'LNK/PIF' Files Automatic File Execution Vulnerability, which was a zero day exploit.

- Stuxnet copies itself to removable drives as ~WTR4132.tmp and ~WTR4141.tmp, which are hardcoded .dll files.

- It also copies Copy of Shortcut to.Ink, Copy of Copy of Shortcut to.Ink, Copy of ..... 4 times.

When the drive is accessed and icons are attempted to be displayed through the .Ink files, it instead runs code to execute the ~WTR4132.tmp file. The main purpose of this file is to execute ~WTR4141.tmp that will be loaded into memory.

The ~WTR4141.tmp file has a valid verified signature that has been signed to ensure validity.

### Windows Print Spooler Service Remote Code Execution Vulnerability

It uses this vulnerability to copy itself from one infected computer to another. It allows a file to be written to the %System% directory of a targeted computer. Stuxnet copies itself to this directory which then it executed later to infect the remote target computer.

### Network Spreading

On a network, it will attempt to spread itself by copying itself to a shared network drive location with the filename DEFRAG[RANDOM NUMBER].tmp. The purpose of this tmp file is to run the .dll file.

### Functionality

It creates additional driver files mrxcls.sys and mrxnet.sys

Along with registry keys - 

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\MRxCls\"ImagePath" = "%System%\drivers\mrxcls.sys"
HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\MRxNet\"ImagePath" = "%System%\drivers\mrxnet.sys"

- It exploits two processes iexplorer.exe and lsass through injection.

- When a removable drive containing Stuxnet is loaded into an uninfected computer, it copies the mrxcls.sys and mrxnet.sys files to the %System%\drivers\ folder and registers the mrxcls.sys as a service.

This creates the above registry keys for the service.

It then also creates the following encrypted copies of the worm, that will reinfect the computer if attempts are made to remove the known worm files - 

%Windir%\inf\oem6C.PNF
%Windir%\inf\oem7A.PNF
%Windir%\inf\mdmcpq3.PNF
%Windir%\inf\mdmeric3.PNF

The file mrxcls.sys decrypts the above files to reinfect a computer if they try to remove the worm.

### Network Infection

Stuxnet is capable of downloading a payload from a network address and execute it.

To begin, it makes a HTTP request to a server with information about the computer it has infected.

http://[C&C SERVER ADDRESS]/index.php?data=[DATA] 

C&C stands for command and control.

Where the data part is the data scraped from the infected computer. It tries to contact the URL's listed above through port 80.

The data parts are encrypted, which are able to be decrypted using the .dll file on the infected computer. The encryption used on the input and output packets are different, however the .dll is capable of decrypting the returning paket.

### Information Sent

The encrypted data packet contains the following information - 

The Windows version information
The computer name
The network group name
Flag for whether SCADA software was installed or not
IP addresses of all network interfaces

The return response instructs Stuxnet to run a procedure that is in the code, or to receive another .dll and execute that one instead.

### SCADA Software

Stands for - Supervisory Control and Data Acquisition

Stuxnet specifically targets systems with this type of software installed. It performs many database queries on the database that is used by the Siemens Step 7 software and interacts with the .dll files for that particular product.

It tries to extract data from the database allowing it to steal code and design projects.

### PLC (Programmable Logic Controllers)

Are mini controllers or computers that can be programmed from a Windows based system. PLC's contain code that controls the automation of industrial processes or machines. 

Programmers use software on a Windows based PC to create the code to be run on a PLC, which is then uploaded to the PLC.

Stuxnet is capable of using as an advantage to upload its own code to a PLC in an industrial system that is monitored by SCADA systems. Additionally, it is capable of hiding this code (code blocks) so when someone views the infected machine and review the code on the PLC it will not see the code that has been injected.

Stuxnet is a rootkit that can hide itself on Windows and PLC's. It hides itself through "hooking" meaning that it also cannot be read or accidentally overridden.

Being able to write code and inject it into a PLC can allow Stuxnet to control how an industrial system operates.

Stuxnet was deliberately designed to speed up and slow down the spinning of centrifuges whilst returning normal operational numbers to the SCADA software so it would seem as though nothing abnormal was happening.

### Flame Worm

Link - https://securelist.com/blog/incidents/34344/the-flame-questions-and-answers-51/
Access Date - 20/10/2015
Author - Alexander Gostev

- An unknown piece of malware was deleting sensitive information across the Middle East, codenamed Worm.Win32.Flame

- Shares similarities in target, geography and careful target selection and select vulnerabilities.

- Flame is an attack toolkit, it consists of a backdoor, a trojan and worm allowing it to replicate through a local network or portable drive

- Once infected, it can sniff network traffic, screenshots, record audio, intercept keyboard, sending it to Flame C&C servers.

- It is a large worm, 20mb in size so different to normal pieces of malware, reason for its size is because it contains many libraries

- Also contains locally used databases by the worm, encryption and compression algorithms.

- It seems to be a very specifically designed worm in respect to what it does and in the many ways that it can do it.

- It also uses bluetooth to seek out other devices to return information about other devices within range.

- It can take screenshots when "interesting" applications are run such as email, IM and all that.

- Discovered about 2010.

- Seems to be a nation state as it does not steal money or bank information, and is not simple. Therefore it may be used by governement.

- Iran is the top infected nation with Flame, along with other middle eastern countries.

- Appears ot be a generalised toolkit for spying, not specific to any type of indistruy, unlike Stuxnet.

- Flame is 20 times larger than Stuxnet, consisting of no major similarities.

- Flame and Stuxnet utilise the "autorun.inf" infection method, along with exploting the same print spooler vulnerbaility.

- Infects using the Autorun Infector (autorun.inf) exploit using the shell32.dll trick. This is used in Stuxnet and nothing since.

- Uses euphoria, which spreads using a junction point (whatever htis is?)

- Uses the MS10-061 vulnerbaility that was exploited by Stuxnet, and can attack ohter machanines local to the network if login rights are at the admin level.

### Stuxnet and Flame differences

Title - Duqu, Flame, Gauss: Followers of Stuxnet
Article - http://www.rsaconference.com/writable/presentations/file_upload/br-208_bencsath.pdf
Date - 20/10/2015
Author - Boldizsar Bencsath Phd, BME CrySys Lab

- Built on a different platform, and languages, C&C platform is run on different OS

- Code within 2009 Stuxnet was found within Flame.

### Flame and Stuxnet malware linked, says Kaspersky

Link - http://venturebeat.com/2012/06/11/flame-and-stuxnet-malware-linked-says-kaspersky/
Date - 20/10/2015
Author - Ricardo Bilton

- “With these kind of operations, your source code is your ultimate possession — and this was shared,” Schowenberg said in an online press conference on Monday. “You don’t share your source of income.”

“This confirms our beliefs that the projects were developed in parallel, and commissioned by the same entities,” he said.

- Resource 207 module.

- Flame predates Stuxnet, and Flame was used as the platform for Stuxnet, this is believed.

### Back to Stuxnet: the missing link

Link - https://securelist.com/blog/incidents/33174/back-to-stuxnet-the-missing-link-64/
Author - Alexander Gostev
Date - 20/10/2015

- 3 variants of stuxnet, June 2009, March 2010, April 2010

- Resource 207 is 520,192 bytes in size, found in June 2009 version of Stuxnet, later dropped from later versions, the code merged into other modules

- Tocy.a an early module of Flame was similar to module 207 from Stuxnet, so similar that the Kaspersky system classified it as Stuxnet.

- 207 is an encrypted DLL containing another PE file inside.

- The PE file within 207 is a Flame Plugin, an early version of flame lots in common with "mssecmgr.ocx" in 2012 Flame.

- Similarities in function returns, shellcode style and other things.

- Whole pieces of code from latest Flame modules are identical to the code in atmpvsvcn.ocx

- Resource 207, used to ensure Stuxnet propogation to removable USB through autorun.inf that flame also uses.

- The exploit code in atmpsvcn.ocx is similar in 2010 Stuxnet. The code style, logic and details were the same in the Stuxnet 2009 and 2010 code.

- Flame and Stuxnet existed around the same time.

- 2009 Stuxnet used a module built on the Flame platform.

- 2009 Flamecode was found in Stuxnet, source code used rather than binaries, later versions share no similarities.

### Flame Breakdown

Link: https://www.sophos.com/en-us/threat-center/threat-analyses/viruses-and-spyware/W32~Flame-Gen/detailed-analysis.aspx
Author: Sophos
Date: 20/10/2015
Title: W32/Flame-Gen

- Flame is an information stealing worm

- Components of flame include - 

%SYSTEM%\advnetcfg.ocx
%SYSTEM%\boot32drv.sys
%SYSTEM%\ccalc32.sys
%SYSTEM%\msglu32.ocx
%SYSTEM%\nteps32.ocx
%SYSTEM%\mssecmgr.ocx
%SYSTEM%\soapr32.ocx

- Main component of flame is mssecmgr.ocx

- Maintains a reboot persistence by adding itself to the registry key - 

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Lsa\

"Authentication Packages"

+ mssecmgr

Where it adds the mssecmgr.ocx file so that it runs (loaded into memory) each time the machine is restarted.

- When executed, it unpacks the other components (listed above) onto the machine.

- Additional files are dropped that provide specific functionality into the system folder

advnetcfg.ocx

msglu32.ocx

nteps32.ocx

soapr32.ocx

- Also create runtime data files that store temporary information encrypted and compressed in a SQLite3 database

ccalc32.sys

boot32drv.sys

audcache

dstrlog.dat

ntcache.dat

~rf<num>.tmp

~DEB93D.tmp

~HLV<num>.tmp

~KWI<num>.tmp

- Attempts to contact a C&C server over HTTPS (dnslocation.info, traffic-spot.com, traffic-spot.biz, smart-access.net, quick-net.info)

### Flame, Duqu and Stuxnet: in-depth code analysis of mssecmgr.ocx

Title : Flame, Duqu and Stuxnet: in-depth code analysis of mssecmgr.ocx
AUthor - ALEKSANDR MATROSOV, Eugene Rodionov
Date: 10/10/2015
Link: http://www.welivesecurity.com/2012/07/20/flame-in-depth-code-analysis-of-mssecmgr-ocx/

- Flame is the same kind of mallicious program, complex and elaborate architecture.

- Maintains persistent precence on the attacked system

- OO code in mssecmgr.ocx in Flame is different to how OO is used in Stuxnet

- Representation of strings in Stuxnet and Flame are quite different [see attached image in link]

- When flame is first installed, it is registered as a LSA authentication package in the registry, loading the mssecmgr.ocx file as part of the lsass.exe system process during startup.

- Flame doesn't run on 64 bit OS but runs on all windows starting from 2000 up to but not including  windows 8

- When main Flame module is loaded, it checks what security is installed. It scans through all of them, and detects the version running and the malware may stop executing if a specific software is found.

- All detected products are split into groups on their functionality (firewalls, HIPS, anti-virus, packet sniffing, etc)

- Uses VirtualAllocEx to allocate memory for the module injected into the target process.

- Uses WriteProcessMemoryReadProcessMemory to inject the code

- CreateREmoteThreadRtlCreateUserThread to transfer control to the injected module

- Flame allocates memory by mapping the system library shell32.dll, it creates all necessessary system structures for the module to inject so it looks legit.

- When the data and code is written into the targeted address space, Flame creates a remote thread executing either CreateRemoteThread or RtlCreateUserThread API and specificying the address of Stub 2 as the entry point of the thread, with the data as a parameter.

- Stub 2 containers loader code to map the injected module into the address space of the targeted process.

## Microsoft Security Bulletin MS10-061 - Critical

Title: Microsoft Security Bulletin MS10-061 - Critical
Date: 10/10/2015
Author: Microsoft
Link: https://technet.microsoft.com/en-us/library/security/ms10-061.aspx

- Allows for remote code execution if an attacker sends a custom print request to a vulnerable system that has a print spooler interface exposed over RPC (shared printers on a network)

### Hacking Windows Via MS10-061 Print Spooler Service Impersonation Using Metasploit + Backtrack 5

- It exposes the service behind printer sharing in windows

- Makes a specific DCE RPC request to the StartDocPrinter procedure, an attacker can impersonate the Printer Spooler service to create a file. The working directory at the time is system32, an attacker can specify any file name, including directory traversal and full paths. 

- Sending a WritePrinter request, an attacker can fully control the content of the created file. In order to gain code execution, this module (write printer) can write to a directory used by Windows Management Instrumentation (WMI) to deploy applications. The Wbem\Mof directory is routinely scanned and new .mof files are processed automatically, this is the same way that Stuxnet uses to execute code remotely.