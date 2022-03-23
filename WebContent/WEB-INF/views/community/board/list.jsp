<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	List<Recipe> boardList = (List) request.getAttribute("boardList"); %>

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
	content: '\F484';
	font-family : bootstrap-icons;
}
button:before {
	content: '\F4CB';
	font-family : bootstrap-icons;
} 
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/recipe/insert";
	});
	
});
</script>


<div id="section">

	<h1>레시피 자랑 게시판</h1>
	<hr>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<tr class="success">
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>추천수</th>
				<th>조회수</th>
			</tr>
			
			<%	for(int i=0; i<boardList.size(); i++) { %>
			<tr onclick="location.href='<%=request.getContextPath() %>/recipe/content?boardno=<%=boardList.get(i).getBoardno() %>'">
				<td><%=boardList.get(i).getBoardno() %></td>
				<td><%=boardList.get(i).getTitle() %></a></td>
				<td><%=boardList.get(i).getNick() %></td>
				<td><%=boardList.get(i).getWriteDate() %></td>
				<td><%=boardList.get(i).getLike() %></td>
				<td><%=boardList.get(i).getHit() %></td>
			</tr>
			<%	} %>
		
		</table>
	</div>
	
	<!-- 글쓰기 버튼 -->
	<div id="btnBox" class="pull-left">
		<button id="btnWrite" class="btn btn-primary">글쓰기</button>
	</div>
	
<%@ include file="/WEB-INF/views/community/board/paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>