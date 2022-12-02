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
			switch ($(".reserve-item.active").text().trim()) {
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
			
			//로딩 화면
			$(".popup-background:eq(1)").removeClass("blind");
			$("#spinner-section").removeClass("blind");
      
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


	$(".reserve_search_paging").on("click", ".paging-box.paging-num", function(){
        var backoffice_no = window.location.href.split("?backoffice_no=")[1].split("&")[0];
        var reserve_state = window.location.href.split("&reserve_state=")[1].split("&")[0];

      //로딩 화면
      $(".popup-background:eq(1)").removeClass("blind");
      $("#spinner-section").removeClass("blind");
      
		if(window.location.href.includes("/backoffice/reserve?")){
	        window.location.href = "/backoffice/reserve?backoffice_no=" + backoffice_no + "&reserve_state=" + reserve_state + "&page=" + $(this).attr("idx");
		}else{
	        var searchword = window.location.href.split("&searchword=")[1].split("&")[0];
	        window.location.href = "/backoffice/search_reserve?backoffice_no=" + backoffice_no + "&reserve_state=" + reserve_state + "&searchword=" + searchword + "&page=" + $(this).attr("idx");
			
		}
    });
    
    // 다음 페이지 리스트로 이동
    $(".reserve_search_paging").on("click", ".next-page-btn", function(){
		var start = Number($($(".paging-box.paging-num")[0]).text()) + 5;
		var last = Number($($(".paging-box.paging-num")[4]).text()) + 5;
		var totalPageCnt = Number($("#totalPageCnt").val());
		
		if($(".before-page-btn").hasClass("hide")){
			$(".before-page-btn").removeClass("hide");
		}
		
		if(last >= totalPageCnt){
			last = totalPageCnt;
			$(".next-page-btn").addClass("hide");
		}
		
		var sample = $(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".paging-num-wrap").append(sample_span);
		}
	});
	
    // 이전 페이지 리스트로 이동
	$(".reserve_search_paging").on("click", ".before-page-btn", function(){
		var start = Number($($(".reserve_search_paging").find(".paging-box.paging-num")[0]).text()) - 5;
		var last = Number($(".reserve_search_paging").find(".paging-box.paging-num:last").text()) - 5;
		
		if(last % 5 != 0){
			last += 5 - (last % 5);
		}
		
		if($(".reserve_search_paging").find(".next-page-btn").hasClass("hide")){
			$(".reserve_search_paging").find(".next-page-btn").removeClass("hide");
		}
		
		if(start == 1){
			$(".reserve_search_paging").find(".before-page-btn").addClass("hide");
		}
		
		var sample = $(".reserve_search_paging").find(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".reserve_search_paging").find(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".reserve_search_paging").find(".paging-num-wrap").append(sample_span);
		}
	});



	// 예약 취소 버튼 클릭 -> 취소 팝업 SHOW
	$(".reserve-ct").on("click", ".reserve_is_cancle", function() {
		$(".popup-background:eq(0)").removeClass("blind");
		$("#reserve-delete-popup").removeClass("blind");

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
			data: {
				backoffice_no: $.cookie("backoffice_no"),
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