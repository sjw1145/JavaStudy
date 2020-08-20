package org.green.phoneBook;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class PhoneBookMySQLDao implements IPhoneDAO {

	public PhoneBookMySQLDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection connect() {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(PreparedStatement pStmt) {
		try {
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String toKor(String en) {
		String kor = null;
		try {
			kor = new String(en.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return kor;
	}
	
	private static String toEn(String kor) {
		String en = null;
		try {
			en = new String(kor.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return en;
	}
	

	@Override
	public int deletePhone(Connection con, int phone_id) {
		int result = 0;
		String sql = "DELETE FROM phone_book WHERE phone_id=?";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, phone_id);
			
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}

	@Override
	public int insertPhone(Connection con, PhoneBook pb) {
		int result = 0;
		String sql = "INSERT INTO phone_book (name, nick, tel, gender) VALUES(?,?,?,?)";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, toKor(pb.getName()));
			pStmt.setString(2, toKor(pb.getNick()));
			pStmt.setString(3, toKor(pb.getTel()));
			pStmt.setString(4, toKor(pb.getGender()));
			
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}

	@Override
	public PhoneBook[] selectPhone(Connection con) {
		PhoneBook[] list = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		String sql = "SELECT * FROM phone_book";
		
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			rs.last();
			list = new PhoneBook[rs.getRow()];
			rs.beforeFirst();
			
			int idx = 0;
			while(rs.next()) {
				list[idx++] = new PhoneBook(
						rs.getInt(1), 
						toEn(rs.getString(2)),
						toEn(rs.getString(3)),
						toEn(rs.getString(4)),
						toEn(rs.getString(5))
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(rs);
			disconnect(pStmt);
		}
		
		return list;
	}

	@Override
	public int modifyPhone(Connection con, PhoneBook pb) {
		int result = 0;
		String sql = "UPDATE phone_book SET name=? , nick=? , tel=? ,gender=? WHERE phone_id=?";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, pb.getName());
			pStmt.setString(2, pb.getNick());
			pStmt.setString(3, pb.getTel());
			pStmt.setString(4, pb.getGender());
			pStmt.setInt(5, pb.getPhone_id());
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}

	public PhoneBook searchPhone(Connection con, int phone_id) {
		PhoneBook pb = null;
		String sql = "SELECT * FROM phone_book WHERE phone_id=?";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, phone_id);
			rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String nick = rs.getString("nick");
				String tel = rs.getString("tel");
				String gender = rs.getString("gender");
				
				pb = new PhoneBook(phone_id, name, nick, tel, gender);
				
				return pb;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(rs);
			disconnect(pStmt);
		}
		
		return pb;
	}
}
