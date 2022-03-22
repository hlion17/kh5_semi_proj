<%@ page import="java.util.List" %>
<%@ page import="dto.ExpireDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<ExpireDate> list = (List<ExpireDate>) request.getAttribute("list"); %>

<style>
div {
    /*border: 1px solid black;*/
}
#item-container {
    display: flex;
    flex-flow: column nowrap;
    overflow: auto;
    height: 600px;
}
.item {
    margin: 20px 0;
    padding: 20px;
    width: 70%;
    background: beige;
    border-radius: 20px;
}
.item span {
 	font-weight: bold;
}
.item hr {
	border-top: 1px solid black;
}
.item > :nth-child(3){
    background: beige;
    padding: 5px;
}
</style>

<div id="item-container">
	<% for(ExpireDate e : list) { %>
	<div class="item">
       <div><h3><%= e.getData1() %></h3></div>
       <hr>
       <div>
           <p><span>유통기한: </span><%= e.getData2() %></p>
           <p><span>업소명: </span><%= e.getData3() %></p>
           <p><span>유형: </span><%= e.getData4() %></p>
       </div>
    </div>
	<% }%>
</div>

    


<!-- footer page -->
<%-- <%@include file="/WEB-INF/views/layout/footer.jsp" %> --%>
