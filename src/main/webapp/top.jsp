<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="./js/m_index.js"></script>
<%
HttpSession hs = request.getSession();//가져와야함
String id = (String) hs.getAttribute("id");
ArrayList<String> al = (ArrayList<String>) hs.getAttribute("user");
%>
<header>
	<script src="./js/m_top.js?v=1"></script>
	<ul class="top_menu">
		<li id="menu_slide"><img src="./img/menu.svg"></li>
		<li><a href="./index.jsp"><img src="./img/header_logo.png"></a></li>
		<%
		if (id == null) {
		%>
		<li class="lg_btn in" onclick="login_pop()"><img
			src="./img/person.svg"></li>
		<%
		} else {
		%>
		<li class="lg_btn out"><%=al.get(1)%>님 <a href="./logout.do"><img
				src="./img/logout.svg"></a></li>
		<%
		}
		%>
	</ul>
</header>
<div class="menus_bar" id="menus_bar">
	<div class="load_menus" id="load_menus">
		<ul>
			<a href="./reservation_check.jsp" style="text-decoration: none;"><li>팬션
					예약확인</li></a>
			<a href="./reservation_check.jsp" style="text-decoration: none;"><li>팬션
					예약취소</li></a>
			<a href="./m_qalist.jsp" style="text-decoration: none;"><li>1:1문의
					게시판</li></a>
		</ul>
	</div>
</div>

<form id="frm_top" method="post" action="./login.do"
	onsubmit="return login_btn();">
	<aside class="popup" id="popup" style="display: none;">

		<div class="login">
			<span class="close" onclick="pop_close();">X</span>
			<p>MEMBER-LOGIN</p>
			<ol>
				<li><input type="text" class="login_input" name="mid" value=""
					id="login_id" placeholder="아이디를 입력하세요"></li>
				<li><input type="password" class="login_input" name="mpw"
					id="login_pw" placeholder="패스워드를 입력하세요"></li>
				<li><label><input type="checkbox" class="login_check"
						name="login_auto" value="" id="login_auto" onclick="auto_check()">
						자동로그인</label></li>
				<li><input type="submit" value="로그인" class="login_btn"></li>
				<li class="login_info"><span onclick="page_location(1)">아이디
						찾기</span> <span onclick="page_location(2)">회원가입</span></li>
			</ol>
		</div>
</form>
</aside>
<script>
	$(function() {
		var ck = localStorage.getItem("auto");
		var autoid = localStorage.getItem("auto_id");
		if (ck != null) {
			document.getElementById("login_id").value = autoid;
			frm_top.login_auto.checked = true;
		}

	})
</script>