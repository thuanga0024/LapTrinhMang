CREATE DATABASE dictionary;

USE dictionary;

CREATE TABLE english (
    english varchar(50),
    vietnamese nvarchar(50),
    meaning nvarchar(100)
);

INSERT INTO english(english, vietnamese, meaning)
VALUES
    ('hello', 'xin chào', 'chào một ai đó'),
    ('hi', 'chào', 'chào một ai đó'),
    ('clap', 'vỗ tay', 'hành động vỗ tay')