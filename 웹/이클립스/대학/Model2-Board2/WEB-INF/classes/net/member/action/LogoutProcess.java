package net.member.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import net.board.action.Action;
import net.board.action.ActionForward;

public class LogoutProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();	
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			session.removeAttribute("userId");
			System.out.println("技记秦力己傍");
			forward.setRedirect(false);
			forward.setPath("LoginForm.do");
			return forward;
		}
		return null;
	}
}
