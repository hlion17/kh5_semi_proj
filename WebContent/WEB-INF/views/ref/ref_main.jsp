<%@page import="java.text.ParseException"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="dto.RefItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<RefItem> list = (List<RefItem>) request.getAttribute("itemList");
	int refCode = (Integer) request.getAttribute("refCode");
	int status = (Integer) request.getAttribute("status");
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<!-- JSP 변수 JS에서 사용해주기 위해서 선언 -->
<script>
	var status = <%= status %>
	var refCode = <%= refCode %>
	
	// 냉장고 선택 모달창 띄우기
	$('#ref-modal').on('shown.bs.modal', function () {
  		$('#myInput').focus()
	})
</script>

<!-- 냉장고 메인페이지 CSS 로드 -->
<link rel="stylesheet" href="/resources/css/ref/ref_main.css">

<!-- 냉장고 메인페이지 JS 로드 -->
<script src="/resources/js/ref/ref_main.js"></script>

</head>
<body>

<!-- 아이템 추가 모달창 -->
<div id="modal" class="modal-overlay" style="display: none;'">
    <div class="modal-window">
        <div id="modal-content-area" style="height: 90%; width: 100%;">
            
            <!-- ajax로 아이템 추가 페이지 삽입-->

        </div>
        <!-- <span class="close-area"> X</span> -->
    </div>
</div>

<!-- 냉장고 메인 -->

<div id="main">
    <div id="ref-main">
        <div id="ref-main-nav">
        	<!--  
            <div id="ref-main-nav-left">
                <button class="filtering active" data-value="4">전체목록</button>
                <button class="filtering" data-value="0">냉장</button>
                <button class="filtering" data-value="1">냉동</button>
                <button class="filtering" data-value="2">상온</button>
            </div>
            -->
            <ul class="nav nav-pills">
				<li class="filtering active" data-value="4" role="presentation">전체목록</li>
				<li class="filtering" data-value="0" role="presentation">냉장</li>
				<li class="filtering" data-value="1" role="presentation">냉동</li>
				<li class="filtering" data-value="2" role="presentation">상온</li>
			</ul>
            <div id="ref-main-right">
                <button id="btn-modal-item-add" type="button" class="btn btn-primary" >폼목 추가하기</button>
                <button id="ref-modal" type="button" class="btn btn-primary" data-toggle="modal" data-target="#ref-choose">냉장고 공유하기</button>
                <select name="orderBy" id="orderBy" class="form-control" style="display:inline-block;width:110px;">
                	<option value="expire_date">유통기한</option>
                    <option value="regDate">등록일</option>
                </select>
            </div>
        </div>
        
        <div id="ref-main-items">
		<%-- 품목보여주기(ajax) --%>        

        </div>

    </div>
</div>


<!-- 아이템 상세 정보 팝업 레이어  
<div id="divView" class="item-holder">
	<button id="#close">x</button>
	<div id="detail-content-area" class="item-holder"></div>
</div>
-->

<%-- 
<div>
냉장고 공유 테스트 레이어<br>
나의 냉장고 코드 : <% int myRefCode = (Integer)session.getAttribute("refCode"); %><%= myRefCode %>
<form action="/ref/share" method="post">
	<label>공유하기<input type="text" name="refCode"></label>
	<button>제출</button>
</form>
</div>
--%>


<!-- 냉장고 공유 -->
<div class="modal fade" id="ref-choose">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h3 class="modal-title"><strong>냉장고 공유</strong></h3>
			</div>
			<div class="modal-body">
				<form action="/ref/share" method="post" id="share-form">	
					<div class="form-group">
						<label>나의 냉장고 코드</label>
					    <p class="form-control-static">
					    <mark>
						  <% int myRefCode = (Integer)session.getAttribute("refCode"); %>
						  <%= myRefCode %>
						</mark>
					  	</p>
					</div>		
					<div class="form-group">
						<label for="refCode">공유하기</label>
					    <input type="text" class="form-control" id="refCode" name="refCode" placeholder="공유할 코드를 입력해주세요">
					</div>												
					<button class="btn btn-primary">공유</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</form>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>


<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>