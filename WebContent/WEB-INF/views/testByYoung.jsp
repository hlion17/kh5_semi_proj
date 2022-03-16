<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/main.css">
<!-- <style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        list-style: none;
    }
    div {
        border: 1px solid black;
        background: beige;
        margin: 5px 0px;
    }
    ul {
        border: 1px solid black;
    }

    /* header */
    header {
        background: beige;
        height: 50px;
        width: 100%;
    }
    #header {
        display: flex;
        justify-content: space-between;
        align-items: center;

        max-width: 1250px;
        min-width: 900px;
        height: 50px;
        margin: 5px auto;
    }
    #header > ul {
        margin: 0px 5px;
    }
    #header > ul > li {
        display: inline-block;

        padding: 5px;
    }

    /* navigator */
    nav {
        background: beige;
        width: 100%;
        height: 50px;
    }
    #nav {
        display: flex;
        justify-content: flex-start;
        align-items: center;

        max-width: 1250px;
        min-width: 900px;
        margin: 5px auto;
        height: 50px;
    }
    #nav > ul {
        display: flex;
        justify-content: space-around;

        width: 50%;
        height: 30px;
        margin-left: 5px;
    }
    #nav > ul > li {
        display: inline-block;
    }

    /* footer */
    footer {
        background: beige;
        width: 100%;
        height: 50px;
    }
    #footer {
        max-width: 1250px;
        min-width: 900px;
        margin: 5px auto;
    }
    #footer > ul {
        display: flex;
        justify-content: space-around;

        width: 400px;
        margin: 10px auto;
    }
    #footer > ul > li {
        display: inline-block;
    }

    /* main */
    #main {
        min-height: 700px;
        max-width: 1250px;
        min-width: 900px;
        min-height: 700px;
        margin: 5px auto;
    }
    /* 여기까지 공통 */
    #sidebar {
        float: left;

        min-height: 700px;
        width: 18%;
        margin: 5px 5px;
    }
    #sidebar > ul {
        display: flex;
        flex-direction: column;
        justify-content: space-around;

        height: 150px;
        margin: 5px 5px;
    }
    #section {
        float: right;

        min-height: 700px;
        width: 78%;
        margin: 5px 5px;
    }
    .clearfix {
        clear: both;
        visibility: hidden;
    }
</style> -->
</head>
<body>

<header>
<div id="header">
    <ul>
        <li>Home</li>
    </ul>
    <ul>
        <li>로그인</li>
        <li>회원가입</li>
        <li>고객센터</li>
    </ul>
</div>
</header>

<nav>
    <div id="nav">
        <ul>
            <li>냉장고</li>
            <li>요리사전</li>
            <li>커뮤니티</li>
            <li>상점</li>
        </ul>
    </div>
</nav>

<main>
<div id="main">
    <div id="sidebar">
        <ul>
            <li>재료검색</li>
            <li>유통기한 검색</li>
            <li>오픈레시피 검색</li>
        </ul>
    </div>
    <div id="section"></div>
    <div class="clearfix"></div>
</div>
</main>

<footer>
<div id="footer">
    <ul>
        <li>HOME</li>
        <li>Pricing</li>
        <li>Q&A</li>
        <li>Abouts</li>
    </ul>
</div>
</footer>

</body>
</html>