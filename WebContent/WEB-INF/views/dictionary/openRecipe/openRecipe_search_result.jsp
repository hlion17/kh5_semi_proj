<%@page import="dto.OpenRecipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<OpenRecipe> list = (List<OpenRecipe>) request.getAttribute("list");
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipe_detail.jsp");
%>

<style>
#recipe-result-container {
	display: flex;
	flex-flow: row wrap;
	justify-content: space-between;
}
.recipe-result {
	display: flex;
	flex-flow: column nowrap;
	
	width: 250px;
	height: 300px;
	
	margin: 20px auto;
}
.recipe-result .recipe-img-div {
    display:table-cell;
    vertical-align:middle;
    
    width: 100%;
    
    text-align: center;
}
.recipe-result .recipe-img-div img {
    max-width:240px;
    max-height:180px;
}
</style>

<script>

// 오픈레시피 상세페이지로 이동
function goRecipeDetail(recipe) {
	//const seq = $(this).children().first().text()
	//location.href="/openRecipe/detail?itemName=" + seq
}	

$(".recipe-result").click(function() {
	const itemName = $(this).children().first().text()
	location.href="/openRecipe/detail?itemName=" + itemName
})

</script>

<%-- 검색결과 --%>
<div id="recipe-result-container">
	<% if (list == null) { %>
	<script>alert("검색결과가 없습니다.")</script>
	<% } else { %>
		<% for(OpenRecipe o : list) { %>
		<div class="recipe-result"" style="cursor: pointer;">
			<div style="display: none;"><%= o.getData1() %></div>
			<div class="recipe-img-div">
			<% if (!"".equals(o.getMainImg())) {%>
		    	<img src="<%= o.getMainImg() %>" style="height: 300px;width: 300px"><br>
		    	<span><%= o.getData1() %></span>
		    <% } %>	
			</div>
			<div>
				<span>요리종류: <%= o.getPat() %></span><br>
				<span>영양정보: </span><br>
				<span>열량: <%= o.getEng() %>g, </span>
				<span>단백질: <%= o.getPro() %>g, </span>
				<span>지방: <%= o.getFat() %>g, </span>
				<span>나트륨: <%= o.getNa() %>g</span>
			</div>
		</div>
		<% }%>
	<% } %>
</div>