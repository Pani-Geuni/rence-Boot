/**
 * @author 전판근
 */

$(function() {
	var IMP = window.IMP;
	IMP.init("imp26554321")
	let payment_state = "T";

	timer();
	
	// ****************
	// 가격에 ,(콤마) 붙이기
	// ****************
	$('.room_price').each(function(index, value) {
		let price = $(value).text();

		$(this).text(price.replace(/\B(?=(\d{3})+(?!\d))/g, ','));
	});

	$('#payment_all').text(
		$('#payment_all')
			.text()
			.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	);

	$('#earned_mileage').text(
		$('#earned_mileage')
			.text()
			.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	);

	$('#my-mileage').text(
		$('#my-mileage')
			.text()
			.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	);
	
	$('#max-use-mileage').text(
		$('#max-use-mileage')
			.text()
			.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	);

	// *******************
	// custom-radio action
	// *******************
	let payment_all = parseInt($("#payment_all").attr("payment_all"));
	let earned_mileage = parseInt($("#earned_mileage").attr("earned_mileage"));
	let actual_payment = payment_all;


	let max_use_mileage = parseInt($("#my-mileage").attr("my-mileage"));
	let deposit = payment_all * 0.2;
	
	if ((payment_all * 0.8) <= max_use_mileage) {
		max_use_mileage = payment_all * 0.8;
		$("#max-use-mileage").attr("max-use-mileage", max_use_mileage);
		$("#max-use-mileage").text(max_use_mileage);
	}

	// *****
	// 선결제	
	// *****
	$(".inner-radio:eq(0)").click(function() {
		$(".mileage-line-wrap").removeClass("blind");
		$(".my-mileage-wrap").removeClass("blind");
		
		
		payment_state = "T";
		$(".inner-radio:eq(0)").addClass("choice-radio");
		$(".inner-radio:eq(1)").removeClass("choice-radio");

		$("#payment_all").text(payment_all);
		$("#earned_mileage").text(earned_mileage);

		$('#payment_all').text(
			$('#payment_all')
				.text()
				.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		);

		$('#earned_mileage').text(
			$('#earned_mileage')
				.text()
				.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		);

		$("#payment_all").attr("payment_all", payment_all);
		$("#earned_mileage").attr("earned_mileage", earned_mileage);

	});

	// *******
	// 후불 결제
	// *******
	$(".inner-radio:eq(1)").click(function() {
		$(".mileage-line-wrap").addClass("blind");
		$(".my-mileage-wrap").addClass("blind");
		
		payment_state = "F";
		$(".inner-radio:eq(0)").removeClass("choice-radio");
		$(".inner-radio:eq(1)").addClass("choice-radio");

		$("#payment_all").text(deposit);
		$("#earned_mileage").text("0");

		$('#payment_all').text(
			$('#payment_all')
				.text()
				.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		);

		$("#payment_all").attr("payment_all", deposit);
		$("#earned_mileage").attr("earned_mileage", 0);

	});

	// *************
	// 나의 사용 마일리지
	// *************
	$("#use-mileage:text[numberOnly]").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
	});

	$(".use-mileage-btn").click(function() {
		actual_payment = payment_all;
		let my_mileage = parseInt($("#my-mileage").attr("my-mileage"));	// 내가 가진 마일리지
		let use_mileage = parseInt($("#use-mileage").val()) || 0;		// 사용할 마일리지 

		if ($(".inner-radio:eq(0)").hasClass('choice-radio')) {
			// 선불 결제 시 로직
			origin_payment = payment_all;	// 원래 결제할 전체 금액
			actual_payment = payment_all; // 마일리지 사용 후 결제 금액	

			// 가진 마일리지보다 적게 사용할 때
			if (use_mileage <= max_use_mileage) {
				// 사용 마일리지가 원결제 금액보다 작을 때
				if (use_mileage <= payment_all) {
					if (use_mileage === 0 || use_mileage === "") {
						actual_payment = payment_all;
						earned_mileage = Math.round(actual_payment * 0.05);
					} else {
						actual_payment = payment_all - use_mileage;
						earned_mileage = Math.round(actual_payment * 0.05);
					}
				} else {
					// 사용 마일리지가 원결제 금액보다 클 때
					$('#use-mileage').val("");
				}
				
				$("#earned_mileage").text(earned_mileage);
				$("#payment_all").text(actual_payment);
			} else {
				// 가진 마일리지보다 많이 사용할 때
				$('#use-mileage').val("");
				actual_payment = payment_all;
				earned_mileage = Math.round(actual_payment * 0.05);
				$("#payment_all").text(actual_payment);
				$("#earned_mileage").text(earned_mileage);
			}

		} else if ($(".inner-radio:eq(1)").hasClass('choice-radio')) {
			// 후불 결제 시 로직
			let payment_later = payment_all * 0.2;
			actual_payment = payment_later;
			earned_mileage = Math.round(actual_payment * 0.05);
			$("#earned_mileage").text(earned_mileage);

			// 가진 마일리지보다 적게 사용할 때
			if (use_mileage <= max_use_mileage) {
				// 사용 마일리지가 원결제 금액보다 작을 때
				if (use_mileage <= actual_payment) {
					if (use_mileage === 0 || use_mileage === "") {
						actual_payment = payment_later;
						earned_mileage = actual_payment * 0.05;
					} else {
						actual_payment = payment_later - use_mileage;
						earned_mileage = actual_payment * 0.05;
					}
				} else {
					// 사용 마일리지가 원결제 금액보다 클 때
					$('#use-mileage').val("");
				}

				$("#earned_mileage").text(earned_mileage);
				$("#payment_all").text(actual_payment);
			} else {
				// 가진 마일리지보다 많이 사용할 때
				$('#use-mileage').val("");
				actual_payment = payment_later;
				earned_mileage = Math.round(actual_payment * 0.05);
				$("#earned_mileage").text(earned_mileage);
				$("#payment_all").text(actual_payment);
			}
		}

		// 숫자 콤마 찍기
		$('#payment_all').text(
			$('#payment_all')
				.text()
				.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		);
		
		
		$("#earned_mileage").attr("earned_mileage", earned_mileage);
		$("#payment_all").attr("payment_all", actual_payment);

	});

	$("#pay-btn").click(function() {
		let reserve_no = $(location).attr("href").split("reserve_no=")[1];

		console.log(reserve_no);
		IMP.request_pay({ // param
			pg: "kakaopay",
			pay_method: "card",
			// merchant_uid: "order_no_0016",
			name: "주문명 : 결제 테스트",
			amount: $("#payment_all").attr("payment_all")
		}, function(rsp) {
			if (rsp.success) {
				console.log("결제 성공");
				console.log("결제 금액 : " + payment_all);
				console.log("결제자 : " + $("#user_name").val());
				console.log(rsp.imp_uid);
				let imp_uid = rsp.imp_uid;

				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");

				$.ajax({
					url: "/office/paymentOK",
					method: "POST",
					dataType: "json",
					data: {
						imp_uid: imp_uid,
						payment_total: $("#payment_all").attr("payment_all"),
						use_mileage: $("#use-mileage").val() || 0,
						actual_payment: $("#payment_all").attr("payment_all"),
						payment_state: payment_state,
						user_no: $.cookie("user_no"),
						reserve_no: reserve_no,
						payment_method: "kakaopay"
					},

					success: function(res) {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						if (res.result == 1) {
							alert("결제 성공");
							location.href = "/rence/reserve_list?time_point=now&user_no=" + $.cookie("user_no");
						} else {
							alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
						}
					},

					error: function() {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
					}
				})
			} else {
				alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
			}
		});
	});
	
	var seconds = 60 * 10;
	var time;
	function timer(){
		time = setInterval(function(){
		   if (seconds == 0) {
				$(".popup-background:eq(1)").removeClass("blind");
			   	$("#common-alert-popup").removeClass("blind");
				$(".common-alert-txt").html("결제 시간을 초과했습니다.<br>다시 시도해주세요.");
				
				if ($("#info-room-type").attr("room_type") == "오피스") {
					location.href = "/office/space_introduce_office?backoffice_no=" + $(".info-company-name").attr("backoffice_no");					
				} else {
					location.href = "/office/space_introduce?backoffice_no=" + $(".info-company-name").attr("backoffice_no");
				}
			   	clearInterval(time);
		   }
		   
		   seconds--;
		}, 1000);
	}
});