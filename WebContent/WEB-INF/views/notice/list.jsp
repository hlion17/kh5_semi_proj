
<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	List<Notice> boardList = (List) request.getAttribute("boardList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/notice/write";
	});
	
});
</script>

<div class="container">

<h1>공지사항</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="success">
	<th>글번호</th>
	<th>제목</th>
	<th>아이디</th>
	<th>조회수</th>
	<th>작성일</th>
</tr>

<%	for(int i=0; i<boardList.size(); i++) { %>
<tr>
	<td><%=boardList.get(i).getBoardno() %></td>
	<td><a href="./view?boardno=<%=boardList.get(i).getBoardno() %>"><%=boardList.get(i).getTitle() %></a></td>
	<td><%=boardList.get(i).getMemberid() %></td>
	<td><%=boardList.get(i).getHit() %></td>
	<td><%=boardList.get(i).getWriteDate() %></td>
</tr>
<%	} %>

</table>
<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left"> <!-- float: left; 와 같음(왼쪽에 버튼 떠다니게) -->
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> <!-- btn-primary -> 파란색 버튼 -->
</div>
 
</div><!-- .container -->

<%@ include file="./layout/paging.jsp" %>

<%@ include file="../layout/footer.jsp" %>






















