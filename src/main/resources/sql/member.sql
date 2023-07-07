DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
    MEMBERID   INT
        CONSTRAINT PK_MEMBER PRIMARY KEY,
    NAME       VARCHAR2(30)           NOT NULL,
    EMAIL      VARCHAR2(50)           NOT NULL,
    PASSWORD   VARCHAR2(130)          NOT NULL,
    SALT       VARCHAR2(64)           NOT NULL,
    CONTACT    VARCHAR2(15)           NOT NULL,
    QUESTION   VARCHAR2(200)          NOT NULL,
    ANSWER     VARCHAR2(200)          NOT NULL,
    REGDATE    DATE   DEFAULT SYSDATE NOT NULL,
    REMAINTIME INT DEFAULT 0       NOT NULL
);

DROP SEQUENCE MEMBERID_SEQ;

CREATE SEQUENCE MEMBERID_SEQ;

BEGIN
    FOR I IN 1..300
        LOOP
            INSERT INTO MEMBER
            VALUES (PCMASTER.MEMBERID_SEQ.NEXTVAL, 'TESTNAME', 'TEST@TEST.COM', 'PASSWORD', 'SALT', '010-1234-1234', 'QUESTION', 'ANSWER', SYSDATE, 0);
        END LOOP;
END;

COMMIT;
