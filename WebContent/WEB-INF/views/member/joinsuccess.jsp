<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member m = (Member) request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>

<div class="container">
<body>
<div class="text-center">
<strong>회원가입이 완료되었습니다!</strong><br><br>
<strong><%=m.getMemberid() %>님 환영합니다!</strong><br>
</div>

<div class="text-center">

<button onclick="location.href='<%=request.getContextPath() %>/member/login'">로그인</button>
<button onclick="location.href='<%=request.getContextPath() %>/main.jsp'">메인으로</button>
</div>

<button></button>
<button></button>
</body>
</div>