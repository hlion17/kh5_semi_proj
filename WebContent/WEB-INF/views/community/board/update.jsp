<%@page import="dto.RecipeFile"%>
<%@page import="dto.Recipe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<%	Recipe updateBoard = (Recipe) request.getAttribute("updateBoard"); %>
<%	RecipeFile boardFile = (RecipeFile) request.getAttribute("boardFile"); %>

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
	if(<%=boardFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=boardFile == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})

});
</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}
</style>

<div class="container">

<h3>게시글 쓰기</h3>
<hr>

<div>
<form action="/recipe/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardno" value="<%=updateBoard.getBoardno() %>" />

<table class="table table-bordered">
<tr><td class="info">아이디</td><td><%=updateBoard.getUserid() %></td></tr>
<tr><td class="info">닉네임</td><td><%=request.getAttribute("writerNick") %></td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%" value="<%=updateBoard.getTitle() %>"/></td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"><%=updateBoard.getContent() %></textarea></td></tr>
</table>

<!-- 첨부파일 -->
<div>

	<div id="beforeFile">
<%	if( boardFile != null ) { %>
		기존 첨부파일: 
		<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
		 download="<%=boardFile.getOriginname() %>">
			<%=boardFile.getOriginname() %>
		</a>
		<span id="delFile" style="color:red; font-weight: bold; cursor: pointer;">X</span>
<%	} %>
	</div>

	<div id="afterFile">
		새 첨부파일:
		<input type="file" name="file""/>
	</div>
</div>

<br>
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- .container -->
</div>


<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
