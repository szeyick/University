## Research Report

This following contains rough notes regarding the Stuxnet Virus

**Title** : Stuxnet Malware Analysis Paper
**Author** : Amr Thabet

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

### Suspected Creators

Israel was the obvious suspect as they have been long against the Iranian nuclear program however there is no evidence in them creating it. The only bits of information that link to Israel is various dates and words found in the malware itself.

Another theory is that it was a joint operation between the US and Israel.

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