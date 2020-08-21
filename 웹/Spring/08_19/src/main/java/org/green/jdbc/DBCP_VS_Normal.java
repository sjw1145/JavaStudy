package org.green.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCP_VS_Normal {
	public static final int COUNT = 10000;
	
	public static void normal() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;	
		long time = System.currentTimeMillis();
		for(int i=0; i<COUNT; i++) {
			try {
				con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test",
					"root",
					"1234"
				);
			} catch (SQLException e) {				
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch(Exception e) {}
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println("normal : " + time + "ms");
	}
	
	private static BasicDataSource ds = new BasicDataSource();
	
	static {
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("1234");
		ds.setInitialSize(10);
		ds.setMaxActive(10);
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
	}
	
	public static void dbcp() {
		long time = System.currentTimeMillis();
		Connection con = null;
		for(int i=0; i<COUNT; i++) {
			try {
				con = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (Exception e) {	}
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println("dbcp : " + time + "ms");
	}
	
	public static void main(String[] args) {
		normal();
		dbcp();
	}
}
