DROP TABLE ORDER;

CREATE TABLE ORDER
(
    ORDERID   NUMBER
        CONSTRAINT PK_ORDER PRIMARY KEY,
    QUANTITY  NUMBER               NOT NULL,
    ORDERDATE DATE DEFAULT SYSDATE NOT NULL,
    STOCKID   NUMBER               NOT NULL
);

ALTER TABLE ORDER
    ADD CONSTRAINT FK_STOCK_ORDER FOREIGN KEY (STOCKID)
        REFERENCES STOCK (STOCKID);

ALTER TABLE ORDER
    ADD CONSTRAINT FK_EMP_ORDER FOREIGN KEY (EMPID)
        REFERENCES EMP (EMPID);

DROP SEQUENCE ORDERID_SEQ;

CREATE SEQUENCE ORDERID_SEQ;
