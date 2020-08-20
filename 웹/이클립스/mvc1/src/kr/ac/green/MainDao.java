package kr.ac.green;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

// �̱��� ����
public class MainDao {
	// ����̹� �ε�
	private MainDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static final MainDao mainDao = new MainDao();
	// ����
	public static MainDao getInstance() {
		return mainDao;
	}

	// �����ͺ��̽� ����
	public static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// ���� ����
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

	// ���ڵ�, ���ڵ�
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
	
	// ���� ������ �޼ҵ�
	
	// ��� �� ��������
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
	// ������ �߰��ϱ�
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