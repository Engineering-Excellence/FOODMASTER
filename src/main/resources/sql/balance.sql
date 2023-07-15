DROP TABLE BALANCE CASCADE CONSTRAINTS;

CREATE TABLE BALANCE
(
    ACCOUNTID   INT NOT NULL,
    CURRENCY  VARCHAR2(10) NOT NULL,   
    AMOUNT    INT  NOT NULL,
    LASTUPDATE DATE NOT NULL
);


BEGIN
FOR I IN 1..300
		LOOP
			INSERT INTO BALANCE
			VALUES (100, 'KRW', 1000, SYSDATE);
END LOOP;
END;

COMMIT;


