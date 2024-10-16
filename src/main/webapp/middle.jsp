<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int login = 0;
	if(id != null){
		login=1;
	}
%>
<section>
	<input type="hidden">
    <ol class="product"></ol>
</section>
<script>
get_pensionlist(<%=login%>);
</script>