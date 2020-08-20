package kr.ac.green.control;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.green.dto.Car;

public class CarDao {

	private static final CarDao instance = new CarDao();

	private CarDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static CarDao getInstance() {
		return instance;
	}

	public Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void disconnect(Connection con) {
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

	public void close(PreparedStatement pStmt) {
		try {
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// euc_kr -> 8859_1
	public String toKor(String en) {
		String kor = null;

		try {
			kor = new String(en.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return kor;
	}

	public String toEn(String kor) {
		String en = null;

		try {
			en = new String(kor.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return en;
	}

	public Car[] getAll(Connection con) {
		Car[] list = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;

		try {
			pStmt = con.prepareStatement(SqlList.SQL[SqlList.GET_ALL]);
			rs = pStmt.executeQuery();

			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();

			list = new Car[size];

			int idx = 0;
			while (rs.next()) {
				int car_id = rs.getInt("car_id");
				String car_model = toEn(rs.getString("car_model"));
				int car_price = rs.getInt("car_price");
				String car_desc = toEn(rs.getString("car_desc"));

				list[idx++] = new Car(car_id, car_model, car_price, car_desc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pStmt);
		}

		return list;
	}

	public int insertCar(Connection con, Car car) {
		int result = 0;

		PreparedStatement pStmt = null;

		try {
			pStmt = con.prepareStatement(SqlList.SQL[SqlList.INSERT]);

			pStmt.setString(1, toKor(car.getCar_model()));
			pStmt.setInt(2, car.getPrice());
			pStmt.setString(3, toKor(car.getCar_desc()));

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pStmt);
		}

		return result;
	}

	public int deleteCar(Connection con) {
		int result = 0;

		PreparedStatement pStmt = null;

		try {
			pStmt = con.prepareStatement(SqlList.SQL[SqlList.DELETE]);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pStmt);
		}
		return result;
	}
}
