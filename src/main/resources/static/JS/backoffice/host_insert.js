/**
 * @author : 전판근, 김예은
 */

// ******************
// 호스트 신청에 필요한 JS
// ******************

$(function() {
	var tag = {};
	var counter = 0;

	var margin_tag_list = [];

	// server로 보낼 값.
	var backoffice_tag = "";

	// 입력한 값을 태그로 생성
	function addTag(value) {
		tag[counter] = value;
		counter++;	// del-btn의 고유 id
	}

	// tag 안에 있는 값을 array type으로 만들어서 넘긴다.
	function marginTag() {
		margin_tag_list = [];

		return Object.values(tag).filter(function(word) {
			return word !== "";
		});
	}

	function toStringTag(list) {
		backoffice_tag = "";

		for (var i = 0; i < list.length; i++) {
			if (i != (list.length - 1)) {
				backoffice_tag += (list[i] + ",");
			} else {
				backoffice_tag += list[i];
			}
		}

		$("#real-input-tag").val(backoffice_tag);
	}

	$("#backoffice_tag").on("keypress", function(e) {
		var self = $(this);

		// 엔터나 스페이스바 눌렀을 때 생성
		if (e.key === "Enter" || e.keyCode == 32) {
			var tagValue = self.val();	// 값 가져오기

			// 해시태그 값이 없으면 실행되지 않음.
			if (tagValue !== "") {
				// 같은 태그가 있는지 검사. 있으면 해당 값이 array로 return
				var result = Object.values(tag).filter(function(word) {
					return word === tagValue;
				});

				// 해시태그 중복 확인
				if (result.length == 0 && margin_tag_list.length < 5) {
					$("#tag-list").append("<li class='tag-item'>" + tagValue + "<span class='del-btn' idx='" + counter + "'>x</span></li>");
					addTag(tagValue);
					margin_tag_list = marginTag();
					toStringTag(margin_tag_list);
					self.val("");
				} else if (margin_tag_list.length >= 5) {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("해시태그는 최대 5개 입니다.");
					self.val("");
				} else {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("중복된 해시태그 입니다.");
					self.val("");
				}
			}
			e.preventDefault();	// SpaceBar 시 빈공간 생기지 않도록 방지
		}
	});

	// 삭제 버튼
	// 인덱스 검사 후 삭제
	$(document).on("click", ".del-btn", function(e) {
		var index = $(this).attr("idx");
		tag[index] = "";
		margin_tag_list = marginTag();
		toStringTag(margin_tag_list);
		$(this).parent().remove();
	});

	/** 공용 알러트 창닫기 버튼 */
	$("#common-alert-btn").click(function() {
		$(".popup-background:eq(1)").addClass("blind");
		$("#common-alert-popup").addClass("blind");
	});

	$("input, textarea").click(function() {
		if ($(this).hasClass("null-input-border")) {
			$(this).removeClass("null-input-border");
		}
	});

	// 빈 항목 팝업 닫기
	$('#empty-fail-alert-btn').click(function() {
		$('#fail-alert-popup').addClass('blind');
		$('.popup-background:eq(0)').addClass('blind');
	});


	// TIME PICKER
	$('.time-picker').timepicker({
		timeFormat: 'HH:mm',
		interval: 60,
		defaultTime: '09',
		dynamic: false,
		dropdown: true,
		scrollbar: true,
	})

	// 휴무일 체크시 timepicker block
	$('#sun_dayoff').click(function() {
		if (this.checked) {
			$('#sun_stime').attr('disabled', true);
			$('#sun_etime').attr('disabled', true);
		} else {
			$('#sun_stime').attr('disabled', false);
			$('#sun_etime').attr('disabled', false);
		}
	})

	$('#mon_dayoff').click(function() {
		if (this.checked) {
			$('#mon_stime').attr('disabled', true);
			$('#mon_etime').attr('disabled', true);
		} else {
			$('#mon_stime').attr('disabled', false);
			$('#mon_etime').attr('disabled', false);
		}
	})

	$('#tue_dayoff').click(function() {
		if (this.checked) {
			$('#tue_stime').attr('disabled', true);
			$('#tue_etime').attr('disabled', true);
		} else {
			$('#tue_stime').attr('disabled', false);
			$('#tue_etime').attr('disabled', false);
		}
	})

	$('#wed_dayoff').click(function() {
		if (this.checked) {
			$('#wed_stime').attr('disabled', true);
			$('#wed_etime').attr('disabled', true);
		} else {
			$('#wed_stime').attr('disabled', false);
			$('#wed_etime').attr('disabled', false);
		}
	})

	$('#thu_dayoff').click(function() {
		if (this.checked) {
			$('#thu_stime').attr('disabled', true);
			$('#thu_etime').attr('disabled', true);
		} else {
			$('#thu_stime').attr('disabled', false);
			$('#thu_etime').attr('disabled', false);
		}
	})

	$('#fri_dayoff').click(function() {
		if (this.checked) {
			$('#fri_stime').attr('disabled', true);
			$('#fri_etime').attr('disabled', true);
		} else {
			$('#fri_stime').attr('disabled', false);
			$('#fri_etime').attr('disabled', false);
		}
	})

	$('#sat_dayoff').click(function() {
		if (this.checked) {
			$('#sat_stime').attr('disabled', true);
			$('#sat_etime').attr('disabled', true);
		} else {
			$('#sat_stime').attr('disabled', false);
			$('#sat_etime').attr('disabled', false);
		}
	});

	/** 공간 타입 체크 박스 - 데스크/회의룸 둘 중 하나라도 체크 시 오피스는 체크할 수 없음 */
	$("#type_checkbox_desk, #type_checkbox_meeting_room").click(function() {
		$("#type_checkbox_office").attr("disabled", true);
		$("#type_checkbox_office").siblings("label").css("text-decoration", "line-through");

		if (!$("#type_checkbox_desk").is(':checked') && !$("#type_checkbox_meeting_room").is(':checked')) {
			$("#type_checkbox_office").attr("disabled", false);
			$("#type_checkbox_office").siblings("label").css("text-decoration", "none");
		}
	});

	/** 공간 타입 체크 박스 - 오피스 체크 시 데스크/회의룸은 체크할 수 없음 */
	$("#type_checkbox_office").click(function() {
		$("#type_checkbox_desk").attr("disabled", true);
		$("#type_checkbox_desk").siblings("label").css("text-decoration", "line-through");
		$("#type_checkbox_meeting_room").attr("disabled", true);
		$("#type_checkbox_meeting_room").siblings("label").css("text-decoration", "line-through");


		if (!$("#type_checkbox_office").is(':checked')) {
			$("#type_checkbox_desk").attr("disabled", false);
			$("#type_checkbox_desk").siblings("label").css("text-decoration", "none");
			$("#type_checkbox_meeting_room").attr("disabled", false);
			$("#type_checkbox_meeting_room").siblings("label").css("text-decoration", "none");
		}
	});


	/** 사업자 번호 형식 맞는지 확인 */
	$("#backoffice_id").on('keyup keydown', function() {
		/* 사업자등록번호 */
		var regExp = /^([0-9]{3})-?([0-9]{2})-?([0-9]{5})$/;
		if (!regExp.test($("#backoffice_id").val().trim())) {
			$(".warning-text:eq(0)").removeClass("blind");
		} else {
			$(".warning-text:eq(0)").addClass("blind");
		}
	});

	/** 전화번호 형식 맞는지 확인 */
	$("#backoffice_tel").on('keyup keydown', function() {
		/* 유전 전화 + 휴대폰 번호 */
		var telExp = /^(0[2-8][0-5]?|01[01346-9])-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;

		/* 대표전화번호 1588 등 */
		var telExp2 = /^(1544|1566|1577|1588|1644|1688)-?([0-9]{4})$/;

		if (!telExp.test($("#backoffice_tel").val().trim()) && !telExp2.test($("#backoffice_tel").val().trim())) {
			$(".warning-text:eq(1)").removeClass("blind");
		} else {
			$(".warning-text:eq(1)").addClass("blind");
		}

	});


	$("#backoffice_info").on("keydown keyup", function() {
		$(".b_info_txt_length").text($(this).val().length);

		if ($(this).val().length > 500) {
			$(this).val($(this).val().substring(0, 500));
		}
	});


	var mail_flag = true;
	/** 인증번호 발송 버튼 클릭 **/
	$("#btn-certification").click(function() {
		if (!$("#btn-certification").prop("check")) {
			if ($("#backoffice_email").val().trim().length > 0) {
				var email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

				if (email.test($("#backoffice_email").val().trim())) {
					if(mail_flag){
						//로딩 화면
						$(".popup-background:eq(1)").removeClass("blind");
						$("#spinner-section").removeClass("blind");
						mail_flag = false;
						
						$.ajax({
							url: "/backoffice/auth",
							type: "GET",
							dataType: 'json',
							data: {
								backoffice_email: $("#backoffice_email").val().trim()
							},
							success: function(res) {
								mail_flag = true;
								
								//로딩 화면 닫기
								$(".popup-background:eq(1)").addClass("blind");
								$("#spinner-section").addClass("blind");
	
								// 이메일 중복 성공
								if (res.result == 1) {
									$("#btn-certification").prop("check", true);
									timer();
									$("#backoffice_email").attr("readonly", true);
									$("#backoffice_email").addClass("readOnly");
	
									$(".popup-background:eq(1)").removeClass("blind");
									$("#common-alert-popup").removeClass("blind");
									$(".common-alert-txt").html("이메일로 인증번호를 발송하였습니다.<br> 2분 내로 인증번호 인증을 완료해주세요.<br> 2분 초과 시 이메일 재인증이 필요합니다!");
								}else if (res.result == 3) {
									$(".popup-background:eq(1)").removeClass("blind");
	                                $("#common-alert-popup").removeClass("blind");
	                                $(".common-alert-txt").html("해당 이메일은 인증번호 발송 후<br> 2분이 되지 않았습니다.<br> 잠시만 기다려주세요!");
								} else {
									$(".popup-background:eq(1)").removeClass("blind");
									$("#common-alert-popup").removeClass("blind");
									$(".common-alert-txt").text("이미 존재하는 이메일입니다.");
								}
							},
							error: function() {
								mail_flag = true;
								
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
			}
			else {
				$("#backoffice_email").addClass("null-input-border");
			}
		}
	});

	/** 인증번호 확인 버튼 클릭 **/
	$("#btn-check-certification").click(function() {
		if (!$("#btn-check-certification").prop("check")) {
			if ($("#auth_code").val().trim().length > 0) {
				//로딩 화면
				$(".popup-background:eq(1)").removeClass("blind");
				$("#spinner-section").removeClass("blind");

				$.ajax({
					url: "/backoffice/authOK",
					type: "POST",
					dataType: 'json',
					data: {
						backoffice_email: $("#backoffice_email").val().trim(),
						auth_code: $("#auth_code").val().trim()
					},
					success: function(res) {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						// 이메일 중복 성공
						if (res.result == 1) {
							$("#btn-check-certification").prop("check", true);
							$("#btn-check-certification").val("인증완료");
							$("#auth_code").attr("readonly", true);
							$("#auth_code").addClass("readOnly");
							
							timer("true");

							$(".popup-background:eq(1)").removeClass("blind");
							$("#common-alert-popup").removeClass("blind");
							$(".common-alert-txt").text("인증완료 되었습니다.");
						} else {
							$(".popup-background:eq(1)").removeClass("blind");
							$("#common-alert-popup").removeClass("blind");
							$(".common-alert-txt").text("인증에 실패하였습니다.");
						}
					},
					error: function() {
						//로딩 화면 닫기
						$(".popup-background:eq(1)").addClass("blind");
						$("#spinner-section").addClass("blind");

						$(".popup-background:eq(1)").removeClass("blind");
						$("#common-alert-popup").removeClass("blind");
						$(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
					}
				});
			}
			else {
				$("#auth_code").addClass("null-input-border");
			}
		}
	});


	var submit_flag = true;
	/** 호스트 신청 버튼 클릭 */
	$("#submit").click(function() {
		// 1. 필수 input / textarea 입력되었는지 확인
		if (
			$("#owner_name").val().trim().length > 0 && $("#backoffice_id").val().trim().length > 0 &&
			$("#backoffice_name").val().trim().length > 0 && $("#company_name").val().trim().length > 0 &&
			$("#backoffice_tel").val().trim().length > 0 && $("#backoffice_email").val().trim().length > 0 &&
			$("#auth_code").val().trim().length > 0 && $("#zipcode").val().trim().length > 0 &&
			$("#backoffice_info").val().trim().length > 0
		) {
			// 2. 정규표현식을 모두 만족하는지 확인
			if ($(".warning-text:eq(0)").hasClass("blind") && $(".warning-text:eq(1)").hasClass("blind")) {
				// 3. 이메일 인증 완료 되었는지 확인
				if ($("#btn-certification").prop("check") && $("#btn-check-certification").prop("check")) {
					// 4. 공간 타입을 선택했는지 확인
					var desk_checked = $('#type_checkbox_desk').is(':checked');
					var meeting_room_checked = $('#type_checkbox_meeting_room').is(':checked');
					var office_checked = $('#type_checkbox_office').is(':checked');

					if (desk_checked || meeting_room_checked || office_checked) {
						if(submit_flag){
							submit_flag = false;
							$("#real-submit").click();
						}
					} else {
						$(".popup-background:eq(1)").removeClass("blind");
						$("#common-alert-popup").removeClass("blind");
						$(".common-alert-txt").text("공간 타입을 선택해주세요.");
					}

				} else {
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("이메일 인증을 완료해주세요.");
				}
			}

		}
		else {
			if ($("#owner_name").val().trim().length == 0) {
				$("#owner_name").addClass("null-input-border");
			}
			if ($("#backoffice_id").val().trim().length == 0) {
				$("#backoffice_id").addClass("null-input-border");
			}
			if ($("#backoffice_name").val().trim().length == 0) {
				$("#backoffice_name").addClass("null-input-border");
			}
			if ($("#company_name").val().trim().length == 0) {
				$("#company_name").addClass("null-input-border");
			}
			if ($("#backoffice_tel").val().trim().length == 0) {
				$("#backoffice_tel").addClass("null-input-border");
			}
			if ($("#backoffice_email").val().trim().length == 0) {
				$("#backoffice_email").addClass("null-input-border");
			}
			if ($("#auth_code").val().trim().length == 0) {
				$("#auth_code").addClass("null-input-border");
			}
			if ($("#zipcode").val().trim().length == 0) {
				$("#zipcode").addClass("null-input-border");
				$("#roadname_address").addClass("null-input-border");
				$("#number_address").addClass("null-input-border");
			}
			if ($("#backoffice_info").val().trim().length == 0) {
				$("#backoffice_info").addClass("null-input-border");
			}
		}

	});
	
	var time = "";
	function timer(check){
		var minute = 1;
		var seconds = 60;
		
		if(check == "true"){
			clearInterval(time);
			$("#btn-certification").val("인증 완료");
			return;
		}
		
		time = setInterval(function(){
		    seconds--;
		    
		    if(seconds <= 9) $("#btn-certification").val("0"+minute + " : "+ "0"+seconds);
		    else $("#btn-certification").val("0"+minute + " : "+ seconds);
		    
		    if(seconds == 0){
		        if(minute != 0){
		            --minute;
		            seconds = 60;
		        }else{
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").html("이메일 인증 시간을 초과했습니다.<br>다시 시도해주세요.");
					
					$("#btn-certification").prop("check", false);
					$("#btn-certification").val("이메일 입력");
                    $("#backoffice_email").val("");
                    $("#backoffice_email").attr("readonly", false);
                    $("#backoffice_email").removeClass("readOnly");
                    
                    $("#btn-check-certification").prop("check", false);
					$("#btn-check-certification").val("인증번호 확인");
                    $("#auth_code").val("");
					$("#auth_code").attr("readonly", false);
					$("#auth_code").removeClass("readOnly");
					
		            clearInterval(time);
		        }
		    }
		}, 1000);
		
	}

});