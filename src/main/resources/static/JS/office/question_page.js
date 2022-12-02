/**
 * @author 김예은
 */
$(function(){

    /** 문의 리스트 클릭 시, 문의 내용 상세창 SHOW / HIDE **/ 
    $(".question-content-wrap").click(function(){
        $(this).siblings(".detail-question-wrap").toggleClass("blind");
    });

    /** 댓글 삭제 버튼 -> 댓글 컨펌 팝업 SHOW */
    $(".question-history-wrap").on("click", ".question-d-btn", function(){
        var idx = $(this).attr("idx");
        $(".popup-background:eq(0)").removeClass("blind");
        $("#q-delete-popup").removeClass("blind");
        $("#q-delete-btn").attr("idx", idx);
    });
    
    /** 댓글 컨펌 팝업 - 삭제 버튼 */
    $("#q-delete-btn").click(function(){
        $(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        
        location.href="/rence/delete_comment?user_no=" + $.cookie("user_no") + "&comment_no=" + $(this).attr("idx");
    });

    /** 댓글 컨펌 팝업 - 창닫기 버튼 */
    $("#q-delete-closeBtn").click(function(){
        $(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
    });

    // 페이지 번호 클릭 이벤트
    $(".paging-num-wrap").on("click", ".paging-box.paging-num", function(){
		//로딩 화면
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");
		
        location.href = "/rence/question_list?user_no=" + $.cookie("user_no") + "&page=" + $(this).attr("idx");
    });
    
    // 다음 페이지 리스트로 이동
    $(".next-page-btn").click(function(){
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
    $(".before-page-btn").click(function(){
		var start = Number($($(".paging-box.paging-num")[0]).text()) - 5;
		var last = Number($(".paging-box.paging-num:last").text()) - 5;
		
		if(last % 5 != 0){
			last += 5 - (last % 5);
		}
		
		if($(".next-page-btn").hasClass("hide")){
			$(".next-page-btn").removeClass("hide");
		}
		
		if(start == 1){
			$(".before-page-btn").addClass("hide");
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
});