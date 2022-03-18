<%@page import="dto.RefItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	RefItem refItem = (RefItem) request.getAttribute("refItem");
	int refCode = (Integer) request.getAttribute("refCode");
%>

<script>
// 품목 삭제 요청
function deleteItem() {
	location.href = "/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= refItem.getItemNo() %>"
}
</script>

<form action="/ref/items/update" method="post" class="item-holder">
<input type="hidden" name="refCode" value=<%= refCode %>>
<input type="hidden" name="itemNo" value=<%= refItem.getItemNo() %>>
품목이름: <input class="item-holder" type="text" name="itemName" value=<%= refItem.getItemName() %>><br>
품목수량: <input class="item-holder" type="text" name="itemQty" value=<%= refItem.getItemQty() %>><br>
보관상태: <br>
냉장: <input class="item-holder" type="radio" name="status" value="0" checked="checked">
냉동: <input class="item-holder" type="radio" name="status" value="1">
실온: <input class="item-holder" type="radio" name="status" value="2">
<br>
등록일: <%= refItem.getRegDate() %><br>
유통기한: <input class="item-holder" type="date" name="expireDate" value=<%= refItem.getExpireDate() %>><br>
메모: <textarea class="item-holder" rows="3" name="note"><%= refItem.getNote() %></textarea>

<button class="item-holder" type="submit">제출</button>
<button class="item-holder" type="button" onclick="deleteItem()">삭제</button>
<button type="button">취소</button>
</form>

