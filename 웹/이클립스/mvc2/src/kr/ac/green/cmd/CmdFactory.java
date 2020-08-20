package kr.ac.green.cmd;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public class CmdFactory {
	public static Hashtable<String, ICmd> cmds;
	public static void init() {
		cmds = new Hashtable<String, ICmd>();
		
		cmds.put("getAll", new GetAllCmd());
		cmds.put("insertGo", new NullCmd());
		cmds.put("insertCar", new InsertCarCmd());
		cmds.put("deleteCar", new DeleteCarCmd());
	}
	
	public static void doAction(HttpServletRequest request, String cmd) {
		cmds.get(cmd).action(request);
	}
}
