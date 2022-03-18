<%@page import="java.util.List"%>
<%@page import="dto.Ingredient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<Ingredient> ingrs = (List<Ingredient>) request.getAttribute("ingrs");
%>

<style>

.ingr-short {
	display: flex;
	flex-flow: column wrap;
	
	width: 40%;
	height: 100px;
}
.ingr-short :nth-child(2) {
	width: 30%;
	height: 100%;
	
	text-align: center;
}
.ingr-short :nth-child(3) {
	width: 70%;
	height: 100%;
}

</style>

<script>
$(".ingr-short").children().click(function() {
	console.log("클릭")
	const ingrCode = $(this).parent().children().first().text()
	location.href = "/ingr/detail?ingrCode=" + ingrCode
})
</script>

<% for(Ingredient i : ingrs) { %>
  	<div class="ingr-short">
  		<div style="display: none;"><%= i.getIngrCode() %></div>
		<div><h3><%= i.getIngrName() %></h3></div>
		<div>
			칼로리: <%= i.getDetail1() %><br>
			제철: <%= i.getDetail2() %><br>
			보관온도: <%= i.getDetail5() %><br>
			보관일: <%= i.getDetail6() %><br>
		</div>
	</div>
<% }%>