<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% 
	List<Cart> cartList = (List<Cart>) request.getAttribute("list");
	int sum = 0;
%>

<!-- header -->
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

 <div id="main">
 	<div id="section-alone">
	<h1>상품 결과</h1>
	<hr>
	<div><%=session.getAttribute("memberid")%>님의 장바구니 목록</div>
	<div>
	<% if (cartList == null) { %>
	<span>장바구니에 넣은 상품이 없습니다.</span>
	<% } else { %>
		<% for (Cart c : cartList) { %>
			
			<form action="/cart/update" method="post">
				<input type="hidden" disabled value="<%=c.getCart_no()%>"><br>
				<span>제품이름: <%=c.getName()%></span>
				<input type="hidden" disabled value="<%=c.getMember_no()%>"><br>
				제품번호: <input type="text" name="proNo" value="<%=c.getPro_no()%>"><br>
				가격: <input disabled value="<%=c.getPrice()%>"><br>
				수량: <input type="text" name="proQty" value="<%=c.getQuantity()%>"><br>
				<button>수정</button>
			</form>
			<a href="/cart/delete?proNo=<%=c.getPro_no()%>">삭제</a>
			<% sum += c.getPrice() * c.getQuantity(); %>
		<% } %>
	<% } %>	
	</div>
	<div>금액 합계: <%= sum %></div>
	<br><br><hr>
	
	<button type="button" onclick="location.href='payment'">결제</button>
	<button type="button" onclick="location.href='store'">쇼핑하기</button>
	<button type="button" onclick="location.href='/order'">주문하기</button>
	
	</div>
</div>




<%@include file = "/WEB-INF/views/layout/footer.jsp" %>