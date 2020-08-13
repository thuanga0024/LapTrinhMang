CREATE DATABASE dethi;

USE dethi;

CREATE TABLE Exam (
    id int,
    question nvarchar(200),
    answer1 nvarchar(200),
    answer2 nvarchar(200),
    answer3 nvarchar(200),
    answer4 nvarchar(200),
    result nvarchar(50)
);

INSERT INTO Exam(id, question, answer1, answer2, answer3, answer4, result)
VALUES
    (1, "Những nước nào tham gia Hội nghị Ianta ?", "A. Anh - Pháp - Mĩ.", "B. Anh - Mĩ - Liên Xô.", "C. Anh - Pháp - Đức.", "D. Mĩ - Liên Xô - Trung Quốc.", "B"),
    (2, "Nằm ở rìa phía Đông của bán đảo Đông Dương là nước", "A. Lào", "B. Campuchia", "C. Việt Nam", "D. Mi-an-ma", "C"),
    (3, "1+1=?", "A. 2", "B. 2-1", "C. 4-3", "D. 2", "A,B,C")