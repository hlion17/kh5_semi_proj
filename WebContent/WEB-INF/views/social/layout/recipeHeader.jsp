<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container"><!---------------- .container ---------------------->

<div id="sidebar">
        <ul>
<!--             <li onclick="location.href = '/recipe/board'">레시피 자랑</li> -->
<!--             <li onclick="location.href = '/rank/recipe'">레시피 랭킹</li> -->
<!--             <li onclick="location.href = '/rank/member'">유저 랭킹</li> -->
            
            <li><a href="/recipe/board">레시피 자랑</a></li>
            <li><a href="/rank/recipe">레시피 랭킹</a></li>
            <li><a href="/rank/member">유저 랭킹</a></li>
        </ul>
</div>