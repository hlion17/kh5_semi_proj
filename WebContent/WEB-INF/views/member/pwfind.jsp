<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %> 

<!-- 부트스트랩 폰트  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<style>

*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div {
	border: none;
}
/* button {
	margin: 5%;
} */
.margin {
	margin: 50%;
}
.margin-top{
	margin: 10%;
}
</style>


<script type="text/javascript">

//페이지 접속 시 아이디 입력창으로 포커스 이동
$("input").eq(0).focus();


//비밀번호 찾기

function FindPw() {
	var memberid = $('#memberid').val();
	var email = $('#email').val();
	var phone = $('#phone').val();
	$.ajax({
		type: 'POST',
		url: '/member/pwfind',
		data: {memberid : memberid, email : email, phone : phone},
		success: function(memberpw) {
			if(memberpw === "") {
				alert("일치하는 회원이 없습니다.");
			} else  {
				alert("회원님의 비밀번호는 "+ memberpw +" 입니다.")
			}
			
		},
		error : function () {
			alert("서버요청실패");
		}
		
	})
}

</script>



<div class="container">
<form action="./pwfind" method="post" class="form-horizontal">

	<div class="margin-top"></div>
	<div class="form-group text-center">
		<h3>비밀번호 찾기</h3><br>
	</div>

	<div class="form-group">
		<label for="memberid" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-8">
			<input type="text" id="memberid" name="memberid" class="form-control" required placeholder="아이디를 입력해주세요.">
		</div>
	</div>
	
	<div class="form-group">
		<label for="email" class="control-label col-xs-2">이메일</label>
		<div class="col-xs-8">
			<input type="text" id="email" name="email" class="form-control" required placeholder="이메일을 입력해주세요.">
		</div>
	</div>
	
	<div class="form-group">
		<label for="phone" class="control-label col-xs-2">전화번호</label>
		<div class="col-xs-8">
			<input type="text" id="phone" name="phone" class="form-control" required placeholder="전화번호를 입력해주세요."><br>
		</div>
	</div>
			<div class="text-center">
			<button type="button" class="btn btn-primary" onclick="FindPw();">확인</button> 
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button> 
			</div>
		<div class="margin"></div>
</form>
</div>
<%@ include file="../layout/footer.jsp" %> 

</body>
</html>