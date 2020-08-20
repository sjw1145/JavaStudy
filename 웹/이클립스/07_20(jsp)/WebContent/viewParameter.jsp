<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Map"%>
<%
	// 파라미터의 인코딩 설정 (POST 방식일 때만 된다.)
	// 반드시 파라미터에 접근하기 전에 호출해야 한다.
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<b>request.getParameter() 메서드 사용</b>
	<br> name 파라미터 : <%= request.getParameter("name") %><br> 
	address 파라미터 =<%= request.getParameter("address") %>
	<p>
		<b>request.getParameterValues() 메서드 사용</b><br>
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
		<b>request.getParameterNames() 메서드 사용</b><br>
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
		<b>request.getparameterMap() 사용</b>
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