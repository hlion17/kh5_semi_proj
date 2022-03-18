$(document).ready(function() {
	function searchIngrs() {
		const ingrName = $("input[name='ingrName']").val()
		
		$.ajax({
			type: "post", 
			url: "/ingr/list", 
			dataType: "html", 
			data: {ingrName: ingrName}, 
			success: function(res) {
				$("#ingr-short-container").html(res)
			}, 
			error: function() {
				console.log("ajax 실패")
			}
		})	
	} 
})