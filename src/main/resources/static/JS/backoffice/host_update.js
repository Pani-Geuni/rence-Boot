/**
 * @author : 전판근
 */

$(function() {
	// TIME PICKER
	$('.time-picker.update').timepicker({
		timeFormat: 'HH:mm',
		interval: 60,
		defaultTime: $(this).val(),
		dynamic: false,
		dropdown: true,
		scrollbar: true,
	});
	
	if($('#sun_dayoff').is(":checked")){
		$('#sun_stime').attr('disabled', true);
		$('#sun_etime').attr('disabled', true);
	}
	if($('#mon_dayoff').is(":checked")){
		$('#mon_stime').attr('disabled', true);
		$('#mon_etime').attr('disabled', true);
	}
	if($('#tue_dayoff').is(":checked")){
		$('#tue_stime').attr('disabled', true);
		$('#tue_etime').attr('disabled', true);
	}
	if($('#wed_dayoff').is(":checked")){
		$('#wed_stime').attr('disabled', true);
		$('#wed_etime').attr('disabled', true);
	}
	if($('#thu_dayoff').is(":checked")){
		$('#thu_stime').attr('disabled', true);
		$('#thu_etime').attr('disabled', true);
	}
	if($('#fri_dayoff').is(":checked")){
		$('#fri_stime').attr('disabled', true);
		$('#fri_etime').attr('disabled', true);
	}
	if($('#sat_dayoff').is(":checked")){
		$('#sat_stime').attr('disabled', true);
		$('#sat_etime').attr('disabled', true);
	}
	
	$('#mon_dayoff').click(function() {
		if (this.checked) {
			$('#mon_stime').attr('disabled', true);
			$('#mon_etime').attr('disabled', true);
		} else {
			$('#mon_stime').attr('disabled', false);
			$('#mon_etime').attr('disabled', false);
		}
	});

	$('#tue_dayoff').click(function() {
		if (this.checked) {
			$('#tue_stime').attr('disabled', true);
			$('#tue_etime').attr('disabled', true);
		} else {
			$('#tue_stime').attr('disabled', false);
			$('#tue_etime').attr('disabled', false);
		}
	});

	$('#wed_dayoff').click(function() {
		if (this.checked) {
			$('#wed_stime').attr('disabled', true);
			$('#wed_etime').attr('disabled', true);
		} else {
			$('#wed_stime').attr('disabled', false);
			$('#wed_etime').attr('disabled', false);
		}
	});

	$('#thu_dayoff').click(function() {
		if (this.checked) {
			$('#thu_stime').attr('disabled', true);
			$('#thu_etime').attr('disabled', true);
		} else {
			$('#thu_stime').attr('disabled', false);
			$('#thu_etime').attr('disabled', false);
		}
	});

	$('#fri_dayoff').click(function() {
		if (this.checked) {
			$('#fri_stime').attr('disabled', true);
			$('#fri_etime').attr('disabled', true);
		} else {
			$('#fri_stime').attr('disabled', false);
			$('#fri_etime').attr('disabled', false);
		}
	});

	$('#sat_dayoff').click(function() {
		if (this.checked) {
			$('#sat_stime').attr('disabled', true);
			$('#sat_etime').attr('disabled', true);
		} else {
			$('#sat_stime').attr('disabled', false);
			$('#sat_etime').attr('disabled', false);
		}
	});

	var tag = {}
	var counter = 0

	var margin_tag_list = []

	// server로 보낼 값.
	var backoffice_tag = ''

	// 입력한 값을 태그로 생성
	function addTag(value) {
		tag[counter] = value
		counter++ // del-btn의 고유 id
	}

	// tag 안에 있는 값을 array type으로 만들어서 넘긴다.
	function marginTag() {
		margin_tag_list = []
		return Object.values(tag).filter(function(word) {
			return word !== ''
		})
	}

	var temp_tag = $("#real-input-tag").val();
	console.log("tag:::::" + temp_tag);
	var arr = temp_tag.split(',');
	console.log(arr);

	for (var i = 0; i < arr.length; i++) {
		console.log("click");
		var self = $("#backoffice_tag");

		var tagValue = arr[i]; // 값 가져오기

		// 해시태그 값이 없으면 실행되지 않음.
		// 같은 태그가 있는지 검사. 있으면 해당 값이 array로 return
		var result = Object.values(tag).filter(function(word) {
			return word === tagValue
		});

		// 해시태그 중복 확인
		if (result.length == 0 && margin_tag_list.length < 5) {
			$('#tag-list').append(
				"<li class='tag-item'>" +
				tagValue +
				"<span class='del-btn' idx='" +
				counter +
				"'>x</span></li>",
			)


			addTag(tagValue)
			margin_tag_list = marginTag()
			toStringTag(margin_tag_list)
			self.val('')
		} else if (margin_tag_list.length >= 5) {
			$('.popup-background:eq(1)').removeClass('blind')
			$('#common-alert-popup').removeClass('blind')
			$('.common-alert-txt').text('해시태그는 최대 5개 입니다.')
			self.val('')
		} else {
			$('.popup-background:eq(1)').removeClass('blind')
			$('#common-alert-popup').removeClass('blind')
			$('.common-alert-txt').text('중복된 해시태그 입니다.')
			self.val('');
		}
		console.log(arr[i])
	}


	function toStringTag(list) {
		backoffice_tag = ''

		for (var i = 0; i < list.length; i++) {
			if (i != list.length - 1) {
				backoffice_tag += list[i] + ','
			} else {
				backoffice_tag += list[i]
			}
		}

		console.log('backoffice tag : ', backoffice_tag)
		$('#real-input-tag').val(backoffice_tag)
	}

	$('#backoffice_tag').on('keypress', function(e) {
		var self = $(this)

		// 엔터나 스페이스바 눌렀을 때 생성
		if (e.key === 'Enter' || e.keyCode == 32) {
			var tagValue = self.val() // 값 가져오기

			// 해시태그 값이 없으면 실행되지 않음.
			if (tagValue !== '') {
				// 같은 태그가 있는지 검사. 있으면 해당 값이 array로 return
				var result = Object.values(tag).filter(function(word) {
					return word === tagValue
				})

				// 해시태그 중복 확인
				if (result.length == 0 && margin_tag_list.length < 5) {
					$('#tag-list').append(
						"<li class='tag-item'>" +
						tagValue +
						"<span class='del-btn' idx='" +
						counter +
						"'>x</span></li>",
					)
					addTag(tagValue)
					margin_tag_list = marginTag()
					toStringTag(margin_tag_list)
					self.val('')
				} else if (margin_tag_list.length >= 5) {
					$('.popup-background:eq(1)').removeClass('blind')
					$('#common-alert-popup').removeClass('blind')
					$('.common-alert-txt').text('해시태그는 최대 5개 입니다.')
					self.val('')
				} else {
					$('.popup-background:eq(1)').removeClass('blind')
					$('#common-alert-popup').removeClass('blind')
					$('.common-alert-txt').text('중복된 해시태그 입니다.')
					self.val('')
				}
			}
			e.preventDefault() // SpaceBar 시 빈공간 생기지 않도록 방지
		}
	})

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


	// 삭제 버튼
	// 인덱스 검사 후 삭제
	$(document).on('click', '.del-btn', function(e) {
		var index = $(this).attr('idx')
		tag[index] = ''
		margin_tag_list = marginTag()
		toStringTag(margin_tag_list)
		$(this).parent().remove()
	})
	
	var backoffice_type = "";
    $("input[name='backoffice_type']:checked").each(function() {
        backoffice_type.concat(',',$(this).val());
    });
    
	var backoffice_option = "";
    $("input[name='backoffice_option']:checked").each(function() {
        backoffice_option.concat(',',$(this).val());
    });
    
	var backoffice_around = "";
    $("input[name='backoffice_around']:checked").each(function() {
        backoffice_around.concat(',',$(this).val());
    });

	/** 호스트 수정 완료 버튼 클릭 */
	$("#submit").on('click', function() {
		// 필수 input / textarea 입력되었는지 확인
		if ($("#backoffice_info").val().trim().length > 0) {
			// 공간 타입을 선택했는지 확인
			var desk_checked = $('#type_checkbox_desk').is(':checked');
			var meeting_room_checked = $('#type_checkbox_meeting_room').is(':checked');
			var office_checked = $('#type_checkbox_office').is(':checked');

			if (desk_checked || meeting_room_checked || office_checked) {
				$("#backoffice_type").val(backoffice_type);
				$("#backoffice_option").val(backoffice_option);
				$("#backoffice_around").val(backoffice_around);
				$("#real-submit").click();
			} else {
				$(".popup-background:eq(1)").removeClass("blind");
				$("#common-alert-popup").removeClass("blind");
				$(".common-alert-txt").text("공간 타입을 선택해주세요.");
			}
		}
		else {
			if ($("#backoffice_info").val().trim().length == 0) {
				$("#backoffice_info").addClass("null-input-border");
			}
		}
	});


})
