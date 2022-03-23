window.onload = function () {
	
	// 페이지 시작시 보여줄 아이템 목록 호출
	getFilteredItemList(status)
	
	// 정렬기준 전역변수 설정
	var select = document.getElementById("orderBy")
	var orderBy = select.options[select.selectedIndex].value
	
	// 아이템 추가 페이지 요청(ajax)
	function addItem() {
			    $.ajax({
	    	type: "get"
	    	, url: "/ref/item/add"
	    	, data: {refCode: refCode}
	    	, dataType: "html"
	    	, success: function(res) {
	    		$("#modal-content-area").html(res)
	    	}
	    	, error: function() {
	    		console.log("ajax 실패")
	    	}
	    })
	}
	
    // 모달창 활성화
    const modal = document.getElementById("modal")
    const btnModal = document.getElementById("btn-modal-item-add")
	btnModal.addEventListener("click", function() {
        modal.style.display = "flex"
		addItem()
    })

    // 모달창 닫기
	/*
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", function() {
        modal.style.display = "none"
    })
	*/

    // 모달창 바깥 영역 클릭시 닫기
    modal.addEventListener("click", e => {
    	const evTarget = e.target
    	if (evTarget.classList.contains("modal-overlay")) {
            modal.style.display = "none"
    	}
    })
	
	// (요청)보관상태로 필터링한 냉장고 품목 리스트 #ref-main-items에 출력(ajax)
	function getFilteredItemList(e) {
		$.ajax({
			type: "get"
			, url: "/ref/itemlist/filterAndSort"
			, data: {refCode: refCode, status: e, orderBy: orderBy}
			, dataType: "html"
			, success: function(res) {
				$("#ref-main-items").html(res)
			}
			, error: function() {
				console.log("ajax 실패")
			}
		})
	}
	
	// 필터링 된 아이템 목록 보여주기
	$(".filtering").click(function() {
		
		// 요청 파라미터값 추출 - 보관상태코드
		var status = this.getAttribute("data-value")
		console.log(status)
		// 요청 파라미터값 추출 - 정렬기준
		var select = document.getElementById("orderBy")
		var orderBy = select.options[select.selectedIndex].value
		
		getFilteredItemList(status)
	})
	
	// "#ref-main-items" 의 값이 바뀔 때마다(ajax로 상세 아이템 조회할 때) 버튼 스타일 변경
	var element = document.querySelector("#ref-main-items")
	element.addEventListener("DOMSubtreeModified", function() {
		status = $("#test").attr("data-value")
		
		switch (status) {
			case "4":  
				$(".filtering").removeClass("active")
				$(".filtering[data-value='"+ status +"']").addClass("active")
				break;
			case "0":
				$(".filtering").removeClass("active")
				$(".filtering[data-value='"+ status +"']").addClass("active")
				break;
			case "1":
				$(".filtering").removeClass("active")
				$(".filtering[data-value='"+ status +"']").addClass("active")
				break;
			case "2":
				$(".filtering").removeClass("active")
				$(".filtering[data-value='"+ status +"']").addClass("active")
				break;
		}

	})
	
	// 정렬기준 select 박스가 변경 될 때마다 아이템 목록 조회 요청
	$("#orderBy").on("change", function() {
		// 요청 파라미터값 추출 - 보관상태코드
		// -> 전역변수 status 값 사용
		
		// 요청 파라미터값 추출 - 정렬기준
		var orderBy = this.value
		
		// 보관상태로 필터링한 냉장고 품목 리스트 #ref-main-items에 출력(ajax)
		 $.ajax({
			type: "get"
			, url: "/ref/itemlist/filterAndSort"
			, data: {refCode: refCode, status: status, orderBy: orderBy}
			, dataType: "html"
			, success: function(res) {
				$("#ref-main-items").html(res)
			}
			, error: function() {
				console.log("ajax 실패")
			}
		}) 
		
		
	})
	
	//-------------------------------------
	// 냉장고 품목 클릭시 해당 위치에 상품 상세 정보 레이어 생성
	$('.item').click(function(e) {
		var divTop = e.clientY;
		var divLeft = e.clientX;
		
		var serial = $(this).attr("serial"); 
		
		//var idx = $(this).attr("idx"); 
		//$('#divView').empty().append('<div style="position:absolute;top:5px;right:5px"><span id="close" style="cursor:pointer;font-size:1.5em" title="닫기">X</span> </div><br><a href="?serial=' + serial + '">serial</a><BR><a href="?idx=' + idx + '">idx</a>');
		$("#divView").css({ "top": divTop ,
							"left": divLeft , 
							"position": "absolute"})
						.show();
		$("#close").click(function(){
			$("#divView").css("display", "none")}); 
		
		var itemNo = $(this).prev().attr("data-itemNo")
		getItemDetail(itemNo);
		
	});
	
	// 상품상세 정보 영역 이외 클릭시 상세 정보 레이어 닫음
	/*
	$(document).click(function (e) {
		if (!$(e.target).hasClass("item-holder")) {
				$("#divView").hide()
			}
		});
	*/
	// 냉장고 품목 상세정보 페이지 요청(ajax)
	function getItemDetail(param) {
		
		$.ajax({
			type: "get",
			url: "/ref/items/detail", 
			dataType: "html", 
			data: {refCode: refCode, itemNo: param}, 
			success: function(res) {
				$("#detail-content-area").html(res)
			}, 
			error: function() {
				console.log("ajax 실패")
			}
		})
	}
	
	// 냉장고 공유 폼 빈칸 검증
	$("#share-form").submit(function() {
		const refCode = $("input[name='refCode']").val()
		
		if (refCode === "") {
			alert("값을 입력하세요")
			return false
		}
		return true
	})


} // onload 끝

