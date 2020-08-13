CREATE  DATABASE QLCH;

USE QLCH;

CREATE  TABLE product (
	id int NOT NULL PRIMARY KEY,
	name nvarchar(50),
	price float,
	amount int
)

INSERT INTO product (id, name, price, amount)
VALUES 
	(1, 'rau cai', 100, 200),
	(2, 'ngo', 50, 300),
	(3, 'ca meo', 200, 50),
	(4, 'thit ba chi', 500, 20)