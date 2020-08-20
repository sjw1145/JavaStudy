
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
	
<c:set var="num" value="${ fn:length(list) }"></c:set>
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
	function modify(button) {
		var tableData = document.getElementById('tb');
		var tr = button.parentElement.parentElement;
		var index = tr.rowIndex;
		var rowNum = tableData.rows[index].cells[0].innerText;
		var input_data = document.getElementsByName("phone_id")[0];
		
		input_data.value = rowNum;
		mainForm.action = "goModify";
		mainForm.method = "post";
		mainForm.submit();
	}

	function deleteProcess(button) {
		var tableData = document.getElementById('tb');
		var tr = button.parentElement.parentElement;
		var index = tr.rowIndex;
		var rowNum = tableData.rows[index].cells[0].innerText;
		var input_data = document.getElementsByName("phone_id")[0];
		
		input_data.value = rowNum;
		
		var mainForm = document.mainForm;
		mainForm.action = "deletePhone";
		mainForm.method = "post";
		mainForm.submit();
	}

	function showMsg(msg) {
		alert(msg);
	}
	
</script>
</head>
<body>
<c:if test="${ msg != null }">
	<script>
		showMsg('${ msg }');
	</script>
</c:if>
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 메뉴 끝 -->
	<!-- 메인 -->
	<div class="container" style="margin-top: 30px">
		<form class="form-inline" action="searchPhone" method="get">
			<div class="form-group">
				<label for="검색">검색 </label>
				
				<select name="search">
					<option value="id">번호</option>
					<option value="name">이름</option>
				</select> 
				
				<input type="text" size="14" name="searchData" class="form-control" />
				<input type="submit" value="go" class="btn btn-dark" />
			</div>
		</form>
		
		<form name="mainForm">
			<table class="table table-hover" style="margin-top: 30px" id="tb">
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>별명</th>
						<th>성별</th>
						<th>전화번호</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					
					<c:choose>
						<c:when test="${ num == 0 }">
							<tr>
								<td colspan="5">is empty</td>
							</tr>
						</c:when>
						<c:when test="${ list == null }">
							<tr>
								<td colspan="5">No Search Data</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="pb" items="${ list }">
								<tr>
									<td>${ pb.phone_id }</td>
									<td>${ pb.name }</td>
									<td>${ pb.nick }</td>
									<td>${ pb.gender }</td>
									<td>${ pb.tel }</td>
									<td><button type="button" class="btn btn-dark"
											onclick="modify(this)">수정</button></td>
									<td><button type="button" class="btn btn-dark"
											onclick="deleteProcess(this)">삭제</button></td>
								</tr>
							</c:forEach>

						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<input type="hidden" name="phone_id" />
		</form>
	</div>

	<!-- 메인 끝 -->

</body>
</html>