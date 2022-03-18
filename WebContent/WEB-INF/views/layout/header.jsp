<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta charset="UTF-8">

<link rel="stylesheet" href="/resources/css/main.css">
</head>

<body>

<header>
<div id="header">
    <ul>
        <li><a href="/main.jsp" >Home</a></li>
    </ul>
    <ul>
        <li>로그인</li>
        <li>회원가입</li>
        <li>고객센터</li>
    </ul>
</div>
</header>

<nav>
    <div id="nav">
        <ul>
            <li><a href="/ref/choose?memberId=test1">냉장고</a></li>
            <li><a href="/ingr/list">요리사전</a></li>
            <li><a href="<%=request.getContextPath() %>/recipe/board">커뮤니티</a></li>
            <li>상점</li>
        </ul>
    </div>
</nav>