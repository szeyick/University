## Cloud Security

### What is Cloud

Cloud = Web Applications + Web Services + Virtualisation

It is cheap because it can be added and reduced depending on load. Also easy to set up and security is not managed by you.

### Web Applications

Accessing software through the web browser.

### Web Services

Distributed web applications that interact with one another. No human input is required and these services talk to each other through exchanging of XML data.

### Virtualisation

Allows OS to be run through virtualised hardware. Like a virtual machine but on the Internet. A single physical machine can run multiple virtual operating systems managed by a hypervisor.

### Cloud Usage

IaaS - Infrastructure as a service

Get a few virtual PC's and do whatever you want with it to set up your service (EC2)

PaaS - Platform as a service

Develop software, website and deploy it to the Cloud. You don't need to worry about the hardware overhead

SaaS - Software as a Service

Software that is sitting in the cloud that functions like a web application. These things can be like DropBox, iTunes, Spotify, Google Docs and types of cloud based software.

### Cloud Risks

Confidentiality - Who can see your data, because your data sits on a hardware with other people it can be possible to hack the hypervisor to jump between operating systems sitting on the one physical device.

Data would be best if stored in the cloud to be encrypted, communication shoud be encrypted so to use SSL/HTTPS/VPN rather than normal HTTP. Utilise TNO here.

Integrity - Is the data safe from interference

Implement guards to stop people from people changing your data, password it. Prevent other unauthorised users from backing up and restoring, and utilise logins and intrustion detection systems.

Accessibility - Is your cloud system always available, there is DDOS attacks and 99.9% guaranteed uptime.

Use the 3-2-1 rule, 3 backups, 2 types of media and 1 offsite backup. Geolocated data centres can guarantee minimal downtime.

### Cloud Questions

- Who owns my data, can I move it?
- If I delete by data, does it get fully deleted
- Where will it be stored, how often will it be backed up and who access to these backups
- Who has jurisdiction over my data, if the backup site is in another country, do their laws apply to my data?
- Who will be sharing my hardware box.
- If my data is has been lost or changed, can I sue anyone.
- What is my disaster recovery plan if everything is lost.
- Do I have access to logs, images and backups if there is a breach?