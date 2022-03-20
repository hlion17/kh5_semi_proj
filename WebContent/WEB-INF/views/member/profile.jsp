<%@page import="dto.Member"%>
<% Member m = (Member) request.getAttribute("result"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<script>
//회원정보수정 버튼 클릭 시 회원정보수정 페이지로 이동
 $("#btnUpdateProfile").click(function() {
	$(location).attr('href', '/member/profile')
})  

</script> 

<style>

.btn-file {
	position: relative;
	overflow: hideen;
}
.btn-file input[type=file] {
	position: absolute;;
	top: 0;
	right: 0;
	mid-width: 100%;
	mid-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity=0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
	
}

</style>



<div class="container">

<form action="/member/profile" method="post" class="form-horizontal" enctype="multipart/form-data">

	<%-- <tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
				 <td><%=m.getMemberpw() %> </td>
				<input type="hidden" name="memberpw" value="<%=session.getAttribute("memberpw") %>"></td>
			<div class="col-xs-10"></div>
		</div>
	</tr>
--%>
	<tr>
		<div class="form-group">
			<label for="memberpw" class="control-label col-xs-2"></label>
				 <span class="btn btn-default btn-file">
				 	이미지를 업로드하세요.<input type="file" name="userProfile">
				 </span>
			<div class="col-xs-10"></div>
		</div>
	</tr>


	
	<div class="text-center">
		<button type="submit" id="btnUpdateProfile" class="btn btn-info">등록</button>
		<a href="/main">홈으로</a>
	</div>

</form>

</div>
</div><!-- .container -->


	


<!-- <div id="btnBox" class="pull-left"> float: left; 와 같음(왼쪽에 버튼 떠다니게)
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> btn-primary -> 파란색 버튼
</div> -->



	
</body>
</html>

<%@ include file="../layout/footer.jsp" %>