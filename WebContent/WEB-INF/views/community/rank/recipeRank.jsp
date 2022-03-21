<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<Recipe> boardList = (List) request.getAttribute("boardList"); %>
<%-- <%	RecipeFile boardFile = (RecipeFile) request.getAttribute("boardFile"); %> --%>

<div id="section">

	<h1>레시피 랭킹</h1>
	<hr>
	
	<div>
		<table class="table table-striped table-hover table-condensed">
			<tr class="success">
				<th>랭킹</th>
			<!-- 	<th>이미지</th> -->
				<th>레시피 이름</th>
				<th>조회수</th>
				<th>추천수</th>
				<th>작성일</th>
			<!-- 		<th>소개글</th> -->
			<!-- 	<th>게시글 번호</th>	 -->
			</tr>
			
			<%	for(int i=0; i<boardList.size(); i++) { %>
			<tr>
				<td><%=i+1 %> 위</td>
			<%-- 	<td><%	if( boardFile != null ) { %> --%>
			<%-- 			<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"	 --%>
			<%-- 			download="<%=boardFile.getOriginname() %>">	<%=boardFile.getOriginname() %></a> --%>
			<%-- 		<%	} %></td> --%>
				<td><a href="<%=request.getContextPath() %>/recipe/content?boardno=<%=boardList.get(i).getBoardno() %>"><%=boardList.get(i).getTitle() %></a></td>
				<td><%=boardList.get(i).getHit() %></td>
				<td><%=boardList.get(i).getLike() %></td>
				<td><%=boardList.get(i).getWriteDate() %></td>
			<%-- 		<td><%=boardList.get(i).getIntro() %></td> --%>
			<%-- 	<td><%=boardList.get(i).getBoardno() %></td> --%>
			</tr>
			<%	} %>
		</table>
	</div>

<%@ include file="/WEB-INF/views/community/board/paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>