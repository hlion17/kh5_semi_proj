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
	int refCode = Integer.parseInt((String) request.getAttribute("refCode"));
%>

<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<!-- css, javascript load -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<link rel="stylesheet" href="/resources/css/ref/ref_main.css">
<script src="/resources/js/ref/ref_main.js"></script>

<script type="text/javascript">

function filterAndSort(a) {
	var status = a.getAttribute("data-value")
	location.href = '/ref/itemlist/filterAndSort?refCode=<%= refCode %>&status=' + status + '&orderBy='+ orderBy.options[orderBy.selectedIndex].value
}

// 두 번 클릭해야 실행되는 문제
$(document).ready(function() {
	$("#btn-modal-item-add").click(function() {
		// 아이템추가 버튼 클릭시 모달창 활성화
	    const modal = document.getElementById("modal")
	    const btnModal = document.getElementById("btn-modal-item-add")
	    btnModal.addEventListener("click", function() {
	    	console.log("클릭")
	        modal.style.display = "flex"
	    })
	    
	    // 아이템 추가 페이지 ajax처리
	    $.ajax({
	    	type: "get"
	    	, url: "/ref/item/add"
	    	, data: {refCode: <%= refCode %>}
	    	, dataType: "html"
	    	, success: function(res) {
	    		$("#modal-content-area").html(res)
	    	}
	    	, error: function() {
	    		console.log("ajax 실패")
	    	}
	    })
	})
})



</script>
<style type="text/css">
	.filtering {
		color: blue;
		cursor: pointer;
	}
</style>
</head>
<body>

<!-- 모달창 -->
<div id="modal" class="modal-overlay" style="display: none;'">
    <div class="modal-window">
        <div id="modal-content-area" style="height: 90%; width: 100%;">
            
            <!-- ajax로 아이템 추가 페이지 삽입-->

        </div>
        <span class="close-area"> X</span>
    </div>
</div>

<!-- 냉장고 메인 -->

<div id="main">
    <div id="ref-main">
        <div id="ref-main-nav">
            <div id="ref-main-nav-left">
                <a class="filtering" data-value="4" onclick="filterAndSort(this);">전체목록</a>
                <a class="filtering" data-value="0" onclick="filterAndSort(this);">냉장</a>
                <a class="filtering" data-value="1" onclick="filterAndSort(this);">냉동</a>
                <a class="filtering" data-value="2" onclick="filterAndSort(this);">상온</a>
            </div>
            <div id="ref-main-right">
                <button id="btn-modal-item-add">폼목 추가하기</button>
                <button>냉장고 공유하기</button>
                <select name="orderBy" id="orderBy">
                    <option value="regDate">등록일</option>
                    <option value="expire_date">유통기한</option>
                </select>
            </div>
        </div>
        
        <div id="ref-main-items">

			<%-- 품목보여주기 --%>        
			<% for (int i = 0; i < list.size(); i++) { %>
            <div class="item">
            	<% 
	            	long diff = System.currentTimeMillis() - list.get(i).getExpireDate().getTime();
            		long dDay = diff / (24 * 60 * 60 * 1000);
           		%>
                	
                	<!-- D-day 계산 -->
               	<% if (dDay <= 0 ) { %>
                	<div style="background: red;"><span>D-Day <%= dDay %></span></div>
                <% } else { %>
                	<div style="background: lime;"><span>D-Day <%= dDay %></span></div>
                <% } %>
                	<!-- 재료구분 코드에 따른 아이콘 불러오기 -->
                	<img src="/resources/img/ingrCty/<%= list.get(i).getIngrCtyCode() %>.png" alt="">
                	
                	<!-- 보관상태에 따른 색 구분 -->
                <% if (list.get(i).getStatus() == 0){ %>
                	<div style="background: yellow;"><%= list.get(i).getItemName() %></div>
                <% } else if(list.get(i).getStatus() == 1) { %>
                	<div style="background: blue;"><%= list.get(i).getItemName() %></div>
                <% } else if(list.get(i).getStatus() == 2) { %>
                	<div style="background: grey;"><%= list.get(i).getItemName() %></div>
                <% } %>
                <a href="/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= list.get(i).getItemNo() %>">위 품목 삭제</a>
                
                
                <%-- 
             	품목번호: <%= list.get(i).getItemNo() %><br> 
			품목이름: <%= list.get(i).getItemName() %><br>
			품목분류: <%= list.get(i).getIngrCtyCode() %><br>
			개수: <%= list.get(i).getItemQty() %><br>
			상태: <%= list.get(i).getStatus() %><br>
			등록일: <%= list.get(i).getRegDate() %><br>
			유통기한: <%= list.get(i).getExpireDate() %><br>
			메모: <%= list.get(i).getNote() %><br>
			<a href="/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= list.get(i).getItemNo() %>">위 품목 삭제</a>
			--%>
			
            </div>
			<% } %>
			<%-- 품목 보여주기 끝 --%>

        </div>
        
        

    </div>
</div>



<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>