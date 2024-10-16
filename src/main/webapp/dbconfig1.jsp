<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String db_driver = "com.mysql.cj.jdbc.Driver"; //db_driver : 일반적으로 db연결시 쓰는 변수명
	String db_url = "jdbc:mysql://webmiwon.co.kr:3306/kkp_402";
	String db_user = "kkp_402";
	String db_pass = "kkpjava";
	Connection dbcon = null;
	try{
	Class.forName(db_driver);
	dbcon = DriverManager.getConnection(db_url,db_user,db_pass);
	
	}catch(Exception e){
		out.print("db접속이 올바르지 않습니다."); //오류발생시 500번 뜸
	}
%>