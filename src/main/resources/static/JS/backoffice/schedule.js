/**
 * @author : 전판근, 김예은, 최진실
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

	$("input:radio[name='set_schedule']:eq(0)").click(function() {
		$(".off-type-warning:eq(0)").removeClass('blind');
		$(".off-type-warning:eq(1)").addClass('blind');
	})

	$("input:radio[name='set_schedule']:eq(1)").click(function() {
		$(".off-type-warning:eq(0)").addClass('blind');
		$(".off-type-warning:eq(1)").removeClass('blind');
	})

	/***** ************** *****/
	/***** DATEPICKER부분 *****/
	/***** ************** *****/
	$(".time-input").datetimepicker({
		dateFormat: 'yy/mm/dd',
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		minDate: new Date().toLocaleDateString().replaceAll(". ", "/"),

		// timepicker 설정
		timeFormat: 'HH:mm:ss',
		controlType: 'select',
		oneLine: true,
	});

	$(".type-border-txt.time-input:eq(0)").change(function() {
		$("input:checkbox[name='select-room']").prop('checked', false);
	})

	$(".type-border-txt.time-input:eq(1)").change(function() {
		$("input:checkbox[name='select-room']").prop('checked', false);
	})

	// *************
	// 전체 선택 체크박스
	// *************
	$('#select-all-room').click(function() {
		if ($(this).is(':checked')) {
			$(this).attr('checked', true);
		} else {
			$(this).attr('checked', false);
		}
	});

	$('#select-all-room').change(function() {
		if ($(this).is(':checked')) {
			$("input:checkbox[name='select-room']").each(function(index) {
				if ($(this).is(':disabled')) {
					console.log(index)
					$(this).prop('checked', false);
				} else {
					$(this).prop('checked', true);
				}
			});

		} else {
			$("input:checkbox[name='select-room']").prop('checked', false);
		}
	});


	// ********************
	// 일정 적용 가능 공간 리스트
	// ********************
	$(".btn-schedule-research").click(function() {

		if ($("input:radio[name='set_schedule']").is(':checked')) {
			if ($(".time-input:eq(0)").val() != '' && $(".time-input:eq(1)").val() != '') {
				if ($(".time-input:eq(0)").val() > $(".time-input:eq(1)").val()) {
					$(".popup-background:eq(0)").removeClass("blind");
					$("#time-input-popup").removeClass("blind");
				} else {
					let query = location.search;
					let param = new URLSearchParams(query);
					let backoffice_no = param.get('backoffice_no');

					let sDateTime = $(".time-input:eq(0)").val().split(' ');
					let eDateTime = $(".time-input:eq(1)").val().split(' ');

					let not_sdate = sDateTime[0];
					let not_stime = sDateTime[1];
					let not_edate = eDateTime[0];
					let not_etime = eDateTime[1];
					let off_type = $("input:radio[name='set_schedule']:checked").val();

					//로딩 화면
					$(".popup-background:eq(1)").removeClass("blind");
					$("#spinner-section").removeClass("blind");

					$.ajax({
						url: "/backoffice/schedule_research",
						type: "GET",
						dataType: "JSON",
						data: {
							backoffice_no: backoffice_no,
							not_sdate: not_sdate,
							not_edate: not_edate,
							not_stime: not_stime,
							not_etime: not_etime,
							off_type: off_type,
							page: 1
						},

						success: function(res) {
							//로딩 화면 닫기
							$(".popup-background:eq(1)").addClass("blind");
							$("#spinner-section").addClass("blind");

							$("input:checkbox[name='select-room']").prop('checked', false);

							$("#maxCnt").attr("maxCnt", res.maxCnt);
							$("#maxCnt").attr("nowCnt", res.nowCnt);

							if (res.cnt > 0) {
								let empty_row = $($('.ct-body-row')[0]).clone();
								$(".ct-body").empty();
								$(".ct-body").append(empty_row);

								for (var i = 0; i < res.cnt; i++) {
									let body_row = $($('.ct-body-row')[0]).clone();
									body_row.removeClass("blind");

									//예약 있을 시, 체크박스 disabled
									if (res.sc_vos[i].reserve_cnt > 0) {
										body_row.find('.room-checkbox').attr('disabled', true);
									}

									body_row.find("#room_no").attr("room_no", res.sc_vos[i].room_no);

									let kor_room_name = "";
									switch (res.sc_vos[i].room_type) {
										case 'desk':
											kor_room_name = "데스크";
											body_row.find(".room_type").text(kor_room_name);
											break;
										case 'meeting_04':
											kor_room_name = "미팅룸 (4인)";
											body_row.find(".room_type").text(kor_room_name);
											break;
										case 'meeting_06':
											kor_room_name = "미팅룸 (6인)";
											body_row.find(".room_type").text(kor_room_name);
											break;
										case 'meeting_10':
											kor_room_name = "미팅룸 (10인)";
											body_row.find(".room_type").text(kor_room_name);
											break;
										case 'office':
											kor_room_name = "오피스";
											body_row.find(".room_type").text(kor_room_name);
											break;

										default:
											break;
									}

									body_row.find(".room_name").text(res.sc_vos[i].room_name);

									if (res.sc_vos[i].reserve_is === 'X') {
										body_row.find(".reserve_is").append('<img src="/static/IMG/dash-board/ico-close.svg" />');
									} else {
										body_row.find(".reserve_is").append('<img src="/static/IMG/dash-board/ico-circle.svg" />');
									}

									body_row.find(".reserve_cnt").text(res.sc_vos[i].reserve_cnt + "명");
									body_row.find(".reserve_cnt").attr("reserve_cnt", res.sc_vos[i].reserve_cnt);

									$(".ct-body").append(body_row);
								}

								if ($(".select-room-section").find("#schedule-confirm-btn").length == 0) {
									$(".select-room-section").append("<input type='button' id='schedule-confirm-btn' class='schedule-confirm-btn' value='일정 설정' />");
								}

							}
						},
						error: function() {
						}
					});
				}
			}

		} else {
			// 라디오 버튼 미선택 시
			$(".popup-background:eq(0)").removeClass("blind");
			$("#radio-check-popup").removeClass("blind");
		}
	});

	var scroll_flag = true;
	// 스케줄 - 스크롤을 이용한 페이징
	$(".schedule-ct").scroll(function() {
		if (Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')) {
			if ($(".ct-body-row").length - 1 < Number($("#maxCnt").attr("maxCnt")) && scroll_flag) {
				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");

				let query = location.search;
				let param = new URLSearchParams(query);
				let backoffice_no = param.get('backoffice_no');

				let sDateTime = $(".time-input:eq(0)").val().split(' ');
				let eDateTime = $(".time-input:eq(1)").val().split(' ');

				let not_sdate = sDateTime[0];
				let not_stime = sDateTime[1];
				let not_edate = eDateTime[0];
				let not_etime = eDateTime[1];
				let off_type = $("input:radio[name='set_schedule']:checked").val();

				var page = $("#maxCnt").attr("nowCnt");

				scroll_flag = false;
				$.ajax({
					url: "/backoffice/schedule_research_paging",
					type: "GET",
					dataType: 'json',
					data: {
						backoffice_no: backoffice_no,
						not_sdate: not_sdate,
						not_edate: not_edate,
						not_stime: not_stime,
						not_etime: not_etime,
						off_type: off_type,
						page: Number(page) + 1
					},
					success: function(res) {
						scroll_flag = true;

						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						var now = $("#maxCnt").attr("nowCnt");
						$("#maxCnt").attr("nowCnt", Number(now) + 1);

						for (var i = 0; i < res.cnt; i++) {
							var row = $($(".ct-body-row")[0]).clone();
							row.removeClass("blind");

							row.find("#room_no").attr("room_no", res.sc_vos[i].room_no);

							let kor_room_name = "";
							switch (res.sc_vos[i].room_type) {
								case 'desk':
									kor_room_name = "데스크";
									break;
								case 'meeting_04':
									kor_room_name = "미팅룸 (4인)";
									break;
								case 'meeting_06':
									kor_room_name = "미팅룸 (6인)";
									break;
								case 'meeting_10':
									kor_room_name = "미팅룸 (10인)";
									break;
								case 'office':
									kor_room_name = "오피스";
									break;

								default:
									break;
							}
							row.find(".room_type").text(kor_room_name);

							row.find(".room_name").text(res.sc_vos[i].room_name);

							if (res.sc_vos[i].reserve_is === 'X') {
								row.find(".reserve_is").append('<img src="/static/IMG/dash-board/ico-close.svg" />');
							} else {
								row.find(".reserve_is").append('<img src="/static/IMG/dash-board/ico-circle.svg" />');
							}

							row.find(".reserve_cnt").text(res.sc_vos[i].reserve_cnt + "명");
							row.find(".reserve_cnt").attr("reserve_cnt", res.sc_vos[i].reserve_cnt);

							$(".schedule-ct").append(row);
						}
					},
					error: function() {
						scroll_flag = true;

						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");
					}
				});
			}
		}
	});

	var scroll_flag2 = true;
	// 예약자 - 스크롤을 이용한 페이징
	$(".reservation-ct").scroll(function() {
		if (Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')) {
			if ($(".ct-body-row").length < Number($("#maxCnt").attr("maxCnt")) && scroll_flag2) {
				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");

				let backoffice_no = $.cookie('backoffice_no');

				let room_no = window.location.href.split("&room_no=")[1].split("&")[0];

				let not_sdate = window.location.href.split("&not_sdate=")[1].split("&")[0];
				let not_stime = window.location.href.split("&not_stime=")[1].split("&")[0];
				let not_edate = window.location.href.split("&not_edate=")[1].split("&")[0];
				let not_etime = window.location.href.split("&not_etime=")[1].split("&")[0];
				let off_type = window.location.href.split("&off_type=")[1].split("&")[0];

				var page = $("#maxCnt").attr("nowCnt");

				scroll_flag2 = false;
				$.ajax({
					url: "/backoffice/reservation_paging",
					type: "GET",
					dataType: 'json',
					data: {
						backoffice_no: backoffice_no,
						room_no: room_no,
						not_sdate: not_sdate,
						not_edate: not_edate,
						not_stime: not_stime,
						not_etime: not_etime,
						off_type: off_type,
						page: Number(page) + 1
					},
					success: function(res) {
						scroll_flag2 = true;

						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						var now = $("#maxCnt").attr("nowCnt");
						$("#maxCnt").attr("nowCnt", Number(now) + 1);

						for (var i = 0; i < res.cnt; i++) {
							var row = $($(".ct-body-row")[0]).clone();

							row.find(".ct-body-cell:eq(0)>input:eq(0)").attr("room_no", res.rv_vos[i].room_no);
							row.find(".ct-body-cell:eq(0)>input:eq(1)").attr("backoffice_no", res.rv_vos[i].backoffice_no);
							row.find(".ct-body-cell:eq(0)>input:eq(2)").attr("reserve_no", res.rv_vos[i].reserve_no);

							row.find(".ct-body-cell:eq(1)>span").text(res.rv_vos[i].user_name);
							row.find(".ct-body-cell:eq(2)>span").text(res.rv_vos[i].user_email);
							row.find(".ct-body-cell:eq(3)>span").text(res.rv_vos[i].user_tel);
							row.find(".ct-body-cell:eq(4)>span").text(res.rv_vos[i].reserve_stime + " ~ " + res.rv_vos[i].reserve_etime);

							$(".reservation-ct").append(row);
						}
					},
					error: function() {
						scroll_flag2 = true;

						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");
					}
				});
			}
		}
	});


	// 일정에 따른 예약자 상세 보기 버튼 클릭 이벤트
	$(".ct-body").on('click', '.reserve_cnt', function() {
		let query = location.search;
		let param = new URLSearchParams(query);
		let backoffice_no = param.get('backoffice_no');

		let sDateTime = $(".time-input:eq(0)").val().split(' ');
		let eDateTime = $(".time-input:eq(1)").val().split(' ');

		let not_sdate = sDateTime[0];
		let not_stime = sDateTime[1];
		let not_edate = eDateTime[0];
		let not_etime = eDateTime[1];
		let room_no = $(this).parent().siblings(".ct-body-cell:eq(0)").children().attr("room_no");
		let off_type = $("input:radio[name='set_schedule']:checked").val();
		let reserve_cnt = parseInt($(this).attr("reserve_cnt"));


		//로딩 화면
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");


		if (reserve_cnt != 0) {
			//로딩 화면 닫기
			$(".popup-background:eq(1)").addClass("blind");
			$("#spinner-section").addClass("blind");

			location.href = "/backoffice/reservation?backoffice_no=" + backoffice_no + "&room_no=" + room_no + "&not_sdate=" + not_sdate + "&not_edate=" + not_edate + "&not_stime=" + not_stime + "&not_etime=" + not_etime + "&off_type=" + off_type + "&page=1";
		} else {
			//로딩 화면 닫기
			$(".popup-background:eq(1)").addClass("blind");
			$("#spinner-section").addClass("blind");

			$(".popup-background:eq(0)").removeClass("blind");
			$("#no-reservation-popup").removeClass("blind");
		}
	});

	// 일정 설정
	var check_room = "";
	$(document).on('click', "#schedule-confirm-btn", function() {
		console.log("in");
		check_room = $("input[type=checkbox]:checked").parents(".ct-body-row");
		if (check_room.length != 0) {
			let backoffice_no = $.cookie('backoffice_no');
			let sDateTime = $(".time-input:eq(0)").val().split(' ');
			let eDateTime = $(".time-input:eq(1)").val().split(' ');

			let not_sdate = sDateTime[0];
			let not_stime = sDateTime[1];
			let not_edate = eDateTime[0];
			let not_etime = eDateTime[1];
			let off_type = $("input:radio[name='set_schedule']:checked").val();
			var flag = "";

			for (var i = 0; i < check_room.length; i++) {
				let room_no = $(check_room[i]).find("#room_no").attr("room_no");

				if (room_no != null) {
					//로딩 화면
					$(".popup-background:eq(1)").removeClass("blind");
					$("#spinner-section").removeClass("blind");

					$.ajax({
						url: "/backoffice/scheduleOK",
						type: "POST",
						dataType: 'json',
						data: {
							backoffice_no: backoffice_no,
							room_no: room_no,
							not_sdate: not_sdate,
							not_edate: not_edate,
							not_stime: not_stime,
							not_etime: not_etime,
							off_type: off_type,
						},
						success: function(res) {
							if (res.result == 1) {
								flag = 1;

							} else {
								flag = 0;
							}

						},
						error: function() {
							flag = 0;
						}
					});
				}
				if (flag = 1) {
					//로딩 화면 닫기
					$(".popup-background:eq(1)").addClass("blind");
					$("#spinner-section").addClass("blind");

					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("일정이 설정되었습니다.");

					// 리스트 새로고침
					$("#common-alert-btn").click(function() {
						$(".btn-schedule-research").trigger("click");
					});
				}
				if (flag = 0) {
					//로딩 화면 닫기
					$(".popup-background:eq(1)").addClass("blind");
					$("#spinner-section").addClass("blind");

					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
					break;
				}
			}
		} else {
			$(".popup-background:eq(1)").removeClass("blind");
			$("#common-alert-popup").removeClass("blind");
			$(".common-alert-txt").text("공간을 선택해주세요.");
		}
	})

	// 팝업
	$("#radio-check-closeBtn").click(function() {
		$("#radio-check-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	});

	$("#time-input-closeBtn").click(function() {
		$("#time-input-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	});

	$("#no-reservation-closeBtn").click(function() {
		$("#no-reservation-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	});
	
	
	// ************************
	// 예약자 리스트 전체 선택 체크박스
	// ************************
	$('#select-all-reservation').click(function() {
		if ($(this).is(':checked')) {
			$(this).attr('checked', true);
		} else {
			$(this).attr('checked', false);
		}
	});

	$('#select-all-reservation').change(function() {
		if ($(this).is(':checked')) {
			$("input:checkbox[name='reserve_no']").prop('checked', true);
		} else {
			$("input:checkbox[name='reserve_no']").prop('checked', false);
		}
	});


	var check_arr = "";
	// 예약 취소 버튼 클릭 -> 취소 팝업 SHOW
	$("#btn-reserve-cancel").on("click", function() {
		check_arr = $("input[type=checkbox]:checked").parents(".ct-body-row");

		if (check_arr.length == 0) {
			$(".popup-background:eq(1)").removeClass("blind");
			$("#common-alert-popup").removeClass("blind");
			$(".common-alert-txt").text("선택된 항목이 없습니다.");
		} else {
			var tmp_flag = false;
			for (var i = 0; i < check_arr.length; i++) {
				var s_date = $(check_arr[i]).find(".reserve-stime").text().split(" ~ ")[0];

				if (new Date(s_date) < new Date()) {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").html("선택된 항목 중 이용중인 예약이 존재합니다.<br><br> 이용중인 항목은 예약 취소할 수 없습니다.");
					tmp_flag = true;
					break;
				}
			}

			if (!tmp_flag) {
				$(".popup-background:eq(0)").removeClass("blind");
				$("#reserve-delete-popup").removeClass("blind");
			}
		}
	});

	// 예약 취소 팝업 - 취소 버튼 클릭 -> 취소 로직 처리
	$("#reserve-delete-btn").click(function() {
		// START FOR문
		for (var i = 0; i < check_arr.length; i++) {
			var stop_flag = false;

			var reserve_no = $(check_arr[i]).find("#reserve_no").attr("reserve_no");
			var user_no = $(check_arr[i]).find(".user_no").attr("user_no");
			var user_email = $(check_arr[i]).find(".reserve_user_email").text().trim();
			var reserve_stime = $(check_arr[i]).find(".reserve_date_set").text().trim().split(" ~ ")[0].trim();
			var reserve_etime = $(check_arr[i]).find(".reserve_date_set").text().trim().split(" ~ ")[1].trim();

			//로딩 화면
			$(".popup-background:eq(1)").removeClass("blind");
			$("#spinner-section").removeClass("blind");

			//ajax 통신
			$.ajax({
				url: "/backoffice/reservation_cancel",
				type: "POST",
				dataType: 'json',
				data: {
					backoffice_no: $.cookie("backoffice_no"),
					reserve_no: reserve_no,
					user_no: user_no,
					user_email: user_email,
					reserve_stime: reserve_stime,
					reserve_etime: reserve_etime
				},
				success: function(res) {
					if (res.result != 1) {
						stop_flag = true;
					}
				},
				error: function() {
					stop_flag = true;
				}
			});

			if (stop_flag) {
				break;
			}
			
		}
		// END FOR문
		
		$(".popup-background:eq(0)").addClass("blind");
		$("#reserve-delete-popup").addClass("blind");
		
		if (stop_flag) {
			$(".popup-background:eq(1)").removeClass("blind");
			$("#common-alert-popup").removeClass("blind");
			$(".common-alert-txt").text("예약 취소에 실패하셨습니다.");
		}
		else {
			//로딩 화면 닫기
			$(".popup-background:eq(1)").addClass("blind");
			$("#spinner-section").addClass("blind");
			
			$(".popup-background:eq(1)").removeClass("blind");
			$("#common-alert-popup").removeClass("blind");
			$(".common-alert-txt").text("예약이 취소되었습니다.");
			$("#common-alert-btn").attr("is_reload", true);
		}
	});

	// 예약 취소 팝업 - 닫기 버튼 클릭 -> 예약 취소 여부 묻는 팝업 닫기
	$("#reserve-delete-closeBtn").click(function() {
		$(".popup-background:eq(0)").addClass("blind");
		$("#reserve-delete-popup").addClass("blind");
		$("#reserve-delete-btn").attr("reserve_no", "");
	});


	// 휴무 일정 캘린더 팝업
	// 휴무 일정 캘린더 팝업
	$("#btn-dayoff-calendar").click(function() {
		let backoffice_no = $.cookie('backoffice_no');
		let month = '';

		//로딩 화면 열기
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");
		$.ajax({
			url: "/backoffice/schedule_calendar",
			type: "GET",
			dataType: 'json',
			data: {
				backoffice_no: backoffice_no
			},
			success: function(res) {
				$(".popup-background:eq(0)").removeClass("blind");
				$(".dayoff-calendar-wrap").removeClass("blind");

				//로딩 화면 닫기
				$(".popup-background:eq(1)").addClass("blind");
				$("#spinner-section").addClass("blind");
				
				month = res.month + "월";
				$("#month").text(month);

				if (res.cnt > 0) {
					let empty_item = $($(".dayoff-list-item")[0]).clone();
					$(".dayoff-list").empty();
					$(".dayoff-list").append(empty_item);

					for (var i = 0; i < res.cnt; i++) {
						let dayoff_list_item = $($(".dayoff-list-item")[0]).clone();
						dayoff_list_item.removeClass("blind");

						let sdate = '';
						let edate = '';
						let stime = '';
						let etime = '';
						let date_duration = '';
						let time_duration = '';

						dayoff_list_item.find(".dayoff-list-item-title").text(res.vos[i].room_name);

						if (res.vos[i].schedule_type === 'dayoff') {
							sdate = res.vos[i].sdate;
							edate = res.vos[i].edate;
							date_duration = sdate + " ~ " + edate;
							dayoff_list_item.find(".badge").text("임시 휴무");

						} else if (res.vos[i].schedule_type === 'breaktime') {
							sdate = res.vos[i].sdate;
							stime = res.vos[i].stime;
							etime = res.vos[i].etime;
							date_duration = sdate;
							time_duration = stime + " ~ " + etime;
							dayoff_list_item.find(".badge").text("브레이크 타임");
						}

						dayoff_list_item.find(".dayoff-list-item-date").text(date_duration);
						dayoff_list_item.find(".dayoff-list-item-time").text(time_duration);
						dayoff_list_item.find(".dayoff-cancel-btn").attr("schedule_no", res.vos[i].schedule_no);
						dayoff_list_item.find(".dayoff-list-item-title").attr("room_no", res.vos[i].room_no);

						$(".dayoff-list").append(dayoff_list_item);
					}

				}


			},
			error: function() {
			}
		})
	});

	// 휴무 일정 캘린더 닫기 버튼
	$(".calendar-close-btn").click(function() {
		$(".popup-background:eq(0)").addClass("blind");
		$(".dayoff-calendar-wrap").addClass("blind");
	})

	let schedule_no = '';
	// 휴무 취소 버튼 - 휴무 취소 확인 팝업 노출
	$(document).on('click', ".dayoff-cancel-btn", function() {
		$(".popup-background:eq(1)").removeClass("blind");
		$("#dayoff-cancel-popup").removeClass("blind");

		schedule_no = $(this).attr("schedule_no");
	})

	// 휴무 취소 확인 팝업 - 닫기
	$("#dayoff-cancel-confirm-closeBtn").click(function() {
		$(".popup-background:eq(1)").addClass("blind");
		$("#dayoff-cancel-popup").addClass("blind");
	})

	// 휴무 취소 확인 팝업 - 휴무 취소
	$("#dayoff-cancel-confirm-btn").click(function() {
		//로딩 화면 열기
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");

		$(".popup-background:eq(1)").addClass("blind");
		$("#dayoff-cancel-popup").addClass("blind");

		$.ajax({
			url: "/backoffice/schedule_cancel",
			type: "POST",
			dataType: 'json',
			data: {
				backoffice_no: $.cookie('backoffice_no'),
				schedule_no: schedule_no
			},

			success: function(res) {
				if (res.result == "1") {
					// 로딩 닫기
					$(".popup-background:eq(1)").addClass("blind");
					$("#spinner-section").addClass("blind");
					
					$(".popup-background:eq(1)").removeClass("blind");
					$("#dayoff-cancel-confirmOK-popup").removeClass("blind");
					
					if (res.cnt > 0) {
						let empty_item = $($(".dayoff-list-item")[0]).clone();
						$(".dayoff-list").empty();
						$(".dayoff-list").append(empty_item);

						for (var i = 0; i < res.cnt; i++) {
							let dayoff_list_item = $($(".dayoff-list-item")[0]).clone();
							dayoff_list_item.removeClass("blind");

							let sdate = '';
							let edate = '';
							let stime = '';
							let etime = '';
							let date_duration = '';
							let time_duration = '';

							dayoff_list_item.find(".dayoff-list-item-title").text(res.vos[i].room_name);

							if (res.vos[i].schedule_type === 'dayoff') {
								sdate = res.vos[i].sdate;
								edate = res.vos[i].edate;
								date_duration = sdate + " ~ " + edate;
								dayoff_list_item.find(".badge").text("임시 휴무");

							} else if (res.vos[i].schedule_type === 'breaktime') {
								sdate = res.vos[i].sdate;
								stime = res.vos[i].stime;
								etime = res.vos[i].etime;
								date_duration = sdate;
								time_duration = stime + " ~ " + etime;
								dayoff_list_item.find(".badge").text("브레이크 타임");
							}
							
							dayoff_list_item.find(".dayoff-list-item-date").text(date_duration);
							dayoff_list_item.find(".dayoff-list-item-time").text(time_duration);
							dayoff_list_item.find(".dayoff-cancel-btn").attr("schedule_no", res.vos[i].schedule_no);
							dayoff_list_item.find(".dayoff-list-item-title").attr("room_no", res.vos[i].room_no);

							$(".dayoff-list").append(dayoff_list_item);
						}

					}

				} else {
					// 로딩 닫기
					$(".popup-background:eq(1)").addClass("blind");
					$("#spinner-section").addClass("blind");
					
					$(".popup-background:eq(1)").removeClass("blind");
					$("#dayoff-cancel-fail-popup").removeClass("blind");
				}
			},error: function() {
			}

		});

	})

	$("#dayoff-cancel-confirmOK-btn").click(function() {
		$("#dayoff-cancel-confirmOK-popup").addClass("blind");
		$(".popup-background:eq(1)").addClass("blind");
	});

	$("#dayoff-cancel-fail-btn").click(function() {
		$("#dayoff-cancel-fail-popup").addClass("blind");
		$(".popup-background:eq(1)").addClass("blind");
	});
});
