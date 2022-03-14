<%@page import="dto.Ref"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% List<Ref> refList = (List)request.getAttribute("list"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% for (int i = 0; i < refList.size(); i++) { %>
<button><%= refList.get(i).getRefName() %></button>
<% } %>
</body>
</html>