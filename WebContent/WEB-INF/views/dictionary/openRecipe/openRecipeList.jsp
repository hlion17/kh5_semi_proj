<%@ page import="dto.OpenRecipe" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- header page -->
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<main>
<div id="main">

	<!-- 사전 사이드바 -->
    <%@include file="/WEB-INF/views/layout/dictionary/dic_sidebar.jsp" %>
    
    <div id="section">

<h1>공식레시피 검색 결과</h1>
<hr>
<% if (request.getAttribute("pageCnt") != null) { %>
    <% int pageCnt = (Integer) request.getAttribute("pageCnt"); %>
    총 검색결과 개수: <%= request.getAttribute("total") %><br>
    총 페이지 개수: <%= pageCnt %>
<ul>
    <% for (int i = 1; i <= pageCnt; i++) {%>
    <a href="/openrecipe/pagelist?post=y&curPage=<%= i %>&item=<%= request.getAttribute("item") %>"><li style="display: inline-block"><%= i %></li></a>
    <% } %>
</ul>

<% } %>

<% List<OpenRecipe> list = (List<OpenRecipe>) request.getAttribute("list"); %>

<% for(OpenRecipe o : list) { %>
<div>
    <h3>레시피명: <%= o.getData1() %></h3><br>
    일련번호: <%= o.getSeq() %><br>
    요리종류: <%= o.getPat() %><br>
    열량: <%= o.getEng() %><br>
    단백질: <%= o.getPro() %><br>
    지방: <%= o.getFat() %><br>
    나트륨: <%= o.getNa() %><br>
    메인이미지: <br>
    <% if (!"".equals(o.getMainImg())) {%>
    <img src="<%= o.getMainImg() %>" style="height: 300px;width: 300px"><br>
    <% } %>
    재료정보: <%= o.getIngrs() %><br>
    조리방법: <%= o.getData2() %><br>
    조리메뉴얼: <br>
    <% for (Map<String, String> map : o.getData4()) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iter = entries.iterator();
        if (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
    %>
    <% if (!"".equals(next.getValue())) {%>
            <img src="<%=next.getValue()%>" style="width: 200px;height: 200px"> <br>
    <% } %>
            <%=next.getKey()%> <br>
    <%
        }
    } %>
</div>
<% }%>

    </div>
</div>
</main>

<!-- footer page -->
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
