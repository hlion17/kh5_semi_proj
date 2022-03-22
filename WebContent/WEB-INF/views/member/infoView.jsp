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

<style>

*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div { 
	border: none !important
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

<%@ include file="../layout/header.jsp" %> 

<% if (errorMsg != null) { %> 
	<script>alert('${errorMsg}')</script>	
<% 	} %>
<% if (alertMsg != null) { %> 
	<script>alert('${alertMsg}')</script>
<%	} %>




<div class="container text-center">

<form action="/member/info" method="post" class="form-horizontal">

	<tr>
		<div class="margin-top"></div>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				<%=m.getMemberpw() %> 
				<input type="hidden" name="memberpw" value="<%=session.getAttribute("memberpw") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
				<%=m.getMembername() %> 
				<input type="hidden" name="membername" value="<%=session.getAttribute("membername") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
	
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
				<%=m.getNick() %>
				<input type="hidden" name="nick" value="<%=session.getAttribute("nick") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>

	<%-- <tr>
		<div class="form-group">
			<label for="gender" class="control-label col-xs-2">성별</label>
				<td><%=m.getGender() %></td>
				<input type="hidden" name="gender" value="<%=session.getAttribute("gender") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr> --%>

	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">이메일</label>
				<%=m.getEmail() %> 
				<input type="hidden" name="email" value="<%=session.getAttribute("email") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
				<%=m.getPhone() %>
				<input type="hidden" name="phone" value="<%=session.getAttribute("phone") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
				<%=m.getZipcode() %> 
				<input type="hidden" name="zipcode" value="<%=session.getAttribute("zipcode") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
				<%=m.getAddress() %>
				<input type="hidden" name="address" value="<%=session.getAttribute("address") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
				<%=m.getIntro() %>
				<input type="hidden" name="intro" value="<%=session.getAttribute("intro") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>

	
	<div class="text-center">
		<button type="submit" id="btnUpdateInfo" class="btn btn-info">회원정보수정</button>
		<button type="button" id="btnSignout" onclick="location.href='/member/signout'" class="btn btn-info">회원탈퇴</button>
		<button type="button" id="btnMain" onclick="location.href='/main'" class="btn btn-info">메인으로</button>
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