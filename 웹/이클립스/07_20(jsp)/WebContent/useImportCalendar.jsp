<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Calendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Calendar cal = Calendar.getInstance();
	%>
	
	Today
	<%= cal.get(Calendar.YEAR) %>��
	<%= cal.get(Calendar.MONTH) + 1 %>��
	<%= cal.get(Calendar.DATE) %>��
	�Դϴ�
</body>
</html>