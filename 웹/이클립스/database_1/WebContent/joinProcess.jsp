<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.*"%>
<%@ page import="java.sql.*"%>
<%
	request.setCharacterEncoding("euc-kr");

	String user_id = request.getParameter("u_id");
	String user_pw = request.getParameter("u_pw");
	String user_name = request.getParameter("u_name");
	String user_nick = request.getParameter("u_nick");
	
	System.out.println(user_id);
	
	Manager.connection();

	String sql = "SELECT * FROM member WHERE user_id='%s';";
	sql = String.format(sql, user_id);
	System.out.println(sql);
	
	ResultSet rs = Manager.search(sql);
	
	if(rs == null) {
		Manager.connection();
		
		String query = "INSERT INTO member(user_id, user_pw, user_name, user_nick) VALUES('%s', '%s', '%s', '%s');";
		query = String.format(query, user_id, user_pw, 
				user_name, user_nick);
		
		int result = Manager.query(query);
		if(result == 0) {
			out.print("<script>alert('회원가입 실패')</script>");
		}
	} else {
		System.out.println("존재하는 아이디");
	}
	
	response.sendRedirect("loginForm.jsp");
%>

