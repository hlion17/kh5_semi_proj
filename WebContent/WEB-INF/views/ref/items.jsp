<%@page import="dto.RefItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<RefItem> list = (List<RefItem>) request.getAttribute("itemList");
	int refCode = (Integer) request.getAttribute("refCode");
	int status = (Integer) request.getAttribute("status");
%>

<div id="test"><%= status %></div>

<%-- 품목보여주기 --%>        
<% for (int i = 0; i < list.size(); i++) { %>
	<div class="item">
	
	<%-- D-day 계산  --%>
	<% 
	 	long diff = list.get(i).getExpireDate().getTime() - System.currentTimeMillis();
		long dDay = diff / (24 * 60 * 60 * 1000);
	%>
    
    <%-- dDay에 따른 색 구분 --%>	
   	<% if (dDay <= 0 ) { %>
    	<div style="background: red;"><span>D-Day <%= dDay %></span></div>
    <% } else { %>
    	<div style="background: lime;"><span>D-Day <%= dDay %></span></div>
    <% } %>
    
    	<!-- 재료구분 코드에 따른 아이콘 불러오기 -->
    	<img src="/resources/img/ingrCty/<%= list.get(i).getIngrCtyCode() %>.png" alt="">
    	
    	<%-- 보관상태에 따른 색 구분 --%>
	<!-- *** 글자수 많으면 넘어가는거 해결하기 *** -->
    <% if (list.get(i).getStatus() == 0){ %>
    	<div style="background: yellow;"><%= list.get(i).getItemName() %></div>
    <% } else if(list.get(i).getStatus() == 1) { %>
    	<div style="background: blue;"><%= list.get(i).getItemName() %></div>
    <% } else if(list.get(i).getStatus() == 2) { %>
    	<div style="background: grey;"><%= list.get(i).getItemName() %></div>
    <% } %>
    
    <a href="/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= list.get(i).getItemNo() %>">위 품목 삭제</a>
	
	</div>
<% } %>
<%-- 품목 보여주기 끝 --%>