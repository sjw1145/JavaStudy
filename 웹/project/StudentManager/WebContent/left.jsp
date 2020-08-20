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

<script>
	function goInsert() {
		location.href = 'MainServlet?cmd=goInsert';
	}

	function goModify() {
		location.href = 'MainServlet?cmd=goModify';
	}

	function goDelete() {
		location.href = 'MainServlet?cmd=goDelete';
	}

	function goList() {
		location.href = 'MainServlet';
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<div class="btn-group">
					<button type="button" class="btn btn-primary" onclick="goList()">학생리스트</button>
					<button type="button" class="btn btn-primary" onclick="goInsert()">학생정보입력</button>
					<button type="button" class="btn btn-primary" onclick="goModify()">학생정보수정</button>
					<button type="button" class="btn btn-primary" onclick="goDelete()">학생정보삭제</button>
				</div>
			</div>
			<div class="col-sm-4">
				<form class="form-inline" action="MainServlet" method="post">
					<div class="form-group">
						<label for="email">검색 : </label> <select name="search">
							<option value="id">ID</option>
							<option value="name">Name</option>
							<option value="class">Class</option>
						</select> <label for="email">내용 : </label> <input type="text" size="14"
							name="searchData" class="form-control" placeholder="검색할 내용" /> <input
							type="submit" value="go" class="btn btn-default" /> <input
							type="hidden" name="cmd" value="searchStudent" />
					</div>

				</form>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
</body>
</html>