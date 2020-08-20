<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- middle.jsp -->
middle 전달받은 값 : <%= request.getParameter("myKey") %>
<jsp:include page="end.jsp" />