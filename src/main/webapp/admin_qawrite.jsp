<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./dbconfig.jsp" %>
<%
String sql = "select * from qa_board where b_idx=?";
PreparedStatement ps = con.prepareStatement(sql);
String b_idx = request.getParameter("b_idx");

ps.setString(1, b_idx);
ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./admin_css/index.css?v=6">
    <script src="./js/jquery.js"></script>
</head>
<script src="./js/admin_board.js"></script>
<body>

<%@ include file="./admin_top.jsp" %>
<form id="frm_replyWrite">
<article class="admin_lists">
    <p>QA 문의 내용</p>
    
    <%
    hs.setAttribute("b_idx", b_idx);
    while(rs.next()){
    %>
    <ul class="qa_write">
        <li>고객명</li>
        <li><%=rs.getString("m_name") %></li>
        <li>제목</li>
        <li><%=rs.getString("b_subj") %></li>
        <li>내용</li>
        <li class="view1"><%=rs.getString("b_post") %></li>
        <li>등록일</li>
        <li><%=rs.getString("b_date").length() > 10 ? rs.getString("b_date").substring(0, 10) : rs.getString("b_date") %></li>
        <li>답변</li>
        <li><textarea placeholder="답변내용을 입력하세요" name="reply" class="answer"></textarea></li>
    </ul>
    <%} %>
    <input type="button" class="adbtn1" value="답변등록" onclick="reply_write()">
</article>
</form>
<%@ include file="./footer.jsp" %>
</body>
</html>