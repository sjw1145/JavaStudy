<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.board.db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="listcount" value="${requestScope.listcount }" />
<c:set var="nowpage" value="${requestScope.page }" />
<c:set var="maxpage" value="${requestScope.maxpage }" />
<c:set var="startpage" value="${requestScope.startpage }" />
<c:set var="endpage" value="${requestScope.endpage }" />
<html>
<head>
<title>MVC �Խ���</title>
</head>

<body>
	<c:set var="userId" value="${sessionScope.userId }" />
	${userId} �� ȯ���մϴ�.
	<br>
	<a href="LogoutProcess.do">[�α׾ƿ�]</a>
	<c:if test="${userId eq 'admin' }">
		<a href="AdminProcess.do">[������������]</a>
	</c:if>
	<hr>
	<!-- �Խ��� ����Ʈ -->
	<table width=50% border="0" cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="4">MVC �Խ���</td>
			<td align=right><font size=2>�� ���� : ${listcount }</font></td>
		</tr>

		<tr align="center" valign="middle" bordercolor="#333333">
			<td style="font-family: Tahoma; font-size: 8pt;" width="8%"
				height="26">
				<div align="center">��ȣ</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="50%">
				<div align="center">����</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="14%">
				<div align="center">�ۼ���</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="17%">
				<div align="center">��¥</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="11%">
				<div align="center">��ȸ��</div>
			</td>
		</tr>

		<%-- <%
		for(int i=0;i<boardList.size();i++){
			BoardBean bl=(BoardBean)boardList.get(i);
	%> --%>
		<c:forEach var="boardList" items="${requestScope.boardlist}">
			<tr align="center" valign="middle" bordercolor="#333333"
				onmouseover="this.style.backgroundColor='F8F8F8'"
				onmouseout="this.style.backgroundColor=''">
				<td height="23" style="font-family: Tahoma; font-size: 10pt;">
					${boardList.BOARD_NUM}</td>

				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="left">
						<c:set var="BOARD_RE_LEV" value="${boardList.BOARD_RE_LEV * 2 }" />
						<c:choose>
							<c:when test="${boardList.BOARD_RE_LEV ne 0 }">
								<%-- <%for(int a=0;a<=bl.getBOARD_RE_LEV()*2;a++){ %> --%>
								<c:forEach var="r" begin="0" end="${BOARD_RE_LEV}">
							&nbsp;
							</c:forEach>
							��
							</c:when>
							<c:otherwise>
							��
							</c:otherwise>
						</c:choose>
						<a href="./BoardDetailAction.bo?num=${boardList.BOARD_NUM}">
							${boardList.BOARD_SUBJECT} </a>
					</div>
				</td>

				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${boardList.BOARD_NAME}</div>
				</td>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${boardList.BOARD_DATE}</div>
				</td>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${boardList.BOARD_READCOUNT}</div>
				</td>
			</tr>
		</c:forEach>
		<tr align=center height=20>
			<td colspan=7 style="font-family: Tahoma; font-size: 10pt;"><c:if
					test="${nowpage <= 1 }">
			[����]&nbsp;
			</c:if> <c:if test="${nowpage > 1 }">
					<a href="./BoardList.bo?page=${nowpage-1}">[����]</a>&nbsp;
			</c:if> <%-- <%for(int a=startpage;a<=endpage;a++){ --%> <c:forEach var="a"
					begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${a==nowpage }">${a}</c:when>
						<c:otherwise>
							<a href="./BoardList.bo?page=${a}">[${a}]</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach> <c:choose>
					<c:when test="${nowpage >= maxpage }">[����]</c:when>
					<c:otherwise>
						<a href="./BoardList.bo?page=${nowpage + 1}">[����]</a>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr align="right">
			<td colspan="5"><a href="./BoardWrite.bo">[�۾���]</a></td>
		</tr>
	</table>
</body>
</html>