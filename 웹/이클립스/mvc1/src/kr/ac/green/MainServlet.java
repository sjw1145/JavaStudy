package kr.ac.green;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doit(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc_kr");
		doit(request, response);
	}
	private void doit(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = false;
		String nextPage = "list.jsp";
		
		String cmd = request.getParameter("cmd");
		if(cmd == null) {
			cmd = "getAll";
		}
		
		if(cmd.equals("getAll")) {
			MainDao mainDao = MainDao.getInstance();
			Connection conn = MainDao.connect();
			
			Car[] list = mainDao.getAll(conn);
			mainDao.close(conn);
			
			request.setAttribute("list", list);
		} else if(cmd.equals("goInsert")) {
			nextPage = "insert.jsp";
		} else if(cmd.equals("insertCar")) {
			MainDao mainDao = MainDao.getInstance();
			Connection conn = MainDao.connect();
			
			Car car = new Car();
			car.setCar_model(request.getParameter("car_model"));
			car.setCar_price(Integer.parseInt(request.getParameter("car_price")));
			car.setCar_desc(request.getParameter("car_desc"));
			
			int result = mainDao.insertCar(conn, car);
			
			mainDao.close(conn);
			isRedirect = true;
		}
		
		if(isRedirect) {
			try {
				response.sendRedirect(request.getContextPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher(nextPage);
			try {
				rs.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
