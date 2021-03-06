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
h1:before {
	content: '\F227';
	font-family : bootstrap-icons;
}

table, tr, th, td {
	text-align: center;
	vertical-align: text-bottom;
}

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
#btnWrite:before {
	content: '\F4CB';
	font-family : bootstrap-icons;
}
#btnCancel:before {
	content: '\F623';
	font-family : bootstrap-icons;
} 
#my_profile:before {
	content: '\F3A7';
	font-family : bootstrap-icons;
	vertical-align: text-bottom;
}
#my_follow:before {
	content: '\F77A';
	font-family : bootstrap-icons;
	vertical-align: text-bottom;
}

#my_recipe:before { 
	content: '\F227'; 
	font-family : bootstrap-icons; 
	vertical-align: text-bottom; 
} 

#my_follower:before {
	content: '\F77B';
 	font-family : bootstrap-icons;
 	vertical-align: text-bottom; 
} */

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

img {
	max-width: 300;
}

.board_content {
	text-align: left;
}
</style>

<div class="container"><!---------------- .container ---------------------->

<div id="sidebar">
        <ul>
<!--             <li onclick="location.href = '/recipe/board'">????????? ??????</li> -->
<!--             <li onclick="location.href = '/rank/recipe'">????????? ??????</li> -->
<!--             <li onclick="location.href = '/rank/member'">?????? ??????</li> -->
            
            <li><a href="/social/profile">?????? ?????????</a></li>
            <li><a href="/social/recipe">?????? ?????????</a></li>
            <li><a href="/social/followee">?????? ?????????</a></li>
            <li><a href="/social/follower">?????? ?????????</a></li>
        </ul>
</div>