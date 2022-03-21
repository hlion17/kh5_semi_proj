<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<!-- login page JS -->
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
	
	//로그인 버튼 클릭 시 회원가입 페이지로 이동한다
	$("#btnJoin").click(function() {
		$(location).attr('href', '/member/join')
	})
	
	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
// 		location.href = "/"
	})
	
	//아이디찾기 버튼 클릭 시 아이디찾기 페이지로 이동
	$("#btnIdFind").click(function() {
		$(location).attr('href', '/member/idfind')
	})
	
	//비밀번호찾기 버튼 클릭 시 아이디찾기 페이지로 이동
	$("#btnPwFind").click(function() {
		$(location).attr('href', '/member/pwfind')
	})
	
})
</script>

<!-- login page CSS -->
<style>
    #section-login {
        width: 100%;
        height: 1000px;
    }
    .login-container {
        display: flex;
        justify-content: center;
        margin-top: 100px;
    }
    .login-container .form-signin{
        width: 400px;
    }
    .login-container .form-signin > *{
        margin: 10px auto;
    }
    .login-container .form-signin > h2{
        text-align: center;
    }
</style>


<%-- 비로그인 상태 --%>
<%	if( session.getAttribute("login") == null ) { %>

<div id="main">
	<div id="section-alone">

		<div id="section-login">
		    <div class="login-container text-center">
		
		    <form class="form-signin" action="./login" method="post">
		        <h2 class="form-signin-heading">로그인</h2>
		
		        <label for="memberid" class="sr-only">아이디</label>
		        <input type="text" id="memberid" class="form-control" name="memberid" placeholder="아이디를 입력해주세요" required autofocus>
		        <label for="memberpw" class="sr-only">패스워드</label>
		        <input type="password" id="memberpw" class="form-control" name="memberpw" placeholder="패스워드를 입력해주세요" required>
		
		
		        <button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="button">로그인</button>
		        <button id="btnJoin" class="btn btn-lg btn-success btn-block" type="button">회원가입</button>
		        
		        <button type="button" id="btnIdFind" class="btn btn-block btn-info">아이디찾기</button>
		        <button type="button" id="btnPwFind" class="btn btn-block btn-info">비밀번호찾기</button>
		    </form>
		
		    </div> <!-- /container -->
		</div>

	</div>
</div>

<%	} %>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>




<!-- 기존 작업 본 백업 -->

<!-- <div class="container"> -->
<!-- <form action="./login" method="post" class="form-horizontal">

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
	<div class="text-center">
		<button type="button" id="btnIdFind" class="btn btn-info">아이디찾기</button>
		<button type="button" id="btnPwFind" class="btn btn-info">비밀번호찾기</button>
	</div>

</form>

</div>.container -->