<%@ page import="java.util.List" %>
<%@ page import="dto.ExpireDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">
    <div id="sidebar">
        <ul>
            <li onclick="location.href = '/ingr/search'">재료검색</li>
            <li onclick="location.href = '/expireDate/list'">유통기한 검색</li>
            <li onclick="location.href = '/openrecipe/pagelist'">오픈레시피 검색</li>
        </ul>
    </div>
    <div id="section">
	    <h1>식품 유통기한 검색 결과</h1>
		<hr>
		
		<% List<ExpireDate> list = (List<ExpireDate>) request.getAttribute("list"); %>
		
		<% for(ExpireDate e : list) { %>
		<div>
		    <h3>제품명: <%= e.getData1() %></h3><br>
		    유통기한: <%= e.getData2() %><br>
		    업소명: <%= e.getData3() %><br>
		    유형: <%= e.getData4() %><br>
		</div>
		<% }%>
    </div>
</div>
</main>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
