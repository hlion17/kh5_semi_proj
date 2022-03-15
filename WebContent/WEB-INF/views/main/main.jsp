<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

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

<button>고객센터</button>
 <br> 
 <button>냉장고</button> 
 <button>요리사전</button> 
 <button>커뮤니티</button> 
 <button>상점</button>
