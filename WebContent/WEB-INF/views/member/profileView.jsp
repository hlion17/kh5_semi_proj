<%@page import="dto.ProfileFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	ProfileFile viewProfile = (ProfileFile) request.getAttribute("viewProfile"); %>

<%@ include file="../layout/header.jsp" %>

<script>
//회원정보수정 버튼 클릭 시 회원정보수정 페이지로 이동
  $("#btnUpdateProfile").click(function() {
	$(location).attr('href', '/member/profile')
}) 
</script>

<div>
<%	if( viewProfile != null ) { %>
	<a href="<%=request.getContextPath() %>/upload/<%=viewProfile.getStoredname() %>"
	 download="<%=viewProfile.getOriginname() %>">
		<%=viewProfile.getOriginname() %>
	</a>
<%	} %>
</div>
<div class="text-align: center">
<%	if( viewProfile == null ) { %>
 	<img src="/resources/img/profileBasic/profile.jpg" class="img-responsive center-block"
		 alt="프로필 이미지를 불러오지 못했습니다." width="20%" height="20%"> 
<%	} %>
</div>
	<!-- 첨부파일 -->
<div>
<%	if( viewProfile != null ) { %>
	<img src="<%=request.getContextPath() %>/upload/<%=viewProfile.getStoredname() %>" 
			 alt="그림을 불러오지못함" width="100%" height="100%"><br>
	<a href="<%=request.getContextPath() %>/upload/<%=viewProfile.getStoredname() %>"
			download="<%=viewProfile.getOriginname() %>">
		<%=viewProfile.getOriginname() %>
	</a>
<%	} %>
</div>


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
		<div class="form-group text-center";>
			<label for="memberpw" class="control-label col-xs-2"></label>
				 <span class="btn btn-default btn-file">
				 	이미지를 업로드하세요.<input type="file" name="userProfile" class="">
				 </span>
			<div class="col-xs-10"></div>
		</div>
	</tr> 


	
	<div class="text-center">
		 <button type="button" id="btnUpdateProfile" class="btn btn-info">등록</button> 
		<a href="/main">홈으로</a>
	</div>

</form>

</div>
</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>