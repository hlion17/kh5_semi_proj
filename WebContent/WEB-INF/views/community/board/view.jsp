<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	Recipe viewBoard = (Recipe) request.getAttribute("viewBoard"); %>
<%	RecipeFile boardFile = (RecipeFile) request.getAttribute("boardFile"); %>
<%	int board_no = (int) request.getAttribute("board_no"); %>

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
<%-- 		$(location).attr("href", "<%= %>"); --%>
	})
	
	//추천버튼
	$("#btnLike").click(function() {
		console.log("#btnLike clicked")
<%-- 		$(location).attr("href", "<%=request.getContextPath() %>/recipe/content?boardno=<%=viewBoard.getBoardno() %>&like=<%=viewBoard.getLike()+1 %>"); --%>
<%-- 		$(location).attr("href", "<%=request.getContextPath() %>/recipe/content?boardno=<%=viewBoard.getBoardno() %>"); --%>
<%-- 		$(location).attr("href", "<%=request.getContextPath() %>/recipe/like?boardno=<%=viewBoard.getBoardno() %>"); --%>
		
		//AJAX 처리
		$.ajax({
			type: "post" //요청메소드
			, url: "/recipe/content" //요청 URL
			, data: { //요청 파라미터
				board_no : <%= board_no %>
// 				, like : $("#like").val()+1
<%-- 				, like : <%=viewBoard.getLike()+1 %> --%>
			}
			, dataType: "text" //응답 데이터의 형식
			, success: function( res ) {
				console.log("AJAX 성공")
				
				//응답 데이터 반영하기
				$("#like").text( res )
				
			}
			, error: function() {
				console.log("AJAX 실패")
		
			}
		})

	})
	
});
</script>


<div id="section">

	<%-- <h1><%= board_no %></h1> --%>
	<h1>게시글 상세보기</h1>
	
	<div>
		<table class="table table-bordered">
			<tr><td class="info">글번호</td><td colspan="3"><%=viewBoard.getBoardno() %></td></tr>
			<tr><td class="info">제목</td><td colspan="3"><%=viewBoard.getTitle() %></td></tr>
			<tr>
				<td class="info">아이디</td><td><%=viewBoard.getUserid() %></td>
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