<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String refCode = (String) request.getAttribute("refCode"); %>

<script>
// 품목 추가 입력값 검증
$(document).ready(function () {
	
	$("#btn-add-form").submit(function () {
		
		if (!formValidation()) {
			return false
		}
		return true
	})
	
	function formValidation() {
		const itemName = $("input[name='itemName']").val()
    	const itemCty = $("input[name='itemQty']").val()
	    const status = $("input[name='status']").val()
	    const expireDate = $("input[name='expireDate']").val()
	    const note = $("textarea[name='note']").val()

	    if (!emptyChk(itemName, "품목이름")) return false
	    if (!emptyChk(itemCty, "수량")) return false
	    if (!emptyChk(status, "보관상태")) return false
	    if (!emptyChk(expireDate, "유통기한")) return false
	    if (!emptyChk(note, "메모")) return false
	    return true
	}

	// 빈칸 검증
	function emptyChk(val, msg) {
		if (val === "" || val === 0) {
      	alert(msg + "을(를) 입력해주세요.")
      	return false
    }
    return true
	}
	
    // 모달창 닫기
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", function() {
        modal.style.display = "none"
    })
})

</script>

<form action="/ref/item/add?refCode=<%= refCode %>" method="post" id="btn-add-form">

<div class="form-group">
	<label for="ingryCtyCode">품목종류</label>
	<select id="ingryCtyCode" class="form-control" name="ingrCtyCode" >
		<option value="10">채소류</option>
		<option value="20">과일류</option>
		<option value="30">곡류</option>
		<option value="40">해조류</option>
		<option value="50">어패류</option>
		<option value="60">견과류</option>
		<option value="70">육류</option>
		<option value="80">버섯류</option>
		<option value="90">기타</option>
	</select>
</div>

<div class="form-group">
    <label for="itemName">품목이름</label>
    <input type="text" class="form-control" id="itemName" name="itemName" placeholder="품목이름을 입력하세요">
</div>

<div class="form-group">
	<label for="itemQty">품목수량</label>
    <input type="text" class="form-control" id="itemQty" name="itemQty" placeholder="품목수량을 입력하세요">
</div>

<div class="form-group">
	<label>보관상태</label><br>
	<label class="radio-inline">
		<input type="radio" name="status" id="inlineRadio1" value="0" checked="checked"> 냉장
	</label>
	<label class="radio-inline">
		<input type="radio" name="status" id="inlineRadio2" value="1"> 냉동
	</label>
	<label class="radio-inline">
		<input type="radio" name="status" id="inlineRadio3" value="2"> 실온
	</label>
</div>

<div class="form-group">
	<label for="expireDate">유통기한</label>
    <input type="date" class="form-control" id="expireDate" name="expireDate">
</div>
<div class="form-group">
	<label for="note">메모</label>
    <textarea class="form-control" rows="3" name="note"></textarea>
</div>

<button class="btn btn-primary">추가</button>
<button type="button" class="btn btn-default close-area">취소</button>
</form>





<!-- 백업

<form action="/ref/item/add?refCode=<%= refCode %>" method="post" id="btn-add-form">
분류코드: 
<select name="ingrCtyCode">
	<option value="10">채소류</option>
	<option value="20">과일류</option>
	<option value="30">곡류</option>
	<option value="40">해조류</option>
	<option value="50">어패류</option>
	<option value="60">견과류</option>
	<option value="70">육류</option>
	<option value="80">버섯류</option>
	<option value="90">기타</option>
</select>
<br>
품목이름: <input type="text" name="itemName"><br>
품목수량: <input type="text" name="itemQty"><br>
보관상태: <br>
냉장: <input type="radio" name="status" value="0" checked="checked">
냉동: <input type="radio" name="status" value="1">
실온: <input type="radio" name="status" value="2">
<br>
유통기한: <input type="date" name="expireDate"><br>
메모: <textarea rows="3" name="note"></textarea>

<button>제출</button>
</form>

 -->
