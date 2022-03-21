<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script type="text/javascript">

//페이지 접속 시 아이디 입력창으로 포커스 이동
$("input").eq(0).focus();



</script>



<div class="container">
<form action="./signout" method="post" class="form-horizontal">

	<div class="form-group text-center">
		<h3>아이디, 비밀번호를 입력해주세요!</h3>
	</div>

	<div class="form-group">
		<label for="memberid" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-6">
			<input type="text" id="memberid" name="memberid" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
		<div class="col-xs-6">
			<input type="text" id="memberpw" name="memberpw" class="form-control" required placeholder="필수 입력 항목입니다">
			<button type="sumbit" class="btn btn-primary" >확인</button> 
		</div>
	</div>
	

</form>
</div>


</body>
</html>

<%@ include file="../layout/footer.jsp" %>