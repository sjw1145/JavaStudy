<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		String str = request.getParameter("userInput");
		str = new String(str.getBytes("8859_1"), "euc_kr");
	%>

	<%= str %>
</body>
</html>