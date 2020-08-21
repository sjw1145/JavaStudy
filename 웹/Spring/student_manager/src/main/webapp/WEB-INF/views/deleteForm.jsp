<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>삭제</h1>
	<form action="doDelete" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" size="14" name="student_id"
					readOnly="readonly" value="${ student.student_id }" /></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><input type="text" size="14" name="student_name"
					readOnly="readonly" value="${ student.student_name }" /></td>
			</tr>
			<tr>
				<td>Tel</td>
				<td><input type="text" size="14" name="student_tel"
					readOnly="readonly" value="${ student.student_tel }" /></td>
			</tr>
			<tr>
				<td>Grade</td>
				<td><input type="text" size="14" name="student_grade"
					readOnly="readonly" value="${ student.student_grade }" /></td>
			</tr>
			<tr>
				<td>Class</td>
				<td><input type="text" size="14" name="student_class"
					readOnly="readonly" value="${ student.student_class }" /></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="delete" /></td>
			</tr>

		</table>
	</form>
</body>
</html>