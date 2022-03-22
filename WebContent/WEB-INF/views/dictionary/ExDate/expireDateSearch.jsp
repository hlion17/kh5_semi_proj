<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<script>
$(document).ready(function() {
	
	// 검색 클릭시 결과 페이지 #ExDate-search-result 에 출력
	$("#btn-ExDate-search").click(function() {
		$.ajax({
			type: "POST"
			, url: "/expireDate/list"
			, dataType: "html"
			, data: {itemName: $("input[name=itemName]").val()}
			, success: function(res) {
				$("#ExDate-search-result").html(res)
			}
			, error: function() {
				console.log("ajax 실패")
			}
		})	 
	})
	
})
</script>

<main>

<div id="main">
	<!-- 사전 사이드바 -->
	<%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
	<div id="section">
		<div>
			<h1 style="margin-bottom: 30px;">식품 유통기한 검색</h1>
			<input type="text" name="itemName">
    		<button id="btn-ExDate-search">검색</button>
		</div>
		<hr>
		<div id="ExDate-search-result"></div>
	</div>
	<div id="clear-fix"></div>
</div>

</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>

