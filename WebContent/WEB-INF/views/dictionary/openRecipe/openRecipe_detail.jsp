<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="dto.OpenRecipe"%>
<%@page import="dto.Ingredient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if (request.getAttribute("list") == null) { %>
<script>
	alert("상세페이지가 준비중입니다.")
	history.back(-1)
</script>		
<% } else {%>

<% 
	List<OpenRecipe> list = (List<OpenRecipe>) request.getAttribute("list"); 
%>


<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<!-- 메인 CSS 로드 -->
<link rel="stylesheet" href="/resources/css/dictionary/dic_ingr_main.css">

<!-- 메인 JS 로드 -->
<script src="/resources/js/dictionary/dic_ingr_main.js"></script>

<!-- 레시피 상세 페이지 CSS -->
<style>
#recipe-container {
	width: 80%;
	margin: 20px auto;
}
#recipe-menu-name {
	font-size: 35px;
	font-weight: bold;
	line-height: 80px;
}
.recipe-info-title {
	font-size: 25px;
	font-weight: bold;
	line-height: 40px;
}
#recipe-main-img {
	padding: 20px;
	text-align: center;	
	margin-bottom: 30px;
}
#recipe-main-img img{
	max-width: 500px;
	border-radius: 20px;
}
#recipe-brief {
	margin-bottom: 30px;
	padding: 20px;
}
#recipe-brief span {
	line-height: 30px;
	padding: 20px;
}
#recipe-ingrs {
	display: flex;
	flex-flow: row wrap;
	/* justify-content: space-around; */
	align-content: space-between;
	/* margin: 30px 0; */
	padding: 20px;
	
}
.recipe-ingr {
	width: 40%;
	padding: 10px;
}
#recipe-step {
	margin-top: 20px;
}
#recipe-step span {
	line-heigth: 20px;
}
#recipe-step img {
	width: 150px;
}
#recipe-step .recipe-step-inner-div {
	margin: 30px 20px;
}
#recipe-step .step {
	display: inline-block;
	margin: 10px;
	padding: 6px;
	background: #74B243;
	border-radius: 20px;
	font-weight: bold;
}
</style>

<!-- 레시피 메인 화면 -->
<main>
<div id="main">

<!-- 사전 사이드바 -->
<%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
    
	<div id="section">
<% for(OpenRecipe o : list) { %>
    
    	<div id="recipe-container">
	    	<div id="recipe-main-img">
				<% if (!"".equals(o.getMainImg())) { %>
				<!-- 레시피 메인이미지 -->
				<img src="<%= o.getMainImg() %>" ><br>
				<% } %>
				<!-- 레시피 이름 -->
				<span id="recipe-menu-name"><%= o.getData1() %></span>
	    	</div>
	    	
	    	<hr>
	    	<!-- 메뉴정보 -->
	    	<span class="recipe-info-title">요리정보</span>
	    	<div id="recipe-brief">
		    	<span>요리종류: <%=o.getPat()%></span><br>
		    	<span>조리방법: <%=o.getData2()%></span><br>
		    	<span>영양정보(1인분 기준)</span><br>
		    	<span>열량: <%=o.getEng()%> Kcal</span><br>
		    	<span>
		    		단백질: <%=o.getPro()%> g, 
		    		지방: <%=o.getFat()%> g, 
		    		나트륨: <%=o.getNa()%> g 
		    	</span>
	    	</div>
	    	
	    	<hr>
	    	<!-- 재료정보 -->
	    	<span class="recipe-info-title">재료</span>
	    	<div id="recipe-ingrs">
	    	<% 
	    		String[] ingrs = o.getIngrs().trim().split(",");
	    		for (String i : ingrs) {
   			%>	    			
	    		<div class="recipe-ingr"><%= i %></div>
	    	<% } %>
	    	</div>
	    	
	    	<hr>
	    	<!-- 조리법 -->
	    	<span class="recipe-info-title">조리법</span>
			<div id="recipe-step">
			<% int i = 0; %>
		    <% for (Map<String, String> map : o.getData4()) {
		    		i++;
					Set<Map.Entry<String, String>> entries = map.entrySet();
		        	Iterator<Map.Entry<String, String>> iter = entries.iterator();
		        	
			        if (iter.hasNext()) {
			            Map.Entry<String, String> next = iter.next(); %>
		            <div class="recipe-step-inner-div">
		            	<div>
		            		<span class="step">STEP <%= i + "." %></span>
		            	</div>
		            	<div>
		            		<div style="display:inline-block;">
			            	<% if (!"".equals(next.getValue())) { %>
					            <img src="<%=next.getValue() %>"><br>
						    <% } %>
						    </div>
						    <div style="display:inline-block;">
				            <% if (!"".equals(next.getKey())) { %>
					            <% int length = next.getKey().length(); %>
			            		
			            		<span><%= next.getKey().substring(3, length)%></span>
				            <% } %>
				            </div>
			            </div>
		    		<% }%>
	    			</div>
			<% } %>    
			</div>
<% } %><%-- 반복문 끝 --%>
		</div><!-- container 끝 -->
    </div> <!-- section 끝 -->
<div class="clearfix"></div>
</div>
</main>
<% } %><%-- null 검증 끝 --%>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>