package net.member.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardDAO;
import net.board.db.MemberBean;

public class AdminProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();
		//ArrayList<String> memberList = null;
		List memberList = null;
		// 데이터를 가져옵니다.
		memberList = boarddao.getAllUserInfo();
		// 리퀘스트 스코프에 값을 저장
		request.setAttribute("memberList", memberList);
		forward.setRedirect(false);
		forward.setPath("./member/Member_list.jsp");
		return forward;
	}
}
