<%@page import="dto.OrderResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	OrderResult orderResult = (OrderResult) request.getAttribute("orderResult");
%>
<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<div id="main">
	<div id="section-alone">

		<h1>주문성공</h1>
		<hr>
		
		주문번호: <%= orderResult.getOrderNo() %><br>
		주문일자: <%= orderResult.getOrderDate() %><br>
		주문총금액: <%= orderResult.getTotal() %><br> 
		<%-- 상품명: <%= orderResult.getProName() %><br><!-- 이상하다 상품명이 여기 왜 나오지 --> --%>
		배송주소: <%= orderResult.getAddress() %><br>
		연락처: <%= orderResult.getPhone() %><br>
		수취인: <%= orderResult.getReceiver() %><br>

	</div>
</div>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>