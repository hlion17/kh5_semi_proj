<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css");
*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}
div { 
	border: none !important;
} 
/* button {
	margin: 5%;
} */
.margin {
	margin: 5%;
}
.margin-top{
	margin: 5%;
}
/* .border{
	border: 1px solid #ccc;
} */
#btnList:before {
	content: '\F479';
	font-family : bootstrap-icons;
}  
#btnUpdate:before {
	content: '\F4CA';
	font-family : bootstrap-icons;
}  
#btnDelete:before {
	content: '\F5DE';
	font-family : bootstrap-icons;
}
#btnLike:before {
	content: '\F407';
	font-family : bootstrap-icons;
}
#btnFollow:before {
	content: '\F4CF';
	font-family : bootstrap-icons;
}
h1:before {
	content: '\F5E7';
	font-family : bootstrap-icons;
}
#btnWrite:before {
	content: '\F4CB';
	font-family : bootstrap-icons;
}
#btnCancel:before {
	content: '\F623';
	font-family : bootstrap-icons;
} 

table, tr, th, td {
	text-align: center;
	vertical-align: text-bottom;
}

#beforeFile, #afterFile, .img_a, .img_input, img {
	display: block;
	margin: 0px auto;
	text-align: center;
	vertical-align: middle;
	align-content: center;
	align-items: center;
	align-self:center;
	max-width: 300;
	max-height: 300;
}
.board_content {
	text-align: left;
}

</style>

<div class="container"><!---------------- .container ---------------------->

<div id="sidebar">
        <ul>
<!--             <li onclick="location.href = '/recipe/board'">레시피 자랑</li> -->
<!--             <li onclick="location.href = '/rank/recipe'">레시피 랭킹</li> -->
<!--             <li onclick="location.href = '/rank/member'">유저 랭킹</li> -->
            
            <li><a href="/recipe/board">레시피 자랑</a></li>
            <li><a href="/rank/recipe">레시피 랭킹</a></li>
            <li><a href="/rank/member">유저 랭킹</a></li>
        </ul>
</div>