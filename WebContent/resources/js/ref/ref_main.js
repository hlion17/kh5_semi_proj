window.onload = function () {
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


}