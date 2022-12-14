/**
 * @author 김예은, 전판근
 */
$(function() {
	let test = 1;

	$("#common-alert-btn").click(function() {
		$(".popup-background:eq(1)").addClass("blind");
		$("#common-alert-popup").addClass("blind");

		if ($(this).attr("is_reload") == "true") {
			location.reload();
			$(this).attr("is_reload", false);
		}
	});

	/***** ************** *****/
	/***** 슬라이드 이미지 *****/
	/***** ************** *****/
	$(".prev").addClass("hide");

	if ($(".img").length == 1) {
		$(".next").addClass("hide");
	}

	else {
		let position = 0;
		$(".next").click(function() {
			if (test < $(".img").length) {
				position += 960;
				$(".container").css("transform", "translateX(-" + position + "px)");
				test = test + 1;

				if (test == $(".img").length) {
					$(".next").addClass("hide");
				}
				if (test == 2) {
					$(".prev").removeClass("hide");
				}
			}
		});

		$(".prev").click(function() {
			if (test != 1) {
				position -= 960;
				$(".container").css("transform", "translateX(-" + position + "px)");
				test = test - 1;

				if (test == $(".img").length - 1) {
					$(".next").removeClass("hide");
				}
				if (test == 1) {
					$(".prev").addClass("hide");
				}
			}
		});
	}


	/***** ************** *****/
	/***** DATEPICKER부분 *****/
	/***** ************** *****/
	$(".time-input").datetimepicker({
		dateFormat: 'yy/mm/dd',
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		minDate: new Date(),
		//.toLocaleDateString().replaceAll(". ", "/"),

		// timepicker 설정
		timeFormat: 'HH:mm:ss',
		controlType: 'select',
		oneLine: true,
	});


	/***** *** ******* *****/
	/***** 고정된 부분 *****/
	/***** *** ******* *****/
	/*** 예약 시간 실패 팝업창 닫기 버튼 클릭 ***/
	$("#time-fail-close-btn").click(function() {
		$(".fixed-popup").addClass("blind");
	});

	/*** 예약 타입 셀렉트 클릭 시 -> 커스텀 셀렉트 SHOW ***/
	$(".type-border").click(function() {
		if ($(".custom-select-type").hasClass("blind")) {
			$(".custom-select-type").removeClass("blind");
			$(".type-border").addClass("open-select");
		} else {
			$(".custom-select-type").addClass("blind");
			$(".type-border").removeClass("open-select");
		}
	});

	/*** 예약 타입 리스트 클릭 ***/
	$(".custom-select-type-list").click(function() {
		$(".type-border-txt").text($(this).children(".room-name").text());
		$(".type-border-txt").prop("check", true);

		let attr_room_no = $(this).children(".room-name").attr("room_no");
		$("#type-choice-value").attr("room_no", attr_room_no);
		
		let attr_room_type = $(this).children(".room-name").attr("room_type");
		$("#type-choice-value").attr("room_type", attr_room_type);

		$(".custom-select-type").addClass("blind");
		$(".type-border").removeClass("open-select");
	});



	// 예약 선택 시간 boundary
	// ex) 11, 16시 선택 시
	// 이 배열에는 11, 12, 13, 14, 15, 16이 들어감.
	// 예약이 가능한지 체크하기 위함.
	// 중간 시간에 예약이 있으면 불가능함.
	let check_reserve_time = [];
	let pick_time_list = [];

	// 선택한 시간 초기화
	$("#check_available").click(function() {
		pick_time_list = [];
	});


	// pick_time_list에 시간이 하나만 들어가 있으면 1시간 대여
	// 2개 있으면 그 사이 시간을 대여하는 것.
	$('.time-boundary-item').on('click', function() {
		
		check_reserve_time = [];
		
		if ($(this).hasClass('selected')) {

			// 선택 취소 했을 때
			pick_time_list.pop($(this).val());
			$(this).removeClass('selected');
		} else {
			// 선택 되었을 때
			if (pick_time_list.length < 2) {
				pick_time_list.push($(this).val());
				pick_time_list.sort();
				$(this).addClass('selected');
			}
		}

		// 예약 불가 처리
		if (pick_time_list.length == 2) {
			
			let reserve_flag = 0;
			for (var t = pick_time_list[0]; t <= pick_time_list[1]; t++) {

				// attr display가 있을 때만 추가
				if ($(".time-boundary-item:eq(" + t + ")").is('[display]')) {
					check_reserve_time.push(t);
					
					$(".time-boundary-item:eq(" + t + ")").css("background-color", "#2EE49D");
					$(".time-boundary-item:eq(" + t + ")").css("color", "#FFFFFF");
				} else {
					$('.fixed-popup').removeClass('blind')
					$('.using-time-fail-txt:eq(0)').html(
						'선택 시간 사이에 예약이 존재하여<br><br>해당 시간은 예약할 수 없습니다.',
					)
					reserve_flag = 1;
				}
			}

			if (reserve_flag === 1) {
				pick_time_list = [];
				check_reserve_time = [];
				for (var t = 0; t < 24; t++) {
					$(".time-boundary-item:eq(" + t + ")").removeClass('selected');
					$(".time-boundary-item:eq(" + t + ")").css("background-color", "#FFFFFF");
					$(".time-boundary-item:eq(" + t + ")").css("color", "#000000");
				}
				
				reserve_flag = 0;
			}
		} else {
			pick_time_list = [];
			check_reserve_time = [];
			
			for (var t = 0; t < 24; t++) {
				if ($(".time-boundary-item:eq(" + t + ")").hasClass("selected")) {
					pick_time_list.push(t);
					$(".time-boundary-item:eq(" + t + ")").css("background-color", "#2EE49D");
					$(".time-boundary-item:eq(" + t + ")").css("color", "#FFFFFF");
				} else {
					$(".time-boundary-item:eq(" + t + ")").css("background-color", "#FFFFFF");
					$(".time-boundary-item:eq(" + t + ")").css("color", "#000000");
				}
			}
		}
	});

	$("#go_reserve").click(function() {
		let user_no = $.cookie("user_no");
		let backoffice_no = location.href.split("backoffice_no=")[1].split("&")[0];
		let room_no = $("#type-choice-value").attr("room_no");
		let reserve_date = $(".time-input:eq(0)").val().trim();
		let room_type = $("#type-choice-value").attr("room_type").trim();
		
		if (pick_time_list.length == 0) {
			$('.fixed-popup').removeClass('blind')
			$('.using-time-fail-txt:eq(0)').html(
				'시간을 선택해 주세요.<br><br> 표시가 안 된 시간은 예약이 불가합니다.',
			)
			
			$("#check_available").removeClass("blind");
			$("#go_reserve").addClass("blind");
		}
		
		if (pick_time_list[0] > pick_time_list[1]) {
			[pick_time_list[0], pick_time_list[1]] = [pick_time_list[1], pick_time_list[0]]
		}
		
		if (pick_time_list.length == 1) {
			pick_time_list.push(pick_time_list[0] + 1);
		} else {
			pick_time_list[1] += 1;		
		}
		
		let reserve_stime = reserve_date + " " + pick_time_list[0] + ":00:00";
		let reserve_etime = reserve_date + " " + pick_time_list[1] + ":00:00";
		
		if (room_type == '데스크') {
			room_type = 'desk';
		} else if (room_type == '4인 미팅룸') {
			room_type = 'meeting_04';
		} else if (room_type == '6인 미팅룸') {
			room_type = 'meeting_06';
		} 
		else if (room_type == '10인 미팅룸') {
			room_type = 'meeting_10';
		}
		
		//로딩 화면
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");
		
		$.ajax({
			url: "/office/reserve",
			type: "GET",
			dataType: "json",
			data: {
				user_no: user_no,
				backoffice_no: backoffice_no,
				room_no: room_no,
				reserve_stime: reserve_stime,
				reserve_etime: reserve_etime,
				room_type: room_type.trim()
			},
			
			success: function(res) {
				//로딩 화면 닫기
				$(".popup-background:eq(1)").addClass("blind");
				$("#spinner-section").addClass("blind");
				
				if (res.result == "1") {
					location.href="/office/payment?reserve_no=" + res.reserve_no;
				}
			},
			error: function() {
				//로딩 화면 닫기
				$(".popup-background:eq(1)").addClass("blind");
				$("#spinner-section").addClass("blind");
			}
		});
	});


	/***** *** ******* *****/
	/***** 고정 안 된 부분 ****/
	/***** *** ******* *****/

	/****** 메뉴 섹션 ******/
	$(".menu").click(function() {
		$(".menu").removeClass("click-menu");
		$(this).addClass("click-menu");

		if ($(this).attr("menu") == "info") {
			$(".space-description-wrap").removeClass("blind");
			$(".question-wrap:eq(0)").addClass("blind");
			$("#review-wrap").addClass("blind");
		}
		else if ($(this).attr("menu") == "qna") {
			$(".space-description-wrap").addClass("blind");
			$(".question-wrap:eq(0)").removeClass("blind");
			$("#review-wrap").addClass("blind");
		}
		else if ($(this).attr("menu") == "review") {
			$(".space-description-wrap").addClass("blind");
			$(".question-wrap:eq(0)").addClass("blind");
			$("#review-wrap").removeClass("blind");
		}
	});


	/***** ************* *****/
	/***** QUESTION MENU *****/
	/***** ************* *****/
	$(".quest-list-wrap").on("click", ".answer_toggle", function() {
		var answer_li = $(this).parents(".quest-list").next(".answer-list");
		if (answer_li.hasClass("blind")) {
			answer_li.removeClass("blind");
			$(this).text("답변 닫기");
		} else {
			answer_li.addClass("blind");
			$(this).text("답변 보기");
		}
	});


	/***** ************** *****/
	/***** QUESTION POPUP *****/
	/***** ************** *****/

	/*** 팝업 닫기 버튼 클릭  ***/
	$("#question-close-btn").click(function() {
		// TEXTAREA 초기화
		$("#question-write").val("");
		// 글자수 초기화
		$(".qna-length").text("0");

		$("#question-write").removeClass("null-input-border");
		$(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
		$(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");

		$("#question-select-choice").text("타입을 선택해 주세요");
		$("#question-select-choice").attr("choice_idx", "");
		$("#question-select-choice").attr("choice", "");
		$(".question-popup-select:eq(0)").addClass("blind");

		$("#toggle").prop("checked", false);

		// 팝업 닫기
		$("#question-popup").addClass("blind");
	});

	/*** 문의 작성 경고 테두리 제거  ***/
	$("#question-write").click(function() {
		$("#question-write").removeClass("null-input-border");
	});

	/*** 문의 작성 글자수 제한  ***/
	$("#question-write").on("keydown keyup", function() {
		if ($(this).val().length > 500) {
			$(this).val($(this).val().substring(0, 500));
		}
		$(".qna-length").text($(this).val().length);
	});

	$(".question-popup-select-val-wrap:eq(0)").click(function() {
		if ($(".question-popup-select:eq(0)").hasClass("blind")) {
			$(".question-popup-select-val-wrap:eq(0)").addClass("open-select");
			$(".question-popup-select:eq(0)").removeClass("blind");
			$(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
		}
		else {
			$(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
			$(".question-popup-select:eq(0)").addClass("blind");
		}
	});

	/** 팝업 셀렉트 리스트 클릭 이벤트 */
	$("#question-popup").on("click", ".question-popup-select-li", function() {
		$("#question-select-choice").text($(this).text());
		$("#question-select-choice").attr("choice", "true");
		$("#question-select-choice").attr("choice_idx", $(this).attr("idx"));

		$(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
		$(".question-popup-select:eq(0)").addClass("blind");
	});

	var question_flag = true;
	$("#question-createBtn").click(function() {
		if ($("#question-select-choice").attr("choice") == "true" && $("#question-write").val().trim().length > 0) {
			if(question_flag){
				question_flag = false;
				
				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");
	
				var is_secret = "";
				$("#toggle").prop("checked") ? is_secret = 'T' : is_secret = 'F';
	
				$.ajax({
					url: "/office/insert_question",
					type: "GET",
					dataType: 'json',
					data: {
						user_no: $.cookie("user_no"),
						backoffice_no: location.href.split("backoffice_no=")[1].split("&")[0],
						room_no: $("#question-select-choice").attr("choice_idx"),
						comment_content: $("#question-write").val().trim(),
						is_secret: is_secret
					},
					success: function(res) {
						question_flag = true;
						
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");
	
						if (res.result == 1) {
							$(".qna-length").text("0");
							$("#question-write").val("");
	
							$(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
							$("#question-write").removeClass("null-input-border");
	
							$("#question-select-choice").text("타입을 선택해 주세요");
							$("#question-select-choice").attr("choice_idx", "");
							$("#question-select-choice").attr("choice", "");
	
							$(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
							$(".question-popup-select:eq(0)").addClass("blind");
							$("#question-popup").addClass("blind");
	
							$("#toggle").prop("checked", false);
	
							$(".popup-background:eq(1)").removeClass("blind");
							$("#common-alert-popup").removeClass("blind");
							$(".common-alert-txt").text("성공적으로 문의가 등록되었습니다.");
							$("#common-alert-btn").attr("is_reload", true);
						} else {
							$(".popup-background:eq(1)").removeClass("blind");
							$("#common-alert-popup").removeClass("blind");
							$(".common-alert-txt").text("비밀번호가 일치하지않습니다.");
						}
					},
					error: function() {
						question_flag = true;
						
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");
	
						$(".popup-background:eq(1)").removeClass("blind");
						$("#common-alert-popup").removeClass("blind");
						$(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
					}
				});
			}
		}
		else {
			if ($("#question-write").val().trim().length == 0) {
				$("#question-write").addClass("null-input-border");
			}
			if ($("#question-select-choice").attr("choice") != "true") {
				$(".question-popup-select-val-wrap:eq(0)").addClass("null-input-border");
			}
		}
	});
	
	
	/************************************************* */
	/****************** PAGING SECTION *************** */
	/************************************************* */
    
    // 문의 탭 - 다음 페이지 리스트로 이동
    $(".question-paging").on("click", ".next-page-btn", function(){
		var start = Number($($(".question-paging").find(".paging-box.paging-num")[0]).text()) + 5;
		var last = Number($($(".question-paging").find(".paging-box.paging-num")[4]).text()) + 5;
		var totalPageCnt = Number($("#totalPageCnt").val());
		
		if($(".question-paging").find(".before-page-btn").hasClass("hide")){
			$(".question-paging").find(".before-page-btn").removeClass("hide");
		}
		
		if(last >= totalPageCnt){
			last = totalPageCnt;
			$(".question-paging").find(".next-page-btn").addClass("hide");
		}
		
		var sample = $(".question-paging").find(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".question-paging").find(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".question-paging").find(".paging-num-wrap").append(sample_span);
		}
	});
	
    // 문의 탭 - 이전 페이지 리스트로 이동
    $(".question-paging").on("click", ".before-page-btn", function(){
		var start = Number($($(".question-paging").find(".paging-box.paging-num")[0]).text()) - 5;
		var last = Number($(".question-paging").find(".paging-box.paging-num:last").text()) - 5;
		
		if(last % 5 != 0){
			last += 5 - (last % 5);
		}
		
		if($(".question-paging").find(".next-page-btn").hasClass("hide")){
			$(".question-paging").find(".next-page-btn").removeClass("hide");
		}
		
		if(start == 1){
			$(".question-paging").find(".before-page-btn").addClass("hide");
		}
		
		var sample = $($(".question-paging").find(".paging-box.paging-num")[0]).clone();
		$(".question-paging").find(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".question-paging").find(".paging-num-wrap").append(sample_span);
		}
	});
    
    // 후기 탭 - 다음 페이지 리스트로 이동
    $(".review-paging").on("click", ".next-page-btn", function(){
		var start = Number($($(".review-paging").find(".paging-box.paging-num")[0]).text()) + 5;
		var last = Number($($(".review-paging").find(".paging-box.paging-num")[4]).text()) + 5;
		var totalPageCnt = Number($("#totalPageCnt2").val());
		
		if($(".review-paging").find(".before-page-btn").hasClass("hide")){
			$(".review-paging").find(".before-page-btn").removeClass("hide");
		}
		
		if(last >= totalPageCnt){
			last = totalPageCnt;
			$(".review-paging").find(".next-page-btn").addClass("hide");
		}
		
		var sample = $(".review-paging").find(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".review-paging").find(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".review-paging").find(".paging-num-wrap").append(sample_span);
		}
	});
	
    // 후기 탭 - 이전 페이지 리스트로 이동
    $(".review-paging").on("click", ".before-page-btn", function(){
		var start = Number($($(".review-paging").find(".paging-box.paging-num")[0]).text()) - 5;
		var last = Number($(".review-paging").find(".paging-box.paging-num:last").text()) - 5;
		
		if(last % 5 != 0){
			last += 5 - (last % 5);
		}
		
		if($(".review-paging").find(".next-page-btn").hasClass("hide")){
			$(".review-paging").find(".next-page-btn").removeClass("hide");
		}
		
		if(start == 1){
			$(".review-paging").find(".before-page-btn").addClass("hide");
		}
		
		var sample = $(".review-paging").find(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".review-paging").find(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".review-paging").find(".paging-num-wrap").append(sample_span);
		}
	});
	
});