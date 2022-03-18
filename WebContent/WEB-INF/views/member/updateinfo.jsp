<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <%-- 카카오 우편번호 API --%> 

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
//페이지 접속 시 첫 입력창으로 포커스 이동
$("input").eq(0).focus();




</script>





<div class="container">

<form action="./updateinfo" method="post" class="form-horizontal">

	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				<%--  <td><%=session.getAttribute("memberpw") %>  --%>
				<input type="text" name="memberpw" id="memberpw" class="form-control"   value="<%=session.getAttribute("memberpw") %>" > ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
				<%--  <td><%=session.getAttribute("membername") %></td>  --%>
				<input type="text" name="membername" id="membername" class="form-control"   value="<%=session.getAttribute("membername") %>"  ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
				 <%-- <td><%=session.getAttribute("nick") %></td> --%> 
				<input type="text" name="nick" id="nick" class="form-control"   value="<%=session.getAttribute("nick") %>"  ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="email" class="control-label col-xs-2">이메일</label>
				 <%-- <td><%=session.getAttribute("email") %></td> --%> 
				<input type="text" name="email" id="email" class="form-control"   value="<%=session.getAttribute("email") %>"  ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
				<%--  <td><%=session.getAttribute("phone") %></td>  --%>
				<input type="text" name="phone" id="phone" class="form-control"   value="<%=session.getAttribute("phone") %>"  ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
				<%--  <td><%=session.getAttribute("zipcode") %></td> --%> 
				<input type="text" name="zipcode" id="zipcode" class="form-control"  value="<%=session.getAttribute("zipcode") %>" ></td>
				<input type="button" value="우편번호찾기" onclick="kakaopost()">
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
				 <%-- <td><%=session.getAttribute("address") %></td> --%> 
				<input type="text" name="address" id="address" class="form-control"  value="<%=session.getAttribute("address") %>" ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
				 <%-- <td><%=session.getAttribute("intro") %></td>  --%>
				<input type="text" name="intro" id="intro" class="form-control"  value="<%=session.getAttribute("intro") %>"  ></td>
			<div class="col-xs-10"></div>
		</div>
	</tr> 

	
	<div class="text-center">
		<button type="submit" id="btnUpdateInfo" class="btn btn-primary" onclick="UpdateInfo();">수정 완료</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</form>

</div><!-- .container -->

