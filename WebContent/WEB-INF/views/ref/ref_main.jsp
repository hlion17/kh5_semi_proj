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

<script type="text/javascript">

function filterAndSort(a) {
	var status = a.getAttribute("data-value")
	location.href = '/ref/itemlist/filterAndSort?refCode=<%= refCode %>&status=' + status + '&orderBy='+ orderBy.options[orderBy.selectedIndex].value
}


</script>
<style type="text/css">
	.filtering {
		color: blue;
		cursor: pointer;
	}
</style>
</head>
<body>

<a class="filtering" data-value="4" onclick="filterAndSort(this);">전체목록</a>
<a class="filtering" data-value="0" onclick="filterAndSort(this);">냉장</a>
<a class="filtering" data-value="1" onclick="filterAndSort(this);">냉동</a>
<a class="filtering" data-value="2" onclick="filterAndSort(this);">상온</a>
<select name="orderBy" id="orderBy">
	<option value="regDate" selected="selected">등록일</option>
	<option value="expire_date">유통기한</option>
</select>


<a href="/ref/item/add?refCode=<%= refCode %>">품목추가</a>
<br><br>
<% for (int i = 0; i < list.size(); i++) { %>

품목번호: <%= list.get(i).getItemNo() %><br> 
품목이름: <%= list.get(i).getItemName() %><br>
품목분류: <%= list.get(i).getIngrCtyCode() %><br>
개수: <%= list.get(i).getItemQty() %><br>
상태: <%= list.get(i).getStatus() %><br>
등록일: <%= list.get(i).getRegDate() %><br>
유통기한: <%= list.get(i).getExpireDate() %><br>
메모: <%= list.get(i).getNote() %><br>
<a href="/ref/item/delete?refCode=<%= refCode %>&itemNo=<%= list.get(i).getItemNo() %>">위 품목 삭제</a>
<br><br>

<% } %>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>