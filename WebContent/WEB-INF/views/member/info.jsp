<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	//회원정보수정 버튼 클릭 시 회원정보수정 페이지로 이동
	$("#btnUpdateInfo").click(function() {
		$(location).attr('href', '/member/updateinfo')
	})
})	
</script>
<div>
		<button type="button" id="btnUpdateInfo" class="btn btn-info">회원정보 수정</button>
</div>



	
</body>
</html>