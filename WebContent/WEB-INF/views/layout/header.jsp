<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String memberid = (String) session.getAttribute("memberid"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 메인 CSS 로드 -->
<link rel="stylesheet" href="/resources/css/main.css">
</head>

<body>

<header>
<div id="header">
<!-- 홈으로 가기 -->
<ul>
    <li><a href="/<%=request.getContextPath() %>" >Home</a></li>
</ul>
    
<!-- 비로그인 상태 헤더 메뉴 -->
<%	if( session.getAttribute("login") == null ) { %>
<ul>
    <li><a href="<%=request.getContextPath() %>/member/login">로그인</a></li>
    <li><a href="<%=request.getContextPath() %>/member/join">회원가입</a></li>
    <li><a href="<%=request.getContextPath() %>/notice/list">공지사항</a></li>
    <li><a href="<%=request.getContextPath() %>/qa/list">Q&A</a></li>
</ul>
<% 	} %>

<!-- 로그인 상태 헤더 메뉴 -->
<%	if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) { %>
<strong><%=session.getAttribute("nick") %>님, 환영합니다</strong>
<ul>
    <li><a href="<%=request.getContextPath() %>/member/logout">로그아웃</a></li>
    <li><a href="<%=request.getContextPath() %>/member/infoView">회원정보</a></li>
    <li><a href="<%=request.getContextPath() %>/notice/list">공지사항</a></li>
    <li><a href="<%=request.getContextPath() %>/qa/list">Q&A</a></li>
</ul>
<% 	} %>
</div>
</header>

<nav>
    <div id="nav">
        <ul>
            <li><a href="/ref/choose?memberId=<%= memberid %>">냉장고</a></li>
            <li><a href="/dictionary/main">요리사전</a></li>
            <li><a href="<%=request.getContextPath() %>/recipe/board">커뮤니티</a></li>
            <li><a href="/store">상점</a></li>
        </ul>
    </div>
</nav>