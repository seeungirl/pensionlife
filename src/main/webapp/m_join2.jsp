<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ag = (String)request.getAttribute("ag");
%>
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
<main>
<!-- 배너 -->
<section>
    <img src="./img/title_Resort.jpg">
</section>
<!-- 배너 끝-->
<!-- 중단 -->
<form id="frm">
<input type="hidden" name="mag" value="<%=ag%>">
<section class="subpage">
    <div class="member_agree">
    <p>회원 기본 정보입력</p>
    <ol class="join_ol">
    <li><input type="text" class="join_input1" id="mid" name="mid" placeholder="아이디 (영문/숫자 6~16자리)" maxlength="16"></li>
    <li><input type="text" class="join_input1" id="mname" name="mname" placeholder="이름 (홍길동)" maxlength="10"></li>
    <li><input type="password" class="join_input1" id="mpw" name="mpw" placeholder="비밀번호 (영문/숫자 6~12자리)" maxlength="12"></li>
    <li><input type="password" class="join_input1" id="mpw2" placeholder="동일한 패스워드를 입력하세요" maxlength="12"></li>
    <li><input type="email" class="join_input1"id="memail" name="memail" placeholder="이메일을 입력하세요" maxlength="35"></li>
    <li><input type="tel" class="join_input1" id="mhp" name="mhp" placeholder="연락처 ('-'는 미입력)" maxlength="11"></li>
    <li class="security">
    보안코드 : <input type="text" class="join_input2 bgcolor" id="capcha" maxlength="6" readonly="readonly">
    <input type="number" class="join_input2" id="sec" placeholder="보안코드 6자리 입력" maxlength="6">
    </li>
    </ol>
    <div class="member_agreebtn" onclick="join()">회원가입</div>
    </div>
</section>
</form>
<!-- 하단 시작 -->
<%@ include file="./bottom.jsp" %>
</main>
<%@ include file="./footer.jsp" %>
</body>
</html>