create database QuanLySinhVien;

use QuanLySinhVien;

create table SinhVien (
    id int,
    ho_ten nvarchar(50),
    ngay_sinh Date,
    que_quan nvarchar(50)
)

INSERT INTO SinhVien(id, ho_ten, ngay_sinh, que_quan)
VALUES
    (1, 'Lương Ngọc Linh Hân', '1999-5-9', 'Dong Nai'),
    (2, 'Đinh Nhật Nam', '1999-5-2', 'Khanh Hoa'),
    (3, 'Nguyễn Thanh Nam', '1998-3-4', 'Kon Tum')