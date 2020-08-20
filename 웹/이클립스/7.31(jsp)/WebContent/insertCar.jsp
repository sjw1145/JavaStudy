<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.ac.green.Car" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="car" scope="request" class="kr.ac.green.Car" />
<jsp:setProperty property="*" name="car"/>
<%
	/* 
		JDBC ���α׷����� ����
		1. ����̹� �ε�
		2. ���� -> java.sql.Connection
		3. ���� -> java.sql.Statement(CRUD)
		4. ��������
	*/
	
	//1. ����̹� �ε� -> DriverManager�� ��� (1ȸ�� ����)
	Class.forName("com.mysql.jdbc.Driver");
	
	//2. ����
	Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/test", 	//DB URL	
		"root", 	//UID
		"1234"  	//UPW
	);

	//3. ���� ��� ��ü
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