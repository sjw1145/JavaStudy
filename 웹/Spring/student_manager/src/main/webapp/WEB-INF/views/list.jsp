<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
body {
	text-align: center;
}

.top {
	width: 100%;
}

.left {
	width: 100%;
}

.menu { .
	width: 100%;
}

.contents {
	width: 100%;
}
</style>
</head>
<body>
	<div class="top">
		<jsp:include page="top.jsp" />

	</div>

	<div class="left">
		<div id="menu">
			<center><jsp:include page="left.jsp" /></center>
			<br />
			<div id="contents">
				<jsp:include page="inner_main.jsp" />
			</div>
		</div>

	</div>


</body>
</html>