<%@page import="dto.NoticeFile"%>
<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	Notice viewBoard = (Notice) request.getAttribute("viewBoard"); %>
<%	NoticeFile boardFile = (NoticeFile) request.getAttribute("boardFile"); %>

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
#btnList:before {
	content: '\F479';
	font-family : bootstrap-icons;
}  
#btnUpdate:before {
	content: '\F4CA';
	font-family : bootstrap-icons;
}  
#btnDelete:before {
	content: '\F5DE';
	font-family : bootstrap-icons;
}  

</style>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/notice/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/notice/update?boardno=<%=viewBoard.getBoardno() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/notice/delete?boardno=<%=viewBoard.getBoardno() %>");
		}
	})
	
});
</script>

<div class="container">

<div class="margin-top"></div>
<!-- <h1>게시글 상세보기</h1> -->
<hr>
<table class="table table-bordered">

<tr>
<td class="info">글번호</td><td colspan="3"><%=viewBoard.getBoardno() %></td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3"><%=viewBoard.getTitle() %></td>
</tr>

<tr>
<td class="info">아이디</td><td><%=viewBoard.getMemberid() %></td>
<td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td>
</tr>

<tr>
<td class="info">조회수</td><td><%=viewBoard.getHit() %></td>
</tr>

<tr><td class="info" colspan="4">본문</td></tr>
<tr><td colspan="4"><%=viewBoard.getContent() %></td></tr>

</table>

<!-- 첨부파일 -->
<div>

<%	if( boardFile != null ) { %>
	<img src="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>" 
			 alt="그림을 불러오지못함" width="50%" height="50%"><br>
	<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
			download="<%=boardFile.getOriginname() %>">
		<%=boardFile.getOriginname() %>
	</a>
<%	} %>
</div>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">&nbsp;목록</button>
	<% if(("admin").equals(session.getAttribute("memberid"))) { %>
	<button id="btnUpdate" class="btn btn-info">&nbsp;수정</button>
	<button id="btnDelete" class="btn btn-danger">&nbsp;삭제</button>
	<%	} else { %>
	<%	}  %>
</div>
<div class="margin"></div>
</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>
















