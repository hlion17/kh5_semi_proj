<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<%	List<Review> ReviewList = (List) request.getAttribute("reviewList"); %>


<div class="container">

<h1>리뷰 게시판 목록</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="success">
	<th>게시글번호</th>
	<th>상품번호</th>
	<th>작성자</th>
	<th>게시글제목</th>
	<th>등록일</th>
	<th>조회수</th>
</tr>

<%	for(int i=0; i<ReviewList.size(); i++) { %>
<tr>
	<td><%=ReviewList.get(i).getReview_no() %></td>
	<td><%=ReviewList.get(i).getPro_no() %></td>
	<td><%=ReviewList.get(i).getMember_no() %></td>
	<td><a href="./Info?reviewno=<%=ReviewList.get(i).getReview_no()%>"><%=ReviewList.get(i).getTitle() %></td>
	<td><%=ReviewList.get(i).getRegdate() %></td>
	<td><%=ReviewList.get(i).getHit() %></td>
</tr>
<%	} %>
</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left">
	<button id="btnWrite" onclick="location.href='write'">글쓰기</button>
</div>

</div><!-- .container -->

<%@ include file="../layout/paging.jsp" %>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>