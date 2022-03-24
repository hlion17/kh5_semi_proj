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

	<h1 id="my_follower">&nbsp;마이 팔로워</h1>
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
			
<%-- 		<% System.out.println("boardList.size() : " + boardList.size()); %> --%>
<%-- 		<% System.out.println("followList.size() : " + followList.size()); %> --%>
			<%	if( boardList != null ) { %>
		
				<%	for(int i=0; i<boardList.size(); i++) { %>
					<%	for(int j=0; j<followList.size(); j++) { %>
				
<%-- 				<% System.out.println("boardList.get(i).getMemberno() : " + boardList.get(i).getMemberno()); %> --%>
<%-- 				<% System.out.println("followList.get(j).getFollower() : " + followList.get(j).getFollower()); %> --%>
				
<!-- 				바깥족 포문 유저넘버가 현로그인세션이팔로우한유저가맞으면 출력 -->
						<%	if( boardList.get(i).getMemberno() == followList.get(j).getFollower() ) {  %>
							<tr onclick="location.href='<%=request.getContextPath() %>/social/member?memberno=<%=boardList.get(i).getMemberno() %>'">
								<td>
									<% if( boardList.get(i).getImage_no() != 0 ) { %>
										<!-- 첨부파일 -->
										<img src="<%=request.getContextPath() %>/resources/img/social/<%=boardList.get(i).getStored_name() %>" alt="" width="100" height="100">
									<% } else { %>
										<img src="<%=request.getContextPath() %>/resources/img/profileBasic/profile.jpg" alt="" width="100" height="100">
									<% } %>
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
			
		<%	} %>
		</table>
	</div>

<%@ include file="/WEB-INF/views/social/layout/werPaging.jsp" %>

</div><!-- #section -->

<%@ include file="/WEB-INF\views\social\layout\socialFooter.jsp" %>