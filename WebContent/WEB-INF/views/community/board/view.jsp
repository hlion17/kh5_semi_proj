<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	Recipe viewBoard = (Recipe) request.getAttribute("viewBoard"); %>
<%	RecipeFile boardFile = (RecipeFile) request.getAttribute("boardFile"); %>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼
	$("#btnList").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/recipe/board");
	})
	
	//수정버튼
	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/recipe/update?boardno=<%=viewBoard.getBoardno() %>");
	})
	
	//삭제버튼
	$("#btnDelete").click(function() {
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/recipe/delete?boardno=<%=viewBoard.getBoardno() %>");
		}
	})
	
	//팔로우버튼
	$("#btnFollow").click(function() {
		console.log("#btnFollow clicked")
		$(location).attr("href", "<%=request.getContextPath() %>/recipe/follow?boardno=<%=viewBoard.getBoardno() %>");
		
		//팔로우완료 알람띄우기
	})
	
	//추천버튼
	$("#btnLike").click(function() {
		console.log("#btnLike clicked")
		
		$(location).attr("href", "<%=request.getContextPath() %>/recipe/like?boardno=<%=viewBoard.getBoardno() %>");
		
		if( <%=request.getSession().getAttribute( "like_" + viewBoard.getBoardno() ) %> ) {
			alert("추천불가상태");
		}
	})
	
});
</script>


<div id="section">

	<h1>게시글 상세보기</h1>
	<div>
		<table class="table table-bordered">
			<tr><td class="info">글번호</td><td colspan="3"><%=viewBoard.getBoardno() %></td></tr>
			<tr><td class="info">제목</td><td colspan="3"><%=viewBoard.getTitle() %></td></tr>
			<tr>
				<td class="info">회원번호</td><td><%=viewBoard.getUserid() %></td>
				<td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td>
			</tr>
			
			<tr>
				<td class="info">조회수</td><td id="hit"><%=viewBoard.getHit() %></td>
				<td class="info">추천수</td><td id="like"><%=viewBoard.getLike() %></td>
			</tr>
			
			<tr><td class="info" colspan="4">본문</td></tr>
			<tr><td colspan="4"><%=viewBoard.getContent() %></td></tr>
		</table>
	</div>

	<!-- 첨부파일 -->
	<div>
	<%	if( boardFile != null ) { %>
		<img src="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>" 
				alt="그림을 불러오지못함" width="100%" height="100%"><br>
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
		
		<button id="btnFollow" class="btn btn-success">팔로우</button>
		<button id="btnLike" class="btn btn-success">추천</button>
	</div>
	
</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>