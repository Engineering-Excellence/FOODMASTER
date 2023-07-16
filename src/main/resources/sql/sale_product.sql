DROP TABLE SALE_PRODUCT;

CREATE TABLE SALE_PRODUCT
(
    SALEID    NUMBER,
    PRODUCTID NUMBER,
    PRIMARY KEY (SALEID, PRODUCTID),
    FOREIGN KEY (SALEID) REFERENCES SALE (SALEID),
    FOREIGN KEY (PRODUCTID) REFERENCES PRODUCT (PRODUCTID)
);
