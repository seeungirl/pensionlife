<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./dbconfig.jsp" %>
<%
String sql = "select * from qa_board where reply_ck='N'";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./admin_css/index.css?v=5">
    <script src="./js/jquery.js"></script>
    <script src="./js/admin_login.js"></script>
</head>
<body>

<%@ include file="./admin_top.jsp" %>

<article class="admin_lists">
    <p>QA 문의 게시판 리스트</p>
    <ul class="lists_uls color1">
        <li>제목</li>
        <li>글쓴이</li>
        <li>등록일</li>
    </ul>
    <!--qa 데이터 출력 리스트 부분 -->
    <%
     while(rs.next()){
    %>
    <ul class="lists_uls" onclick="location.href='./admin_qawrite.jsp?b_idx=<%= rs.getString("b_idx") %>'" style="cursor: pointer;">
        <li style="text-align: left;"><%= rs.getString("b_subj").length() > 15 ? rs.getString("b_subj").substring(0, 15) + "..." : rs.getString("b_subj") %></li>
        <li><%=rs.getString("m_name") %></li>
        <li><%= rs.getString("b_date").length() > 10 ? rs.getString("b_date").substring(0, 10) : rs.getString("b_date") %></li>
    </ul>
    <%} %>
    <!--qa 데이터 출력 리스트 부분 -->
</article>
<br><br>
<%@ include file="./footer.jsp" %>
</body>
</html>


<%
con.close();
%>