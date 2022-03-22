<%@page import="dto.Member"%>
<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	List<Notice> boardList = (List) request.getAttribute("boardList"); %>
<%	Member m = (Member)request.getAttribute("result"); %>

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
		location.href="/notice/write";
	});
	
});
</script>

<div class="container">
<div class="margin-top"></div>

<h1>&nbsp;공지사항</h1>
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

<% if(("admin").equals(session.getAttribute("memberid"))) { %>
<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left"> <!-- float: left; 와 같음(왼쪽에 버튼 떠다니게) -->
	<button id="btnWrite" class="btn btn-primary">&nbsp;글쓰기</button> <!-- btn-primary -> 파란색 버튼 -->
</div>
<div class="margin"></div>
 
</div><!-- .container -->
<%	} else { %>

<% 	} %>
<%@ include file="./layout/paging.jsp" %>

<%@ include file="../layout/footer.jsp" %>






















