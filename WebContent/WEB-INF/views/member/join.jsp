<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/WEB-INF/views/layout/header.jsp" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <%-- 카카오 우편번호 API --%> 

<script>
function kakaopost() { //우편번호 검색 함수 with 카카오
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#zipcode").value = data.zonecode; //우편번호
           document.querySelector("#address").value =  data.address //주소
        }
    }).open();
}
</script> 

    

 <script type="text/javascript">
$(document).ready(function() {
	
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	
	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
// 		location.href = "/"
	})
	
})

//------------------------------------------------------------
//아이디 중복 검사

function registerCheckFunction() {
	var memberid = $('#memberid').val();
	$.ajax({
		type: 'POST',
		url: '/IdCheckController',
		data: {memberid : memberid},
		success: function(result) {
			if(result == 0) {
				$("#checkId").html('사용할 수 없는 아이디입니다.')
				$("#checkId").attr('color', 'red');
			} else {
				$("#checkId").html('사용할 수 있는 아이디입니다.')
				$("#checkId").attr('color', 'green');
				
			}
			
		},
		error : function () {
			alert("서버요청실패");
		}
		
	})
}


 //form validation
//	폼 데이터 유효성 검증

//	-> 유효성 값을 입력했는지 검증
//	-> 적당한 값이 아니면 form을 submit하지 않는다

//------------------------------------------------------------


//아이디, 비밀번호 조합 제한
var req_idpw = /^[A-Za-z]{1}[A-Za-z0-9_]{3,19}$/ //반드시 영문으로 시작 숫자+언더바 허용 4~20자리

//이메일 형식 제한
var req_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

//전화번호 형식 제한
var req_phone = /^\d{11}$/;

//우편번호 형식 제한
//var req_zipcode = /^\d{3}-?\d{3}$/u;
var req_zipcode = /^\d{5}$/;

//주소 형식 제한(한글 + 공백 + 숫자)
var req_address = /^[가-힣\s0-9]+$/;


//------------------------------------------------------------

