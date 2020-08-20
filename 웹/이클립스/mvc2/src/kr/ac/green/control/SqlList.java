package kr.ac.green.control;

public interface SqlList {
	String[] SQL = {
		"SELECT * FROM car ORDER BY car_id DESC",
		"INSERT INTO car(car_model, car_price, car_desc) VALUES(?, ?, ?)",
		"DELETE FROM car"
		
	};
	
	int GET_ALL = 0;
	int INSERT = 1;
	int DELETE = 2;
}
