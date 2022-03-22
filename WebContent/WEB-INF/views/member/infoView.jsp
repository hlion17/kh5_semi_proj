<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Member"%>
<% Member m = (Member) request.getAttribute("result"); %>
<%	String alertMsg = (String)request.getAttribute("alertMsg"); %>
<%	String errorMsg = (String)request.getAttribute("errorMsg"); %>
    
<!-- 부트스트랩 폰트  -->
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
.border{
	border: 1px solid #ccc;
}
 h3:before {
	content: '\F4DB';
	font-family : bootstrap-icons;
}  
</style>

<%@ include file="../layout/header.jsp" %> 

<% if (errorMsg != null) { %> 
	<script>alert('${errorMsg}')</script>	
<% 	} %>
<% if (alertMsg != null) { %> 
	<script>alert('${alertMsg}')</script>
<%	} %>




<div class="container text-center">
<div class="border">

<form action="/member/info" method="post" class="form-horizontal">
		<div class="margin-top"></div>
	<div class="form-group text-center">
		<h3>&nbsp;회원 정보</h3><br>
	</div>
	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
			<div class="col-xs-8">
				<td><%=m.getMemberpw() %> 
				<input type="hidden" name="memberpw" class="form-control" value="<%=session.getAttribute("memberpw") %>"></td>
		</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
			<div class="col-xs-8">
				<td><%=m.getMembername() %> 
				<input type="hidden" name="membername" class="form-control" value="<%=session.getAttribute("membername") %>"></td>
		</div>
		</div>
	</tr>
	<tr>
	
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
			<div class="col-xs-8">
				<td><%=m.getNick() %>
				<input type="hidden" name="nick"class="form-control"  value="<%=session.getAttribute("nick") %>"></td>
		</div>
		</div>
	</tr>

	<%-- <tr>
		<div class="form-group">
			<label for="gender" class="control-label col-xs-2">성별</label>
				<td><%=m.getGender() %></td>
			<div class="col-xs-8">
				<input type="hidden" name="gender"class="form-control"  value="<%=session.getAttribute("gender") %>"></td>
		</div>
		</div>
	</tr> --%>

	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">이메일</label>
			<div class="col-xs-8">
				<td><%=m.getEmail() %> 
				<input type="hidden" name="email" class="form-control"  value="<%=session.getAttribute("email") %>"></td>
		</div> 
		</div> 
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
			<div class="col-xs-8">
				<td><%=m.getPhone() %>
				<input type="hidden" name="phone" class="form-control" value="<%=session.getAttribute("phone") %>"></td>
		</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
			<div class="col-xs-8">
				<td><%=m.getZipcode() %> 
				<input type="hidden" name="zipcode" class="form-control" value="<%=session.getAttribute("zipcode") %>"></td>
		</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
			<div class="col-xs-8">
				<td><%=m.getAddress() %>
				<input type="hidden" name="address" class="form-control" value="<%=session.getAttribute("address") %>"></td>
		</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
			<div class="col-xs-8">
				<td><%=m.getIntro() %>
				<input type="hidden" name="intro" class="form-control" value="<%=session.getAttribute("intro") %>"></td><br><br><br>
		</div>
		</div>
	</tr>

	
	<div class="text-center">
		<button type="submit" id="btnUpdateInfo" class="btn btn-info">회원정보수정</button>
		<button type="button" id="btnSignout" onclick="location.href='/member/signout'" class="btn btn-danger">회원탈퇴</button>
		<button type="button" id="btnMain" onclick="location.href='/main'" class="btn btn-info">메인으로</button>
	</div>
	</div>
	<div class="margin"></div>

</form>

</div><!-- .container -->

	


<!-- <div id="btnBox" class="pull-left"> float: left; 와 같음(왼쪽에 버튼 떠다니게)
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> btn-primary -> 파란색 버튼
</div> -->

<%@ include file="../layout/footer.jsp" %> 


	
</body>
</html>