<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>    
    
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
<strong><%=session.getAttribute("nick") %>님, 환영합니다</strong><br><br>

<button onclick="location.href='<%=request.getContextPath() %>/member/info'">회원정보</button>
<button onclick="location.href='<%=request.getContextPath() %>/board/notice'">공지사항 가기</button>
<button onclick="location.href='<%=request.getContextPath() %>/recipe/board'">레시피 자랑 게시판</button>
<button onclick="location.href='<%=request.getContextPath() %>/review/list'">리뷰게시판</button>
<button onclick="location.href='<%=request.getContextPath() %>/store'">상점</button>
<button onclick="location.href='<%=request.getContextPath() %>/member/logout'">로그아웃</button>
<%	} %>


</div>

</div><!-- .container -->