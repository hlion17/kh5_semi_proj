<%@ page import="java.util.List" %>
<%@ page import="dto.ExpireDate" %>
<%@ page import="dto.OpenRecipe" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
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

<h1>공식레시피 검색 결과</h1>
<hr>

<% List<OpenRecipe> list = (List<OpenRecipe>) request.getAttribute("list"); %>

<% for(OpenRecipe o : list) { %>
<div>
    <h3>레시피명: <%= o.getData1() %></h3><br>
    일련번호: <%= o.getSeq() %><br>
    요리종류: <%= o.getPat() %><br>
    열량: <%= o.getEng() %><br>
    단백질: <%= o.getPro() %><br>
    지방: <%= o.getFat() %><br>
    나트륨: <%= o.getNa() %><br>
    메인이미지: <br>
    <% if (!"".equals(o.getMainImg())) {%>
    <img src="<%= o.getMainImg() %>" alt=""><br>
    <% } %>
    재료정보: <%= o.getIngrs() %><br>
    조리방법: <%= o.getData2() %><br>
    조리메뉴얼: <br>
    <% for (Map<String, String> map : o.getData4()) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iter = entries.iterator();
        if (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
    %>
    <% if (!"".equals(next.getValue())) {%>
            <img src="<%=next.getValue()%>" style="width: 200px;height: 200px"> <br>
    <% } %>
            <%=next.getKey()%> <br>
    <%
        }
    } %>
</div>
<% }%>

</body>
</html>
