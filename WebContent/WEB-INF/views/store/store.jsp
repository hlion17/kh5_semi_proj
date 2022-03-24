<!-- /store -->

<%@page import="jdk.nashorn.internal.ir.annotations.Immutable"%>
<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!--부트스트랩  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inspiration&family=Roboto:wght@300&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inspiration&family=Noto+Sans+KR&family=Roboto:wght@300&display=swap" rel="stylesheet">


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
	margin; 0 0 0 0 ;
	padding-bottom: 100px;
}

#product-container {
	background:white;
	
}
.pro-item {
	display: flex;
	flex-flow: column nowrap;
	width: 300px;
	height: 300px;
	text-align: center;
 	margin: 50px; 
 	border: 2px solid #77af9c;
 	
}


.name {
	text-align: center;
}

.btns {
        display: flex;
        justify-content: right;
        align-items: center;
}

.pro-item-second { /*상품 이름*/
	text-align: center;
	margin: 8px;
	height: 30px;
	font-family: 'Roboto', sans-serif;
}

.pro-item-third { /*상품 가격*/
	text-align: center;
	margin: 	px;
	height: 30px;
	font-family: 'Roboto', sans-serif;

}

.name {
	font-family: 'Bebas Neue', cursive;
	height: : 50px;

}

h2 {
 	font-size: 60px;
/* 	color: #EEE9E9; */
/* 	background: #E2D5D5; */
	background: #77af9c;
	color: #d7fff1;
}

.free {
	background: #77af9c;
    border-radius: 43%;
    margin: 20;
    color: white;
    font-size: small;
    padding: 3;
}
</style>



<div id="main">

	<span class="btns">
	<button type="button"  class="btn btn-warning" onclick="location.href='/cart'">My cart</button>
	<button type="button"  class="btn btn-warning" onclick="location.href='review/list'">Review Board</button>
<!-- 	<button type="button" class="btn btn-warning" onclick="location.href='order/check'">Order List</button> -->
	</span>
	
	<h2 class="name">STORE</h2>

	<div id="section-alone">
		<div id="product-container">
			<%	for(int i=0; i<productList.size(); i++) { %>
			<div class="pro-item">
			
				<!-- 이미지 클릭 시 상품 상세정보로 이동  -->
				<a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>">
				<img src="/resources/img/store/item_<%= productList.get(i).getPro_no() %>.jpg"
					class="img-thumbnail"></a>
				
					
<%-- 								<div class="pro-item-first"><%=productList.get(i).getPro_no() %></div> --%>
				<div class="pro-item-second">
					<a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></a>
				</div>
				<span class="pro-item-third"><%=productList.get(i).getPrice() %>원</span>
				<span><small class="free">무료배송</small></span>
			</div>
			<%	} %>
		</div>
	</div>
</div>





<%@include file="/WEB-INF/views/layout/footer.jsp"%>