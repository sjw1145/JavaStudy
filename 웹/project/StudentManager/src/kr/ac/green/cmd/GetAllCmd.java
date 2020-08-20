package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.contoller.StudentDao;
import kr.ac.green.dto.Student;

public class GetAllCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		StudentDao dao = StudentDao.getInstance();

		Connection con = dao.connect();

		Student[] list = dao.getAll(con);

		dao.disconnect(con);
		System.out.println("여기 요청했음 !!");
		request.setAttribute("list", list);
		request.setAttribute("nextPage", "list.jsp");
	}
}