<!-- /store -->

<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<Product> productList = (List)request.getAttribute("ProductList"); %>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>

    <%
        request.setCharacterEncoding("UTF-8");
 
        String name = request.getParameter("nick");
        request.getSession().setAttribute("userName", name);
 
        session.setAttribute("login", name);
    %>
       <%=request.getSession().getAttribute("login")%>님이 입장 하셨습니다!! 
<table>
<tr>
	<th>상품번호</th>
	<th>상품이름</th>
	<th>상품가격</th>
</tr>

<%	for(int i=0; i<productList.size(); i++) { %>
<tr>
	<td><%=productList.get(i).getPro_no() %></td>
	<td><a href="./productInfo?pro_no=<%=productList.get(i).getPro_no() %>"><%=productList.get(i).getName() %></td>
	<td><%=productList.get(i).getPrice() %></td>
	
	
</tr>
<%	} %>

</table>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>