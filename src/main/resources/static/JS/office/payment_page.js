/**
 * @author 전판근
 */
 
$(function() {
	var IMP = window.IMP;
	IMP.init("imp26554321")
	let payment_state = "T";
	
	// ****************
	// 가격에 ,(콤마) 붙이기
	// ****************
	$('.room_price').each(function (index, value) {
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
	
	// *******************
	// custom-radio action
	// *******************
	let payment_all = $("#payment_all").attr("payment_all");
	let earned_mileage = $("#earned_mileage").attr("earned_mileage");
	
	let deposit = payment_all * 0.2;
	let deposit_earned_mileage = deposit * 0.05;

	// *****
	// 선결제	
	// *****
	$(".inner-radio:eq(0)").click(function() {
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
		
		/* $('#earned_mileage').text(
		    $('#earned_mileage')
		      .text()
		      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		); */
		
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
		let actual_payment = payment_all;
		let my_mileage = $("#my-mileage").attr("my-mileage");	// 내가 가진 마일리지
		let use_mileage = $("#use-mileage").val();				// 사용할 마일리지 
		
		// 가진 마일리지보다 많이 사용할 때
		// 마일리지 잠시 홀드
		/* if (use_mileage > my_mileage) {
			
			$("#use-mileage").val("");
			actual_payment = payment_all;
			$("#payment_all").text(actual_payment);
			
		} else if (use_mileage <= my_mileage) {
			// 가진 마일리지보다 적게 사용할 때
			console.log("use_mileage :: ", use_mileage);
			console.log("my_mileage :: ", my_mileage);
			if (use_mileage == 0 || use_mileage == "") {
				actual_payment = payment_all;
			} else {
				actual_payment = payment_all - use_mileage;
			}
			
			$("#payment_all").text(actual_payment);
			
		}
		$('#payment_all').text(
			$('#payment_all')
		      .text()
		      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
		);
			
		$("#payment_all").attr("payment_all", actual_payment); */
	});
	
	$("#pay-btn").click(function() {
		let full_url = $(location).attr("href");
		let reserve_no = full_url.split("reserve_no=")[1];
	
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
      			
      			$.ajax({
      				url : "/rence/reserve_paymentOK",
      				method : "POST",
      				dataType : "json",
      				data : {
      					payment_total : $("#payment_all").attr("payment_all"),
      					use_mileage : $("#use-mileage").val(),
      					actual_payment : $("#payment_all").attr("payment_all"),
      					payment_state : payment_state,
      					user_no : $.cookie("user_no"),
      					reserve_no : reserve_no,
      					payment_method : "kakaopay"
      				},
      				
      				success : function(res) {
      					if (res.result == 1) {
      						alert("결제 성공");
      						location.href="/rence/reserve_list?time_point=now&user_no="+$.cookie("user_no");
      					} else {
      						alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
      					}
      				},
      				
      				error : function(error) {
      					alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
      				}
      			})
      		} else {
      			alert("결제에 실패했습니다. 에러 내용 : " + rsp.error_msg);
      		}
      	});
	});
})