<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<div class="container">

<div class="text-center">

<%-- 비로그인 상태 --%>
<%	if( session.getAttribute("login") == null ) { %>
<strong>로그인이 필요합니다</strong><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/member/login'">로그인</button>
<button onclick="location.href='<%=request.getContextPath() %>/member/join'">회원가입</button>
<%	} %>

<%-- 로그인 상태 --%>
<%	if( session.getAttribute("login") != null && (boolean) session.getAttribute("login") ) { %>
<strong><%=session.getAttribute("usernick") %>님, 환영합니다</strong><br><br>
<button onclick="location.href='<%=request.getContextPath() %>/board/list'">게시판 가기</button>
<button onclick="location.href='<%=request.getContextPath() %>/member/logout'">로그아웃</button>
<%	} %>

</div>

</div><!-- .container -->