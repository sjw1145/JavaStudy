package kr.ac.green.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.green.cmd.CmdFactory;
import kr.ac.green.cmd.ICmd;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		CmdFactory.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		todo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc_kr");
		todo(request, response);
	}

	private void todo(HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("cmd");

		if (cmd == null) {
			cmd = "getAll";
		}

		CmdFactory.doAction(request, cmd);

		String nextPage = (String) request.getAttribute("nextPage");
		System.out.println(nextPage);
		if (request.getAttribute("isRedirect") != null) {
			try {
				response.sendRedirect(nextPage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
