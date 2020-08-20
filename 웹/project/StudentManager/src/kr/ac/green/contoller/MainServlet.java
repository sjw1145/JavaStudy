package kr.ac.green.contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.green.cmd.CmdFactory;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		CmdFactory.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc_kr");
		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		
		if (cmd == null) {
			cmd = "getAll";
		}
		
		CmdFactory.doAction(request, cmd);

		String nextPage = (String) request.getAttribute("nextPage");
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
