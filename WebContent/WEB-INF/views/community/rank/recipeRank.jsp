<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<Recipe> boardList = (List) request.getAttribute("boardList"); %>

<div id="section">
<div class="margin-top"></div>

	<h1>&nbsp;레시피 랭킹</h1>
	<hr>
	
	<div>
		<table class="table table-striped table-hover table-condensed">
			<%	if( boardList != null ) { %>
				<tr class="info">
					<th>랭킹</th>
					<th>레시피 이름</th>
					<th>조회수</th>
					<th>추천수</th>
					<th>작성일</th>
				</tr>
				
				<%	for(int i=0; i<boardList.size(); i++) { %>
				<tr onclick="location.href='<%=request.getContextPath() %>/recipe/content?boardno=<%=boardList.get(i).getBoardno()%>'">
					<td><%=i+1 %> 위</td>
					<td><%=boardList.get(i).getTitle() %></td>
					<td><%=boardList.get(i).getHit() %></td>
					<td><%=boardList.get(i).getLike() %></td>
					<td><%=boardList.get(i).getWriteDate() %></td>
				</tr>
				<%	} %>
			<%	} %>
		</table>
	</div>
	<div class="margin"></div>

<%@ include file="/WEB-INF/views/community/rank/r_paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>