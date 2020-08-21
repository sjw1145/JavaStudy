<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	text-align: center;
}

th {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<hr />
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
				<c:choose>
					<c:when test="${ list eq null}">
						<tr>
							<td colspan="5">
								<div class="alert alert-danger">
									<strong>알림!</strong> 데이터가 존재하지 않습니다.
								</div>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="listItem" items="${ list }">
							<tr>
								<td>${ listItem.student_id }</td>
								<td>${ listItem.student_name }</td>
								<td>${ listItem.student_tel }</td>
								<td>${ listItem.student_grade }</td>
								<td>${ listItem.student_class }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>