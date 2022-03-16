<%@page import="dto.Ingredient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Ingredient> ingrs = (List<Ingredient>) request.getAttribute("ingrs"); 
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">
    <div id="sidebar">
        <ul>
            <li onclick="location.href = '/ingr/list'">재료검색</li>
            <li onclick="location.href = '/expireDate/list'">유통기한 검색</li>
            <li onclick="location.href = '/openrecipe/pagelist'">오픈레시피 검색</li>
        </ul>
    </div>
    <div id="section">
    	<h1>등록된 재료 리스트</h1>
		<hr>
    
	    <% for(Ingredient i : ingrs) { %>
		<div>
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
		
		</div>
		<% }%>
    </div>
    <div class="clearfix"></div>
</div>
</main>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
