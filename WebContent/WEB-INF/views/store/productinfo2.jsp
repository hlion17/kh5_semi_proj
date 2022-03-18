<!-- /productList -->

<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<%	Product ProductList = (Product) request.getAttribute("viewProduct"); %>

<div class="container">

<h3>상품 상세 정보</h3>
<hr>

<div>

<tr><td class="info">상품번호</td><td><%=ProductList.getPro_no() %></td></tr><br>
<tr><td class="info">상품명</td><td><%=ProductList.getName() %></td></tr><br>
<tr><td class="info">상품이미지</td><td><%=ProductList.getImg_path() %></td></tr><br>
<tr><td class="info">상품가격</td><td><%=ProductList.getPrice() %>원</td></tr><br>
<tr><td class="info">상품설명</td><td><%=ProductList.getDescription() %></td></tr><br>

</div>

<div>
<!-- 	<button type="button" onclick="location.href='cart'">장바구니</button> -->
	<button type="button" onclick="payment()">결제</button>
</div>


</div>


<script type="text/javascript">

	function addToCart(){
		// 확인 true 취소 false
		if(confirm("상품을 장바구니에 추가하시겠습니까?")){ // 확인
			document.addForm.submit();
		}else{ // 취소
			document.addForm.reset();
		}
	}
</script>

<p>
	<form name ="addForm" method="post" action="[test]addCart.jsp?id=${product.productId}">
	<a href="#" class="btn btn-info" onclick="addToCart()">
	상품주문 &raquo;</a>
	<a href="cart.jsp" class="btn btn-warning">
        장바구니&raquo;</a>
	<a href="products.jsp" class="btn btn-secondary">
        상품목록&raquo;</a>	
	</form>
</p>



<%@include file = "/WEB-INF/views/layout/footer.jsp" %>