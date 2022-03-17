<%@page import="dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	List<Member> MemberInfoList = (List) request.getAttribute("MemberInfoList"); %>


<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	//회원정보수정 버튼 클릭 시 회원정보수정 페이지로 이동
	$("#btnUpdateInfo").click(function() {
		$(location).attr('href', '/member/updateinfo')
	})
})	
</script>



<div class="container">

<h1>회원정보</h1>
<hr>

<table class="table table-striped table-hover table-condensed">
<tr class="success">
	<th>이름</th>
	<th>이메일</th>
	<th>전화번호</th>
	<th>우편번호</th>
	<th>주소</th>
	<th>자기소개</th>
</tr>

<%	for(int i=0; i<MemberInfoList.size(); i++) { %>
<tr>
	<td><%=MemberInfoList.get(i).getMembername() %></td>
	<td><%=MemberInfoList.get(i).getEmail() %></td>
	<td><%=MemberInfoList.get(i).getPhone() %></td>
	<td><%=MemberInfoList.get(i).getZipcode() %></td>
	<td><%=MemberInfoList.get(i).getAddress() %></td>
	<td><%=MemberInfoList.get(i).getIntro() %></td>
</tr>
<%	} %> 

</table>

<!-- 글쓰기 버튼 -->
<div id="btnBox" class="pull-left"> <!-- float: left; 와 같음(왼쪽에 버튼 떠다니게) -->
	<button id="btnWrite" class="btn btn-primary">글쓰기</button> <!-- btn-primary -> 파란색 버튼 -->
</div>

<div>
		<button type="button" id="btnUpdateInfo" class="btn btn-info">회원정보 수정</button>
</div>
</div><!-- .container -->



	
</body>
</html>