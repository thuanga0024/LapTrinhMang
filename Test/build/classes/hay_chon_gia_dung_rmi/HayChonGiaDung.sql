CREATE DATABASE HayChonGiaDung;

USE HayChonGiaDung;

CREATE TABLE mat_hang (
    ma_hang_hoa nvarchar(50),
    ten_hang_hoa nvarchar(50),
    gia_hang_hoa float
)

INSERT INTO mat_hang(ma_hang_hoa, ten_hang_hoa, gia_hang_hoa)
VALUES
    ('1', 'socola', 5000),
    ('2', 'mi hao hao', 4000),
    ('3', 'sting', 10000)