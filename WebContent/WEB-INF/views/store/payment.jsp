<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>

<p>
<button id="check_module" type="button">test</button>
</p>
    
<script type="text/javascript">
	$("#check_module").click(function() {
// 		var IMP = window.IMP;
		IMP.init('imp37543068');
	
   		IMP.request_pay({
        	pg : 'html5_inicis',
        	pay_method : 'card',
        	merchant_uid : 'merchant_' + new Date().getTime(),
        	name : '사과',
        	amount : 100,
        	buyer_email : 'dss2@naver.com',
         	buyer_name : 'hee',
         	buyer_tel : '010-000-0000' ,
         	buyer_addr : '서울시',
         	buyer_postcode : '123-456',
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
            } else {
                    	
            	var msg = '결제에 실패하였습니다.';
            	msg += '에러 내용 : '  + rsp.error_msg;
            	
            }
            alert(msg);

    });
    
});
</script>
 
</body>
</html>

