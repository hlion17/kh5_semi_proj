<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%	Product ProductList = (Product) request.getAttribute("viewProduct"); %>

<div class="container">

<h3>상품 상세 정보</h3>
<hr>

<div>
<form action="./cart" method="post" >


<tr><td class="info">상품번호</td><td><%=ProductList.getPro_no() %></td></tr><br>
<tr><td class="info">상품명</td><td><%=ProductList.getName() %></td></tr><br>
<tr><td class="info">상품이미지</td><td><%=ProductList.getImg_path() %></td></tr><br>
<tr><td class="info">상품가격</td><td><%=ProductList.getPrice() %>원</td></tr><br>
<tr><td class="info">상품설명</td><td><%=ProductList.getDescription() %></td></tr><br>


</form>
</div>

<div>
	<button type="button" onclick="location.href='cart'">장바구니</button>
	<button type="button" onclick="payment()">결제</button>
</div>


</div>


 <script type="text/javascript"> 
  	function payment(){
	if(confirm('해당 상품을 주문 하시겠습니까?')){
		location.href='payment';
} else {
			document.addFrom.reset();
 		}
 	}
 	  </script> 


</body>
</html>