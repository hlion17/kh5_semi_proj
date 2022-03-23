<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String alertMsg = (String)request.getAttribute("alertMsg"); %>
<%	String msg = (String)request.getAttribute("alertMsg"); %>
<%	String errorMsg = (String)request.getAttribute("errorMsg"); %>
<%@ include file="../layout/header.jsp" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css");
*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div { 
	border: none !important;
} 
/* button {
	margin: 5%;
} */
.margin {
	margin: 50%;
}
.margin-top{
	margin: 15%;
}
/* .border{
	border: 1px solid #ccc;
} */
 h3:before {
	content: '\F4DB';
	font-family : bootstrap-icons;
} 

</style>

<script type="text/javascript">

//페이지 접속 시 아이디 입력창으로 포커스 이동
$(document).ready(function() {
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();

	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
//		location.href = "/"
	})
})



</script>

<% if (alertMsg != null) { %> 
	<script>alert('${alertMsg}')</script>
<%	request.getSession().invalidate();

%>	
	<script>location.href = "/";</script>
	
<% 	} %>
<% if (errorMsg != null) { %> 
	<script>alert('${errorMsg}')</script>	
<% 	} %>



<div class="container text-center">
<form action="./signout" method="post" class="form-horizontal">

	<div class="margin-top"></div>
	<div class="form-group text-center">
		<h3>&nbsp;회원탈퇴</h3>
	</div>

	<!-- <div class="form-group">
		<label for="memberid" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-8">
			<input type="text" id="memberid" name="memberid" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div> -->
	
	<div class="form-group">
		<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
		<div class="col-xs-8">
			<input type="password" id="memberpw" name="memberpw" class="form-control" required placeholder="비밀번호를 입력하세요."><br><br>
			<button type="sumbit" class="btn btn-primary" >확인</button> 
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button> 
		</div>
	</div>
	<div class="margin"></div>

</form>
</div>


</body>
</html>

<%@ include file="../layout/footer.jsp" %>