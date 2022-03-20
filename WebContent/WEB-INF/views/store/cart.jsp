<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   
<% List<Cart> cartList = (List)request.getAttribute("ProductList.getPro_no()"); %>
<%out.println(session.getAttribute("id")); %>


<%@include file = "/WEB-INF/views/layout/header.jsp" %>

<%=session.getAttribute(".info") %>님 반갑습니다:D<br>


<%out.println(session.getAttribute("ProductList.setPro_no()")); %>

<% request.setCharacterEncoding("UTF-8"); %>
 
    <% ArrayList<String> arrr = (ArrayList) (session.getAttribute("productList")); %>
    <center>
        <h1>상품 결과</h1>
        <hr>
        <%=session.getAttribute("login")%>님의 장바구니 목록
        <hr>
        <% if (arrr == null) { %>
        장바구니에 넣은 상품이 없습니다.
        <% } else {
                for (String i : arrr) {
                    out.println(i); %><br>
                <% }
        } %>
        <br><br><hr>
    </center>



<button type="button" onclick="location.href='payment'">결제</button>
<button type="button" onclick="location.href='store'">쇼핑하기</button>

<%@include file = "/WEB-INF/views/layout/footer.jsp" %>