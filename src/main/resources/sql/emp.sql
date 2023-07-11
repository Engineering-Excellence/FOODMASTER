DROP TABLE IF EXISTS EMP;

CREATE TABLE EMP
(
    EMPID    INT
        CONSTRAINT PK_EMP PRIMARY KEY,
    ACCOUNT  VARCHAR2(30) NOT NULL,
    PASSWORD VARCHAR2(130) NOT NULL,
    SALT     VARCHAR2(64) NOT NULL
);

DROP SEQUENCE IF EXISTS EMPID_SEQ;

CREATE SEQUENCE EMPID_SEQ;
