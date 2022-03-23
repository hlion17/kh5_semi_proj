<%@page import="dto.OrderResult"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- header -->
<%@include file="/WEB-INF/views/layout/header.jsp"%>
	
<!-- 부트스트랩 적용  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inspiration&family=Roboto:wght@300&display=swap" rel="stylesheet">

<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Do+Hyeon&family=Inspiration&family=Noto+Sans+KR&family=Roboto:wght@300&display=swap" rel="stylesheet">



<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>


<%
	Product ProductList = (Product) request.getAttribute("viewProduct");
	
	int memberNo = (Integer) session.getAttribute("memberno");
	String email = (String) session.getAttribute("email");
	String address = (String) session.getAttribute("address");
	String phone = (String) session.getAttribute("phone");
	String name = (String) session.getAttribute("membername");
	String zipcode = (String) session.getAttribute("zipcode");
	String pro_name = "";
	int price = 0;	
%>





<style type="text/css">

body { padding: 50px;}

#main {
 	text-align: center;
 	font-family: font-family: 'Noto Sans KR', sans-serif;
 	
/*  	position: relative; padding: 0 0 0 395px; widhth:962px; box-sizing: border-box;  */
} 

/* 썸네일 이미지 위치 */
.thumbnail {
	width: 450px; height: 450px; border: 1px solid #e8e8e8; margin: 20px; 
	float: left; 
}

.itemPrice {
    font-size: 17px;
    text-align: initial;
    margin: 0px 0 0 474px;
    font-family: 'Noto Sans KR', sans-serif;
}
	 
#productInfo {
	text-align: center;
    margin: 0 auto;

	
}

.description {
	text-align: center;
	margin: 0 auto;
    font-size: 43px;
    margin: 50 70 100;
    background: #46ae4f;
    color: white;
}

/* 상세설명 이미지 */
#productInfo {
	height: 170px;
	margin: 0 auto;
	padding: 50px;
}

.itemName {
/* 	height: 50px; */
	font-size: 30px;
    font-size: 30px;
    text-align: initial;
    margin: 0 0 0 474;
	
}

.prc {
	font-size: 10px;
	color: red;
}


/* 상세보기 텍스트 */
h1 {
	padding: 0 50 50px;
}

.btn-cart{
	background: #fff;
	color: #46ae4f;
	border: 1px solid #46ae4f;
	width: 264px;
	height: 54px;
	padding: 18px 0 19px;
}

.btn-pay{
	background: #46ae4f;
	border: 1px solid #46ae4f;
	color: #fff;
	width: 264px;
	height: 54px;
	padding: 18px 0 19px;

}

.free{
    background: #46AE32;
    border-radius: 43%;
    margin: 20;
    color: white;
    font-size: small;
    padding: 3;
}

.prc {
	font-size: 40px;
    color: #46AE32;
	
}
</style>









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
		<img class="thumbnail" alt=""
			src="/resources/img/store/item_<%=ProductList.getPro_no()%>.jpg">

	
	<span>		
		<div id="productInfo">
			
			<div class="itemName"><%=ProductList.getName()%></div><br>
		
			<div class="itemPrice">
<%-- 			상품번호: <%=ProductList.getPro_no()%> --%>
			<small>상품가격:</small> <span class="prc"><%=ProductList.getPrice()%></span> <small>원</small>
			<span class="free">무료배송</span>
			
			<hr>
			<div>판매자: 냉스타그램</div>
			<div>택배사: CJ택배</div>
			<hr>
			<form action="/cart/add" method="post">
				<input type="hidden" name="memberNo" value="<%=memberNo%>">
				<input type="hidden" name="proNo" value="<%=ProductList.getPro_no()%>"> 
				수량: <input type="text" name="proQty" value="1" placeholder="구매할 수량을 입력하세요">개 
				<input type="hidden" name="proPrice" class="proprice" value="<%=ProductList.getPrice()%>"> 
			</div>
				<br><br>
				
			<button id="btn-to-cart" class="btn-cart" type="submit" onclick="addcart()">장바구니</button>		
			</form>
			
			<button id="btn-to-payment" class="btn-pay" type="button" >바로구매</button>
<!-- 			<button id="btn-to-order" type="button">주문하기</button> -->
		</div>
			
			<!-- 리뷰게시판 연결 -->
<%-- 			<%	for(int i=0; i<ProductList.size(); i++) { %> --%>
<%-- 			<button type="button" onclick="location.href='review/list/info?reviewno=' + <%=ProductList.get(i).getReview_no()%>">리뷰게시판</button> --%>
<%-- 			<% } %> --%>

	</span>

	</div>
	
	
	<hr>
	
	<p style="clear:both;" class="desciption">
	<div class="description">상세 설명</div>
	<img class="productInfo"
		src="/resources/img/store/iteminfo_<%=ProductList.getPro_no()%>.jpg">
	<p>
	
	
	
</div>


<script type="text/javascript">
function addcart() {
	if (confirm('장바구니 페이지로 이동하시겠습니까?')) {
		location.href = 'cart';
	} else {
		//장바구니 담기 누르고 취소하면 안담기고 이동됨 -> 담기고 이동안되게끔 해야한다 
	}
};
</script>


<% 
	System.out.println(ProductList.getName());
	System.out.println(ProductList.getPrice());
	System.out.println(email);
	System.out.println(name);
	System.out.println(phone);
	System.out.println(address);
	System.out.println(zipcode);
%>






<!-- 결제모듈  -->
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btn-to-payment").click(function() {
	const c = confirm("결제하시겠습니까?")
	if (!c) return false;
		
	 	var IMP = window.IMP;
		IMP.init('imp37543068');
	
		IMP.request_pay({
	   	pg : 'html5_inicis',
	   	pay_method : 'card',
	   	merchant_uid : 'merchant_' + new Date().getTime(),
	   	name : '<%=ProductList.getName()%>' ,
	   	amount : <%=ProductList.getPrice()%> ,
	   	buyer_email : '<%=email %>',
	    	buyer_name : '<%=name %>',
	    	buyer_tel : '<%=phone %>',
	    	buyer_addr : '<%=address %>',
	    	buyer_postcode : '<%=zipcode %>',
		m_redirect_url : 'http://www.naver.com'
	}, function(rsp) {
	 	console.log(rsp);
	     if ( rsp.success ) {
	     	
	     	var msg = '결제가 완료되었습니다.';
	       msg = '결제가 완료되었습니다.';
	       msg += '\n고유ID : ' + rsp.imp_uid;
	       msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	       msg += '\결제 금액 : ' + rsp.paid_amount;
	       msg += '카드 승인번호 : ' + rsp.apply_num;
	                                        
	       alert(msg);
	       
	       updateStatus(orderNo)
	       } else {
	               	
	       	var msg = '결제에 실패하였습니다.';
	       	msg += '에러 내용 : '  + rsp.error_msg;
	       	
	       }
	       alert(msg);
    });
		    
	});
})
	
</script>




<%@include file="/WEB-INF/views/layout/footer.jsp"%>