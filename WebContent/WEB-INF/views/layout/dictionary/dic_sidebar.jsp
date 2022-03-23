<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<style>
#sidebar > ul {
	list-style: none;
}
#sidebar > ul > li {
	cursor: pointer;
}
</style>

<script>
// 재료 검색 페이지로 이동
function goIngrList() {
	$.ajax({
		type: "GET"
		, url: "/ingr/list"
		, dataType: "html"
		, data: ""
		, success: function(res) {
			$("#section").html(res)
		}
		, error: function() {
			console.log("ajax 실패")
		}
	})
}
//유통기한 검색 페이지로 이동
function goExDateList() {
	$.ajax({
		type: "GET"
		, url: "/expireDate/list"
		, dataType: "html"
		, data: ""
		, success: function(res) {
			$("#section").html(res)
		}
		, error: function() {
			console.log("ajax 실패")
		}
	})
}
//오픈레시피 검색 페이지로 이동
function goOpenRecipe() {
	$.ajax({
		type: "GET"
		, url: "/openRecipe/search"
		, dataType: "html"
		, data: ""
		, success: function(res) {
			$("#section").html(res)
		}
		, error: function() {
			console.log("ajax 실패")
		}
	})
}
</script>

<div id="sidebar">
<!--     <ul>
        <li onclick="goIngrList()">재료검색</li>
        <li onclick="goExDateList()">유통기한 검색</li>
        <li onclick="goOpenRecipe()">오픈레시피 검색</li>
    </ul> -->
	<ul>
        <li><a href="/ingr/list">재료검색</a></li>
        <li><a href="/expireDate/list">유통기한 검색</a></li>
        <li><a href="/openRecipe/search">오픈레시피 검색</a></li>
    </ul>
</div>