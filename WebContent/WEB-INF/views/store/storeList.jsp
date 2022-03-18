<!-- /store -->

<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<Product> productList = (List)request.getAttribute("ProductList"); %>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<h1>상품 목록</h1>
<hr>

<table>
<tr>
	<th>상품번호</th>
<!-- 	<th>상품카테고리번호</th> -->
	<th>상품이름</th>
	<th>상품사진경로</th>
	<th>상품가격</th>
	<th>설명</th>
</tr>

<%	for(int i=0; i<productList.size(); i++) { %>
<tr>
	<td><%=productList.get(i).getPro_no() %></td>
<%-- 	<td><%=productList.get(i).getCty_no() %></td> --%>
	<td><a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></td>
	<td><%=productList.get(i).getImg_path() %></td>
	<td><%=productList.get(i).getPrice() %></td>
	<td><%=productList.get(i).getDescription() %></td>
	
	
</tr>
<%	} %>

</table>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>