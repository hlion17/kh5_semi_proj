<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member m = (Member) request.getAttribute("result"); %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<!-- <script src="https://kit.fontawesome.com/dc662594ab.js" crossorigin="anonymous"></script> -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">


<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css");

*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div {
	border: none;
}
/* button {
	margin: 5%;
} */
.margin {
	margin: 50%;
}
.margin-top{
	margin: 10%;
}
strong{
	font-size: 20px;
}
/*  strong:before {
	content: '\e31b';
	font-family : FontAwesome;
}  */
  strong:before {
	content: '\F708';
	font-family : bootstrap-icons;
}  
  strong:after {
	content: '\F708';
	font-family : bootstrap-icons;
}  
</style>

</head>

<div class="container">
<div class="margin-top"></div>
	<div class="text-center">
	<strong>회원가입이 완료되었습니다!</strong><br><br>
	<strong>회원가입이 완료되었습니다!</strong><br><br>
	<%-- <strong><%=m.getMemberid() %>님 환영합니다!</strong><br><br> --%>
	<%-- <%
	    out.println("<script>alert('회원가입이 완료되었습니다!');</script>");
	%> --%>

	</div>

	<div class="text-center">
	<button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/member/login'">로그인</button>
	<button type="button" class="btn btn-info" onclick="location.href='<%=request.getContextPath() %>/main.jsp'">메인으로</button>
	</div>

	<div class="margin"></div>

</body>
</div>
<!-- header page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>