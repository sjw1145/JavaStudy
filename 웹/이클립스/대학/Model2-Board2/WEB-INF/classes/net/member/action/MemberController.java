package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import net.board.action.Action;
import net.board.action.ActionForward;

public class MemberController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/LoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
			System.out.println("들어온다");
		} else if (command.equals("/JoinForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/joinForm.jsp");
		} else if (command.equals("/LoginAction.do")) {
			action = new LoginProcess();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/JoinProcess.do")) {
			action = new JoinProcess();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/LogoutProcess.do")) {
			action = new LogoutProcess();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/AdminProcess.do")) {
			action = new AdminProcess();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberInfoProcess.do")) {
			action = new MemberInfoProcess();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDeleteProcess.do")) {
			action = new MemberDeleteProcess();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
