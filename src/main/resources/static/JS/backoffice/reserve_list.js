/**
 * @author 김예은
 */
$(function() {
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
							row.find(".reserve-price").text(res.r_vos[i].actual_payment);
							
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
							row.find(".reserve_payment_state").text(reserve_payment_state);
							
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
});