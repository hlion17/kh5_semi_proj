<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/layout/header.jsp"%>


<%
	Product ProductList = (Product) request.getAttribute("viewProduct");
int memberNo = (Integer) session.getAttribute("memberno");
%>


<style type="text/css">
p {
	border: 1px solid #ccc;
}

.thumbnail {
	width: 300px;
	height: 300px;
	/*  	float: left;  */
	margin: 20px;
}


.productInfo {
	justify-content: center;
}

</style>



<div id="main">
	<div class="container">

		<h3>상품 상세 정보</h3>
		<hr>
		<a href="/cart">내 장바구니 보기</a><br>
		<%=session.getAttribute("memberid")%>님 반갑습니다<br> <img
			class="thumbnail" alt="onion"
			src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg">

		<%-- 상품번호: <%=ProductList.getPro_no()%><br> --%>
		<h3>
			<span>상품명: <%=ProductList.getName()%></span><br>
		</h3>
		<span>상품가격: <%=ProductList.getPrice()%>원
		</span>

			<form action="/cart/add" method="post">
				<input type="hidden" name="memberNo" value="<%=memberNo%>">
				<input type="hidden" name="proNo"
					value="<%=ProductList.getPro_no()%>"> 수량: <input
					type="text" name="proQty"> <input type="hidden"
					name="proPrice" value="<%=ProductList.getPrice()%>"> <br>
				<button id="btn-to-cart" type="submit">장바구니에 담기</button>
			</form>
			<button type="button" onclick="payment()">결제</button>
			<button type="button" onclick="location.href='review/list'">리뷰게시판</button>
			
		</span>


		<div></div>
			<h1>상세 설명</h1>
			<img class="productInfo"
				src="/resources/img/store/iteminfo_<%=ProductList.getPro_no()%>.jpg"><br>
		</div>

	</div>
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