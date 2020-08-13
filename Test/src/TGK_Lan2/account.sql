CREATE DATABASE AT14N01_Bank;

USE AT14N01_Bank;

CREATE TABLE account (
	id int NOT NULL,
	balance float DEFAULT 0,
	withdraw_day date,
	deposit_day date
)

INSERT INTO account(id, balance)
VALUES
	(1, 20000),
	(2, 3000),
	(3, 400)