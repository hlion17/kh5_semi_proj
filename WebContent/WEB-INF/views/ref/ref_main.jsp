<%@page import="java.util.List"%>
<%@page import="dto.RefItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<RefItem> list = (List<RefItem>) request.getAttribute("itemList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% for (int i = 0; i < list.size(); i++) { %>

품목번호: <%= list.get(i).getItemNo() %><br> 
품목이름: <%= list.get(i).getItemName() %><br>
품목분류: <%= list.get(i).getIngrCtyCode() %><br>
개수: <%= list.get(i).getItemQty() %><br>
상태: <%= list.get(i).getStatus() %><br>
등록일: <%= list.get(i).getRegDate() %><br>
유통기한: <%= list.get(i).getExpireDate() %><br>
메모: <%= list.get(i).getNote() %><br>
<br><br>

<% } %>
</body>
</html>