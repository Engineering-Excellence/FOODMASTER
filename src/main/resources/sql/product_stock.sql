DROP TABLE PRODUCT_STOCK;

CREATE TABLE PRODUCT_STOCK
(
    PRODUCTID NUMBER,
    STOCKID   NUMBER,
    PRIMARY KEY (PRODUCTID, STOCKID),
    FOREIGN KEY (PRODUCTID) REFERENCES PRODUCT (PRODUCTID)
        ON DELETE CASCADE,
    FOREIGN KEY (STOCKID) REFERENCES STOCK (STOCKID)
        ON DELETE CASCADE
);
