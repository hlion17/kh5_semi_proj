<%@page import="dao.face.CartDao"%>
<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 부트스트랩 적용  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inspiration&family=Roboto:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

 <!-- 글꼴 라이브러리  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>


<% 
	List<Cart> cartList = (List<Cart>) request.getAttribute("list");
	int sum = 0;
%>

<%
	String email = (String) session.getAttribute("email");
	String address = (String) session.getAttribute("address");
	String phone = (String) session.getAttribute("phone");
	String name = (String) session.getAttribute("membername");
	String zipcode = (String) session.getAttribute("zipcode");
	String itemName = "";
%>





<style type="text/css">

.userhi {
    text-align: center;
    font-size: 30px;
    margin: 70px;
    font-family: 'Black Han Sans', sans-serif;
}


.delete {
	width: 51px;
    height: 32px;
    background: red;
    -webkit-line-break: after-white-space;
    padding: 3px 7px;
    border: 1px solid black;
  	color: white;
}

.table {
	margin: 100px;
}

#btn{
	background: red;
}


.container {
	font-family: 'Noto Sans KR', sans-serif;
	
}
.btns {
	text-align: center;

}

.img {
    width: 253px;
    height: 160px;
    overflow: auto;
}

input {
	width: 150px;
}

.itemName{
	font-size : 19px;
}

th {
	background: #77af9c;
	font-size: 20px;
    text-align-last: center;
	    
}

table {
    width: 100%;
    border-top: 1px solid #444444;
  }
th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
    text-align: center;
  }
  
.tr {
	color: white;
	
}

/* 상품금액, 수량 input태그 칸 안보이기 */
input {
    cursor: default;
    border: none;
    background: white;
    width: 56px;
    margin: 0 auto;
    text-align: center;
}

.proQty{
	border: 1px solid black;
	margin: 2px;
	
}


.total {
	font-size: 50px
}


.pr{
	font-size: 30;
	border: 1px solid black;
	text-align-last: center;
/* 	background: #77af9c; */
	font-family: 'Noto Sans KR', sans-serif;
	
	
}

.price, .totalPrice {
	font-size: 40px;
}

.totalPrice {
	color: red;
}

.btns {
	text-align: center;
}
#btn-payment{
	background: #fff;
	color: #77af9c;
	border: 1px solid #77af9c;
	width: 264px;
	height: 54px;
	padding: 18px 0 19px;
	margin: 10px
}

#btn-shopping{
	background: #77af9c;
	border: 1px solid #77af9c;
	color: #fff;
	width: 264px;
	height: 54px;
	padding: 18px 0 19px;
	margin: 10px

}


</style>


<!-- header -->
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

 <div class="main">
 
 	<div class="userhi">
 		<i class="bi bi-heart"></i>
		<span><%=session.getAttribute("memberid")%>의 장바구니</span>
		<i class="bi bi-heart"></i>
	</div>
	
	
	<div class="container">
	<table class="class" >
		
			<tr class="tr">
				<th width=55% colspan="2">상품정보</th>
				<th width=20%>상품금액</th>
				<th width=5%>수량</th>
				<th width=10%>배송비</th>
				<th width=10%>수정/삭제</th>
			</tr>
			
			<!-- <tr> -->
				<!-- 장바구니가 null일 때 확인되는 문구  -->
			<% if (cartList == null) { %> 
			<div>장바구니에 넣은 상품이 없습니다.</div>
			
			<% } else { %>
			
			<!-- 장바구니에 상품이 있을 때 확인되는 부분  -->
			<% for (Cart c : cartList) { %> 
			<tr>
				<form action="/cart/update" method="post">
					<input type="hidden" disabled value="<%=c.getCart_no()%>">
				<td>
					<img class="img" alt=""src="/resources/img/store/item_<%=c.getPro_no()%>.jpg">
				</td>
				<td class="itemName">
					<%=c.getName()%>
				</td>
					<% itemName = c.getName(); %>
					<input type="hidden" disabled value="<%=c.getMember_no()%>">
					<input type="hidden" name="proNo" value="<%=c.getPro_no()%>">
				<td>
					<input disabled value="<%=c.getPrice()%>">원
				</td>
				<td>
					<input type="text" name="proQty" class="proQty" value="<%=c.getQuantity()%>">
				</td>
				<td>무료배송</td>
					
				<td>
					<button>수정</button>
					<a href="/cart/delete?proNo=<%=c.getPro_no()%>" class="delete">삭제</a>
				</td>
				</form>
				
				<% sum += c.getPrice() * c.getQuantity(); %>
			</tr>	
			<% } %>
			<% } %>	
	<!-- 			</td>
			</tr> -->
				
		</table>


	<hr>
	
	<div class="pr">
	총 상품가격 <span class="price"><%= sum %>원</span>
	    +    총 배송비 0  원     =    
	총 주문금액 : <span class="totalPrice"> <%= sum %>원</span>
	
	</div>
	<br>
	
	<div class="btns">
<!-- 	<button type="button" id="btn" onclick="location.href='payment'">결제</button> -->
	<button type="button" id="btn-payment" >결제</button>
	<button type="button" id="btn-shopping" onclick="location.href='store'">쇼핑하기</button>
	</div>
	
	
	</div>

</div>




<!-- 아이포트 결제 모듈 -->
<script type="text/javascript">
$(document).ready(function() {
		
		$("#btn-payment").click(function() {
		const c = confirm("결제하시겠습니까?")
		if (!c) return false;
			
	 	var IMP = window.IMP;
		IMP.init('imp37543068');
	
			IMP.request_pay({
	   	pg : 'html5_inicis',
	   	pay_method : 'card',
	   	merchant_uid : 'merchant_' + new Date().getTime(),
	   	name : '<%=itemName%>' ,
	   	amount : <%=sum%> ,
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
// 	       msg += '\n고유ID : ' + rsp.imp_uid;
// 	       msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	       msg += '\n결제 금액 : ' + rsp.paid_amount;
	       msg += '\n카드 승인번호 : ' + rsp.apply_num;
	                                        
	       alert(msg);
	       location.href="/"
	       
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


<%@include file = "/WEB-INF/views/layout/footer.jsp" %>