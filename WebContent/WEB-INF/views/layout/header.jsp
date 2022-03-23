<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!-- 헤더 위에 이거 적어줘야 main.css를 link 할 수 있는데 왜 인지 모르겠다. -->
<!-- 헤더안에 적으면 또 적용 안됨 -->

 <!--  Bootstrap 3  -->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 메인 CSS 로드 -->
<link rel="stylesheet" href="/resources/css/main.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css?after" type="text/css"> --%>

</head>

<% String memberid = (String) request.getSession().getAttribute("memberid"); %>    

<body>

<header>
<div id="header">
<!-- 홈으로 가기 -->
<ul>
    <li><a href="/<%=request.getContextPath() %>" >Home</a></li>
</ul>
    
<!-- 비로그인 상태 헤더 메뉴 -->
<%	if( request.getSession().getAttribute("login") == null ) { %>
<ul>
    <li><a href="<%=request.getContextPath() %>/member/login">로그인</a></li>
    <li><a href="<%=request.getContextPath() %>/member/join">회원가입</a></li>
    <li><a href="<%=request.getContextPath() %>/notice/list">공지사항</a></li>
    <li><a href="<%=request.getContextPath() %>/qa/list">Q&A</a></li>
</ul>
<% 	} %>

<!-- 로그인 상태 헤더 메뉴 -->
<%	if( request.getSession().getAttribute("login") != null && (boolean) request.getSession().getAttribute("login") ) { %>
<strong><%=request.getSession().getAttribute("nick") %>님, 환영합니다</strong>
<ul>
    <li><a href="<%=request.getContextPath() %>/member/logout">로그아웃</a></li>
    <li><a href="<%=request.getContextPath() %>/member/infoView">회원정보</a></li>
    <li><a href="<%=request.getContextPath() %>/social/profile">프로필</a></li>
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
            <li><a href="/ingr/list">요리사전</a></li>
            <li><a href="/recipe/board">커뮤니티</a></li>
            <li><a href="/store">상점</a></li>
            <li><a href="/social/profile">소셜룸</a></li>
        </ul>
    </div>
</nav>