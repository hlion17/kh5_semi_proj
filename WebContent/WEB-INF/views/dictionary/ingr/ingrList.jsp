<%@page import="dto.Ingredient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Ingredient> ingrs = (List<Ingredient>) request.getAttribute("ingrs"); 
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<link rel="stylesheet" href="/resources/css/dictionary/dic_ingr_main.css">

<!-- <script src="/resources/js/dictionary/dic_ingr_main.js"></script> -->

<script>
$(document).ready(function() {
	
	// 재료 조회하는 요청(ajax)
	$("#btn-ingrsearch-submit").click(function() {
		const ingrName = $("input[name='ingrName']").val()
		
		$.ajax({
			type: "POST", 
			url: "/ingr/list", 
			dataType: "html", 
			data: {ingrName: ingrName}, 
			success: function(res) {
				$("#ingr-short-container").html(res)
			}, 
			error: function() {
				console.log("ajax 실패")
			}
		})	
	})
	
	$(".ingr-short").children().click(function() {
		const ingrCode = $(this).parent().children().first().text()
		location.href = "/ingr/detail?ingrCode=" + ingrCode
	})
	
})
</script>

<main>
<div id="main">

	<!-- 사전 사이드바 -->
    <%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
    
    <div id="section">

    	<h1>등록된 재료 리스트</h1>
		<hr>
		<div>
			<input type="text" name="ingrName">
			<button id="btn-ingrsearch-submit">검색</button>
		</div>
    
    	<div id="ingr-short-container">
	    <% for(Ingredient i : ingrs) { %>
	    	<div class="ingr-short">
	    		<div style="display: none;"><%= i.getIngrCode() %></div>
				<div><h3><%= i.getIngrName() %></h3></div>
				<div>
					칼로리: <%= i.getDetail1() %><br>
					제철: <%= i.getDetail2() %><br>
					보관온도: <%= i.getDetail5() %><br>
					보관일: <%= i.getDetail6() %><br>
				</div>
    		</div>
		
		<% }%>
		</div>
    </div>
    <div class="clearfix"></div>
</div>
</main>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>


<%-- 		<div id="ingr-short">
		    <h3>재료이름: <%= i.getIngrName() %></h3><br>
		    <img src="/resources/img/ingredient/<%= i.getIngrCode()%>.jpg"><br>
			    재료코드: <%= i.getIngrCode() %><br>
			    재료분류번호: <%= i.getIngrCtyCode() %><br>
			    칼로리: <%= i.getDetail1() %><br>
			    제철: <%= i.getDetail2() %><br>
			    요약: <%= i.getDetail3() %><br>
			    구입요령: <%= i.getDetail4() %><br>
			    보관온도: <%= i.getDetail5() %><br>
			    보관일: <%= i.getDetail6() %><br>
			    보관법: <%= i.getDetail7() %><br>
			    손질법: <%= i.getDetail8() %><br>
			    섭취방법: <%= i.getDetail9() %><br>
			    효능: <%= i.getDetail10() %><br>
		
		</div> --%>
