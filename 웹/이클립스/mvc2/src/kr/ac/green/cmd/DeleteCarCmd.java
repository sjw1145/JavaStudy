package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.control.CarDao;

public class DeleteCarCmd implements ICmd{

	@Override
	public void action(HttpServletRequest request) {
		CarDao dao = CarDao.getInstance();
		
		Connection con = dao.connect();
		
		dao.deleteCar(con);
		
		dao.disconnect(con);
		
		request.setAttribute("isRedirect", true);
		request.setAttribute("nextPage", request.getContextPath());
	}
	
}
