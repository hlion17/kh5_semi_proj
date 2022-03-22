<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/views/layout/header.jsp"%> --%>


<%
	Product ProductList = (Product) request.getAttribute("viewProduct");
int memberNo = (Integer) session.getAttribute("memberno");
%>

<script>
$(document).ready(function() {
	$("#btn-to-order").click(function() {
		const proQty = $("input[name=proQty]").val()
		
		location.href = "/order?proNo=<%=ProductList.getPro_no()%>&proQty=" + proQty
	})
})
</script>


<!-- <style type="text/css"> -->

/* body{ padding: 50px;} */

/* .product_view { position: relative; padding: 0 0 0 395px; widhth:962px; box-sizing: border-box;} */
/* .product_view .img { position: absolute; left: 0; top:0;} */
/* .product_view .img > img {width: 368px; height: 370px; border: 1px solid #e8e8e8;} */
/* .product_view .img li:after { content:""; display: block; clear: both;} */
/* .product_view .img li { float: left; padding: 10px 10px 0 0 ;} */
/* .product_view .img li.on img {border-color: #0a56a9;} */
/* .product_view .img li img ( width: 68px; height: 68px; border: 1px solid #e8e8e8;} */
/* .product_view h2 {margin : 0 0 15px;  padding: 0 0 20px; border-bottom: 2px solid #333; font-size:24px; color:#232323; */
/* 					line-height: 26px;} */
/* .product_view table th { font-size:15px; color:#444; text-align: left;} */
/* .product_view table td ( font-size:14px; color:#444;) */
/* .product_view table td.price { font-size: 22px; } */

/* .product_view .btns { padding: 45px 0 0 ; text-align: center; }  */
/* .product_view .btns > a { display: inline-block; width: 136px; height: 42px; text-align: center; font-size: 16px; */
/* 	color:#fff; border-radius: 2px; line-height: 42px;} */
/* .product_view .btns > a.btn1 { background: #666; } */
/* .product_view .btns > a.btn2 {background: #0a56a9;} */
	
 
<!-- </style> -->



<h3>상품 상세 정보</h3>
<hr>

	<div class="product_view">
		<h2><%=ProductList.getName()%></h2>
		<table>
			<caption>
				<details class="hide">
					<summary>상품정보</summary>
				</details>
			</caption>
			<colgroup>
			<col style="width:173px;">
			<col>
			</colgroup>
			





			<tbody>
			<tr>
				<th>판매가</th>
				<td class="price"><%=ProductList.getPrice()%>원</td>
			</tr>
			<tr>
				<th>상품코드</th>
				<td><%=ProductList.getPro_no()%></td>
			</tr>
			<tr>
				<th>구매수량</th>
				<td>
					<input type="text" name="proQty"> 
					<input type="hidden" name="proPrice" value="<%=ProductList.getPrice()%>">	
				</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td>무료배송</td>
			</tr>
		</tbody>
		
	</table>
	<div class="img">
		<img src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg" alt="">
		<ul>
			<li class="on"><img src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg" alt=""></li>
			<li><img src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg" alt=""></li>
		</ul>
	</div>
	<div class="btns">
		<a class="btn1"><button id="btn-to-cart" type="submit">장바구니</button></a>
		<a class="btn2"><button id="btn-to-order" type="button">구매하기</button></a>
	</div>
	
</div>
						
							
<!-- <h1>상세 설명</h1> -->
<!-- <img class="productInfo" -->
<%-- 	src="/resources/img/store/iteminfo_<%=ProductList.getPro_no()%>.jpg"><br> --%>






<script type="text/javascript">
	function payment() {
		if (confirm('해당 상품을 주문 하시겠습니까?')) {
			location.href = 'payment';
		} else {
			document.addFrom.reset();
		}
	}
</script>



<%-- <%@include file="/WEB-INF/views/layout/footer.jsp"%> --%>