<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">TIS</a> //메인 페이지

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Login</a> //로그인 페이지
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Join</a> //회원가입 페이지
    </li>
     <li class="nav-item">
      <a class="nav-link" href="#">Users</a> //회원목록 페이지
    </li>

    <!-- Dropdown -->
    <li class="nav-item dropdown"> //그 외 메뉴 페이지
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        	download link
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#">Link 1</a>
        <a class="dropdown-item" href="#">Link 2</a>
        <a class="dropdown-item" href="#">Link 3</a>
      </div>
    </li>
  </ul>
</nav>
</body>
</html>