DROP TABLE SALE CASCADE CONSTRAINTS;

CREATE TABLE SALE
(
    SALEID    NUMBER
        CONSTRAINT PK_SALE PRIMARY KEY,
    INCOME    NUMBER NOT NULL
        CONSTRAINT CK_SALE_INCOME CHECK (INCOME >= 0),
    SALEDATE  DATE,
    MEMBERID  NUMBER,
    ACCOUNTID VARCHAR2(20)
);

ALTER TABLE SALE
    ADD CONSTRAINT FK_MEMBER_SALE FOREIGN KEY (MEMBERID)
        REFERENCES MEMBER (MEMBERID)
            ON DELETE SET NULL;

ALTER TABLE SALE
    ADD CONSTRAINT FK_BALANCE_SALE FOREIGN KEY (ACCOUNTID)
        REFERENCES BALANCE (ACCOUNTID);

DROP SEQUENCE SALEID_SEQ;

CREATE SEQUENCE SALEID_SEQ;
