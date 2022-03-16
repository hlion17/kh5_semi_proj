<!-- jQuery 2.2.4 -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script> -->

<!-- Bootstrap 3 -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->

<%@page import="dto.Recipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<%	List<Recipe> boardList = (List) request.getAttribute("boardList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/write";
	});
	
});
</script>

<div class="container">

<h1>레시피 자랑 게시판</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="success">
	<th>게시글 번호</th>
	<th>게시글 제목</th>
	<th>글쓴이</th>
	<th>등록일</th>
	<th>추천수</th>
	<th>조회수</th>
</tr>

<%	for(int i=0; i<boardList.size(); i++) { %>
<tr>
	<td><%=boardList.get(i).getBoardno() %></td>
	<td><a href="./view?boardno=<%=boardList.get(i).getBoardno() %>"><%=boardList.get(i).getTitle() %></a></td>
	<td><%=boardList.get(i).getUserid() %></td>
	<td><%=boardList.get(i).getWriteDate() %></td>
	<td><%=boardList.get(i).getLike() %></td>
	<td><%=boardList.get(i).getHit() %></td>
</tr>
<%	} %>

</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left">
	<button id="btnWrite" class="btn btn-primary">글쓰기</button>
</div>

</div><!-- .container -->

<%@ include file="/WEB-INF/views/community/board/paging.jsp" %>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>





















