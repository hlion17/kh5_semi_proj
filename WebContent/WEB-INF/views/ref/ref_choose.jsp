<%@page import="dto.Ref"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Ref> refList = (List)request.getAttribute("list"); 
%>

<!-- header page -->        
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">

<% for (int i = 0; i < refList.size(); i++) { %>
<button onclick="location.href='/ref/itemlist?refCode=<%= refList.get(i).getRefCode() %>'"><%= refList.get(i).getRefName() %></button>
<% } %>

</div>
</main>

<!-- footer page -->
<%@include file = "/WEB-INF/views/layout/footer.jsp" %>