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
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inspiration&family=Noto+Sans+KR&family=Roboto:wght@300&display=swap" rel="stylesheet">

<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>


<%
	Product ProductList = (Product) request.getAttribute("viewProduct");
	int memberNo = (Integer) session.getAttribute("memberno");
%>




<%
// 	List<OrderResult> list = (List<OrderResult>) request.getAttribute("list");
	// 결제 정보에 필요한 데이터 로드
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
/*  	position: relative; padding: 0 0 0 395px; widhth:962px; box-sizing: border-box;  */
} 

/* 썸네일 이미지 위치 */
.thumbnail {
	width: 450px; height: 450px; border: 1px solid #e8e8e8; margin: 20px; 
	float: left; 
}
	 
#productInfo {
	margin:  0 auto;
	padding : center;
	
}

.desciption {
	text-align: center;
}

/* 상세설명 이미지 */
#productInfo {
	height: 170px;
	margin: 0 auto;
	font-family: 'Noto Sans KR', sans-serif;
}

.itemName {
/* 	height: 50px; */
	font-size: 30px;
	text-align: center;
	
}



#btn-to-order, #btn-to-payment, #btn-to-cart {
	border: 1px solid black;
 }

.btn:hover{
	background: red;
	
}


/* 상세보기 텍스트 */
h1 {
	padding: 0 50 50px;
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
			
			
		<div id="productInfo">
			
			<span class="itemName"><%=ProductList.getName()%></span><br>
			<span>
<%-- 			상품번호: <%=ProductList.getPro_no()%> --%>
			상품가격: <%=ProductList.getPrice()%>원
			</span>
			
			<form action="/cart/add" method="post">
				<input type="hidden" name="memberNo" value="<%=memberNo%>">
				<input type="hidden" name="proNo" value="<%=ProductList.getPro_no()%>"> 
				수량: <input type="text" name="proQty" value="1" placeholder="수량을 입력하세요">개 
				<input type="hidden" name="proPrice" class="proprice" value="<%=ProductList.getPrice()%>"> 
				
				<br><br>
				
				<button id="btn-to-cart" class="btn btn-outline-info" type="submit" onclick="addcart()">장바구니에 담기</button>		
			</form>
			
			<br>
<!-- 			<button id="btn-to-order" type="button">주문하기</button> -->
			<button id="btn-to-payment" type="button" >결제</button>
		</div>
			
			<!-- 리뷰게시판 연결 -->
<%-- 			<%	for(int i=0; i<ProductList.size(); i++) { %> --%>
<%-- 			<button type="button" onclick="location.href='review/list/info?reviewno=' + <%=ProductList.get(i).getReview_no()%>">리뷰게시판</button> --%>
<%-- 			<% } %> --%>

	</div>
	
	<hr>
	
	<p style="clear:both;" class="desciption">
	<h1 background="yellow">상세 설명</h1>
	<img class="productInfo"
		src="/resources/img/store/iteminfo_<%=ProductList.getPro_no()%>.jpg">
	<p>

</div>



<script type="text/javascript">
	function addcart() {
		if (confirm('장바구니 페이지로 이동하시겠습니까?')) {
// 			console.log("true")
			location.href = 'cart';
		} else {
// 			console.log("false")
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


<script type="text/javascript">
//결제 모듈
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