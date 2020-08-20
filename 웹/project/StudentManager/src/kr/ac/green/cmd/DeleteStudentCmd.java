package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.contoller.StudentDao;
import kr.ac.green.dto.Student;

public class DeleteStudentCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		StudentDao dao = StudentDao.getInstance();
		Connection con = dao.connect();
		
		int student_id = Integer.parseInt(request.getParameter("student_id"));
		dao.deleteStudent(con, student_id);
		
		dao.disconnect(con);
		
		request.setAttribute("nextPage", request.getContextPath());
		request.setAttribute("isRedirect", true);
	}

}
