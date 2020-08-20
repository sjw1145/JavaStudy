package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

public class BoardReplyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("euc-kr");
		 	ActionForward forward = new ActionForward();
		 	
			BoardDAO boarddao=new BoardDAO();
	   		BoardBean boarddata=new BoardBean();
	   		int result=0;
	   		
	   		HttpSession session = request.getSession(); 
   			String str = (String)session.getAttribute("userId");
	   		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
	   		boarddata.setBOARD_NAME(str);
	   		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
	   		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	   		boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
	   		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	   		
	   		result=boarddao.boardReply(boarddata);
	   		if(result==0){
	   			System.out.println("���� ����");
	   			return null;
	   		}
	   		System.out.println("���� �Ϸ�");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardDetailAction.bo?num="+result);
	   		return forward;
	}  	
}