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
<link rel="stylesheet" type="text/css" href="./css/m_reservation.css?v=2">
<script src="./js/jquery.js"></script>
</head>
<body>
	<!-- 상단 시작 -->
	<%@ include file="./top.jsp"%>
	<!-- 상단 끝 -->
	<main>
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
		<!-- 배너 -->
		<%@ include file="./banner.jsp"%>
		<!-- 배너 끝-->
		<!-- 중간 -->
		<form id="reservation_form">
			<section class="subpage">
				<input type="hidden" name="r_id" id="r_id" value="<%=al.get(0)%>">
				<input type="hidden" name="p_code" id="p_code">
				<input type="hidden" name="r_rname" id="r_rname">
				<div class="member_agree">
					<p>펜션 예약하기</p>
					<ol class="reser_ol">
						<li>
							<span class="reser_part1">펜션명</span> 
							<span class="reser_part2" id="p_name"> [강원 평창군] 한화리조트 평창 </span>
						</li>
						<li>
							<span class="reser_part1">객실선택</span> 
							<span class="reser_part2"> 
								<select class="reser_select" id="p_roomname" name="p_roomname">
									<option value="">객실선택</option>
								</select>
							</span>
						</li>
						<li>
							<span class="reser_part1">일자선택</span> 
							<span class="reser_part2"> 
								<input type="datetime-local" class="reser_input" id="r_date" name="r_date">
							</span>
						</li>
						<li>
							<span class="reser_part1">객실구조</span> 
							<span class="reser_part2" id="p_roomtype"> 방2, 주방1, 화장실1, 거실1</span>
						</li>
						<li>
							<span class="reser_part1">입실인원</span> 
							<span class="reser_part2">기준 <span id="p_person">3</span>인 / 최대 <span id="p_maxperson">3</span>인 </span>
						</li>
						<li>
							<span class="reser_part1">추가인원</span> 
							<span class="reser_part2" id="p_maxperson">기준인원 초과시 추가요금이 발생합니다. </span>
						</li>
						<li>
							<span class="reser_part1">구매금액</span> 
							<span class="reser_part2" id="p_price"> 165,600원 </span>
						</li>
					</ol>
					<p>예약자정보 입력</p>
					<ol class="reser_ol">
						<li>
							<span class="reser_part1">예약자명</span> 
							<span class="reser_part2"> 
								<input type="text" class="reser_input" maxlength="30" id="r_name" name="r_name" value="<%=m_name %>">
							</span>
						</li>
						<li>
							<span class="reser_part1">휴대폰</span> 
							<span class="reser_part2"> 
								<input type="number" class="reser_input" maxlength="11" id="r_phone" name="r_phone" value="<%=m_hp %>">
							</span>
						</li>
						<li>
							<span class="reser_part1">입실인원</span> 
							<span class="reser_part2"> 
								<select class="reser_select" id="r_person" name="r_person">
									<option value="">입실 인원선택</option>
								</select>
							</span>
						</li>
						<li>
							<span class="reser_part1">이메일</span> 
							<span class="reser_part2"> 
								<input type="email" class="reser_input" maxlength="50" id="r_email" name="r_email" value="<%=m_email %>">
							</span>
						</li>
					</ol>
					<div class="member_agreebtn" onclick="reservation_submit()">예약하기</div>
				</div>
			</section>
		</form>
		<!-- 하단 시작 -->
		<%@ include file="./bottom.jsp"%>
	</main>
	<%@ include file="./footer.jsp"%>
</body>
</html>
<script>
get_roomlist();
</script>