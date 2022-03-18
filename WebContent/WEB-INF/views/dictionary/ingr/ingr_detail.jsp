<%@page import="dto.Ingredient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Ingredient ingr = (Ingredient) request.getAttribute("ingr");
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">

	<!-- 사전 사이드바 -->
    <%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
    
    <div id="section">
    	
<h3>재료이름: <%= ingr.getIngrName() %></h3><br>
<img src="/resources/img/ingredient/<%= ingr.getIngrCode()%>.jpg"><br>
 재료코드: <%= ingr.getIngrCode() %><br>
 재료분류번호: <%= ingr.getIngrCtyCode() %><br>
 칼로리: <%= ingr.getDetail1() %><br>
 제철: <%= ingr.getDetail2() %><br>
 요약: <%= ingr.getDetail3() %><br>
 구입요령: <%= ingr.getDetail4() %><br>
 보관온도: <%= ingr.getDetail5() %><br>
 보관일: <%= ingr.getDetail6() %><br>
 보관법: <%= ingr.getDetail7() %><br>
 손질법: <%= ingr.getDetail8() %><br>
 섭취방법: <%= ingr.getDetail9() %><br>
 효능: <%= ingr.getDetail10() %><br>
		

</main>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
