<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>join.jsp</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
	<script>
		function joinProcess() {
			var joinForm = document.joinForm;
			
			joinForm.action = "joinProcess.jsp";
			joinForm.submit();
		}
	</script>
</head>
<body>	
	<p class="titleStr">
		Join Member
	</p>
	<form name="joinForm" method="post">
		<div class="centerBox">
			<label for="u_id">ID :</label> <input type="text" name="u_id" /><br/>
			<label for="u_pw">PW :</label> <input type="password" name="u_pw" /><br/>
			<label for="u_re">Retry :</label> <input type="password" name="u_re" /><br/>
			<label for="u_name">Name :</label> <input type="text" name="u_name" /><br/>
			<label for="u_nick">Nick :</label> <input type="text" name="u_nick" /><br/>				
			<div class="btns">
				<input type="button" value="ȸ������" onclick="joinProcess()" />
				<input type="button" value="���" onclick="history.go(-1)" />		
			</div>
		</div>
		<div class="msgBox" id="msg">
			put message, here
		</div>	
	</form>	
</body>
</html>