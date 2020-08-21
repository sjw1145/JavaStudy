<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="doSearch" method="post">
		검색 : <select name="search">
			<option value="id">ID</option>
		</select> &nbsp; 내용: <input type="text" size="14" name="searchData" /> &nbsp;
		<input type="submit" value="go" />
		<input type="hidden" name="work" value="delete" />
	</form>
</body>
</html>