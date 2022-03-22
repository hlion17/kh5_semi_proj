<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<%	List<Review> ReviewList = (List) request.getAttribute("reviewList"); %>


<style type="text/css">

.container {
	width: 70%;
	margin: 0 auto;
 	text-overflow: ellipsis;
	
}

.name {
 text-overflow: ellipsis;
}

</style>








<div class="container">
<h1>리뷰 게시판 목록</h1>
		<div class="row">
			<form method="post" name="search" action="/review/search">
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="searchField">
								<option value="0">선택</option>
								<option value="bbsTitle">제목</option>
								<option value="userID">작성자</option>
						</select></td>
						<td><input type="text" class="form-control"
							placeholder="검색어 입력" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
					</tr>

				</table>
			</form>
		</div>
	</div>




<div class="container">
	<table class="table table-striped table-bordered table-hover active">
	<tr class="success">
		<th width=5%>글번호</th>
		<th width=30%>제목</th>
		<th width=10%>상품번호</th>
		<th width=30% class="name">상품명</th>
		<th width=10%>작성자</th>
		<th width=10%>등록일</th>
		<th width=5%>조회수</th>
	</tr>
	
	<%	for(int i=0; i<ReviewList.size(); i++) { %>
	<tr>
		<td><%=ReviewList.get(i).getReview_no() %></td>
		<td><a href="<%=request.getContextPath() %>/review/info?reviewno=<%=ReviewList.get(i).getReview_no()%>"><%=ReviewList.get(i).getTitle() %></td>
		<td><%=ReviewList.get(i).getPro_no() %></td>
		<td><%=ReviewList.get(i).getPro_name() %></td>
		<td><%=ReviewList.get(i).getNick() %></td>
		<td><%=ReviewList.get(i).getRegdate() %></td>
		<td><%=ReviewList.get(i).getHit() %></td>
	<%-- 	<td><button id="btnWrite" onclick="location.href='<%=request.getContextPath() %>/review/write?reviewno=<%=ReviewList.get(i).getReview_no()%>'">리뷰쓰기</button></td> --%>
	</tr>
	<%	} %>
	</table>
	<!-- 글쓰기 버튼 -->
	<div id="btnBox" class="pull-left">
		<button id="btnWrite" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/review/write'">리뷰쓰기</button>
	</div>
</div><!-- .container -->

<%@ include file="./layout/reviewPaging.jsp" %>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>