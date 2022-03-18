<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">

	<!-- 사전 사이드바 -->
    <%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
    
    <div id="section">


<h1>식품 유통기한 검색</h1>
<hr>
<form action="/expireDate/list" method="post">
    <input type="text" name="item">
    <button>검색</button>
</form>





    </div>
</div>
</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>

