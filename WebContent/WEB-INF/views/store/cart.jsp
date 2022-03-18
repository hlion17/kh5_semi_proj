<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% List<Cart> cartList = (List)request.getAttribute("cartList"); %>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<table class="table table-hover">

	<tr>
		<th>상품</th><br>
<!-- 		<th>장바구니번호</th><br> -->
<!-- 		<th>회원번호</th><br> -->
<!-- 		<th>상품번호</th><br> -->
		<th>상품수량</th><br>
		<th>가격</th><br>
	</tr>

	<%	for(int i=0; i<cartList.size(); i++) { %>
	<tr>
		
		<td><img alt="onion" src="../resources/img/store/onion.jpg" width="200" height="200"></td><br>
<%-- 		<td><%=cartList.get(i).getCart_no() %></td><br> --%>
<%-- 		<td><%=cartList.get(i).getMember_no() %></td><br> --%>
<%-- 		<td><%=cartList.get(i).getPro_no() %></td><br> --%>
		<td><%=cartList.get(i).getQuantity() %></td><br>
		<td><%=cartList.get(i).getPrice() %></td><br>
	</tr>
	<%	} %> 
	
	</table>

<button type="button" onclick="location.href='payment'">결제</button>
<button type="button" onclick="location.href='store'">쇼핑하기</button>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>