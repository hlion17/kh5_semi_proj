<%@page import="dto.Review"%>
<%@page import="dto.ReviewFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<script type="text/javascript" src="../resources/se2/js/service/HuskyEZCreator.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<%	Review viewReview = (Review) request.getAttribute("viewReview"); %>
<%	ReviewFile reviewFile = (ReviewFile) request.getAttribute("reviewFile"); %>


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

<div class="container">

<h3>리뷰 쓰기</h3>
<hr>

<div>
	<form action="./write" method="post" enctype="multipart/form-data">
	
	
	<table class="table table-bordered">
	
	<tr><td class="info">회원번호</td><td><%=session.getAttribute("memberno") %></td></tr>
	<tr><td class="info">닉네임</td><td><%=session.getAttribute("nick") %></td></tr>
	<tr><td class="info">상품번호</td><td><input type="text" name="pro_no"></td></tr>
<%-- 	<tr><td class="info">상품번호</td><td><%=viewReview.getPro_no() %></td> --%>
	<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
	<tr><td class="info" colspan="2">본문</td></tr>
<%-- 	<tr><td><input type="hidden" name="pro_no" value="<%=viewReview.getPro_no() %>"/></td></tr> --%>
	
	<tr><td colspan="4"></td></tr>
	</table>
	
	첨부파일 <input type="file" name="file">
	
	<textarea id="content" name="content"></textarea>
	
	</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

<!-- .container -->
</div>



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

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>