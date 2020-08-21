<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="update" method="post">
		content <input type="text" name="dcontent" value="${dummy.dcontent }" />
		<input type="submit" value="update" />
		<input type="hidden" name="dnum" value="${dummy.dnum }" />
	</form>
</body>
</html>