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
<%=session.getAttribute("id") %>님 반갑습니다<br>
<%out.println(session.getAttribute("id")); %>



<div>

<tr><td class="info">상품번호</td><td><%="ProductList.getPro_no() %></td></tr><br>
<tr><td class="info">상품명</td><td><%=ProductList.getName() %></td></tr><br>
<tr><td class="info">상품이미지</td><td><%=ProductList.getImg_path() %></td></tr><br>
<tr><td class="info">상품가격</td><td><%=ProductList.getPrice() %>원</td></tr><br>
<tr><td class="info">상품설명</td><td><%=ProductList.getDescription() %></td></tr><br>

</div>




<div>
<!-- 	<button type="button" onclick="location.href='test'">장바구니</button> -->
	<button type="button" action=add.jsp">장바구니</button>
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



<%@include file = "/WEB-INF/views/layout/footer.jsp" %>