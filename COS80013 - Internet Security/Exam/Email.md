## Email

Understanding email headers is important to understanding how it filters SPAM.

Below shows an example of a email header -

```
Delivered-To: MrSmith@gmail.com
Received: by 10.36.81.3 with SMTP id e3cs239nzb; Tue, 29 Mar 2005 15:11:47 -0800 (PST)
Return-Path: 

Received: from mail.emailprovider.com (mail.emailprovider.com [111.111.11.111]) by mx.gmail.com with SMTP id h19si826631rnb.2005.03.29.15.11.46; Tue, 29 Mar 2005 15:11:47 -0800 (PST)
Message-ID: <20050329231145.62086.mail@mail.emailprovider.com>

Received: from [11.11.111.111] by mail.emailprovider.com via HTTP; Tue, 29 Mar 2005 15:11:45 PST
Date: Tue, 29 Mar 2005 15:11:45 -0800 (PST)
From: Mr Jones 
Subject: Hello
To: Mr Smith 
```

In the example above, it shows that the headers were added to the message 3 times.

The first is when Mr Jones composes the email (Date, From, Subject, To).

The second is when the email is sent through the servers of Mr Jones's email provider mail.emailprovider.com

The third is when the email provider transfers it to Mr Smith's gmail address.


```Delivered-To: MrSmith@gmail.com``` - The address the message will be delivered to.
```Received: by 10.36.81.3 with SMTP id e3cs239nzb; Tue, 29 Mar 2005 15:11:47 -0800 (PST)``` - The time it reached it reached the mail server.
```Return-Path: ``` - The address from which the email was send.

```
Received: from mail.emailprovider.com (mail.emailprovider.com [111.111.11.111]) by mx.gmail.com with SMTP id h19si826631rnb.2005.03.29.15.11.46; Tue, 29 Mar 2005 15:11:47 -0800 (PST)
```

- The message was received from emailprovider.com by the mail server above.

```Message-ID: <20050329231145.62086.mail@mail.emailprovider.com>``` - The message ID provided to identify the email by emailprovider.com

```Received: from [11.11.111.111] by mail.emailprovider.com via HTTP; Tue, 29 Mar 2005 15:11:45 PST``` - The time the message was received from the mail server.

```
Date: Tue, 29 Mar 2005 15:11:45 -0800 (PST)
From: Mr Jones 
Subject: Hello
To: Mr Smith 
```

The contents of the email.

With an email header, the most recent events are up the top, therefore to trace from sender to recipient we need to go from bottom to top.
