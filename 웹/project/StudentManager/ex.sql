CREATE TABLE student (
	student_id		INT			PRIMARY KEY		AUTO_INCREMENT,
	student_name	VARCHAR(10)	NOT NULL,
	student_tel		VARCHAR(13)	NOT	NULL,
	student_grade	INT			NOT	NULL,
	student_class	VARCHAR(3)		NOT	NULL
);
-- ��� ���� ��������
SELECT * FROM student ORDER BY student_id DESC;

-- ���
INSERT INTO student(student_name, student_tel, student_grade, student_class)
	VALUES('a','123',30,'A');
-- ����
UPDATE student SET student_tel='4444', student_grade=20, student_class='B' WHERE student_id=1;

DELETE FROM student;

-- ����
DELETE FROM student WHERE student_id = ?
-- �˻�(id, name, class)
SELECT * FROM student WHERE student_id=24;
SELECT * FROM student WHERE student_name=?
SELECT * FROM student WHERE student_class=?

DROP TABLE student;

