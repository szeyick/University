## Relational Schema

EXPERT (expertid, expfirstname, explastname, expcontact)
	PRIMARY KEY - expertid

TOUR LEADER (expertid, tourdate)
	PRIMARY KEY - expertid, tourdate
	FOREIGN KEY - expertid REFERENCES EXPERT
	FOREIGN KEY - tourdate REFERENCES TOUR OFFERING

TOUR OFFERING (tourdate, tourprice, tourid)
	PRIMARY KEY - tourdate
	FOREIGN KEY - tourid REFERENCES TOUR

TOUR (tourid, tourname, tourdesc)
	PRIMARY KEY - tourid

PAYMENT (paydate, custid, paytype, amount)
	PRIMARY KEY - paydate, custid
	FOREIGN KEY - (custid, paydate, tourdate) REFERENCES BOOKING

GIFT (giftid, diftdesc)
	PRIMARY KEY - giftid

GIFT OFFERING (giftid, tourdate)
	PRIMARY KEY - giftid, tourdate
	FOREIGN KEY - giftid REFERENCES GIFT
	FOREIGN KEY - tourdate REFERENCES TOUR OFFERING

GIFT CHOSEN (giftid, custid)
	PRIMARY KEY - giftid
	FOREIGN KEY - giftid REFERENCES GIFT OFFERING
	FOREIGN KEY - (custid, paydate, tourdate) REFERENCES BOOKING

CUSTOMER (custid, custfirstname, custlastname, custaddress, custcontact)
	PRIMARY KEY - custid

BROCHURE REQUEST (reqdate, custid, tourid, sentdate)
	PRIMARY KEY - reqdate, custid, tourid
	FOREIGN KEY - custid REFERENCES CUSTOMER
	FOREIGN KEY - tourid REFERENCES TOUR

BOOKING	(custid, paydate, tourdate)
	PRIMARY KEY - custid, paydate, tourdate
	FOREIGN KEY - custid REFERENCES CUSTOMER
	FOREIGN KEY - paydate REFERENCES PAYMENT
	FOREIGN KEY - tourdate REFERENCES TOUR OFFERING