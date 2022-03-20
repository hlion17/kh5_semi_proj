<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    request.setCharacterEncoding("UTF-8");

    String productName = request.getParameter("product");
    ArrayList list = (ArrayList)session.getAttribute("productlist");
    if( list == null ) {
        list = new ArrayList();
        session.setAttribute("productlist", list);
    }
    list.add(productName);
%>
<script> history.go(-1); </script>

</html>