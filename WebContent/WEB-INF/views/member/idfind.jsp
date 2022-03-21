<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %> 

<script type="text/javascript">

//페이지 접속 시 첫 입력창으로 포커스 이동
$("input").eq(0).focus();


//아이디 찾기

function FindId() {
	var email = $('#email').val();
	var phone = $('#phone').val();
	$.ajax({
		type: 'POST',
		url: '/member/idfind',
		data: {email : email, phone : phone},
		success: function(memberid) {
			if(memberid === "") {
				alert("일치하는 회원이 없습니다.");
			} else  {
				alert("회원님의 아이디는 "+ memberid +" 입니다.")
			}
			
		},
		error : function () {
			alert("서버요청실패");
		}
		
	})
}

</script>



<div class="container">
<form action="./idfind" method="post" class="form-horizontal">

	<div class="form-group text-center">
		<h3>가입하실 때 적었던 정보를 입력해주세요!</h3>
	</div>

	<div class="form-group">
		<label for="email" class="control-label col-xs-2">이메일</label>
		<div class="col-xs-6">
			<input type="text" id="email" name="email" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="phone" class="control-label col-xs-2">전화번호</label>
		<div class="col-xs-6">
			<input type="text" id="phone" name="phone" class="form-control" required placeholder="필수 입력 항목입니다">
			<button type="button" class="btn btn-primary" onclick="FindId();">확인</button> 
		</div>
	</div>
</form>
</div>

<%@ include file="../layout/footer.jsp" %> 
</body>
</html>