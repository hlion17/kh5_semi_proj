<%@page import="dto.SocialMember"%>
<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

<%	SocialMember updateBoard = (SocialMember) request.getAttribute("updateBoard"); %>

<!-- 스마트에디터 2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents( $("#btnUpdate") )
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	
	
	//파일이 없을 경우
	if(<%=updateBoard.getStored_name() != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=updateBoard.getStored_name() == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
		//이걸 눌렀을때 원본이나 추가파일 둘다 삭제되어야함 추후 구현
		//제일 좋은건 기존 첨부파일과 새로 첨부파일을 동시에 보여주고
		//그걸 여러파일로 할 수 있게 구현하고
		//필요한것만 삭제를 바로바로 반영할수있게 하는것
	})

});
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div id="section">

	<h3>프로필 수정</h3>
	<hr>

	<div>
		<form action="/social/profile/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="memberno" value="<%=updateBoard.getMemberno() %>" />
			<table class="table table-bordered">
			<tr><td class="info">회원번호</td><td colspan="3"><%=updateBoard.getMemberno() %></td></tr>
			<tr><td class="info">아이디</td><td colspan="3"><%=updateBoard.getMemberid() %></td></tr>
			<tr><td class="info">이름</td><td colspan="3"><%=updateBoard.getMembername() %></td></tr>
			<tr><td class="info">닉네임</td><td colspan="3"><%=updateBoard.getNick() %></td></tr>
			<tr><td class="info">성별</td><td colspan="3"><%=updateBoard.getGender() %></td></tr>
			<tr><td class="info">이메일</td><td colspan="3"><%=updateBoard.getEmail() %></td></tr>
			<tr><td class="info">연락처</td><td colspan="3"><%=updateBoard.getPhone() %></td></tr>
			<tr><td class="info">우편번호</td><td colspan="3"><%=updateBoard.getZipcode() %></td></tr>
			<tr><td class="info">주소</td><td colspan="3"><%=updateBoard.getAddress() %></td></tr>
			<tr><td class="info">소개글</td><td colspan="3"><%=updateBoard.getIntro() %></td></tr>
			<tr><td class="info">냉장고번호</td><td colspan="3"><%=updateBoard.getMy_ref_code() %></td></tr>
<!-- 			나중에 각 항목에 버튼넣어서 보여주는 요소들을 사용자가 원하는대로 제한할수있게 -->
<%-- 				<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="<%=updateBoard.getNick() %>"/></td></tr> --%>
			</table>
			
			<!-- 첨부파일 -->
			<div>
				<div id="beforeFile">
					<%	if( updateBoard.getStored_name() != null ) { %>
							기존 첨부파일: 
							<img src="<%=request.getContextPath() %>/upload/<%=updateBoard.getStored_name() %>" 
									alt="그림을 불러오지못함" width="100%" height="100%">
							<br>
							<a href="<%=request.getContextPath() %>/upload/<%=updateBoard.getStored_name() %>"
							 		download="<%=updateBoard.getOrigin_name() %>">
								<%=updateBoard.getOrigin_name() %>
							</a>
							<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
					<%	} %>
				</div>
			
				<div id="afterFile">
					새 첨부파일:
					<input type="file" name="file" accept="image/*" />
				</div>
			</div>
			<br>
		</form>
	</div>

	<div class="text-center">	
		<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</div><!-- #section -->


<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>