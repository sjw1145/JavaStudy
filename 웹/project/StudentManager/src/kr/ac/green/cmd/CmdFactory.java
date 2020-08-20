package kr.ac.green.cmd;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public class CmdFactory {
	private static Hashtable<String, ICmd> cmds;
	
	public static void init() {
		cmds = new Hashtable<>();
		
		cmds.put("getAll", new GetAllCmd());
		cmds.put("insertStudent", new InsertStudentCmd());
		cmds.put("deleteStudent", new DeleteStudentCmd());
		cmds.put("updateStudent", new UpdateStudentCmd());
		cmds.put("searchStudent", new SearchStudentCmd());
		cmds.put("goInsert", new NullCmd());
		cmds.put("goModify", new NullCmd());
		cmds.put("goDelete", new NullCmd());
		cmds.put("showStudent", new ShowStudentCmd());
		
		
	}
	
	public static void doAction(HttpServletRequest request, String cmd) {
		cmds.get(cmd).action(request);
	}
}
