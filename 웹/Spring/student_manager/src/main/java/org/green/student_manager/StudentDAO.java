package org.green.student_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;


@Repository
public class StudentDAO implements IStudentDAO{
	public StudentDAO() {
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
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test",
					"root", 
					"1234");
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
	
	@Override
	public Student[] getAll(Connection con) {
		Student[] list = null;
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		String sql = "SELECT * FROM student ORDER BY student_id DESC";
		
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			if(rs.next()) {
				rs.last();
				list = new Student[rs.getRow()];
				rs.beforeFirst();
				
				int idx = 0;
				while(rs.next()) {
					int student_id = rs.getInt(1);
					String student_name = rs.getString(2);
					String student_tel = rs.getString(3);
					int student_grade = rs.getInt(4);
					String student_class = rs.getString(5);
					
					list[idx++] = new Student(
							student_id, 
							student_name, 
							student_tel, 
							student_grade, 
							student_class);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(rs);
			disconnect(pStmt);
		}
		
		return list;
	}

	public int doInsert(Connection con, Student student) {
		int result = 0;
		String sql = "INSERT INTO student(student_name, student_tel, student_grade, student_class) VALUES (?,?,?,?)";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, student.getStudent_name());
			pStmt.setString(2, student.getStudent_tel());
			pStmt.setInt(3, student.getStudent_grade());
			pStmt.setString(4, student.getStudent_class());
			
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}

	public Student doSearch(Connection con, int searchData) {
		String sql = "SELECT * FROM student WHERE student_id = ?";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, searchData);
			rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int student_id = rs.getInt(1);
				String student_name = rs.getString(2);
				String student_tel = rs.getString(3);
				int student_grade = rs.getInt(4);
				String student_class = rs.getString(5);
				
				student = new Student(student_id, student_name, student_tel, student_grade, student_class);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(rs);
			disconnect(pStmt);
		}
		
		return student;
	}
	
	@Override
	public Student doSearch(Connection con, String searchData, String work) {
		Student student = null;
		String sql = "SELECT * FROM student WHERE student_name = ?";
		if(work.equals("class")) {
			sql = "SELECT * FROM student WHERE student_class = ?";
		}
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, searchData);
			rs = pStmt.executeQuery();
			
			if(rs.next()) {
				int student_id = rs.getInt(1);
				String student_name = rs.getString(2);
				String student_tel = rs.getString(3);
				int student_grade = rs.getInt(4);
				String student_class = rs.getString(5);
				
				student = new Student(student_id, student_name, student_tel, student_grade, student_class);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}

	public int doDelete(Connection con, Student student) {
		int result = 0;
		String sql = "DELETE FROM student WHERE student_id = ?";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, student.getStudent_id());
			result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}

	public int doModify(Connection con, Student student) {
		int result = 0;
		String sql = "UPDATE student SET student_name=?, student_tel=?, student_grade=?, student_class=? WHERE student_id=?";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, student.getStudent_name());
			pStmt.setString(2, student.getStudent_tel());
			pStmt.setInt(3, student.getStudent_grade());
			pStmt.setString(4, student.getStudent_class());
			pStmt.setInt(5, student.getStudent_id());
			result = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(pStmt);
		}
		
		return result;
	}
}
