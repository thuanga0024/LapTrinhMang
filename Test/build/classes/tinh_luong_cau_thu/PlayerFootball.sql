CREATE  DATABASE PlayerFootball;

USE PlayerFootball;

CREATE TABLE player (
	id int NOT NULL,
	name nvarchar(50) NOT NULL,
	year_of_birth int,
	position_player nvarchar(20),
	salary float
);

INSERT  INTO player(id, name, year_of_birth, position_player, salary)
VALUES 
	(1, 'Nguyen Hung Dung', 1997, 'Tien ve', 1000),
	(2, 'Nguyen Tien Linh', 1995, 'Tien dao', 1500)

