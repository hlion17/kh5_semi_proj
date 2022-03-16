<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% List<Cart> cartList = (List)request.getAttribute("cartList"); %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class="table table-hover">
	<tr>
		<th>장바구니번호</th>
		<th>회원번호</th>
		<th>상품번호</th>
		<th>상품수량</th>
		<th>가격</th>
	</tr>

	<%	for(int i=0; i<cartList.size(); i++) { %>
	<tr>
		<td><%=cartList.get(i).getCart_no() %></td>
		<td><%=cartList.get(i).getMember_no() %></td>
		<td><%=cartList.get(i).getPro_no() %></td>
		<td><%=cartList.get(i).getQuantity() %></td>
		<td><%=cartList.get(i).getPrice() %></td>
	</tr>
	<%	} %>
	
	</table>


<button type="button" onclick="location.href='payment'">결제</button>
<button type="button" onclick="location.href='store'">쇼핑하기</button>

</body>
</html>