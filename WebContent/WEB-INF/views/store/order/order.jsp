<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<div id="main">
	<div id="section-alone">
		
		<h1>주문페이지</h1>
		<hr>
		
		<div>
		<form action="/order/add" method="post">
		<% if (request.getAttribute("product") != null) { %>
			<% Product product = (Product) request.getAttribute("product"); %>
			상품번호: <%= product.getPro_no() %><br>
			상품카테고리: <%= product.getCty_no() %><br>
			상품이름: <%= product.getName() %><br>
			이미지: <%= product.getImg_path() %><br>
			가격: <%= product.getPrice() %><br>
			상품 설명: <%= product.getDescription() %><br>
			수량: <%= request.getAttribute("proQty") %><br>
			합계: <%= product.getPrice() %><br>
			<input type="text" name="proNo" value="<%= product.getPro_no() %>">
			<input type="text" name="proQty" value="<%= request.getAttribute("proQty") %>">
			<input type="text" name="total" value="<%= product.getPrice() %>">
		<% } else if (request.getAttribute("list") != null) { %>
			<% 
				List<Cart> list = (List<Cart>) request.getAttribute("list");
				int sum = 0;
			%>
			<% for (Cart c : list) { %>
			장바구니번호: <%= c.getCart_no() %><br>
			회원번호: <%= c.getMember_no() %><br>
			상품번호: <%= c.getPro_no() %><br>
			수량: <%= c.getQuantity() %><br>
			가격: <%= c.getPrice() %><br>
			<input type="text" name="proNo" value="<%= c.getPro_no() %>">
			<input type="text" name="proQty" value="<%= c.getQuantity() %>">
			<% sum += c.getPrice() * c.getQuantity(); %>
			<hr>
			<% } %>
			<div>
			합계: <input type="text" name="total" value="<%=sum%>">
			</div>
			
		<% } %>
			주소: <input type="text" name="address">
			전화번호: <input type="text" name="phone">
			수취인: <input type="text" name="receiver">
			<button>주문하기</button>
		</form>
		</div>
		<%-- 
		<div>
			<span>배송정보</span>
			<form action="#">
				주소: <input type="text" name="address">
				전화번호: <input type="text" name="phone">
				수취인: <input type="text" name="reciever">
				<button>주문하기</button>
			</form>
		</div>
		--%>

	</div>
</div>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>