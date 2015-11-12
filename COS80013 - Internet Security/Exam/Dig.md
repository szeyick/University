## Dig

Is a command tool for querying DNS servers for information. 

### Dig Default

The default command is ```dig <domain name>```. 

If we do it on telstra.com we get the output - 

```
; <<>> DiG 9.8.3-P1 <<>> www.telstra.com
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 32407
;; flags: qr rd ra; QUERY: 1, ANSWER: 1, AUTHORITY: 4, ADDITIONAL: 0

;; QUESTION SECTION:
;www.telstra.com.		IN	A

;; ANSWER SECTION:
www.telstra.com.	67	IN	A	144.140.108.23

;; AUTHORITY SECTION:
telstra.com.		33419	IN	NS	dns1.telstra.net.
telstra.com.		33419	IN	NS	dns0.telstra.net.
telstra.com.		33419	IN	NS	sec1.apnic.net.
telstra.com.		33419	IN	NS	sec3.apnic.net.

;; Query time: 22 msec
;; SERVER: 192.168.0.1#53(192.168.0.1)
;; WHEN: Tue Nov 10 13:39:11 2015
;; MSG SIZE  rcvd: 142
```

The opening section just tells you about the version fo Dig and the global options that are set. The second line with the flags tells us the answers received from the DNS server.

QUESTION SECTION - The query that we made, with the type (A)
ANSWER SECTION - The answer, with the IP for the domain name.
AUTHORITY SECTION - Which DNS servers can provide the authoratitive answer.