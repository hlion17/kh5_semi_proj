<%@ page import="java.util.List" %>
<%@ page import="dto.ExpireDate" %>
<%@ page import="dto.OpenRecipe" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<OpenRecipe> list = (List<OpenRecipe>) request.getAttribute("list"); 
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">
    <div id="sidebar">
        <ul>
            <li onclick="location.href = '/ingr/search'">재료검색</li>
            <li onclick="location.href = '/expireDate/list'">유통기한 검색</li>
            <li onclick="location.href = '/openrecipe/pagelist'">오픈레시피 검색</li>
        </ul>
    </div>
    <div id="section">
		<h1>공식레시피 검색 결과</h1>
		<hr>
	
		<% for(OpenRecipe o : list) { %>
		<div>
		    <h3>레시피명: <%= o.getData1() %></h3><br>
		    일련번호: <%= o.getSeq() %><br>
		    요리종류: <%= o.getPat() %><br>
		    열량: <%= o.getEng() %><br>
		    단백질: <%= o.getPro() %><br>
		    지방: <%= o.getFat() %><br>
		    나트륨: <%= o.getNa() %><br>
		    메인이미지: <br>
		    <% if (!"".equals(o.getMainImg())) {%>
		    <img src="<%= o.getMainImg() %>" alt=""><br>
		    <% } %>
		    재료정보: <%= o.getIngrs() %><br>
		    조리방법: <%= o.getData2() %><br>
		    조리메뉴얼: <br>
		    <% for (Map<String, String> map : o.getData4()) {
		        Set<Map.Entry<String, String>> entries = map.entrySet();
		        Iterator<Map.Entry<String, String>> iter = entries.iterator();
		        if (iter.hasNext()) {
		            Map.Entry<String, String> next = iter.next();
		    %>
		    <% if (!"".equals(next.getValue())) {%>
		            <img src="<%=next.getValue()%>" style="width: 200px;height: 200px"> <br>
		    <% } %>
		            <%=next.getKey()%> <br>
		    <%
		        }
		    } %>
		</div>
		<% }%>
    </div>
</div>
</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
