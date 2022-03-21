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

<style>
#product-container {
	display: flex;
	flex-flow: row wrap;
	
}
.pro-item {
	display: flex;
	flex-flow: column nowrap;
	margin: 20px;
	width: 100px;
	height: 150px;
	text-align: center;
}
.pro-item > :nth-child(1) {
	font-size: 20px;
	background: gold;
}
.pro-item :nth-child(2) {
	height: 50%;
}
</style>

<div id="main">
	<div id="section-alone">
		<div id="product-container">
			<%	for(int i=0; i<productList.size(); i++) { %>
			<div class="pro-item">
				<div class="pro-item-first"><%=productList.get(i).getPro_no() %></div>
				<div class="pro-item-second"><a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></a></div>
				<div class="pro-item-third"><%=productList.get(i).getPrice() %></div>
			</div>
			<%	} %>
		</div>
	</div>
</div>

<%--  
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
--%>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>