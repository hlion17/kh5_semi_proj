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

<h1>식품 유통기한 검색</h1>
<hr>
<form action="/expireDate/list" method="post">
    <input type="text" name="item">
    <button>검색</button>
</form>


</body>
</html>
