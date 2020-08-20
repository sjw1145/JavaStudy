package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.contoller.StudentDao;
import kr.ac.green.dto.Student;

public class ShowStudentCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		StudentDao dao = StudentDao.getInstance();

		Connection con = dao.connect();

		int student_id = Integer.parseInt(request.getParameter("searchData"));
		String work = request.getParameter("work");

		Student student = dao.showStudent(con, student_id);
		System.out.println(work);

		dao.disconnect(con);

		if (student != null && work != null) {
			request.setAttribute("student", student);
			request.setAttribute("nextPage", "deleteForm.jsp");
		} else {
			if (student != null) {
				request.setAttribute("student", student);
				request.setAttribute("nextPage", "modifyForm.jsp");
			} else {
				request.setAttribute("msg", "존재하지 않습니다.");
				request.setAttribute("nextPage", "wait.jsp");
			}
		}
	}

}
