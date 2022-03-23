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
	
	width: 270px;
	height: auto;
	
	margin: 20px auto;
}
.recipe-result .recipe-img-div {
    /* display:table-cell; */
    vertical-align:middle;
    
    width: 100%;
    
    text-align: center;
}
.recipe-result .recipe-img-div img {
    max-width:240px;
    max-height:180px;
    border-radius: 10px;
}
.recipe-result .recipe-img-div h4 {
    font-weight: bold;
}
.recipe-result .recipe-img-div > div:nth-child(2) {
	maring-top: 20px;
    border-bottom: 1px solid black;
}
.recipe-short {
	padding: 10px;
}
.recipe-short span {
	font-weight: bold;
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
		<div class="recipe-result" style="cursor: pointer;">
			<div style="display: none;"><%= o.getData1() %></div>
			<div class="recipe-img-div">
			<% if (!"".equals(o.getMainImg())) {%>
		    	<div><img src="<%= o.getMainImg() %>" style="height: 300px;width: 300px"></div>
		    	<div><h4><%= o.getData1() %></h4></div>
		    <% } %>	
			</div>
			<div class="recipe-short">
				<p><span>요리종류: </span><%= o.getPat() %></p>
				<p><span>영양정보: </span></p>
				<p>
					<span>열량: </span><%= o.getEng() %>g, 
					<span>단백질: </span><%= o.getPro() %>g,<br> 
					<span>지방: </span><%= o.getFat() %>g, 
					<span>나트륨: </span><%= o.getNa() %>g
				</p>
			</div>
		</div>
		<% }%>
	<% } %>
</div>