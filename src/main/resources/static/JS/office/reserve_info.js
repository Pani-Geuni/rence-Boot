/**
 * @author : 전판근
 */

 $(function() {
	 
	 $("#pay-cancel-btn").click(function() {
		console.log("pay cancel button");
		
		
		
		$(".popup-background:eq(0)").removeClass("blind");
		$("#reserve-cancel-popup").removeClass("blind");
		
	 });
	 
	 $("#refund-btn").click(function() {
		let reserve_no = location.href.split("reserve_no=")[1].split("&")[0];
		let user_no = $.cookie("user_no");
		
		$.ajax({
			url: "/rence/reserve_cancel",
			type: "GET",
			dataType: "JSON",
			data: {
				reserve_no: reserve_no,
				user_no: user_no
			},
			
			success: function(res) {
				
				if (res.result === "1") {
					$("#reserve-cancel-popup").addClass("blind");
					$("#reserve-cancel-confirm-popup").removeClass("blind")
					
					location.href="/";
				} else {
					$("#reserve-cancel-popup").addClass("blind");
					$("#reserve-cancel-fail-popup").removeClass("blind");
				}
			},
			
			error: function(error) {
				console.log(error);
			}
		});
	 });
	 
	 $("#refund-closeBtn").click(function() {
		 $("#reserve-cancel-popup").addClass("blind");
		 $(".popup-background:eq(0)").addClass("blind");
	 });
	 
	 $("#reserve-cancel-btn").click(function() {
		 $("#reserve-cancel-confirm-popup").addClass("blind");
		 $(".popup-background:eq(0)").addClass("blind");
	 });
	 
	 $("#reserve-cancel-fail-btn").click(function() {
		 $("#reserve-cancel-fail-popup").addClass("blind");
		 $(".popup-background:eq(0)").addClass("blind");
	 });
 })