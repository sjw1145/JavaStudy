package kr.ac.green.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.control.CarDao;
import kr.ac.green.dto.Car;

public class InsertCarCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		CarDao dao = CarDao.getInstance();

		Connection con = dao.connect();

		String car_model = request.getParameter("car_model");
		int car_price = Integer.parseInt(request.getParameter("car_price"));
		String car_desc = request.getParameter("car_desc");

		dao.insertCar(con, new Car(car_model, car_price, car_desc));

		dao.disconnect(con);

		request.setAttribute("nextPage", request.getContextPath());
		request.setAttribute("isRedirect", true);
	}

}
