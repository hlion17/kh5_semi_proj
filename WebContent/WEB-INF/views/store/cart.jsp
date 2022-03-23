<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 부트스트랩 적용  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inspiration&family=Roboto:wght@300&display=swap" rel="stylesheet">
   
<% 
	List<Cart> cartList = (List<Cart>) request.getAttribute("list");
	int sum = 0;
%>


<style type="text/css">

.userhi {
	text-align: center;
	font-size: 50px;
	
}

.delete {
	border: 1px solid black;
	background: yellow;
}

.cartinfo {
	
}

#btn{
	background: red;
}


th {
	text-align: center;
}

</style>



<!-- header -->
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

 <div id="main">
		<div class="userhi"><%=session.getAttribute("memberid")%>님의 장바구니 목록</div>
		
		<table class="table-bordered table-condensed" >
				<th width=45%>상품정보</th>
				<th width=20%>상품금액</th>
				<th width=5%>수량</th>
				<th width=10%>배송비</th>
				<th width=10%></th>
				<th width=10%></th>
				
			</tr>
			
			<!-- 장바구니가 null일 때 확인되는 문구  -->
			<% if (cartList == null) { %> 
			<div>장바구니에 넣은 상품이 없습니다.</div>
			<% } else { %>
			<!-- 장바구니에 상품이 있을 때 확인되는 부분  -->
			<% for (Cart c : cartList) { %> 
			<form action="/cart/update" method="post">
				<tr>
					<input type="hidden" disabled value="<%=c.getCart_no()%>">
					<td><%=c.getName()%></td>
					<input type="hidden" disabled value="<%=c.getMember_no()%>">
					<input type="hidden" name="proNo" value="<%=c.getPro_no()%>">
					<td> <input disabled value="<%=c.getPrice()%>"><br></td>
					<td> <input type="text" name="proQty" value="<%=c.getQuantity()%>"></td>
					<td> 무료배송</td>
					<td><button>수정</button></td>
					<td>
					</form>
					<a href="/cart/delete?proNo=<%=c.getPro_no()%>" class="delete">삭제</a>
						<% sum += c.getPrice() * c.getQuantity(); %>
							<% } %>
					<% } %>	
					</td>
				</tr>
			
	
		</table>
			
	<div>금액 합계: <%= sum %></div>

	<br>
	<hr>
	
	<button type="button" id="btn" onclick="location.href='payment'">결제</button>
	<button type="button" id="btn" onclick="location.href='store'">쇼핑하기</button>
	<button type="button" id="btn" onclick="location.href='/order'">주문하기</button>
	
</div>




<%@include file = "/WEB-INF/views/layout/footer.jsp" %>