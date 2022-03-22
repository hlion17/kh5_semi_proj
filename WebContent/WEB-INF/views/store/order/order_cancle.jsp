<%@page import="dto.OrderResult"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<OrderResult> list = (List<OrderResult>) request.getAttribute("list");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>주문취소 페이지</h1>
<hr>

<% for (OrderResult o : list) { %>

상품: <%=o.getProName() %><br>
주문일: <%=o.getOrderDate() %><br>
배송지: <%=o.getAddress() %><br>
연락번호: <%=o.getPhone() %><br>
수취인: <%=o.getReceiver() %><br>
주문상태: <%=o.getStatus() %><br>

<% } %>


</body>
</html>