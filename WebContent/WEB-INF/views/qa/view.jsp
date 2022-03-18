<%@page import="dto.QaFile"%>
<%@page import="dto.Qa"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%	Qa viewBoard = (Qa) request.getAttribute("viewBoard"); %>
<%	QaFile boardFile = (QaFile) request.getAttribute("boardFile"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/qa/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/qa/update?boardno=<%=viewBoard.getBoardno() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/qa/delete?boardno=<%=viewBoard.getBoardno() %>");
		}
	})
	
});
</script>

<div class="container">

<h1>게시글 상세보기</h1>
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
<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
 download="<%=boardFile.getOriginname() %>">
	<%=boardFile.getOriginname() %>
</a>
<%	} %>
</div>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->

<%@ include file="../layout/footer.jsp" %>
















