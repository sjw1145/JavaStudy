<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.dto.Car" %>	
<%
	Car[] list = (Car[])request.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list.jsp</title>
</head>
<body>
	<a href="MainServlet?cmd=insertGo">insert car</a>

	<form action="MainServlet" method="post">
		<input type="hidden" name="cmd" value="deleteCar" />
		<input type="submit" value="clear" />
	</form>
	<hr>
	<table>
		<tr>
			<th>ID</th>
			<th>Model</th>
			<th>Price</th>
			<th>Description</th>
		</tr>
		
		<%
			if(list.length == 0) {
		%>
			<tr>
				<td colspan="4"/>empty</td>
			</tr>
		<%
			} else {
				
				for(Car temp : list) {
		%>
				<tr>
					<td><%= temp.getCar_id() %></td>
					<td><%= temp.getCar_model() %></td>
					<td><%= temp.getPrice() %></td>
					<td><%= temp.getCar_desc() %></td>
				</tr>
		
		<%
				}
			}
		%>
	</table>
</body>
</html>