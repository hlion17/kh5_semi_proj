<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container"><!---------------- .container ---------------------->

<div id="sidebar">
        <ul>
<!--             <li onclick="location.href = '/recipe/board'">레시피 자랑</li> -->
<!--             <li onclick="location.href = '/rank/recipe'">레시피 랭킹</li> -->
<!--             <li onclick="location.href = '/rank/member'">유저 랭킹</li> -->
            
            <li><a href="/social/profile">마이 프로필</a></li>
            <li><a href="/social/recipe">마이 레시피</a></li>
            <li><a href="/social/followee">마이 팔로우</a></li>
            <li><a href="/social/follower">마이 팔로워</a></li>
        </ul>
</div>