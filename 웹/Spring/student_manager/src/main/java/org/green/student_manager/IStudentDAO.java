package org.green.student_manager;

import java.sql.Connection;

public interface IStudentDAO {
	Connection connect();
	void disconnect(Connection con);
	
	Student[] getAll(Connection con);
	int doInsert(Connection con, Student student);
	Student doSearch(Connection con, int searchData);
	Student doSearch(Connection con, String searchData, String work);
	int doDelete(Connection con, Student student);
	int doModify(Connection con, Student student);
}
