<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<!-- <script type="text/javascript"> -->
// $(document).ready(function() {
	
// 	//장바구니버튼 동작
// 	$("#btnCat").click(function() {
// // 		$("form").submit();
// 	});
	
// 	//주문버튼 동작
// 	$("#btnorder").click(function() {
// // 		$("form").submit();
// 	});
	
// });

<!-- </script> -->

<style type="text/css">
#content {
	width: 100%;
}
</style>

<div class="container">

<h3>상품 상세 정보</h3>
<hr>

<div>
<form action="./cart" method="post" >

<table class="store_project_info">
<tr><td class="info">상품번호</td><td><%=session.getAttribute("pro_no") %></td></tr>
<tr><td class="info">상품명</td><td><%=session.getAttribute("name") %></td></tr>
<tr><td class="info">상품이미지</td><td><%=session.getAttribute("img_path") %></td></tr>
<tr><td class="info">상품가격</td><td><%=session.getAttribute("price") %></td></tr>
<tr><td class="info">상품설명</td><td><%=session.getAttribute("description") %></td></tr>
</table>

</form>
</div>

<div class="text-center">	
	<button type="button" id="btncart" onclick="location.href='/cart.jsp'">장바구니</button>
	<button type="button" id="btnorder" onclick="location='/order.jsp'">주문</button>
</div>

<!-- .container -->
</div>





</body>
</html>