/**
 * @author 김예은
 */
$(function() {
	$("#common-alert-btn").click(function() {
		$(".popup-background:eq(1)").addClass("blind");
		$("#common-alert-popup").addClass("blind");

		if ($(this).attr("is_reload") == "true") {
			location.reload();
			$(this).attr("is_reload", false);
		}
	});

	$("#input_searchBar").on('keydown', function(e) {
		if (e.keyCode == 13) {
			var reserve_state = "";
			switch ($(".reserve-item.active").text()) {
				case "전체":
					reserve_state = "all";
					break;
				case "예약중":
					reserve_state = "in_use";
					break;
				case "취소":
					reserve_state = "cancel";
					break;
				case "종료":
					reserve_state = "end";
					break;
			}
			location.href = "/backoffice/search_reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state=" + reserve_state + "&searchword=" + $("#input_searchBar").val().trim() + "&page=1";
		}
	});

	// 미니 메뉴 부분 클릭 이벤트
	$(".reserve-item").click(function() {
		var reserve_state = "";
		switch ($(this).text().trim()) {
			case "전체":
				reserve_state = "all";
				break;
			case "예약중":
				reserve_state = "in_use";
				break;
			case "취소":
				reserve_state = "cancel";
				break;
			case "종료":
				reserve_state = "end";
				break;
		}
		location.href = "/backoffice/reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state=" + reserve_state + "&page=1";
	});


	// 스크롤을 이용한 페이징
	$(".reserve-ct").scroll(function() {
		if (Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')) {
			if ($(".ct-body-row").length < Number($("#maxCnt").attr("maxCnt"))) {
				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");

				var reserve_state = "";
				switch ($(this).text().trim()) {
					case "전체":
						reserve_state = "all";
						break;
					case "예약중":
						reserve_state = "in_use";
						break;
					case "취소":
						reserve_state = "cancel";
						break;
					case "종료":
						reserve_state = "end";
						break;
				}

				var page = $("#maxCnt").attr("nowCnt");

				$.ajax({
					url: "/backoffice/reserve_paging",
					type: "GET",
					dataType: 'json',
					data: {
						backoffice_no: $.cookie("backoffice_no"),
						reserve_state: reserve_state,
						page: Number(page) + 1
					},
					success: function(res) {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						var now = $("#maxCnt").attr("nowCnt");
						$("#maxCnt").attr("nowCnt", Number(now) + 1);

						for (var i = 0; i < res.cnt; i++) {
							var row = $($(".ct-body-row.reserve")[0]).clone();
							row.find(".reserve_date_set").text(res.r_vos[i].reserve_sdate + " ~ " + res.r_vos[i].reserve_edate);

							row.find(".reserve_state>button").removeClass("begin");
							row.find(".reserve_state>button").removeClass("in_use");
							row.find(".reserve_state>button").removeClass("end");
							row.find(".reserve_state>button").removeClass("cancel");

							let reserve_state = "";
							switch (res.r_vos[i].reserve_state) {
								case 'begin':
									reserve_state = "이용전";
									break;
								case 'in_use':
									reserve_state = "이용중";
									break;
								case 'end':
									reserve_state = "이용완료";
									break;
								case 'cancel':
									reserve_state = "취소";
									break;

								default:
									break;
							}
							row.find(".reserve_state>button").text(reserve_state);
							row.find(".reserve_state>button").addClass(res.r_vos[i].reserve_state);

							row.find(".reserve_room_name").text(res.r_vos[i].room_name);
							row.find(".reserve_user_name").text(res.r_vos[i].user_name);
							row.find(".reserve_user_tel").text(res.r_vos[i].user_tel);
							row.find(".reserve_user_email").text(res.r_vos[i].user_email);

							let reserve_payment_state = "";
							switch (res.r_vos[i].payment_state) {
								case 'T':
									reserve_payment_state = "선";
									break;
								case 'F':
									reserve_payment_state = "후";

								default:
									break;
							}
							row.find(".reserve-price").text(res.r_vos[i].actual_payment);
							row.find(".reserve_is_cancle").attr("reserve_no", res.r_vos[i].reserve_no);

							$(".reserve_state").append(row);
						}
					},
					error: function() {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");
					}
				});
			}
		}
	});

	// 예약 취소 버튼 클릭 -> 취소 팝업 SHOW
	$(".reserve-ct").on("click", ".reserve_is_cancle", function() {
		$(".popup-background:eq(0)").removeClass("blind");
		$("#reserve-delete-popup").removeClass("blind");
		console.log($(this).parents(".reserve-btn-cell").siblings(".reserve_user_email"));
		var user_email = $(this).parents(".reserve-btn-cell").siblings(".reserve_user_email").text();
		var reserve_stime = $(this).parents(".reserve-btn-cell").siblings(".reserve_date_set").text().split(" ~ ")[0];
		var reserve_etime = $(this).parents(".reserve-btn-cell").siblings(".reserve_date_set").text().split(" ~ ")[1];
		
		$("#reserve-delete-btn").prop("reserve_no", $(this).attr("reserve_no"));
		$("#reserve-delete-btn").prop("user_no", $(this).attr("user_no"));
		$("#reserve-delete-btn").prop("user_email", user_email);
		$("#reserve-delete-btn").prop("reserve_stime", reserve_stime);
		$("#reserve-delete-btn").prop("reserve_etime", reserve_etime);
	});

	// 예약 취소 팝업 - 취소 버튼 클릭 -> 취소 로직 처리
	$("#reserve-delete-btn").click(function() {
		//로딩 화면
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");
		
		//ajax 통신
		$.ajax({
			url: "/backoffice/reservation_cancel",
			type: "POST",
			dataType: 'json',
			data: {				backoffice_no: $.cookie("backoffice_no"),
				reserve_no: $(this).prop("reserve_no"),
				user_no: $(this).prop("user_no"),
				user_email : $(this).prop("user_email"),
				reserve_stime : $(this).prop("reserve_stime"),
				reserve_etime : $(this).prop("reserve_etime")
			},
			success: function(res) {
				//로딩 화면 닫기
				$("#spinner-section").addClass("blind");
				
				$(".popup-background:eq(0)").addClass("blind");
				$("#reserve-delete-popup").addClass("blind");
				
				if (res.result == 1) {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("예약이 취소되었습니다.");
					$("#common-alert-btn").attr("is_reload", true);
				} else {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("예약 취소에 실패하셨습니다.");
				}
			},
			error: function() {
				//로딩 화면 닫기
				$(".popup-background:eq(1)").addClass("blind");
				$("#spinner-section").addClass("blind");
			}
		});
	});

	// 예약 취소 팝업 - 닫기 버튼 클릭 -> 예약 취소 여부 묻는 팝업 닫기
	$("#reserve-delete-closeBtn").click(function() {
		$(".popup-background:eq(0)").addClass("blind");
		$("#reserve-delete-popup").addClass("blind");
		$("#reserve-delete-btn").attr("reserve_no", "");
	});
});