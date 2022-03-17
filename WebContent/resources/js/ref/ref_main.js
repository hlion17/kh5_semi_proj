window.onload = function () {
	
	console.log("ref_main.js")
	
    // 모달창 활성화
/*
    const modal = document.getElementById("modal")
    const btnModal = document.getElementById("btn-modal-item-add")
    btnModal.addEventListener("click", function() {
        modal.style.display = "flex"
    })
*/

    // 모달창 닫기
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", function() {
        modal.style.display = "none"
    })

    // 모달창 바깥 영역 클릭시 닫기
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if (evTarget.classList.contains("modal-overlay")) {
            modal.style.display = "none"
        }
    })

	// -----------------------------------

	
	// 정렬기준 
	var select = document.getElementById("orderBy")
	var orderBy = select.options[select.selectedIndex].value
	
	
	
	// 아이템 추가 창 띄우기
	$("#btn-modal-item-add").click(function() {
		
		// 아이템추가 버튼 클릭시 모달창 활성화
	    const modal = document.getElementById("modal")
	    const btnModal = document.getElementById("btn-modal-item-add")
	    btnModal.addEventListener("click", function() {
	    	console.log("클릭")
	        modal.style.display = "flex"
	    })
	    
	    // 아이템 추가 페이지 ajax처리
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
	})
	
	// 필터링 된 아이템 목록 보여주기
	$(".filtering").click(function() {
		
		// 요청 파라미터값 추출 - 보관상태코드
		var status = this.getAttribute("data-value")
		// 요청 파라미터값 추출 - 정렬기준
		var select = document.getElementById("orderBy")
		var orderBy = select.options[select.selectedIndex].value
		
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

	})  // 버튼 스타일 변경 끝
	
	// select 박스가 변경 될 때마다 --
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


}