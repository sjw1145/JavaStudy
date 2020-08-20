package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardDAO;
import net.board.db.MemberBean;

public class MemberDeleteProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();

		try {
			String userId = request.getParameter("id");

			if (boarddao.deleteMember(userId)) {
				forward.setRedirect(true);
				forward.setPath("./AdminProcess.do");

			}
			return forward;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
}
