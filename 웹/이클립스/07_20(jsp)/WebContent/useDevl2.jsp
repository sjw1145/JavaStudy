<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
	public int add(int a, int b) {
		return a+b;
	}

	public int subtract(int a, int b) {
		return a-b;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int value1 = 3;
	int value2 = 9;
	
	int addResult = add(value1, value2);
	int subtractResult = subtract(value1, value2);
%>

<%= value1 %> + <%= value2 %> = <%= addResult %>
<br>
<%= value1 %> - <%= value2 %> = <%= subtractResult %>

</body>
</html>