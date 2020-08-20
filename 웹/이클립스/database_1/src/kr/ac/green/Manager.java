package kr.ac.green;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Manager {

	private static String root = "root";
	private static String pw = "1234";

	public static Connection con;
	public static Statement stmt;

	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void connection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", root, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int query(String sql) {
		int result = 0;
		try {
			stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public static ResultSet search(String sql) {
		try {
			ResultSet rs;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
