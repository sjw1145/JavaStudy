<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.dto.Student"%>
<%
	Student[] list = (Student[]) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
<style>
	.table {
		text-align:center;
	}
	
	th {
		text-align:center;
	}
</style>
</head>
<body >
	<div class="container">
		<hr/>
		<table class="table table-hover">
			<h2 class="bg-primary">Student List</h2>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Tel</th>
					<th>Grade</th>
					<th>Class</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (list.length == 0) {
				%>
				<tr>
					<td colspan="5">
						<div class="alert alert-danger">
  							<strong>알림!</strong> 데이터가 존재하지 않습니다.
						</div>
					
					</td>
				</tr>
				<%
					} else {
						for (int i = 0; i < list.length; i++) {
							Student temp = list[i];
				%>
				<tr>
					<td><%=temp.getStudent_id()%></td>
					<td><%=temp.getStudent_name()%></td>
					<td><%=temp.getStudent_tel()%></td>
					<td><%=temp.getStudent_grade()%></td>
					<td><%=temp.getStudent_class()%></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>


			<tfoot>
				<tr>
					<td>ROW : <%=list.length%></td>
				</tr>
			</tfoot>

		</table>
	</div>
</body>
</html>