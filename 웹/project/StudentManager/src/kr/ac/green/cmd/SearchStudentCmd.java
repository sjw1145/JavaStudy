package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.contoller.StudentDao;
import kr.ac.green.dto.Student;

public class SearchStudentCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		StudentDao dao = StudentDao.getInstance();
		Connection con = dao.connect();
		
		String search = request.getParameter("search");
		String searchData = request.getParameter("searchData");
		
		
		System.out.println(search);
		System.out.println(searchData);
		
		Student[] student = dao.searchStudent(con, search, searchData);
		
		System.out.println("±Ê¿Ã " + student.length);
		
		dao.disconnect(con);
		
		request.setAttribute("list", student);
		request.setAttribute("nextPage", "list.jsp");
		
	}

}
