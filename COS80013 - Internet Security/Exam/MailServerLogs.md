## Mail Server Logs

The below snippet is an example of a mail server log

```
Mar 16 04:19:32 localhost dovecot: pop3-login: Aborted login: user=<abuse>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:33 localhost dovecot: pop3-login: Aborted login: user=<access>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:34 localhost dovecot: pop3-login: Aborted login: user=<abby>, method=PLAIN, rip=::ffff:70.228.46.25
Mar 16 04:19:34 localhost dovecot: pop3-login: Aborted login: user=<abraham>, method=PLAIN, rip=::ffff:70.228.46.25 
```

We have the following fields

Date and Time - The date and time that the mail was received by this server (mail agent)
LocalHost Dovecot - The hostname of the machine receiving
Pop3-login - The method called to login (string, string) for username and password. Aborted means unsuccessful login attempt.
