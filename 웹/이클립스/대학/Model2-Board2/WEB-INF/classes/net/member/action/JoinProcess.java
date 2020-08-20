package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardDAO;
import net.board.db.MemberBean;

public class JoinProcess implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		MemberBean member = new MemberBean();
		ActionForward forward = new ActionForward();

		try {
			// 회원가입 때 입력 했던 부분을 다 가져온다.
			member.setId(request.getParameter("id"));
			member.setPasswd(request.getParameter("passwd"));
			member.setEmail(request.getParameter("email"));
			member.setNames(request.getParameter("names"));
			member.setManNumber(request.getParameter("manNumber"));
			member.setHobby1(request.getParameter("hobby1"));
			member.setHobby2(request.getParameter("hobby2"));
			member.setHobby3(request.getParameter("hobby3"));
			member.setHobby4(request.getParameter("hobby4"));
			member.setHobby5(request.getParameter("hobby5"));
			member.setMyView(request.getParameter("myView"));
			// 디비에 빈즈 데이터를 넘겨서 등록처리함
			if (boarddao.joinAction(member)) {
				System.out.println("회원등록성공");
				forward.setRedirect(true);
				forward.setPath("LoginForm.do");
				return forward;
			} else {
				System.out.println("회원등록실패");
				forward.setRedirect(true);
				forward.setPath("JoinForm.do");
				return forward;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
