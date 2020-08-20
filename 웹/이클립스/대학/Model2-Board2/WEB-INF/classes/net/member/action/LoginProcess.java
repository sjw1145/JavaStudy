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

		// ���̵� ��й�ȣ �����´�.
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		// ������ ���̵� ��й�ȣ�� DAO �� �����ؼ�
		try {
			if (boarddao.loginCheck(id, passwd)) {
				System.out.println("�α��μ�����");
				forward.setRedirect(true);
				forward.setPath("./BoardList.bo");
				// ������ �ο���
				HttpSession session = request.getSession();
				session.setAttribute("userId", id);
				System.out.println("���Ǻο�����");
				return forward;
			} else {
				System.out.println("�α��ν�����");
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
