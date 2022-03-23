<%@page import="dto.RefItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 페이지에 필요한 데이터(냉장고 품목 리스트, 냉장고 코드, 냉장고 상태코드)
	List<RefItem> list = (List<RefItem>) request.getAttribute("itemList");
	int refCode = (Integer) request.getAttribute("refCode");
	int status = (Integer) request.getAttribute("status");
%>

<script>

//냉장고 품목 클릭시 해당 위치에 상품 상세 정보 레이어 생성
$('.item').click(function(e) {
	var divTop = e.clientY;
	var divLeft = e.clientX;
	
	var serial = $(this).attr("serial"); 
	
	//var idx = $(this).attr("idx"); 
	//$('#divView').empty().append('<div style="position:absolute;top:5px;right:5px"><span id="close" style="cursor:pointer;font-size:1.5em" title="닫기">X</span> </div><br><a href="?serial=' + serial + '">serial</a><BR><a href="?idx=' + idx + '">idx</a>');
	$("#divView").css({ "top": divTop ,
						"left": divLeft , 
						"position": "absolute"})
					.show();
	$("#close").click(function(){
		$("#divView").css("display", "none")}); 
	
	var itemNo = $(this).prev().attr("data-itemNo")
	getItemDetail(itemNo);
	
});

// 상품상세 정보 영역 이외 클릭시 상세 정보 레이어 닫음

$(document).click(function (e) {
	if (!$(e.target).hasClass("item-holder")) {
			$("#divView").hide()
	}
}); 

// 냉장고 품목 상세정보 페이지 요청(ajax)
function getItemDetail(param) {
	
	$.ajax({
		type: "get",
		url: "/ref/items/detail", 
		dataType: "html", 
		data: {refCode: refCode, itemNo: param}, 
		success: function(res) {
			$("#detail-content-area").html(res)
		}, 
		error: function() {
			console.log("ajax 실패")
		}
	})
}
	
</script>

<%-- 필터링 정보 넘길 때 사용 --%>
<div id="test" data-value="<%= status %>" style="display: hidden;"></div>

<%-- 품목보여주기 --%>        
<% for (int i = 0; i < list.size(); i++) { %>
	<div data-itemNo="<%= list.get(i).getItemNo() %>" style="display: none;"></div>
	<div class="item" class="item-holder">
	
	<%-- D-day 계산  --%>
	<% 
	 	long diff = list.get(i).getExpireDate().getTime() - System.currentTimeMillis();
		long dDay = diff / (24 * 60 * 60 * 1000);
	%>
    
	<%-- dDay에 따른 색 구분 --%>	
	<% if (dDay <= 0 ) { %>
		<div style="background: #FF6666;" class="item-holder"><span>D-Day <%= dDay %></span></div>
	<% } else { %>
		<div style="background: #B4EEB4;" class="item-holder"><span>D-Day <%= dDay %></span></div>
	<% } %>
   	<!-- 재료구분 코드에 따른 아이콘 불러오기 -->
   	<% if (list.get(i).getStatus() == 0){ %>
	<img src="/resources/img/ingrCty/<%= list.get(i).getIngrCtyCode() %>.png" class="item-holder" style="background: #B1E693;">
   	<% } else if(list.get(i).getStatus() == 1) { %>
	<img src="/resources/img/ingrCty/<%= list.get(i).getIngrCtyCode() %>.png" class="item-holder" style="background: #D6E5FA;" class="item-holder">	
   	<% } else if(list.get(i).getStatus() == 2) { %>
   	<img src="/resources/img/ingrCty/<%= list.get(i).getIngrCtyCode() %>.png" class="item-holder" style="background: beige;">
   	<% } %>
   	<%-- 보관상태에 따른 색 구분 --%>
<!-- *** 글자수 많으면 넘어가는거 해결하기 *** -->
	<% if (list.get(i).getStatus() == 0){ %>
		<div style="background: #B1E693;" class="item-holder"><%= list.get(i).getItemName() %></div>
	<% } else if(list.get(i).getStatus() == 1) { %>
		<div style="background: #D6E5FA;" class="item-holder"><%= list.get(i).getItemName() %></div>
	<% } else if(list.get(i).getStatus() == 2) { %>
		<div style="background: beige;" class="item-holder"><%= list.get(i).getItemName() %></div>
	<% } %>
	</div>
	
<% } %>

<!-- 아이템 상세 정보 팝업 레이어 --> 
<div id="divView" class="item-holder">
	<div id="detail-content-area" class="item-holder"></div>
</div>
