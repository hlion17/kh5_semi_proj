<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

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


//form validation
//	폼 데이터 유효성 검증

//	-> 유효성 값을 입력했는지 검증
//	-> 적당한 값이 아니면 form을 submit하지 않는다

//------------------------------------------------------------


//아이디, 비밀번호 조합 제한
var req_idpw = /^[A-Za-z]{1}[A-Za-z0-9_]{3,19}$/ //반드시 영문으로 시작 숫자+언더바 허용 4~20자리


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
	
	
}

//유효성 이중 검사
$(document).ready(function () {
	
	//form태그에 submit이 발생했을 때 이벤트 처리
	//	-> submit수행 전에 동작한다.
	$("form").submit(function () {
		//console.log("submit event")
		
		//유효성 검사 결과에 따른 submit 
		if(!validateId() || !validatePw()) {
			
		//console.log("SUBMIT 중단됨")
		
		//submit을 중단시킨다.(return false를 submit에 쓰면)
		return false;
		
		}
		
		//alert("SUBMIT 수행됨")
	})
	
})

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
		warnMsgPw2.style.display = "block";	
		return false;
			
	} else if(memberpw.value.length<4 || memberpw.value.length>20) { //비밀번호 길이 검증
		warnMsgPw.innerHTML = "비밀번호는 4~20자 사이의 영문 + 숫자 조합으로 입력해주세요";
		warnMsgPw.style.display = "block";
		warnMsgPw2.style.display = "block";
		return false;
			
	} else if(memberpw.value != memberpw2.value) {
		warnMsgPw2.innerHTML = "비밀번호와 비밀번호 확인을 같게 입력해주세요.";;
		warnMsgPw.style.display = "block";
		warnMsgPw2.style.display = "block";
		return false;
		
	} else { //맞게 입력하면
		warnMsgPw.innerHTML = "";
		warnMsgPw2.innerHTML = "";
		warnMsgPw.style.display = "none";
		warnMsgPw2.style.display = "none";
		return true;
		console.log("submit완료")
	} 
	
	
}
</script>

<style type="text/css">
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
</style>

<div class="container">

<form action="./join" method="post" class="form-horizontal">
<div class="fieldBox">

	<div class="form-group">
		<label for="memberid" class="control-label col-xs-2">아이디</label>
		<div class="col-xs-10">
			<input type="text" id="memberid" name="memberid" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
		<div id="warnMsgId" class="msg" ></div>
	</div>

	<div class="form-group">
		<label for="memberpw" class="control-label col-xs-2">비밀번호</label>
		<div class="col-xs-10">
			<input type="text" id="memberpw" name="memberpw" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
		<div id="warnMsgPw" class="msg" ></div>
	</div>
	
	<div class="form-group">
		<label for="memberpw2" class="control-label col-xs-2">비밀번호확인</label>
		<div class="col-xs-10">
			<input type="text" id="memberpw2" name="memberpw2" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="membername" class="control-label col-xs-2">이름</label>
		<div class="col-xs-10">
			<input type="text" id="membername" name="membername" class="form-control" >
		</div>
	</div>
	

	<div class="form-group">
		<label for="nick" class="control-label col-xs-2">닉네임</label>
		<div class="col-xs-10">
			<input type="text" id="nick" name="nick" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="gender" class="control-label col-xs-2">성별</label>
		<div class="col-xs-10">
			<label class="radio-inline"><input type="radio" id="gender" name="gender" value="m" >남</label>
			<label class="radio-inline"><input type="radio" id="gender" name="gender" value="f" >여</label>
		</div>
	</div>
	

	<div class="form-group">
		<label for="email" class="control-label col-xs-2">이메일</label>
		<div class="col-xs-10">
			<input type="text" id="email" name="email" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="phone" class="control-label col-xs-2">전화번호</label>
		<div class="col-xs-10">
			<input type="text" id="phone" name="phone" class="form-control" required placeholder="필수 입력 항목입니다">
		</div>
	</div>
	
	<div class="form-group">
		<label for="zipcode" class="control-label col-xs-2">우편번호</label>
		<div class="col-xs-10">
			<input type="text" id="zipcode" name="zipcode" class="form-control" >
		<input type="button" value="우편번호찾기" onclick="kakaopost()">
		</div>
	</div>
	
	<div class="form-group">
		<label for="address" class="control-label col-xs-2">주소</label>
		<div class="col-xs-10">
			<input type="text" id="address" name="address" class="form-control" >
		</div>
	</div>
	
	<div class="form-group">
		<label for="intro" class="control-label col-xs-2">자기소개</label>
		<div class="col-xs-10">
			<textarea class="form-control" rows="3" id="intro" placeholder="입력하세요"></textarea>
		</div>
	</div>
</div>
	
	<div class="text-center">
		<button type="submit" id="btnJoin" class="btn btn-primary">회원 가입</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>

</form>

</div><!-- .container -->
