<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set scope="request" var="pb" value="${ phoneBook }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->

<script>
	function checkGender(gender) {
		var location = document.getElementsByName('gender');
		alert(location.options[0].selected);
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>

	<div class="container" style="margin-top: 30px">
		<form action="modifyPhone" method="post">
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">번호</label>
				<div class="col-sm-10">
					<input type="text" name="phone_id" class="form-control"
						value="${pb.phone_id}" readonly="readonly" />
				</div>
			</div>
			<br />

			<div class="form-group">
				<label for="name" class="control-label col-sm-2">이름</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control"
						value="${pb.name}" />
				</div>
			</div>
			<br />

			<div class="form-group">
				<label class="control-label col-sm-2" for="tel">별명</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="nick"
						value="${pb.nick}">
				</div>
			</div>

			<br />

			<div class="form-group">
				<label class="control-label col-sm-2" for="gender">성별</label>
				<div class="col-sm-10">
					<script>
						checkGender('${ pb.gender }');
					</script>
					<select name="gender" class="custom-select">
						<option <c:if test="${pb.gender eq '남자' }">selected</c:if> value="남자">남자</option>
						<option <c:if test="${pb.gender eq '여자' }">selected</c:if> value="여자">여자</option>
					</select>
				</div>
			</div>
			<br />

			<div class="form-group">
				<label class="control-label col-sm-2" for="grade">전화번호</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="tel"
						value="${pb.tel}">
				</div>
			</div>
			<br />
			<div class="form-group">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-dark">수정</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>