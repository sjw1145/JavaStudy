package kr.ac.green;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDao {
	public static final MemberDao instance = new MemberDao();

	private MemberDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MemberDao getInstance() {
		return instance;
	}

	// 연결
	public Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
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

	// euc_kr -> 8859_1
	private static String toEn(String kor) {
		String en = null;

		try {
			en = new String(kor.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return en;
	}

	// 8859_1 -> euc_kr
	private static String toKor(String en) {
		String kor = null;

		try {
			kor = new String(en.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return kor;
	}

	public int joinProcess(Connection con, Member member) {
		int result = 0;
		Statement stmt = null;
		String sql = "INSERT INTO member (user_id, user_pw, user_name, user_nick) " + "VALUES('%s', '%s', '%s', '%s')";
		sql = String.format(sql, member.getUser_id(), member.getUser_pw(), toEn(member.getUser_name()),
				toEn(member.getUser_nick()));

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

	public boolean searchUser(Connection con, String user_id) {
		boolean isCheck = false;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE user_id ='%s'";
		sql = String.format(sql, user_id);

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isCheck = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				close(rs);
			}
			if (stmt != null) {
				close(stmt);
			}
		}

		return isCheck;
	}

	public boolean loginProcess(Connection con, String user_id, String user_pw) {
		boolean isCheck = false;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE user_id='%s' AND user_pw='%s'";
		sql = String.format(sql, user_id, user_pw);

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isCheck = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				close(rs);
			}
			if (stmt != null) {
				close(stmt);
			}
		}

		return isCheck;
	}
}
