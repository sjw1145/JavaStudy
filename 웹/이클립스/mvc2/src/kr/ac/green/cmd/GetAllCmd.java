package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.control.CarDao;
import kr.ac.green.dto.Car;

public class GetAllCmd implements ICmd {
	@Override
	public void action(HttpServletRequest request) {
		CarDao dao = CarDao.getInstance();

		Connection con = dao.connect();

		Car[] list = dao.getAll(con);

		dao.disconnect(con);

		request.setAttribute("list", list);
		request.setAttribute("nextPage", "list.jsp");
	}
}
