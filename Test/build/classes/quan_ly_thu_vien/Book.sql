CREATE DATABASE AT14N01_QLTV;

CREATE TABLE book (
	id int NOT NULL PRIMARY KEY,
	name nvarchar(50) NOT NULL,
	publisher nvarchar(50),
	total int,
	amount_borrowed int DEFAULT 0
)