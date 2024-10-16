<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession hs = request.getSession();
%>
    
<header class="admin_header"><img src="./img/header_logo.png" onclick="location.href='/admin_qalist.jsp';" style="cursor:pointer;"></header>
<aside class="admin_qa">
    <p><img src="./admin_img/logo.png"><span><%=(String)hs.getAttribute("admin_name") %>님 환영합니다.<span id="logout" onclick="admin_logout()" style="cursor:pointer;">[로그아웃]</span></span></p>
</aside>
    