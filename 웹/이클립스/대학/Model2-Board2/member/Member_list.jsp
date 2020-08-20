<%@page import="net.board.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<c:forEach items="${requestScope.memberList }" var="memberList">
	<h3>
		<a href="MemberInfoProcess.do?id=${memberList.id }">${memberList.id }</a>
		<a href="MemberDeleteProcess.do?id=${memberList.id }">회원삭제</a>
	</h3>
	<!--<c:out value="${memberList.id }"/>-->
</c:forEach>
<h2>회원목록</h2>
<%-- <%
	for (int i = 0; i < memberList.size(); i++){
		MemberBean m1 = (MemberBean) memberList.get(i);
%>
<h3>
	<a href="MemberInfoProcess.do?id=${m11.getId()}">${m11.getId()}</a> <a
		href="MemberDeleteProcess.do?id=${m11.getId()}">회원삭제</a>

</h3>
<%
	}
%>
 --%>
<br>
<a href="./BoardList.bo">[돌아가기]</a>
