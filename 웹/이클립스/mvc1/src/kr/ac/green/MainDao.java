package kr.ac.green;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

// 싱글톤 패턴
public class MainDao {
	// 드라이버 로드
	private MainDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static final MainDao mainDao = new MainDao();
	// 게터
	public static MainDao getInstance() {
		return mainDao;
	}

	// 데이터베이스 연결
	public static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// 연결 해제
	public void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 인코딩, 디코딩
	private static String toEn(String kor) {
		String en = null;

		try {
			en = new String(kor.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return en;
	}

	private static String toKor(String en) {
		String kor = null;

		try {
			kor = new String(en.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return kor;
	}
	
	// 질의 던지는 메소드
	
	// 모든 값 가져오기
	public Car[] getAll(Connection con) {
		Car[] list = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM car ORDER BY car_id DESC";
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			Vector<Car> carList = new Vector<Car>();
			
			while(rs.next()) {
				int id = rs.getInt("car_id");
				String model = toKor(rs.getString("car_model"));
				String desc = toKor(rs.getString("car_desc"));
				int price = rs.getInt("car_price");
				
				carList.add(new Car(id, model, price, desc));
			}
			
			list = carList.toArray(new Car[0]);
		} catch (Exception e) {
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	// 데이터 추가하기
	public int insertCar(Connection con , Car car) {
		int result = 0;
		String sql = "INSERT INTO car(car_model, car_price, car_desc) VALUES('%s', %d, '%s')";
		sql = String.format(sql, toEn(car.getCar_model()), car.getCar_price(), toEn(car.getCar_desc()));
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			
			result = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}