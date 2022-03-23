<%@page import="dto.SocialMember"%>
<%@page import="dto.ProfileFile"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>

<%@ include file="/WEB-INF\views\social\layout\socialHeader.jsp" %>

<%	SocialMember viewBoard = (SocialMember) request.getAttribute("viewBoard"); %>
<%-- <%	ProfileFile profileFile = (ProfileFile) request.getAttribute("profileFile"); %> --%>

<script type="text/javascript">
$(document).ready(function() {

	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/social/profile/update?memberno=<%=viewBoard.getMemberno() %>");
	})

	//팔로우버튼
	$("#btnFollow").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/social/profile/follow?memberno=<%=viewBoard.getMemberno() %>");
	})
	
});
</script>

<div id="section">
	<h1>마이 프로필</h1>
	
	<!-- 첨부파일 -->
	<div>
	
		<div>
			<%	if( viewBoard != null ) { %>
				<%	if( viewBoard.getStored_name() != null ) { %>
					<img src="<%=request.getContextPath() %>/resources/img/social/<%=viewBoard.getStored_name() %>" alt="" width="100%" height="100%"><br>
					<a href="<%=request.getContextPath() %>/resources/img/social/<%=viewBoard.getStored_name() %>" download="<%=viewBoard.getOrigin_name() %>">
						<%=viewBoard.getOrigin_name() %>
					</a>
				<% } else { %>
					<img src="<%=request.getContextPath() %>/resources/img/profileBasic/profile.jpg" alt="" width="100%" height="100%">
				<% } %>
			<%	} %>
		</div>
		
		<div class="text-center">
			<% 	if( viewBoard.getMemberno() == Integer.parseInt(request.getSession().getAttribute("memberno").toString()) ) { %>
					<button id="btnUpdate" class="btn btn-info">이미지 수정</button>
			<% 	} else { %>
					<button id="btnFollow" class="btn btn-info">팔로우</button>
			<% 	} %>
		</div>
		
	</div>
	
	<div>
		<%	if( viewBoard != null ) { %>
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
		<% 	} %>
	</div>
	
</div><!-- #section -->

<%@ include file="/WEB-INF\views\social\layout\socialFooter.jsp" %>