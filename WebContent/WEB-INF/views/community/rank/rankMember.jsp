<%@page import="dto.RankMember"%>
<%@page import="dto.Member"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<RankMember> boardList = (List) request.getAttribute("boardList"); %>

<div id="section">

	<h1>유저 랭킹</h1>
	<hr>
	
	<table class="table table-striped table-hover table-condensed">
	<tr class="success">
		<th>랭킹</th>
		<th>팔로우수</th>
		<th>이미지</th>
		<th>아이디</th>
		<th>닉네임</th>
		<th>성별</th>
		<th>이메일</th>
		<th>연락처</th>
<!-- 		<th>레시피수</th> -->
		<th>소개글</th>
	</tr>
	
	<%	for(int i=0; i<boardList.size(); i++) { %>
	<tr>
		<td><%=i+1 %> 위</td>
		<td><%=boardList.get(i).getFollowCnt() %></td>
		<td>
	<!-- 첨부파일 -->
	<%	if( boardList != null ) { %>
		<img src="<%=request.getContextPath() %>/upload/<%=boardList.get(i).getStored_name() %>" 
				alt="그림을 불러오지못함" width="100%" height="100%"><br>
		<a href="<%=request.getContextPath() %>/upload/<%=boardList.get(i).getStored_name() %>"
				download="<%=boardList.get(i).getOrigin_name() %>">
			<%=boardList.get(i).getOrigin_name() %>
		</a>
	<%	} %>
		</td>
<%-- 		<td><a href="<%=request.getContextPath() %>/recipe/content?boardno=<%=boardList.get(i).getMemberno() %>"><%=boardList.get(i).getTitle() %></a></td> --%>
		<td><%=boardList.get(i).getMemberid() %></td>
		<td><%=boardList.get(i).getNick() %></td>
		<td><%=boardList.get(i).getGender() %></td>
		<td><%=boardList.get(i).getEmail() %></td>
		<td><%=boardList.get(i).getPhone() %></td>
<%-- 		<td><%=boardList.get(i).get() %></td> --%>
		<td><%=boardList.get(i).getIntro() %></td>
	</tr>
	
	<%	} %>

</table>

<%@ include file="/WEB-INF/views/community/rank/m_paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>