<%@ page import="dto.Ingredient" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JYG
  Date: 2022-03-05
  Time: 오후 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        div {
            border: 1px solid black;
            margin: 10px 0;
        }

        img {
            width: 200px;
            height: 200px;
        }

    </style>
</head>
<body>

<h1>등록된 재료 리스트</h1>
<hr>

<% List<Ingredient> ingrs = (List<Ingredient>) request.getAttribute("ingrs"); %>

<% for(Ingredient i : ingrs) { %>
<div>
    <h3>재료이름: <%= i.getIngrName() %></h3><br>
    <img src="/resources/img/ingredient/<%= i.getIngrCode()%>.jpg"><br>
    재료코드: <%= i.getIngrCode() %><br>
    재료분류번호: <%= i.getIngrCtyCode() %><br>
    칼로리: <%= i.getDetail1() %><br>
    제철: <%= i.getDetail2() %><br>
    요약: <%= i.getDetail3() %><br>
    구입요령: <%= i.getDetail4() %><br>
    보관온도: <%= i.getDetail5() %><br>
    보관일: <%= i.getDetail6() %><br>
    보관법: <%= i.getDetail7() %><br>
    손질법: <%= i.getDetail8() %><br>
    섭취방법: <%= i.getDetail9() %><br>
    효능: <%= i.getDetail10() %><br>

</div>
<% }%>

</body>
</html>
