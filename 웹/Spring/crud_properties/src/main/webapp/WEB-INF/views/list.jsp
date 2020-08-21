<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a href="goInsert">insert</a>
	<hr/>
	<table>
		<caption>content list</caption>
		<thead>
			<tr>
				<th>num</th>
				<th>content</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan="2">
					<c:forEach var="i" begin="1" end="${pageCount }" step="1">
						<c:choose>
							<c:when test="${i == pageNum }">
								[${i }]
							</c:when>
							<c:otherwise>
								<a href="list?pageNum=${i }">[${i }]</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="dummy" items="${list }">
				<tr>
					<td class="numCol">${dummy.dnum }</td>
					<td class="contentCol">
						<a href="get?dnum=${dummy.dnum }">${dummy.dcontent }</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="delete" method="post">
		글 번호 : <input type="text" name="dnum" />
		<input type="submit" value="delete" />
	</form>
	${pageNum }
</body>
</html>