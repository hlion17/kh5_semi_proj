<%@page import="dto.SocialMember"%>
<%@page import="dto.ProfileFile"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	SocialMember viewBoard = (SocialMember) request.getAttribute("viewBoard"); %>
<%-- <%	ProfileFile profileFile = (ProfileFile) request.getAttribute("profileFile"); %> --%>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/social/profile/update?memberno=<%=viewBoard.getMemberno() %>");
	})
	
});
</script>

<div id="section">
	<h1>프로필</h1>
	
	<!-- 첨부파일 -->
	<div>
	<%	if( viewBoard != null ) { %>

		<img src="<%=request.getContextPath() %>/upload/<%=viewBoard.getStored_name() %>" 
				alt="그림을 불러오지못함" width="100%"><br>
		<a href="<%=request.getContextPath() %>/upload/<%=viewBoard.getStored_name() %>"
				download="<%=viewBoard.getOrigin_name() %>">
			<%=viewBoard.getOrigin_name() %>
		</a>

<%-- 		<img src="<%=request.getContextPath() %>/upload/<%=profileFile.getStoredname() %>"  --%>
<!-- 				alt="그림을 불러오지못함" width="400" height="300"><br> -->
<%-- 		<a href="<%=request.getContextPath() %>/upload/<%=profileFile.getStoredname() %>" --%>
<%-- 				download="<%=profileFile.getOriginname() %>"> --%>
<%-- 			<%=profileFile.getOriginname() %> --%>
<!-- 		</a> -->
	
	<%	} %>
	</div>
	
	<div>
		<table class="table table-bordered">
			<tr><td class="info">회원번호</td><td colspan="3"><%=viewBoard.getMemberno() %></td></tr>
			<tr><td class="info">아이디</td><td colspan="3"><%=viewBoard.getMemberid() %></td></tr>
			<tr><td class="info">이름</td><td colspan="3"><%=viewBoard.getMembername() %></td></tr>
			<tr><td class="info">닉네임</td><td colspan="3"><%=viewBoard.getNick() %></td></tr>
			<tr><td class="info">성별</td><td colspan="3"><%=viewBoard.getGender() %></td></tr>
			<tr><td class="info">이메일</td><td colspan="3"><%=viewBoard.getEmail() %></td></tr>
			<tr><td class="info">연락처</td><td colspan="3"><%=viewBoard.getPhone() %></td></tr>
			<tr><td class="info">우편번호</td><td colspan="3"><%=viewBoard.getZipcode() %></td></tr>
			<tr><td class="info">주소</td><td colspan="3"><%=viewBoard.getAddress() %></td></tr>
			<tr><td class="info">소개글</td><td colspan="3"><%=viewBoard.getIntro() %></td></tr>
			<tr><td class="info">냉장고번호</td><td colspan="3"><%=viewBoard.getMy_ref_code() %></td></tr>
		</table>
	</div>
	
	<div class="text-center">
	<% if( viewBoard.getMemberno() == Integer.parseInt(request.getSession().getAttribute("memberno").toString()) ) { %>
		<button id="btnUpdate" class="btn btn-info">수정</button>
	<% } %>
	</div>
	
</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>