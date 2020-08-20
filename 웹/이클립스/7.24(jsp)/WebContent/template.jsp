<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>template.jsp</title>
<%
	String contents = request.getParameter("contents");
	String contentsPage = "contentsA";
	if(contents != null) {
		contentsPage = contents; 
	}
	
	contentsPage += ".jsp";
%>
</head>
<body>
	<table border="1" width="80%" align="center">
		<tr>
			<!-- logo.jsp  -->
			<td colspan="2">
				<jsp:include page="logo.jsp" />
			</td>
				
		</tr>
		<tr>
			<td width="30%">
				<!-- menu.jsp -->
				<jsp:include page="menu.jsp" />
			</td>
			<td>
				<!-- contents.jsp -->
				<jsp:include page="<%= contentsPage %>" />
			</td>
		</tr>
		<tr>
		</tr>
	</table>
</body>
</html>