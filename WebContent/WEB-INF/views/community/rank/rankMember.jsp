<%@page import="dto.RankMember"%>
<%@page import="dto.Member"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<RankMember> boardList = (List) request.getAttribute("boardList"); %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css");
*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div { 
	border: none !important;
} 
/* button {
	margin: 5%;
} */
.margin {
	margin: 5%;
}
.margin-top{
	margin: 5%;
}
/* .border{
	border: 1px solid #ccc;
} */
  h1:before {
	content: '\F5E7';
	font-family : bootstrap-icons;
}
table, tr, th, td {
	text-align: center;
	vertical-align: text-bottom;
}

</style>

<div id="section">
<div class="margin-top"></div>
	<h1>&nbsp;유저 랭킹</h1>
	<hr>
	
	<div>
		<table class="table table-striped table-hover table-condensed">
		<tr class="info">
			<th>랭킹</th>
			<th>팔로우수</th>
			<th>이미지</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>성별</th>
		</tr>
		
		<%	for(int i=0; i<boardList.size(); i++) { %>
			<%	if( boardList != null ) { %>
			<tr onclick="location.href='<%=request.getContextPath() %>/social/member?memberno=<%=boardList.get(i).getMemberno() %>'">
				<td><%=boardList.get(i).getDense_rank() %> 위</td>
				<td><%=boardList.get(i).getFollowCnt() %></td>
				<td>
			<!-- 첨부파일 -->
				<% if( boardList.get(i).getImage_no() != 0 ) { %>
					<img src="<%=request.getContextPath() %>/resources/img/social/<%=boardList.get(i).getStored_name() %>" class="img-circle" alt="" width="100" height="100"><br>
				<% } else { %>
					<img src="<%=request.getContextPath() %>/resources/img/profileBasic/profile.jpg" class="img-circle" alt="" width="100" height="100">
				<% } %>
				</td>
				<td><%=boardList.get(i).getMemberid() %></td>
				<td><%=boardList.get(i).getNick() %></td>
				<td><%=boardList.get(i).getGender() %></td>
			</tr>
			<%	} %>
		<%	} %>
		</table>
	</div>
	<div class="margin"></div>

<%@ include file="/WEB-INF/views/community/rank/m_paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>