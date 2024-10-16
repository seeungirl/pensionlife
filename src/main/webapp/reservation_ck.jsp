<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>)request.getAttribute("result_list"); 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/m_sub.css?v=2">
    <link rel="stylesheet" type="text/css" href="./css/m_reservation.css?v=2">
    <script src="./js/jquery.js"></script>
</head>
<body>
<!-- 상단 시작 -->
<%@ include file="./top.jsp"%>
<!-- 상단 끝 -->
<main>
<!-- 배너 -->
<%@ include file="./banner.jsp"%>
<!-- 배너 끝-->
<!-- 중단 -->
<section class="subpage">
	<form id="reservation_del_form">
		<input type="hidden" name="r_idx" id="r_idx" value="">
	</form>
	<% 
	if(data.size() <= 0) {
	%>
	<div class="member_agree_empty">예약 내역이 없습니다</div>
	<%
	}else{
		int w=0;
		while(w<data.size()){
	%>
    <div class="member_agree">
    	<p>펜션 예약확인</p>
	    <ol class="reser_ol">
		    <li>
			    <span class="reser_part1">펜션명</span>
			    <span class="reser_part2"><%= data.get(w).get(0)%></span>
		    </li>
		    <li>
			    <span class="reser_part1">객실선택</span>
			    <span class="reser_part2"><%= data.get(w).get(1)%></span>
		    </li>
		    <li>
			    <span class="reser_part1">일자선택</span>
			    <span class="reser_part2"><%= data.get(w).get(2)%></span>
		    </li>
		    <li>
			    <span class="reser_part1">객실구조</span>
			    <span class="reser_part2"><%= data.get(w).get(3)%></span>
		    </li>
		    <li>
			    <span class="reser_part1">입실인원</span>
			    <span class="reser_part2">기준 <%= data.get(w).get(4)%>인 / 최대 <%= data.get(w).get(5)%>인</span>
		    </li>
		    <li>
			    <span class="reser_part1">추가인원</span>
			    <span class="reser_part2">기준인원 초과시 추가요금이 발생합니다.</span>
		    </li>
		    <li>
			    <span class="reser_part1">구매금액</span>
			    <span class="reser_part2"><%= data.get(w).get(6)%></span>
		    </li>
	    </ol>
		<p>예약자 정보</p>
	     <ol class="reser_ol">
		    <li>
			    <span class="reser_part1">객실선택</span>
			    <span class="reser_part2"><%= data.get(w).get(1)%></span>
		    </li>
		    <li>
			    <span class="reser_part1">예약자명</span>
			    <span class="reser_part2"><%= data.get(w).get(7)%></span>
		    </li>
			<li>
				<span class="reser_part1">휴대폰</span>
				<span class="reser_part2"><%= data.get(w).get(8)%></span>
			</li>
			<li>
				<span class="reser_part1">입실인원</span>
				<span class="reser_part2"><%= data.get(w).get(9)%>명</span>
			</li>
			<li>
				<span class="reser_part1">이메일</span>
				<span class="reser_part2"><%= data.get(w).get(10)%></span>
			</li>
	 	</ol>
    	<div class="member_agreebtn" onclick="reservation_delete('<%= data.get(w).get(11)%>')">예약취소</div>
    </div>
    <% 
    w++;
    }
		}%>
</section>
<!-- 하단 시작 -->
<%@ include file="./bottom.jsp"%>
</main>
<%@ include file="./footer.jsp"%>
</body>
</html>