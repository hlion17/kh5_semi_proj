<%@page import="dto.Member"%>
<% Member m = (Member) request.getAttribute("result"); %>
<%	String alertMsg = (String)request.getAttribute("alertMsg"); %>
<%	String errorMsg = (String)request.getAttribute("errorMsg"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> 

<% if (errorMsg != null) { %> 
	<script>alert('${errorMsg}')</script>	
<% 	} %>
<% if (alertMsg != null) { %> 
	<script>alert('${alertMsg}')</script>
<%	} %>




<div class="container">

<form action="/member/info" method="post" class="form-horizontal">

	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				 <td><%=m.getMemberpw() %> </td>
				<input type="hidden" name="memberpw" value="<%=session.getAttribute("memberpw") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="membername" class="control-label col-xs-2">이름</label>
				<td><%=m.getMembername() %> </td>
				<input type="hidden" name="membername" value="<%=session.getAttribute("membername") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="nick" class="control-label col-xs-2">닉네임</label>
				<td><%=m.getNick() %></td>
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
				<td><%=m.getEmail() %> </td>
				<input type="hidden" name="email" value="<%=session.getAttribute("email") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="phone" class="control-label col-xs-2">전화번호</label>
				<td><%=m.getPhone() %></td>
				<input type="hidden" name="phone" value="<%=session.getAttribute("phone") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="zipcode" class="control-label col-xs-2">우편번호</label>
				<td><%=m.getZipcode() %> </td>
				<input type="hidden" name="zipcode" value="<%=session.getAttribute("zipcode") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="address" class="control-label col-xs-2">주소</label>
				<td><%=m.getAddress() %></td>
				<input type="hidden" name="address" value="<%=session.getAttribute("address") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
	<tr>
		<div class="form-group">
			<label for="intro" class="control-label col-xs-2">자기소개</label>
				<td><%=m.getIntro() %></td>
				<input type="hidden" name="intro" value="<%=session.getAttribute("intro") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr> 

	
	<div class="text-center">
		<button type="submit" id="btnUpdateInfo" class="btn btn-info">회원정보수정</button>
		<button type="button" id="btnSignout" onclick="location.href='/member/signout'" class="btn btn-info">회원탈퇴</button>
		<button type="button" id="btnMain" onclick="location.href='/main'" class="btn btn-info">메인으로</button>
	</div>

</form>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %> 
	


<!-- <div id="btnBox" class="pull-left"> float: left; 와 같음(왼쪽에 버튼 떠다니게)
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> btn-primary -> 파란색 버튼
</div> -->



	
</body>
</html>