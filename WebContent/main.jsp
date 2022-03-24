<%@page import="java.util.List"%>
<%@page import="dto.Ref"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- header -->
<%@include file="/WEB-INF/views/layout/header.jsp"%>


<style>
div {
    /* border: 1px solid #dee2e6; */
    box-sizing: border-box;

}
#section {
    width: 80%;
    margin: 0 auto;
}
.content-title {
    width: 80%;
    margin: 0 auto;
}
.main-content {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    width: 80%;
    height: 300px;
    margin: 0 auto;
}
.main-content > .items {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 30%;
    height: 90%;
    text-align: center;
}
.main-content > .items > .item-img {
    max-height: 80%;
    padding: 20px;
}
.main-content > .items > .item-img img {
    width: 100%;
    height: 100%;
    border-radius: 15px;
}
.main-content > .items > .item-detail {
    width: 100%;
    height: 20%;
}
.main-content2 {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    align-content: center;
    align-content: center;
    width: 70%;
    height: 400px;
    margin: 0 auto;
}
.main-content2 > .items2 {
    width: 50%;
    height: 80%;
}
.main-content2 > .items2 img {
    width: 100%;
    height: 100%;
}
.main-content2 > .items2 p {
    padding: 5px;
}
</style>

<main>

<div id="main">

<div id="section-alone">
	
	    <div class="content-title"><h2>이달의 제철 식품</h2></div>
    <div class="main-content2">

        <div class="items2">
            <img src="/resources/img/ingredient/6.jpg" alt="">
        </div>
        <div class="items2">
            <p>
                봄이면 빠질 수 없는 딸기! 향도 달콤, 맛도 달콤한 딸기로 스펀지케이크, 비타민 C가 풍부한 딸기를 이용한 요리 어떠신가요? 붉은 과실의 선두 주자 딸기를 소개합니다.
            </p>
            <p>보관온도: 1~5℃</p>
            <p>보관일: 4일</p>
            <p><a>자세히 보기</a></p>
        </div>
    </div>

    <div class="content-title"><h2>인기 유저</h2></div>
    <div class="main-content">
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/test/1.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>나희도</strong>
            </div>
        </div>
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/test/2.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>백이진</strong>
            </div>
        </div>
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/test/3.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>보나</strong>
            </div>
        </div>
    </div>

    <div class="content-title"><h2>이달의 추천 레시피</h2></div>
    <div class="main-content">
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/ingredient/1.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>브로콜리</strong>
            </div>
        </div>
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/ingredient/2.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>백이진</strong>
            </div>
        </div>
        <div class="items">
            <div class="item-img">
                <img src="/resources/img/ingredient/3.jpg" alt="">
            </div>
            <div class="item-detail">
                <strong>토마토</strong>
            </div>
        </div>
    </div>
	
</div>

</div>

</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>