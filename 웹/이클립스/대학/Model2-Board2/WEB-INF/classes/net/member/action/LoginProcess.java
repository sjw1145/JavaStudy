package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class LoginProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();

		// 아이디 비밀번호 가져온다.
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		// 가져온 아이디 비밀번호를 DAO 에 연결해서
		try {
			if (boarddao.loginCheck(id, passwd)) {
				System.out.println("로그인성공함");
				forward.setRedirect(true);
				forward.setPath("./BoardList.bo");
				// 세션을 부여함
				HttpSession session = request.getSession();
				session.setAttribute("userId", id);
				System.out.println("세션부여성공");
				return forward;
			} else {
				System.out.println("로그인실패함");
				forward.setRedirect(false);
				forward.setPath("LoginForm.do");
				return forward;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
