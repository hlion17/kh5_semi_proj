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

<h1>공식 레시피 조회</h1>
<hr>
<form action="/openrecipe/pagelist" method="post">
    <input type="text" name="item">
    <button>검색</button>
</form>


    </div>
</div>
</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
