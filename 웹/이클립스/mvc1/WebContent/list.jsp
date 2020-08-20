<%@page import="java.io.UnsupportedEncodingException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>list.jsp</title>
</head>
<body>
	<a href="MainServlet?cmd=goInsert">insert car</a>
	<br/>
	<hr/>
	<table>
		<tr>
			<th>ID</th>
			<th>MODEL</th>
			<th>PRICE</th>
			<th>DESC</th>
		</tr>
		<%
			Car[] list = (Car[])request.getAttribute("list");
			if(list.length == 0) {
		%>
			<tr>
				<td colspan="4">empty</td>
			</tr>
		<%
			} else {
				for(Car temp : list) {
		%>
				<tr>
					<td><%= temp.getCar_id() %></td>
					<td><%= temp.getCar_model() %></td>
					<td><%= temp.getCar_price() %></td>
					<td><%= temp.getCar_desc() %></td>
				</tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>