package org.green.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class BasicDBCP {
	private BasicDataSource ds;
	
	public BasicDBCP() {
		ds = new BasicDataSource();
		
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("1234");
		ds.setInitialSize(10);
		ds.setMaxActive(10);
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
	}
	
	
	private int insert(String value) {
		String sql = "INSERT INTO dummy (dvalue) VALUES(?)";
		int result = 0;
		
		Connection con = null;
		PreparedStatement pStmt = null;
		try {
			con = ds.getConnection();
			pStmt = con.prepareStatement(sql);
			pStmt.setNString(1, value);
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.close();
			} catch(Exception e ) {}
			try {
				con.close();
			} catch(Exception e) {}
		}		
		return result;
	}
	public static void main(String[] args) {
		BasicDBCP dbcp  = new BasicDBCP();
		System.out.println(dbcp.insert("abc"));
	}
}







