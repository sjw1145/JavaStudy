package kr.ac.green;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		boolean isRedirect = false;
		String nextPage = "loginForm.jsp";

		String cmd = request.getParameter("cmd");
		if (cmd != null) {
			if (cmd.equals("goJoinForm")) {
				nextPage = "join.jsp";
			} else if (cmd.equals("joinProcess")) {
				MemberDao md = MemberDao.getInstance();

				Connection con = md.connect();

				if (md.searchUser(con, request.getParameter("u_id"))) {
					// �̹� �����ϴ� ���� !!
					nextPage = "join.jsp";
				} else {
					md.joinProcess(con, new Member(request.getParameter("u_id"), request.getParameter("u_pw"),
							request.getParameter("u_name"), request.getParameter("u_nick")));

					request.setAttribute("state", "ȸ������ ����!");

					isRedirect = true;
				}
				md.close(con);

			} else if (cmd.equals("loginProcess")) {
				String user_id = request.getParameter("u_id");
				String user_pw = request.getParameter("u_pw");

				MemberDao md = MemberDao.getInstance();

				Connection con = md.connect();

				if (md.loginProcess(con, user_id, user_pw)) {
					// ������ �����ϹǷ� ������ �ο��ϰ� �����̷�Ʈ
					HttpSession session = request.getSession();
					session.setAttribute("session", user_id);

					nextPage = "loginSuccess.jsp";
				} else {
					request.setAttribute("state", "�α��� ����");

					isRedirect = true;
				}

				md.close(con);
			}
		}

		if (isRedirect) {
			try {
				response.sendRedirect(request.getContextPath());
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
