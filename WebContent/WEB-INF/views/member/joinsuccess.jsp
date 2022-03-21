<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member m = (Member) request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

</head>

<div class="container">
<body>
<div class="text-center">
<strong>회원가입이 완료되었습니다!</strong><br><br>
<strong><%=m.getMemberid() %>님 환영합니다!</strong><br>
<%
    out.println("<script>alert('회원가입이 완료되었습니다!');</script>");
%>

</div>

<div class="text-center">

<button onclick="location.href='<%=request.getContextPath() %>/member/login'">로그인</button>
<button onclick="location.href='<%=request.getContextPath() %>/main.jsp'">메인으로</button>
</div>

<button></button>
<button></button>
</body>
</div>
<!-- header page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>