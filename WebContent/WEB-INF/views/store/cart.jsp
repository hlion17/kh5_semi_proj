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
	font-size: 50px;
	
}

.delete {
	border: 1px solid black;
	background: yellow;
}

.cartinfo {
	
}

#btn{
	background: red;
}


th {
	text-align: center;
}

</style>



<!-- header -->
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

 <div id="main">
		<div class="userhi"><%=session.getAttribute("memberid")%>님의 장바구니 목록</div>
		
		<table class="table-bordered table-condensed" >
				<th width=45%>상품정보</th>
				<th width=20%>상품금액</th>
				<th width=5%>수량</th>
				<th width=10%>배송비</th>
				<th width=20% colspan="2"></th>
<!-- 				<th width=10%></th> -->
				
			</tr>
			
			<!-- 장바구니가 null일 때 확인되는 문구  -->
			<% if (cartList == null) { %> 
			<div>장바구니에 넣은 상품이 없습니다.</div>
			<% } else { %>
			<!-- 장바구니에 상품이 있을 때 확인되는 부분  -->
			<% for (Cart c : cartList) { %> 
			<form action="/cart/update" method="post">
				<tr>
					<input type="hidden" disabled value="<%=c.getCart_no()%>">
					<td><%=c.getName()%></td>
					<% itemName = c.getName(); %>
					
					<input type="hidden" disabled value="<%=c.getMember_no()%>">
					<input type="hidden" name="proNo" value="<%=c.getPro_no()%>">
					<td> <input disabled value="<%=c.getPrice()%>"><br></td>
					<td> <input type="text" name="proQty" value="<%=c.getQuantity()%>"></td>
					<td> 무료배송</td>
					<td><button>수정</button></td>
					<td>
					</form>
					<a href="/cart/delete?proNo=<%=c.getPro_no()%>" class="delete">삭제</a>
						<% sum += c.getPrice() * c.getQuantity(); %>
			<% } %>	
			<% } %>	
					</td>
				</tr>
			
	
		</table>
			
	<div>금액 합계: <%= sum %>원</div>

	<br>
	<hr>
	
<!-- 	<button type="button" id="btn" onclick="location.href='payment'">결제</button> -->
	<button type="button" id="btn-payment" >결제</button>
	<button type="button" id="btn" onclick="location.href='store'">쇼핑하기</button>
	<button type="button" id="btn" onclick="location.href='/order'">주문하기</button>
	
</div>


<script type="text/javascript">
//결제 모듈
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