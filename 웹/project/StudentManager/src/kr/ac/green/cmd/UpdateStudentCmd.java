package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.contoller.StudentDao;
import kr.ac.green.dto.Student;

public class UpdateStudentCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		StudentDao dao = StudentDao.getInstance();
		Connection con = dao.connect();
		
		int student_id = Integer.parseInt(request.getParameter("student_id"));
		String student_name = request.getParameter("student_name");
		String student_tel = request.getParameter("student_tel");
		int student_grade = Integer.parseInt(request.getParameter("student_grade"));
		String student_class = request.getParameter("student_class");

		Student student = new Student(student_id, student_name, student_tel, student_grade, student_class);
		
		dao.modifyStudent(con, student);
		
		dao.disconnect(con);
		
		request.setAttribute("nextPage", request.getContextPath());
		request.setAttribute("isRedirect", true);
	}

}
