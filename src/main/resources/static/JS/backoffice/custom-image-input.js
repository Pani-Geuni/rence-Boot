/**
 * @author : 김예은
 */

$(function() {
	/** 파일 추가할 시 */
	$("#multipartFile_room").change(function() {
		var img_name = "";
		var length = $('#multipartFile_room').get(0).files.length;

		if (length < 11) {
			for (var i = 0; i < length; i++) {
				var type = $('#multipartFile_room').get(0).files[i].type;
				if (type == "image/jpeg" || type == "image/jpg" || type == "image/png") {
					img_name += $('#multipartFile_room').get(0).files[i].name;
					if (i != length - 1) {
						img_name += " / ";
					}
				} else {
					// file 선택 값 초기화를 위한 코드 (타입을 바꿨다 돌아옴)
					$('#multipartFile_room').attr("type", "radio");
					$('#multipartFile_room').attr("type", "file");
					
					$(".popup-background:eq(1)").removeClass("blind");
					$("#common-alert-popup").removeClass("blind");
					$(".common-alert-txt").text("jpg, jpeg, png 확장자만 선택가능합니다.");
				}
			}

			$(".upload-name").val(img_name);
		} else {
			// file 선택 값 초기화를 위한 코드 (타입을 바꿨다 돌아옴)
			$('#multipartFile_room').attr("type", "radio");
			$('#multipartFile_room').attr("type", "file");
					
			$(".popup-background:eq(1)").removeClass("blind");
			$("#common-alert-popup").removeClass("blind");
			$(".common-alert-txt").text("최대 10개의 이미지 선택이 가능합니다.");
		}

	});
})
