<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./dbconfig.jsp" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/m_sub.css?v=2">
    <link rel="stylesheet" type="text/css" href="./css/m_qaboard.css?v=2">
    <script src="./js/jquery.js"></script>
    <script src="./js/m_board.js"></script>
</head>
<%@ include file="./top.jsp" %>
<body>
<main>
<!-- 배너 -->
<section>
    <img src="./img/title_Resort.jpg">
</section>
<!-- 배너 끝-->
<!-- 중단 -->

<form id="frm_board">
<section class="subpage">
    <div class="member_agree">
        <p>1:1 문의게시판(글쓰기)</p>
        <span class="sub_titles">문의 유형 선택</span>
        <input type="hidden" id="selectedValue" name="selectedValue">
        <ol class="qa_part">
            <li>이용문의</li>
            <li>예약 및 취소</li>
            <li>환불 및 요금</li>
            <li>시설문의</li>
            <li>이벤트 문의</li>
            <li>기타문의</li>
        </ol>
<%
String sql = "select * from pension_member where m_id=?";

PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, al.get(0));
ResultSet rs = ps.executeQuery();

String m_name = "";
String m_hp = "";
String m_email = "";
while(rs.next()){
	m_name = rs.getString("m_name");
	m_hp = rs.getString("m_hp");
	m_email = rs.getString("m_email");
};
%>
        <ul class="write_ul">
            <li><input type="hidden" name="m_id" value="<%=al.get(0) %>"></li>
            <li><input type="text" class="w_input1 w_bg" name="m_name" value="<%=m_name %>" readonly></li>
            <li><input type="text" class="w_input1 w_bg" name="m_hp" value="<%=m_hp %>" readonly></li>
            <li><input type="text" class="w_input1 w_bg" name="m_email" value="<%=m_email %>" readonly></li>
            <li><input type="text" class="w_input1" name="b_subj" id="b_subj" placeholder="질문 제목"></li>
            <li><textarea class="w_input2" name="b_post" id="b_post" placeholder="질문 내용"></textarea></li>
            <li><input type="file" class="w_li" name="rfile1" onchange="checkFileExtension(this)"> * 최대 2MB까지 가능</li>
            <li><input type="file" class="w_li" name="rfile2" onchange="checkFileExtension(this)"> * 최대 2MB까지 가능</li>
            <li>※ 첨부파일은 이미지만 등록 가능합니다.</li>
        </ul>
        <div class="member_agreebtn" id="qa_write">문의등록</div>
    </div>  
</section>
</form>

<%@ include file="./bottom.jsp" %>

</main>
<%@ include file="./footer.jsp" %>
</body>
</html>
