-- �ȿ� ��ӵǾ� �ִ� ��ɾ�� �빮��


CREATE TABLE car (
	car_id		INT				PRIMARY KEY			AUTO_INCREMENT,
	car_model	CHAR(20)		NOT NULL,
	car_price	INT				NOT NULL,
	car_desc	VARCHAR(200)	
);

DESC car;

/*
 * ������ �Է�(INSERT INTO)
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
 * ������ �б�(Read) -> (SELECT)
 * FROM ���̺��� �а���� �� �̸��� �´�.
 */
SELECT * FROM car;

-- ��Ī �ޱ�
SELECT car_model AS model, car_price AS price FROM car;

/*
 * ���� WHERE 
 * = ����, < , > , <> �ٸ���
 */
SELECT * FROM car WHERE car_price <> 3000;

-- ������ 2500�̻� 5000����
SELECT * FROM car WHERE car_price >= 2500 AND car_price < 5000;

SELECT * FROM car WHERE car_price BETWEEN 3000 AND 3700;

-- ������ 3000�̰ų� 3700�� ��
SELECT * FROM car WHERE car_price = 3000 OR car_price = 3700;

SELECT * FROM car WHERE car_price IN(3000, 2500);

-- ������ NULL�� �ڵ��� , NULL �� �ƴ� �ڵ���
SELECT car_model FROM car WHERE car_desc IS NULL;

SELECT car_model FROM car WHERE car_desc IS NOT NULL;

-- ����(��������, ��������)
SELECT * FROM car ORDER BY car_price ASC;

SELECT * FROM car ORDER BY car_price DESC;

SELECT * FROM car ORDER BY car_price DESC, car_id DESC;

-- FROM -> WHERE -> ORDER BY
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC;

-- MySQL ���� �ִ� ���
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC LIMIT 3;
-- INDEX ���� ����
SELECT * FROM car WHERE car_price >= 2500 ORDER BY car_price DESC, car_id DESC LIMIT 3, 2;

/*
 * % ���ϵ�ī�� ( 0���� ~ ���Ѵ� ���� )
 * _ ������ 1���ڸ� ������ �Ѵ�. _m_ 3���� �� �߰��� m�� �� ����
 * index �� �����ϴ� �����߿� �ϳ� (���� , ���� , ����, ����) �ʹ� ���� �ε����� ��ȿ���� �� �� �ִ�.
 */
SELECT car_model FROM car WHERE car_model LIKE 'm%';

SELECT car_model FROM car WHERE car_model LIKE 'm_';

/*
 * ����(Delete) -> DELETE FROM 
 */
SELECT * FROM car WHERE car_model = 'k5';
-- �� ������ ������ �������� !
DELETE FROM car WHERE car_model = 'k5';

/*
 * ����(Update) -? UPDATE
 */

SELECT * FROM car WHERE car_model = 'avante';
UPDATE car SET car_desc = 'very good', car_price = 3900 WHERE car_model = 'avante';

SELECT * FROM car;
DROP TABLE car;
