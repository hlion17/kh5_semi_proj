<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<style type="text/css">
.cart ion-icon {
	vertical-align: bottom;
	font-size: 20px;
	padding-right: 5px;
}

.cart a {
	background-color: royalblue;
	color: #fff;
	cursor: pointer;
	text-decoration: none;
}

.container,
.products-container {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	margin-top: 50px;
	padding-bottom: 100px;
}

.image {
	margin-right: 20px;
	margin-left: 20px;
	position: relative;
	overflow: hidden;
}

.add-cart {
	position : absolute;
	width: 100%;
	background-color: darkgray;
	transition: all 0.3s ease-in-out;
	opacity: 0;
	cursor: pointer;
	text-align: center;
}


.image:hover .cart1,
.image:hover .cart2,
.image:hover .cart3,
.image:hover .cart4 {
	bottom: 50px;
	opacity: 1;
	padding: 10px;
	text-decoration: none;
}
</style>




<meta charset="UTF-8">
<title>store</title>
</head>


<body>

<li><a href="/cart">cart</a></li>
				<li class="cart"><a href="cart.html"> <ion-icon name="cart"></ion-icon>Cart<span>0</span>
				</a></li>
			</ul>
		</nav>
	</header>


	<div class="container">
		<div class="image">
			<img src="../resources/img/store/cherry.jpg" alt="cherry">
			<h3>cherry</h3>
			<h3>1000</h3>
			<a class="add-cart cart1" href="#">Add Cart</a>
		</div>

		<div class="image">
			<img src="../resources/img/store/fineapple.jpg" alt="fineapple">
			<h3>fineapple</h3>
			<h3>2000</h3>
			<a class="add-cart cart2" href="#">Add Cart</a>
		</div>


		<div class="image">
			<img src="../resources/img/store/banana.jpg" alt="banana">
			<h3>banana</h3>
			<h3>3000</h3>
			<a class="add-cart cart3" href="#">Add Cart</a>
		</div>

		<div class="image">
			<img src="../resources/img/store/onion.jpg" alt="onion">
			<h3>onion</h3>
			<h3>4000</h3>
			<a class="add-cart cart4" href="#">Add Cart</a>
		</div>
	</div><!-- container end -->


	<!-- ioicons사용 url -->
	<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>

	<script src="main.js"></script>

<!-- </body> -->


<!-- </html> -->

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>