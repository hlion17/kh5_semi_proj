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

<h1>공식 레시피 조회</h1>
<hr>
<form action="/openrecipe/list" method="post">
    <input type="text" name="item">
    <button>검색</button>
</form>


</body>
</html>
