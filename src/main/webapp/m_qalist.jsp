<%@page import="java.sql.PreparedStatement"%>
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
</head>
<body>
<!-- 상단 시작 -->
<%@ include file="./top.jsp" %>
<%
    if (al == null || al.isEmpty() || al.get(0) == null || al.get(0).isEmpty()) {
        // 로그인하지 않은 사용자일 경우 로그인 페이지로 리다이렉트
%>
        <script type="text/javascript">
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "index.jsp";
        </script>
<%
        return;
    }
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
        <p>1:1 문의게시판</p>
        <span class="sub_titles">질문하신 리스트가 출력 됩니다.</span>
        <ul class="qa_lists">
            <li>질문제목</li>
            <li>글쓴이</li>
            <li>등록일</li>
            <li>처리</li>
        </ul>
<%
String sql = "select * from qa_board where m_id=? order by b_idx desc";
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, al.get(0));
ResultSet rs = ps.executeQuery();

	while(rs.next()){
	%>
	        <ul class="qa_lists2" onclick="location.href='./m_qaview.jsp?b_idx=<%= rs.getString("b_idx") %>'" style="cursor: pointer;">
	            <li><%= rs.getString("b_subj").length() > 15 ? rs.getString("b_subj").substring(0, 15) + "..." : rs.getString("b_subj") %></li> 
	            <li><%= rs.getString("m_name") %></li>
	            <li><%= rs.getString("b_date").length() > 10 ? rs.getString("b_date").substring(0, 10) : rs.getString("b_date") %></li>
	            <li><%
	            if(rs.getString("reply_ck").equals("N")){
	            	out.print("미답변");	
	            }
	            else {%>
	            	<font style="color:red">
	            	<%out.print("답변완료");%></font><%
	            }%>
	            </li>
	        </ul>
	<%
	}
%>
        <div class="member_agreebtn" onclick="location.href='./m_qawrite.jsp';">문의하기</div>
    </div>   
</section>
<%@ include file="./bottom.jsp" %>

</main>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
</html>