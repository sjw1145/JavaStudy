package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardDAO;
import net.board.db.MemberBean;

public class MemberInfoProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();
		String userId = request.getParameter("id");
		try {
			MemberBean userInfo = new MemberBean();	
			userInfo = boarddao.getUserInfo(userId);
			System.out.println("사용자정보가져오는거");
			request.setAttribute("userInfo", userInfo);
			forward.setRedirect(false);
			forward.setPath("./member/Member_info.jsp");		
			return forward;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
