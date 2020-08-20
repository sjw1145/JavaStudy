<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	request.setCharacterEncoding("euc_kr");

	String state = (String)request.getAttribute("state");
	
	if(state != null) {
%>
		<script>
			alert('회원가입 성공!');
		</script>
<%
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>LoginForm</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script>
		function loginProcess() {
			var loginForm = document.loginForm;
			
			loginForm.action = "MainServlet?cmd=loginProcess";
			loginForm.submit();
		}
		
		function goJoinForm() {
			location.href="MainServlet?cmd=goJoinForm";
		}
	</script>
</head>
<body>
	<p class="titleStr">
		Login
	</p>
	<form name="loginForm" method="post">
		<div class="centerBox">
			<label for="u_id">ID :</label> <input type="text" name="u_id" /><br/>
			<label for="u_pw">PW :</label> <input type="password" name="u_pw" /><br/>
			<div class="btns">
				<input type="button" value="로그인" onclick="loginProcess()"/>
				<input type="button" value="회원가입" onclick="goJoinForm()"/>
			</div>
		</div>
		<div class="msgBox">
			put message, here
		</div>		
	</form>
</body>
</html>