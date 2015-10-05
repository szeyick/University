## Cryptography and Secure Communication

### What is Network Security

- **Secrecy** 

Only the sender and intended recipient should understand the contents of the message. The sender should encrypt and the receiver should decrypt the message.

- **Authentication**

The sender and receiver should be able to confirm each others identity.

- **Message Integrity**

The sender and receiver should be assured that the message sent is the same one that has been received.

### Packet Sniffing

Affects messages that are broadcasted since a NIC (Network Interface Card) can read all the packets that are incoming and outgoing, therefore it can have the capability to intercept and read unecrypted data.

### IP Spoofing

Create an IP packet with any value in the IP source field to trick the receiver of the sender. This will mean that the receiver will not know whether the sender if the original sender.

### Denial of Service (DOS)

Generating large numbers of IP packets to flood the receiver, a DDOS (Distributed Denial of Service) is where multiple sources flood the same reciever simultaneously.

### Symmetric Key

The idea in cryptography where the key used to encrypt is the same key used to decrypt, meaning that the key that the sender and receiver use are identical.

- DES (Data Encyption Standard)

Is the US encryption standard that uses a 56-bit symmetric key for 64 bit plaintext input.

**Substitution Cipher**

Where you subsitute plaintext characters (the thing you want to encrypt) with ciphertext (the characters to change each character to) to generate the encrypted message -

```
Cipher:
plaintext:  abcd
ciphertext: defg

Message:
plaintext:  dad
ciphertext: gdg
```

Substitution ciphers can be seen as easy to crack, especially if you know the key, since the key would control how the characters are converted.

- Polyalphabetic Cipher

Is a type of cipher where each character is "rotated" by a different amount (1-25), the number that it is rotated is kept in a lookup table. Everytime the same character is hit, the same mapping is used. 

This cipher is seen to be easily cracked especially if the message contains lots of commonly used words.

- Codebook Cipher, Vignere, OTP

Each character is again rotated by an amount by the key is different each time the same letter is encountered. If the mapping of a ciphertext and plantext letter is rarely repeated, it can be very hard to crack especially if the message never repeats letters or words and the key is completely randomised.

### Public Key Encryption

The idea in cryptography where the key used to encrypt is a public key, and the key used to decrypt is kept private/secret.

For public key cryptography, a publically known encryption key is used to encrypt the message, a decryption key only known to the message receiver is used to decrypt the message.

The requirements of a encryption key algorithm is -

**Insert Image**

So that the encryption and decryption will correctly reveal the same message. However we should also be never be able to generate the decryption key from the public key. An example of this would be the RSA algorithm.

**RSA (Rivest, Shamir, Adelson Algorithm)**

The process of choosing a key for the RSA algorithm is -

- Select two large prime numbers **p** and **q**
- Compute **n** where n = p.q, z = (p-1)(q-1)
- Choose **e** where e < n, and where e has **no common factors** with z.
- Choose **d** where ed - 1 is exactly divisible by z
- Public Key is (n,e). Private key is (n.d)

To encrypt the bit pattern **m**

- c = m^e mod n 

To decrypt the received bit pattern **c**

- m = c^d mod n

Therefore **m = (m^e mod n)d mod n**

**Example**

**Insert RSA Image Example Page 21 Lecture 8**

Another important propertly of the RSA is that using the public key then the private key to encrypt and using the private key then the public key will decrypt.

### Digital Singnature

It is a crytographic technique used to mimic real life signatures. The message is encrypted with a private key and the original message and encrypted message are sent to the recipient.

The recipient will then decrypt the message with the senders public key and check that the decrypted message equals the originak message. If they equal it would mean that the sender had the same private key.

###Message Digests

To generate computationally expensive public keys to encrypt long messages. The goal is to create fixed length signatures to apply a hash function to.

### Hash Function Algorithms

MD5 hash function computes a 128 bit message digest in a 4 step process, it is difficult to construct a random 128 bit string to match the MD5 hashed message where the hashed message is the same as the generated one.

SHA-1 uses a 160 bit message digest.

Collisions in hash functions  make it possible to construct different messages with the same MD5 hash.

###Trusted Intermediaries

The problem with sharing secret keys over a network is that you still don't know whether the key is from that other person. The solution here would be to use a KDC (Key Distribution Centre) that functions as a intermediary between the sender and receiver, otherwise known as a CA (Certification Authority).

The idea here is that the KDC shares a different key with each other, so the sender and receiver first communicate with the KDC. The KDC is the one that provides the symmetric key for the sender and receiver.

### Certification Authorities (CA)

Binds a public key to an entity, where an entity can register a public key with a CA. The entity is required to prove their identity to the CA before it is authenticated for use.

Here the recipient will request a public key from the CA, then the certificate from the sender and apply the public key to the certificate to retrieve the senders actual public key.

CA's are used to authenticate the integrity of a message.

### CA Certificates

These certificates contain information that is unique to the CA along with other information about the certificate owner and the algorithm used.

### Pretty Good Privacy

Is the standard for Internet email encryption that uses all of the above techniques. It fulfills the criteria of providing secrecy, authentication and integrity.

### Diffie Hellman

Uses a session key that is generated by both the client and server, generating random numbers and sending it to each other.

After this is done, both client and server have both numbers and can use them to generate a shared key. This key cannot be determined by sniffing.

### SSL (Secure Sockets Layer)

Used between browsers and servers for internet commerce (HTTPS). It provides data encryption, server authentication and optional client authentication.

For browsers using SSL, it will request a certificate from a server that is issued by a CA. The browser will then use the CA's public key to extract the servers public key from the certificate sent from the CA.

For an encrypted SSL session, the browser will generate a symmetric session key and encrypts it with the servers public key and sends the encrypted session key to the server.

The server will then use its private key to decrypt the session key. The browser and server will then agree that all messages will be encrypted and all data sent between from there on in will be encrypted with the session key.

SSL is used for the transport layer security (TLS) and non-web applications.

### Secure Electronic Transactions (SET)

Designed for payments over the internet to provide security for customers, merchants and the merchants bank. All three entities must have certificates.

In this system, the customers card information is passed to the merchants bank without the merchant ever knowing the card number.

The main components of this system are the, customer wallet, merchants server and the acquirer gateway.

### IPsec: Network Layer Security

- The sending host encrypts the data in a IP datagram.

- The destination host can authenticate the source IP.

- Two authentication protocols, AH (Authentication Header) and ESP (Encapsulation Security Payload).

- For both protocols, the handshake creates a SA (Service Agreement) channel.

- The SA is a one directional channel and is uniquely determined by the protocol, source IP and connection ID (32-bit)

### Authentication Header Protocol (AH)

Provides host authentication and data integrity, but does not provide secrecy.

This header is inserted between the IP header and IP data field, the protocol field is defined in the IP header (protocol 51).

This header block includes the connection ID, signed authentication data and the the type of data.

**Insert Image Page 56**

### Encapsulation Security Payload (ESP) Protocol**

Provides host authentication and data integrity. The data and the ESP trailer itself are encrypted.

The next header field is in the ESP trailer (tail). The authentication field is similar to that of the authentication header, and it uses protocol 50.

**Insert Image Page 57**

### IPSec Adoption

Is mandatory in IPv6 but is currently not used by everyone. The reason for that is that IPv4 addresses hadn't run out yet and with IPv6, all IP addresses are unique meaning that they can be tracked and assigned to individuals.