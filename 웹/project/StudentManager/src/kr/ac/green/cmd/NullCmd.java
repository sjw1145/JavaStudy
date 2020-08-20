package kr.ac.green.cmd;

import javax.servlet.http.HttpServletRequest;

public class NullCmd implements ICmd {
	@Override
	public void action(HttpServletRequest request) {
		String cmd = request.getParameter("cmd");
		if(cmd.equals("goInsert")) {
			request.setAttribute("nextPage", "insertForm.jsp");
		} else if (cmd.equals("goModify")) {
			request.setAttribute("nextPage", "modify.jsp");
		} else if (cmd.equals("goDelete")) {
			request.setAttribute("nextPage", "delete.jsp");
		}
	}
}
