<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String db_driver = "com.mysql.jdbc.Driver"; 
String db = "jdbc:mysql://webmiwon.co.kr:3306/kkp_402"; 
String user = "kkp_402"; 
String pw = "kkpjava";  

Class.forName(db_driver);
Connection con = DriverManager.getConnection(db,user,pw);
%>