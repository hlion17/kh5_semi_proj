<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF\views\community\layout\recipeHeader.jsp" %>

`<link rel="preconnect" href="https://fonts.googleapis.com">

<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css");
*{
	font-family: 'Jua', sans-serif;
}
div { 
	border: none !important;
} 
/* button {
	margin: 5%;
} */
.margin {
	margin: 5%;
}
.margin-top{
	margin: 5%;
}
#btnWrite:before {
	content: '\F4CB';
	font-family : bootstrap-icons;
}
#btnCancel:before {
	content: '\F623';
	font-family : bootstrap-icons;
} 
</style>

<!-- 스마트에디터2 설치 -->
<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//submit전에 스마트에디터에 작성된 내용을 <textarea>로 반영한다
		submitContents( $("#btnWrite") );
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});

//스마트에디터에 작성한 내용을 <textarea>에 반영하는 함수
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<style type="text/css">
#content {
	width: 100%;
}
</style>

<div id="section">
	<div class="margin-top"></div>
	<!-- <h3>게시글 쓰기</h3> -->
	<hr>

	<div>
		<form action="./insert" method="post" enctype="multipart/form-data">

			<table class="table table-bordered">
				<tr><td class="info">아이디</td><td><%=session.getAttribute("memberid") %></td></tr>
				<tr><td class="info">닉네임</td><td><%=session.getAttribute("nick") %></td></tr>
				<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
				<tr><td class="info" colspan="2">본문</td></tr>
				<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
			</table>
			
			첨부파일 <input type="file" name="file">
		
		</form>
	</div>

	<div class="text-center">	
		<button type="button" id="btnWrite" class="btn btn-info">&nbsp;작성</button>
		<button type="button" id="btnCancel" class="btn btn-danger">&nbsp;취소</button>
	</div>
	<div class="margin"></div>

</div><!-- #section -->

<!-- <textarea>태그에 스마트에디터2를 스킨 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //스킨을 적용할 <textarea>의 id를 적어준다
	sSkinURI: "../resources/se2/SmartEditor2Skin.html",
	fCreate: "createSEditor2"
})
</script>

<%@ include file="/WEB-INF\views\community\layout\recipeFooter.jsp" %>