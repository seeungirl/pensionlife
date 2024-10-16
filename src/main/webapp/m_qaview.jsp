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
</head> 
<!-- 상단 시작 -->
<%@ include file="./top.jsp" %>

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
        <p>1:1 문의게시판(내용확인)</p>
        <span class="sub_titles">빠르게 궁금한 사항을 답변 드리도록 하겠습니다.</span>
<%

String b_idx = request.getParameter("b_idx");
String sql = "select * from qa_board where b_idx=?";

PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, b_idx);
ResultSet rs = ps.executeQuery();

while(rs.next()){
%>
        <ul class="write_ul">
            <li class="cate_txt"><%=rs.getString("b_cate") %></li>
            <li><input type="hidden" name="m_id" value="<%=al.get(0) %>"></li>
            <li><input type="text" class="w_input1 w_bg" name="m_name" value="<%=rs.getString("m_name") %>" readonly></li>
            <li><input type="text" class="w_input1 w_bg" name="m_hp" value="<%=rs.getString("m_hp") %>" readonly></li>
            <li><input type="text" class="w_input1 w_bg" name="m_email" value="<%=rs.getString("m_email") %>" readonly></li>
            <li><input type="text" class="w_input1" value="<%=rs.getString("b_subj") %>" readonly></li>
            <li><textarea class="w_input2"readonly><%=rs.getString("b_post") %></textarea></li>
            <li class="fileview">
            <%if(rs.getString("rfile1")!=null){
            	out.print("첨부파일 : "+rs.getString("rfile1"));
            	if(rs.getString("rfile2")!=null){
            		out.print("<br>");
            		out.print("첨부파일 : "+rs.getString("rfile2"));
            	}
           	}else {
           	out.print("첨부파일이 없습니다");
            }%></li>
        </ul>

        <!--관리자 답변사항-->
        <span class="admin_view" style="display:<%if(rs.getString("reply_ck").equals("Y")){out.print("block");}else{out.print("none");}%>;">관리자 답변내용</span>
        <ul class="answer_admin" style="display:<%if(rs.getString("reply_ck").equals("Y")){out.print("block");}else{out.print("none");}%>;">
            <li><%=rs.getString("reply") %></li>
        </ul>

        <!--관리자 답변사항-->
        
        <%if(rs.getString("reply_ck").equals("N")){%>
      		<div class="member_agreebtn modify_btn" onclick="location.href='./m_qamodify.jsp?b_idx=<%= rs.getString("b_idx") %>';">문의수정</div>
      	<%}%>
        <div class="member_agreebtn" onclick="location.href='./m_qalist.jsp';" style="cursor:pointer;">문의 리스트</div>
    </div> 
<%} %> 
</section>
<!-- 하단 시작 -->
<%@ include file="./bottom.jsp" %>

</main>
<%@ include file="./footer.jsp" %>
</body>
</html>