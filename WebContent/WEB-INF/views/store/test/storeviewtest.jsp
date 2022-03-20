
<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%@ page import = "user.dao.UserMenuDAO" %> --%>
<%--     <%@ page import = "admin.dto.ProductDTO" %> --%>
    <%@ page import = "java.util.*" %>
    <%
    request.setCharacterEncoding("UTF-8");
    %>


<title>상품 보기</title>

<%@include file = "/WEB-INF/views/layout/header.jsp" %>


<div id="product" align="center">

<%--사이드 메뉴 끌어오기 --%>
<%-- <%@ include file ="aside.jsp" %> --%>

<link href="style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	<%--상품 추가하기 --%>
	function addPd(){
		var str = '';
		var sendZone = document.getElementById("sendZone");
		var size = document.getElementsByName("size")[0].value;
		var color = document.getElementsByName("color")[0].value;
		var qty = document.getElementsByName("qty")[0].value;
	
		if(size==""||color==""||qty==""){
			str = "정확히 확인해주세요.";
		}else{
			str= size+" "+ color+" "+ qty+"개";	
		}
		sendZone.innerHTML = str
	}
	<%--장바구니 --%>
	function emptyCart(){
		var size = document.getElementsByName("size")[0].value;
		var color = document.getElementsByName("color")[0].value;
		var qty = document.getElementsByName("qty")[0].value;
	
		if(size==""||color==""||qty==""){
			alert("옵션을 입력해주세요.");
		}
	}
	
	<%--장바구니 담기 버튼 --%>
	function fnCart(name, price){
		if(confirm("장바구니에 담으시겠습니까?")){
			location.href="Cart.jsp?name=" +name+"&price"+price;
		}
	}
	<%--장바구니 이동 버튼 --%>
	function ifView(){
		if(confirm("장바구니를 보시겠습니까?")){
			location.href="CartView.jsp"
		}
	}
	
</script>

<%-- 에러나서 주석처리 해놓음

<%
	//상품 코드 받기
	String productCode = request.getParameter("pdCode");
	int pdCode = Integer.parseInt(productCode);
	
	//상품정보 가져오기
	// 에러나서 주석처리함
	UserMenuDAO dao = new UserMenuDAO();
	String productImg1 = dao.getCateThumbImg(pdCode); //이미지 가져오기
	Product dto = dao.productInfo(pdCode); //상품이름, 가격 가져오기
	List size = null; 
	List color = null; 
	size = dao.pdSize(pdCode);
	color = dao.pdColor(pdCode); //상품 옵션 가져오기
	
%>

<main>
<h1><a class="title" href="main.jsp">BAD GUDU</a></h1>
<br />
	<table>
		<tr>
			<td rowspan='5'>
				<img src="${pageContext.request.contextPath}\productImg\<%=productImg1 %>" width="700" height="700"/>
			</td>
			<td align="center" width='400' height='150'>
				<h3><%=dto.getProductName() %></h3><br /><br/>
				<h4><%=dto.getSellingPrice() %>원</h4>
			</td>
		</tr>
		<tr>
			<td align="center" width='400' height='50'>
				<input type="button" value="재고확인" style="width:300px; height:40px; font-size:12pt" />
			</td>
		</tr>
		<tr>
			<td align="center" width='400' height='100'>
				사이즈 : 
				<select name="size">
					<option value="">사이즈 선택</option>
				<%
					for(int i = 0; i<size.size();i++){
						%><option value=<%=size.get(i) %>><%=size.get(i) %></option> <%
					}
				%>
				</select><br/><br/>
			
				색상: 
				<select name="color">
					<option value="">컬러 선택</option>
				<%
					for(int i = 0; i<color.size();i++){
						%><option value=<%=color.get(i) %>><%=color.get(i) %></option> <%
					}
				%>
				</select> <br/><br/>
				수량 : <input type="number" name="qty" value="1" style="width:40px"/>
				<br/><br/>
				<input type="button" name="add" id="add" value="추가 하기" onclick="addPd();" style="width:300px; height:50px; font-size:12pt" />
			</td>
		</tr>
		<tr>
			<td align="center" width='400' height='50'>
				<div id="sendZone"></div>
			</td>
		</tr>
		<tr>
			<td align="center" width='400'>
				<input type="button" name="addCart" value="장바구니 추가" onclick="emptyCart();" style="width:150px; height:50px; font-size:12pt"/>
				<input type="button" name="order" value="주문하기" style="width:150px; height:50px; font-size:12pt"/>
			</td>
		</tr>
	</table>

--%>

</main>
</div>

</body>
</html>