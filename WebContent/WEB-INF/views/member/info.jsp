
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> 
 

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <%-- 카카오 우편번호 API --%> 

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

<%@page import="dto.Member"%>

<% Member m = (Member) request.getAttribute("result"); %>

<script>
function kakaopost() { //우편번호 검색 함수 with 카카오
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#zipcode").value = data.zonecode; //우편번호
           document.querySelector("#address").value =  data.address //주소
        }
    }).open();
}
</script> 
<script type="text/javascript" >
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





<div class="container">

<form action="/member/updateinfo" method="post" class="form-horizontal">

	<div class="margin-top"></div>
	<div class="form-group text-center">
		<h3>&nbsp;회원 정보 수정</h3><br>
	</div>
	<tr>
		<div class="form-group text-center">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				<td><%-- <%=session.getAttribute("memberpw") %> --%>
			<div class="col-xs-8">
				<input type="text" name="memberpw" class="form-control" value="<%=m.getMemberpw() %>"></td>
			</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
				<td><%-- <%=session.getAttribute("membername") %> --%></td>
			<div class="col-xs-8">
				<input type="text" name="membername" class="form-control" value="<%=m.getMembername() %>"></td>
			</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
<%-- 				<td><%=session.getAttribute("nick") %></td>
--%>			<div class="col-xs-8">
 				<input type="text" name="nick" class="form-control" value="<%=m.getNick() %>"></td>
 			</div>
		</div>
	</tr>
	<%-- <tr>
		<div class="form-group">
			<label for="gender" class="control-label col-xs-2">성별</label>
				<td><%=session.getAttribute("gender") %></td>
			<div class="col-xs-8">
				<input type="text" name="gender" class="form-control" value="<%=session.getAttribute("gender") %>"></td>
			</div>
		</div>
	</tr> --%>
	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">이메일</label>
<%-- 				<td><%=session.getAttribute("email") %></td>
<--%>			<div class="col-xs-8">
				<input type="text" name="email" class="form-control" value="<%=m.getEmail() %>"></td>
			</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
<%-- 				<td><%=session.getAttribute("phone") %></td>
--%>			<div class="col-xs-8">
 				<input type="text" name="phone" class="form-control" value="<%=m.getPhone() %>"></td>
 				</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
<%-- 				<td><%=session.getAttribute("zipcode") %></td>
--%>			<div class="col-xs-8">
 				<input type="text" id="zipcode" name="zipcode" class="form-control" value="<%=m.getZipcode() %>"></td>
 				<input type="button" value="우편번호찾기" onclick="kakaopost()">
 				</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
<%-- 				<td><%=session.getAttribute("address") %></td>
--%>			<div class="col-xs-8">
 				<input type="text" id="address" name="address" class="form-control" value="<%=m.getAddress() %>"></td>
 				</div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
<%-- 				<td><%=session.getAttribute("intro") %></td>
--%>			<div class="col-xs-8">
 				<input type="text" name="intro" class="form-control" value="<%=m.getIntro() %>"></td><br><br>
 				</div>
		</div>
	</tr> 

   
   <div class="text-center">
      <button type="submit" id="btnUpdateInfo" class="btn btn-info">수정완료</button>
      <button type="button" id="btnCancel" class="btn btn-danger" >취소</button> 
   </div>
   <div class="margin"></div>

</form>

</div><!-- .container -->
<%@ include file="../layout/footer.jsp" %> 

	


<!-- <div id="btnBox" class="pull-left"> float: left; 와 같음(왼쪽에 버튼 떠다니게)
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> btn-primary -> 파란색 버튼
</div> -->



	
</body>
</html>