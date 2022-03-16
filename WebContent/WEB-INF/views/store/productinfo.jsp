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
<!-- <form name="form2" method="post" action="addProduct.jsp"> -->

<tr><td class="info">상품번호</td><td><%=ProductList.getPro_no() %></td></tr><br>
<tr><td class="info">상품명</td><td><%=ProductList.getName() %></td></tr><br>
<tr><td class="info">상품이미지</td><td><%=ProductList.getImg_path() %></td></tr><br>
<tr><td class="info">상품가격</td><td><%=ProductList.getPrice() %>원</td></tr><br>
<tr><td class="info">상품설명</td><td><%=ProductList.getDescription() %></td></tr><br>
<!-- </table> -->


<!-- <table class="table table-bordered"> -->
<!-- <tr> -->
<%-- <td class="info">글번호</td><td><%=ProductList.getPro_no() %></td> --%>
<%-- <td class="info">제목</td><td colspan="3"><%=viewProduct.getName() %></td> --%>
<%-- <td class="info">아이디</td><td><%=viewProduct.getImg_path( %></td> --%>
<%-- <td class="info">닉네임</td><td><%=request.getAttribute("pro_no") %></td> --%>
<%-- <td class="info">조회수</td><td><%=viewProduct.getPrice() %></td> --%>
<%-- <td class="info">조회수</td><td><%=viewProduct.getDescription() %></td> --%>
<!-- </tr> -->
<!-- </table> -->





</form>
</div>

<div>
	<button type="button" onclick="location.href='cart'">장바구니</button>
	<button type="button" onclick="location.href='payment'">결제</button>
</div>

<%-- <p><form name="addFrom" action="./Cart.jsp?id=<%=ProductgetProductNo() %>"method="post"> --%>
<!-- 	<a href="#" class="btn btn-order" onclick="order()"> 상품 주문&raquo;</a> -->
<!-- 	<a href="./cart.jsp" class="btn btn-cart">장바구니&raquo;</a> -->
<!-- 	<a href="./list.jsp" class="btn btn-list">상품목록&raquo;</a> -->
<!-- </form> -->


<!-- 
	request.setCharacterEncoding("UTF-8");
	String product = request.getParameter("product");
	ArrayList list = (ArrayList)session.getAttribute("productlist");
	if(list==null) list = new ArrayList();
	list.add(product);
	session.setAttribute("productlist", list);
	out.println(product + "이(가) 추가 되었습니다.");
-->

<!-- .container -->
</div>


<!-- <script type="text/javascript"> 
  	function addToCart(){
	if(confirm('해당 상품을 장바구니에 추가하시겠습니까?')){
		document.addForm.submit();
} else {
			document.addFrom.reset();
 		}
 	}
 	  </script> -->


</body>
</html>