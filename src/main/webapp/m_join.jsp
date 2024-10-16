<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/m_sub.css?v=1">
    <script src="./js/jquery.js"></script>
</head>
<body>
<!-- 상단 시작 -->
<%@ include file="./top.jsp" %>
<!-- 상단 끝 -->
<main>
<!-- 배너 -->
<%@ include file="./banner.jsp" %>
<!-- 배너 끝-->
<!-- 중단 -->
<form id="frm_ag">
<section class="subpage">
    <div class="member_agree">
    <p>회원가입 약관동의</p>
    <ol class="agree_ol" id="ck_box">
    <li><label><input type="checkbox" id="ck1" class="ckbox" onclick="ck_box()"> 이용약관의 동의</label><span class="agree_info">[자세히 보기]</span></li>
    <li><label><input type="checkbox" id="ck2" class="ckbox" onclick="ck_box()"> 개인정보 수집의 동의</label><span class="agree_info">[자세히 보기]</span></li>
    <li><label><input type="checkbox" id="ck3" class="ckbox" onclick="ck_box()"> 개인정보 제3자 제공 동의</label><span class="agree_info">[자세히 보기]</span></li>
    <li><label><input type="checkbox" id="ck4" class="ckbox" onclick="ck_box()"> 개인정보 위탁제공 동의</label><span class="agree_info">[자세히 보기]</span></li>
    <li><label><input type="checkbox" id="ck5" name = "ag" value="Y" class="ckbox" onclick="ck_box()"> 마케팅 활용 동의 (선택)</label><span class="agree_info">[자세히 보기]</span></li>
    <li><label><input type="checkbox" id="ck6" class="ckbox" onclick="all_ag()"> 위 약관에 모두 동의 합니다.</label></li>
    </ol>
    <div class="member_agreebtn" id="join_agree" onclick="join_agree()">기본정보 등록하기</div>
    </div>
</section>
</form>
<!-- 하단 시작 -->
<%@ include file="./bottom.jsp" %>
</main>
<%@ include file="./footer.jsp" %>
</body>
</html>