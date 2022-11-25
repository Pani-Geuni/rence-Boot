/**
 * @author : 전판근
 */

$(function() {

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

	// *************
	// 전체 선택 체크박스
	// *************
	$('#select-all-room').click(function() {
		console.log('asdf')

		if ($(this).is(':checked')) {
			$('.room-checkbox').attr('checked', true)
		} else {
			$('.room-checkbox').attr('checked', false)
		}
	})

	$('#select-all-room').change(function() {
		if ($(this).is(':checked')) {
			$("input:checkbox[name='select-room']").prop('checked', true)
		} else {
			$("input:checkbox[name='select-room']").prop('checked', false)
		}
	})


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
							page:1
						},

						success: function(res) {
							//로딩 화면 닫기
				            $(".popup-background:eq(1)").addClass("blind");
				            $("#spinner-section").addClass("blind");
				            
				            console.log(res);
				            
				            $("#maxCnt").attr("maxCnt", res.maxCnt);
				            $("#maxCnt").attr("nowCnt", res.nowCnt);
				            
							if (res.cnt > 0) {
								let empty_row = $($('.ct-body-row')[0]).clone();
								$(".ct-body").empty();
								$(".ct-body").append(empty_row);

								for (var i = 0; i < res.cnt; i++) {
									let body_row = $($('.ct-body-row')[0]).clone();
									body_row.removeClass("blind");

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
						error: function(error) {
							console.log(error);
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
	
	// 스케줄 - 스크롤을 이용한 페이징
	$(".schedule-ct").scroll(function(){
        if(Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')){
            if($(".ct-body-row").length - 1 < Number($("#maxCnt").attr("maxCnt"))){
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

                $.ajax({
                    url : "/backoffice/schedule_research_paging",
                    type : "GET",
                    dataType : 'json',
                    data : {
                        backoffice_no: backoffice_no,
						not_sdate: not_sdate,
						not_edate: not_edate,
						not_stime: not_stime,
						not_etime: not_etime,
						off_type: off_type,
                        page : Number(page) + 1
                    },
                    success : function(res) {
                        //로딩 화면 닫기
                        $(".popup-background:eq(1)").addClass("blind");
                        $("#spinner-section").addClass("blind");

                        var now = $("#maxCnt").attr("nowCnt");
                        $("#maxCnt").attr("nowCnt", Number(now) + 1);

                        for(var i = 0; i < res.cnt; i++){
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
                    error : function() {
                        //로딩 화면 닫기
                        $(".popup-background:eq(1)").addClass("blind");
                        $("#spinner-section").addClass("blind");
                    }
                });
            }
        }
    });
	
	// 예약자 - 스크롤을 이용한 페이징
	$(".reservation-ct").scroll(function(){
        if(Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')){
            if($(".ct-body-row").length < Number($("#maxCnt").attr("maxCnt"))){
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

                $.ajax({
                    url : "/backoffice/reservation_paging",
                    type : "GET",
                    dataType : 'json',
                    data : {
                        backoffice_no: backoffice_no,
                        room_no: room_no,
						not_sdate: not_sdate,
						not_edate: not_edate,
						not_stime: not_stime,
						not_etime: not_etime,
						off_type: off_type,
                        page : Number(page) + 1
                    },
                    success : function(res) {
                        //로딩 화면 닫기
                        $(".popup-background:eq(1)").addClass("blind");
                        $("#spinner-section").addClass("blind");

                        var now = $("#maxCnt").attr("nowCnt");
                        $("#maxCnt").attr("nowCnt", Number(now) + 1);

                        for(var i = 0; i < res.cnt; i++){
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
                    error : function() {
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

	$("#schedule-confirm-btn").click(function() {
		console.log("confirm button click")
	})

	// 팝업
	$("#radio-check-closeBtn").click(function() {
		$("#radio-check-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	})

	$("#time-input-closeBtn").click(function() {
		$("#time-input-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	})
	
	$("#no-reservation-closeBtn").click(function() {
		$("#no-reservation-popup").addClass("blind");
		$(".popup-background:eq(0)").addClass("blind");
	})
})
