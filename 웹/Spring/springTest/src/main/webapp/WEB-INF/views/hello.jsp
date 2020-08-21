<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hello.jsp</title>
</head>
<body>
	<a href="a/b/what">pathVariable</a>
	<a href="empty">go Empty</a>
	<hr>
	<form action="userInfo" method="post">
		name : <input type="text" name="userName" />
		<br>
		age : <input type="text" name="userAge" />
		<br>
		<input type="submit" />
	</form>
	<hr>
	<form action="formData" method="post">
		input : <input type="text" name="inputValue" />
		<input type="submit" />
	</form>
	<hr>
	<a href="callTodo">님들 잘됨?</a>
	<hr>
	${ some }
</body>
</html>