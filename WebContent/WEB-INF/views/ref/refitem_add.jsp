<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String refCode = (String) request.getAttribute("refCode"); %>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> -->

<form action="/ref/item/add?refCode=<%= refCode %>" method="post">
분류코드: 
<select name="ingrCtyCode">
	<option value="10">채소류</option>
	<option value="20">과일류</option>
	<option value="30">곡류</option>
	<option value="40">해조류</option>
	<option value="50">어패류</option>
	<option value="60">견과류</option>
	<option value="70">육류</option>
	<option value="80">버섯류</option>
	<option value="90">기타</option>
</select>
<br>
품목이름: <input type="text" name="itemName"><br>
품목수량: <input type="text" name="itemQty"><br>
보관상태: <br>
냉장: <input type="radio" name="status" value="0" checked="checked">
냉동: <input type="radio" name="status" value="1">
실온: <input type="radio" name="status" value="2">
<br>
유통기한: <input type="date" name="expireDate"><br>
메모: <textarea rows="3" name="note"></textarea>

<button>제출</button>
</form>

<!-- </body>
</html> -->