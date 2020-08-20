CREATE TABLE student (
	student_id		INT			PRIMARY KEY		AUTO_INCREMENT,
	student_name	VARCHAR(10)	NOT NULL,
	student_tel		VARCHAR(13)	NOT	NULL,
	student_grade	INT			NOT	NULL,
	student_class	VARCHAR(3)		NOT	NULL
);
-- 모든 정보 가져오기
SELECT * FROM student ORDER BY student_id DESC;

-- 등록
INSERT INTO student(student_name, student_tel, student_grade, student_class)
	VALUES('a','123',30,'A');
-- 수정
UPDATE student SET student_tel='4444', student_grade=20, student_class='B' WHERE student_id=1;

DELETE FROM student;

-- 삭제
DELETE FROM student WHERE student_id = ?
-- 검색(id, name, class)
SELECT * FROM student WHERE student_id=24;
SELECT * FROM student WHERE student_name=?
SELECT * FROM student WHERE student_class=?

DROP TABLE student;

