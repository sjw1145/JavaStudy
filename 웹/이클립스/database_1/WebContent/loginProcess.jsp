<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.sql.*" %>
<%
	request.setCharacterEncoding("euc-kr");

	String user_id = request.getParameter("u_id");
	String user_pw = request.getParameter("u_pw");
	
	// �α��� ó��
	// ����̹� �ε�
	Class.forName("com.mysql.jdbc.Driver");
	
	// Ŀ�ؼ� ��ü ����
	Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/test" ,
		"root",
		"1234"
	);
	
	// ����
	String query = "SELECT * FROM member WHERE user_id='%s' AND user_pw='%s';";
	query = String.format(query, user_id, user_pw);
	System.out.println(query);
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	out.println(query);
	
	if(rs.next() == false) {
%>
		<script>
			alert("����ڰ� �������� �ʽ��ϴ�.");
			history.go(-1);
		</script>
<%
	} else {
		// ����ڰ� ������ ���
		session.setAttribute("user_id", user_id);
		response.sendRedirect("loginSuccess.jsp");
	}
	
	stmt.close();
	con.close();
%>