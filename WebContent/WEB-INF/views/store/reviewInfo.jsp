<%@page import="dto.ReviewFile"%>
<%@page import="dto.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 내용</title>
</head>
<body>

<%	Review viewReview = (Review) request.getAttribute("viewReview"); %>
<%	ReviewFile reviewFile = (ReviewFile) request.getAttribute("reviewFile"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/board/list");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/board/update?reviewno=<%=viewReview.getReview_no() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/board/delete?reviewno=<%=viewReview.getReview_no() %>");
		}
	})
	
});
</script>

<div class="container">

<h1>게시글 상세보기</h1>
<hr>
<table class="table table-bordered">

<tr>
<td class="info">글번호</td><td colspan="3"><%=viewReview.getReview_no() %></td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3"><%=viewReview.getTitle() %></td>
</tr>

<tr>
<td class="info">회원번호</td><td><%=viewReview.getMember_no() %></td>
<td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td>
</tr>

<tr>
<td class="info">조회수</td><td><%=viewReview.getHit() %></td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr><td class="info" colspan="4">본문</td></tr>
<tr><td colspan="4"><%=viewReview.getContent() %></td></tr>

</table>

<!-- 첨부파일 -->
<div>
<%	if( reviewFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=reviewFile.getStored_name() %>"
 download="<%=reviewFile.getOrigin_name() %>">
	<%=reviewFile.getOrigin_name() %>
</a>
<%	} %>
</div>

<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div><!-- .container -->



</body>
</html>