CREATE TABLE member2(
	user_id		VARCHAR(14)		PRIMARY KEY ,
	user_pw		VARCHAR(25)		NOT NULL ,
	user_name	VARCHAR(10)		NOT NULL ,
	user_nick	VARCHAR(8)		NOT NULL ,
	user_age	INT				NOT NULL
);

SELECT * FROM member;

SELECT user_id, user_pw FROM member;

DESC member;

SELECT * FROM member WHERE user_id ='sjw1145';

INSERT INTO member(user_id, user_pw, user_name, user_nick) 
VALUES('sjw11455', 'asd123', 'jongwan', 'sunset');

SELECT * FROM member WHERE user_id='a' AND user_pw='asd123';


INSERT INTO member2(user_id, user_pw, user_name, user_nick, user_age) 
VALUES
	('a', 'asd123', 'A', 'AA', 25) ,
	('b', 'asd123', 'B', 'BB', 30) ,
	('c', 'asd123', 'C', 'CC', 34) ,
	('d', 'asd123', 'D', 'DD', 19) ,
	('e', 'asd123', 'E', 'EE', 23) ,
	('f', 'asd123', 'F', 'FF', 21);

SELECT * FROM member WHERE user_id = 'sjw1145'; 

SELECT * FROM member WHERE user_id <> 'sjw1145';

SELECT * FROM member WHERE user_id = 'sjw11455' OR user_name = 'A';

SELECT * FROM member WHERE user_id <> 'sjw1145' AND user_id <> 'sjw11455';

SELECT * FROM member WHERE user_id IS NOT NULL;

SELECT * FROM member WHERE user_id LIKE '_'; 

SELECT * FROM member2 WHERE user_age < 30 ORDER BY user_age, user_name DESC;

SELECT * FROM member2 LIMIT 3;

DELETE FROM member2 WHERE user_id = 'sjw1145';

SELECT * FROM member;

UPDATE member2 SET user_name = 'AA', user_nick = 'AAA' WHERE user_id = 'a';

SELECT * FROM member WHERE user_id='qwe' AND user_pw='123';


SELECT * FROM member WHERE user_id='asd123';