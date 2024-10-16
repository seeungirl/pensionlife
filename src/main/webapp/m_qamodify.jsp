<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
<body>
<!-- 상단 시작 -->
<%@ include file="./top.jsp" %>
<%
String b_idx = request.getParameter("b_idx");
%>
<!-- 상단 끝 -->
<main>
<!-- 배너 -->
<section>
    <img src="./img/title_Resort.jpg">
</section>
<!-- 배너 끝-->
<!-- 중단 -->
<section class="subpage">

    <div class="member_agree">
        <p>1:1 문의게시판(문의수정)</p>
        <span class="sub_titles">빠르게 궁금한 사항을 답변 드리도록 하겠습니다.</span>
 
<form id="frm_qa_modify">
   	<input type="hidden" name="b_idx" value="<%=b_idx%>">

<%
String sql = "select * from qa_board where b_idx=?";
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, b_idx);
ResultSet rs = ps.executeQuery();

while(rs.next()){
%>

    <ul class="write_ul">
        <li class="cate_txt">질문항목 : 환불 및 요금</li>
        <li><input type="text" class="w_input1 w_bg" value="<%= rs.getString("m_name") %>" readonly></li>
        <li><input type="text" class="w_input1 w_bg" value="<%= rs.getString("m_hp") %>" readonly></li>
        <li><input type="text" class="w_input1 w_bg" value="<%= rs.getString("m_email") %>" readonly></li>
        <li><input type="text" class="w_input1" name="b_subj" value="<%= rs.getString("b_subj") %>"></li>
        <li><textarea class="w_input2" name="b_post"><%= rs.getString("b_post") %></textarea></li>
        <li class="fileview" id="fileview1">
        <%
        if(rs.getString("rfile1") != null){
        %>
            첨부파일 : <%= rs.getString("rfile1") %> 
            <span class='btn_del' onclick='removeFileTag("fileview1")'>삭제</span>
        <%
        }
        %>
	    </li>
	    <li class="fileview" id="fileview2">
	        <%
	        if(rs.getString("rfile2") != null){
	        %>
	            첨부파일 : <%= rs.getString("rfile2") %> 
	            <span class='btn_del' onclick='removeFileTag("fileview2")'>삭제</span>
	        <%
	        }
	        %>
	    </li>
        <li class="fileview"><input type="file" class="w_li" name="rfile1"> * 최대 2MB까지 가능</li>
        <li class="fileview"><input type="file" class="w_li" name="rfile2"> * 최대 2MB까지 가능</li>
        <li>※ 첨부파일은 이미지만 등록 가능합니다.</li>
    </ul>
<%
}
%>
        <div class="member_agreebtn" id="qa_modify">문의수정</div>
</form> 
<form id="frm_qa_delete">
   	<input type="hidden" name="b_idx" value="<%=b_idx%>">
	<div class="member_agreebtn" id="qa_delete" style="background-color: darkblue; color:white;">문의삭제</div>
</form>
    </div> 
</section>
<%@ include file="./bottom.jsp" %>

</main>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>

</body>
</html>