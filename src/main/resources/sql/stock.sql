DROP TABLE STOCK CASCADE CONSTRAINTS;

CREATE TABLE STOCK
(
    STOCKID   INT
        CONSTRAINT PK_STOCK PRIMARY KEY,
    STOCKNAME VARCHAR2(30) NOT NULL,
    AMOUNT    INT   NOT NULL,
    UNITPRICE INT   NOT NULL,
    STOCKDATE DATE     NOT NULL,
    PRODUCTID INT   NOT NULL
);

ALTER TABLE STOCK
    ADD CONSTRAINT FK_PRODUCT_STOCK FOREIGN KEY (PRODUCTID)
        REFERENCES PRODUCT (PRODUCTID);

DROP SEQUENCE STOCKID_SEQ;

CREATE SEQUENCE STOCKID_SEQ;


BEGIN
	FOR I IN 1..300
		LOOP
			INSERT INTO STOCK
			VALUES (PCMASTER.STOCKID_SEQ.NEXTVAL, '신라면', 100, 1000, SYSDATE, 1);
		END LOOP;
END;

COMMIT;
