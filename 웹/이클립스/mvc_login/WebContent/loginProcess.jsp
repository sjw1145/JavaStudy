<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.sql.*" %>
<%
	request.setCharacterEncoding("euc-kr");

	String user_id = request.getParameter("u_id");
	String user_pw = request.getParameter("u_pw");
	
	// 로그인 처리
	// 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	// 커넥션 객체 얻음
	Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/test" ,
		"root",
		"1234"
	);
	
	// 질의
	String query = "SELECT * FROM member WHERE user_id='%s' AND user_pw='%s';";
	query = String.format(query, user_id, user_pw);
	System.out.println(query);
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	out.println(query);
	
	if(rs.next() == false) {
%>
		<script>
			alert("사용자가 존재하지 않습니다.");
			history.go(-1);
		</script>
<%
	} else {
		// 사용자가 존재한 경우
		session.setAttribute("user_id", user_id);
		response.sendRedirect("loginSuccess.jsp");
	}
	
	stmt.close();
	con.close();
%>