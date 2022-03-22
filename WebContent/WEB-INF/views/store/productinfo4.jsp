<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Product ProductList = (Product) request.getAttribute("viewProduct");
	int memberNo = (Integer) session.getAttribute("memberno");
%>


<style type="text/css">

body { padding: 50px;}

 #main {
 	text-align: center;
/*  	position: relative; padding: 0 0 0 395px; widhth:962px; box-sizing: border-box;  */
} 

.thumbnail {
	width: 370px; height: 370px; border: 1px solid #e8e8e8; margin: 30px; 
	float: left; 
}
	 
#productInfo {
	margin: 30px;
	
}

.desciption {
	text-align: center;
}

/* 상세설명 이미지 */
.productInfo {
	width: 1170px;
	margin: 0 auto;
	
}


</style>



<!-- header -->
<%@include file="/WEB-INF/views/layout/header.jsp"%>

<script>
$(document).ready(function() {
	$("#btn-to-order").click(function() {
		const proQty = $("input[name=proQty]").val()
		
		location.href = "/order?proNo=<%=ProductList.getPro_no()%>&proQty=" + proQty
	})
})
</script>





<%-- 		<%=session.getAttribute("memberid")%>님 반갑습니다<br>  --%>
<div id="main">
	<div class="container">
		<img class="thumbnail" alt="onion"
			src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg">
			
			
		<div id="productInfo">
			<h2><span class="itemName"><%=ProductList.getName()%></span></h2>
			<br>

		<h5>상품번호: <%=ProductList.getPro_no()%></h5><br>
		<h5>상품가격: <%=ProductList.getPrice()%>원</h5>
		

			<form action="/cart/add" method="post">
				<input type="hidden" name="memberNo" value="<%=memberNo%>">
				<input type="hidden" name="proNo" value="<%=ProductList.getPro_no()%>"> 
					수량: <input type="text" name="proQty"> 
					<input type="hidden" name="proPrice" value="<%=ProductList.getPrice()%>"> <br>
				<button id="btn-to-cart" type="submit">장바구니에 담기</button>		
			</form>
			<button id="btn-to-order" type="button">주문하기</button>
			<button type="button" onclick="payment()">결제</button>
		</div>
			
			<!-- 리뷰게시판 연결 -->
<%-- 			<%	for(int i=0; i<ProductList.size(); i++) { %> --%>
<%-- 			<button type="button" onclick="location.href='review/list/info?reviewno=' + <%=ProductList.get(i).getReview_no()%>">리뷰게시판</button> --%>
<%-- 			<% } %> --%>

	</div>
	
	<p style="clear:both;" class="desciption">
	<h1 background="yellow">상세 설명</h1>
	<img class="productInfo"
		src="/resources/img/store/iteminfo_<%=ProductList.getPro_no()%>.jpg">
	<p>

</div>





<script type="text/javascript">
	function payment() {
		if (confirm('해당 상품을 주문 하시겠습니까?')) {
			location.href = 'payment';
		} else {
			document.addFrom.reset();
		}
	}
</script>



<%@include file="/WEB-INF/views/layout/footer.jsp"%>