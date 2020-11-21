CREATE TABLE AUTHOR
(
    ID           INT AUTO_INCREMENT,
    NAME        VARCHAR (40),
    PRIMARY KEY (ID)
);

CREATE TABLE BOOK (
    ISBN    VARCHAR(40),
    TITLE   VARCHAR (80) NOT NULL,
    PRICE   DOUBLE NOT NULL,
    AUTHOR_ID   INT NOT NULL,
    CREATED_AT TIMESTAMP NULL,
    LAST_UPDATED_AT TIMESTAMP NULL,
    PRIMARY KEY (ISBN),
    CONSTRAINT FK_BOOK_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID)
);

CREATE TABLE BOOK_STOCK(
    ID           INT AUTO_INCREMENT,
    BOOK_ISBN       VARCHAR (40) NOT NULL,
    TOTAL_STOCK     INT NOT NULL,
    ORDERED_STOCK   INT NOT NULL,
    VERSION         BIGINT NOT NULL,
    CREATED_AT TIMESTAMP NULL,
    LAST_UPDATED_AT TIMESTAMP NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_BOOK_STOCK_BOOK_ISBN FOREIGN KEY (BOOK_ISBN) REFERENCES BOOK(ISBN)
);

CREATE TABLE STOCK_ENTRY (
    ID           INT AUTO_INCREMENT,
    BOOK_STOCK_ID   INT NOT NULL,
    STOCK INT NOT  NULL,
    CREATED_AT TIMESTAMP NULL,
    LAST_UPDATED_AT TIMESTAMP NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_STOCK_ENTRY_TO_BOOK_STOCK FOREIGN KEY (BOOK_STOCK_ID) REFERENCES BOOK_STOCK(ID)
);

CREATE TABLE BOOK_ORDER (
    ID           INT AUTO_INCREMENT,
    BOOK_ISBN       VARCHAR (40) NOT NULL,
    QUANTITY    INT NOT NULL,
    COST        DOUBLE NOT NULL,
    TOTAL       DOUBLE  NOT NULL,
    STATUS      VARCHAR (40) NOT NULL,
    CREATED_AT TIMESTAMP NULL,
    LAST_UPDATED_AT TIMESTAMP NULL,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ORDER_BOOK_ISBN FOREIGN KEY (BOOK_ISBN) REFERENCES BOOK(ISBN)
);