window.onload = function () {
	
	//focus를 잃었을 때 blur이벤트 발생
	memberid.addEventListener("blur", function () {
		console.log("#memberid blur")
		
		validateId();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	memberid.addEventListener("keyup", function () {
		console.log("#memberid keyup")
		
		validateId();
	})
	
	//focus를 잃었을 때 blur이벤트 발생
	memberpw.addEventListener("blur", function () {
		console.log("#memberpw blur")
		
		validatePw();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	memberpw.addEventListener("keyup", function () {
		console.log("#memberpw keyup")
		
		validatePw();
	})
	/* //focus를 잃었을 때 blur이벤트 발생
	memberpw.addEventListener("blur", function () {
		console.log("#memberpw2 blur")
		
		validatePw2();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	memberpw.addEventListener("keyup", function () {
		console.log("#memberpw2 keyup")
		
		validatePw2();
	}) */
	//focus를 잃었을 때 blur이벤트 발생
	email.addEventListener("blur", function () {
		console.log("#email blur")
		
		validateEmail();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	email.addEventListener("keyup", function () {
		console.log("#email keyup")
		
		validateEmail();
	})
	phone.addEventListener("blur", function () {
		console.log("#phone blur")
		
		validatePhone();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	phone.addEventListener("keyup", function () {
		console.log("#phone keyup")
		
		validatePhone();
	})
	zipcode.addEventListener("blur", function () {
		console.log("#zipcode blur")
		
		validateZipcode();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	zipcode.addEventListener("keyup", function () {
		console.log("#zipcode keyup")
		
		validateZipcode();
	})
	/* address.addEventListener("blur", function () {
		console.log("#address blur")
		
		validateAddress();
	}) 
	
	//타이핑 할 때마다 확인하게(위아래 둘다 두는 편) keyup이벤트 발생
	address.addEventListener("keyup", function () {
		console.log("#address keyup")
		
		validateAddress();
	}) */
	
}

/*  //유효성 이중 검사
  $(document).ready(function () {
	
	//form태그에 submit이 발생했을 때 이벤트 처리
	//	-> submit수행 전에 동작한다.
	$("form").submit(function () {
		//console.log("submit event")
		
		//유효성 검사 결과에 따른 submit 
		if(!validateId() || !validatePw() /*||  !validatePw2 */ //||!validateEmail() ||!validatePhone() ||validateZipcode() /* ||validateAddress */) {
			
		//console.log("SUBMIT 중단됨")
		
		//submit을 중단시킨다.(return false를 submit에 쓰면)
		/* alert("입력하신 정보를 다시 확인해주세요!") */
		//return false;

		
		//}
		
		//return true;
		//alert("SUBMIT 수행됨")
	//}) 
	
// })  

//폼 아이디 검증 함수
function validateId() {
	//CASE 1. 문자열을 입력하지 않았을 때
	//	warnMsg를 보이지 않음
	//	버튼을 누를 때 submit되지 않음
	
	//CASE 2. 길이가 8~20이 아닌 입력
	//	warnMsg를 보여줌(8글자 이상 20글자 이하로 입력하세요)
	//	버튼을 누를 때 submit되지 않음
	
	
	//CASE 3. 길이가 8~20인 입력
	//	warnMsg를 보이지 않음
	//	버튼을 누를 때 submit됨
//----------------------------------------------------------------

	console.log(memberid.value)
	
	if(memberid.value=="") {
		warnMsgId.innerHTML = "";
		warnMsgId.style.display="none";
		return false;
		
	} else if(!req_idpw.test(memberid.value)) { //아이디 조합 검증
		warnMsgId.innerHTML = "아이디는 4~20자 사이의 영문 + 숫자 조합으로 입력해주세요";
		warnMsgId.style.display = "block";	
		return false;
			
	} else if(memberid.value.length<4 || memberid.value.length>20) { //아이디 길이 검증
		warnMsgId.innerHTML = "아이디는 4~20자 사이의 영문 + 숫자 조합으로 입력해주세요";
		warnMsgId.style.display = "block";
		return false;
			
	} else { //맞게 입력하면
		warnMsgId.innerHTML = "";
		warnMsgId.style.display = "none";
		return true;
		console.log("submit완료")
	}
	
	
}

//폼 비밀번호검증 함수
function validatePw() {
	//CASE 1. 문자열을 입력하지 않았을 때
	//	warnMsg를 보이지 않음
	//	버튼을 누를 때 submit되지 않음
	
	//CASE 2. 길이가 8~20이 아닌 입력
	//	warnMsg를 보여줌(8글자 이상 20글자 이하로 입력하세요)
	//	버튼을 누를 때 submit되지 않음
	
	//CAER 3. 비밀번호와 비밀번호 입력이 같지 않을 때
	//	warnMsg를 보여줌(비밀번호를 다시 확인하세요)
	//	버튼을 누를 때 submit되지 않음

	//CASE 4. 길이가 8~20인 입력, 비밀번호와 비밀번호 입력이 같으면
	//	warnMsg를 보이지 않음
	//	버튼을 누를 때 submit됨
//----------------------------------------------------------------

	console.log(memberpw.value)
	
	if(memberpw.value=="") {
		warnMsgPw.innerHTML = "";
		warnMsgPw.style.display="none";
		return false;
		
	} else if(!req_idpw.test(memberpw.value)) { //비밀번호 조합 검증
		warnMsgPw.innerHTML = "비밀번호는 4~20자 사이의 영문 + 숫자 조합으로 입력해주세요";
		warnMsgPw.style.display = "block";	
	//	warnMsgPw2.style.display = "block";	
		return false;
			
	} else if(memberpw.value.length<4 || memberpw.value.length>20) { //비밀번호 길이 검증
		warnMsgPw.innerHTML = "비밀번호는 4~20자 사이의 영문 + 숫자 조합으로 입력해주세요";
		warnMsgPw.style.display = "block";
		//	warnMsgPw2.style.display = "block";
		return false;
			
	/* } else if(memberpw.value != memberpw2.value) {
		warnMsgPw2.innerHTML = "비밀번호와 비밀번호 확인을 같게 입력해주세요.";;
		warnMsgPw.style.display = "block";
		warnMsgPw2.style.display = "block";
		return false; */
		
	} else { //맞게 입력하면
		warnMsgPw.innerHTML = "";
		/* warnMsgPw2.innerHTML = ""; */
		warnMsgPw.style.display = "none";
		/* warnMsgPw2.style.display = "none"; */
		return true;
		console.log("submit완료")
	} 
	 
	
}

/* function validatePw2() {
	console.log(memberpw2.value)
	
	if(memberpw.value=="") {
		warnMsgPw.innerHTML = "";
		warnMsgPw.style.display="none";
		return false;
	} else if(memberpw.value !== memberpw2.value) {
		warnMsgPw2.innerHTML = "비밀번호와 비밀번호 확인을 같게 입력해주세요.";;
		warnMsgPw2.style.display = "block";
		return false;
	} else {
		warnMsgPw2.innerHTML = "";
		warnMsgPw2.style.display = "none";
		return true;
		console.log("submit완료")
	}
} */

//이메일 유효성 검사
 function validateEmail() {
	console.log(email.value)
	
	if(email.value=="") {
		warnMsgEmail.innerHTML = "";
		warnMsgEmail.style.display="none";
		return false;
	} else if(!req_email.test(email.value)) {
		warnMsgEmail.innerHTML = "이메일 형식으로 입력해주세요.";
		warnMsgEmail.style.display = "block";
		return false;
	} else {
		warnMsgEmail.innerHTML = "";
		warnMsgEmail.style.display = "none";
		return true;
		console.log("submit완료")
	}
} 
//전화번호 유효성 검사
 function validatePhone() {
	console.log(phone.value)
	
	if(phone.value=="") {
		warnMsgPhone.innerHTML = "";
		warnMsgPhone.style.display="none";
		return false;
	} else if(!req_phone.test(phone.value)) {
		warnMsgPhone.innerHTML = "전화번호를 올바르게 입력해주세요.";
		warnMsgPhone.style.display = "block";
		return false;
	} else {
		warnMsgPhone.innerHTML = "";
		warnMsgPhone.style.display = "none";
		return true;
		console.log("submit완료")
	}
} 
//우편번호 유효성 검사
 function validateZipcode() {
	console.log(zipcode.value)
	
	if(zipcode.value=="") {
		warnMsgZipcode.innerHTML = "";
		warnMsgZipcode.style.display="none";
		return false;
	} else if(!req_zipcode.test(zipcode.value)) {
		warnMsgZipcode.innerHTML = "우편번호 찾기를 이용해주세요.";
		warnMsgZipcode.style.display = "block";
		return false;
	} else {
		warnMsgZipcode.innerHTML = "";
		warnMsgZipcode.style.display = "none";
		return true;
		console.log("submit완료")
	}
} 
 //주소 유효성 검사
 function validateAddress() {
	console.log(address.value)
	
	if(address.value=="") {
		warnMsgAddress.innerHTML = "";
		warnMsgAddress.style.display="none";
		return false;
	} else if(!req_address.test(address.value)) {
		warnMsgAddress.innerHTML = "우편번호 찾기를 이용해 주소를 적어주세요.";
		warnMsgAddress.style.display = "block";
		return false;
	} else {
		warnMsgAddress.innerHTML = "";
		warnMsgAddress.style.display = "none";
		return true;
		console.log("submit완료")
	} 
} 
</script>

<style type="text/css">
*{
	font-family: 'Jua', sans-serif;
}
form {
	width: 600px;
	margin: 0 auto;
}

.fieldBox {
	position: relative;
}

.msg { //fieldBox를 기준으로 위치 지정
	position: absolute;
	color : red;
	top: 0;
	left: 500px;
	font-size: 10px;
}
div{
	border: none;
}
.margin{
	margin: 50%;
}
.margin-top{
	margin: 10%;
}

</style>

<div class="container">

<form action="./join" method="post" class="form-horizontal">
<div class="fieldBox">

	<div class="margin-top"></div>
	
	<div class="form-group text-center">
		<h3>회원가입</h3><br><br>
	</div>
	<div class="form-group">
		<label for="memberid" class="control-label col-xs-2">* 아이디</label>
		<div class="col-xs-8">
			<input type="text" id="memberid" name="memberid" class="form-control input_id" required placeholder="아이디를 입력해주세요.">
				<div id="warnMsgId" class="msg" ></div>
			<button type="button" class="btn btn-primary" onclick="registerCheckFunction();">중복검사</button> <!-- 중복검사 -->
				<font id= "checkId" size="2"></font>
		</div>
	</div>

	<div class="form-group">
		<label for="memberpw" class="control-label col-xs-2">* 비밀번호</label>
		<div class="col-xs-8">
			<input type="password" id="memberpw" name="memberpw" class="form-control" required placeholder="비밀번호를 입력해주세요.">
				<div id="warnMsgPw" class="msg" ></div>
		</div>
	</div>
	
	<!-- <div class="form-group">
		<label for="memberpw2" class="control-label col-xs-2">비밀번호확인</label>
		<div class="col-xs-10">
			<input type="text" id="memberpw2" name="memberpw2" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
		<div id="warnMsgPw2" class="msg" ></div>
	</div> -->
	
	<div class="form-group">
		<label for="membername" class="control-label col-xs-2">* 이름</label>
		<div class="col-xs-8">
			<input type="text" id="membername" name="membername" class="form-control" required placeholder="이름을 입력해주세요." >
		</div>
	</div>
	

	<div class="form-group">
		<label for="nick" class="control-label col-xs-2">* 닉네임</label>
		<div class="col-xs-8">
			<input type="text" id="nick" name="nick" class="form-control" required placeholder="닉네임을 입력해주세요.">
		</div>
	</div>
	
	<div class="form-group">
		<label for="gender" class="control-label col-xs-2">* 성별</label>
		<div class="col-xs-8">
			<label class="radio-inline"><input type="radio" id="gender" name="gender" value="M" checked>남</label>
			<label class="radio-inline"><input type="radio" id="gender" name="gender" value="F" >여</label>
		</div>
	</div>
	

	<div class="form-group">
		<label for="email" class="control-label col-xs-2">* 이메일</label>
		<div class="col-xs-8">
			<input type="text" id="email" name="email" class="form-control" required placeholder="이메일을 입력해주세요.">
				<div id="warnMsgEmail" class="msg" ></div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="phone" class="control-label col-xs-2">* 전화번호</label>
		<div class="col-xs-8">
			<input type="text" id="phone" name="phone" class="form-control" required placeholder="전화번호를 입력해주세요.">
				<div id="warnMsgPhone" class="msg" ></div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="zipcode" class="control-label col-xs-2">* 우편번호</label>
		<div class="col-xs-8">
			<input type="text" id="zipcode" name="zipcode" class="form-control" required placeholder="우편번호를 입력해주세요." >
				<div id="warnMsgZipcode" class="msg" ></div>
		<input type="button" value="우편번호찾기" onclick="kakaopost()">
		</div>
	</div>
	
	<div class="form-group">
		<label for="address" class="control-label col-xs-2">* 주소</label>
		<div class="col-xs-8">
			<input type="text" id="address" name="address" class="form-control"  required placeholder="주소를 입력해주세요.">
				<div id="warnMsgAddress" class="msg" ></div>
		</div>
	</div>
	
	<div class="form-group">
		<label for="intro" class="control-label col-xs-2">* 자기소개</label>
		<div class="col-xs-8">
			<textarea class="form-control" name="intro" rows="3" id="intro" required placeholder="자기소개를 입력해주세요."></textarea><br>
		</div>
	</div>
</div>
	
	<div class="text-center">
		<button type="submit" id="btnJoin" class="btn btn-primary">회원 가입</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
	<div class="margin"></div>

</form>

</div><!-- .container -->

<%@include file="/WEB-INF/views/layout/footer.jsp" %>
