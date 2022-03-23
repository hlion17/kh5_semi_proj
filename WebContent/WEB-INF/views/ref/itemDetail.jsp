<%@page import="dto.RefItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	RefItem refItem = (RefItem) request.getAttribute("refItem");
	int refCode = (Integer) request.getAttribute("refCode");
	
	int status = refItem.getStatus();
	String sts = "";
	
	switch (status) {
	case 0:
		sts = "냉장보관";
		break;
	case 1: 
		sts = "냉동보관";
		break;
	case 2: 
		sts = "상온보관";
		break;
	default:
		break;
	}
%>

<style>
/* #item-detail {
	width: 500px;
}
#item-detail .form-group {
	margin: 0 auto;
	width: 400px;
}
#item-detail .form-group label {
	margin: 0;
	padding-top: 12px;
} */
</style>

<script>
// 품목 삭제 요청
function deleteItem() {
	location.href = "/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= refItem.getItemNo() %>"
}

</script>

<div id="item-detail">
<form action="/ref/items/update" method="post" class="item-holder form-horizontal">
	
		<%-- 냉장고 코드, 아이템 코드 수정 요청 보낼 때 필요에서 히든 속성 지정 --%>
		<input type="hidden" name="refCode" value=<%= refCode %>>
		<input type="hidden" name="itemNo" value=<%= refItem.getItemNo() %>>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">품목이름</label>
	   		<div class="col-sm-9 item-holder">
				<p class="form-control-static item-holder"><%= refItem.getItemName() %></p>
	   		</div>
		</div>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">품목수량</label>
	   		<div class="col-sm-9 item-holder">
				<p class="form-control-static item-holder"><%= refItem.getItemQty() %></p>
	   		</div>
		</div>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">보관상태</label>
	   		<div class="col-sm-9 item-holder">
				<p class="form-control-static item-holder"><%= refItem.getItemName() %></p>
	   		</div>
		</div>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">등록일</label>
	   		<div class="col-sm-9 item-holder">
				<p class="form-control-static item-holder"><%= refItem.getRegDate() %></p>
	   		</div>
		</div>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">유통기한</label>
	   		<div class="col-sm-9 item-holder">
				<p class="form-control-static item-holder"><%= refItem.getExpireDate() %></p>
	   		</div>
		</div>
		
		<div class="form-group item-holder">
			<label class="col-sm-3 control-label item-holder">메모</label>
	   		<div class="col-sm-9 item-holder">
				<textarea class="form-control item-holder" rows="3" name="note"><%= refItem.getNote() %></textarea>
	   		</div>
		</div>
		
		<div class="form-group item-holder" style="padding-left: 20px;">
			<button type="submit" class="item-holder btn btn-primary">수정</button>
			<button type="button" class="item-holder btn btn-danger" type="button" onclick="deleteItem()">삭제</button>
			<button type="button" class="btn btn-default" id="#close">취소</button>
		</div>
</form>
</div>


<%-- 백업
<form action="/ref/items/update" method="post" class="item-holder form-horizontal">
<input type="hidden" name="refCode" value=<%= refCode %>>
<input type="hidden" name="itemNo" value=<%= refItem.getItemNo() %>>
품목이름: <input class="item-holder" type="text" name="itemName" value=<%= refItem.getItemName() %>><br>
품목수량: <input class="item-holder" type="text" name="itemQty" value=<%= refItem.getItemQty() %>><br>
보관상태: <%= sts %><br>
<br>
등록일: <%= refItem.getRegDate() %><br>
유통기한: <input class="item-holder" type="date" name="expireDate" value=<%= refItem.getExpireDate() %>><br>
메모: <textarea class="item-holder" rows="3" name="note"><%= refItem.getNote() %></textarea>

<button class="item-holder" type="submit">제출</button>
<button class="item-holder" type="button" onclick="deleteItem()">삭제</button>
<button type="button">취소</button>
--%>