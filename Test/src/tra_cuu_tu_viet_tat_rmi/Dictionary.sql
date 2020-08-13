CREATE DATABASE ThuatNgu;

USE ThuatNgu;

CREATE TABLE VietTat (
    tu_viet_tat varchar(50),
    tu_day_du nvarchar(100),
);

INSERT INTO VietTat(tu_viet_tat, tu_day_du)
VALUES
    ('KMA', 'hoc viet ky thuat mat ma'),
    ('IT', 'cong nghe thong tin')