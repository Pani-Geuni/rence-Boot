/**
 * @author : 전판근
 */

 $(function() {
	 
	 $("#pay-cancel-btn").click(function() {
		$(".popup-background:eq(0)").removeClass("blind");
		$("#reserve-cancel-popup").removeClass("blind");
	 });
	 
	 $("#refund-btn").click(function() {
		let reserve_no = location.href.split("reserve_no=")[1].split("&")[0];
		let user_no = $.cookie("user_no");
		
		let actual_payment = parseInt($("#actual_payment").attr("actual_payment").replace(",", ""));
		
		// 환불 로직
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
					let reserve_no = location.href.split("reserve_no=")[1].split("&")[0];
					let cancel_amount = parseInt($("#actual_payment").attr("actual_payment").replace(",", ""));

					// 환불 로직
					$.ajax({
						"url": "/rence/payment_cancel",
						"type": "POST",
						"dataType": "json",
						"data": {
							reserve_no: reserve_no,
							cancel_amount: cancel_amount,
							reason: "예약 취소로 인한 결제 환불"
							
						},
						success: function(res) {
							if (res.result == "1") {
								$("#reserve-cancel-popup").addClass("blind");
								$("#reserve-cancel-confirm-popup").removeClass("blind");
					
								location.href="/rence/reserve_list?time_point=now&user_no=" + $.cookie("user_no") + "&page=1";
							}
						},
						
						error: function() {
							$(".popup-background:eq(1)").removeClass("blind");
			                $("#common-alert-popup").removeClass("blind");
			                $(".common-alert-txt").text("오류 발생으로 인해 환불에 실패하였습니다.");
						}
					});
					
				} else {
					$("#reserve-cancel-popup").addClass("blind");
					$("#reserve-cancel-fail-popup").removeClass("blind");
				}
			},
			
			error: function() {
				$(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("오류 발생으로 인해 환불에 실패하였습니다.");
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