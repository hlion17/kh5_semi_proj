<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터키 입력 시 submit하도록 한다
	$("input").eq(1).keydown(function( e ) {
		if( e.keyCode == 13 ) { //엔터키
			$(this).parents("form").submit();
		}
	});

	//로그인 버튼 클릭 시 submit하도록 한다
	$("#btnLogin").click(function() {
		$(this).parents("form").submit();
	})
	
	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
// 		location.href = "/"
	})
	
})
</script>

<style type="text/css">
form {
	width: 600px;
	margin: 0 auto;
}
</style>

<div class="container">

<form action="./login" method="post" class="form-horizontal">

	<div class="form-group">
		<label for="userid" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-10">
			<input type="text" id="memberid" name="memberid" class="form-control">
		</div>
	</div>

	<div class="form-group">
		<label for="userpw" class="control-label col-xs-2">패스워드</label>
		<div class="col-xs-10">
			<input type="text" id="memberpw" name="memberpw" class="form-control">
		</div>
	</div>
	
	<div class="text-center">
		<button type="button" id="btnLogin" class="btn btn-primary">로그인</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</form>

</div><!-- .container -->