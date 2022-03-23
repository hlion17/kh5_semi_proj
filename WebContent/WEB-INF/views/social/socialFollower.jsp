<%@page import="dto.Follow"%>
<%@page import="dto.SocialMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF\views\social\layout\socialHeader.jsp" %>

<%	List<SocialMember> boardList = (List) request.getAttribute("boardList"); %>
<%	List<Follow> followList = (List) request.getAttribute("followList"); %>

<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/recipe/insert";
	});
	
});
</script>


<div id="section">

	<h1>마이 팔로워</h1>
	<hr>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<tr class="success">
			<th>이미지</th>
			<th>순위</th>
			<th>팔로우수</th>
			<th>닉네임</th>
			<th>아이디</th>
			<th>성별</th>
			</tr>
			
		<% System.out.println("boardList.size() : " + boardList.size()); %>
		<% System.out.println("followList.size() : " + followList.size()); %>
		
		<%	for(int i=0; i<boardList.size(); i++) { %>
			<%	for(int j=0; j<followList.size(); j++) { %>
				
				<% System.out.println("boardList.get(i).getMemberno() : " + boardList.get(i).getMemberno()); %>
				<% System.out.println("followList.get(j).getFollower() : " + followList.get(j).getFollower()); %>
				
<!-- 				바깥족 포문 유저넘버가 현로그인세션이팔로우한유저가맞으면 출력 -->
				<%	if( boardList.get(i).getMemberno() == followList.get(j).getFollower() ) {  %>
		
					<tr onclick="location.href='<%=request.getContextPath() %>/social/member?memberno=<%=boardList.get(i).getMemberno() %>'">
						<td>
					<!-- 첨부파일 -->
					<%	if( boardList != null ) { %>
						<img src="<%=request.getContextPath() %>/resources/img/social/<%=boardList.get(i).getStored_name() %>" 
								alt="그림을 불러오지못함" width="100" height="100"><br>
						<a href="<%=request.getContextPath() %>/upload/<%=boardList.get(i).getStored_name() %>"
								download="<%=boardList.get(i).getOrigin_name() %>">
							<%=boardList.get(i).getOrigin_name() %>
						</a>
					<%	} %>
					
						</td>
						<td><%=boardList.get(i).getDense_rank() %> 위</td>
						<td><%=boardList.get(i).getFollowCnt() %></td>
						<td><%=boardList.get(i).getNick() %></td>
						<td><%=boardList.get(i).getMemberid() %></td>
						<td><%=boardList.get(i).getGender() %></td>
<%-- 						<td><%=boardList.get(i).getEmail() %></td> --%>
					</tr>
				
				
				<%	} %>
				
			<%	} %>
		<%	} %>
		</table>
	</div>

<%@ include file="/WEB-INF/views/community/rank/m_paging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\social\layout\socialFooter.jsp" %>