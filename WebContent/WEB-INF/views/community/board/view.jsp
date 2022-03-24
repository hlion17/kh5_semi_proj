<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	Recipe viewBoard = (Recipe) request.getAttribute("viewBoard"); %>
<%	RecipeFile boardFile = (RecipeFile) request.getAttribute("boardFile"); %>
<%	int b = viewBoard.getBoardno(); %>
<%	HttpSession s = request.getSession(); %>

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
		
		<%	try { %> 
		
			//팔로우완료 알람띄우기
			<% if( (boolean)s.getAttribute("follow_myself_flag") ) { %>
					alert( '<%=s.getAttribute("follow_myself")%>' );
			<% } %>
			<% if ( (boolean)s.getAttribute("follow_already_flag") ) { %>
					alert( '<%=s.getAttribute("follow_already")%>' );
			<% } %>
			<% if ( (boolean)s.getAttribute("follow_success_flag") ) { %>
					alert( '<%=s.getAttribute("follow_success")%>' );
			<% } %> 
			<% if ( (boolean)s.getAttribute("follow_unknown_flag") ) { %>
					alert( '<%=s.getAttribute("follow_unknown")%>' );
			<% } %>	
			
		<%	} catch (NullPointerException e) { %>
		<%			System.out.println("null발생");%>
		<%	} %>
	})
	
	//추천버튼
	$("#btnLike").click(function() {
		console.log("#btnLike clicked")		
		<%	try { %> 
		<%		boolean lf = (boolean)request.getSession().getAttribute( "like_" + b ); %> 
		<% 		System.out.println("추천불가메시지 출력!"); %>
				alert("추천할 수 없습니다!");
		<%	} catch (NullPointerException e) { %>
				alert("추천하셨습니다 :)")
		<%		System.out.println("추천메시지출력!!");	%>
		<%	} %>
		
		$(location).attr("href", "<%=request.getContextPath() %>/recipe/like?boardno=<%=viewBoard.getBoardno() %>");
		
	})
	
});
</script>


<div id="section">
<div class="margin-top"></div>
	<!-- <h1>게시글 상세보기</h1> -->
	<div>
		<table class="table table-bordered">
			<%	if( viewBoard != null ) { %>
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
			<%	} %>
		</table>
	</div>
	
	<br>
	<!-- 첨부파일 -->
	<div>
		<%	if( boardFile != null ) { %>
			<img src="<%=request.getContextPath() %>/resources/img/recipe/<%=boardFile.getStoredname() %>" alt="">
			<a class="img_a" href="<%=request.getContextPath() %>/resources/img/recipe/<%=boardFile.getStoredname() %>" download="<%=boardFile.getOriginname() %>">
				<%=boardFile.getOriginname() %>
			</a>
		<% } else { %>
			<img src="<%=request.getContextPath() %>/resources/img/profileBasic/profile.jpg" alt="" width="300" hidden="300">
		<% } %>
	</div>

	<div class="text-center">
		<br><br>
		<button id="btnList" class="btn btn-primary">&nbsp;목록</button>
		<%	if( request.getSession().getAttribute("memberno") != null ) { %>
			<% 	if( viewBoard.getUserid() == Integer.parseInt(request.getSession().getAttribute("memberno").toString()) ) { %>
				<button id="btnUpdate" class="btn btn-info">&nbsp;수정</button>
				<button id="btnDelete" class="btn btn-danger">&nbsp;삭제</button>
				<button id="btnLike" class="btn btn-success">&nbsp;추천</button>
			<% 	} else { %>
				<button id="btnLike" class="btn btn-success">&nbsp;추천</button>
				<button id="btnFollow" class="btn btn-warning">&nbsp;팔로우</button>
			<% 	} %>
		<% 	} %>
	</div>
	
	<div class="margin"></div>
	
</div><!-- #section -->

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>