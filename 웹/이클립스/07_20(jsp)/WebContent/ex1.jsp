<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%!
		// 멤버변수 , 메서드 정의
		public String getName() {
			return "백다원";
		}
	%>

	<table>
		<%
			int i = 0;
			for (i = 0; i <= 10; i++) {
		%>
				<tr>
					<td><%=i%></td><td><%=getName()%></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>