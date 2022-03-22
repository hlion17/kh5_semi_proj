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

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 --> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<style>
/* div {
	border: none;
	background: white;
} */
</style>

<script>

$(document).ready(function() {
	
	
	
	// 버튼을 눌렀을 떄 공유 취소 요청 보내기
	$(".btn-cancle-share").click(function() {
		const targetRefName = $(this).prev().text()
		const targetMemberNo = $(this).attr("data-targetMemberNo");
		const isCancling = confirm("정말로 " + targetRefName + "의 공유를 중단하시겠습니까?")
		if (isCancling == true) {
			location.href = "/ref/share/cancle?myRefCode=" + <%=myRefCode%> + "&targetMemberNo=" + targetMemberNo
		}
	})

})
</script>

<button type="button" class="btn btn-info" onclick="location.href='/ref/itemlist?refCode=<%=myRefCode%>'"><%=name%>의 냉장고</button>
<% for (int i = 0; i < refList.size(); i++) { %>
	<% if (refList.get(i).getRefCode() != myRefCode) { %>
	<div class="share-item">
		<button type="button" class="btn btn-primary" onclick="location.href='/ref/itemlist?refCode=<%= refList.get(i).getRefCode() %>'"><%= refList.get(i).getRefName() %></button>
		<button type="button" class="btn btn-danger" class="btn-cancle-share" data-targetMemberNo="<%= refList.get(i).getYourMemberNo() %>">공유취소</button>
	</div>
	<% } %>
<% } %>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>