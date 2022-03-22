<!-- /store -->

<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!--부트스트랩  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<% 
	List<Product> productList = (List)request.getAttribute("ProductList");

	String name = request.getParameter("nick");
	//request.getSession().setAttribute("userName", name);
	//session.setAttribute("login", name);
%>


<!-- header -->
<%@include file="/WEB-INF/views/layout/header.jsp"%>


<style>
/* #product-container { */
/* 	display: flex; */
/* 	flex-flow: row wrap; */

/* } */
/* .pro-item { */
/* 	display: flex; */
/* 	flex-flow: column nowrap; */
/* 	margin: 20px; */
/* 	width: 100px; */
/* 	height: 150px; */
/* 	text-align: center; */
/* } */
/* .pro-item > :nth-child(1) { */
/* 	font-size: 20px; */
/* 	background: gold; */
/* } */
/* .pro-item :nth-child(2) { */
/* 	height: 50%; */

/* } */





/* 희연 추가 */
#product-container {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	padding-bottom: 100px;
}


.pro-item {
	display: flex;
	flex-flow: column nowrap;
	width: 250px;
	height: 250px;
	text-align: center;
	margin: 50px;
}


.name {
	text-align: center;
}


</style>



<div id="main">
<h2 class="name">STORE</h2>

	<div id="section-alone">
		<div id="product-container">
			<%	for(int i=0; i<productList.size(); i++) { %>
			<div class="pro-item">
				<img 
					src="/resources/img/store/item_<%= productList.get(i).getPro_no() %>.jpg"
					class="img-thumbnail">
								<div class="pro-item-first"><%=productList.get(i).getPro_no() %></div>
				<div class="pro-item-second">
					<a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></a>
				</div>
				<h4><div class="pro-item-third"><%=productList.get(i).getPrice() %>원</div></h4>
			</div>
			<%	} %>
		</div>
	</div>
</div>





<%@include file="/WEB-INF/views/layout/footer.jsp"%>