<!-- /store -->

<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Product> productList = (List)request.getAttribute("ProductList");

	String name = request.getParameter("nick");
	//request.getSession().setAttribute("userName", name);
	//session.setAttribute("login", name);
%>	

<!-- header -->       
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<table>
<tr>
	<th>상품번호</th>
	<th>상품이름</th>
	<th>상품가격</th>
</tr>

<%	for(int i=0; i<productList.size(); i++) { %>
<tr>
	<td><%=productList.get(i).getPro_no() %></td>
	<td><a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></a></td>
	<td><%=productList.get(i).getPrice() %></td>
</tr>
<%	} %>

</table>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>