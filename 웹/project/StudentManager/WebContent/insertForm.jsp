<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div>
		<jsp:include page="top.jsp" />

	</div>
	<div>
		<div id="menu">
			<center><jsp:include page="left.jsp" /></center>
			<br />
		</div>
	</div>
	<hr />
	<div class="container">
		<h2>학생정보입력</h2>
		<form action="MainServlet" method="post">
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">이름 : </label>
				
				<div class="col-sm-10">
						<input type="text" name="student_name" class="form-control" name="student_name" />
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="control-label col-sm-2" for="tel">전화번호 : </label>
				
				<div class="col-sm-10">
					<input type="text" class="form-control" name="student_tel">
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="control-label col-sm-2" for="grade">점수 : </label>
				
				<div class="col-sm-10">
					<input type="text" class="form-control" name="student_grade">
				</div>
			</div>
			<br/>
			<div class="form-group">
				<label class="control-label col-sm-2" for="grade">등급 : </label>
				
				<div class="col-sm-10">
					<input type="text" class="form-control" name="student_class">
				</div>
			</div>
			<br/>
			<div class="form-group">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-default">등록</button>
				</div>
			</div>
			<input type="hidden" name="cmd" value="insertStudent" />
		</form>
	</div>
</body>
</html>