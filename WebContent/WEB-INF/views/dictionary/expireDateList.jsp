<%@ page import="java.util.List" %>
<%@ page import="dto.ExpireDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        div {
            border: 1px solid black;
            margin: 10px 0;
        }
    </style>
</head>
<body>

<h1>식품 유통기한 검색 결과</h1>
<hr>

<% List<ExpireDate> list = (List<ExpireDate>) request.getAttribute("list"); %>

<% for(ExpireDate e : list) { %>
<div>
    <h3>제품명: <%= e.getData1() %></h3><br>
    유통기한: <%= e.getData2() %><br>
    업소명: <%= e.getData3() %><br>
    유형: <%= e.getData4() %><br>
</div>
<% }%>

</body>
</html>
