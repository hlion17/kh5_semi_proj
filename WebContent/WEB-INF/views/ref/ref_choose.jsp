<%@page import="dto.Ref"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Ref> refList = (List)request.getAttribute("list");
	String memberId = (String) session.getAttribute("memberid");
	int myRefCode = (Integer) session.getAttribute("refCode");
	String name = (String) session.getAttribute("membername");
%>

<!-- header page -->        
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<script>
$(document).ready(function() {
	$(".btn-cancle-share").click(function() {
		const targetRefName = $(this).prev().text()
		const targetMemberNo = $(this).attr("data-targetMemberNo");
		console.log(targetRefName)
		console.log(targetMemberNo)
		const isCancling = confirm("정말로 " + targetRefName + "의 공유를 중단하시겠습니까?")
		if (isCancling == true) {
			location.href = "/ref/share/cancle?myRefCode=" + <%=myRefCode%> + "&targetMemberNo=" + targetMemberNo
		}
	})
})
</script>

<style>
#share-item-container {
	display: flex;
	flex-flow: column wrap;
	justify-content: center;
	align-items: center;
	
	height: 400px;
	
}
#share-item-container .share-item {
	width: 400px;
	text-align: center;
}
</style>

<main>
<div id="main">

<div class="container">
<div id=share-item-container>
	<div class="share-item">
		<button onclick="location.href='/ref/itemlist?refCode=<%=myRefCode%>'"><%=name%>의 냉장고</button>
	</div>
<% for (int i = 0; i < refList.size(); i++) { %>
	<% if (refList.get(i).getRefCode() != myRefCode) { %>
	<div class="share-item">
		<button onclick="location.href='/ref/itemlist?refCode=<%= refList.get(i).getRefCode() %>'"><%= refList.get(i).getRefName() %></button>
		<button class="btn-cancle-share" data-targetMemberNo="<%= refList.get(i).getYourMemberNo() %>">공유취소</button>
	</div>
	<% } %>
<% } %>
</div>
</div>

</div>
</main>

<!-- footer page -->
<%@include file = "/WEB-INF/views/layout/footer.jsp" %>