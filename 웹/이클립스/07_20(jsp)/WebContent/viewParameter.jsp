<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Map"%>
<%
	// �Ķ������ ���ڵ� ���� (POST ����� ���� �ȴ�.)
	// �ݵ�� �Ķ���Ϳ� �����ϱ� ���� ȣ���ؾ� �Ѵ�.
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<b>request.getParameter() �޼��� ���</b>
	<br> name �Ķ���� : <%= request.getParameter("name") %><br> 
	address �Ķ���� =<%= request.getParameter("address") %>
	<p>
		<b>request.getParameterValues() �޼��� ���</b><br>
	<%
		String[] values = request.getParameterValues("pet");
		if(values != null) {
			for(int i = 0; i < values.length; i++) {
			
		%>
			<%= values[i] %>
		<% 
			}
		}
	%>
	
	<p>
		<b>request.getParameterNames() �޼��� ���</b><br>
	<%
		Enumeration paramEnum = request.getParameterNames();
		while(paramEnum.hasMoreElements()) {
			String name = (String) paramEnum.nextElement();		
		
	%>
			<%= name %>
	<%
		}
	%>
	
	<p>
		<b>request.getparameterMap() ���</b>
	<%
		Map parameterMap = request.getParameterMap();
		String[] nameParam = (String[]) parameterMap.get("name");
		if(nameParam != null) {
	%>
		name = <%= nameParam[0] %>
	<%
		}
	%>
	
</body>
</html>