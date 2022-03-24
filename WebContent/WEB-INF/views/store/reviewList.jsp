<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 부트스트랩 적용  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inspiration&family=Roboto:wght@300&display=swap" rel="stylesheet">

<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inspiration&family=Noto+Sans+KR&family=Roboto:wght@300&display=swap" rel="stylesheet">




<style type="text/css">

* {
	/* 폰트지정 */
	font-family: 'Inspiration', cursive;
	font-family: 'Roboto', sans-serif;
}

.container {
	width: 1250%;
/* 	margin: 0 auto; */
/*  	text-overflow: ellipsis; */
 	text-align: center;
 	margin: 10px;
}

tr, td {
	overflow:hidden;
	white-space : nowrap;
	text-overflow: ellipsis;
	margin: 0 auto;
	text-align: center;
}

.pull-left {
	/* 버튼 오른쪽 배치  */
}

.reviewList{
margin: 0 auto;
}

.row{
	margin: 5px;
}

.success {
	text-align: center;
}

.rb {
	text-align: center;
	font-family: 'Bebas Neue', cursive;
	font-size: 20px;
}

h1 {
	margin-top: 30px;
	margin-bottom: 20px;
}

th {
	float: none; 
	margin:0 auto;
    text-align-last: center;
}

</style>



<%	List<Review> ReviewList = (List) request.getAttribute("reviewList"); %>


<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<div class="container">



<div class="rb"><h1>Review Board</h1></div>

<!-- <div class="reviewList"> -->
<!-- 	<div class="row"> -->
	
	<!-- 글쓰기 버튼 -->
	<div id="btnBox" class="pull-right">
		<button id="btnWrite" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/review/write'">리뷰쓰기</button>
	</div>
	
	<br>	<br>

	<div class="container">
		<table class="table table-striped table-hover table-condensed">
			<tr class="info" >
				<th width=10%>NO</th>
				<th width=20%>제목</th>
<!-- 				<th width=10%>상품번호</th> -->
				<th width=20%>상품명</th>
				<th width=10%>작성자</th>
				<th width=10%>등록일</th>
				<th width=10%>조회수</th>
			</tr>
	
			<%	for(int i=0; i<ReviewList.size(); i++) { %>
			<tr>
				<td><%=ReviewList.get(i).getReview_no() %></td>
				<td><a href="<%=request.getContextPath() %>/review/info?reviewno=<%=ReviewList.get(i).getReview_no()%>"><%=ReviewList.get(i).getTitle() %></td>
		<%-- 		<td><%=ReviewList.get(i).getPro_no() %></td> --%>
				<td><%=ReviewList.get(i).getPro_name() %></td>
				<td><%=ReviewList.get(i).getNick() %></td>
				<td><%=ReviewList.get(i).getRegdate() %></td>
				<td><%=ReviewList.get(i).getHit() %></td>
			<%-- 	<td><button id="btnWrite" onclick="location.href='<%=request.getContextPath() %>/review/write?reviewno=<%=ReviewList.get(i).getReview_no()%>'">리뷰쓰기</button></td> --%>
			</tr>
				<%	} %>
		</table>
	
<!-- 	</div>.container -->
</div>


<%@ include file="./layout/reviewPaging.jsp" %>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>