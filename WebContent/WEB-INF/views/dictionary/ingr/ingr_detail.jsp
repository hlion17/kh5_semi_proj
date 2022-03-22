<%@page import="dto.Ingredient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Ingredient ingr = (Ingredient) request.getAttribute("ingr");
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<link rel="stylesheet" href="/resources/css/dictionary/ingr_detail.css">

<main>
	<div id="main">

	<!-- 사전 사이드바 -->
    <%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
	
	<div id="section">
		<div id="ingr-detail-container">
			<div id="ingr-detail-img">
           		<div><img src="/resources/img/ingredient/<%= ingr.getIngrCode()%>.jpg"></div>
           		<div><h2><%= ingr.getIngrName() %></h2></div>
       		</div>
			<div id="ingr-detail-short">
				<h3>요약</h3>
           		<hr>
	            <p><span>칼로리</span>: <%= ingr.getDetail1() %></p>
	            <p><span>제철</span>: <%= ingr.getDetail2() %></p>
	            <p><span>요약</span>: <br><%= ingr.getDetail3() %></p>
       		</div>
	        <div id="ingr-detail-tip">
	            <h3>구입요령</h3>
	            <hr>
	            <p><%= ingr.getDetail4() %></p>
	        </div>
	        <div id="ingr-detail-keep">
	            <h3>보관법</h3>
	            <hr>
	            <p><span>보관온도</span>: <%= ingr.getDetail5() %></p>
	            <p><span>보관일</span>: <%= ingr.getDetail6() %></p>
	            <p><span>보관법</span>: <br><%= ingr.getDetail7() %></p>
	        </div>
	        <div id="ingr-detail-good">
	            <h3>효능</h3>
	            <hr>
	            <p><%= ingr.getDetail10() %></p>
	        </div>
	        <div id="ingr-detail-etc">
	            <h3>기타</h3>
	            <hr>
	            <p><span>손질법</span>: <br><%= ingr.getDetail8() %></p>
	            <p><span>섭취방법</span>: <br><%= ingr.getDetail9() %></p>
	        </div>
   		</div>
	</div>
	<div id="clear-fix"></div>
</div>
</main>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
