package kr.ac.green.contoller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.green.dto.Student;

public class StudentDao {
	private static StudentDao instance = new StudentDao();

	private StudentDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static StudentDao getInstance() {
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

	private String toEn(String kor) {
		String en = null;

		try {
			en = new String(kor.getBytes("8859_1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return en;
	}

	private String toKor(String en) {
		String kor = null;

		try {
			kor = new String(en.getBytes("euc_kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return kor;
	}

	// 모든 정보 가져오기
	public Student[] getAll(Connection con) {
		Student[] list = null;
		String sql = "SELECT * FROM student ORDER BY student_id DESC";
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();

			rs.last();
			list = new Student[rs.getRow()];
			rs.beforeFirst();

			int idx = 0;
			while (rs.next()) {
				int student_id = rs.getInt("student_id");
				String student_name = toEn(rs.getString("student_name"));
				String student_tel = toEn(rs.getString("student_tel"));
				int student_grade = Integer.parseInt(rs.getString("student_grade"));
				String student_class = toEn(rs.getString("student_class"));

				list[idx++] = new Student(student_id, student_name, student_tel, student_grade, student_class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			try {
				pStmt.clearParameters();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pStmt);
		}

		return list;
	}

	// 등록
	public int insertStudent(Connection con, Student student) {
		int result = 0;
		String sql = "INSERT INTO student(student_name, student_tel, student_grade, student_class) VALUES(?,?,?,?)";
		PreparedStatement pStmt = null;
		try {
			pStmt = con.prepareStatement(sql);

			pStmt.setString(1, toKor(student.getStudent_name()));
			pStmt.setString(2, toKor(student.getStudent_tel()));
			pStmt.setInt(3, student.getStudent_grade());
			pStmt.setString(4, toKor(student.getStudent_class()));

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.clearParameters();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pStmt);
		}

		return result;
	}

	// 수정
	public int modifyStudent(Connection con, Student student) {
		int result = 0;
		String sql = "UPDATE student SET student_tel=?, student_grade=?, student_class=? WHERE student_id=?";
		PreparedStatement pStmt = null;

		try {
			pStmt = con.prepareStatement(sql);

			pStmt.setString(1, toKor(student.getStudent_tel()));
			pStmt.setInt(2, student.getStudent_grade());
			pStmt.setString(3, toKor(student.getStudent_class()));
			pStmt.setInt(4, student.getStudent_id());

			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.clearParameters();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pStmt);
		}

		return result;
	}

	// 삭제
	public int deleteStudent(Connection con, int student_id) {
		int result = 0;
		String sql = "DELETE FROM student WHERE student_id=?";
		PreparedStatement pStmt = null;

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, student_id);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.clearParameters();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pStmt);
		}

		return result;
	}

	// 검색(int : id, String : name, String : class)
	public Student[] searchStudent(Connection con, String search, String searchData) {
		ResultSet rs = null;
		Student[] list = null;
		PreparedStatement pStmt = null;
		String sql = null;

		try {
			System.out.println("서치 "+ searchData);
			if (search.equals("id")) {
				sql = "SELECT * FROM student WHERE student_id=?";
				pStmt = con.prepareStatement(sql);
				int student_id = Integer.parseInt(searchData);
				pStmt.setInt(1, student_id);

			} else if (search.equals("name")) {
				sql = "SELECT * FROM student WHERE student_name=?";
				pStmt = con.prepareStatement(sql);
				pStmt.setString(1, toKor(searchData));

			} else {
				sql = "SELECT * FROM student WHERE student_class=?";
				pStmt = con.prepareStatement(sql);
				pStmt.setString(1, toKor(searchData));

			}

			rs = pStmt.executeQuery();

			rs.last();
			list = new Student[rs.getRow()];
			rs.beforeFirst();

			int idx = 0;
			while (rs.next()) {
				list[idx++] = new Student(rs.getInt("student_id"), toEn(rs.getString("student_name")),
						toEn(rs.getString("student_tel")), rs.getInt("student_grade"),
						toEn(rs.getString("student_class")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			try {
				pStmt.clearParameters();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(pStmt);
		}

		return list;
	}

	public Student showStudent(Connection con, int student_id) {
		Student student = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		System.out.println(student_id);
		String sql = "SELECT * FROM student WHERE student_id=?";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, student_id);

			rs = pStmt.executeQuery();

			if (rs.next()) {
				student = new Student(rs.getInt("student_id"), toEn(rs.getString("student_name")),
						toEn(rs.getString("student_tel")), rs.getInt("student_grade"),
						toEn(rs.getString("student_class")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

}
