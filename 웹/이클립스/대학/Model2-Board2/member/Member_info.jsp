<%@page import="net.board.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- <c:set var="info" value="${requestScope.userInfo} " /> --%>
<c:set var="info" value="${requestScope.userInfo}" />
<center>
	<h2>회원정보</h2>
	<table border=1>
		<tr>
			<td>아이디 :</td>
			<td>${info.id}</td>
		</tr>
		<tr>
			<td>비밀번호 :</td>
			<td>${info.passwd}</td>
		</tr>
		<tr>
			<td>이메일 :</td>
			<td>${info.email}</td>
		</tr>
		<tr>
			<td>이름 :</td>
			<td>${info.getNames()}</td>
		</tr>
		<tr>
			<td>주민번호 :</td>
			<td>${info.getManNumber()}</td>
		</tr>
		<tr>
			<td>취미 :</td>
			<td>${info.getHobby1()}${info.getHobby2()} ${info.getHobby3()}
				${info.getHobby4()} ${info.getHobby5()}</td>
		</tr>
		<tr>
			<td>자기소개</td>
			<td>${info.getMyView()}</td>
		</tr>
	</table>
	<a href="AdminProcess.do">[돌아가기]</a>
</center>