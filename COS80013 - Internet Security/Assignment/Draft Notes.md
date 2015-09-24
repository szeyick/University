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