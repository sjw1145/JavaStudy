<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.Car" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="car" scope="request" class="kr.ac.green.Car" />
<jsp:setProperty property="*" name="car"/>
<%
	/* 
		JDBC 프로그래밍의 절차
		1. 드라이버 로드
		2. 연결 -> java.sql.Connection
		3. 질의 -> java.sql.Statement(CRUD)
		4. 연결해제
	*/
	
	//1. 드라이버 로드 -> DriverManager에 등록 (1회만 실행)
	Class.forName("com.mysql.jdbc.Driver");
	
	//2. 연결
	Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/test", 	//DB URL	
		"root", 	//UID
		"1234"  	//UPW
	);

	//3. 질의 담당 객체
	Statement stmt = con.createStatement();
	String sql = "INSERT INTO car (car_model, car_price, car_desc) " + 
			"VALUES ('%s', %d, '%s');";
	
	sql = String.format(sql, car.getCar_model(), car.getCar_price(), car.getCar_desc());
	
	/*
		executeUpdate(sql:String) : int 
		INSERT , UPDATE, DELETE
		
		executeQuery(sql:String) : ResultSet
		SELECT
	*/
	int result = stmt.executeUpdate(sql);
	
	
	//4. disconnect
	stmt.close();
	con.close();
	
	response.sendRedirect("list.jsp");
%>