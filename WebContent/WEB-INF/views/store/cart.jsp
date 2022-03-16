<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <%
	// 세션의 고유 아이디를 가져온다.
	String cartId = session.getId();
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">장바구니</h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left">
					<a href="deleteCart.jsp?cartId=<%=cartId%>" class="btn btn-danger">
					삭제하기
					</a>
					</td>
					<td align="right">
					<a href="shippingInfo.jsp?cartId=<%=cartId %>" class="btn btn-success">주문하기</a>
					</td>
				</tr>
			</table> 
		</div>
		<div style="padding-top:50px;">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
	<%
		ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
		//out.print("cartList크기: "+ cartList.size());
		if(cartList == null){
			cartList = new ArrayList<Product>();
		}
		int sum = 0; 
		for(int i=0; i<cartList.size(); i++){
			Product product = cartList.get(i);
			// 소계 = 가격 * 수량
			int total = product.getPrice();
// 			int total = product.getUniPrice() * product.getQuantity();
			sum = sum + total;
	%>
				<tr>
<%-- 					<td><%=product.getProductId() %>-<%=product.getPname() %></td> --%>
<%-- 					<td><%=product.getUniPrice() %></td> --%>
<%-- 					<td><%=product.getQuantity() %></td> --%>
					<td><%=product.getPro_no() %>-<%=product.getName() %></td>
					<td><%=product.getPrice() %></td>
					<td><%=total%></td>
					<td>삭제</td>
				</tr>
	<%
		}
	%>			<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><%=sum %></th>
					<th></th>
				</tr>
			</table>
			<a href="products.jsp" class="btn btn-secondary">&raquo; 쇼핑 계속하기</a>
		</div>



</body>
</html>