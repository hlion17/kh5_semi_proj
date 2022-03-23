<%@page import="dto.SocialMember"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF\views\social\layout\socialHeader.jsp" %>

<%	SocialMember updateBoard = (SocialMember) request.getAttribute("updateBoard"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트 에디터 제거
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	//파일이 없을 경우
	if(<%=updateBoard.getStored_name() != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=updateBoard.getStored_name() == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})
	
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div id="section">
	<h3>프로필 수정</h3>
	<hr>

	<div>
		<form action="/social/profile/update" method="post" enctype="multipart/form-data">
			
		<div>
			<!-- 첨부파일 -->
			<div id="beforeFile">
				<%	if( updateBoard.getStored_name() != null ) { %>
						<img src="<%=request.getContextPath() %>/resources/img/social/<%=updateBoard.getStored_name() %>" 
								alt="" width="400" height="400">
						<br>
						기존 첨부파일: 
						<a href="<%=request.getContextPath() %>/resources/img/social/<%=updateBoard.getStored_name() %>"
						 		download="<%=updateBoard.getOrigin_name() %>">
							<%=updateBoard.getOrigin_name() %>
						</a>
						<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
				<% } else { %>
						<img src="<%=request.getContextPath() %>/resources/img/profileBasic/profile.jpg" alt="" width="100" height="100">
				<% } %>
			</div>
			
			<div id="afterFile">
				새 첨부파일:<input type="file" name="file" accept="image/*" />
			</div>
			
			<div class="text-center">	
				<button type="button" id="btnUpdate" class="btn btn-info">완료</button>
				<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
			</div>
			
		</div>
		
		<div>
			<input type="hidden" name="memberno" value="<%=updateBoard.getMemberno() %>" />
			<table class="table table-bordered">
			<tr><td class="info">회원번호</td><td colspan="3"><%=updateBoard.getMemberno() %></td></tr>
			<tr><td class="info">아이디</td><td colspan="3"><%=updateBoard.getMemberid() %></td></tr>
			<tr><td class="info">이름</td><td colspan="3"><%=updateBoard.getMembername() %></td></tr>
			<tr><td class="info">닉네임</td><td colspan="3"><%=updateBoard.getNick() %></td></tr>
			<tr><td class="info">성별</td><td colspan="3"><%=updateBoard.getGender() %></td></tr>
			<tr><td class="info">이메일</td><td colspan="3"><%=updateBoard.getEmail() %></td></tr>
			<tr><td class="info">연락처</td><td colspan="3"><%=updateBoard.getPhone() %></td></tr>
			<tr><td class="info">우편번호</td><td colspan="3"><%=updateBoard.getZipcode() %></td></tr>
			<tr><td class="info">주소</td><td colspan="3"><%=updateBoard.getAddress() %></td></tr>
			<tr><td class="info">소개글</td><td colspan="3"><%=updateBoard.getIntro() %></td></tr>
			<tr><td class="info">냉장고번호</td><td colspan="3"><%=updateBoard.getMy_ref_code() %></td></tr>
<%-- 				<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="<%=updateBoard.getNick() %>"/></td></tr> --%>
			</table>
		</div>
		
		</form>
	</div>


</div><!-- #section -->


<%@ include file="/WEB-INF\views\social\layout\socialFooter.jsp" %>