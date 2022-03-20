<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<style type="text/css">

<%@include file = "/WEB-INF/views/layout/header.jsp" %>

/* *{ */
/* 	margin: 0; */
/* 	padding: 0; */
/* 	box-sizing: border-box; */
/* } */

/* header { */
/* 	background-image:url('https://i.pinimg.com/564x/a4/24/19/a42419e6aca6d3ae031d668245aa7292.jpg'); */
/* 	background-repeat: no-repeat; */
/* 	background-size: cover; */
/* 	background-position: center; */
/* 	height: 150px; */
/* 	position: relative; */
/* } */


/* .overlay { */
/* 	position: absolute; */
/* 	z-index: 1; */
/* 	top: 0; */
/* 	left: 0; */
/* 	height: 100px; */
/* 	right: 0; */
/* 	botton: 0; */
/* 	padding: 0; ! important; */
/* 	margin: 0; ! important; */
/* 	background-color: rgba(10, 10, 10, 0, 3); */
/* } */

/* nav { */
/* 	position: absolute; */
/* 	top: 0; */
/* 	right: 0; */
/* 	left: 0; */
/* 	z-index: 2; */
/* 	display: flex; */
/* 	flex-wrap: wrap; */
/* 	justify-content: space-between; */
/* 	padding: 50px 60px 0 60px; */
/* } */

/* nav li { */
/* 	list-style: none; */
/* 	display: inline-block; */
/* 	padding-right: 10px; */
/* 	padding-left: 10px; */
/* } */

/* nav li span { */
/* 	padding-left: 5px; */
/* } */

/* li a { */
/* 	padding: 5px; */
/* 	background-color: #fff; */
/* 	text-decoration: none; */
/* } */

.products-container {
	max-width: 650px;
	justify-content: space-around;
	margin: 0 auto;
	margin-top : 50px;
}

.products-container ion-icon {
	font-size: 25px;
	color: blue;
	margin-left: 10px;
	margin-right: 10px;
	cursor: pointer;
}

.product-header {
	width: 100%;
	max-width: 650px;
	display: flex;
	justify-content: flex-start;
	border-bottom: 4px solid lightgrey;
	margin: 0 auto;
}

.product-title{
	width: 45%;
}

.price {
	width: 15%;
	display: flex;
	align-items: center;
	border-bottom: 1px solid lightgrey;
}

.quantity {
	width: 30%;
	display: flex;
	align-items: center;
	border-bottom: 1px solid lightgrey;
}

.total {
	width: 10%;
	display: flex;
	align-items: center;
	border-bottom: 1px solid lightgrey;
}

.product {
	width: 45%;
	display: flex;
	justify-content: space-around;
	align-items: center;
	padding: 10px 0;	
	border-bottom: 1px solid lightgrey;
}

.product ion-icon {
	cursor: pointer;
}

.products {
	width: 100%;
	display: flex;
	flex-wrap: wrap;
}

.products img {
	width: 75px;
}

.basketTotalContainer {
	display: flex;
	justify-content: flex-end;
	width: 100%;
	padding: 10px 0;
	
}


.basketTotalTitle{
	width: 30%;
}

.basketTotal {
	width: 10%;
}
</style>




<body>

<body>
	<header>
		<div class="overlay"></div>
		<nav>
			<h2>SHOP</h2>
			<ul>
				<li><a href="index.html">HOME</a></li>
				<li><a href="#">About</a></li>
				<li class="cart">
					<a href="cart.html">
						<ion-icon name="basket"></ion-icon>Cart<span>0</span>
					</a>
				</li>
			</ul>
		</nav>
	</header>
	
	<div class="products-container">
		<div class="product-header">
			<h5 class="product-title">PRODUCT</h5>
			<h5 class="price">PRICE</h5>
			<h5 class="quantity">QUANTITY</h5>
			<h5 class="total">TOTAL</h5>
		</div>
		<div class="products">
		
		</div>
	</div>
	
	<script src="main.js"></script>
	<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>



<%@include file = "/WEB-INF/views/layout/footer.jsp" %>

<!-- </body> -->
<!-- </html> -->