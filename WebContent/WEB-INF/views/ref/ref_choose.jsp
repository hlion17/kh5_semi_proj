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
		
	// 버튼을 눌렀을 떄 공유 취소 요청 보내기
	$(".btn-cancle-share").click(function() {
		console.log("클릭")
		const targetRefName = $(this).prev().text()
		const targetMemberNo = $(this).attr("data-targetMemberNo");
		const isCancling = confirm("정말로 " + targetRefName + "의 공유를 중단하시겠습니까?")
		if (isCancling == true) {
			location.href = "/ref/share/cancle?myRefCode=" + <%=myRefCode%> + "&targetMemberNo=" + targetMemberNo
		}
	})
	
	// 페이지 띄울 때 자동 실행하는 거
	// 문제 있음 일단 보류
	//$("#ref-modal").hide();
	//$("#ref-modal").trigger("click");
	
	
	// 냉장고 선택 모달창 띄우기
	$('#ref-modal').on('shown.bs.modal', function () {
  		$('#myInput').focus()
	})
})
</script>

<style>

/* 메인 div css 무시 */
div {
/*  	border: none;
	background: white; */
}

#share-item-container {
	display: flex;
	flex-flow: column wrap;
	justify-content: center;
	align-items: center;
	
	height: 400px;
	
}
#share-item-container .share-item {
	width: 220px;
	margin-top: 10px;
	/* text-align: center; */
}
</style>

<main>
<div id="main">

<div class="container">
<div id=share-item-container>
	<div class="share-item">
		<button type="button" class="btn btn-info" onclick="location.href='/ref/itemlist?refCode=<%=myRefCode%>'"><%=name%>의 냉장고</button>
	</div>
<% for (int i = 0; i < refList.size(); i++) { %>
	<% if (refList.get(i).getRefCode() != myRefCode) { %>
	<div class="share-item">
		<button type="button" class="btn btn-primary" onclick="location.href='/ref/itemlist?refCode=<%= refList.get(i).getRefCode() %>'"><%= refList.get(i).getRefName() %></button>
		<button type="button" class="btn btn-danger btn-cancle-share" data-targetMemberNo="<%= refList.get(i).getYourMemberNo() %>">공유취소</button>
	</div>
	<% } %>
<% } %>
</div>

<!-- 냉장고 선택 모달창 띄우는 버튼 -->
<button id="ref-modal" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ref-choose">
 모달창 테스트
</button>

</div>

</div>
</main>


<!-- 냉장고 선택 모달창 -->
<div class="modal fade" id="ref-choose">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h3 class="modal-title"><strong>냉장고를 선택해주세요</strong></h3>
			</div>
			<div class="modal-body">
				<button type="button" class="btn btn-info" onclick="location.href='/ref/itemlist?refCode=<%=myRefCode%>'"><%=name%>의 냉장고</button>
				<% for (int i = 0; i < refList.size(); i++) { %>
					<% if (refList.get(i).getRefCode() != myRefCode) { %>
					<div class="share-item">
						<button type="button" class="btn btn-primary" onclick="location.href='/ref/itemlist?refCode=<%= refList.get(i).getRefCode() %>'"><%= refList.get(i).getRefName() %></button>
						<button type="button" class="btn btn-danger" class="btn-cancle-share" data-targetMemberNo="<%= refList.get(i).getYourMemberNo() %>">공유취소</button>
					</div>
					<% } %>
				<% } %>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>



<!-- footer page -->
<%@include file = "/WEB-INF/views/layout/footer.jsp" %>