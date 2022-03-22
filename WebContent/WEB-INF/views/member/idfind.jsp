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

//페이지 접속 시 첫 입력창으로 포커스 이동
$("input").eq(0).focus();

//취소 버튼 클릭 시 뒤로가기
$("#btnCancel").click(function() {
	history.go(-1);
//		location.href = "/"
})


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

	<div class="margin-top"></div>
	<div class="form-group text-center">
		<h3>아이디 찾기</h3><br>
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
			<button type="button" class="btn btn-primary" onclick="FindId();">확인</button>
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button> 
			</div>
	<div class="margin"></div>
</form>
</div>

<%@ include file="../layout/footer.jsp" %> 
</body>
</html>