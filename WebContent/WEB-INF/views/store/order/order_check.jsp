<%@page import="dto.OrderResult"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<OrderResult> list = (List<OrderResult>) request.getAttribute("list");
	// 결제 정보에 필요한 데이터 로드
	String email = (String) session.getAttribute("email");
	String address = (String) session.getAttribute("address");
	String phone = (String) session.getAttribute("phone");
	String name = (String) session.getAttribute("membername");
	String zipcode = (String) session.getAttribute("zipcode");
	
	
%>

<!-- header -->
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	$(".btn-order-cancle").click(function() {
		const orderNo = $(this).attr("data-orderNo")
		
		location.href = "/order/delete?orderNo=" + orderNo
	})
	
	// 결제 모듈
	$(".btn-pay").click(function() {
		const total = $(this).attr("data-total")
		const proName = $(this).attr("data-proName")
		const orderNo = $(this).attr("data-orderNo")
		console.log(orderNo)
		
	 	var IMP = window.IMP;
		IMP.init('imp37543068');

  		IMP.request_pay({
       	pg : 'html5_inicis',
       	pay_method : 'card',
       	merchant_uid : 'merchant_' + new Date().getTime(),
       	name : proName,
       	amount : total,
       	buyer_email : '<%= email %>',
        	buyer_name : '<%= name %>',
        	buyer_tel : '<%= phone %>',
        	buyer_addr : '<%= address %>',
        	buyer_postcode : '<%= zipcode %>',
		m_redirect_url : 'http://www.naver.com'
    }, function(rsp) {
     	console.log(rsp);
         if ( rsp.success ) {
         	
         	var msg = '결제가 완료되었습니다.';
           msg = '결제가 완료되었습니다.';
//            msg += '\n고유ID : ' + rsp.imp_uid;
//            msg += '\n상점 거래ID : ' + rsp.merchant_uid;
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
	
	function updateStatus(orderNo) {
		location.href = "/order/update?orderNo=" + orderNo
	}
	
})


</script>

<div id="main">
	<div id="section-alone">
		<h1>주문확인 페이지</h1>
		<hr>
		<div>
		<% for (OrderResult o : list) { %>
		상품: <%=o.getProName() %><br>
		주문일: <%=o.getOrderDate() %><br>
		배송지: <%=o.getAddress() %><br>
		연락번호: <%=o.getPhone() %><br>
		수취인: <%=o.getReceiver() %><br>
		주문상태: <%=o.getStatus() %><br>
		주문 금액: <%=o.getTotal()%><br>
		<% String proName = o.getProName();%>
		<hr>
		
			<% if (!"결제완료".equals(o.getStatus())) { %>
			<button class="btn-order-cancle" data-orderNo="<%=o.getOrderNo()%>">주문 취소</button><br>
			<% } %>
			
			<% if ("주문됨".equals(o.getStatus())) { %>
			<button class="btn-pay" data-total="<%=o.getTotal()%>" data-proName="<%=proName%>" data-orderNo="<%=o.getOrderNo()%>">결제하기</button><br>
			<% } %>
		<% } %>
		</div>
		
	</div>
</div>


<script>

</script>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>