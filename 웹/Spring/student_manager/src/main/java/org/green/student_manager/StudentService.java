package org.green.student_manager;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private IStudentDAO dao;

	public Student[] getAll() {
		Connection con = dao.connect();
		Student[] list = dao.getAll(con);
		dao.disconnect(con);

		return list;
	}

	public int doInsert(Student student) {
		Connection con = dao.connect();
		int result = dao.doInsert(con, student);
		dao.disconnect(con);

		return result;
	}

	public Student doSearch(int searchData) {
		Connection con = dao.connect();
		Student student = dao.doSearch(con, searchData);
		dao.disconnect(con);

		return student;
	}
	
	public Student doSearch(String searchData, String work) {
		Connection con = dao.connect();
		Student student = dao.doSearch(con, searchData, work);
		dao.disconnect(con);
		
		return student;
	}
	
	public int doDelete(Student student) {
		Connection con = dao.connect();
		int result = dao.doDelete(con, student);
		dao.disconnect(con);
		
		return result;
	}
	
	public int doModify(Student student) {
		Connection con = dao.connect();
		int result = dao.doModify(con, student);
		dao.disconnect(con);
		
		return result;
	}

}
