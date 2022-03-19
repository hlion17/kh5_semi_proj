
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">


//회원정보수정 버튼 클릭 시 회원정보수정 페이지로 이동
$("#btnUpdateInfo").click(function() {
	$(location).attr('href', '/member/info')
})
</script>





<div class="container">

<form action="./infoView" method="post" class="form-horizontal">

	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				<td><%=session.getAttribute("memberpw") %>
				<input type="hidden" name="memberpw" value="<%=session.getAttribute("memberpw") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
				<td><%=session.getAttribute("membername") %></td>
				<input type="hidden" name="membername" value="<%=session.getAttribute("membername") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
				<td><%=session.getAttribute("nick") %></td>
				<input type="hidden" name="nick" value="<%=session.getAttribute("nick") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="gender" class="control-label col-xs-2">성별</label>
				<td><%=session.getAttribute("gender") %></td>
				<input type="hidden" name="gender" value="<%=session.getAttribute("gender") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">이메일</label>
				<td><%=session.getAttribute("email") %></td>
				<input type="hidden" name="email" value="<%=session.getAttribute("email") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
				<td><%=session.getAttribute("phone") %></td>
				<input type="hidden" name="phone" value="<%=session.getAttribute("phone") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
				<td><%=session.getAttribute("zipcode") %></td>
				<input type="hidden" name="zipcode" value="<%=session.getAttribute("zipcode") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
				<td><%=session.getAttribute("address") %></td>
				<input type="hidden" name="address" value="<%=session.getAttribute("address") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
				<td><%=session.getAttribute("intro") %></td>
				<input type="hidden" name="intro" value="<%=session.getAttribute("intro") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr> 

	
	<div class="text-center">
		<button type="submit" id="btnUpdateInfo" class="btn btn-info">회원정보수정</button>
	</div>

</form>

</div><!-- .container -->


	


<!-- <div id="btnBox" class="pull-left"> float: left; 와 같음(왼쪽에 버튼 떠다니게)
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> btn-primary -> 파란색 버튼
</div> -->



	
</body>
</html>