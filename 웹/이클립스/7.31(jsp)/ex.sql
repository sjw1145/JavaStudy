-- 안에 약속되어 있는 명령어는 대문자


CREATE TABLE car (
	car_id		INT				PRIMARY KEY			AUTO_INCREMENT,
	car_model	CHAR(20)		NOT NULL,
	car_price	INT				NOT NULL,
	car_desc	VARCHAR(200)	
);

DESC car;

/*
 * 데이터 입력(INSERT INTO)
 */
INSERT INTO car (car_model, car_price, car_desc)
	VALUES ('g80', '8000', 'very good');

INSERT INTO car (car_desc, car_model, car_price)
	VALUES ('good', 'sonata', 3000);

INSERT INTO car (car_model, car_price)
	VALUES ('morning', 1500);

INSERT INTO car 
		(car_model, car_price, car_desc)
	VALUES 
		('avante', 2500, 'bad'), ('audiA8', 10000, 'nice'),
		('k5', 3000, 'trash'), ('malibu', 3100, 'good')


/*
 * 데이터 읽기(Read) -> (SELECT)
 * FROM 테이블에서 읽고싶은 열 이름이 온다.
 */
SELECT * FROM car;

-- 별칭 달기
SELECT car_model AS model, car_price AS price FROM car;

/*
 * 조건 WHERE 
 * = 같다, < , > , <> 다르다
 */
SELECT * FROM car WHERE car_price <> 3000;

-- 가격이 2500이상 5000이하
SELECT * FROM car WHERE car_price >= 2500 AND car_price < 5000;

SELECT * FROM car WHERE car_price BETWEEN 3000 AND 3700;

-- 가격이 3000이거나 3700인 것
SELECT * FROM car WHERE car_price = 3000 OR car_price = 3700;

SELECT * FROM car WHERE car_price IN(3000, 2500);

-- 설명이 NULL인 자동차 , NULL 이 아닌 자동차
SELECT car_model FROM car WHERE car_desc IS NULL;

SELECT car_model FROM car WHERE car_desc IS NOT NULL;

-- 정렬(오름차순, 내림차순)
SELECT * FROM car ORDER BY car_price ASC;

SELECT * FROM car ORDER BY car_price DESC;

SELECT * FROM car ORDER BY car_price DESC, car_id DESC;

-- FROM -> WHERE -> ORDER BY
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC;

-- MySQL 에만 있는 기능
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC LIMIT 3;
-- INDEX 부터 개수
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC LIMIT 3, 2;

/*
 * % 와일드카드 ( 0부터 ~ 무한대 까지 )
 * _ 무조건 1글자를 만들어야 한다. _m_ 3글자 중 중간에 m이 들어간 글자
 * index 가 정렬하는 기준중에 하나 (변경 , 수정 , 삭제, 삽입) 너무 많은 인덱스는 역효과가 올 수 있다.
 */
SELECT car_model FROM car WHERE car_model LIKE 'm%';

SELECT car_model FROM car WHERE car_model LIKE 'm_';

/*
 * 삭제(Delete) -> DELETE FROM 
 */
SELECT * FROM car WHERE car_model = 'k5';
-- 위 조건이 맞으면 지워야함 !
DELETE FROM car WHERE car_model = 'k5';

/*
 * 수정(Update) -? UPDATE
 */

SELECT * FROM car WHERE car_model = 'avante';
UPDATE car SET car_desc = 'very good', car_price = 3900 WHERE car_model = 'avante';

SELECT * FROM car;
DROP TABLE car;
