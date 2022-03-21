<%@page import="dto.Member"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<Member> boardList = (List) request.getAttribute("boardList"); %>

<div id="section">

	<h1>유저 랭킹</h1>
	<hr>
	
	<table class="table table-striped table-hover table-condensed">
	<tr class="success">
		<th>랭킹</th>
	<!-- 	<th>이미지</th> -->
		<th>닉네임</th>
<!-- 		<th>레시피수</th> -->
<!-- 		<th>팔로우수</th> -->
		<th>소개글</th>
	</tr>
	
	<%	for(int i=0; i<boardList.size(); i++) { %>
<!-- 	<tr> -->
		<td><%=i+1 %> 위</td>
		
	<%-- 	<td><%	if( boardFile != null ) { %> --%>
	<%-- 			<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"	 --%>
	<%-- 			download="<%=boardFile.getOriginname() %>">	<%=boardFile.getOriginname() %></a> --%>
	<%-- 		<%	} %></td> --%>
<%-- 		<td><a href="<%=request.getContextPath() %>/recipe/content?boardno=<%=boardList.get(i).getBoardno() %>"><%=boardList.get(i).getTitle() %></a></td> --%>

		<td><%=boardList.get(i).getNick() %></td>
<%-- 		<td><%=boardList.get(i).get() %></td> --%>
<%-- 		<td><%=boardList.get(i).get() %></td> --%>
		<td><%=boardList.get(i).getIntro() %></td>
	</tr>
	
	<%	} %>

</table>

<%@ include file="/WEB-INF/views/community/rank/paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>