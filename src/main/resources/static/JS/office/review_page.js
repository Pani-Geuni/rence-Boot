/**
* @author 김예은
*/
$(function(){
    // 후기 내용 상세 부분 SHOW
    $(".question-content-wrap").click(function(){
        $(this).siblings(".detail-question-wrap").toggleClass("blind");
    });

    // 후기 삭제 버튼 클릭 이벤트 -> 팝업 SHOW
    $(".question-btn").click(function(){
        $(".popup-background:eq(0)").removeClass("blind");
        $("#q-delete-popup").removeClass("blind");
        $("#q-delete-btn").attr("idx", $(this).attr("idx"));
    });
    
    // 후기 삭제 팝업 - "아니오" 클릭 시, 발생하는 이벤트
    $("#q-delete-closeBtn").click(function(){
    	$(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        $("#q-delete-btn").attr("idx", undefined);
    });

    // 후기 삭제 팝업 - "예" 클릭 시, 발생하는 이벤트
    $("#q-delete-btn").click(function(){
    	$(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        $("#q-delete-btn").attr("idx", undefined);

        location.href = "/rence/delete_review?user_no=" + $.cookie("user_no") + "&review_no="+$("#q-delete-btn").attr("idx");
    });

    // 페이지 번호 클릭 이벤트
    $(".paging-box.paging-num").click(function(){
        location.href = "/rence/review_list?user_no=" + $.cookie("user_no") + "&page=" + $(this).attr("idx");
    });
});