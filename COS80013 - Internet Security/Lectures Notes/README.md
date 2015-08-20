## Rough Notes for Lecture 3

Patches are usually held off until the 0 day exploit has been seen before they release it. The reason being that patching it warns attackers that there is a vulnerability in that particular area that they can look at it more closely.

Also if you receive patches really often it degrades the perception of the company making people think that they have massive problems.

Theres no authentication on the internet, it is usually build into the application.

### C Programming

Is the language that is built on top of assembly allowing programmers to play with memory registers and stuff in a higher language. 0 means false in C, anything else means true.

### Buffer Overflow

An exploit in C that copies past the length of an allocated array. This is done because C actually lets you do it and the consequence of this is that it will let you write into other memory register areas.

A buffer overflow is the process of executing data as code. This becomes a problem as you can inject executable code as data.
