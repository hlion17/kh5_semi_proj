<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
	<td><%=ReviewList.get(i).getTitle() %></td>
<%-- 	<td><%=ReviewList.get(i).getContent() %></td> --%>
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


</body>
</html>