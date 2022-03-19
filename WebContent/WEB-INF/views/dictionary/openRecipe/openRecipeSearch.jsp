<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>

$(document).ready(function() {
	
	// 검색 클릭시 결과 페이지 #ExDate-search-result 에 출력
	$("#btn-openRecipe-search").click(function() {
		$.ajax({
			type: "POST"
			, url: "/openRecipe/search"
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

<div>
<h1>공식 레시피 조회</h1>
	<hr>
	<input type="text" name="itemName">
	<button id="btn-openRecipe-search">검색</button>
</div>
<div id="ExDate-search-result"></div>
