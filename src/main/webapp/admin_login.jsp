<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./admin_css/index.css?v=4">
    <script src="./js/jquery.js"></script>
    <script src="./js/admin_login.js"></script>
</head>
<body>
<header class="admin_header"><img src="./img/header_logo.png"></header>
<aside class="admin_login">
    <p><img src="./admin_img/logo.png"><span>ADMINISTRATOR</span></p>
    <form id="frm_admin">
    <ol class="admin_part">
        <li><input type="text" class="adin1" id="ad_id" name="ad_id" placeholder="아이디를 입력하세요"></li>
        <li><input type="password" class="adin1" id="ad_pw" name="ad_pw" placeholder="패스워드를 입력하세요"></li>
        <li><input type="button" class="adbtn1" value="LOGIN" onclick="admin_login()"></li>
    </ol>
    </form>
</aside>
<%@include file="./footer.jsp" %>
</body>
</html>