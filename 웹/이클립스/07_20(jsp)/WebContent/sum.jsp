<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		int sum = 0;
		for(int i = 0; i <= 10; i++) {
			sum += i;
		}
		
	%>
	
	1부터 10까지의 합은 <%= sum %> 입니다
	<br>
	
	<%
		int sum2 = 0;
	for(int i = 11; i <= 20; i++) {
		sum2 += i;
	}
	%>
	11부터 20까지의 합은 <%= sum2 %> 입니다
</body>
</html>